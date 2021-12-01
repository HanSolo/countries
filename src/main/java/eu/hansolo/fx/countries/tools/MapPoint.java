package eu.hansolo.fx.countries.tools;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;
import javafx.scene.paint.Color;


public class MapPoint extends Point {
    private String                _name;
    private StringProperty        name;
    private Color                 _fill;
    private ObjectProperty<Color> fill;
    private Color                 _stroke;
    private ObjectProperty<Color> stroke;


    public MapPoint() {
        super();
        this._name   = "";
        this._fill   = Color.GRAY;
        this._stroke = Color.TRANSPARENT;
    }
    public MapPoint(final String name, final double lat, final double lon) {
        this(name, Color.GRAY, Color.TRANSPARENT, lat, lon);
    }
    public MapPoint(final String name, final Color fill, final Color stroke, final double lat, final double lon) {
        super(lat, lon);
        this._name   = name;
        this._fill   = fill;
        this._stroke = stroke;
    }


    public String getName() { return null == name ? _name : name.get(); }
    public void setName(final String name) {
        if (null == this.name) {
            _name = name;
        } else {
            this.name.set(name);
        }
    }
    public StringProperty nameProperty() {
        if (null == name) {
            name = new StringPropertyBase(_name) {
                @Override protected void invalidated() {}
                @Override public Object getBean() { return MapPoint.this; }
                @Override public String getName() { return "name"; }
            };
            _name = null;
        }
        return name;
    }

    public Color getFill() { return null == fill ? _fill : fill.get(); }
    public void setFill(final Color fill) {
        if (null == this.fill) {
            _fill = fill;
        } else {
            this.fill.set(fill);
        }
    }
    public ObjectProperty<Color> fillProperty() {
        if (null == fill) {
            fill = new ObjectPropertyBase<>(_fill) {
                @Override protected void invalidated() {}
                @Override public Object getBean() { return MapPoint.this; }
                @Override public String getName() { return "fill"; }
            };
            _fill = null;
        }
        return fill;
    }

    public Color getStroke() { return null == stroke ? _stroke : stroke.get(); }
    public void setStroke(final Color stroke) {
        if (null == this.stroke) {
            _stroke = stroke;
        } else {
            this.stroke.set(stroke);
        }
    }
    public ObjectProperty<Color> strokeProperty() {
        if (null == stroke) {
            stroke = new ObjectPropertyBase<>(_stroke) {
                @Override protected void invalidated() {}
                @Override public Object getBean() { return MapPoint.this; }
                @Override public String getName() { return "stroke"; }
            };
            _stroke = null;
        }
        return stroke;
    }

    @Override public String toString() {
        return new StringBuilder().append("{")
                                  .append("\"name\":\"").append(getName()).append("\",")
                                  .append("\"x\":").append(getX()).append(",")
                                  .append("\"y\":").append(getY()).append(",")
                                  .append("\"fill\":\"").append(Helper.colorToWeb(getFill())).append("\",")
                                  .append("\"stroke\":\"").append(Helper.colorToWeb(getStroke())).append("\"")
                                  .append("}").toString();
    }
}
