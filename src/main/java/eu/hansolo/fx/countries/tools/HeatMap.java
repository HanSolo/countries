package eu.hansolo.fx.countries.tools;

import javafx.animation.Interpolator;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HeatMap extends ImageView {
    private static final SnapshotParameters  SNAPSHOT_PARAMETERS = new SnapshotParameters();
    private              int                 width;
    private              int                 height;
    private              double              oldWidth;
    private              double              oldHeight;
    private              double              scaleX;
    private              double              scaleY;
    private              List<Point>         spotList;
    private              Map<String, Image>  spotImages;
    private              Mapping             colorMapping;
    private              LinearGradient      mappingGradient;
    private              boolean             fadeColors;
    private              double              spotRadius;
    private              OpacityDistribution opacityDistribution;
    private              Image               spotImage;
    private              Canvas              canvas;
    private              GraphicsContext     ctx;
    private              WritableImage       monochromeImage;
    private              WritableImage       heatMap;


    // ******************** Constructors **************************************
    public HeatMap() {
        this(100, 100, ColorMapping.LIME_YELLOW_RED, 15.5, true, 0.5, OpacityDistribution.CUSTOM);
    }
    public HeatMap(final double width, final double height) {
        this(width, height, ColorMapping.LIME_YELLOW_RED, 15.5, true, 0.5, OpacityDistribution.CUSTOM);
    }
    public HeatMap(final double width, final double height, Mapping colorMapping) {
        this(width, height, colorMapping, 15.5, true, 0.5, OpacityDistribution.CUSTOM);
    }
    public HeatMap(final double width, final double height, Mapping colorMapping, final double spotRadius) {
        this(width, height, colorMapping, spotRadius, true, 0.5, OpacityDistribution.CUSTOM);
    }
    public HeatMap(final double width, final double height, Mapping colorMapping, final double spotRadius, final boolean fadeColors, final double heatMapOpacity, final OpacityDistribution opacityDistribution) {
        super();
        this.width               = (int) Helper.clamp(10, 4096, width);
        this.height              = (int) Helper.clamp(10, 4096, height);
        this.oldWidth            = (int) Helper.clamp(10, 4096, width);
        this.oldHeight           = (int) Helper.clamp(10, 4096, height);
        this.colorMapping        = null == colorMapping ? ColorMapping.INFRARED_4 : colorMapping;
        this.spotRadius          = Helper.clamp(1, 30, spotRadius);
        this.fadeColors          = fadeColors;
        this.opacityDistribution = null == opacityDistribution ? OpacityDistribution.LINEAR : opacityDistribution;

        spotList                 = new ArrayList<>();
        spotImages               = new HashMap<>();
        mappingGradient          = this.colorMapping.getGradient();
        spotImage                = createSpotImage(this.spotRadius, this.opacityDistribution);
        canvas                   = new Canvas(this.width, this.height);
        ctx                      = canvas.getGraphicsContext2D();
        monochromeImage          = new WritableImage((int) width, (int) height);

        SNAPSHOT_PARAMETERS.setFill(Color.TRANSPARENT);
        setImage(heatMap);
        setMouseTransparent(true);
        setOpacity(heatMapOpacity);
        registerListeners();
    }

    public void registerListeners() {
        fitWidthProperty().addListener(o -> resize());
        fitHeightProperty().addListener(o -> resize());
    }


    // ******************** Methods *******************************************
    public List<Point> getSpots() { return spotList; }
    public void setSpots(final List<Point> spots) {
        spotList.clear();
        addSpots(spots);
    }

    /**
     * Add a list of spots and update the heatmap after all spots
     * have been added
     * @param spots
     */
    public void addSpots(final Point... spots) { addSpots(Arrays.asList(spots)); }
    /**
     * Add a list of spots and update the heatmap after all spots
     * have been added
     * @param spots
     */
    public void addSpots(final List<Point> spots) {
        spots.forEach(spot -> {
            spotList.add(new Point(spot.getX(), spot.getY()));
            ctx.drawImage(spotImage, spot.getX() - spotRadius, spot.getY() - spotRadius);
        });
        updateHeatMap();
    }

    /**
     * If you don't need to weight spots you could use this method which
     * will create spots that always use the global weight
     * @param x
     * @param y
     */
    public void addSpot(final double x, final double y) { addSpot(new Point(x,y), spotImage, spotRadius, spotRadius); }
    /**
     * If you don't need to weight spots you could use this method which
     * will create spots that always use the global weight
     * @param spot
     */
    public void addSpot(final Point spot) { addSpot(spot, spotImage, spotRadius, spotRadius); }
    /**
     * Visualizes a spot with the given radius and opacity gradient
     * @param x
     * @param y
     * @param offsetX
     * @param offsetY
     * @param spotRadius
     * @param opacityDistribution
     */
    public void addSpot(final double x, final double y, final double offsetX, final double offsetY, final double spotRadius, final OpacityDistribution opacityDistribution) {
        this.spotRadius          = spotRadius;
        this.opacityDistribution = opacityDistribution;
        this.spotImage           = createSpotImage(spotRadius, opacityDistribution);
        addSpot(new Point(x, y), spotImage, offsetX, offsetY);
    }
    /**
     * Visualizes a spot with a given image at the given position and with
     * the given offset. So one could use different weighted images for different
     * kinds of spots (e.g. important events more opaque as unimportant spots)
     * @param x
     * @param y
     * @param spotImage
     * @param offsetX
     * @param offsetY
     */
    public void addSpot(final double x, final double y, final Image spotImage, final double offsetX, final double offsetY) {
        addSpot(new Point(x, y), spotImage, offsetX, offsetY);
    }
    /**
     * Visualizes a spot with a given image at the given position and with
     * the given offset. So one could use different weighted images for different
     * kinds of spots (e.g. important events more opaque as unimportant spots)
     * @param spot
     * @param spotImage
     * @param offsetX
     * @param offsetY
     */
    public void addSpot(final Point spot, final Image spotImage, final double offsetX, final double offsetY) {
        //spot.scale(scaleX, scaleY);
        spotList.add(spot);
        ctx.drawImage(spotImage, spot.getX() - offsetX, spot.getY() - offsetY);
        updateHeatMap();
    }

    /**
     * Calling this method will lead to a clean new heat map without any data
     */
    public void clearHeatMap() {
        spotList.clear();
        ctx.clearRect(0, 0, width, height);
        monochromeImage = new WritableImage(width, height);
        updateHeatMap();
    }

    /**
     * Returns the used color mapping with the gradient that is used
     * to visualize the data
     * @return
     */
    public Mapping getColorMapping() { return colorMapping; }
    /**
     * The ColorMapping enum contains some examples for color mappings
     * that might be useful to visualize data and here you could set
     * the one you like most. Setting another color mapping will recreate
     * the heat map automatically.
     * @param colorMapping
     */
    public void setColorMapping(final Mapping colorMapping) {
        this.colorMapping = colorMapping;
        mappingGradient = colorMapping.getGradient();
        updateHeatMap();
    }

    /**
     * Returns true if the heat map is used to visualize frequencies (default)
     * @return true if the heat map is used to visualize frequencies
     */
    public boolean isFadeColors() { return fadeColors; }
    /**
     * If true each event will be visualized by a radial gradient
     * with the colors from the given color mapping and decreasing
     * opacity from the inside to the outside. If you set it to false
     * the color opacity won't fade out but will be opaque. This might
     * be handy if you would like to visualize the density instead of
     * the frequency
     * @param fadeColors
     */
    public void setFadeColors(final boolean fadeColors) {
        this.fadeColors = fadeColors;
        updateHeatMap();
    }

    /**
     * Returns the radius of the circle that is used to visualize a
     * spot.
     * @return the radius of the circle that is used to visualize a spot
     */
    public double getSpotRadius() { return spotRadius; }
    /**
     * Each spot will be visualized by a circle filled with a radial
     * gradient with decreasing opacity from the inside to the outside.
     * If you have lot's of spots it makes sense to set the spot radius
     * to a smaller value. The default value is 15.5
     * @param radius
     */
    public void setSpotRadius(final double radius) {
        this.spotRadius = radius < 1 ? 1 : radius;
        spotImage = createSpotImage(this.spotRadius, opacityDistribution);
    }

    /**
     * Returns the opacity distribution that will be used to visualize
     * the events in the monochrome map. If you have lot's of events
     * it makes sense to reduce the radius and the set the opacity
     * distribution to exponential.
     * @return the opacity distribution of events in the monochrome map
     */
    public OpacityDistribution getOpacityDistribution() { return opacityDistribution; }
    /**
     * Changing the opacity distribution will affect the smoothing of
     * the heat map. If you choose a linear opacity distribution you will
     * see bigger colored dots for each event than using the exponential
     * opacity distribution (at the same event radius).
     * @param opacityDistribution
     */
    public void setOpacityDistribution(final OpacityDistribution opacityDistribution) {
        this.opacityDistribution = opacityDistribution;
        spotImage                = createSpotImage(spotRadius, this.opacityDistribution);
    }

    /**
     * Because the heat map is based on images you have to create a new
     * writeable image each time you would like to change the size of
     * the heatmap
     * @param width
     * @param height
     */
    public void setSize(final double width, final double height) {
        setFitWidth(width);
        setFitHeight(height);
    }

    /**
     * Saves the current heat map image as png with the given name to the desktop folder of the current user
     * @param fileName
     */
    public void saveAsPng(final String fileName) { saveAsPng(this, fileName + ".png"); }
    /**
     * Saves the given node as png with the given name to the desktop folder of the current user
     * @param node
     * @param fileName
     */
    public void saveAsPng(final Node node, final String fileName) {
        new Thread(() ->
            Platform.runLater(() -> {
               final String target = System.getProperty("user.home") + "/Desktop/" + fileName + ".png";
               try {
                   ImageIO.write(SwingFXUtils.fromFXImage(node.snapshot(SNAPSHOT_PARAMETERS, null), null), "png", new File(target));
               } catch (IOException exception) {
                   // handle exception here
               }
            })
        ).start();
    }

    /**
     * Create an image that contains a circle filled with a
     * radial gradient from white to transparent
     * @param radius
     * @return an image that contains a filled circle
     */
    public Image createSpotImage(final double radius, final OpacityDistribution opacityDistribution) {
        double r = radius < 1 ? 1 : radius;
        if (spotImages.containsKey(opacityDistribution.name() + r)) {
            return spotImages.get(opacityDistribution.name() + r);
        }
        Stop[] stops = new Stop[11];
        for (int i = 0; i < 11; i++) {
            stops[i] = new Stop(i * 0.1, Color.rgb(255, 255, 255, opacityDistribution.getDistribution()[i]));
        }

        int           diameter      = (int) (r * 2);
        WritableImage raster        = new WritableImage(diameter, diameter);
        PixelWriter   pixelWriter   = raster.getPixelWriter();
        double        maxDistFactor = 1 / r;
        Color         pixelColor;
        for (int y = 0; y < diameter; y++) {
            for (int x = 0; x < diameter; x++) {
                double deltaX   = r - x;
                double deltaY   = r - y;
                double distance = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
                double fraction = maxDistFactor * distance;
                for (int i = 0; i < 10; i++) {
                    if (fraction >= stops[i].getOffset() && fraction <= stops[i + 1].getOffset()) {
                    //if (Double.compare(fraction, stops[i].getOffset()) >= 0 && Double.compare(fraction, stops[i + 1].getOffset()) <= 0) {
                        pixelColor = (Color) Interpolator.LINEAR.interpolate(stops[i].getColor(), stops[i + 1].getColor(), (fraction - stops[i].getOffset()) / 0.1);
                        pixelWriter.setColor(x, y, pixelColor);
                        break;
                    }
                }
            }
        }
        spotImages.put(opacityDistribution.name() + r, raster);
        return raster;
    }

    /**
     * Updates each spot in the monochrome map to the given opacity gradient
     * which could be useful to reduce oversmoothing
     * @param opacityDistribution
     */
    public void updateMonochromeMap(final OpacityDistribution opacityDistribution) {
        ctx.clearRect(0, 0, width, height);
        spotImage = createSpotImage(spotRadius, opacityDistribution);
        spotList.forEach(spot -> ctx.drawImage(spotImage, spot.getX() - spotRadius, spot.getY() - spotRadius));
        updateHeatMap();
    }

    /**
     * Recreates the heatmap based on the current monochrome map.
     * Using this approach makes it easy to change the used color
     * mapping.
     */
    private void updateHeatMap() {
        canvas.snapshot(SNAPSHOT_PARAMETERS, monochromeImage);

        heatMap = new WritableImage(width, height);

        Color       colorFromMonoChromeImage;
        double      brightness;
        Color       mappedColor;
        PixelWriter pixelWriter = heatMap.getPixelWriter();
        PixelReader pixelReader = monochromeImage.getPixelReader();
        for (int y = 0 ; y < height ; y++) {
            for (int x = 0 ; x < width ; x++) {
                colorFromMonoChromeImage = pixelReader.getColor(x, y);
                brightness               = colorFromMonoChromeImage.getOpacity();
                mappedColor              = Helper.getColorAt(mappingGradient, brightness);
                pixelWriter.setColor(x, y, fadeColors ? Color.color(mappedColor.getRed(), mappedColor.getGreen(), mappedColor.getBlue(), brightness) : mappedColor);
            }
        }
        setImage(heatMap);
    }

    private void updateSpots() {
        ctx.clearRect(0, 0, width, height);
        spotList.forEach(spot -> ctx.drawImage(spotImage, spot.getX() - spotRadius, spot.getY() - spotRadius));
    }

    private void resize() {
        oldWidth  = width;
        oldHeight = height;
        width     = (int) getFitWidth();
        height    = (int) getFitHeight();

        canvas.setWidth(width);
        canvas.setHeight(height);

        if (width > 0 && height > 0 && oldWidth > 0 && oldHeight > 0) {
            scaleX     = width / oldWidth;
            scaleY     = height / oldHeight;
            spotRadius *= scaleX;
            spotImage  = createSpotImage(spotRadius, opacityDistribution);
            spotList.forEach(spot -> spot.scale(scaleX, scaleY));
            //updateSpots();
        }
    }
}
