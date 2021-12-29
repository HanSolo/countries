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

package eu.hansolo.fx.countries;

import eu.hansolo.fx.countries.tools.BusinessRegion;
import eu.hansolo.fx.countries.tools.CRegion;
import eu.hansolo.fx.countries.tools.Connection;
import eu.hansolo.fx.countries.tools.ConnectionBuilder;
import eu.hansolo.fx.countries.tools.ConnectionPartType;
import eu.hansolo.fx.countries.tools.Helper;
import eu.hansolo.fx.countries.tools.CLocation;
import eu.hansolo.fx.countries.tools.CLocationBuilder;
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

import java.util.List;
import java.util.stream.Collectors;


public class DemoRegionPane extends Application {
    public static final String VERSION = PropertyManager.INSTANCE.getVersionNumber();
    private RegionPane  regionPane;
    private List<Point> heatmapSpots;


    @Override public void init() {
        CRegion     region   = BusinessRegion.EUROPEAN_UNION;
        List<Poi>   capitals = Helper.getCapitals()
                                     .stream()
                                     .filter(city -> region.getCountries().contains(city.country()))
                                     .map(city -> PoiBuilder.create().lat(city.lat()).lon(city.lon()).name(city.name()).fill(Color.CYAN).pointSize(PoiSize.NORMAL).build())
                                     .collect(Collectors.toList());

        CLocation  fmo      = CLocationBuilder.create().name("FMO").latitude(52.1307).longitude(7.6941).connectionPartType(ConnectionPartType.SOURCE).build();
        CLocation  arn      = CLocationBuilder.create().name("ARN").latitude(59.6498).longitude(17.9238).connectionPartType(ConnectionPartType.TARGET).build();
        CLocation  mad      = CLocationBuilder.create().name("MAD").latitude(40.4983).longitude(-3.5676).connectionPartType(ConnectionPartType.SOURCE).build();
        CLocation  lis      = CLocationBuilder.create().name("LIS").latitude(38.7756).longitude(-9.1354).connectionPartType(ConnectionPartType.SOURCE).build();
        Connection madToArn = ConnectionBuilder.create(mad, arn).arrowsVisible(true).lineWidth(2).stroke(Color.MAGENTA).build();
        Connection lisToArn = ConnectionBuilder.create(lis, arn).arrowsVisible(true).lineWidth(2).stroke(Color.YELLOW).build();
        Connection fmoToArn = ConnectionBuilder.create(fmo, arn).arrowsVisible(true).lineWidth(2).stroke(Color.CYAN).build();

        heatmapSpots = Helper.getCities()
                             .stream()
                             .filter(city -> region.getCountries().contains(city.country()))
                             .filter(city -> !city.isCapital())
                             .filter(city -> city.population() > 200_000)
                             .map(city -> new Point(city.lon(), city.lat())) // keep in mind that longitude = x and latitude = y
                             .collect(Collectors.toList());

        regionPane = RegionPaneBuilder.create(region)
                                      .poisVisible(true)
                                      .poiTextVisible(true)
                                      .heatmapVisible(true)
                                      .heatmapSpotRadius(5)
                                      .heatmapOpacityDistribution(OpacityDistribution.LINEAR)
                                      .heatmapSpots(heatmapSpots)
                                      .pois(capitals)
                                      .connections(List.of(fmoToArn, madToArn, lisToArn))
                                      .overlayVisible(true)
                                      .hoverEnabled(true)
                                      .selectionEnabled(true)
                                      .build();

        regionPane.selectedCountryProperty().addListener((o, ov, nv) -> System.out.println(nv));
    }

    @Override public void start(final Stage stage) {
        StackPane pane = new StackPane(regionPane);
        pane.setPrefSize(600, 400);
        pane.setPadding(new Insets(10));

        Scene scene = new Scene(pane);

        stage.setTitle("RegionPane Version: " + VERSION);
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
