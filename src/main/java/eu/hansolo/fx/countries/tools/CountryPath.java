package eu.hansolo.fx.countries.tools;

import javafx.scene.control.Tooltip;
import javafx.scene.shape.SVGPath;

import java.util.Locale;


public class CountryPath extends SVGPath {
    private String  name;
    private Locale  locale;
    private Tooltip tooltip;


    // ******************** Constructors **************************************
    public CountryPath() {
        this ("", null);
    }
    public CountryPath(final String name) {
        this(name, null);
    }
    public CountryPath(final String name, final String content) {
        super();
        if (null == content) return;
        this.name    = name;
        this.locale  = new Locale("", name);
        this.tooltip = new Tooltip(locale.getDisplayCountry());

        Tooltip.install(CountryPath.this, tooltip);
        setContent(content);
    }


    // ******************** Methods *******************************************
    public String getName() { return name; }
    public void setName(final String name) { this.name = name; }

    public Locale getLocale() { return locale; }
    public void setLocale(final Locale locale) { this.locale = locale; }

    public Tooltip getTooltip() { return tooltip; }
    public void setTooltip(final Tooltip tooltip) {
        this.tooltip = tooltip;
        Tooltip.install(CountryPath.this, this.tooltip);
    }

    @Override public String toString() {
        return new StringBuilder().append("{")
                                  .append("\"name\":\"").append(name).append("\",")
                                  .append("\"locale\":\"").append(locale).append("\",")
                                  .append("\"tooltip\":\"").append(tooltip.getText()).append("\",")
                                  .append("\"content\":\"").append(getContent()).append("\"")
                                  .append("}")
                                  .toString();
    }
}
