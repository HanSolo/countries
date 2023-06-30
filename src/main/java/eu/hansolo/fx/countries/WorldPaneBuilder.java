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
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashMap;
import java.util.List;


public class WorldPaneBuilder<B extends WorldPaneBuilder<B>> {
    private final HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected WorldPaneBuilder() {}


    // ******************** Methods *******************************************
    public static final WorldPaneBuilder create() { return new WorldPaneBuilder(); }

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


    public final WorldPane build() {
        WorldPane worldPane = new WorldPane();
        for (String key : properties.keySet()) {
            switch (key) {
                case "prefSize" -> {
                    Dimension2D dim = ((ObjectProperty<Dimension2D>) properties.get(key)).get();
                    worldPane.setPrefSize(dim.getWidth(), dim.getHeight());
                }
                case "minSize" -> {
                    Dimension2D dim = ((ObjectProperty<Dimension2D>) properties.get(key)).get();
                    worldPane.setMinSize(dim.getWidth(), dim.getHeight());
                }
                case "maxSize" -> {
                    Dimension2D dim = ((ObjectProperty<Dimension2D>) properties.get(key)).get();
                    worldPane.setMaxSize(dim.getWidth(), dim.getHeight());
                }
                case "prefWidth"                  -> worldPane.setPrefWidth(((DoubleProperty) properties.get(key)).get());
                case "prefHeight"                 -> worldPane.setPrefHeight(((DoubleProperty) properties.get(key)).get());
                case "minWidth"                   -> worldPane.setMinWidth(((DoubleProperty) properties.get(key)).get());
                case "minHeight"                  -> worldPane.setMinHeight(((DoubleProperty) properties.get(key)).get());
                case "maxWidth"                   -> worldPane.setMaxWidth(((DoubleProperty) properties.get(key)).get());
                case "maxHeight"                  -> worldPane.setMaxHeight(((DoubleProperty) properties.get(key)).get());
                case "scaleX"                     -> worldPane.setScaleX(((DoubleProperty) properties.get(key)).get());
                case "scaleY"                     -> worldPane.setScaleY(((DoubleProperty) properties.get(key)).get());
                case "layoutX"                    -> worldPane.setLayoutX(((DoubleProperty) properties.get(key)).get());
                case "layoutY"                    -> worldPane.setLayoutY(((DoubleProperty) properties.get(key)).get());
                case "translateX"                 -> worldPane.setTranslateX(((DoubleProperty) properties.get(key)).get());
                case "translateY"                 -> worldPane.setTranslateY(((DoubleProperty) properties.get(key)).get());
                case "padding"                    -> worldPane.setPadding(((ObjectProperty<Insets>) properties.get(key)).get());
                case "backgroundColor"            -> worldPane.setBackground(((ObjectProperty<Paint>) properties.get(key)).get());
                case "fill"                       -> worldPane.setFill(((ObjectProperty<Color>) properties.get(key)).get());
                case "stroke"                     -> worldPane.setStroke(((ObjectProperty<Color>) properties.get(key)).get());
                case "lineWidth"                  -> worldPane.setLineWidth(((DoubleProperty) properties.get(key)).get());
                case "poiFill"                    -> worldPane.setPoiFill(((ObjectProperty<Color>) properties.get(key)).get());
                case "poiStroke"                  -> worldPane.setPoiStroke(((ObjectProperty<Color>) properties.get(key)).get());
                case "poiTextFill"                -> worldPane.setPoiTextFill(((ObjectProperty<Color>) properties.get(key)).get());
                case "poisVisible"                -> worldPane.setPoisVisible(((BooleanProperty) properties.get(key)).get());
                case "poiTextVisible"             -> worldPane.setPoiTextVisible(((BooleanProperty) properties.get(key)).get());
                case "pois"                       -> worldPane.addPois(((ObjectProperty<List<Poi>>) properties.get(key)).get());
                case "heatmapVisible"             -> worldPane.setHeatmapVisible(((BooleanProperty) properties.get(key)).get());
                case "heatmapSpots"               -> worldPane.setHeatmapSpots(((ObjectProperty<List<Point>>) properties.get(key)).get());
                case "heatmapColorMapping"        -> worldPane.setHeatmapColorMapping(((ObjectProperty<Mapping>) properties.get(key)).get());
                case "heatmapSpotRadius"          -> worldPane.setHeatmapSpotRadius(((DoubleProperty) properties.get(key)).get());
                case "heatmapFadeColors"          -> worldPane.setHeatmapFadeColors(((BooleanProperty) properties.get(key)).get());
                case "heatmapOpacityDistribution" -> worldPane.setHeatmapOpacityDistribution(((ObjectProperty<OpacityDistribution>) properties.get(key)).get());
                case "heatmapOpacity"             -> worldPane.setHeatmapOpacity(((DoubleProperty) properties.get(key)).get());
                case "connections"                -> worldPane.setConnections(((ObjectProperty<List<Connection>>) properties.get(key)).get());
                case "overlayVisible"             -> worldPane.setOverlayVisible(((BooleanProperty) properties.get(key)).get());
                case "hoverEnabled"               -> worldPane.setHoverEnabled(((BooleanProperty) properties.get(key)).get());
                case "selectionEnabled"           -> worldPane.setSelectionEnabled(((BooleanProperty) properties.get(key)).get());
                case "hoverColor"                 -> worldPane.setHoverColor(((ObjectProperty<Color>) properties.get(key)).get());
                case "pressedColor"               -> worldPane.setPressedColor(((ObjectProperty<Color>) properties.get(key)).get());
                case "selectedColor"              -> worldPane.setSelectedColor(((ObjectProperty<Color>) properties.get(key)).get());
                case "mouseEnterHandler"          -> worldPane.setMouseEnterHandler(((ObjectProperty<EventHandler<MouseEvent>>) properties.get(key)).get());
                case "mousePressHandler"          -> worldPane.setMousePressHandler(((ObjectProperty<EventHandler<MouseEvent>>) properties.get(key)).get());
                case "mouseReleaseHandler"        -> worldPane.setMouseReleaseHandler(((ObjectProperty<EventHandler<MouseEvent>>) properties.get(key)).get());
                case "mouseExitHandler"           -> worldPane.setMouseExitHandler(((ObjectProperty<EventHandler<MouseEvent>>) properties.get(key)).get());
            }
        }
        return worldPane;
    }
}
