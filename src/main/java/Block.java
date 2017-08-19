package main.java;

/* These are the shapes and their acronyms
 * O: **
 *    **
 *
 * I: *
 *    *
 *    *
 *    *
 *
 * L: *
 *    *
 *    **
 *
 * J:  *
 *     *
 *    **
 *
 * A:  *
 *    ***
 *
 * S:  **
 *    **
 *
 * Z: **
 *     **
 */

/* main.java.Block is immutable. */
class Block {
    private BlockType bType;
    private boolean[][] pixels;
    private Colors color;
    private int x, y;

    Block(BlockType bType) {
        this.bType = bType;
        x = 0;
        y = 0;

        int size = getSize();

        pixels = new boolean[size][size];
        clearMe();

        switch (bType) {
            case O:
                pixels[0][0] = true;
                pixels[0][1] = true;
                pixels[1][0] = true;
                pixels[1][1] = true;
                color = Colors.GREEN;
                break;
            case I:
                pixels[0][2] = true;
                pixels[1][2] = true;
                pixels[2][2] = true;
                pixels[3][2] = true;
                color = Colors.YELLOW;
                break;
            case L:
                pixels[2][0] = true;
                pixels[0][1] = true;
                pixels[1][1] = true;
                pixels[2][1] = true;
                color = Colors.RED;
                break;
            case J:
                pixels[0][2] = true;
                pixels[0][1] = true;
                pixels[1][1] = true;
                pixels[2][1] = true;
                color = Colors.BLUE;
                break;
            case A:
                pixels[1][2] = true;
                pixels[0][1] = true;
                pixels[1][1] = true;
                pixels[2][1] = true;
                color = Colors.PINK;
                break;
            case S:
                pixels[0][1] = true;
                pixels[1][1] = true;
                pixels[1][2] = true;
                pixels[2][2] = true;
                color = Colors.ORANGE;
                break;
            case Z:
                pixels[0][2] = true;
                pixels[1][2] = true;
                pixels[1][1] = true;
                pixels[2][0] = true;
                color = Colors.CYAN;
                break;
        }
    }

    Block(Block b) {
        this.bType = b.bType;
        this.x = b.x;
        this.y = b.y;

        int size = getSize();

        pixels = new boolean[size][size];

        for (int i = 0; i < pixels.length; i++)
            System.arraycopy(b.pixels[i], 0,
                    pixels[i], 0, pixels[i].length);

    }

    private void clearMe() {
        for (boolean[] row : pixels) {
            for (boolean pixel : row) {
                pixel = false;
            }
        }
    }

    Block rotateCW() {
        Block b = new Block(this);
        MatrixStuff.rotateCW(b.pixels);
        return b;
    }

    boolean getPixel(int x, int y) throws ArrayIndexOutOfBoundsException {
        int s = getSize();

        if (x >= s || x < 0 || y >= s || y < 0)
            throw new ArrayIndexOutOfBoundsException();

        return pixels[x][y];
    }

    Block setXY(int x, int y) {
        Block b = new Block(this);
        b.x = x;
        b.y = y;
        return b;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getSize() {
        switch (bType) {
            case O:
                return 2;
            case I:
                return 4;
            default:
                return 3;
        }
    }

    Colors getColor() {
        return color;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (boolean[] row : pixels) {
            for (boolean pixel : row)
                if (pixel) sb.append('*');
                else sb.append(' ');

            sb.append('\n');
        }

        return sb.toString();
    }
}
