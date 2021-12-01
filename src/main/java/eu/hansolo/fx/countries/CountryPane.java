package eu.hansolo.fx.countries;

import eu.hansolo.fx.countries.font.Fonts;
import eu.hansolo.fx.countries.tools.ColorMapping;
import eu.hansolo.fx.countries.tools.Constants;
import eu.hansolo.fx.countries.tools.HeatMap;
import eu.hansolo.fx.countries.tools.HeatMapBuilder;
import eu.hansolo.fx.countries.tools.Helper;
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
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@DefaultProperty("children")
public class CountryPane extends Region {
    private static final double                  PREFERRED_WIDTH  = 250;
    private static final double                  PREFERRED_HEIGHT = 250;
    private static final double                  MINIMUM_WIDTH    = 50;
    private static final double                  MINIMUM_HEIGHT   = 50;
    private static final double                  MAXIMUM_WIDTH    = 4096;
    private static final double                  MAXIMUM_HEIGHT   = 4096;
    private static       double                  aspectRatio;
    private              double                  size;
    private              double                  width;
    private              double                  height;
    private              HeatMap                 heatmap;
    private              Canvas                  canvas;
    private              GraphicsContext         ctx;
    private              Pane                    pane;
    private              Group                   group;
    private              Country                 country;
    private              double                  countryOffsetX;
    private              double                  countryOffsetY;
    private              double                  scaleX;
    private              double                  scaleY;
    private              Color                   _fill;
    private              ObjectProperty<Color>   fill;
    private              Color                   _stroke;
    private              ObjectProperty<Color>   stroke;
    private              double                  _lineWidth;
    private              DoubleProperty          lineWidth;
    private              Color                   _poiFill;
    private              ObjectProperty<Color>   poiFill;
    private              Color                   _poiStroke;
    private              ObjectProperty<Color>   poiStroke;
    private              Color                   _poiTextFill;
    private              ObjectProperty<Color>   poiTextFill;
    private              boolean                 _poisVisible;
    private              BooleanProperty         poisVisible;
    private              boolean                 _poiTextVisible;
    private              BooleanProperty         poiTextVisible;
    private              ObservableList<Poi>     pois;
    private              List<Point>             heatmapSpots;
    private              BooleanBinding          showing;
    private              ListChangeListener<Poi> poiListChangeListener;


    // ******************** Constructors **************************************
    public CountryPane(final Country country) {
        this.country               = country;
        this._fill                 = Constants.FILL;
        this._stroke               = Constants.STROKE;
        this._lineWidth            = 1;
        this._poiFill              = Constants.POI_FILL;
        this._poiStroke            = Color.TRANSPARENT;
        this._poisVisible          = false;
        this._poiTextVisible       = false;
        this._poiTextFill          = Constants.TEXT_FILL;
        this.pois                  = FXCollections.observableArrayList();
        this.heatmapSpots          = new ArrayList<>();
        this.poiListChangeListener = c -> {
            while (c.next()) {
                if (c.wasAdded()) {

                } else if (c.wasRemoved()) {

                }
            }
            redraw();
        };

        final List<Point> countryBounds = Country.getCountryBounds().get(country);
        final Point       upperLeft     = Helper.latLonToXY(countryBounds.get(0));
        final Point       lowerRight    = Helper.latLonToXY(countryBounds.get(1));
        countryOffsetX = upperLeft.getX();
        countryOffsetY = upperLeft.getY();
        aspectRatio    = (lowerRight.getY() - upperLeft.getY()) / (lowerRight.getX() - upperLeft.getX());

        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void initCountry() {
        final List<Point> countryBounds = Country.getCountryBounds().get(country);
        final Point       upperLeft     = Helper.latLonToXY(countryBounds.get(0));
        final Point       lowerRight    = Helper.latLonToXY(countryBounds.get(1));
        countryOffsetX = upperLeft.getX();
        countryOffsetY = upperLeft.getY();
        aspectRatio    = (lowerRight.getY() - upperLeft.getY()) / (lowerRight.getX() - upperLeft.getX());
        pois.clear();
        heatmapSpots.clear();
        heatmap.clearHeatMap();
        double scaledLineWidth = getLineWidth() / scaleX;
        country.getPaths().forEach(path -> {
            path.setFill(null == country.getFill() ? getFill() : country.getFill());
            path.setStroke(null == country.getStroke() ? getStroke() : country.getStroke());
            path.setStrokeWidth(scaledLineWidth);
            path.setTranslateX(-countryOffsetX);
            path.setTranslateY(-countryOffsetY);
            path.setStrokeLineCap(StrokeLineCap.ROUND);
            path.setStrokeLineJoin(StrokeLineJoin.ROUND);
        });
        group.getChildren().setAll(country.getPaths());
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

        heatmap  = HeatMapBuilder.create()
                                 .prefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT)
                                 .colorMapping(ColorMapping.INFRARED_4)
                                 .spotRadius(5)
                                 .fadeColors(true)
                                 .opacityDistribution(OpacityDistribution.LINEAR)
                                 .heatMapOpacity(0.75)
                                 .build();
        heatmap.setVisible(false);
        heatmap.setManaged(false);

        canvas = new Canvas(PREFERRED_WIDTH, PREFERRED_HEIGHT);
        ctx    = canvas.getGraphicsContext2D();

        country.getPaths().forEach(path -> {
            path.setFill(null == country.getFill() ? getFill() : country.getFill());
            path.setStroke(null == country.getStroke() ? getStroke() : country.getStroke());
            path.setStrokeWidth(getLineWidth());
            path.setTranslateX(-countryOffsetX);
            path.setTranslateY(-countryOffsetY);
            path.setStrokeLineCap(StrokeLineCap.ROUND);
            path.setStrokeLineJoin(StrokeLineJoin.ROUND);
        });
        group = new Group();
        group.getChildren().addAll(country.getPaths());

        pane = new Pane(group);

        setBackground(Constants.BACKGROUND);

        getChildren().setAll(pane, canvas, heatmap);
    }

    private void registerListeners() {
        widthProperty().addListener(o -> resize());
        heightProperty().addListener(o -> resize());
        pois.addListener(poiListChangeListener);

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
                Platform.runLater(() -> {
                    heatmap.clearHeatMap();
                    heatmapSpots.forEach(spot -> {
                        final Point  p = Helper.latLonToXY(spot);
                        final double x = (p.getX() - countryOffsetX) * scaleX;
                        final double y = (p.getY() - countryOffsetY) * scaleY;
                        heatmap.addSpot(x, y);
                    });
                });
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

    public Country getCountry() { return country; }
    public void setCountry(final Country country) {
        this.country = country;
        initCountry();
    }

    public void setBackground(final Paint paint) {
        setBackground(new Background(new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public Color getFill() { return null == fill ? _fill : fill.get(); }
    public void setFill(final Color fill) {
        if (null == this.fill) {
            _fill = fill;
            redraw();
        } else {
            this.fill.set(fill);
        }
    }
    public ObjectProperty<Color> fillProperty() {
        if (null == fill) {
            fill = new ObjectPropertyBase<>(_fill) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return CountryPane.this; }
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
            redraw();
        } else {
            this.stroke.set(stroke);
        }
    }
    public ObjectProperty<Color> strokeProperty() {
        if (null == stroke) {
            stroke = new ObjectPropertyBase<>(_stroke) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return CountryPane.this; }
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
            redraw();
        } else {
            this.lineWidth.set(lineWidth);
        }
    }
    public DoubleProperty lineWidthProperty() {
        if (null == lineWidth) {
            lineWidth = new DoublePropertyBase(_lineWidth) {
                @Override protected void invalidated() { redraw(); }
                @Override public Object getBean() { return CountryPane.this; }
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
                @Override public Object getBean() { return CountryPane.this; }
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
                @Override public Object getBean() { return CountryPane.this; }
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
                @Override public Object getBean() { return CountryPane.this; }
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
                @Override public Object getBean() { return CountryPane.this; }
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
                @Override public Object getBean() { return CountryPane.this; }
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

    public boolean isShowing() { return null != showing && showing.get(); }
    public BooleanBinding showingProperty() { return showing; }


    // ******************** Heatmap related ***********************************
    public boolean isHeatmapVisible() { return heatmap.isVisible(); }
    public void setHeatmapVisible(final boolean heatmapVisible) {
        heatmap.setManaged(heatmapVisible);
        heatmap.setVisible(heatmapVisible);
    }

    public Mapping getHeatmapColorMapping() { return heatmap.getColorMapping(); }
    public void setHeatmapColorMapping(final Mapping colorMapping) { heatmap.setColorMapping(colorMapping); }

    public double getHeatmapSpotRadius() { return heatmap.getSpotRadius(); }
    public void setHeatmapSpotRadius(final double spotRadius) { heatmap.setSpotRadius(spotRadius); }

    public boolean isHeatmapFadeColors() { return heatmap.isFadeColors(); }
    public void setHeatmapFadeColors(final boolean fadeColors) { heatmap.setFadeColors(fadeColors); }

    public OpacityDistribution getHeatmapOpacityDistribution() { return heatmap.getOpacityDistribution(); }
    public void setHeatmapOpacityDistribution(final OpacityDistribution opacityDistribution) { heatmap.setOpacityDistribution(opacityDistribution); }

    public double getHeatmapOpacity() { return heatmap.getOpacity(); }
    public void setHeatmapOpacity(final double opacity) { heatmap.setOpacity(opacity); }

    public List<Point> getHeatmapSpots() { return heatmapSpots; }
    public void setHeatmapSpots(final List<Point> spots) {
        heatmapSpots.clear();
        heatmapSpots.addAll(spots);
        Platform.runLater(() -> {
            heatmap.clearHeatMap();
            heatmapSpots.forEach(spot -> {
                final Point  p = Helper.latLonToXY(spot);
                final double x = (p.getX() - countryOffsetX) * scaleX;
                final double y = (p.getY() - countryOffsetY) * scaleY;
                heatmap.addSpot(x, y);
            });
        });
        redraw();
    }
    public void addHeatmapSpot(final Point spot) {
        if (heatmapSpots.contains(spot)) { return; }
        heatmapSpots.add(spot);
        Platform.runLater(() -> {
            final Point  p = Helper.latLonToXY(spot);
            final double x = (p.getX() - countryOffsetX) * scaleX;
            final double y = (p.getY() - countryOffsetY) * scaleY;
            heatmap.addSpot(x, y);
        });
        redraw();
    }

    public void clearHeatmap() {
        heatmapSpots.clear();
        heatmap.clearHeatMap();
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

            country.getPaths().forEach(path -> path.setStrokeWidth(scaledLineWidth));

            pane.relocate(relocateX, relocateY);

            canvas.setWidth(pane.getWidth() * scaleX);
            canvas.setHeight(pane.getHeight() * scaleY);
            canvas.relocate((getWidth() - canvas.getWidth()) * 0.5, (getHeight() - canvas.getHeight()) * 0.5);

            heatmap.setFitWidth(pane.getWidth() * scaleX);
            heatmap.setFitHeight(pane.getHeight() * scaleY);
            heatmap.relocate((getWidth() - canvas.getWidth()) * 0.5, (getHeight() - canvas.getHeight()) * 0.5);

            redraw();
        }
    }

    private void redraw() {
        double width    = canvas.getWidth();
        double height   = canvas.getHeight();
        double fontsize = size * 0.015;

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
                final double x = (p.getX() - countryOffsetX) * scaleX;
                final double y = (p.getY() - countryOffsetY) * scaleY;
                if (null == poi.getImage() && null == poi.getSvgPath()) {
                    final double r;
                    final double d;
                    switch (poi.getPointSize()) {
                        case TINY   -> { r = 0.5; d = 1;  }
                        case SMALL  -> { r = 1;   d = 2;  }
                        case NORMAL -> { r = 2.5; d = 5;  }
                        case BIG    -> { r = 5;   d = 10; }
                        case HUGE   -> { r = 10;  d = 20; }
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
    }
}