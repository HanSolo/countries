package eu.hansolo.fx.countries.tools;

public class Point implements Comparable<Point> {
    private double  x;
    private double  y;
    private boolean empty;


    // ******************** Constructors **************************************
    public Point() {
        this(0.0, 0.0, false);
    }
    public Point(final boolean isEmpty) {
        this(0.0, 0.0, isEmpty);
    }
    public Point(final double x, final double y) {
        this(x, y, false);
    }
    public Point(final double x, final double y, final boolean isEmpty) {
        this.x     = x;
        this.y     = y;
        this.empty = isEmpty;
    }


    // ******************** Methods *******************************************
    public double getX() { return x; }
    public void setX(final double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(final double y) { this.y = y; }

    public void set(final Point p) { set(p.getX(), p.getY()); }
    public void set(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public boolean isEmpty() { return empty; }
    public void setEmpty(final boolean isEmpty) { this.empty = isEmpty; }

    public double distanceTo(final Point p) { return distance(p.getX(), p.getY(), x, y); }
    public double distanceTo(final double x, final double y) { return distance(x, y, this.x, this.y); }

    public static double distance(final Point p1, final Point p2) { return distance(p1.getX(), p1.getY(), p2.getX(), p2.getY()); }
    public static double distance(final double x1, final double y1, final double x2, final double y2) {
        double deltaX = (x2 - x1);
        double deltaY = (y2 - y1);
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
    }

    public int compareTo(final Point point) {
        return x != point.getX() ? Double.compare(x, point.x) : Double.compare(y, point.y);
    }

    public void scale(final double sx, final double sy) {
        x *= sx;
        y *= sy;
    }

    @Override public String toString() {
        return new StringBuilder().append("{")
                                  .append("\"x\":").append(x).append(",")
                                  .append("\"y\": ").append(y)
                                  .append("}")
                                  .toString();
    }
}
