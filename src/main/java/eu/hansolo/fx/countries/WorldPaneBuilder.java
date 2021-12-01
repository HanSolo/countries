package eu.hansolo.fx.countries;

import eu.hansolo.fx.countries.tools.Mapping;
import eu.hansolo.fx.countries.tools.OpacityDistribution;
import eu.hansolo.fx.countries.tools.Poi;
import eu.hansolo.fx.countries.tools.Point;
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
    private HashMap<String, Property> properties = new HashMap<>();


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
            if ("prefSize".equals(key)) {
                Dimension2D dim = ((ObjectProperty<Dimension2D>) properties.get(key)).get();
                worldPane.setPrefSize(dim.getWidth(), dim.getHeight());
            } else if ("minSize".equals(key)) {
                Dimension2D dim = ((ObjectProperty<Dimension2D>) properties.get(key)).get();
                worldPane.setMinSize(dim.getWidth(), dim.getHeight());
            } else if ("maxSize".equals(key)) {
                Dimension2D dim = ((ObjectProperty<Dimension2D>) properties.get(key)).get();
                worldPane.setMaxSize(dim.getWidth(), dim.getHeight());
            } else if ("prefWidth".equals(key)) {
                worldPane.setPrefWidth(((DoubleProperty) properties.get(key)).get());
            } else if ("prefHeight".equals(key)) {
                worldPane.setPrefHeight(((DoubleProperty) properties.get(key)).get());
            } else if ("minWidth".equals(key)) {
                worldPane.setMinWidth(((DoubleProperty) properties.get(key)).get());
            } else if ("minHeight".equals(key)) {
                worldPane.setMinHeight(((DoubleProperty) properties.get(key)).get());
            } else if ("maxWidth".equals(key)) {
                worldPane.setMaxWidth(((DoubleProperty) properties.get(key)).get());
            } else if ("maxHeight".equals(key)) {
                worldPane.setMaxHeight(((DoubleProperty) properties.get(key)).get());
            } else if ("scaleX".equals(key)) {
                worldPane.setScaleX(((DoubleProperty) properties.get(key)).get());
            } else if ("scaleY".equals(key)) {
                worldPane.setScaleY(((DoubleProperty) properties.get(key)).get());
            } else if ("layoutX".equals(key)) {
                worldPane.setLayoutX(((DoubleProperty) properties.get(key)).get());
            } else if ("layoutY".equals(key)) {
                worldPane.setLayoutY(((DoubleProperty) properties.get(key)).get());
            } else if ("translateX".equals(key)) {
                worldPane.setTranslateX(((DoubleProperty) properties.get(key)).get());
            } else if ("translateY".equals(key)) {
                worldPane.setTranslateY(((DoubleProperty) properties.get(key)).get());
            } else if ("padding".equals(key)) {
                worldPane.setPadding(((ObjectProperty<Insets>) properties.get(key)).get());
            } else if ("backgroundColor".equals(key)) {
                worldPane.setBackground(((ObjectProperty<Paint>) properties.get(key)).get());
            } else if ("fill".equals(key)) {
                worldPane.setFill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("stroke".equals(key)) {
                worldPane.setStroke(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("lineWidth".equals(key)) {
                worldPane.setLineWidth(((DoubleProperty) properties.get(key)).get());
            } else if ("poiFill".equals(key)) {
                worldPane.setPoiFill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("poiStroke".equals(key)) {
                worldPane.setPoiStroke(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("poiTextFill".equals(key)) {
                worldPane.setPoiTextFill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("poisVisible".equals(key)) {
                worldPane.setPoisVisible(((BooleanProperty) properties.get(key)).get());
            } else if ("poiTextVisible".equals(key)) {
                worldPane.setPoiTextVisible(((BooleanProperty) properties.get(key)).get());
            } else if ("pois".equals(key)) {
                worldPane.addPois(((ObjectProperty<List<Poi>>) properties.get(key)).get());
            } else if ("heatmapVisible".equals(key)) {
                worldPane.setHeatmapVisible(((BooleanProperty) properties.get(key)).get());
            } else if ("heatmapSpots".equals(key)) {
                worldPane.setHeatmapSpots(((ObjectProperty<List<Point>>) properties.get(key)).get());
            } else if ("heatmapColorMapping".equals(key)) {
                worldPane.setHeatmapColorMapping(((ObjectProperty<Mapping>) properties.get(key)).get());
            } else if ("heatmapSpotRadius".equals(key)) {
                worldPane.setHeatmapSpotRadius(((DoubleProperty) properties.get(key)).get());
            } else if ("heatmapFadeColors".equals(key)) {
                worldPane.setHeatmapFadeColors(((BooleanProperty) properties.get(key)).get());
            } else if ("heatmapOpacityDistribution".equals(key)) {
                worldPane.setHeatmapOpacityDistribution(((ObjectProperty<OpacityDistribution>) properties.get(key)).get());
            } else if ("heatmapOpacity".equals(key)) {
                worldPane.setHeatmapOpacity(((DoubleProperty) properties.get(key)).get());
            } else if ("hoverEnabled".equals(key)) {
                worldPane.setHoverEnabled(((BooleanProperty) properties.get(key)).get());
            } else if ("selectionEnabled".equals(key)) {
                worldPane.setSelectionEnabled(((BooleanProperty) properties.get(key)).get());
            } else if ("hoverColor".equals(key)) {
                worldPane.setHoverColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("pressedColor".equals(key)) {
                worldPane.setPressedColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("selectedColor".equals(key)) {
                worldPane.setSelectedColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("mouseEnterHandler".equals(key)) {
                worldPane.setMouseEnterHandler(((ObjectProperty<EventHandler<MouseEvent>>) properties.get(key)).get());
            } else if ("mousePressHandler".equals(key)) {
                worldPane.setMousePressHandler(((ObjectProperty<EventHandler<MouseEvent>>) properties.get(key)).get());
            } else if ("mouseReleaseHandler".equals(key)) {
                worldPane.setMouseReleaseHandler(((ObjectProperty<EventHandler<MouseEvent>>) properties.get(key)).get());
            } else if ("mouseExitHandler".equals(key)) {
                worldPane.setMouseExitHandler(((ObjectProperty<EventHandler<MouseEvent>>) properties.get(key)).get());
            }
        }
        return worldPane;
    }
}
