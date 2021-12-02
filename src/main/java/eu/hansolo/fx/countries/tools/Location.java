package eu.hansolo.fx.countries.tools;

import eu.hansolo.fx.countries.evt.EvtType;
import eu.hansolo.fx.countries.evt.Evt;
import eu.hansolo.fx.countries.evt.EvtObserver;
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
import java.util.concurrent.CopyOnWriteArrayList;


public class Location {
    public enum CardinalDirection {
        N("North", 348.75, 11.25), NNE("North North-East", 11.25, 33.75), NE("North-East", 33.75, 56.25), ENE("East North-East", 56.25, 78.75), E("East", 78.75, 101.25), ESE("East South-East", 101.25, 123.75),
        SE("South-East", 123.75, 146.25), SSE("South South-East", 146.25, 168.75), S("South", 168.75, 191.25), SSW("South South-West", 191.25, 213.75), SW("South-West", 213.75, 236.25), WSW("West South-West", 236.25, 258.75),
        W("West", 258.75, 281.25), WNW("West North-West", 281.25, 303.75), NW("North-West", 303.75, 326.25), NNW("North North-West", 326.25, 348.75);

        public String direction;
        public double from;
        public double to;

        CardinalDirection(final String DIRECTION, final double FROM, final double TO) {
            direction = DIRECTION;
            from = FROM;
            to = TO;
        }
    }


    private final Evt<Location>               UPDATE_EVENT = new Evt<>(Location.this, EvtType.UPDATE);
    private       String                      _name;
    private       StringProperty              name;
    private       Instant                     _timestamp;
    private       ObjectProperty<Instant>     timestamp;
    private       double                      _latitude;
    private       DoubleProperty              latitude;
    private       double                      _longitude;
    private       DoubleProperty              longitude;
    private       double                      _altitude;
    private       DoubleProperty              altitude;
    private       String                      _info;
    private       StringProperty              info;
    private       Color                       _fill;
    private       ObjectProperty<Color>       fill;
    private       Color                       _stroke;
    private       ObjectProperty<Color>       stroke;
    private       ConnectionPartType          connectionPartType;
    private       List<EvtObserver<Location>> observers;
    private       EventHandler<MouseEvent>    mouseEnterHandler;
    private       EventHandler<MouseEvent>    mousePressHandler;
    private       EventHandler<MouseEvent>    mouseReleaseHandler;
    private       EventHandler<MouseEvent>    mouseExitHandler;


    // ******************** Constructors **************************************
    public Location() {
        this(0, 0, 0, Instant.now(), "", "", Color.BLACK, Color.TRANSPARENT);
    }
    public Location(final double latitude, final double longitude) {
        this(latitude, longitude, 0, Instant.now(), "", "", Color.BLACK, Color.TRANSPARENT);
    }
    public Location(final double latitude, final double longitude, final String name) {
        this(latitude, longitude, 0, Instant.now() ,name, "", Color.BLACK, Color.TRANSPARENT);
    }
    public Location(final double latitude, final double longitude, final String name, final Color fill) {
        this(latitude, longitude, 0, Instant.now() ,name, "", fill, Color.TRANSPARENT);
    }
    public Location(final double latitude, final double longitude, final String name, final String info) {
        this(latitude, longitude, 0, Instant.now() ,name, info, Color.BLACK, Color.TRANSPARENT);
    }
    public Location(final double latitude, final double longitude, final String name, final String info, final Color fill) {
        this(latitude, longitude, 0, Instant.now() ,name, info, fill, Color.TRANSPARENT);
    }
    public Location(final double latitude, final double longitude, final double altitude, final String name) {
        this(latitude, longitude, altitude, Instant.now(), name, "", Color.BLACK, Color.TRANSPARENT);
    }
    public Location(final double latitude, final double longitude, final double altitude, final Instant timestamp, final String name) {
        this(latitude, longitude, altitude, timestamp, name, "", Color.BLACK, Color.TRANSPARENT);
    }
    public Location(final double latitude, final double longitude, final double altitude, final Instant timestamp, final String name, final String info, final Color fill, final Color stroke) {
        _name              = name;
        _latitude          = latitude;
        _longitude         = longitude;
        _altitude          = altitude;
        _timestamp         = timestamp;
        _info              = info;
        _fill              = fill;
        _stroke            = stroke;
        connectionPartType = ConnectionPartType.NONE;
        observers          = new CopyOnWriteArrayList<>();
    }


    // ******************** Methods *******************************************
    public String getName() { return null == name ? _name : name.get(); }
    public void setName(final String name) {
        if (null == this.name) {
            _name = name;
            fireEvt(UPDATE_EVENT);
        } else {
            this.name.set(name);
        }
    }
    public StringProperty nameProperty() {
        if (null == name) {
            name = new StringPropertyBase(_name) {
                @Override protected void invalidated() { fireEvt(UPDATE_EVENT); }
                @Override public Object getBean() { return Location.this; }
                @Override public String getName() { return "name"; }
            };
            _name = null;
        }
        return name;
    }

    public Instant getTimestamp() { return null == timestamp ? _timestamp : timestamp.get(); }
    public long getTimestampInSeconds() { return getTimestamp().getEpochSecond(); }
    public void setTimestamp(final Instant timestamp) {
        if (null == this.timestamp) {
            _timestamp = timestamp;
            fireEvt(UPDATE_EVENT);
        } else {
            this.timestamp.set(timestamp);
        }
    }
    public ObjectProperty<Instant> timestampProperty() {
        if (null == timestamp) {
            timestamp = new ObjectPropertyBase<Instant>(_timestamp) {
                @Override protected void invalidated() { fireEvt(UPDATE_EVENT); }
                @Override public Object getBean() { return Location.this; }
                @Override public String getName() { return "timestamp"; }
            };
            _timestamp = null;
        }
        return timestamp;
    }

    public double getLatitude() { return null == latitude ? _latitude : latitude.get(); }
    public void setLatitude(final double latitude) {
        if (null == this.latitude) {
            _latitude = latitude;
            fireEvt(UPDATE_EVENT);
        } else {
            this.latitude.set(latitude);
        }
    }
    public DoubleProperty latitudeProperty() {
        if (null == latitude) {
            latitude = new DoublePropertyBase(_latitude) {
                @Override protected void invalidated() { fireEvt(UPDATE_EVENT); }
                @Override public Object getBean() { return Location.this; }
                @Override public String getName() { return "latitude"; }
            };
        }
        return latitude;
    }

    public double getLongitude() { return null == longitude ? _longitude : longitude.get(); }
    public void setLongitude(final double longitude) {
        if (null == this.longitude) {
            _longitude = longitude;
            fireEvt(UPDATE_EVENT);
        } else {
            this.longitude.set(longitude);
        }
    }
    public DoubleProperty longitudeProperty() {
        if (null == longitude) {
            longitude = new DoublePropertyBase(_longitude) {
                @Override protected void invalidated() { fireEvt(UPDATE_EVENT); }
                @Override public Object getBean() { return Location.this; }
                @Override public String getName() { return "longitude"; }
            };
        }
        return longitude;
    }

    public double getAltitude() { return null == altitude ? _altitude : altitude.get(); }
    public void setAltitude(final double altitude) {
        if (null == this.altitude) {
            _altitude = altitude;
            fireEvt(UPDATE_EVENT);
        } else {
            this.altitude.set(altitude);
        }
    }
    public DoubleProperty altitudeProperty() {
        if (null == altitude) {
            altitude = new DoublePropertyBase(_altitude) {
                @Override protected void invalidated() { fireEvt(UPDATE_EVENT); }
                @Override public Object getBean() { return Location.this; }
                @Override public String getName() { return "altitude"; }
            };
        }
        return altitude;
    }

    public String getInfo() { return null == info ? _info : info.get(); }
    public void setInfo(final String info) {
        if (null == this.info) {
            _info = info;
            fireEvt(UPDATE_EVENT);
        } else {
            this.info.set(info);
        }
    }
    public StringProperty infoProperty() {
        if (null == info) {
            info = new StringPropertyBase(_info) {
                @Override protected void invalidated() { fireEvt(UPDATE_EVENT); }
                @Override public Object getBean() { return Location.this; }
                @Override public String getName() { return "info"; }
            };
            _info = null;
        }
        return info;
    }

    public Color getFill() { return null == fill ? _fill : fill.get(); }
    public void setFill(final Color fill) {
        if (null == this.fill) {
            _fill = fill;
            fireEvt(UPDATE_EVENT);
        } else {
            this.fill.set(fill);
        }
    }
    public ObjectProperty<Color> fillProperty() {
        if (null == fill) {
            fill = new ObjectPropertyBase<>(_fill) {
                @Override protected void invalidated() { fireEvt(UPDATE_EVENT); }
                @Override public Object getBean() { return Location.this; }
                @Override public String getName() { return "fill"; }
            };
            _fill = null;
        }
        return fill;
    }

    public Color getStroke() { return null == stroke ? _stroke : stroke.get(); }
    public void setStroke(final Color stroke) {
        if (null == this.stroke) {
            _stroke = stroke;
            fireEvt(UPDATE_EVENT);
        } else {
            this.stroke.set(stroke);
        }
    }
    public ObjectProperty<Color> strokeProperty() {
        if (null == stroke) {
            stroke = new ObjectPropertyBase<>(_stroke) {
                @Override protected void invalidated() { fireEvt(UPDATE_EVENT); }
                @Override public Object getBean() { return Location.this; }
                @Override public String getName() { return "stroke"; }
            };
            _stroke = null;
        }
        return stroke;
    }

    public ConnectionPartType getConnectionPartType() { return connectionPartType; }
    public void setConnectionPartType(final ConnectionPartType connectionPartType) { this.connectionPartType = connectionPartType; }

    public ZonedDateTime getZonedDateTime() { return getZonedDateTime(ZoneId.systemDefault()); }
    public ZonedDateTime getZonedDateTime(final ZoneId zoneId) { return ZonedDateTime.ofInstant(getTimestamp(), zoneId); }

    public void update(final double latitude, final double longitude) { set(latitude, longitude); }

    public void set(final double latitude, final double longitude) {
        set(latitude, longitude, getAltitude(), getTimestamp(), getInfo());
    }
    public void set(final double latitude, final double longitude, final double altitude, final Instant timestamp) {
        set(latitude, longitude, altitude, timestamp, getInfo());
    }
    public void set(final double latitude, final double longitude, final double altitude, final Instant timestamp, final String info) {
        if (null == this.latitude)  { _latitude  = latitude;  } else { this.latitude.set(latitude);   }
        if (null == this.longitude) { _longitude = longitude; } else { this.longitude.set(longitude); }
        if (null == this.altitude)  { _altitude  = altitude;  } else { this.altitude.set(altitude);   }
        if (null == this.timestamp) { _timestamp = timestamp; } else { this.timestamp.set(timestamp); }
        if (null == this.info)      { _info      = info;      } else { this.info.set(info);           }
        fireEvt(UPDATE_EVENT);
    }
    public void set(final Location location) {
        if (null == name)               { _name      = location.getName();      }                  else { name.set(location.getName());           }
        if (null == latitude)           { _latitude  = location.getLatitude();  }                  else { latitude.set(location.getLatitude());   }
        if (null == longitude)          { _longitude = location.getLongitude(); }                  else { longitude.set(location.getLongitude()); }
        if (null == altitude)           { _altitude  = location.getAltitude();  }                  else { altitude.set(location.getAltitude());   }
        if (null == timestamp)          { _timestamp = location.getTimestamp(); }                  else { timestamp.set(location.getTimestamp()); }
        if (null == info)               { _info      = location.getInfo();      }                  else { info.set(location.getInfo());           }
        if (null == fill)               { _fill      = location.getFill();      }                  else { fill.set(location.getFill());           }
        if (null == stroke)             { _stroke    = location.getStroke();    }                  else { stroke.set(location.getStroke());       }
        if (null == connectionPartType) { connectionPartType = location.getConnectionPartType(); } else { connectionPartType = ConnectionPartType.NONE; }
        fireEvt(UPDATE_EVENT);
    }

    public double getDistanceTo(final Location location) { return calcDistanceInMeter(this, location); }

    public boolean isWithinRangeOf(final Location location, final double meters) { return getDistanceTo(location) < meters; }

    public double calcDistanceInMeter(final Location p1, final Location p2) {
        return calcDistanceInMeter(p1.getLatitude(), p1.getLongitude(), p2.getLatitude(), p2.getLongitude());
    }
    public double calcDistanceInKilometer(final Location p1, final Location p2) {
        return calcDistanceInMeter(p1, p2) / 1000.0;
    }
    public double calcDistanceInMeter(final double lat1, final double lon1, final double lat2, final double lon2) {
        final double EARTH_RADIUS      = 6_371_000; // m
        final double LAT_1_RADIANS     = Math.toRadians(lat1);
        final double LAT_2_RADIANS     = Math.toRadians(lat2);
        final double DELTA_LAT_RADIANS = Math.toRadians(lat2-lat1);
        final double DELTA_LON_RADIANS = Math.toRadians(lon2-lon1);

        final double A = Math.sin(DELTA_LAT_RADIANS * 0.5) * Math.sin(DELTA_LAT_RADIANS * 0.5) + Math.cos(LAT_1_RADIANS) * Math.cos(LAT_2_RADIANS) * Math.sin(DELTA_LON_RADIANS * 0.5) * Math.sin(DELTA_LON_RADIANS * 0.5);
        final double C = 2 * Math.atan2(Math.sqrt(A), Math.sqrt(1-A));

        final double DISTANCE = EARTH_RADIUS * C;

        return DISTANCE;
    }

    public double getAltitudeDifferenceInMeter(final Location location) { return (getAltitude() - location.getAltitude()); }

    public double getBearingTo(final Location location) {
        return calcBearingInDegree(getLatitude(), getLongitude(), location.getLatitude(), location.getLongitude());
    }
    public double getBearingTo(final double latitude, final double longitude) {
        return calcBearingInDegree(getLatitude(), getLongitude(), latitude, longitude);
    }

    public boolean isZero() { return Double.compare(getLatitude(), 0) == 0 && Double.compare(getLongitude(), 0) == 0; }

    public double calcBearingInDegree(final double lat1, final double lon1, final double lat2, final double lon2) {
        double lt1      = Math.toRadians(lat1);
        double ln1      = Math.toRadians(lon1);
        double lt2      = Math.toRadians(lat2);
        double ln2      = Math.toRadians(lon2);
        double deltaLon = ln2 - ln1;
        double deltaPhi = Math.log(Math.tan(lt2 * 0.5 + Math.PI * 0.25) / Math.tan(lt1 * 0.5 + Math.PI * 0.25));
        if (Math.abs(deltaLon) > Math.PI) {
            if (deltaLon > 0) {
                deltaLon = -(2.0 * Math.PI - deltaLon);
            } else {
                deltaLon = (2.0 * Math.PI + deltaLon);
            }
        }
        double bearing = (Math.toDegrees(Math.atan2(deltaLon, deltaPhi)) + 360.0) % 360.0;
        return bearing;
    }

    public String getCardinalDirectionFromBearing(final double bearing) {
        double brng = bearing % 360.0;
        for (CardinalDirection cardinalDirection : CardinalDirection.values()) {
            if (Double.compare(brng, cardinalDirection.from) >= 0 && Double.compare(brng, cardinalDirection.to) < 0) {
                return cardinalDirection.direction;
            }
        }
        return "";
    }


    // ******************** Event Handling ************************************
    public void setOnEvt(final EvtObserver<Location> observer) { addEvtObserver(observer); }
    public void addEvtObserver(final EvtObserver<Location> observer) { if (!observers.contains(observer)) observers.add(observer); }
    public void removeEvtObserver(final EvtObserver<Location> observer) { if (observers.contains(observer)) observers.remove(observer); }

    public void fireEvt(final Evt<Location> evt) {
        for (EvtObserver<Location> observer : observers) { observer.onEvt(evt); }
    }


    public EventHandler<MouseEvent> getMouseEnterHandler() { return mouseEnterHandler; }
    public void setMouseEnterHandler(final EventHandler<MouseEvent> handler) { mouseEnterHandler = handler; }

    public EventHandler<MouseEvent> getMousePressHandler() { return mousePressHandler; }
    public void setMousePressHandler(final EventHandler<MouseEvent> handler) { mousePressHandler = handler; }

    public EventHandler<MouseEvent> getMouseReleaseHandler() { return mouseReleaseHandler; }
    public void setMouseReleaseHandler(final EventHandler<MouseEvent> handler) { mouseReleaseHandler = handler;  }

    public EventHandler<MouseEvent> getMouseExitHandler() { return mouseExitHandler; }
    public void setMouseExitHandler(final EventHandler<MouseEvent> handler) { mouseExitHandler = handler; }

    public Poi toPoi() {
        return new Poi(getLatitude(), getLongitude(), getName(), "", null, PointSize.NORMAL, getFill(), getStroke(), null, null, null);
    }

    public static Location fromPoi(final Poi poi) {
        return LocationBuilder.create().name(poi.getName()).fill(poi.getFill()).stroke(poi.getStroke()).latitude(poi.getLat()).longitude(poi.getLon()).build();
    }


    // ******************** Misc **********************************************
    @Override public boolean equals(final Object obj) {
        if (obj instanceof Location) {
            final Location location = (Location) obj;
            return (Double.compare(getLatitude(), location.getLatitude()) == 0 &&
                    Double.compare(getLongitude(), location.getLongitude()) == 0 &&
                    Double.compare(getAltitude(), location.getAltitude()) == 0);
        } else {
            return false;
        }
    }

    @Override public String toString() {
        return new StringBuilder().append("{")
                                  .append("\"name\":\"").append(getName()).append("\",")
                                  .append("\"timestamp\":\"").append(getTimestamp()).append("\",")
                                  .append("\"lat\":").append(getLatitude()).append(",")
                                  .append("\"lon\":").append(getLongitude()).append(",")
                                  .append("\"alt \":").append(getAltitude()).append(",")
                                  .append("\"info\":\"").append(getInfo()).append("\",")
                                  .append("\"fill\":\"").append(Helper.colorToWeb(getFill())).append("\",")
                                  .append("\"stroke\":\"").append(Helper.colorToWeb(getStroke())).append("\",")
                                  .append("\"connection_part_type\":\"").append(connectionPartType.name()).append("\"")
                                  .append("}")
                                  .toString();
    }

    @Override public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp   = Double.doubleToLongBits(getLatitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp   = Double.doubleToLongBits(getLongitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp   = Double.doubleToLongBits(getAltitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
