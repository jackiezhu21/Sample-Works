
public class ImageProcessor {

    private Pic origPic;
    private Pic watermark;
    private Pic newPic;

    public ImageProcessor(String picFile) {

        origPic = new Pic(picFile);
        newPic = origPic.deepCopy();
    }

    public ImageProcessor(String picFile, String watermarkFile) {

        this(picFile);
        this.watermark = new Pic(watermarkFile);
    }

    public void greyscale() {
        newPic = origPic.deepCopy();
        for (int i = 0; i < (newPic.getWidth()); i++) {
            for (int j = 0; j < (newPic.getHeight()); j++) {
                int totalRed = newPic.getPixel(j, i).getRed();
                int totalGreen = newPic.getPixel(j, i).getGreen();
                int totalBlue = newPic.getPixel(j, i).getBlue();
                int totalPixVal = totalRed + totalBlue + totalGreen;
                int avgVal = totalPixVal / 3;
                newPic.getPixel(j, i).setRed(avgVal);
                newPic.getPixel(j, i).setGreen(avgVal);
                newPic.getPixel(j, i).setBlue(avgVal);

            }
        }
        newPic.show();
    }

    public void invert() {
        newPic = origPic.deepCopy();
        for (int i = 0; i < (newPic.getWidth()); i++) {
            for (int j = 0; j < (newPic.getHeight()); j++) {
                int redVal = newPic.getPixel(j, i).getRed();
                int greenVal = newPic.getPixel(j, i).getGreen();
                int blueVal = newPic.getPixel(j, i).getBlue();
                int newRed = 255 - redVal;
                int newGreen = 255 - greenVal;
                int newBlue = 255 - blueVal;
                newPic.getPixel(j, i).setRed(newRed);
                newPic.getPixel(j, i).setGreen(newGreen);
                newPic.getPixel(j, i).setBlue(newBlue);

            }
        }
        newPic.show();
    }

    public void noRed() {
        newPic = origPic.deepCopy();
        for (int i = 0; i < (newPic.getWidth()); i++) {
            for (int j = 0; j < (newPic.getHeight()); j++) {
                newPic.getPixel(j, i).setRed(0);


            }
        }
        newPic.show();
    }

    public void noBlue() {
        newPic = origPic.deepCopy();
        for (int i = 0; i < (newPic.getWidth()); i++) {
            for (int j = 0; j < (newPic.getHeight()); j++) {
                newPic.getPixel(j, i).setBlue(0);

            }
        }
        newPic.show();
    }

    public void noGreen() {
        newPic = origPic.deepCopy();
        for (int i = 0; i < (newPic.getWidth()); i++) {
            for (int j = 0; j < (newPic.getHeight()); j++) {
                newPic.getPixel(j, i).setGreen(0);

            }
        }
        newPic.show();
    }

    public void maximize() {
        newPic = origPic.deepCopy();
        for (int i = 0; i < (newPic.getWidth()); i++) {
            for (int j = 0; j < (newPic.getHeight()); j++) {
                int redVal = newPic.getPixel(j, i).getRed();
                int greenVal = newPic.getPixel(j, i).getGreen();
                int blueVal = newPic.getPixel(j, i).getBlue();

                if ((redVal > greenVal) && (redVal > blueVal)) {
                    newPic.getPixel(j, i).setGreen(0);
                    newPic.getPixel(j, i).setBlue(0);
                } else if ((greenVal > redVal) && (greenVal > blueVal)) {
                    newPic.getPixel(j, i).setRed(0);
                    newPic.getPixel(j, i).setBlue(0);
                } else if ((blueVal > redVal) && (blueVal > greenVal)) {
                    newPic.getPixel(j, i).setRed(0);
                    newPic.getPixel(j, i).setGreen(0);
                } else if ((redVal == greenVal) && (greenVal == blueVal)) {
                    newPic.getPixel(j, i).setRed(redVal);
                    newPic.getPixel(j, i).setGreen(greenVal);
                    newPic.getPixel(j, i).setBlue(blueVal);
                } else if ((redVal == greenVal) && (redVal > blueVal)) {
                    newPic.getPixel(j, i).setRed(redVal);
                    newPic.getPixel(j, i).setGreen(greenVal);
                    newPic.getPixel(j, i).setBlue(0);
                } else if ((greenVal == blueVal) && (greenVal > redVal)) {
                    newPic.getPixel(j, i).setRed(0);
                    newPic.getPixel(j, i).setGreen(greenVal);
                    newPic.getPixel(j, i).setBlue(blueVal);
                } else if ((blueVal == redVal) && (blueVal > greenVal)) {
                    newPic.getPixel(j, i).setRed(redVal);
                    newPic.getPixel(j, i).setGreen(0);
                    newPic.getPixel(j, i).setBlue(blueVal);
                }

            }
        }
        newPic.show();
    }

    public void overlay(Pic watermark) {
        newPic = origPic.deepCopy();
        int heightBound, widthBound;
        heightBound = newPic.getHeight();
        widthBound = newPic.getWidth();
        if (newPic.getHeight() > watermark.getHeight()) {
            heightBound = watermark.getHeight();
        }
        if (newPic.getWidth() > watermark.getWidth()) {
            widthBound = watermark.getWidth();
        }
        for (int i = 0; i < widthBound; i++) {
            for (int j = 0; j < heightBound; j++) {
                int picRed = newPic.getPixel(j, i).getRed();
                int watermarkRed = watermark.getPixel(j, i).getRed();
                int picGreen = newPic.getPixel(j, i).getGreen();
                int watermarkGreen = watermark.getPixel(j, i).getGreen();
                int picBlue = newPic.getPixel(j, i).getBlue();
                int watermarkBlue = watermark.getPixel(j, i).getBlue();
                int newRed = (picRed + watermarkRed) / 2;
                int newGreen = (picGreen + watermarkGreen) / 2;
                int newBlue = (picBlue + watermarkBlue) / 2;
                newPic.getPixel(j, i).setRed(newRed);
                newPic.getPixel(j, i).setGreen(newGreen);
                newPic.getPixel(j, i).setBlue(newBlue);
            }
        }
        newPic.show();
    }

    public static void main(String[]args) {
        String picFileName = args[0];
        ImageProcessor editPic = new ImageProcessor(picFileName);
        if (args.length > 1) {
            String watermarkFile = args[1];
            editPic.overlay(new Pic(watermarkFile));
        }
        editPic.greyscale();
        editPic.invert();
        editPic.noRed();
        editPic.noBlue();
        editPic.noGreen();
        editPic.maximize();
    }
}