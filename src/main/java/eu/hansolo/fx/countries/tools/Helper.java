/*
 * Copyright (c) 2021 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.fx.countries.tools;

import eu.hansolo.fx.countries.Country;
import eu.hansolo.fx.countries.tools.Records.Airport;
import eu.hansolo.fx.countries.tools.Records.Airport2;
import eu.hansolo.fx.countries.tools.Records.City;
import eu.hansolo.toolboxfx.geom.Point;
import javafx.animation.Interpolator;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.Properties;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Helper {
    private static final List<City>            cities       = new ArrayList<>();
    private static final List<City>            capitals     = new ArrayList<>();
    private static final Map<String, Airport>  airports    = new HashMap<>();
    private static final Map<String, Airport2> airports2   = new HashMap<>();
    private static final Map<Country, Long>    populations = new HashMap<>();


    public static final int clamp(final int min, final int max, final int value) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    public static final long clamp(final long min, final long max, final long value) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    public static final float clamp(final float min, final float max, final float value) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    public static final double clamp(final double min, final double max, final double value) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    public static final OperatingSystem getOperatingSystem() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("win") >= 0)
            return OperatingSystem.WINDOWS;
        if (os.indexOf("mac") >= 0)
            return OperatingSystem.MACOS;
        if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0)
            return OperatingSystem.LINUX;
        if (os.indexOf("sunos") >= 0) {
            return OperatingSystem.SOLARIS;
        }
        return OperatingSystem.NONE;
    }

    public static final List<City> getCities() {
        if (cities.isEmpty()) {
            try(InputStream in = Helper.class.getResourceAsStream(Constants.CITIES_FILE);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                Stream<String> lines = reader.lines();
                lines.forEach(line -> {
                    String[]          cityParts  = line.split(",");
                    String            name       = cityParts[0];
                    double            lat        = Double.parseDouble(cityParts[2]);
                    double            lon        = Double.parseDouble(cityParts[3]);
                    Optional<Country> countryOpt = Country.fromIso2(cityParts[5]);
                    Country           country    = countryOpt.isPresent() ? countryOpt.get() : null;
                    boolean           capital    = cityParts[8].equals("primary");
                    long              population = cityParts.length == 10 ? Long.parseLong(cityParts[9]) : -1;

                    if (null != country) { cities.add(new City(name, lat, lon, country, capital, population)); }
                });
                lines.close();
            } catch (IOException e) {

            }
        }
        return cities;
    }

    public static final List<City> getCapitals() {
        if (capitals.isEmpty()) {
            capitals.addAll(getCities().stream().filter(city -> city.isCapital()).collect(Collectors.toList()));
        }
        return capitals;
    }

    public static final Map<String, Airport> getAirports() {
        if (airports.isEmpty()) {
            // size,name,iso2,iata,lat,lon
            try(InputStream in = Helper.class.getResourceAsStream(Constants.AIRPORTS_FILE);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                Stream<String> lines = reader.lines();
                lines.forEach(line -> {
                    String[]          airportParts = line.split(",");
                    Size              size         = Size.fromText(airportParts[0]);
                    String            name         = airportParts[1];
                    Optional<Country> countryOpt   = Country.fromIso2(airportParts[2]);
                    String            iata         = airportParts[3];
                    double            lat          = Double.parseDouble(airportParts[4]);
                    double            lon          = Double.parseDouble(airportParts[5]);

                    if (countryOpt.isPresent()) { airports.put(iata, new Airport(name, lat, lon, countryOpt.get(), size, iata)); }
                });
                lines.close();
            } catch (IOException e) {

            }
        }
        return airports;
    }

    public static final Map<String, Airport2> getAirports2() {
        // "country_code","region_name","iata","icao","airport","latitude","longitude"
        if (airports2.isEmpty()) {
            // size,name,iso2,iata,lat,lon
            try(InputStream in = Helper.class.getResourceAsStream(Constants.AIRPORTS2_FILE);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                Stream<String> lines = reader.lines();
                lines.forEach(line -> {
                    String[]          airportParts = line.split(",");
                    if (airportParts.length == 7) {
                        String            name       = airportParts[4];
                        Optional<Country> countryOpt = Country.fromIso2(airportParts[0]);
                        String            iata       = airportParts[2];
                        String            icao       = airportParts[3];
                        double            lat        = Double.parseDouble(airportParts[5]);
                        double            lon        = Double.parseDouble(airportParts[6]);
                        System.out.println(iata + "   " + icao);
                        if (countryOpt.isPresent()) { airports2.put(iata, new Airport2(name, lat, lon, countryOpt.get(), iata, icao)); }
                    }
                });
                lines.close();
            } catch (IOException e) {

            }
        }
        return airports2;
    }

    public static final Map<Country, Long> getPopulations() {
        if (populations.isEmpty()) {
            // iso2,population
            try(InputStream in = Helper.class.getResourceAsStream(Constants.POPULATION_FILE);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                Stream<String> lines  = reader.lines();
                lines.forEach(line -> {
                    String[]          populationParts = line.split(",");
                    String            countryIso2     = populationParts[0];
                    Optional<Country> countryOpt      = Country.fromIso2(countryIso2);
                    Long              population      = Long.valueOf(populationParts[1]);
                    if (countryOpt.isPresent()) { populations.put(countryOpt.get(), population); }
                });
                lines.close();
            }
            catch (IOException e) {

            }
        }
        return populations;
    }

    public static final Map<String, List<CountryPath>> createCountryPaths(final Resolution resolution) {
        final Properties                     properties   = readProperties(Resolution.HI_RES == resolution ? Constants.HIRES_PROPERTIES : Constants.LORES_PROPERTIES);
        final Map<String, List<CountryPath>> countryPaths = new HashMap<>();
        properties.forEach((key, value) -> {
            final String            name     = key.toString();
            final List<CountryPath> pathList = new ArrayList<>();
            for (String path : value.toString().split(";")) { pathList.add(new CountryPath(name, path)); }
            countryPaths.put(name, pathList);
        });
        return countryPaths;
    }

    public static final Map<Country, List<CountryPath>> createCountryPaths2(final Resolution resolution) {
        final Properties                      resolutionProperties = readProperties(Resolution.HI_RES == resolution ? Constants.HIRES_PROPERTIES : Constants.LORES_PROPERTIES);
        final Map<Country, List<CountryPath>> countryPaths         = new HashMap<>();
        resolutionProperties.forEach((key, value) -> {
            final String            iso2     = key.toString();
            final Optional<Country> country  = Country.fromIso2(key.toString());
            if (country.isEmpty()) { return; }
            final List<CountryPath> pathList   = new ArrayList<>();
            for (String path : value.toString().split(";")) { pathList.add(new CountryPath(iso2, path)); }
            countryPaths.put(country.get(), pathList);
        });
        return countryPaths;
    }

    public static final String colorToWeb(final Color color) { return color.toString().replace("0x", "#").substring(0, 7); }

    public static final Color getColorAt(final LinearGradient gradient, final double fraction) {
        List<Stop> stops     = gradient.getStops();
        double     frctn     = fraction < 0f ? 0f : (fraction > 1 ? 1 : fraction);
        Stop       lowerStop = new Stop(0.0, stops.get(0).getColor());
        Stop       upperStop = new Stop(1.0, stops.get(stops.size() - 1).getColor());

        for (Stop stop : stops) {
            double currentFraction = stop.getOffset();
            if (Double.compare(currentFraction, frctn) == 0) {
                return stop.getColor();
            } else if (Double.compare(currentFraction, frctn) < 0) {
                lowerStop = new Stop(currentFraction, stop.getColor());
            } else {
                upperStop = new Stop(currentFraction, stop.getColor());
                break;
            }
        }

        double interpolationFraction = (frctn - lowerStop.getOffset()) / (upperStop.getOffset() - lowerStop.getOffset());
        return (Color) Interpolator.LINEAR.interpolate(lowerStop.getColor(), upperStop.getColor(), interpolationFraction);
    }

    public static final double distance(final double x1, final double y1, final double x2, final double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public static final double bearing(final Point p1, final Point p2) {
        return bearing(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }
    public static final double bearing(final double x1, final double y1, final double x2, final double y2) {
        double bearing = Math.toDegrees(Math.atan2(y2 - y1, x2 - x1)) + 90;
        if (bearing < 0) { bearing += 360.0; }
        return bearing;
    }

    public static final int getMinutes(final double decimalDegree) { return (int) ((decimalDegree - ((int) decimalDegree)) * 60); }
    public static final double getSeconds(final double decimalDegree) { return (((decimalDegree - ((int) decimalDegree)) * 60) - getMinutes(decimalDegree)) * 60; }

    public static final double getDecimalDeg(final int degrees, final int minutes, final double seconds) {
        return (((seconds / 60) + minutes) / 60) + degrees;
    }

    public static final Point latLonToXY(final double lat, final double lon) {
        final double[] xy = latLonToXY(lat, lon, Constants.MAP_OFFSET_X, Constants.MAP_OFFSET_Y, Constants.MAP_WIDTH, Constants.MAP_HEIGHT);
        return new Point(xy[0], xy[1]);
    }
    public static final Point latLonToXY(final Point latlon) {
        return latLonToXY(latlon, Constants.MAP_OFFSET_X, Constants.MAP_OFFSET_Y, Constants.MAP_WIDTH, Constants.MAP_HEIGHT);
    }
    public static final Point latLonToXY(final Point latlon, final double mapOffsetX, final double mapOffsetY, final double mapWidth, final double mapHeight) {
        final double[] xy = latLonToXY(latlon.getY(), latlon.getX(), mapOffsetX, mapOffsetY, mapWidth, mapHeight);
        return new Point(xy[0], xy[1]);
    }
    public static final double[] latLonToXY(final double latitude, final double longitude, final double mapOffsetX, final double mapOffsetY, final double mapWidth, final double mapHeight) {
        final double x = (longitude + 180) * (mapWidth / 360) + mapOffsetX;
        final double y = (mapHeight / 2) - (mapWidth * (Math.log(Math.tan((Math.PI / 4) + (Math.toRadians(latitude) / 2)))) / (2 * Math.PI)) + mapOffsetY;
        return new double[]{ x, y };
    }

    public static final Point xyToLatLon(final double x, final double y) {
        double[] latlon = xyToLatLon(x, y, Constants.MAP_OFFSET_X, Constants.MAP_OFFSET_Y, Constants.MAP_WIDTH, Constants.MAP_HEIGHT);
        return new Point(latlon[0], latlon[1]);
    }
    public static final Point xyToLatLon(final Point xy) {
        return xyToLatLon(xy, Constants.MAP_OFFSET_X, Constants.MAP_OFFSET_Y, Constants.MAP_WIDTH, Constants.MAP_HEIGHT);
    }
    public static final Point xyToLatLon(final Point xy, final double mapOffsetX, final double mapOffsetY, final double mapWidth, final double mapHeight) {
        final double[] ll = xyToLatLon(xy.getY(), xy.getX(), mapOffsetX, mapOffsetY, mapWidth, mapHeight);
        return new Point(ll[0], ll[1]);
    }
    public static final double[] xyToLatLon(final double x, final double y, final double mapOffsetX, final double mapOffsetY, final double mapWidth, final double mapHeight) {
        final double longitude = ((x - mapOffsetX) * 360) / mapWidth - 180;
        final double latitude  = Math.toDegrees(-(Math.atan(Math.exp((((y - mapOffsetY) * 2 - mapHeight) * Math.PI) / mapWidth)) * 4 - Math.PI) / 2);
        return new double[]{ latitude, longitude };
    }


    public static final Properties readProperties(final String filename) {
        final ClassLoader loader     = Thread.currentThread().getContextClassLoader();
        final Properties  properties = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(filename)) {
            properties.load(resourceStream);
        } catch (IOException exception) {
        }
        return properties;
    }

    private static final NavigableMap<Long, String> SUFFIXES = new TreeMap<>(Map.of(1_000L, "k",
                                                                                    1_000_000L, "M",
                                                                                    1_000_000_000L, "G",
                                                                                    1_000_000_000_000L, "T",
                                                                                    1_000_000_000_000_000L, "P",
                                                                                    1_000_000_000_000_000_000L, "E"));
    public static final String shortenNumber(final long value) {
        return shortenNumber(value, Locale.US);
    }
    public static final String shortenNumber(final long value, final Locale locale) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) { return shortenNumber(Long.MIN_VALUE + 1, locale); }
        if (value < 0)               { return "-" + shortenNumber(-value, locale); }
        if (value < 1000)            { return Long.toString(value); }

        final Entry<Long, String> entry    = SUFFIXES.floorEntry(value);
        final Long                divideBy = entry.getKey();
        final String                 suffix     = entry.getValue();
        final long                   truncated  = value / (divideBy / 10);
        final boolean                hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        final java.text.NumberFormat formatter  = java.text.NumberFormat.getNumberInstance(locale);
        formatter.setMinimumFractionDigits(1);
        formatter.setMaximumFractionDigits(1);
        return hasDecimal ? formatter.format(truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

    public static final <T extends Point> Point getMidPoint(final T p1, final T p2) {
        return new Point((p1.getX() + p2.getX()) / 2.0, (p1.getY() + p2.getY()) / 2.0);
    }
    public static final double[] getMidPoint(final double x1, final double y1, final double x2, final double y2) {
        return new double[] { (x1 + x2) / 2.0, (y1 + y2) / 2.0 };
    }

    public static final Point rotatePointAroundRotationCenter(final Point p, final Point rc, final double angle) {
        double[] rp = rotatePointAroundRotationCenter(p.getX(), p.getY(), rc.getX(), rc.getY(), angle);
        return new Point(rp[0], rp[1]);
    }
    public static final double[] rotatePointAroundRotationCenter(final double x, final double y, final double rx, final double ry, final double angle) {
        final double rad = Math.toRadians(angle);
        final double sin = Math.sin(rad);
        final double cos = Math.cos(rad);
        final double nX  = rx + (x - rx) * cos - (y - ry) * sin;
        final double nY  = ry + (x - rx) * sin + (y - ry) * cos;
        return new double[] { nX, nY };
    }

    /**
     * @param startPoint
     * @param controlPoint1
     * @param controlPoint2
     * @param endPoint
     * @param distance in % (0-1)
     * @return
     */
    public static final Point getCubicBezierXYatT(final Point startPoint, final Point controlPoint1, final Point controlPoint2, final Point endPoint, final double distance) {
        final double x = cubicN(distance, startPoint.getX(), controlPoint1.getX(), controlPoint2.getX(), endPoint.getX());
        final double y = cubicN(distance, startPoint.getY(), controlPoint1.getY(), controlPoint2.getY(), endPoint.getY());
        return new Point(x, y);
    }
    public static final double[] getCubicBezierXYatT(final double startPointX, final double startPointY,
                                                     final double controlPoint1X, final double controlPoint1Y,
                                                     final double controlPoint2X, final double controlPoint2Y,
                                                     final double endPointX, final double endPointY, final double distance) {
        final double x = cubicN(distance, startPointX, controlPoint1X, controlPoint2X, endPointX);
        final double y = cubicN(distance, startPointY, controlPoint1Y, controlPoint2Y, endPointY);
        return new double[] { x, y };
    }
    private static double cubicN(final double distance, final double a, final double b, final double c, final double d) {
        final double t2 = distance * distance;
        final double t3 = t2 * distance;
        return a + (-a * 3 + distance * (3 * a - a * distance)) * distance + (3 * b + distance * (-6 * b + b * 3 * distance)) * distance + (c * 3 - c * 3 * distance) * t2 + d * t3;
    }

    public static final double getControlPointAngle(final Point p1, final Point p2) {
        final double distance = distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        final double cpAngle;
        if (distance > 600) {
            cpAngle = 80;
        } else if (distance > 500) {
            cpAngle = 70;
        } else if (distance > 400) {
            cpAngle = 60;
        } else if (distance > 300) {
            cpAngle = 50;
        } else if (distance > 200) {
            cpAngle = 40;
        } else if (distance > 100) {
            cpAngle = 30;
        } else if (distance > 50) {
            cpAngle = 20;
        } else {
            cpAngle = 10;
        }
        return cpAngle;
    }
}
