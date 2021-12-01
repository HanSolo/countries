package eu.hansolo.fx.countries.evt;

import eu.hansolo.fx.countries.tools.Location;


public class LocationEvt {
    private final Location location;
    private final EvtType  type;


    // ******************** Constructors **************************************
    public LocationEvt(final Location location) {
        this(location, EvtType.UPDATE);
    }
    public LocationEvt(final Location location, final EvtType type) {
        this.location = location;
        this.type = type;
    }


    // ******************** Methods *******************************************
    public Location getLocation() { return location; }

    public EvtType getEventType() { return type; }
}
