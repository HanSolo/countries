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

    exports eu.hansolo.fx.countries;
    exports eu.hansolo.fx.countries.evt;
    exports eu.hansolo.fx.countries.flag;
    exports eu.hansolo.fx.countries.font;
    exports eu.hansolo.fx.countries.tools;
}