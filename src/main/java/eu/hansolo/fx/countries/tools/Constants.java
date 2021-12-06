package eu.hansolo.fx.countries.tools;

import javafx.scene.paint.Color;


public class Constants {
    /**
     * Default background color
     */
    public static final Color  BACKGROUND        = Color.rgb(42, 42, 42);
    /**
     * Default text fill color
     */
    public static final Color  TEXT_FILL         = Color.web("#a5a5a5");
    /**
     * Default fill color
     */
    public static final Color  FILL              = Color.web("#3b3b3b");
    /**
     * Default stroke color
     */
    public static final Color  STROKE            = Color.web("#a5a5a5");
    /**
     * Default poi fill
     */
    public static final Color  POI_FILL          = Color.web("#d86875");
    /**
     * Base map width for for world map
     */
    public static final double MAP_WIDTH         = 1009;
    /**
     * Base map height for world map
     */
    public static final double MAP_HEIGHT        = 665;
    /**
     * Base map offset x for world map
     */
    public static final double MAP_OFFSET_X      = -28.7565;
    /**
     * Base map offset y for world map
     */
    public static final double MAP_OFFSET_Y      = 129.675;
    /**
     * Default aspect ratio for world map
     */
    public static final double ASPECT_RATIO      = 0.6590683845;
    /**
     * Properties file that contains the upper left and lower right corners of the country boundaries
     */
    public static final String BOUNDS_PROPERTIES = "eu/hansolo/fx/countries/bounds.properties";
    /**
     * Properties file that contains high resolution svg paths for all countries
     */
    public static final String HIRES_PROPERTIES  = "eu/hansolo/fx/countries/hires.properties";
    /**
     * Properties file that contains low resolution svg paths for all countries
     */
    public static final String LORES_PROPERTIES  = "eu/hansolo/fx/countries/lores.properties";

    public static final String CITIES_FILE       = "cities.txt";
    public static final String AIRPORTS_FILE     = "airports.txt";
    public static final String POPULATION_FILE   = "population_2020.txt";
}
