package eu.hansolo.fx.countries.tools;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public class Poi {
    private double      lat;
    private double      lon;
    private String      name;
    private String      info;
    private ValueObject valueObject;
    private PointSize   pointSize;
    private Color       fill;
    private Color       stroke;
    private Image       image;
    private String      svgPath;
    private Dimension2D svgPathDim;


    // ******************** Constructors **************************************
    public Poi() {
        this(0, 0, "", "", null, PointSize.NORMAL, null, null, null, null, null);
    }
    public Poi(final double lat, final double lon, final String name, final String info, final ValueObject valueObject, final PointSize pointSize, final Color fill, final Color stroke, final Image image, final String svgPath, final Dimension2D svgPathDim) {
        this.lat         = lat;
        this.lon         = lon;
        this.name        = name;
        this.info        = info;
        this.valueObject = valueObject;
        this.pointSize   = pointSize;
        this.fill        = fill;
        this.stroke      = stroke;
        this.image       = image;
        this.svgPath     = svgPath;
        this.svgPathDim  = svgPathDim;

        if (null == svgPathDim && svgPath != null) { throw new IllegalArgumentException("svgPathDim cannot be null"); }
    }


    // ******************** Methods *******************************************
    public double getLat() { return lat; }
    public void setLat(final double lat) { this.lat = lat; }

    public double getLon() { return lon; }
    public void setLon(final double lon) { this.lon = lon; }

    public Point getLonLat() { return new Point(lon, lat); }
    public void setLonLat(final Point lonlat) {
        this.lon = lonlat.getX();
        this.lat = lonlat.getY();
    }

    public Point getLatLon() { return new Point(lat, lon); }
    public void setLatLon(final Point latlon) {
        this.lat = latlon.getY();
        this.lon = latlon.getX();
    }

    public String getName() { return name; }
    public void setName(final String name) { this.name = name; }

    public String getInfo() { return info; }
    public void setInfo(final String info) { this.info = info; }

    public ValueObject getValueObject() { return valueObject; }
    public void setValueObject(final ValueObject valueObject) { this.valueObject = valueObject; }

    public PointSize getPointSize() { return pointSize; }
    public void setPointSize(final PointSize pointSize) { this.pointSize = pointSize; }

    public Color getFill() { return fill; }
    public void setFill(final Color fill) { this.fill = fill; }

    public Color getStroke() { return stroke; }
    public void setStroke(final Color stroke) { this.stroke = stroke; }

    public Image getImage() { return image; }
    public void setImage(final Image image) { this.image = image; }

    public String getSvgPath() { return svgPath; }
    public void setSvgPath(final String svgPath) { this.svgPath = svgPath; }

    public Dimension2D getSvgPathDim() { return svgPathDim; }
    public void setSvgPathDim(final Dimension2D svgPathDim) { this.svgPathDim = svgPathDim; }

    public Location toLocation() {
        return LocationBuilder.create().name(getName()).fill(getFill()).stroke(getStroke()).latitude(getLat()).longitude(getLon()).build();
    }

    public static Poi fromLocation(final Location location) {
        return new Poi(location.getLatitude(), location.getLongitude(), location.getName(), "", null, PointSize.NORMAL, location.getFill(), location.getStroke(), null, null, null);
    }
}
