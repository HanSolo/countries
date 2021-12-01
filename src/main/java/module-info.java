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

    exports eu.hansolo.fx.countries;
    exports eu.hansolo.fx.countries.tools;
    exports eu.hansolo.fx.countries.evt;
    exports eu.hansolo.fx.countries.font;
    exports eu.hansolo.fx.countries.flag;
}