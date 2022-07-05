package eu.hansolo.fx.countries.tools;

import eu.hansolo.fx.countries.Country;
import eu.hansolo.toolboxfx.geom.Poi;
import eu.hansolo.toolboxfx.geom.PoiSize;
import javafx.scene.paint.Color;


public class Records {
    public record City(String name, double lat, double lon, Country country, boolean isCapital, long population){
        public Poi toPoi() {
            return new Poi(lat, lon, name, country.getIso2(), null, PoiSize.NORMAL, Color.CYAN, Color.TRANSPARENT, null, null, null);
        }

        public CLocation toLocation() {
            return CLocationBuilder.create().latitude(lat).longitude(lon).name(name).country(country).build();
        }

        public CLocationBuilder toLocationBuilder() {
            return CLocationBuilder.create().latitude(lat).longitude(lon).name(name).country(country);
        }

        @Override public String toString() {
            return new StringBuilder().append("{")
                                      .append("\"name\":\"").append(name).append("\",")
                                      .append("\"lat\":").append(lat).append(",")
                                      .append("\"lon\":").append(lat).append(",")
                                      .append("\"country\":\"").append(country.getDisplayName()).append("\",")
                                      .append("\"iso2\":\"").append(country.getIso2()).append("\",")
                                      .append("\"capital\":").append(isCapital).append(",")
                                      .append("\"population\":").append(population)
                                      .append("}")
                                      .toString();
        }
    }

    public record Airport(String name, double lat, double lon, Country country, Size size, String iata){

        public Poi toPoi() {
            return new Poi(lat, lon, name, iata, null, PoiSize.NORMAL, Color.RED, Color.TRANSPARENT, null, null, null);
        }

        public CLocation toLocation() {
            return CLocationBuilder.create().latitude(lat).longitude(lon).name(name).info(iata).country(country).build();
        }

        public CLocationBuilder toLocationBuilder() {
            return CLocationBuilder.create().latitude(lat).longitude(lon).name(name).info(iata).country(country);
        }

        @Override public String toString() {
            return new StringBuilder().append("{")
                                      .append("\"name\":\"").append(name).append("\",")
                                      .append("\"lat\":").append(lat).append(",")
                                      .append("\"lon\":").append(lat).append(",")
                                      .append("\"country\":\"").append(country.getDisplayName()).append("\",")
                                      .append("\"iso2\":\"").append(country.getIso2()).append("\",")
                                      .append("\"size\":").append(size).append(",")
                                      .append("\"iata\":").append(iata)
                                      .append("}")
                                      .toString();
        }
    }

    public record Airport2(String name, double lat, double lon, Country country, String iata, String icao) {
        public Poi toPoi() { return new Poi(lat, lon, name, iata, null, PoiSize.NORMAL, Color.RED, Color.TRANSPARENT, null, null, null); }

        public CLocationBuilder toLocationBuilder() { return CLocationBuilder.create().latitude(lat).longitude(lon).name(name).info(iata).country(country); }

        @Override public String toString() {
            return new StringBuilder().append("{")
                                      .append("\"name\":\"").append(name).append("\",")
                                      .append("\"lat\":").append(lat).append(",")
                                      .append("\"lon\":").append(lat).append(",")
                                      .append("\"country\":\"").append(country.getDisplayName()).append("\",")
                                      .append("\"iso2\":\"").append(country.getIso2()).append("\",")
                                      .append("\"icao\":").append(icao).append(",")
                                      .append("\"iata\":").append(iata)
                                      .append("}")
                                      .toString();
        }
    }
}
