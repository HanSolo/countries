package eu.hansolo.fx.countries.tools;

import eu.hansolo.fx.countries.Country;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

import java.time.Instant;
import java.util.HashMap;
import java.util.Optional;


public class CLocationBuilder<B extends CLocationBuilder<B>> {
    private final HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected CLocationBuilder() {}


    // ******************** Methods *******************************************
    public static final CLocationBuilder create() {
        return new CLocationBuilder();
    }

    public final B name(final String name) {
        properties.put("name", new SimpleStringProperty(name));
        return (B) this;
    }

    public final B timestamp(final Instant timestamp) {
        properties.put("timestamp", new SimpleObjectProperty<>(timestamp));
        return (B) this;
    }

    public final B latitude(final double latitude) {
        properties.put("latitude", new SimpleDoubleProperty(latitude));
        return (B) this;
    }

    public final B longitude(final double longitude) {
        properties.put("longitude", new SimpleDoubleProperty(longitude));
        return (B) this;
    }

    public final B altitude(final double altitude) {
        properties.put("altitude", new SimpleDoubleProperty(altitude));
        return (B) this;
    }

    public final B info(final String info) {
        properties.put("info", new SimpleStringProperty(info));
        return (B) this;
    }

    public final B fill(final Color fill) {
        properties.put("fill", new SimpleObjectProperty(fill));
        return (B) this;
    }

    public final B stroke(final Color stroke) {
        properties.put("stroke", new SimpleObjectProperty(stroke));
        return (B) this;
    }

    public final B country(final Country country) {
        properties.put("country", new SimpleObjectProperty<>(Optional.of(country)));
        return (B)this;
    }

    public final B connectionPartType(final ConnectionPartType connectionPartType) {
        properties.put("connectionPartType", new SimpleObjectProperty<>(connectionPartType));
        return (B) this;
    }


    public final CLocation build() {
        CLocation control = new CLocation();
        properties.forEach((key, property) -> {
            if ("name".equals(key)) {
                control.setName(((StringProperty) property).get());
            } else if ("timestamp".equals(key)) {
                control.setTimestamp(((ObjectProperty<Instant>) property).get());
            } else if ("latitude".equals(key)) {
                control.setLatitude(((DoubleProperty) property).get());
            } else if ("longitude".equals(key)) {
                control.setLongitude(((DoubleProperty) property).get());
            } else if ("altitude".equals(key)) {
                control.setAltitude(((DoubleProperty) property).get());
            } else if ("info".equals(key)) {
                control.setInfo(((StringProperty) property).get());
            } else if ("fill".equals(key)) {
                control.setFill(((ObjectProperty<Color>) property).get());
            } else if ("stroke".equals(key)) {
                control.setStroke(((ObjectProperty<Color>) property).get());
            } else if ("connectionPartType".equals(key)) {
                control.setConnectionPartType(((ObjectProperty<ConnectionPartType>) property).get());
            } else if ("country".equals(key)) {
                control.setCountry(((ObjectProperty<Optional<Country>>) property).get());
            }
        });
        return control;
    }
}
