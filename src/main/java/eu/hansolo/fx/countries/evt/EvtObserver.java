package eu.hansolo.fx.countries.evt;

@FunctionalInterface
public interface EvtObserver<T> {
    void onEvt(final Evt<T> evt);
}
