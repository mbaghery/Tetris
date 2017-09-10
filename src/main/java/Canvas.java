package main.java;

/* main.java.Canvas is immutable. */
public class Canvas {
    /*
     * x and y run from 0 to (width-1) and (height-1) respectively
     */

    private int width, height;
    private Colour[] pixels;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;

        pixels = new Colour[noPixels()];

        for (int i = 0; i < pixels.length; i++)
            pixels[i] = Colour.WHITE;
    }

    public Canvas(Canvas cnv) {
        this.width = cnv.width;
        this.height = cnv.height;

        pixels = new Colour[noPixels()];

        System.arraycopy(cnv.pixels, 0, this.pixels, 0, noPixels());
    }

    public Colour readPixel(int x, int y) throws ArrayIndexOutOfBoundsException {
        if (x >= getWidth() || x < 0 || y >= getHeight() || y < 0)
            throw new ArrayIndexOutOfBoundsException();

        return pixels[x * getHeight() + y];
    }

    public Canvas writePixels(Colour[] cs, int[] xs, int[] ys) throws Exception {
        if (cs.length != xs.length || xs.length != ys.length)
            throw new Exception("Vectors pixels, xs and ys should be the same size.");

        for (int i = 0; i < xs.length; i++)
            if (xs[i] >= getWidth() || ys[i] >= getHeight())
                throw new ArrayIndexOutOfBoundsException();


        Canvas newCanvas = new Canvas(this);

        for (int i = 0; i < xs.length; i++)
            newCanvas.pixels[xs[i] * getHeight() + ys[i]] = cs[i];

        return newCanvas;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int noPixels() {
        return width * height;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int j = getHeight() - 1; j >= 0; j--) {
            for (int i = 0; i < getWidth(); i++)
                sb.append(readPixel(i, j).toString().charAt(0));

            sb.append('\n');

        }

        return sb.toString();
    }

}
