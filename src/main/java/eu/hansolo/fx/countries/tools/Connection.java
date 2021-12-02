package eu.hansolo.fx.countries.tools;

import eu.hansolo.fx.countries.evt.Evt;
import eu.hansolo.fx.countries.evt.EvtObserver;
import eu.hansolo.fx.countries.evt.EvtType;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Connection {
    private final Evt<Connection>               SELECTED_EVENT = new Evt<>(Connection.this, EvtType.SELECTED);
    private final Evt<Connection>               UPDATED_EVENT  = new Evt<>(Connection.this, EvtType.UPDATE);
    private       List<EvtObserver<Connection>> observers;
    private       Location                      _sourceLocation;
    private       ObjectProperty<Location>      sourceLocation;
    private       Location                      _targetLocation;
    private       ObjectProperty<Location>      targetLocation;
    private       String                        _name;
    private       StringProperty                name;
    private       double                        _value;
    private       DoubleProperty                value;
    private       Color                         _stroke;
    private       ObjectProperty<Color>         stroke;
    private       Color                         _sourceColor;
    private       ObjectProperty<Color>         sourceColor;
    private       Color                         _targetColor;
    private       ObjectProperty<Color>         targetColor;
    private       boolean                       _gradientFill;
    private       BooleanProperty               gradientFill;
    private       Color                         _selectedStroke;
    private       ObjectProperty<Color>         selectedStroke;
    private       double                        _lineWidth;
    private       DoubleProperty                lineWidth;
    private       boolean                       _arrowsVisible;
    private       BooleanProperty               arrowsVisible;
    private       String                        _tooltipText;
    private       StringProperty                tooltipText;
    private       boolean                       _selected;
    private       BooleanProperty               selected;


    public Connection(final Location sourceLocation, final Location targetLocation) {
        this(sourceLocation, targetLocation, "", 0, Color.BLACK, Color.BLUE, Color.RED, false, 1, "");
    }
    public Connection(final Location sourceLocation, final Location targetLocation, final Color stroke) {
        this(sourceLocation, targetLocation,"", 0, stroke, Color.BLUE, Color.RED, false, 1, "");
    }
    public Connection(final Location sourceLocation, final Location targetLocation, final String name) {
        this(sourceLocation, targetLocation, name, 0, Color.BLACK, Color.BLUE, Color.RED, false, 1, "");
    }
    public Connection(final Location sourceLocation, final Location targetLocation, final String name, final Color stroke) {
        this(sourceLocation, targetLocation, name, 0, stroke, Color.BLUE, Color.RED, false, 1, "");
    }
    public Connection(final Location sourceLocation, final Location targetLocation, final String name, final double value) {
        this(sourceLocation, targetLocation, name, value, Color.BLACK, Color.BLUE, Color.RED, false, 1, "");
    }
    public Connection(final Location sourceLocation, final Location targetLocation, final String name, final double value, final Color stroke) {
        this(sourceLocation, targetLocation, name, value, stroke, Color.BLUE, Color.RED, false, 1, "");
    }
    public Connection(final Location sourceLocation, final Location targetLocation, final String name, final double value, final Color sourceColor, final Color targetColor, final boolean gradientFill) {
        this(sourceLocation, targetLocation, name, value, Color.BLACK, sourceColor, targetColor, gradientFill, 1, "");
    }
    public Connection(final Location sourceLocation, final Location targetLocation, final String name, final double value, final Color sourceColor, final Color targetColor, final boolean gradientFill, final String tooltipText) {
        this(sourceLocation, targetLocation, name, value, Color.BLACK, sourceColor, targetColor, gradientFill, 1, tooltipText);
    }
    public Connection(final Location sourceLocation, final Location targetLocation, final String name, final double value, final Color stroke, final String tooltipText) {
        this(sourceLocation, targetLocation, name, value, stroke, Color.BLUE, Color.RED, false, 1, tooltipText);
    }
    public Connection(final Location sourceLocation, final Location targetLocation, final String name, final double value, final Color stroke, final Color sourceColor, final Color targetColor, final boolean gradientFill, final double lineWidth, final String tooltipText) {
        if (ConnectionPartType.SOURCE != sourceLocation.getConnectionPartType()) { throw new IllegalArgumentException("targetLocation needs to be of type SOURCE"); }
        if (ConnectionPartType.TARGET != targetLocation.getConnectionPartType()) { throw new IllegalArgumentException("sourceLocation needs to be of type TARGET"); }
        
        observers       = new CopyOnWriteArrayList<>();
        _sourceLocation = sourceLocation;
        _targetLocation = targetLocation;
        _name           = name;
        _value          = value;
        _stroke         = stroke;
        _tooltipText    = tooltipText;
        _sourceColor    = sourceColor;
        _targetColor    = targetColor;
        _gradientFill   = gradientFill;
        _selectedStroke = Color.RED;
        _lineWidth      = Helper.clamp(0.5, 10, lineWidth);
        _arrowsVisible  = false;
        _selected       = false;
    }


    public Location getTargetLocation() { return null == targetLocation ? _targetLocation : targetLocation.get(); }
    public void setTargetLocation(final Location targetLocation) {
        if (null == this.targetLocation) {
            _targetLocation = targetLocation;
            fireEvt(UPDATED_EVENT);
        } else {
            this.targetLocation.set(targetLocation);
        }
    }
    public ObjectProperty<Location> targetLocationProperty() {
        if (null == targetLocation) {
            targetLocation = new ObjectPropertyBase<Location>(_targetLocation) {
                @Override protected void invalidated() { fireEvt(UPDATED_EVENT); }
                @Override public Object getBean() { return Connection.this; }
                @Override public String getName() { return "targetLocation"; }
            };
            _targetLocation = null;
        }
        return targetLocation;
    }

    public Location getSourceLocation() { return null == sourceLocation ? _sourceLocation : sourceLocation.get(); }
    public void setSourceLocation(final Location sourceLocation) {
        if (null == this.sourceLocation) {
            _sourceLocation = sourceLocation;
            fireEvt(UPDATED_EVENT);
        } else {
            this.sourceLocation.set(sourceLocation);
        }

    }
    public ObjectProperty<Location> sourceLocationProperty() {
        if (null == sourceLocation) {
            sourceLocation = new ObjectPropertyBase<Location>(_sourceLocation) {
                @Override protected void invalidated() { fireEvt(UPDATED_EVENT); }
                @Override public Object getBean() { return Connection.this; }
                @Override public String getName() { return "sourceLocation"; }
            };
            _sourceLocation = null;
        }
        return sourceLocation;
    }

    public String getName() { return null == name ? _name : name.get(); }
    public void setName(final String name) {
        if (null == this.name) {
            _name = name;
            fireEvt(UPDATED_EVENT);
        } else {
            this.name.set(name);
        }
    }
    public StringProperty nameProperty() {
        if (null == name) {
            name = new StringPropertyBase(_name) {
                @Override protected void invalidated() { fireEvt(UPDATED_EVENT); }
                @Override public Object getBean() { return Connection.this; }
                @Override public String getName() { return "name"; }
            };
            _name = null;
        }
        return name;
    }

    public double getValue() { return null == value ? _value : value.get(); }
    public void setValue(final double value) {
        if (null == this.value) {
            _value = value;
            fireEvt(UPDATED_EVENT);
        } else {
            this.value.set(value);
        }
    }
    public DoubleProperty valueProperty() {
        if (null == value) {
            value = new DoublePropertyBase(_value) {
                @Override protected void invalidated() { fireEvt(UPDATED_EVENT); }
                @Override public Object getBean() { return Connection.this; }
                @Override public String getName() { return "value"; }
            };
        }
        return value;
    }

    public Color getStroke() { return null == stroke ? _stroke : stroke.get(); }
    public void setStroke(final Color stroke) {
        if (null == this.stroke) {
            _stroke = stroke;
            fireEvt(UPDATED_EVENT);
        } else {
            this.stroke.set(stroke);
        }
    }
    public ObjectProperty<Color> strokeProperty() {
        if (null == stroke) {
            stroke = new ObjectPropertyBase<>(_stroke) {
                @Override protected void invalidated() { fireEvt(UPDATED_EVENT); }
                @Override public Object getBean() { return Connection.this; }
                @Override public String getName() { return "stroke"; }
            };
            _stroke = null;
        }
        return stroke;
    }

    public Color getSourceColor() { return null == sourceColor ? _sourceColor : sourceColor.get(); }
    public void setSourceColor(final Color sourceColor) {
        if (null == this.sourceColor) {
            _sourceColor = sourceColor;
            fireEvt(UPDATED_EVENT);
        } else {
            this.sourceColor.set(sourceColor);
        }
    }
    public ObjectProperty<Color> sourceColorProperty() {
        if (null == sourceColor) {
            sourceColor = new ObjectPropertyBase<>(_sourceColor) {
                @Override protected void invalidated() { fireEvt(UPDATED_EVENT); }
                @Override public Object getBean() { return Connection.this; }
                @Override public String getName() { return "sourceColor"; }
            };
            _sourceColor = null;
        }
        return sourceColor;
    }

    public Color getTargetColor() { return null == targetColor ? _targetColor : targetColor.get(); }
    public void setTargetColor(final Color targetColor) {
        if (null == this.targetColor) {
            _targetColor = targetColor;
            fireEvt(UPDATED_EVENT);
        } else {
            this.targetColor.set(targetColor);
        }
    }
    public ObjectProperty<Color> targetColorProperty() {
        if (null == targetColor) {
            targetColor = new ObjectPropertyBase<>(_targetColor) {
                @Override protected void invalidated() { fireEvt(UPDATED_EVENT); }
                @Override public Object getBean() { return Connection.this; }
                @Override public String getName() { return "targetColor"; }
            };
            _targetColor = null;
        }
        return targetColor;
    }

    public boolean getGradientFill() { return null == gradientFill ? _gradientFill : gradientFill.get(); }
    public void setGradientFill(final boolean gradientFill) {
        if (null == this.gradientFill) {
            _gradientFill = gradientFill;
            fireEvt(UPDATED_EVENT);
        } else {
            this.gradientFill.set(gradientFill);
        }
    }
    public BooleanProperty gradientFillProperty() {
        if (null == gradientFill) {
            gradientFill = new BooleanPropertyBase(_gradientFill) {
                @Override protected void invalidated() { fireEvt(UPDATED_EVENT); }
                @Override public Object getBean() { return Connection.this; }
                @Override public String getName() { return "gradientFill"; }
            };
        }
        return gradientFill;
    }

    public Color getSelectedStroke() { return null == selectedStroke ? _selectedStroke : selectedStroke.get(); }
    public void setSelectedStroke(final Color selectedStroke) {
        if (null == this.selectedStroke) {
            _selectedStroke = selectedStroke;
            fireEvt(UPDATED_EVENT);
        } else {
            this.selectedStroke.set(selectedStroke);
        }
    }
    public ObjectProperty<Color> selectedStrokeProperty() {
        if (null == selectedStroke) {
            selectedStroke = new ObjectPropertyBase<>(_selectedStroke) {
                @Override protected void invalidated() { fireEvt(UPDATED_EVENT); }
                @Override public Object getBean() { return Connection.this; }
                @Override public String getName() { return "selectedStroke"; }
            };
            _selectedStroke = null;
        }
        return selectedStroke;
    }

    public double getLineWidth() { return null == lineWidth ? _lineWidth : lineWidth.get(); }
    public void setLineWidth(final double lineWidth) {
        if (null == this.lineWidth) {
            _lineWidth = Helper.clamp(0.5, 10, lineWidth);
            fireEvt(UPDATED_EVENT);
        } else {
            this.lineWidth.set(lineWidth);
        }
    }
    public DoubleProperty lineWidthProperty() {
        if (null == lineWidth) {
            lineWidth = new DoublePropertyBase(_lineWidth) {
                @Override protected void invalidated() {
                    set(Helper.clamp(0.5, 10, get()));
                    fireEvt(UPDATED_EVENT);
                }
                @Override public Object getBean() { return Connection.this; }
                @Override public String getName() { return "lineWidth"; }
            };
        }
        return lineWidth;
    }

    public boolean getArrowsVisible() { return null == arrowsVisible ? _arrowsVisible : arrowsVisible.get(); }
    public void setArrowsVisible(final boolean arrowsVisible) {
        if (null == this.arrowsVisible) {
            _arrowsVisible = arrowsVisible;
            fireEvt(UPDATED_EVENT);
        } else {
            this.arrowsVisible.set(arrowsVisible);
        }
    }
    public BooleanProperty arrowsVisibleProperty() {
        if (null == arrowsVisible) {
            arrowsVisible = new BooleanPropertyBase(_arrowsVisible) {
                @Override protected void invalidated() { fireEvt(UPDATED_EVENT); }
                @Override public Object getBean() { return Connection.this; }
                @Override public String getName() { return "arrowsVisible"; }
            };
        }
        return arrowsVisible;
    }

    public String getTooltipText() { return null == tooltipText ? _tooltipText : tooltipText.get(); }
    public void setTooltipText(final String tooltipText) {
        if (null == this.tooltipText) {
            _tooltipText = tooltipText;
        } else {
            this.tooltipText.set(tooltipText);
        }
    }
    public StringProperty tooltipTextProperty() {
        if (null == tooltipText) {
            tooltipText = new StringPropertyBase(_tooltipText) {
                @Override public Object getBean() { return Connection.this; }
                @Override public String getName() { return "tooltipText"; }
            };
        }
        return tooltipText;
    }

    public boolean isSelected() { return null == selected ? _selected : selected.get(); }
    public void setSelected(final boolean selected) {
        if (null == this.selected) {
            _selected = selected;
            fireEvt(SELECTED_EVENT);
        } else {
            this.selected.set(selected);
        }
    }
    public BooleanProperty selectedProperty() {
        if (null == selected) {
            selected = new BooleanPropertyBase(_selected) {
                @Override protected void invalidated() { fireEvt(SELECTED_EVENT); }
                @Override public Object getBean() { return Connection.this; }
                @Override public String getName() { return "selected"; }
            };
        }
        return selected;
    }


    // ******************** Event Handling ************************************
    public void setOnEvt(final EvtObserver observer) { addEvtObserver(observer); }
    public void addEvtObserver(final EvtObserver observer) { if (!observers.contains(observer)) observers.add(observer); }
    public void removeEvtObserver(final EvtObserver observer) { if (observers.contains(observer)) observers.remove(observer); }

    public void fireEvt(final Evt evt) {
        for (EvtObserver observer : observers) { observer.onEvt(evt); }
    }
}

