package eu.hansolo.fx.countries;

import eu.hansolo.fx.countries.tools.Helper;
import eu.hansolo.fx.countries.tools.Poi;
import eu.hansolo.fx.countries.tools.PoiBuilder;
import eu.hansolo.fx.countries.tools.Point;
import eu.hansolo.fx.countries.tools.PointSize;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class DemoCountryPane extends Application {
    public static final String      VERSION = PropertyManager.INSTANCE.getVersionNumber();
    private             Country     country;
    private             CountryPane countryPane;


    @Override public void init() {
        country = Country.DE;
        List<Poi>   pois         = new ArrayList<>();
        List<Point> heatmapSpots = new ArrayList<>();

        Poi muenster = PoiBuilder.create()
                                 .lat(51.91183747470598)
                                 .lon(7.633806255269727)
                                 .name("M\u00fcnster")
                                 .image(Country.DE.getFlag().getImage())
                                 .svgPath("M9.998,0.004l2.24,6.908l7.252,0l-5.867,4.27l2.241,6.909l-5.866,-4.27l-5.867,4.27l2.241,-6.909l-5.867,-4.27l7.252,0l2.241,-6.908Z")
                                 .svgPathDim(new Dimension2D(20, 20))
                                 .build();

        Helper.getCities()
              .stream()
              .filter(city -> city.country() == country)
              .filter(city -> city.population() > 300_000)// && city.population() <= 1_000_000)
              .forEach(city -> {
                  Poi poi = PoiBuilder.create().lat(city.lat()).lon(city.lon()).name(city.name()).fill(Color.RED).pointSize(PointSize.NORMAL).build();
                  pois.add(poi);
                  heatmapSpots.add(new Point(city.lon(), city.lat())); // keep in mind that longitude = x and latitude = y
              });


        countryPane = CountryPaneBuilder.create(country)
                                        .pois(pois)
                                        .poisVisible(true)
                                        .poiTextVisible(true)
                                        .heatmapVisible(true)
                                        .heatmapSpots(heatmapSpots)
                                        .build();
    }

    @Override public void start(final Stage stage) {
        StackPane pane = new StackPane(countryPane);
        pane.setPrefSize(400, 400);
        pane.setPadding(new Insets(10));

        Scene scene = new Scene(pane);

        stage.setTitle("CountryPane Version: " + VERSION);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
        //countryPane.addHeatmapSpot(new Point(7.633806255269727, 51.91183747470598));
        //countryPane.setHeatmapSpots(spots);
    }


    @Override public void stop() {
        // Remove event handlers

        // Shutdown
        Platform.exit();
        System.exit(0);
    }


    // ******************** Launching *******************************
    public static void main(final String[] args) {
        launch(args);
    }
}
