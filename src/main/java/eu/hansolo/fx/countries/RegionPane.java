package eu.hansolo.fx.countries;

import eu.hansolo.fx.countries.evt.Evt;
import eu.hansolo.fx.countries.evt.EvtObserver;
import eu.hansolo.fx.countries.font.Fonts;
import eu.hansolo.fx.countries.tools.CRegion;
import eu.hansolo.fx.countries.tools.ColorMapping;
import eu.hansolo.fx.countries.tools.Connection;
import eu.hansolo.fx.countries.tools.Constants;
import eu.hansolo.fx.countries.tools.CountryPath;
import eu.hansolo.fx.countries.tools.HeatMap;
import eu.hansolo.fx.countries.tools.HeatMapBuilder;
import eu.hansolo.fx.countries.tools.Helper;
import eu.hansolo.fx.countries.tools.Location;
import eu.hansolo.fx.countries.tools.Mapping;
import eu.hansolo.fx.countries.tools.OpacityDistribution;
import eu.hansolo.fx.countries.tools.Poi;
import eu.hansolo.fx.countries.tools.Point;
import javafx.application.Platform;
import javafx.beans.DefaultProperty;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.event.WeakEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.scene.input.MouseEvent.MOUSE_ENTERED;
import static javafx.scene.input.MouseEvent.MOUSE_EXITED;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;
import static javafx.scene.input.MouseEvent.MOUSE_RELEASED;


@DefaultProperty("children")
public class RegionPane extends Region implements EvtObserver<Connection> {
    private static final double                          PREFERRED_WIDTH  = 250;
    private static final double                          PREFERRED_HEIGHT = 250;
    private static final double                          MINIMUM_WIDTH    = 50;
    private static final double                          MINIMUM_HEIGHT   = 50;
    private static final double                          MAXIMUM_WIDTH    = 4096;
    private static final double                          MAXIMUM_HEIGHT   = 4096;
    private static       double                          aspectRatio;
    private              double                          size;
    private              double                          width;
    private              double                          height;
    private              Canvas                          overlay;
    private              GraphicsContext                 overlayCtx;
    private              HeatMap                         heatmap;
    private              Canvas                          canvas;
    private              GraphicsContext                 ctx;
    private              Pane                            pane;
    private              Group                           group;
    private              CRegion                         region;
    private              Map<Country, List<CountryPath>> countryPaths;
    private              Collection<CountryPath>         paths;
    private              ObservableList<Connection>      connections;
    private              double                          regionOffsetX;
    private              double                          regionOffsetY;
    private              double                          scaleX;
    private              double                          scaleY;
    private              Color                           _fill;
    private              ObjectProperty<Color>           fill;
    private              Color                           _stroke;
    private              ObjectProperty<Color>           stroke;
    private              double                          _lineWidth;
    private              DoubleProperty                  lineWidth;
    private              Color                           _poiFill;
    private              ObjectProperty<Color>           poiFill;
    private              Color                           _poiStroke;
    private              ObjectProperty<Color>           poiStroke;
    private              Color                           _poiTextFill;
    private              ObjectProperty<Color>           poiTextFill;
    private              boolean                         _poisVisible;
    private              BooleanProperty                 poisVisible;
    private              boolean                         _poiTextVisible;
    private              BooleanProperty                 poiTextVisible;
    private              boolean                         _hoverEnabled;
    private              BooleanProperty                 hoverEnabled;
    private              boolean                         _selectionEnabled;
    private              BooleanProperty                 selectionEnabled;
    private              Country                         _selectedCountry;
    private              ObjectProperty<Country>         selectedCountry;
    private              Country                         formerSelectedCountry;
    private              Color                           _hoverColor;
    private              ObjectProperty<Color>           hoverColor;
    private              Color                           _pressedColor;
    private              ObjectProperty<Color>           pressedColor;
    private              Color                           _selectedColor;
    private              ObjectProperty<Color>           selectedColor;
    private              ObservableList<Poi>             pois;
    private              List<Point>                     heatmapSpots;
    private              BooleanBinding                  showing;
    private              ListChangeListener<Poi>         poiListChangeListener;
    private              ListChangeListener<Connection>  connectionListChangeListener;
    // internal event handlers
    protected            EventHandler<MouseEvent>        _mouseEnterHandler;
    protected            EventHandler<MouseEvent>        _mousePressHandler;
    protected            EventHandler<MouseEvent>        _mouseReleaseHandler;
    protected            EventHandler<MouseEvent>        _mouseExitHandler;
    // exposed event handlers
    private              EventHandler<MouseEvent>        mouseEnterHandler;
    private              EventHandler<MouseEvent>        mousePressHandler;
    private              EventHandler<MouseEvent>        mouseReleaseHandler;
    private              EventHandler<MouseEvent>        mouseExitHandler;


    // ******************** Constructors **************************************
    public RegionPane(final CRegion region) {
        this.region                       = region;
        this.paths                        = new ArrayList<>();
        this.connections                  = FXCollections.observableArrayList();
        this._fill                        = Constants.FILL;
        this._stroke                      = Constants.STROKE;
        this._lineWidth                   = 1;
        this._poiFill                     = Constants.POI_FILL;
        this._poiStroke                   = Color.TRANSPARENT;
        this._poisVisible                 = false;
        this._poiTextVisible              = false;
        this._poiTextFill                 = Constants.TEXT_FILL;
        this._hoverEnabled                = false;
        this._selectionEnabled            = false;
        this._selectedCountry             = null;
        this.formerSelectedCountry        = null;
        this._hoverColor                  = Color.web("#3b5b6b");
        this._pressedColor                = Color.DARKBLUE;
        this._selectedColor               = Color.web("#28596f");
        this.pois                         = FXCollections.observableArrayList();
        this.heatmapSpots                 = new ArrayList<>();
        this.poiListChangeListener        = c -> {
            while (c.next()) {
                if (c.wasAdded()) {

                } else if (c.wasRemoved()) {

                }
            }
            redraw();
        };
        this.connectionListChangeListener = c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    c.getAddedSubList().forEach(connection -> connection.addEvtObserver(RegionPane.this));
                } else if (c.wasRemoved()) {
                    c.getRemoved().forEach(connection -> connection.removeEvtObserver(RegionPane.this));
                }
            }
            if (overlay.isVisible()) { redrawOverlay(); }
        };
        this._mouseEnterHandler           = evt -> handleMouseEvent(evt, mouseEnterHandler);
        this._mousePressHandler           = evt -> handleMouseEvent(evt, mousePressHandler);
        this._mouseReleaseHandler         = evt -> handleMouseEvent(evt, mouseReleaseHandler);
        this._mouseExitHandler            = evt -> handleMouseEvent(evt, mouseExitHandler);

        final List<Point> regionBounds = region.getRegionBounds();
        final Point       upperLeft    = Helper.latLonToXY(regionBounds.get(0));
        final Point       lowerRight   = Helper.latLonToXY(regionBounds.get(1));
        regionOffsetX = upperLeft.getX();
        regionOffsetY = upperLeft.getY();
        aspectRatio   = (lowerRight.getY() - upperLeft.getY()) / (lowerRight.getX() - upperLeft.getX());

        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void initRegion() {
        final List<Point> regionBounds = region.getRegionBounds();
        final Point       upperLeft    = Helper.latLonToXY(regionBounds.get(0));
        final Point       lowerRight   = Helper.latLonToXY(regionBounds.get(1));
        regionOffsetX = upperLeft.getX();
        regionOffsetY = upperLeft.getY();
        aspectRatio   = (lowerRight.getY() - upperLeft.getY()) / (lowerRight.getX() - upperLeft.getX());
        pois.clear();
        heatmapSpots.clear();
        heatmap.clearHeatMap();
        double scaledLineWidth = getLineWidth() / scaleX;

        countryPaths = region.getCountryPaths();
        paths        = region.getPaths();
        paths.forEach(path -> {
            path.setFill(getFill());
            path.setStroke(getStroke());
            path.setStrokeWidth(scaledLineWidth);
            path.setTranslateX(-regionOffsetX);
            path.setTranslateY(-regionOffsetY);
            path.setStrokeLineCap(StrokeLineCap.ROUND);
            path.setStrokeLineJoin(StrokeLineJoin.ROUND);
            path.setOnMouseEntered(new WeakEventHandler<>(_mouseEnterHandler));
            path.setOnMousePressed(new WeakEventHandler<>(_mousePressHandler));
            path.setOnMouseReleased(new WeakEventHandler<>(_mouseReleaseHandler));
            path.setOnMouseExited(new WeakEventHandler<>(_mouseExitHandler));
        });
        group.getChildren().setAll(paths);
        layoutChildren();
        resize();
    }

    private void initGraphics() {
        if (Double.compare(getPrefWidth(), 0.0) <= 0 || Double.compare(getPrefHeight(), 0.0) <= 0 || Double.compare(getWidth(), 0.0) <= 0 || Double.compare(getHeight(), 0.0) <= 0) {
            if (getPrefWidth() > 0 && getPrefHeight() > 0) {
                setPrefSize(getPrefWidth(), getPrefHeight());
            } else {
                setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
            }
        }

        overlay    = new Canvas(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        overlayCtx = overlay.getGraphicsContext2D();
        overlay.setMouseTransparent(true);
        overlay.setVisible(false);
        overlay.setManaged(false);

        heatmap  = HeatMapBuilder.create()
                                 .prefSize(1009, 665)
                                 .colorMapping(ColorMapping.INFRARED_4)
                                 .spotRadius(5)
                                 .fadeColors(true)
                                 .opacityDistribution(OpacityDistribution.LINEAR)
                                 .heatMapOpacity(0.75)
                                 .build();
        heatmap.setVisible(false);
        heatmap.setManaged(false);
        heatmap.setMouseTransparent(true);

        canvas = new Canvas(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        ctx    = canvas.getGraphicsContext2D();
        canvas.setMouseTransparent(true);

        countryPaths = region.getCountryPaths();
        paths        = region.getPaths();
        paths.forEach(path -> {
            path.setFill(getFill());
            path.setStroke(getStroke());
            path.setStrokeWidth(getLineWidth());
            path.setTranslateX(-regionOffsetX);
            path.setTranslateY(-regionOffsetY);
            path.setStrokeLineCap(StrokeLineCap.ROUND);
            path.setStrokeLineJoin(StrokeLineJoin.ROUND);
            path.setOnMouseEntered(new WeakEventHandler<>(_mouseEnterHandler));
            path.setOnMousePressed(new WeakEventHandler<>(_mousePressHandler));
            path.setOnMouseReleased(new WeakEventHandler<>(_mouseReleaseHandler));
            path.setOnMouseExited(new WeakEventHandler<>(_mouseExitHandler));
        });
        
        group = new Group();
        group.getChildren().addAll(paths);

        pane = new Pane(group);

        setBackground(Constants.BACKGROUND);

        getChildren().setAll(pane, canvas, heatmap, overlay);
    }

    private void registerListeners() {
        widthProperty().addListener(o -> resize());
        heightProperty().addListener(o -> resize());
        pois.addListener(poiListChangeListener);
        connections.addListener(connectionListChangeListener);

        if (null != getScene()) {
            setupBinding();
        } else {
            sceneProperty().addListener((o1, ov1, nv1) -> {
                if (null == nv1) { return; }
                if (null != getScene().getWindow()) {
                    setupBinding();
                } else {
                    sceneProperty().get().windowProperty().addListener((o2, ov2, nv2) -> {
                        if (null == nv2) { return; }
                        setupBinding();
                    });
                }
            });
        }
    }

    private void setupBinding() {
        showing = Bindings.createBooleanBinding(() -> {
            if (getScene() != null && getScene().getWindow() != null) {
                return getScene().getWindow().isShowing();
            } else {
                return false;
            }
        }, sceneProperty(), getScene().windowProperty(), getScene().getWindow().showingProperty());

        showing.addListener(o -> {
            if (showing.get()) {
                resize();
                heatmap.clearHeatMap();
                List<Point> scaledSpots = new ArrayList<>();
                heatmapSpots.forEach(spot -> {
                    final Point  p = Helper.latLonToXY(spot);
                    final double x = (p.getX() - regionOffsetX) * scaleX;
                    final double y = (p.getY() - regionOffsetY) * scaleY;
                    scaledSpots.add(new Point(x, y));
                });
                heatmap.setSpots(scaledSpots);
            }
        });
    }


    // ******************** Methods *******************************************
    @Override protected double computeMinWidth(final double height) { return MINIMUM_WIDTH; }
    @Override protected double computeMinHeight(final double width) { return MINIMUM_HEIGHT; }
    @Override protected double computePrefWidth(final double height) { return super.computePrefWidth(height); }
    @Override protected double computePrefHeight(final double width) { return super.computePrefHeight(width); }
    @Override protected double computeMaxWidth(final double height) { return MAXIMUM_WIDTH; }
    @Override protected double computeMaxHeight(final double width) { return MAXIMUM_HEIGHT; }

    @Override public ObservableList<Node> getChildren() { return super.getChildren(); }

    public CRegion getRegion() { return region; }
    public void setCountry(final CRegion region) {
        this.region = region;
        initRegion();
    }

    public void setBackground(final Paint paint) {
        setBackground(new Background(new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public Color getFill() { return null == fill ? _fill : fill.get(); }
    public void setFill(final Color fill) {
        if (null == this.fill) {
            _fill = fill;
            countryPaths.entrySet().forEach(entry -> entry.getValue().forEach(countryPath -> countryPath.setFill(null == entry.getKey().getFill() ? fill : entry.getKey().getFill())));
        } else {
            this.fill.set(fill);
        }
    }
    public ObjectProperty<Color> fillProperty() {
        if (null == fill) {
            fill = new ObjectPropertyBase<>(_fill) {
                @Override protected void invalidated() {
                    countryPaths.entrySet().forEach(entry -> entry.getValue().forEach(countryPath -> countryPath.setFill(null == entry.getKey().getFill() ? get() : entry.getKey().getFill())));
                }
                @Override public Object getBean() { return RegionPane.this; }
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
            countryPaths.entrySet().forEach(entry -> entry.getValue().forEach(countryPath -> countryPath.setStroke(null == entry.getKey().getStroke() ? stroke : entry.getKey().getStroke())));
        } else {
            this.stroke.set(stroke);
        }
    }
    public ObjectProperty<Color> strokeProperty() {
        if (null == stroke) {
            stroke = new ObjectPropertyBase<>(_stroke) {
                @Override protected void invalidated() {
                    countryPaths.entrySet().forEach(entry -> entry.getValue().forEach(countryPath -> countryPath.setStroke(null == entry.getKey().getStroke() ? get() : entry.getKey().getStroke())));
                }
                @Override public Object getBean() { return RegionPane.this; }
                @Override public String getName() { return "stroke"; }
            };
            _stroke = null;
        }
        return stroke;
    }

    public double getLineWidth() { return null == lineWidth ? _lineWidth : lineWidth.get(); }
    public void setLineWidth(final double lineWidth) {
        if (null == this.lineWidth) {
            _lineWidth = lineWidth;
            countryPaths.entrySet().forEach(entry -> entry.getValue().forEach(countryPath -> countryPath.setStrokeWidth(lineWidth)));
        } else {
            this.lineWidth.set(lineWidth);
        }
    }
    public DoubleProperty lineWidthProperty() {
        if (null == lineWidth) {
            lineWidth = new DoublePropertyBase(_lineWidth) {
                @Override protected void invalidated() {
                    countryPaths.entrySet().forEach(entry -> entry.getValue().forEach(countryPath -> countryPath.setStrokeWidth(get())));
                }
                @Override public Object getBean() { return RegionPane.this; }
                @Override public String getName() { return "lineWidth"; }
            };
        }
        return lineWidth;
    }

    public Color getPoiFill() { return null == poiFill ? _poiFill : poiFill.get(); }
    public void setPoiFill(final Color poiFill) {
        if (null == this.poiFill) {
            _poiFill = poiFill;
            redraw();
        } else {
            this.poiFill.set(poiFill);
        }
    }
    public ObjectProperty<Color> poiFillProperty() {
        if (null == poiFill) {
            poiFill = new ObjectPropertyBase<>(_poiFill) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return RegionPane.this; }
                @Override public String getName() { return "poiFill"; }
            };
            _poiFill = null;
        }
        return poiFill;
    }

    public Color getPoiStroke() { return null == poiStroke ? _poiStroke : poiStroke.get(); }
    public void setPoiStroke(final Color poiStroke) {
        if (null == this.poiStroke) {
            _poiStroke = poiStroke;
            redraw();
        } else {
            this.poiStroke.set(poiStroke);
        }
    }
    public ObjectProperty<Color> poiStrokeProperty() {
        if (null == poiStroke) {
            poiStroke = new ObjectPropertyBase<>(_poiStroke) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return RegionPane.this; }
                @Override public String getName() { return "poiStroke"; }
            };
            _poiStroke = null;
        }
        return poiStroke;
    }

    public boolean getPoisVisible() { return null == poisVisible ? _poisVisible : poisVisible.get(); }
    public void setPoisVisible(final boolean poisVisible) {
        if (null == this.poisVisible) {
            _poisVisible = poisVisible;
            redraw();
        } else {
            this.poisVisible.set(poisVisible);
        }
    }
    public BooleanProperty poisVisibleProperty() {
        if (null == poisVisible) {
            poisVisible = new BooleanPropertyBase(_poisVisible) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return RegionPane.this; }
                @Override public String getName() { return "poisVisible"; }
            };
        }
        return poisVisible;
    }

    public boolean getPoiTextVisible() { return null == poiTextVisible ? _poiTextVisible : poiTextVisible.get(); }
    public void setPoiTextVisible(final boolean poiTextVisible) {
        if (null == this.poiTextVisible) {
            _poiTextVisible = poiTextVisible;
            redraw();
        } else {
            this.poiTextVisible.set(poiTextVisible);
        }
    }
    public BooleanProperty poiTextVisibleProperty() {
        if (null == poiTextVisible) {
            poiTextVisible = new BooleanPropertyBase(_poiTextVisible) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return RegionPane.this; }
                @Override public String getName() { return "poiTextVisible"; }
            };
        }
        return poiTextVisible;
    }

    public Color getPoiTextFill() { return null == poiTextFill ? _poiTextFill : poiTextFill.get(); }
    public void setPoiTextFill(final Color poiTextFill) {
        if (null == this.poiTextFill) {
            _poiTextFill = poiTextFill;
            redraw();
        } else {
            this.poiTextFill.set(poiTextFill);
        }
    }
    public ObjectProperty<Color> poiTextFillProperty() {
        if (null == poiTextFill) {
            poiTextFill = new ObjectPropertyBase<>(_poiTextFill) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return RegionPane.this; }
                @Override public String getName() { return "poiTextFill"; }
            };
            _poiTextFill = null;
        }
        return poiTextFill;
    }

    public List<Poi> getPois() { return Collections.unmodifiableList(pois); }
    public void addPoi(final Poi poi) {
        if (!pois.contains(poi)) { pois.add(poi); }
    }
    public void removePoi(final Poi poi) {
        if (pois.contains(poi)) { pois.remove(poi); }
    }
    public void addPois(final List<Poi> pois) {
        this.pois.removeListener(poiListChangeListener);
        pois.forEach(poi -> addPoi(poi));
        this.pois.addListener(poiListChangeListener);
        redraw();
    }
    public void removePois(final List<Poi> pois) {
        this.pois.removeListener(poiListChangeListener);
        pois.forEach(poi -> removePoi(poi));
        this.pois.addListener(poiListChangeListener);
        redraw();
    }
    public void setPois(final List<Poi> pois) {
        this.pois.removeListener(poiListChangeListener);
        this.pois.setAll(pois);
        this.pois.addListener(poiListChangeListener);
        redraw();
    }
    public void clearPois() { pois.clear(); }

    public List<Connection> getConnections() { return Collections.unmodifiableList(connections); }
    public void addConnection(final Connection connection) {
        if (!connections.contains(connection)) { connections.add(connection); }
    }
    public void removeConnection(final Connection connection) {
        if (connections.contains(connection)) { connections.remove(connection); }
    }
    public void addConnections(final List<Connection> connections) {
        this.connections.removeListener(connectionListChangeListener);
        connections.forEach(connection -> {
            connection.addEvtObserver(RegionPane.this);
            addConnection(connection);
        });
        this.connections.addListener(connectionListChangeListener);
        redraw();
    }
    public void removeConnections(final List<Connection> connections) {
        this.connections.removeListener(connectionListChangeListener);
        connections.forEach(connection -> {
            connection.removeEvtObserver(RegionPane.this);
            removeConnection(connection);
        });
        this.connections.addListener(connectionListChangeListener);
        redraw();
    }
    public void setConnections(final List<Connection> connections) {
        this.connections.removeListener(connectionListChangeListener);
        connections.forEach(connection -> connection.addEvtObserver(RegionPane.this));
        this.connections.setAll(connections);
        this.connections.addListener(connectionListChangeListener);
        redraw();
    }
    public void clearConnections() { connections.clear(); }

    public boolean isShowing() { return null != showing && showing.get(); }
    public BooleanBinding showingProperty() { return showing; }


    // ******************** Interaction ***************************************
    public boolean isHoverEnabled() { return null == hoverEnabled ? _hoverEnabled : hoverEnabled.get(); }
    public void setHoverEnabled(final boolean hoverEnabled) {
        if (null == this.hoverEnabled) {
            _hoverEnabled = hoverEnabled;
        } else {
            this.hoverEnabled.set(hoverEnabled);
        }
    }
    public BooleanProperty hoverEnabledProperty() {
        if (null == hoverEnabled) {
            hoverEnabled = new BooleanPropertyBase(_hoverEnabled) {
                @Override public Object getBean() { return RegionPane.this; }
                @Override public String getName() { return "hoverEnabled"; }
            };
        }
        return hoverEnabled;
    }

    public boolean isSelectionEnabled() { return null == selectionEnabled ? _selectionEnabled : selectionEnabled.get(); }
    public void setSelectionEnabled(final boolean selectionEnabled) {
        if (null == this.selectionEnabled) {
            _selectionEnabled = selectionEnabled;
        } else {
            this.selectionEnabled.set(selectionEnabled);
        }
    }
    public BooleanProperty selectionEnabledProperty() {
        if (null == selectionEnabled) {
            selectionEnabled = new BooleanPropertyBase() {
                @Override public Object getBean() { return RegionPane.this; }
                @Override public String getName() { return "selectionEnabled"; }
            };
        }
        return selectionEnabled;
    }

    public Country getSelectedCountry() { return null == selectedCountry ? _selectedCountry : selectedCountry.get(); }
    public void setSelectedCountry(final Country selectedCountry) {
        if (null == this.selectedCountry) {
            _selectedCountry = selectedCountry;
        } else {
            this.selectedCountry.set(selectedCountry);
        }
    }
    public ObjectProperty<Country> selectedCountryProperty() {
        if (null == selectedCountry) {
            selectedCountry = new ObjectPropertyBase<>(_selectedCountry) {
                @Override public Object getBean() { return RegionPane.this; }
                @Override public String getName() { return "selectedCountry"; }
            };
            _selectedCountry = null;
        }
        return selectedCountry;
    }

    public Color getHoverColor() { return null == hoverColor ? _hoverColor : hoverColor.getValue(); }
    public void setHoverColor(final Color hoverColor) {
        if (null == this.hoverColor) {
            _hoverColor = hoverColor;
        } else {
            this.hoverColor.setValue(hoverColor);
        }
    }
    public ObjectProperty<Color> hoverColorProperty() {
        if (null == hoverColor) {
            hoverColor = new ObjectPropertyBase<>(_hoverColor) {
                @Override public Object getBean() { return RegionPane.this; }
                @Override public String getName() { return "hoverColor"; }
            };
            _hoverColor = null;
        }
        return hoverColor;
    }

    public Color getPressedColor() { return null == pressedColor ? _pressedColor : pressedColor.getValue(); }
    public void setPressedColor(final Color pressedColor) {
        if (null == this.pressedColor) {
            _pressedColor = pressedColor;
        } else {
            this.pressedColor.setValue(pressedColor);
        }
    }
    public ObjectProperty<Color> pressedColorProperty() {
        if (null == pressedColor) {
            pressedColor = new ObjectPropertyBase<>(_pressedColor) {
                @Override public Object getBean() { return RegionPane.this; }
                @Override public String getName() { return "pressedColor"; }
            };
            _pressedColor = null;
        }
        return pressedColor;
    }

    public Color getSelectedColor() { return null == selectedColor ? _selectedColor : selectedColor.getValue(); }
    public void setSelectedColor(final Color selectedColor) {
        if (null == this.selectedColor) {
            _selectedColor = selectedColor;
        } else {
            this.selectedColor.setValue(selectedColor);
        }
    }
    public ObjectProperty<Color> selectedColorProperty() {
        if (null == selectedColor) {
            selectedColor = new ObjectPropertyBase<>(_selectedColor) {
                @Override public Object getBean() { return RegionPane.this; }
                @Override public String getName() { return "selectedColor"; }
            };
            _selectedColor = null;
        }
        return selectedColor;
    }

    public void setMouseEnterHandler(final EventHandler<MouseEvent> handler) { mouseEnterHandler = handler; }
    public void setMousePressHandler(final EventHandler<MouseEvent> handler) { mousePressHandler = handler; }
    public void setMouseReleaseHandler(final EventHandler<MouseEvent> handler) { mouseReleaseHandler = handler;  }
    public void setMouseExitHandler(final EventHandler<MouseEvent> handler) { mouseExitHandler = handler; }


    // ******************** Heatmap related ***********************************
    public boolean isHeatmapVisible() { return heatmap.isVisible(); }
    public void setHeatmapVisible(final boolean heatmapVisible) {
        heatmap.setManaged(heatmapVisible);
        heatmap.setVisible(heatmapVisible);
        if (heatmapVisible) { redraw(); }
    }

    public boolean isOverlayVisible() { return overlay.isVisible(); }
    public void setOverlayVisible(final boolean overlayVisible) {
        overlay.setManaged(overlayVisible);
        overlay.setVisible(overlayVisible);
        if (overlayVisible) { redrawOverlay(); }
    }

    public Mapping getHeatmapColorMapping() { return heatmap.getColorMapping(); }
    public void setHeatmapColorMapping(final Mapping colorMapping) { Platform.runLater(() -> heatmap.setColorMapping(colorMapping)); }

    public double getHeatmapSpotRadius() { return heatmap.getSpotRadius(); }
    public void setHeatmapSpotRadius(final double spotRadius) { heatmap.setSpotRadius(spotRadius); }

    public boolean isHeatmapFadeColors() { return heatmap.isFadeColors(); }
    public void setHeatmapFadeColors(final boolean fadeColors) { Platform.runLater(() -> heatmap.setFadeColors(fadeColors)); }

    public OpacityDistribution getHeatmapOpacityDistribution() { return heatmap.getOpacityDistribution(); }
    public void setHeatmapOpacityDistribution(final OpacityDistribution opacityDistribution) { heatmap.setOpacityDistribution(opacityDistribution); }

    public double getHeatmapOpacity() { return heatmap.getOpacity(); }
    public void setHeatmapOpacity(final double opacity) { heatmap.setOpacity(opacity); }

    public List<Point> getHeatmapSpots() { return heatmapSpots; }
    public void setHeatmapSpots(final List<Point> spots) {
        heatmapSpots.clear();
        heatmapSpots.addAll(spots);
        Platform.runLater(() -> {
            List<Point> scaledSpots = new ArrayList<>();
            heatmapSpots.forEach(spot -> {
                final Point  p = Helper.latLonToXY(spot);
                final double x = (p.getX() - regionOffsetX) * scaleX;
                final double y = (p.getY() - regionOffsetY) * scaleY;
                scaledSpots.add(new Point(x, y));
            });
            heatmap.setSpots(scaledSpots);
        });
        redraw();
    }
    public void addHeatmapSpot(final Point spot) {
        if (heatmapSpots.contains(spot)) { return; }
        heatmapSpots.add(spot);
        Platform.runLater(() -> {
            final Point  p = Helper.latLonToXY(spot);
            final double x = (p.getX() - regionOffsetX) * scaleX;
            final double y = (p.getY() - regionOffsetY) * scaleY;
            heatmap.addSpot(x, y);
        });
        redraw();
    }

    public void clearHeatmap() {
        heatmapSpots.clear();
        heatmap.clearHeatMap();
    }


    // ******************** EventHandling *************************************
    private void handleMouseEvent(final MouseEvent event, final EventHandler<MouseEvent> handler) {
        final CountryPath       countryPath = (CountryPath) event.getSource();
        final String            countryName = countryPath.getName();
        final Country           country     = Country.valueOf(countryName);
        final List<CountryPath> paths = countryPaths.get(country);
        final EventType         type  = event.getEventType();
        if (MOUSE_ENTERED == type) {
            if (isHoverEnabled()) {
                Color color = isSelectionEnabled() && country.equals(getSelectedCountry()) ? getSelectedColor() : getHoverColor();
                for (SVGPath path : paths) { path.setFill(color); }
            }
        } else if (MOUSE_PRESSED == type) {
            if (isSelectionEnabled()) {
                Color color;
                if (null == getSelectedCountry()) {
                    setSelectedCountry(country);
                    color = getSelectedColor();
                } else {
                    color = null == getSelectedCountry().getFill() ? getFill() : getSelectedCountry().getFill();
                }
                for (SVGPath path : countryPaths.get(getSelectedCountry())) { path.setFill(color); }
            } else {
                if (isHoverEnabled()) {
                    for (SVGPath path : paths) { path.setFill(getPressedColor()); }
                }
            }
        } else if (MOUSE_RELEASED == type) {
            Color color;
            if (isSelectionEnabled()) {
                if (formerSelectedCountry == country) {
                    setSelectedCountry(null);
                    color = null == country.getFill() ? getFill() : country.getFill();
                } else {
                    setSelectedCountry(country);
                    color = getSelectedColor();
                }
                formerSelectedCountry = getSelectedCountry();
            } else {
                color = getHoverColor();
            }
            if (isHoverEnabled()) {
                for (SVGPath path : paths) { path.setFill(color); }
            }
        } else if (MOUSE_EXITED == type) {
            if (isHoverEnabled()) {
                Color color = isSelectionEnabled() && country.equals(getSelectedCountry()) ? getSelectedColor() : getFill();
                for (SVGPath path : paths) {
                    path.setFill(null == country.getFill() || country == getSelectedCountry() ? color : country.getFill());
                }
            }
        }

        if (null != handler) handler.handle(event);
    }

    @Override public void onEvt(final Evt<Connection> evt) {
        switch(evt.getEventType()) {
            case UPDATE   -> { if (overlay.isVisible()) { redrawOverlay(); } }
            case SELECTED -> { if (overlay.isVisible()) { redrawOverlay(); } }
        }
    }


    // ******************** Layout *******************************************
    @Override public void layoutChildren() {
        super.layoutChildren();
    }

    private void resize() {
        width  = getWidth()- getInsets().getLeft() - getInsets().getRight();
        height = getHeight() - getInsets().getTop() - getInsets().getBottom();
        size   = width < height ? width : height;

        if (aspectRatio * width > height) {
            width = 1 / (aspectRatio / height);
        } else if (1 / (aspectRatio / height) > width) {
            height = aspectRatio * width;
        }

        if (width > 0 && height > 0) {
            scaleX = width / pane.getWidth();
            scaleY = height / pane.getHeight();

            double scaledLineWidth = getLineWidth() / scaleX;
            double relocateX       = (getWidth() - pane.getWidth()) * 0.5;
            double relocateY       = (getHeight() - pane.getHeight()) * 0.5;

            pane.setCache(true);
            pane.setCacheHint(CacheHint.SCALE);
            pane.setScaleX(scaleX);
            pane.setScaleY(scaleY);
            pane.setCache(false);

            paths.forEach(path -> path.setStrokeWidth(scaledLineWidth));

            pane.relocate(relocateX, relocateY);

            canvas.setWidth(pane.getWidth() * scaleX);
            canvas.setHeight(pane.getHeight() * scaleY);
            canvas.relocate((getWidth() - canvas.getWidth()) * 0.5, (getHeight() - canvas.getHeight()) * 0.5);

            heatmap.setFitWidth(pane.getWidth() * scaleX);
            heatmap.setFitHeight(pane.getHeight() * scaleY);
            heatmap.relocate((getWidth() - canvas.getWidth()) * 0.5, (getHeight() - canvas.getHeight()) * 0.5);

            overlay.setWidth(pane.getWidth() * scaleX);
            overlay.setHeight(pane.getHeight() * scaleY);
            overlay.relocate((getWidth() - overlay.getWidth()) * 0.5, (getHeight() - overlay.getHeight()) * 0.5);

            redraw();
        }
    }

    private void redraw() {
        final double width    = canvas.getWidth();
        final double height   = canvas.getHeight();
        final double fontsize = size * 0.015;

        ctx.clearRect(0, 0, width, height);
        ctx.setFont(Fonts.opensansLight(fontsize));
        ctx.setTextBaseline(VPos.CENTER);
        ctx.setTextAlign(TextAlignment.CENTER);

        // Draw pois
        if (getPoisVisible()) {
            Map<Poi, Point> points = new HashMap<>();
            pois.forEach(poi -> {
                ctx.setFill(null == poi.getFill() ? getPoiFill() : poi.getFill());
                ctx.setStroke(null == poi.getStroke() ? getPoiStroke() : poi.getStroke());
                final Point  p = Helper.latLonToXY(poi.getLonLat());
                final double x = (p.getX() - regionOffsetX) * scaleX;
                final double y = (p.getY() - regionOffsetY) * scaleY;
                if (null == poi.getImage() && null == poi.getSvgPath()) {
                    final double r;
                    final double d;
                    switch (poi.getPointSize()) {
                        case TINY   -> { r = 0.5; d = 1;  }
                        case SMALL  -> { r = 1;   d = 2;  }
                        case NORMAL -> { r = 1.5; d = 3;  }
                        case BIG    -> { r = 2.5; d = 5; }
                        case HUGE   -> { r = 4;   d = 8; }
                        default     -> { r = 1;   d = 2;  }
                    }
                    if (d >= 1) {
                        ctx.fillOval(x - r, y - r, d, d);
                    }
                } else {
                    if (null != poi.getImage()) {
                        Image img = poi.getImage();
                        ctx.drawImage(img, x - img.getWidth() * 0.5, y - img.getHeight() * 0.5, img.getWidth(), img.getHeight());
                    }
                    if (null != poi.getSvgPath() && null != poi.getSvgPathDim()) {
                        ctx.save();
                        ctx.translate(x - poi.getSvgPathDim().getWidth() * 0.5, y - poi.getSvgPathDim().getHeight() * 0.5);
                        ctx.beginPath();
                        ctx.appendSVGPath(poi.getSvgPath());
                        ctx.closePath();
                        ctx.setFill(Color.LIME);
                        ctx.fill();
                        ctx.stroke();
                        ctx.restore();
                    }
                }
                points.put(poi, new Point(x, y));
            });
            if (getPoiTextVisible() && fontsize > 6) {
                ctx.setFill(getPoiTextFill());
                points.entrySet().forEach(entry -> {
                    ctx.fillText(entry.getKey().getName(), entry.getValue().getX(), entry.getValue().getY() - fontsize);
                });
            }
        }

        // Draw overlay
        if (overlay.isVisible()) {
            redrawOverlay();
        }
    }

    private void redrawOverlay() {
        final double width  = canvas.getWidth();
        final double height = canvas.getHeight();

        overlayCtx.clearRect(0, 0, width, height);

        for (Connection connection : connections) {
            final Location p1 = connection.getTargetLocation();
            final Location p2 = connection.getSourceLocation();

            if (null == p1 || null == p2) { continue; }

            final Point xy1       = Helper.latLonToXY(p1.getLatitude(), p1.getLongitude());
            final Point xy2       = Helper.latLonToXY(p2.getLatitude(), p2.getLongitude());
            final Point midPoint  = Helper.getMidPoint(xy1, xy2);
            final Point midPoint1 = Helper.getMidPoint(xy1, midPoint);
            final Point midPoint2 = Helper.getMidPoint(xy2, midPoint);
            final Point rotCp1    = Helper.getMidPoint(xy1, midPoint2);
            final Point rotCp2    = Helper.getMidPoint(midPoint1, xy2);

            final double cpAngle  = Helper.getControlPointAngle(xy1, xy2);

            xy1.translate(-regionOffsetX, -regionOffsetY);
            xy1.scale(scaleX, scaleY);
            xy2.translate(-regionOffsetX, -regionOffsetY);
            xy2.scale(scaleX, scaleY);
            midPoint.translate(-regionOffsetX, -regionOffsetY);
            midPoint.scale(scaleX, scaleY);
            midPoint1.translate(-regionOffsetX, -regionOffsetY);
            midPoint1.scale(scaleX, scaleY);
            midPoint2.translate(-regionOffsetX, -regionOffsetY);
            midPoint2.scale(scaleX, scaleY);
            rotCp1.translate(-regionOffsetX, -regionOffsetY);
            rotCp1.scale(scaleX, scaleY);
            rotCp2.translate(-regionOffsetX, -regionOffsetY);
            rotCp2.scale(scaleX, scaleY);

            final Point cp1;
            final Point cp2;
            if (xy2.getX() > xy1.getX()) {
                cp1 = Helper.rotatePointAroundRotationCenter(rotCp1, xy1, -cpAngle);
                cp2 = Helper.rotatePointAroundRotationCenter(rotCp2, xy2, cpAngle);
                overlayCtx.beginPath();
                overlayCtx.moveTo(xy1.getX(), xy1.getY());
                overlayCtx.bezierCurveTo(cp1.getX(), cp1.getY(), cp2.getX(), cp2.getY(), xy2.getX(), xy2.getY());
            } else {
                cp1 = Helper.rotatePointAroundRotationCenter(rotCp1, xy1, cpAngle);
                cp2 = Helper.rotatePointAroundRotationCenter(rotCp2, xy2, -cpAngle);
                overlayCtx.beginPath();
                overlayCtx.moveTo(xy2.getX(), xy2.getY());
                overlayCtx.bezierCurveTo(cp2.getX(), cp2.getY(), cp1.getX(), cp1.getY(), xy1.getX(), xy1.getY());
            }

            overlayCtx.setLineWidth(connection.getLineWidth());
            if (connection.getGradientFill()) {
                LinearGradient gradient = new LinearGradient(xy1.getX(), xy1.getY(), xy2.getX(), xy2.getY(), false, CycleMethod.NO_CYCLE,
                                                             new Stop(0.0, connection.getTargetColor()),
                                                             new Stop(0.5, connection.getSourceColor()),
                                                             new Stop(1.0, connection.getSourceColor()));
                overlayCtx.setStroke(gradient);
            } else {
                overlayCtx.setStroke(connection.isSelected() ? connection.getSelectedStroke() : connection.getStroke());
            }
            overlayCtx.stroke();

            // Draw arrows
            final Point  pointNearStart = Helper.getCubicBezierXYatT(xy1, cp1, cp2, xy2, 0.01);
            final double dx             = xy1.getX() - pointNearStart.getX();
            final double dy             = xy1.getY() - pointNearStart.getY();
            final double angleAtEnd     = Math.toDegrees(Math.atan2(dy, dx));

            if (connection.getArrowsVisible()) {
                final double arrowSize = connection.getLineWidth() * 1.5;
                overlayCtx.beginPath();
                overlayCtx.save();
                if (connection.getGradientFill()) {
                    overlayCtx.setFill(connection.getTargetColor());
                } else {
                    overlayCtx.setFill(connection.isSelected() ? connection.getSelectedStroke() : connection.getStroke());
                }
                overlayCtx.translate(xy1.getX(), xy1.getY());
                overlayCtx.rotate(angleAtEnd);
                overlayCtx.moveTo(-arrowSize * 3, 0);
                overlayCtx.lineTo(-arrowSize * 3, -arrowSize * 2); // Point 1 of arrow
                overlayCtx.lineTo(0, 0);
                overlayCtx.lineTo(-arrowSize * 3, arrowSize * 2); // Point 2 of arrow
                overlayCtx.lineTo(-arrowSize * 3, 0);
                overlayCtx.closePath();
                overlayCtx.fill();
                overlayCtx.restore();
            }
        }
    }
}
