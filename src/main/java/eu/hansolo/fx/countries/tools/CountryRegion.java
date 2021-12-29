package eu.hansolo.fx.countries.tools;

import eu.hansolo.fx.countries.Country;
import eu.hansolo.toolboxfx.geom.Point;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CountryRegion implements CRegion {
    private String                          name;
    private List<Country>                   countries;
    private Map<Country, List<CountryPath>> countryPaths;


    // ******************** Constructors **************************************
    public CountryRegion(final String name, final Country... countries) {
        this.name      = name;
        this.countries = new ArrayList(List.of(countries));
    }


    // ******************** Methods *******************************************
    @Override public String name() { return name; }

    @Override public List<Country> getCountries() { return countries; }

    @Override public void setFill(final Color fill) { countries.forEach(country -> country.setFill(fill)); }

    @Override public void setStroke(final Color stroke) { countries.forEach(country -> country.setFill(stroke)); }

    @Override public final List<Point> getRegionBounds() {
        double ulx     = 360;
        double uly     = 180;
        double lastULx;
        double lastULy;
        double lrx     = 0;
        double lry     = 0;
        double lastLRx;
        double lastLRy;
        for (Country country : getCountries()) {
            List<Point> coords = Country.getCountryBounds().get(country);
            final Point ul = coords.get(0);
            final Point lr = coords.get(1);

            lastULx = ulx;
            lastULy = uly;
            ulx = ul.getX() + 180;
            uly = (ul.getY() - 90.0) * -1;

            ulx = Math.min(ulx, lastULx);
            uly = Math.min(uly, lastULy);

            lastLRx = lrx;
            lastLRy = lry;
            lrx = lr.getX() + 180;
            lry = (lr.getY() - 90.0) * -1;

            lrx = Math.max(lrx, lastLRx);
            lry = Math.max(lry, lastLRy);
        };
        ulx = ulx - 180;
        uly = uly * -1 + 90;
        lrx = lrx - 180;
        lry = lry * -1 + 90;

        Point upperLeft  = new Point(ulx, uly);
        Point lowerRight = new Point(lrx, lry);
        return List.of(upperLeft, lowerRight);
    }

    @Override public final Map<Country, List<CountryPath>> getCountryPaths() {
        if (null == countryPaths) {
            countryPaths = new HashMap<>();
            getCountries().forEach(country -> {
                List<CountryPath> paths = new ArrayList<>();
                country.getCopyOfPaths().forEach(countryPath -> paths.add(countryPath));
                countryPaths.put(country, paths);
            });
        }
        return countryPaths;
    }

    @Override public final List<CountryPath> getPaths() {
        List<CountryPath> paths = new ArrayList<>();
        getCountryPaths().values().forEach(cps -> cps.forEach(countryPath -> paths.add(countryPath)));
        return paths;
    }
}
