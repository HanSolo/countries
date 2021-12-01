package eu.hansolo.fx.countries.flag;

import eu.hansolo.fx.countries.tools.Helper;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;


public class FlagIcon extends Region {
    public  static final double    DEFAULT_FLAG_SIZE = 30;
    private static final double    PREFERRED_WIDTH   = 30;
    private static final double    PREFERRED_HEIGHT  = 30;
    private static final double    MINIMUM_WIDTH     = 5;
    private static final double    MINIMUM_HEIGHT    = 5;
    private static final double    MAXIMUM_WIDTH     = 1024;
    private static final double    MAXIMUM_HEIGHT    = 1024;
    private              double    size;
    private              double    width;
    private              double    height;
    private              ImageView imageView;
    private              Flag      flag;
    private              double    flagSize;


    // ******************** Constructors **************************************
    public FlagIcon() {
        this(Flag.GERMANY, 30);
    }
    public FlagIcon(final Flag flag) {
        this(flag, 30);
    }
    public FlagIcon(final Flag flag, final double flagSize) {
        this.flag     = flag;
        this.flagSize = Helper.clamp(5, 1024, flagSize);
        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void initGraphics() {
        if (Double.compare(getPrefWidth(), 0.0) <= 0 || Double.compare(getPrefHeight(), 0.0) <= 0 || Double.compare(getWidth(), 0.0) <= 0 ||
            Double.compare(getHeight(), 0.0) <= 0) {
            if (getPrefWidth() > 0 && getPrefHeight() > 0) {
                setPrefSize(flagSize, flagSize);
            } else {
                setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
            }
        }
        imageView = new ImageView(flag.getImage(flagSize));
        getChildren().setAll(imageView);
    }

    private void registerListeners() {
        widthProperty().addListener(o -> resize());
        heightProperty().addListener(o -> resize());
    }


    // ******************** Methods *******************************************
    @Override protected double computeMinWidth(final double height)  { return MINIMUM_WIDTH; }
    @Override protected double computeMinHeight(final double width)  { return MINIMUM_HEIGHT; }
    @Override protected double computePrefWidth(final double height) { return super.computePrefWidth(height); }
    @Override protected double computePrefHeight(final double width) { return super.computePrefHeight(width); }
    @Override protected double computeMaxWidth(final double height)  { return MAXIMUM_WIDTH; }
    @Override protected double computeMaxHeight(final double width)  { return MAXIMUM_HEIGHT; }

    public Flag getFlag() { return flag; }
    public void setFlag(final Flag flag) {
        setFlag(flag, flagSize);
    }
    public void setFlag(final Flag flag, final double FLAG_SIZE) {
        if (null == flag) { throw new IllegalArgumentException("Flag cannot be null"); }
        this.flag = flag;
        flagSize = Helper.clamp(5, 1024, FLAG_SIZE);
        redraw();
    }

    public double getFlagSize() { return flagSize; }
    public void setFlagSize(final double flagSize) {
        this.flagSize = Helper.clamp(5, 1024, flagSize);
        redraw();
    }


    // ******************** Resizing ******************************************
    private void resize() {
        width  = getWidth() - getInsets().getLeft() - getInsets().getRight();
        height = getHeight() - getInsets().getTop() - getInsets().getBottom();
        size   = width < height ? width : height;

        if (width > 0 && height > 0) {
            imageView.setFitWidth(size);
            imageView.setFitHeight(size);
            imageView.relocate((getWidth() - size) * 0.5, (getHeight() - size) * 0.5);

            redraw();
        }
    }

    private void redraw() {
        if (null == flag) { return; }
        imageView.setImage(flag.getImage(flagSize));
    }
}
