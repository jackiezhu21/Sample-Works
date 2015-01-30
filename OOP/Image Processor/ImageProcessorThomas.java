public class ImageProcessorThomas {

    private Pic picture;

    public ImageProcessorThomas(Pic aPic) {
        this.picture = aPic;
    }

    public void greyscale() {
        Pic copy = picture.deepCopy();
        Pixel pix;
        int red;
        int blue;
        int green;
        int avg;
        for (int y = 0; y < copy.getWidth(); y++) {
            for (int x = 0; x < copy.getHeight(); x++) {
                pix = copy.getPixel(x, y);
                red = pix.getRed();
                green = pix.getGreen();
                blue = pix.getBlue();
                avg = (red + blue + green) / 3;
                pix.setRed(avg);
                pix.setGreen(avg);
                pix.setBlue(avg);
            }
        }
        copy.show();
    }

    public void invert() {
        Pic copy = picture.deepCopy();
        Pixel pix;
        int red;
        int blue;
        int green;
        for (int y = 0; y < copy.getWidth(); y++) {
            for (int x = 0; x < copy.getHeight(); x++) {
                pix = copy.getPixel(x, y);
                red = pix.getRed();
                green = pix.getGreen();
                blue = pix.getBlue();
                pix.setRed(255 - red);
                pix.setGreen(255 - green);
                pix.setBlue(255 - blue);
            }
        }
        copy.show();
    }

    public void noRed() {
        Pic copy = picture.deepCopy();
        Pixel pix;
        int red;
        for (int y = 0; y < copy.getWidth(); y++) {
            for (int x = 0; x < copy.getHeight(); x++) {
                pix = copy.getPixel(x, y);
                pix.setRed(0);
            }
        }
        copy.show();
    }

    public void noBlue() {
        Pic copy = picture.deepCopy();
        Pixel pix;
        int blue;
        for (int y = 0; y < copy.getWidth(); y++) {
            for (int x = 0; x < copy.getHeight(); x++) {
                pix = copy.getPixel(x, y);
                pix.setBlue(0);
            }
        }
        copy.show();
    }

    public void noGreen() {
        Pic copy = picture.deepCopy();
        Pixel pix;
        int blue;
        for (int y = 0; y < copy.getWidth(); y++) {
            for (int x = 0; x < copy.getHeight(); x++) {
                pix = copy.getPixel(x, y);
                pix.setGreen(0);
            }
        }
        copy.show();
    }
    public void maximize() {
        Pic copy = picture.deepCopy();
        Pixel pix;
        int red;
        int blue;
        int green;
        for (int y = 0; y < copy.getWidth(); y++) {
            for (int x = 0; x < copy.getHeight(); x++) {
                pix = copy.getPixel(x, y);
                red = pix.getRed();
                green = pix.getGreen();
                blue = pix.getBlue();
                if (red > green && red > blue) {
                    pix.setBlue(0);
                    pix.setGreen(0);
                } else if (green > red && green > blue) {
                    pix.setBlue(0);
                    pix.setRed(0);
                } else if (blue > red && blue > green) {
                    pix.setRed(0);
                    pix.setGreen(0);
                } else if (red == blue && red != green) {
                    pix.setGreen(0);
                } else if (red == green && red != blue) {
                    pix.setBlue(0);
                } else if (blue == green && blue != red) {
                    pix.setRed(0);
                }
            }
        }
        copy.show();
    }

    public void overlay(Pic other) {
        Pic copy1 = picture.deepCopy();
        Pic copy2 = other.deepCopy();
        Pixel pix1;
        Pixel pix2;
        int red1;
        int blue1;
        int green1;
        int red2;
        int blue2;
        int green2;
        int avgRed;
        int avgGreen;
        int avgBlue;
        int copy2Height = copy2.getHeight();
        int copy2Width = copy2.getWidth();
        for (int y = 0; y < copy1.getWidth(); y++) {
            for (int x = 0; x < copy1.getHeight(); x++) {
                if ((y < copy2Width) && (x < copy2Height)) {
                    pix1 = copy1.getPixel(x, y);
                    pix2 = copy2.getPixel(x, y);

                    red1 = pix1.getRed();
                    green1 = pix1.getGreen();
                    blue1 = pix1.getBlue();

                    red2 = pix2.getRed();
                    green2 = pix2.getGreen();
                    blue2 = pix2.getBlue();

                    avgRed = (red1 + red2) / 2;
                    avgGreen = (green1 + green2) / 2;
                    avgBlue = (blue1 + blue2) / 2;

                    pix1.setRed(avgRed);
                    pix1.setGreen(avgGreen);
                    pix1.setBlue(avgBlue);
                }
            }
        }
        copy1.show();
    }

    public static void main(String[] args) {
        ImageProcessorThomas ip  = new ImageProcessorThomas(new Pic(args[0]));
        ip.greyscale();
        ip.invert();
        ip.noRed();
        ip.noBlue();
        ip.noGreen();
        ip.maximize();
//        if (args.length > 1) {
//            ip.overlay(new Pic(args[1]));
//        } 
    }
}