package eu.hansolo.fx.countries;

import eu.hansolo.fx.countries.flag.Flag;
import eu.hansolo.fx.countries.tools.Cities;
import eu.hansolo.fx.countries.tools.Constants;
import eu.hansolo.fx.countries.tools.CountryPath;
import eu.hansolo.fx.countries.tools.Helper;
import eu.hansolo.fx.countries.tools.CLocation;
import eu.hansolo.fx.countries.tools.Records.Airport;
import eu.hansolo.fx.countries.tools.Records.City;
import eu.hansolo.fx.countries.tools.Resolution;
import eu.hansolo.toolboxfx.ValueObject;
import eu.hansolo.toolboxfx.geom.Point;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;


public enum Country {
    AD("Andorra", new CLocation(42.546245, 1.601554)),
    AE("United Arab Emirates", new CLocation(23.424076, 53.847818)),
    AF("Afghanistan", new CLocation(33.93911, 67.709953)),
    AG("Antigua and Barbuda", new CLocation(17.060816, -61.796428)),
    AI("Anguilla", new CLocation(18.220554, -63.068615)),
    AL("Albania", new CLocation(41.153332, 20.168331)),
    AM("Armenia", new CLocation(40.069099, 45.038189)),
    AN("Netherlands Antilles", new CLocation(12.226079, -69.060087)),
    AO("Angola", new CLocation(-11.202692, 17.873887)),
    AQ("Antarctica", new CLocation(-75.250973, -0.071389)),
    AR("Argentina", new CLocation(-38.416097, -63.616672)),
    AS("American Samoa", new CLocation(-14.270972, -170.132217)),
    AT("Austria", new CLocation(47.516231, 14.550072)),
    AU("Australia", new CLocation(-25.274398, 133.775136)),
    AW("Aruba", new CLocation(12.52111, -69.968338)),
    AX("\u00C5land Islands", new CLocation(60.241034, 20.063198)),
    AZ("Azerbaijan", new CLocation(40.143105, 47.576927)),
    BA("Bosnia and Herzegovina", new CLocation(43.915886, 17.679076)),
    BB("Barbados", new CLocation(13.193887, -59.543198)),
    BD("Bangladesh", new CLocation(23.684994, 90.356331)),
    BE("Belgium", new CLocation(50.503887, 4.469936)),
    BF("Burkina Faso", new CLocation(12.238333, -1.561593)),
    BG("Bulgaria", new CLocation(42.733883, 25.48583)),
    BH("Bahrain", new CLocation(25.930414, 50.637772)),
    BI("Burundi", new CLocation(-3.373056, 29.918886)),
    BJ("Benin", new CLocation(9.30769, 2.315834)),
    BL("Saint Barth\u00E9lemy", new CLocation(17.901325, -62.823085)),
    BM("Bermuda", new CLocation(32.321384, -64.75737)),
    BN("Brunei", new CLocation(4.535277, 114.727669)),
    BO("Bolivia", new CLocation(-16.290154, -63.588653)),
    BQ("Bonaire", new CLocation(12.137575, -68.264719)),
    BR("Brazil", new CLocation(-14.235004, -51.92528)),
    BS("Bahamas", new CLocation(25.03428, -77.39628)),
    BT("Bhutan", new CLocation(27.514162, 90.433601)),
    BV("Bouvet Island", new CLocation(-54.423199, 3.413194)),
    BW("Botswana", new CLocation(-22.328474, 24.684866)),
    BY("Belarus", new CLocation(53.709807, 27.953389)),
    BZ("Belize", new CLocation(17.189877, -88.49765)),
    CA("Canada", new CLocation(56.130366, -106.346771)),
    CC("Cocos [Keeling] Islands", new CLocation(-12.164165, 96.870956)),
    CD("Congo [DRC]", new CLocation(-4.038333, 21.758664)),
    CF("Central African Republic", new CLocation(6.611111, 20.939444)),
    CG("Congo [Republic]", new CLocation(-0.228021, 15.827659)),
    CH("Switzerland", new CLocation(46.818188, 8.227512)),
    CI("C\u00F4te d'Ivoire", new CLocation(7.539989, -5.54708)),
    CK("Cook Islands", new CLocation(-21.236736, -159.777671)),
    CL("Chile", new CLocation(-35.675147, -71.542969)),
    CM("Cameroon", new CLocation(7.369722, 12.354722)),
    CN("China", new CLocation(35.86166, 104.195397)),
    CO("Colombia", new CLocation(4.570868, -74.297333)),
    CR("Costa Rica", new CLocation(9.748917, -83.753428)),
    CU("Cuba", new CLocation(21.521757, -77.781167)),
    CV("Cape Verde", new CLocation(16.002082, -24.013197)),
    CW("Cura\u00E7ao", new CLocation(12.122552, -68.873150)),
    CX("Christmas Island", new CLocation(-10.447525, 105.690449)),
    CY("Cyprus", new CLocation(35.126413, 33.429859)),
    CZ("Czech Republic", new CLocation(49.817492, 15.472962)),
    DE("Germany", new CLocation(51.165691, 10.451526)),
    DJ("Djibouti", new CLocation(11.825138, 42.590275)),
    DK("Denmark", new CLocation(56.26392, 9.501785)),
    DM("Dominica", new CLocation(15.414999, -61.370976)),
    DO("Dominican Republic", new CLocation(18.735693, -70.162651)),
    DZ("Algeria", new CLocation(28.033886, 1.659626)),
    EC("Ecuador", new CLocation(-1.831239, -78.183406)),
    EE("Estonia", new CLocation(58.595272, 25.013607)),
    EG("Egypt", new CLocation(26.820553, 30.802498)),
    EH("Western Sahara", new CLocation(24.215527, -12.885834)),
    ER("Eritrea", new CLocation(15.179384, 39.782334)),
    ES("Spain", new CLocation(40.463667, -3.74922)),
    ET("Ethiopia", new CLocation(9.145, 40.489673)),
    FI("Finland", new CLocation(61.92411, 25.748151)),
    FJ("Fiji", new CLocation(-16.578193, 179.414413)),
    FK("Falkland Islands [Islas Malvinas]", new CLocation(-51.796253, -59.523613)),
    FM("Micronesia", new CLocation(7.425554, 150.550812)),
    FO("Faroe Islands", new CLocation(61.892635, -6.911806)),
    FR("France", new CLocation(46.227638, 2.213749)),
    GA("Gabon", new CLocation(-0.803689, 11.609444)),
    GB("United Kingdom", new CLocation(55.378051, -3.435973)),
    GD("Grenada", new CLocation(12.262776, -61.604171)),
    GE("Georgia", new CLocation(42.315407, 43.356892)),
    GF("French Guiana", new CLocation(3.933889, -53.125782)),
    GG("Guernsey", new CLocation(49.465691, -2.585278)),
    GH("Ghana", new CLocation(7.946527, -1.023194)),
    GI("Gibraltar", new CLocation(36.137741, -5.345374)),
    GL("Greenland", new CLocation(71.706936, -42.604303)),
    GM("Gambia", new CLocation(13.443182, -15.310139)),
    GN("Guinea", new CLocation(9.945587, -9.696645)),
    GP("Guadeloupe", new CLocation(16.995971, -62.067641)),
    GQ("Equatorial Guinea", new CLocation(1.650801, 10.267895)),
    GR("Greece", new CLocation(39.074208, 21.824312)),
    GS("South Georgia and the South Sandwich Islands", new CLocation(-54.429579, -36.587909)),
    GT("Guatemala", new CLocation(15.783471, -90.230759)),
    GU("Guam", new CLocation(13.444304, 144.793731)),
    GW("Guinea-Bissau", new CLocation(11.803749, -15.180413)),
    GY("Guyana", new CLocation(4.860416, -58.93018)),
    GZ("Gaza Strip", new CLocation(31.354676, 34.308825)),
    HK("Hong Kong", new CLocation(22.396428, 114.109497)),
    HM("Heard Island and McDonald Islands", new CLocation(-53.08181, 73.504158)),
    HN("Honduras", new CLocation(15.199999, -86.241905)),
    HR("Croatia", new CLocation(45.1, 15.2)),
    HT("Haiti", new CLocation(18.971187, -72.285215)),
    HU("Hungary", new CLocation(47.162494, 19.503304)),
    ID("Indonesia", new CLocation(-0.789275, 113.921327)),
    IE("Ireland", new CLocation(53.41291, -8.24389)),
    IL("Israel", new CLocation(31.046051, 34.851612)),
    IM("Isle of Man", new CLocation(54.236107, -4.548056)),
    IN("India", new CLocation(20.593684, 78.96288)),
    IO("British Indian Ocean Territory", new CLocation(-6.343194, 71.876519)),
    IQ("Iraq", new CLocation(33.223191, 43.679291)),
    IR("Iran", new CLocation(32.427908, 53.688046)),
    IS("Iceland", new CLocation(64.963051, -19.020835)),
    IT("Italy", new CLocation(41.87194, 12.56738)),
    JE("Jersey", new CLocation(49.214439, -2.13125)),
    JM("Jamaica", new CLocation(18.109581, -77.297508)),
    JO("Jordan", new CLocation(30.585164, 36.238414)),
    JP("Japan", new CLocation(36.204824, 138.252924)),
    KE("Kenya", new CLocation(-0.023559, 37.906193)),
    KG("Kyrgyzstan", new CLocation(41.20438, 74.766098)),
    KH("Cambodia", new CLocation(12.565679, 104.990963)),
    KI("Kiribati", new CLocation(-3.370417, -168.734039)),
    KM("Comoros", new CLocation(-11.875001, 43.872219)),
    KN("Saint Kitts and Nevis", new CLocation(17.357822, -62.782998)),
    KP("North Korea", new CLocation(40.339852, 127.510093)),
    KR("South Korea", new CLocation(35.907757, 127.766922)),
    KW("Kuwait", new CLocation(29.31166, 47.481766)),
    KY("Cayman Islands", new CLocation(19.513469, -80.566956)),
    KZ("Kazakhstan", new CLocation(48.019573, 66.923684)),
    LA("Laos", new CLocation(19.85627, 102.495496)),
    LB("Lebanon", new CLocation(33.854721, 35.862285)),
    LC("Saint Lucia", new CLocation(13.909444, -60.978893)),
    LI("Liechtenstein", new CLocation(47.166, 9.555373)),
    LK("Sri Lanka", new CLocation(7.873054, 80.771797)),
    LR("Liberia", new CLocation(6.428055, -9.429499)),
    LS("Lesotho", new CLocation(-29.609988, 28.233608)),
    LT("Lithuania", new CLocation(55.169438, 23.881275)),
    LU("Luxembourg", new CLocation(49.815273, 6.129583)),
    LV("Latvia", new CLocation(56.879635, 24.603189)),
    LY("Libya", new CLocation(26.3351, 17.228331)),
    MA("Morocco", new CLocation(31.791702, -7.09262)),
    MC("Monaco", new CLocation(43.750298, 7.412841)),
    MD("Moldova", new CLocation(47.411631, 28.369885)),
    ME("Montenegro", new CLocation(42.708678, 19.37439)),
    MF("Saint Martin", new CLocation(18.069680, -63.079014)),
    MG("Madagascar", new CLocation(-18.766947, 46.869107)),
    MH("Marshall Islands", new CLocation(7.131474, 171.184478)),
    MK("Macedonia [FYROM]", new CLocation(41.608635, 21.745275)),
    ML("Mali", new CLocation(17.570692, -3.996166)),
    MM("Myanmar [Burma]", new CLocation(21.913965, 95.956223)),
    MN("Mongolia", new CLocation(46.862496, 103.846656)),
    MO("Macau", new CLocation(22.198745, 113.543873)),
    MP("Northern Mariana Islands", new CLocation(17.33083, 145.38469)),
    MQ("Martinique", new CLocation(14.641528, -61.024174)),
    MR("Mauritania", new CLocation(21.00789, -10.940835)),
    MS("Montserrat", new CLocation(16.742498, -62.187366)),
    MT("Malta", new CLocation(35.937496, 14.375416)),
    MU("Mauritius", new CLocation(-20.348404, 57.552152)),
    MV("Maldives", new CLocation(3.202778, 73.22068)),
    MW("Malawi", new CLocation(-13.254308, 34.301525)),
    MX("Mexico", new CLocation(23.634501, -102.552784)),
    MY("Malaysia", new CLocation(4.210484, 101.975766)),
    MZ("Mozambique", new CLocation(-18.665695, 35.529562)),
    NA("Namibia", new CLocation(-22.95764, 18.49041)),
    NC("New Caledonia", new CLocation(-20.904305, 165.618042)),
    NE("Niger", new CLocation(17.607789, 8.081666)),
    NF("Norfolk Island", new CLocation(-29.040835, 167.954712)),
    NG("Nigeria", new CLocation(9.081999, 8.675277)),
    NI("Nicaragua", new CLocation(12.865416, -85.207229)),
    NL("Netherlands", new CLocation(52.132633, 5.291266)),
    NO("Norway", new CLocation(60.472024, 8.468946)),
    NP("Nepal", new CLocation(28.394857, 84.124008)),
    NR("Nauru", new CLocation(-0.522778, 166.931503)),
    NU("Niue", new CLocation(-19.054445, -169.867233)),
    NZ("New Zealand", new CLocation(-40.900557, 174.885971)),
    OM("Oman", new CLocation(21.512583, 55.923255)),
    PA("Panama", new CLocation(8.537981, -80.782127)),
    PE("Peru", new CLocation(-9.189967, -75.015152)),
    PF("French Polynesia", new CLocation(-17.679742, -149.406843)),
    PG("Papua New Guinea", new CLocation(-6.314993, 143.95555)),
    PH("Philippines", new CLocation(12.879721, 121.774017)),
    PK("Pakistan", new CLocation(30.375321, 69.345116)),
    PL("Poland", new CLocation(51.919438, 19.145136)),
    PM("Saint Pierre and Miquelon", new CLocation(46.941936, -56.27111)),
    PN("Pitcairn Islands", new CLocation(-24.703615, -127.439308)),
    PR("Puerto Rico", new CLocation(18.220833, -66.590149)),
    PS("Palestinian Territories", new CLocation(31.952162, 35.233154)),
    PT("Portugal", new CLocation(39.399872, -8.224454)),
    PW("Palau", new CLocation(7.51498, 134.58252)),
    PY("Paraguay", new CLocation(-23.442503, -58.443832)),
    QA("Qatar", new CLocation(25.354826, 51.183884)),
    RE("R\u00E9union", new CLocation(-21.115141, 55.536384)),
    RO("Romania", new CLocation(45.943161, 24.96676)),
    RS("Serbia", new CLocation(44.016521, 21.005859)),
    RU("Russia", new CLocation(61.52401, 105.318756)),
    RW("Rwanda", new CLocation(-1.940278, 29.873888)),
    SA("Saudi Arabia", new CLocation(23.885942, 45.079162)),
    SB("Solomon Islands", new CLocation(-9.64571, 160.156194)),
    SC("Seychelles", new CLocation(-4.679574, 55.491977)),
    SD("Sudan", new CLocation(12.862807, 30.217636)),
    SE("Sweden", new CLocation(60.128161, 18.643501)),
    SG("Singapore", new CLocation(1.352083, 103.819836)),
    SH("Saint Helena", new CLocation(-24.143474, -10.030696)),
    SI("Slovenia", new CLocation(46.151241, 14.995463)),
    SJ("Svalbard and Jan Mayen", new CLocation(77.553604, 23.670272)),
    SK("Slovakia", new CLocation(48.669026, 19.699024)),
    SL("Sierra Leone", new CLocation(8.460555, -11.779889)),
    SM("San Marino", new CLocation(43.94236, 12.457777)),
    SN("Senegal", new CLocation(14.497401, -14.452362)),
    SO("Somalia", new CLocation(5.152149, 46.199616)),
    SR("Suriname", new CLocation(3.919305, -56.027783)),
    SS("South Sudan", new CLocation(4.855148, 31.579661)),
    ST("S\u00E3o Tom\u00E9 and Pr\u00EDncipe", new CLocation(0.18636, 6.613081)),
    SV("El Salvador", new CLocation(13.794185, -88.89653)),
    SX("Sint Maarten (Dutch part)", new CLocation(18.043674, -63.063529)),
    SY("Syria", new CLocation(34.802075, 38.996815)),
    SZ("Swaziland", new CLocation(-26.522503, 31.465866)),
    TC("Turks and Caicos Islands", new CLocation(21.694025, -71.797928)),
    TD("Chad", new CLocation(15.454166, 18.732207)),
    TF("French Southern Territories", new CLocation(-49.280366, 69.348557)),
    TG("Togo", new CLocation(8.619543, 0.824782)),
    TH("Thailand", new CLocation(15.870032, 100.992541)),
    TJ("Tajikistan", new CLocation(38.861034, 71.276093)),
    TK("Tokelau", new CLocation(-8.967363, -171.855881)),
    TL("Timor-Leste", new CLocation(-8.874217, 125.727539)),
    TM("Turkmenistan", new CLocation(38.969719, 59.556278)),
    TN("Tunisia", new CLocation(33.886917, 9.537499)),
    TO("Tonga", new CLocation(-21.178986, -175.198242)),
    TR("Turkey", new CLocation(38.963745, 35.243322)),
    TT("Trinidad and Tobago", new CLocation(10.691803, -61.222503)),
    TV("Tuvalu", new CLocation(-7.109535, 177.64933)),
    TW("Taiwan", new CLocation(23.69781, 120.960515)),
    TZ("Tanzania", new CLocation(-6.369028, 34.888822)),
    UA("Ukraine", new CLocation(48.379433, 31.16558)),
    UG("Uganda", new CLocation(1.373333, 32.290275)),
    UM("U.S. Minor Outlying Islands", new CLocation(19.280211, 166.647776)),
    US("United States", new CLocation(37.09024, -95.712891)),
    AK("United States Alaska", new CLocation(64.850858, -151.114289)),
    UY("Uruguay", new CLocation(-32.522779, -55.765835)),
    UZ("Uzbekistan", new CLocation(41.377491, 64.585262)),
    VA("Vatican City", new CLocation(41.902916, 12.453389)),
    VC("Saint Vincent and the Grenadines", new CLocation(12.984305, -61.287228)),
    VE("Venezuela", new CLocation(6.42375, -66.58973)),
    VG("British Virgin Islands", new CLocation(18.420695, -64.639968)),
    VI("U.S. Virgin Islands", new CLocation(18.335765, -64.896335)),
    VN("Vietnam", new CLocation(14.058324, 108.277199)),
    VU("Vanuatu", new CLocation(-15.376706, 166.959158)),
    WF("Wallis and Futuna", new CLocation(-13.768752, -177.156097)),
    WS("Samoa", new CLocation(-13.759029, -172.104629)),
    XK("Kosovo", new CLocation(42.602636, 20.902977)),
    YE("Yemen", new CLocation(15.552727, 48.516388)),
    YT("Mayotte", new CLocation(-12.8275, 45.166244)),
    ZA("South Africa", new CLocation(-30.559482, 22.937506)),
    ZM("Zambia", new CLocation(-13.133897, 27.849332)),
    ZW("Zimbabwe", new CLocation(-19.015438, 29.154857));

    private        ValueObject                     valueObject;
    private        double                          value;
    private        Color                           fill;
    private        Color                           stroke;
    private        CLocation                       location;
    private        String                          displayName;
    private static Map<Country, List<CountryPath>> countryPaths;
    private static Map<Country, List<Point>>       countryBounds;


    // ******************** Constructors **************************************
    Country(final String displayName, final CLocation location) {
        this.valueObject  = null;
        this.value        = 0;
        this.fill         = null;
        this.stroke       = null;
        this.location     = location;
        this.displayName  = displayName;
    }


    // ******************** Methods *******************************************
    public String getName() { return name(); }

    public String getIso2() { return name(); }

    public ValueObject getValueObject() { return valueObject; }
    public void setValueObject(final ValueObject valueObject) { this.valueObject = valueObject; }

    public double getValue() { return value; }
    public void setValue(final double value) { this.value = value; }

    public Color getFill() { return fill; }
    public void setFill(final Color fill) { this.fill = fill; }

    public Color getStroke() { return stroke; }
    public void setStroke(final Color stroke) { this.stroke = stroke; }

    public String getDisplayName() { return displayName; }

    public CLocation getLocation() { return location; }

    public Flag getFlag() {
        return Flag.getAsList().parallelStream().filter(flag -> flag.getIso2().equals(name())).findFirst().orElse(Flag.NOT_FOUND);
    }

    public Optional<City> getCapital() { return Cities.INSTANCE.capitals().parallelStream().filter(city -> city.country() == Country.this).findFirst(); }

    public List<City> getCities() { return Helper.getCities().stream().filter(city -> city.country() == Country.this).collect(Collectors.toList()); }

    public List<Airport> getAirports() { return Helper.getAirports().entrySet().stream().filter(entry -> entry.getValue().country() == Country.this).map(entry -> entry.getValue()).collect(Collectors.toList()); }

    public List<CountryPath> getPaths() { return getCountryPaths().get(Country.this); }

    public List<CountryPath> getCopyOfPaths() { return getCopyOfCountryPaths().get(Country.this); }

    public static final List<Country> getAsList() { return Arrays.asList(values()); }

    public static final Optional<Country> fromIso2(final String iso2) {
        return Arrays.asList(values()).parallelStream().filter(country -> country.name().equalsIgnoreCase(iso2)).findFirst();
    }

    public static final Optional<Country> fromName(final String text) {
        return getAsList().parallelStream().filter(country -> country.getDisplayName().equalsIgnoreCase(text)).findFirst();
    }

    public static final Map<Country, List<CountryPath>> getCountryPaths() {
        if (null == countryPaths) { countryPaths = Helper.createCountryPaths2(Resolution.HI_RES); }
        return countryPaths;
    }

    public static final Map<Country, List<CountryPath>> getCopyOfCountryPaths() {
        Map<Country, List<CountryPath>> copy = new HashMap<>();
        getCountryPaths().entrySet().forEach(entry -> {
            List<CountryPath> copyList = new ArrayList<>();
            entry.getValue().forEach(countryPath -> {
                CountryPath cp = new CountryPath(countryPath.getName(), countryPath.getContent());
                cp.setFill(countryPath.getFill());
                cp.setStroke(countryPath.getStroke());
                copyList.add(cp);
            });
            copy.put(entry.getKey(), copyList);
        });
        return copy;
    }

    public static final Map<Country, List<Point>> getCountryBounds() {
        if (null == countryBounds) {
            countryBounds = new HashMap<>();
            final Properties boundsProperties = Helper.readProperties(Constants.BOUNDS_PROPERTIES);
            boundsProperties.entrySet().forEach(entry -> {
                Optional<Country> country = Country.fromIso2(entry.getKey().toString());
                if (country.isEmpty()) { return; }
                final String[] coords     = entry.getValue().toString().split(",");
                final Point    upperLeft  = new Point(Double.parseDouble(coords[1]), Double.parseDouble(coords[0]));
                final Point    lowerRight = new Point(Double.parseDouble(coords[3]), Double.parseDouble(coords[2]));
                countryBounds.put(country.get(), List.of(upperLeft, lowerRight));
            });
        }
        return countryBounds;
    }
}
