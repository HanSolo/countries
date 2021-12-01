package eu.hansolo.fx.countries.tools;

import eu.hansolo.fx.countries.Country;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static eu.hansolo.fx.countries.Country.*;


public enum BusinessRegion implements CRegion {
    AFRICA(DZ, EG, EH, LY, MA, SD, SS, TN, BF, BJ, CI, CV, GH, GM, GN, GW, LR, ML, MR, NE, NG, SH, SL, SN, TG,AO, CD, CF, CG, CM, GA, GQ, ST, TD, BI, DJ, ER, ET, KE, KM, MG, MU, MW, MZ, RE, RW, SC, SO, TZ, UG, YT, ZM, ZW, BW, LS, NA, SZ, ZA),
    NORTHERN_AFRICA(DZ, EG, EH, LY, MA, SD, SS, TN),
    WESTERN_AFRICA(BF, BJ, CI, CV, GH, GM, GN, GW, LR, ML, MR, NE, NG, SH, SL, SN, TG),
    MIDDLE_AFRICA(AO, CD, CF, CG, CM, GA, GQ, ST, TD),
    EASTERN_AFRICA(BI, DJ, ER, ET, KE, KM, MG, MU, MW, MZ, RE, RW, SC, SO, TZ, UG, YT, ZM, ZW),
    SOUTHERN_AFRICA(BW, LS, NA, SZ, ZA),
    AMERICAS(BM, CA, PM, US, AG, AI, AW, BB, BL, BS, CU, DM, DO, GD, GP, HT, JM, KN, KY, LC, MF, MQ, MS, PR, TC, TT, VC, VG, VI, BZ, CR, GT, HN, MX, NI, PA, SV, AR, BO, BR, CL, CO, EC, FK, GF, GY, PE, PY, SR, UY, VE),
    AMERICAS_INCLUDING_GREENLAND(BM, CA, GL, PM, US, AG, AI, AW, BB, BL, BS, CU, DM, DO, GD, GP, HT, JM, KN, KY, LC, MF, MQ, MS, PR, TC, TT, VC, VG, VI, BZ, CR, GT, HN, MX, NI, PA, SV, AR, BO, BR, CL, CO, EC, FK, GF, GY, PE, PY, SR, UY, VE),
    NORTHERN_AMERICA(BM, CA, GL, PM, US),
    CARIBBEAN(AG, AI, AW, BB, BL, BS, CU, DM, DO, GD, GP, HT, JM, KN, KY, LC, MF, MQ, MS, PR, TC, TT, VC, VG, VI),
    CENTRAL_AMERICA(BZ, CR, GT, HN, MX, NI, PA, SV),
    SOUTH_AMERICA(AR, BO, BR, CL, CO, EC, FK, GF, GY, PE, PY, SR, UY, VE),
    APAC(AS, AU, BD, BN, BT, CC, CK, CN, CX, FJ, FM, GU, HK, ID, IN, IO, JP, KH, KI, KP, KR, LA, LK, MH, MM, MN, MO, MP, MV, MY, NC, NF, NP, NR, NU, NZ, PF, PG, PH, PK, PN, PW, SB, SG, TH, TK, TL, TO, TV, TW, VN, VU, WF, WS),
    ASIA(TM, TJ, KG, KZ, UZ, CN, HK, JP, KP, KR, MN, MO, TW, AF, BD, BT, IN, IR, LK, MV, NP, PK, BN, ID, KH, LA, MM, MY, PH, SG, TH, TL, VN, AE, AM, AZ, BH, CY, GE, IL, IQ, JO, KW, LB, OM, PS, QA, SA, SY, TR, YE),
    CENTRAL_ASIA(TM, TJ, KG, KZ, UZ),
    EASTERN_ASIA(CN, HK, JP, KP, KR, MN, MO, TW),
    SOUTHERN_ASIA(AF, BD, BT, IN, IR, LK, MV, NP, PK),
    SOUTH_EASTERN_ASIA(BN, ID, KH, LA, MM, MY, PH, SG, TH, TL, VN),
    WESTERN_ASIA(AE, AM, AZ, BH, CY, GE, IL, IQ, JO, KW, LB, OM, PS, QA, SA, SY, TR, YE),
    APJC(AS, AU, BD, BN, BT, CC, CK, CN, CX, FJ, FM, GU, HK, HM, ID, IN, IO, JP, KH, KI, KP, KR, LA, LK, MH, MM, MN, MO, MP, MV, MY, NC, NF, NP, NR, NU, NZ, PF, PG, PH, PN, PW, SB, SG, TH, TK, TL, TO, TV, TW, VN, VU, WS),
    ANZ(AU, NZ),
    BENELUX(BE, NL, LU),
    BRICS(RU, BR, CN, IN, ZA),
    DACH(DE, AT, CH),
    EMEA(AF, AX, AL, DZ, AD, AO, AM, AT, AZ, BH, BY, BE, BJ, BA, BW, BV, BG, BF, BI, CM, CV, CF, TD, KM, CD, CG, HR, CY, CZ, DK, DJ, EG, GQ, ER, EE, ET, FK, FO, FI, FR, GA, GM, GE, DE, GH, GI, GR, GL, GG, GW, HU, IS, IR, IQ, IE, IM, IL, IT, CI, JE, JO, KZ, KE, XK, KW, KG, LV, LB, LS, LR, LY, LI, LT, LU, MK, MG, MW, ML, MT, MR, MU, YT, MD, MC, ME, MA, MZ, NA, NL, NE, NG, NO, OM, PK, PS, PL, PT, QA, RE, RO, RU, RW, SH, SM, ST, SA, SN, RS, SC, SL, SK, SI, SO, ZA, GS, ES, SD, SJ, SZ, SE, CH, SY, TJ, TZ, TG, TN, TR, TM, UG, UA, AE, GB, UZ, VA, EH, YE, ZM, ZW),
    EUROPEAN_UNION(AT, BE, BG, HR, CY, CZ, DK, EE, FI, FR, DE, GR, HU, IE, IT, LV, LT, LU, MT, NL, PL, PT, RO, SK, SI, ES, SE),
    EUROPE(GG, JE, AX, DK, EE, FI, FO, GB, IE, IM, IS, LT, LV, NO, SE, SJ, AT, BE, CH, DE, FR, LI, LU, MC, NL, BG, BY, CZ, HU, MD, PL, RO, RU, SK, UA, 	AD, AL, BA, ES, GI, GR, HR, IT, ME, MK, MT, RS, PT, SI, SM, VA),
    NORTHERN_EUROPE(GG, JE, AX, DK, EE, FI, FO, GB, IE, IM, IS, LT, LV, NO, SE, SJ),
    WESTERN_EUROPE(AT, BE, CH, DE, FR, LI, LU, MC, NL),
    EASTERN_EUROPE(BG, BY, CZ, HU, MD, PL, RO, RU, SK, UA),
    SOUTHERN_EUROPE(AD, AL, BA, ES, GI, GR, HR, IT, ME, MK, MT, RS, PT, SI, SM, VA),
    OCEANIA(AU, NF, NZ, FJ, NC, PG, SB, VU,	FM, GU, KI, MH, MP, NR, PW, AS, CK, NU, PF, PN, TK, TO, TV, WF, WS),
    AUSTRLIA_AND_NEW_ZEALAND(AU, NF, NZ),
    MELANESIA(FJ, NC, PG, SB, VU),
    MICRONESIA(FM, GU, KI, MH, MP, NR, PW),
    POLYNESIA(AS, CK, NU, PF, PN, TK, TO, TV, WF, WS),
    NORAM(US, CA, MX, GT, BZ, CU, DO, HT, HN, SV, NI, CR, PA);

    private List<Country>                   countries;
    private Map<Country, List<CountryPath>> countryPaths;


    // ******************** Constructors **************************************
    BusinessRegion(final Country... countries) {
        this.countries = new ArrayList(Set.of(countries));
    }


    // ******************** Methods *******************************************
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
            if (null == coords) { continue; }
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