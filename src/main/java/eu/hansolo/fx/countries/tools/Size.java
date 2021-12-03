package eu.hansolo.fx.countries.tools;

public enum Size {
    SMALL, MEDIUM, LARGE, UNKNOWN;

    public static final Size fromText(final String text) {
        switch(text) {
            case "s" -> { return SMALL;   }
            case "m" -> { return MEDIUM;  }
            case "l" -> { return LARGE;   }
            default  -> { return UNKNOWN; }
        }
    }
}
