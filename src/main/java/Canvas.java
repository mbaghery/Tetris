/* Canvas is immutable. */
public class Canvas {
    /*
     * x and y run from 0 to (width-1) and (height-1) respectively
     */

    private int width, height;
    private Colors[] pixels;

    Canvas(int width, int height) {
        this.width = width;
        this.height = height;

        pixels = new Colors[noPixels()];

        for (int i = 0; i < pixels.length; i++)
            pixels[i] = Colors.WHITE;
    }

    Canvas(Canvas cnv) {
        this.width = cnv.width;
        this.height = cnv.height;

        pixels = new Colors[noPixels()];

        System.arraycopy(cnv.pixels, 0, this.pixels, 0, noPixels());
    }

    Colors readPixel(int x, int y) throws ArrayIndexOutOfBoundsException {
        if (x >= getWidth() || x < 0 || y >= getHeight() || y < 0)
            throw new ArrayIndexOutOfBoundsException();

        return pixels[x * getHeight() + y];
    }

    Canvas writePixels(Colors[] cs, int[] xs, int[] ys) throws Exception {
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

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    int noPixels() {
        return width * height;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++)
                sb.append(readPixel(i, j).toString().charAt(0));

            sb.append('\n');
        }

        return sb.toString();
    }

}
