package eu.hansolo.fx.countries.tools;

import eu.hansolo.fx.countries.tools.Records.City;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public enum Cities {
    INSTANCE;

    final List<City> listOfCities;
    final List<City> capitals;


    // ******************** Constructors **************************************
    Cities() {
        listOfCities = new ArrayList<>(Helper.getCities());
        capitals     = listOfCities.stream().filter(city -> city.isCapital()).collect(Collectors.toList());
    }



    // ******************** Methods *******************************************
    public List<City> get() { return listOfCities; }

    public List<City> capitals() { return capitals; }
}
