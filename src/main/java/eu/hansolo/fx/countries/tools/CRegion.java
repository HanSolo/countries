package eu.hansolo.fx.countries.tools;

import eu.hansolo.fx.countries.Country;
import eu.hansolo.toolboxfx.geom.Point;
import javafx.scene.paint.Color;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface CRegion {

    String name();

    List<Country> getCountries();

    void setFill(Color stroke);

    void setStroke(Color stroke);

    List<Point> getRegionBounds();

    Map<Country, List<CountryPath>> getCountryPaths();

    Collection<CountryPath> getPaths();
}
