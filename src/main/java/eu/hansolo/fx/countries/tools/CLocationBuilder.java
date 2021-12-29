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
        CLocation location = new CLocation();
        properties.forEach((key, property) -> {
            if ("name".equals(key)) {
                location.setName(((StringProperty) properties.get(key)).get());
            } else if ("timestamp".equals(key)) {
                location.setTimestamp(((ObjectProperty<Instant>) properties.get(key)).get());
            } else if ("latitude".equals(key)) {
                location.setLatitude(((DoubleProperty) properties.get(key)).get());
            } else if ("longitude".equals(key)) {
                location.setLongitude(((DoubleProperty) properties.get(key)).get());
            } else if ("altitude".equals(key)) {
                location.setAltitude(((DoubleProperty) properties.get(key)).get());
            } else if ("info".equals(key)) {
                location.setInfo(((StringProperty) properties.get(key)).get());
            } else if ("fill".equals(key)) {
                location.setFill(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("stroke".equals(key)) {
                location.setStroke(((ObjectProperty<Color>) properties.get(key)).get());
            } else if ("connectionPartType".equals(key)) {
                location.setConnectionPartType(((ObjectProperty<ConnectionPartType>) properties.get(key)).get());
            } else if ("country".equals(key)) {
                location.setCountry(((ObjectProperty<Optional<Country>>) properties.get(key)).get());
            }
        });
        return location;
    }
}
