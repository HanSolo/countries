package eu.hansolo.fx.countries.font;

import javafx.scene.text.Font;


public class Fonts {
    private static final String OPEN_SANS_LIGHT_NAME;
    private static final String OPEN_SANS_REGULAR_NAME;
    private static final String OPEN_SANS_BOLD_NAME;

    private static String openSansLightName;
    private static String openSansRegularName;
    private static String openSansBoldName;


    static {
        try {
            openSansLightName     = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/fx/countries/font/OpenSans-Light.ttf"), 10).getName();
            openSansRegularName   = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/fx/countries/font/OpenSans-Regular.ttf"), 10).getName();
            openSansBoldName      = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/fx/countries/font/OpenSans-Bold.ttf"), 10).getName();
        } catch (Exception exception) { }

        OPEN_SANS_LIGHT_NAME      = openSansLightName;
        OPEN_SANS_REGULAR_NAME    = openSansRegularName;
        OPEN_SANS_BOLD_NAME       = openSansBoldName;
    }


    // ******************** Methods *******************************************
    public static Font opensansLight(final double SIZE) { return new Font(OPEN_SANS_LIGHT_NAME, SIZE); }
    public static Font opensansRegular(final double SIZE) { return new Font(OPEN_SANS_REGULAR_NAME, SIZE); }
    public static Font opensansBold(final double SIZE) { return new Font(OPEN_SANS_BOLD_NAME, SIZE); }
}
