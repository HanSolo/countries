package eu.hansolo.fx.countries.tools;

import eu.hansolo.fx.countries.Country;
import eu.hansolo.fx.countries.evt.CountryEvt;
import eu.hansolo.toolbox.evt.EvtObserver;
import eu.hansolo.toolboxfx.geom.CardinalDirection;
import eu.hansolo.toolboxfx.geom.Location;
import eu.hansolo.toolboxfx.geom.Poi;
import eu.hansolo.toolboxfx.geom.PoiSize;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;


public class CLocation extends Location {
    private final CountryEvt<CLocation>                    UPDATE_EVENT = new CountryEvt<>(CLocation.this, CountryEvt.LOCATION, CLocation.this);
    private       Optional<Country>                        country;
    private       ConnectionPartType                       connectionPartType;
    private       List<EvtObserver<CountryEvt<CLocation>>> observers;


    // ******************** Constructors **************************************
    public CLocation() {
        this(0, 0, 0, Instant.now(), "", "", Color.BLACK, Color.TRANSPARENT);
    }
    public CLocation(final double latitude, final double longitude) {
        this(latitude, longitude, 0, Instant.now(), "", "", Color.BLACK, Color.TRANSPARENT);
    }
    public CLocation(final double latitude, final double longitude, final String name) {
        this(latitude, longitude, 0, Instant.now() ,name, "", Color.BLACK, Color.TRANSPARENT);
    }
    public CLocation(final double latitude, final double longitude, final String name, final Color fill) {
        this(latitude, longitude, 0, Instant.now() ,name, "", fill, Color.TRANSPARENT);
    }
    public CLocation(final double latitude, final double longitude, final String name, final String info) {
        this(latitude, longitude, 0, Instant.now() ,name, info, Color.BLACK, Color.TRANSPARENT);
    }
    public CLocation(final double latitude, final double longitude, final String name, final String info, final Color fill) {
        this(latitude, longitude, 0, Instant.now() ,name, info, fill, Color.TRANSPARENT);
    }
    public CLocation(final double latitude, final double longitude, final double altitude, final String name) {
        this(latitude, longitude, altitude, Instant.now(), name, "", Color.BLACK, Color.TRANSPARENT);
    }
    public CLocation(final double latitude, final double longitude, final double altitude, final Instant timestamp, final String name) {
        this(latitude, longitude, altitude, timestamp, name, "", Color.BLACK, Color.TRANSPARENT);
    }
    public CLocation(final double latitude, final double longitude, final double altitude, final Instant timestamp, final String name, final String info, final Color fill, final Color stroke) {
        super(timestamp, latitude, longitude, altitude, 1, name, info, fill, stroke);
        country            = Optional.empty();
        connectionPartType = ConnectionPartType.NONE;
        observers          = new CopyOnWriteArrayList<>();
    }


    // ******************** Methods *******************************************
    public Optional<Country> getCountry() { return country ;}
    public void setCountry(final Country country) { setCountry(Optional.of(country)); }
    public void setCountry(final Optional<Country> country) {
        this.country = country;
        fireEvt(UPDATE_EVENT);
    }

    public ConnectionPartType getConnectionPartType() { return connectionPartType; }
    public void setConnectionPartType(final ConnectionPartType connectionPartType) { this.connectionPartType = connectionPartType; }

    public void update(final double latitude, final double longitude) { set(latitude, longitude); }

    public void set(final double latitude, final double longitude) {
        set(latitude, longitude, getAltitude(), getTimestamp(), getInfo());
    }
    public void set(final double latitude, final double longitude, final double altitude, final Instant timestamp) {
        set(latitude, longitude, altitude, timestamp, getInfo());
    }
    public void set(final double latitude, final double longitude, final double altitude, final Instant timestamp, final String info) {
        setLatitude(latitude);
        setLongitude(longitude);
        setAltitude(altitude);
        setTimestamp(timestamp);
        setInfo(info);
        fireEvt(UPDATE_EVENT);
    }
    public void set(final CLocation location) {
        setName(location.getName());
        setLatitude(location.getLatitude());
        setLongitude(location.getLongitude());
        setAltitude(location.getAltitude());
        setTimestamp(location.getTimestamp());
        setInfo(location.getInfo());
        setFill(location.getFill());
        setStroke(location.getStroke());
        if (null == connectionPartType) { connectionPartType = location.getConnectionPartType(); } else { connectionPartType = ConnectionPartType.NONE; }
        fireEvt(UPDATE_EVENT);
    }


    // ******************** Event Handling ************************************
    public void setOnEvt(final EvtObserver<CountryEvt<CLocation>> observer) { addEvtObserver(observer); }
    public void addEvtObserver(final EvtObserver<CountryEvt<CLocation>> observer)    { if (!observers.contains(observer)) observers.add(observer); }
    public void removeEvtObserver(final EvtObserver<CountryEvt<CLocation>> observer) { if (observers.contains(observer)) observers.remove(observer); }

    public void fireEvt(final CountryEvt<CLocation> evt) {
        observers.forEach(observer -> observer.handle(evt));
    }


    public Poi toPoi() {
        return new Poi(getLatitude(), getLongitude(), getName(), "", null, PoiSize.NORMAL, getFill(), getStroke(), null, null, null);
    }

    public static CLocation fromPoi(final Poi poi) {
        return CLocationBuilder.create().name(poi.getName()).fill(poi.getFill()).stroke(poi.getStroke()).latitude(poi.getLat()).longitude(poi.getLon()).build();
    }
}
