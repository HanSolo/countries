package eu.hansolo.fx.countries;

import eu.hansolo.fx.countries.tools.CRegion;
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
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashMap;
import java.util.List;


public class RegionPaneBuilder<B extends RegionPaneBuilder<B>> {
    private final HashMap<String, Property> properties = new HashMap<>();
    private final CRegion                   region;


    // ******************** Constructors **************************************
    protected RegionPaneBuilder(final CRegion region) {
        if (null == region) { throw new IllegalArgumentException("region cannot be null"); }
        this.region = region;
    }


    // ******************** Methods *******************************************
    public static final RegionPaneBuilder create(final CRegion region) { return new RegionPaneBuilder(region); }

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

    public final B hoverEnabled(final boolean hoverEnabled) {
        properties.put("hoverEnabled", new SimpleBooleanProperty(hoverEnabled));
        return (B)this;
    }

    public final B selectionEnabled(final boolean selectionEnabled) {
        properties.put("selectionEnabled", new SimpleBooleanProperty(selectionEnabled));
        return (B)this;
    }

    public final B hoverColor(final Color hoverColor) {
        properties.put("hoverColor", new SimpleObjectProperty<>(hoverColor));
        return (B)this;
    }

    public final B pressedColor(final Color pressedColor) {
        properties.put("pressedColor", new SimpleObjectProperty<>(pressedColor));
        return (B)this;
    }

    public final B selectedColor(final Color selectedColor) {
        properties.put("selectedColor", new SimpleObjectProperty<>(selectedColor));
        return (B)this;
    }

    public final B mouseEnterHandler(final EventHandler<MouseEvent> handler) {
        properties.put("mouseEnterHandler", new SimpleObjectProperty<>(handler));
        return (B)this;
    }

    public final B mousePressHandler(final EventHandler<MouseEvent> handler) {
        properties.put("mousePressHandler", new SimpleObjectProperty<>(handler));
        return (B)this;
    }

    public final B mouseReleaseHandler(final EventHandler<MouseEvent> handler) {
        properties.put("mouseReleaseHandler", new SimpleObjectProperty<>(handler));
        return (B)this;
    }

    public final B mouseExitHandler(final EventHandler<MouseEvent> handler) {
        properties.put("mouseExitHandler", new SimpleObjectProperty<>(handler));
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


    public final RegionPane build() {
        RegionPane control = new RegionPane(region);
        properties.forEach((key, property) -> {
            switch (key) {
                case "prefSize" -> {
                    Dimension2D dim = ((ObjectProperty<Dimension2D>) property).get();
                    control.setPrefSize(dim.getWidth(), dim.getHeight());
                }
                case "minSize" -> {
                    Dimension2D dim = ((ObjectProperty<Dimension2D>) property).get();
                    control.setMinSize(dim.getWidth(), dim.getHeight());
                }
                case "maxSize" -> {
                    Dimension2D dim = ((ObjectProperty<Dimension2D>) property).get();
                    control.setMaxSize(dim.getWidth(), dim.getHeight());
                }
                case "prefWidth"                  -> control.setPrefWidth(((DoubleProperty) property).get());
                case "prefHeight"                 -> control.setPrefHeight(((DoubleProperty) property).get());
                case "minWidth"                   -> control.setMinWidth(((DoubleProperty) property).get());
                case "minHeight"                  -> control.setMinHeight(((DoubleProperty) property).get());
                case "maxWidth"                   -> control.setMaxWidth(((DoubleProperty) property).get());
                case "maxHeight"                  -> control.setMaxHeight(((DoubleProperty) property).get());
                case "scaleX"                     -> control.setScaleX(((DoubleProperty) property).get());
                case "scaleY"                     -> control.setScaleY(((DoubleProperty) property).get());
                case "layoutX"                    -> control.setLayoutX(((DoubleProperty) property).get());
                case "layoutY"                    -> control.setLayoutY(((DoubleProperty) property).get());
                case "translateX"                 -> control.setTranslateX(((DoubleProperty) property).get());
                case "translateY"                 -> control.setTranslateY(((DoubleProperty) property).get());
                case "padding"                    -> control.setPadding(((ObjectProperty<Insets>) property).get());
                case "backgroundColor"            -> control.setBackground(((ObjectProperty<Paint>) property).get());
                case "fill"                       -> control.setFill(((ObjectProperty<Color>) property).get());
                case "stroke"                     -> control.setStroke(((ObjectProperty<Color>) property).get());
                case "lineWidth"                  -> control.setLineWidth(((DoubleProperty) property).get());
                case "poiFill"                    -> control.setPoiFill(((ObjectProperty<Color>) property).get());
                case "poiStroke"                  -> control.setPoiStroke(((ObjectProperty<Color>) property).get());
                case "poiTextFill"                -> control.setPoiTextFill(((ObjectProperty<Color>) property).get());
                case "poisVisible"                -> control.setPoisVisible(((BooleanProperty) property).get());
                case "poiTextVisible"             -> control.setPoiTextVisible(((BooleanProperty) property).get());
                case "pois"                       -> control.addPois(((ObjectProperty<List<Poi>>) property).get());
                case "heatmapVisible"             -> control.setHeatmapVisible(((BooleanProperty) property).get());
                case "heatmapSpots"               -> control.setHeatmapSpots(((ObjectProperty<List<Point>>) property).get());
                case "heatmapColorMapping"        -> control.setHeatmapColorMapping(((ObjectProperty<Mapping>) property).get());
                case "heatmapSpotRadius"          -> control.setHeatmapSpotRadius(((DoubleProperty) property).get());
                case "heatmapFadeColors"          -> control.setHeatmapFadeColors(((BooleanProperty) property).get());
                case "heatmapOpacityDistribution" -> control.setHeatmapOpacityDistribution(((ObjectProperty<OpacityDistribution>) property).get());
                case "heatmapOpacity"             -> control.setHeatmapOpacity(((DoubleProperty) property).get());
                case "connections"                -> control.setConnections(((ObjectProperty<List<Connection>>) property).get());
                case "overlayVisible"             -> control.setOverlayVisible(((BooleanProperty) property).get());
                case "hoverEnabled"               -> control.setHoverEnabled(((BooleanProperty) property).get());
                case "selectionEnabled"           -> control.setSelectionEnabled(((BooleanProperty) property).get());
                case "hoverColor"                 -> control.setHoverColor(((ObjectProperty<Color>) property).get());
                case "pressedColor"               -> control.setPressedColor(((ObjectProperty<Color>) property).get());
                case "selectedColor"              -> control.setSelectedColor(((ObjectProperty<Color>) property).get());
                case "mouseEnterHandler"          -> control.setMouseEnterHandler(((ObjectProperty<EventHandler<MouseEvent>>) property).get());
                case "mousePressHandler"          -> control.setMousePressHandler(((ObjectProperty<EventHandler<MouseEvent>>) property).get());
                case "mouseReleaseHandler"        -> control.setMouseReleaseHandler(((ObjectProperty<EventHandler<MouseEvent>>) property).get());
                case "mouseExitHandler"           -> control.setMouseExitHandler(((ObjectProperty<EventHandler<MouseEvent>>) property).get());
            }
        });
        return control;
    }
}
