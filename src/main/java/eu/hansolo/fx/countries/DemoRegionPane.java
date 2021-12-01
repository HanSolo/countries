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
import eu.hansolo.fx.countries.tools.Helper;
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

import java.util.List;
import java.util.stream.Collectors;


public class DemoRegionPane extends Application {
    public static final String VERSION = PropertyManager.INSTANCE.getVersionNumber();
    private RegionPane  regionPane;


    @Override public void init() {
        CRegion     region       = BusinessRegion.EUROPEAN_UNION;
        List<Poi>   capitals     = Helper.getCapitals()
                                         .stream()
                                         .filter(city -> region.getCountries().contains(city.country()))
                                         .map(city -> PoiBuilder.create().lat(city.lat()).lon(city.lon()).name(city.name()).fill(Color.CYAN).pointSize(PointSize.NORMAL).build())
                                         .collect(Collectors.toList());

        List<Point> heatmapSpots = Helper.getCities()
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
