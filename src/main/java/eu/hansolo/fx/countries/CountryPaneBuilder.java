package eu.hansolo.fx.countries;

import eu.hansolo.fx.countries.tools.Connection;
import eu.hansolo.toolboxfx.geom.Poi;
import eu.hansolo.toolboxfx.geom.Point;
import eu.hansolo.fx.heatmap.Mapping;
import eu.hansolo.fx.heatmap.OpacityDistribution;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashMap;
import java.util.List;


public class CountryPaneBuilder<B extends CountryPaneBuilder<B>> {
    private final HashMap<String, Property> properties = new HashMap<>();
    private final Country                   country;


    // ******************** Constructors **************************************
    protected CountryPaneBuilder(final Country country) {
        if (null == country) { throw new IllegalArgumentException("country cannot be null"); }
        this.country = country;
    }


    // ******************** Methods *******************************************
    public static final CountryPaneBuilder create(final Country country) { return new CountryPaneBuilder(country); }

    public final B background(final Paint paint) {
        properties.put("background", new SimpleObjectProperty<>(paint));
        return (B) this;
    }

    public final B fill(final Color fill) {
        properties.put("fill", new SimpleObjectProperty<>(fill));
        return (B)this;
    }

    public final B lineWidth(final double lineWidth) {
        properties.put("lineWidth", new SimpleDoubleProperty(lineWidth));
        return (B)this;
    }

    public final B stroke(final Color stroke) {
        properties.put("stroke", new SimpleObjectProperty<>(stroke));
        return (B)this;
    }

    public final B poiFill(final Color poiFill) {
        properties.put("poiFill", new SimpleObjectProperty<>(poiFill));
        return (B)this;
    }

    public final B poiStroke(final Color poiStroke) {
        properties.put("poiStroke", new SimpleObjectProperty<>(poiStroke));
        return (B)this;
    }

    public final B poiTextFill(final Color poiTextFill) {
        properties.put("poiTextFill", new SimpleObjectProperty<>(poiTextFill));
        return (B)this;
    }

    public final B poisVisible(final boolean poisVisible) {
        properties.put("poisVisible", new SimpleBooleanProperty(poisVisible));
        return (B)this;
    }

    public final B poiTextVisible(final boolean poiTextVisible) {
        properties.put("poiTextVisible", new SimpleBooleanProperty(poiTextVisible));
        return (B)this;
    }

    public final B pois(final List<Poi> pois) {
        properties.put("pois", new SimpleObjectProperty<>(pois));
        return (B)this;
    }

    public final B heatmapVisible(final boolean heatmapVisible) {
        properties.put("heatmapVisible", new SimpleBooleanProperty(heatmapVisible));
        return (B)this;
    }

    public final B heatmapSpots(final List<Point> heatmapSpots) {
        properties.put("heatmapSpots", new SimpleObjectProperty<>(heatmapSpots));
        return (B)this;
    }

    public final B heatmapColorMapping(final Mapping heatmapColorMapping) {
        properties.put("heatmapColorMapping", new SimpleObjectProperty<>(heatmapColorMapping));
        return (B) this;
    }

    public final B heatmapSpotRadius(final double heatmapSpotRadius) {
        properties.put("heatmapSpotRadius", new SimpleDoubleProperty(heatmapSpotRadius));
        return (B) this;
    }

    public final B heatmapFadeColors(final boolean heatmapFadeColors) {
        properties.put("heatmapFadeColors", new SimpleBooleanProperty(heatmapFadeColors));
        return (B)this;
    }

    public final B heatmapOpacityDistribution(final OpacityDistribution heatmapOpacityDistribution) {
        properties.put("heatmapOpacityDistribution", new SimpleObjectProperty(heatmapOpacityDistribution));
        return (B)this;
    }

    public final B heatmapOpacity(final double heatmapOpacity) {
        properties.put("heatmapOpacity", new SimpleDoubleProperty(heatmapOpacity));
        return (B) this;
    }

    public final B connections(final List<Connection> connections) {
        properties.put("connections", new SimpleObjectProperty<>(connections));
        return (B)this;
    }

    public final B overlayVisible(final boolean overlayVisible) {
        properties.put("overlayVisible", new SimpleBooleanProperty(overlayVisible));
        return (B)this;
    }

    public final B prefSize(final double width, final double height) {
        properties.put("prefSize", new SimpleObjectProperty<>(new Dimension2D(width, height)));
        return (B)this;
    }
    public final B minSize(final double width, final double height) {
        properties.put("minSize", new SimpleObjectProperty<>(new Dimension2D(width, height)));
        return (B)this;
    }
    public final B maxSize(final double width, final double height) {
        properties.put("maxSize", new SimpleObjectProperty<>(new Dimension2D(width, height)));
        return (B)this;
    }

    public final B prefWidth(final double prefWidth) {
        properties.put("prefWidth", new SimpleDoubleProperty(prefWidth));
        return (B)this;
    }
    public final B prefHeight(final double prefHeight) {
        properties.put("prefHeight", new SimpleDoubleProperty(prefHeight));
        return (B)this;
    }

    public final B minWidth(final double minWidth) {
        properties.put("minWidth", new SimpleDoubleProperty(minWidth));
        return (B)this;
    }
    public final B minHeight(final double minHeight) {
        properties.put("minHeight", new SimpleDoubleProperty(minHeight));
        return (B)this;
    }

    public final B maxWidth(final double maxWidth) {
        properties.put("maxWidth", new SimpleDoubleProperty(maxWidth));
        return (B)this;
    }
    public final B maxHeight(final double maxheight) {
        properties.put("maxHeight", new SimpleDoubleProperty(maxheight));
        return (B)this;
    }

    public final B scaleX(final double scaleX) {
        properties.put("scaleX", new SimpleDoubleProperty(scaleX));
        return (B)this;
    }
    public final B scaleY(final double scaleY) {
        properties.put("scaleY", new SimpleDoubleProperty(scaleY));
        return (B)this;
    }

    public final B layoutX(final double layoutX) {
        properties.put("layoutX", new SimpleDoubleProperty(layoutX));
        return (B)this;
    }
    public final B layoutY(final double layoutY) {
        properties.put("layoutY", new SimpleDoubleProperty(layoutY));
        return (B)this;
    }

    public final B translateX(final double translateX) {
        properties.put("translateX", new SimpleDoubleProperty(translateX));
        return (B)this;
    }
    public final B translateY(final double translateY) {
        properties.put("translateY", new SimpleDoubleProperty(translateY));
        return (B)this;
    }

    public final B padding(final Insets insets) {
        properties.put("padding", new SimpleObjectProperty<>(insets));
        return (B)this;
    }


    public final CountryPane build() {
        CountryPane countryPane = new CountryPane(country);
        for (String key : properties.keySet()) {
            switch (key) {
                case "prefSize" -> {
                    Dimension2D dim = ((ObjectProperty<Dimension2D>) properties.get(key)).get();
                    countryPane.setPrefSize(dim.getWidth(), dim.getHeight());
                }
                case "minSize" -> {
                    Dimension2D dim = ((ObjectProperty<Dimension2D>) properties.get(key)).get();
                    countryPane.setMinSize(dim.getWidth(), dim.getHeight());
                }
                case "maxSize" -> {
                    Dimension2D dim = ((ObjectProperty<Dimension2D>) properties.get(key)).get();
                    countryPane.setMaxSize(dim.getWidth(), dim.getHeight());
                }
                case "prefWidth"                  -> countryPane.setPrefWidth(((DoubleProperty) properties.get(key)).get());
                case "prefHeight"                 -> countryPane.setPrefHeight(((DoubleProperty) properties.get(key)).get());
                case "minWidth"                   -> countryPane.setMinWidth(((DoubleProperty) properties.get(key)).get());
                case "minHeight"                  -> countryPane.setMinHeight(((DoubleProperty) properties.get(key)).get());
                case "maxWidth"                   -> countryPane.setMaxWidth(((DoubleProperty) properties.get(key)).get());
                case "maxHeight"                  -> countryPane.setMaxHeight(((DoubleProperty) properties.get(key)).get());
                case "scaleX"                     -> countryPane.setScaleX(((DoubleProperty) properties.get(key)).get());
                case "scaleY"                     -> countryPane.setScaleY(((DoubleProperty) properties.get(key)).get());
                case "layoutX"                    -> countryPane.setLayoutX(((DoubleProperty) properties.get(key)).get());
                case "layoutY"                    -> countryPane.setLayoutY(((DoubleProperty) properties.get(key)).get());
                case "translateX"                 -> countryPane.setTranslateX(((DoubleProperty) properties.get(key)).get());
                case "translateY"                 -> countryPane.setTranslateY(((DoubleProperty) properties.get(key)).get());
                case "padding"                    -> countryPane.setPadding(((ObjectProperty<Insets>) properties.get(key)).get());
                case "backgroundColor"            -> countryPane.setBackground(((ObjectProperty<Paint>) properties.get(key)).get());
                case "fill"                       -> countryPane.setFill(((ObjectProperty<Color>) properties.get(key)).get());
                case "stroke"                     -> countryPane.setStroke(((ObjectProperty<Color>) properties.get(key)).get());
                case "lineWidth"                  -> countryPane.setLineWidth(((DoubleProperty) properties.get(key)).get());
                case "poiFill"                    -> countryPane.setPoiFill(((ObjectProperty<Color>) properties.get(key)).get());
                case "poiStroke"                  -> countryPane.setPoiStroke(((ObjectProperty<Color>) properties.get(key)).get());
                case "poiTextFill"                -> countryPane.setPoiTextFill(((ObjectProperty<Color>) properties.get(key)).get());
                case "poisVisible"                -> countryPane.setPoisVisible(((BooleanProperty) properties.get(key)).get());
                case "poiTextVisible"             -> countryPane.setPoiTextVisible(((BooleanProperty) properties.get(key)).get());
                case "pois"                       -> countryPane.addPois(((ObjectProperty<List<Poi>>) properties.get(key)).get());
                case "heatmapVisible"             -> countryPane.setHeatmapVisible(((BooleanProperty) properties.get(key)).get());
                case "heatmapSpots"               -> countryPane.setHeatmapSpots(((ObjectProperty<List<Point>>) properties.get(key)).get());
                case "heatmapColorMapping"        -> countryPane.setHeatmapColorMapping(((ObjectProperty<Mapping>) properties.get(key)).get());
                case "heatmapSpotRadius"          -> countryPane.setHeatmapSpotRadius(((DoubleProperty) properties.get(key)).get());
                case "heatmapFadeColors"          -> countryPane.setHeatmapFadeColors(((BooleanProperty) properties.get(key)).get());
                case "heatmapOpacityDistribution" -> countryPane.setHeatmapOpacityDistribution(((ObjectProperty<OpacityDistribution>) properties.get(key)).get());
                case "heatmapOpacity"             -> countryPane.setHeatmapOpacity(((DoubleProperty) properties.get(key)).get());
                case "connections"                -> countryPane.setConnections(((ObjectProperty<List<Connection>>) properties.get(key)).get());
                case "overlayVisible"             -> countryPane.setOverlayVisible(((BooleanProperty) properties.get(key)).get());
            }
        }
        return countryPane;
    }
}
