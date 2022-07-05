package eu.hansolo.fx.countries;

import eu.hansolo.fx.countries.tools.Connection;
import eu.hansolo.fx.countries.tools.ConnectionBuilder;
import eu.hansolo.fx.countries.tools.ConnectionPartType;
import eu.hansolo.fx.countries.tools.Helper;
import eu.hansolo.fx.countries.tools.CLocation;
import eu.hansolo.fx.countries.tools.Records.Airport;
import eu.hansolo.fx.countries.tools.Records.Airport2;
import eu.hansolo.toolboxfx.geom.Poi;
import eu.hansolo.toolboxfx.geom.PoiBuilder;
import eu.hansolo.toolboxfx.geom.PoiSize;
import eu.hansolo.toolboxfx.geom.Point;
import eu.hansolo.fx.heatmap.OpacityDistribution;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class DemoWorldPane extends Application {
    public static final String      VERSION = PropertyManager.INSTANCE.getVersionNumber();
    private             WorldPane   worldPane;


    @Override public void init() {
        List<Poi> capitals = Helper.getCapitals()
                                   .stream()
                                   .map(city -> PoiBuilder.create().lat(city.lat()).lon(city.lon()).name(city.name()).fill(Color.CYAN).pointSize(PoiSize.NORMAL).build())
                                   .collect(Collectors.toList());

        Map<String,Airport> airports = Helper.getAirports();
        
        CLocation fmo = airports.get("FMO").toLocationBuilder().connectionPartType(ConnectionPartType.SOURCE).build();
        CLocation sin = airports.get("SIN").toLocationBuilder().connectionPartType(ConnectionPartType.TARGET).build();
        CLocation jfk = airports.get("JFK").toLocationBuilder().connectionPartType(ConnectionPartType.SOURCE).build();
        CLocation hnd = airports.get("HND").toLocationBuilder().connectionPartType(ConnectionPartType.TARGET).build();
        CLocation syd = airports.get("SYD").toLocationBuilder().connectionPartType(ConnectionPartType.SOURCE).build();
        CLocation sfo = airports.get("SFO").toLocationBuilder().connectionPartType(ConnectionPartType.TARGET).build();

        Connection fmoToSin = ConnectionBuilder.create(fmo, sin).arrowsVisible(true).lineWidth(2).stroke(Color.CYAN).build();
        Connection jfkToHnd = ConnectionBuilder.create(jfk, hnd).arrowsVisible(true).lineWidth(2).stroke(Color.MAGENTA).build();
        Connection sydToSfo = ConnectionBuilder.create(syd, sfo).arrowsVisible(true).lineWidth(2).stroke(Color.YELLOW).build();

        Map<String, Airport2> airports2 = Helper.getAirports2();
        Airport2 fmo2 = airports2.get("FMO");
        System.out.println(fmo2.name() + "   " + fmo2.iata() + "   " + fmo2.icao());


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
