package eu.hansolo.fx.countries.evt;

@FunctionalInterface
public interface LocationEvtObserver {
    void onLocationEvt(final LocationEvt evt);
}
