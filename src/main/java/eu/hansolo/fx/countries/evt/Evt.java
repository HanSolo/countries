package eu.hansolo.fx.countries.evt;

import eu.hansolo.fx.countries.tools.Location;


public class Evt<T> {
    private final T       src;
    private final EvtType type;


    // ******************** Constructors **************************************
    public Evt(final T src) {
        this(src, EvtType.UPDATE);
    }
    public Evt(final T src, final EvtType type) {
        this.src  = src;
        this.type = type;
    }


    // ******************** Methods *******************************************
    public T getSrc() { return src; }

    public EvtType getEventType() { return type; }
}
