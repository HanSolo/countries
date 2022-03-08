module eu.hansolo.fx.countries {
    // Java
    requires java.base;
    requires java.net.http;
    requires java.desktop;

    // Java-FX
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires transitive javafx.swing;

    // 3rd Party
    requires transitive eu.hansolo.toolbox;
    requires transitive eu.hansolo.toolboxfx;
    requires transitive eu.hansolo.fx.heatmap;

    opens eu.hansolo.fx.countries to eu.hansolo.toolbox, eu.hansolo.toolboxfx, eu.hansolo.fx.heatmap;
    opens eu.hansolo.fx.countries.evt to eu.hansolo.toolbox, eu.hansolo.toolboxfx, eu.hansolo.fx.heatmap;
    opens eu.hansolo.fx.countries.flag to eu.hansolo.toolbox, eu.hansolo.toolboxfx, eu.hansolo.fx.heatmap;
    opens eu.hansolo.fx.countries.tools to eu.hansolo.toolbox, eu.hansolo.toolboxfx, eu.hansolo.fx.heatmap;

    exports eu.hansolo.fx.countries;
    exports eu.hansolo.fx.countries.evt;
    exports eu.hansolo.fx.countries.flag;
    exports eu.hansolo.fx.countries.tools;
}