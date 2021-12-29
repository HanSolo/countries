package eu.hansolo.fx.countries.tools;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

import java.util.HashMap;


public class ConnectionBuilder<B extends ConnectionBuilder<B>> {
    private final HashMap<String, Property> properties = new HashMap<>();
    private final CLocation                 sourceLocation;
    private final CLocation                 targetLocation;


    // ******************** Constructors **************************************
    protected ConnectionBuilder(final CLocation sourceLocation, final CLocation targetLocation) {
        if (null == sourceLocation) { throw new IllegalArgumentException("sourceLocation cannot be null"); }
        if (null == targetLocation) { throw new IllegalArgumentException("targetLocation cannot be null"); }
        this.sourceLocation = sourceLocation;
        this.targetLocation = targetLocation;
    }


    // ******************** Methods *******************************************
    public static final ConnectionBuilder create(final CLocation sourceLocation, final CLocation targetLocation) {
        return new ConnectionBuilder(sourceLocation, targetLocation);
    }

    public final B name(final String name) {
        properties.put("name", new SimpleStringProperty(name));
        return (B)this;
    }

    public final B value(final double value) {
        properties.put("value", new SimpleDoubleProperty(value));
        return (B)this;
    }

    public final B stroke(final Color stroke) {
        properties.put("stroke", new SimpleObjectProperty<>(stroke));
        return (B)this;
    }

    public final B sourceColor(final Color sourceColor) {
        properties.put("sourceColor", new SimpleObjectProperty<>(sourceColor));
        return (B)this;
    }

    public final B targetColor(final Color targetColor) {
        properties.put("targetColor", new SimpleObjectProperty<>(targetColor));
        return (B)this;
    }

    public final B gradientFill(final boolean gradientFill) {
        properties.put("gradientFill", new SimpleBooleanProperty(gradientFill));
        return (B)this;
    }

    public final B selectedStroke(final Color selectedStroke) {
        properties.put("selectedStroke", new SimpleObjectProperty<>(selectedStroke));
        return (B)this;
    }

    public final B lineWidth(final double lineWidth) {
        properties.put("lineWidth", new SimpleDoubleProperty(lineWidth));
        return (B)this;
    }

    public final B arrowsVisible(final boolean arrowsVisible) {
        properties.put("arrowsVisible", new SimpleBooleanProperty(arrowsVisible));
        return (B)this;
    }

    public final B tooltipText(final String tooltipText) {
        properties.put("tooltipText", new SimpleStringProperty(tooltipText));
        return (B)this;
    }


    public final Connection build() {
        Connection connection = new Connection(sourceLocation, targetLocation);
        properties.forEach((key, property) -> {
            if ("name".equals(key)) {
                connection.setName(((StringProperty) properties.get(key)).get());
            } else if ("value".equals(key)) {
                connection.setValue(((DoubleProperty) properties.get(key)).get());
            } else if ("stroke".equals(key)) {
                connection.setStroke(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("sourceColor".equals(key)) {
                connection.setSourceColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("targetColor".equals(key)) {
                connection.setTargetColor(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("gradientFill".equals(key)) {
                connection.setGradientFill(((BooleanProperty) properties.get(key)).get());
            } else if ("selectedStroke".equals(key)) {
                connection.setSelectedStroke(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("lineWidth".equals(key)) {
                connection.setLineWidth(((DoubleProperty) properties.get(key)).get());
            } else if ("arrowsVisible".equals(key)) {
                connection.setArrowsVisible(((BooleanProperty) properties.get(key)).get());
            } else if ("tooltipText".equals(key)) {
                connection.setTooltipText(((StringProperty) properties.get(key)).get());
            }
        });
        return connection;
    }
}
