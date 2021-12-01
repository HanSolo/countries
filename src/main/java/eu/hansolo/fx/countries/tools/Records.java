package eu.hansolo.fx.countries.tools;

import eu.hansolo.fx.countries.Country;


public class Records {
    public record City(String name, double lat, double lon, Country country, boolean isCapital, long population){
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
}
