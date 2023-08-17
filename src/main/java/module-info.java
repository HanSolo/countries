module eu.hansolo.fx.countries {
    // Java
    requires java.base;
    requires java.net.http;
    requires java.desktop;

    // Java-FX
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.swing;

    // 3rd Party
    requires transitive eu.hansolo.fx.heatmap;

    opens eu.hansolo.fx.countries to eu.hansolo.fx.heatmap;
    opens eu.hansolo.fx.countries.evt to eu.hansolo.fx.heatmap;
    opens eu.hansolo.fx.countries.flag to eu.hansolo.fx.heatmap;
    opens eu.hansolo.fx.countries.tools to eu.hansolo.fx.heatmap;

    exports eu.hansolo.fx.countries;
    exports eu.hansolo.fx.countries.evt;
    exports eu.hansolo.fx.countries.flag;
    exports eu.hansolo.fx.countries.tools;
}
