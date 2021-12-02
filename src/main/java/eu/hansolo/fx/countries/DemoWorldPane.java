package eu.hansolo.fx.countries;

import eu.hansolo.fx.countries.tools.Connection;
import eu.hansolo.fx.countries.tools.ConnectionBuilder;
import eu.hansolo.fx.countries.tools.ConnectionPartType;
import eu.hansolo.fx.countries.tools.Helper;
import eu.hansolo.fx.countries.tools.Location;
import eu.hansolo.fx.countries.tools.LocationBuilder;
import eu.hansolo.fx.countries.tools.OpacityDistribution;
import eu.hansolo.fx.countries.tools.Poi;
import eu.hansolo.fx.countries.tools.PoiBuilder;
import eu.hansolo.fx.countries.tools.Point;
import eu.hansolo.fx.countries.tools.PointSize;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DemoWorldPane extends Application {
    public static final String      VERSION = PropertyManager.INSTANCE.getVersionNumber();
    private             WorldPane   worldPane;


    @Override public void init() {
        List<Poi> capitals = Helper.getCapitals()
                                   .stream()
                                   .map(city -> PoiBuilder.create().lat(city.lat()).lon(city.lon()).name(city.name()).fill(Color.CYAN).pointSize(PointSize.NORMAL).build())
                                   .collect(Collectors.toList());

        Location fmo = LocationBuilder.create().name("FMO").latitude(52.1307).longitude(7.6941).connectionPartType(ConnectionPartType.SOURCE).build();
        Location sin = LocationBuilder.create().name("SIN").latitude(1.3644).longitude(103.9915).connectionPartType(ConnectionPartType.TARGET).build();
        Location jfk = LocationBuilder.create().name("JFK").latitude(40.6413).longitude(-73.7781).connectionPartType(ConnectionPartType.SOURCE).build();
        Location hnd = LocationBuilder.create().name("HND").latitude(35.5494).longitude(139.7798).connectionPartType(ConnectionPartType.TARGET).build();
        Location syd = LocationBuilder.create().name("SYD").latitude(-33.95).longitude(151.1819).connectionPartType(ConnectionPartType.SOURCE).build();
        Location sfo = LocationBuilder.create().name("SFO").latitude(37.6213).longitude(-122.379).connectionPartType(ConnectionPartType.TARGET).build();

        Connection fmoToSin = ConnectionBuilder.create(fmo, sin).arrowsVisible(true).lineWidth(2).stroke(Color.CYAN).build();
        Connection jfkToHnd = ConnectionBuilder.create(jfk, hnd).arrowsVisible(true).lineWidth(2).stroke(Color.MAGENTA).build();
        Connection sydToSfo = ConnectionBuilder.create(syd, sfo).arrowsVisible(true).lineWidth(2).stroke(Color.YELLOW).build();

        List<Point> heatmapSpots = new ArrayList<>();

        Helper.getCities()
              .stream()
              //.filter(city -> city.country() == Country.DE)
              .filter(city -> city.population() > 1_000_000)
              .forEach(city -> heatmapSpots.add(new Point(city.lon(), city.lat()))); // Keep in mind that longitude == x and latitude == y

        worldPane = WorldPaneBuilder.create()
                                    .poisVisible(true)
                                    .poiTextVisible(true)
                                    .heatmapVisible(true)
                                    .heatmapSpotRadius(3)
                                    .heatmapOpacityDistribution(OpacityDistribution.LINEAR)
                                    .heatmapSpots(heatmapSpots)
                                    .pois(capitals)
                                    .connections(List.of(fmoToSin, jfkToHnd, sydToSfo))
                                    .overlayVisible(true)
                                    .hoverEnabled(true)
                                    .selectionEnabled(true)
                                    .build();

        worldPane.selectedCountryProperty().addListener((o, ov, nv) -> System.out.println(nv));
    }

    @Override public void start(final Stage stage) {
        StackPane pane = new StackPane(worldPane);
        pane.setPrefSize(800, 400);
        pane.setPadding(new Insets(10));

        Scene scene = new Scene(pane);

        stage.setTitle("WorldPane Version: " + VERSION);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
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
