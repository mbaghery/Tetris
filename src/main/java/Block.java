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

import java.util.Random;

/* main.java.Block is immutable. */
public class Block {
    private BlockType bType;
    private boolean[][] pixels;
    private Colour color;
    private int x, y;

    private Random random;

    {
        random = new Random(System.currentTimeMillis());
    }


    public Block() {
        this.bType = BlockType.values()[random.nextInt(7)];
        initialise();
    }

    public Block(BlockType bType) {
        this.bType = bType;
        initialise();
    }


    private void initialise() {
        x = 0;
        y = 0;

        int size = getSize();


        pixels = new boolean[size][size];

        for (boolean[] row : pixels)
            for (boolean pixel : row)
                pixel = false;


        switch (bType) {
            case O:
                pixels[0][0] = true;
                pixels[0][1] = true;
                pixels[1][0] = true;
                pixels[1][1] = true;
                color = Colour.GREEN;
                break;
            case I:
                pixels[0][2] = true;
                pixels[1][2] = true;
                pixels[2][2] = true;
                pixels[3][2] = true;
                color = Colour.YELLOW;
                break;
            case L:
                pixels[2][2] = true;
                pixels[0][1] = true;
                pixels[1][1] = true;
                pixels[2][1] = true;
                color = Colour.RED;
                break;
            case J:
                pixels[0][2] = true;
                pixels[0][1] = true;
                pixels[1][1] = true;
                pixels[2][1] = true;
                color = Colour.BLUE;
                break;
            case A:
                pixels[1][2] = true;
                pixels[0][1] = true;
                pixels[1][1] = true;
                pixels[2][1] = true;
                color = Colour.PINK;
                break;
            case S:
                pixels[0][1] = true;
                pixels[1][1] = true;
                pixels[1][2] = true;
                pixels[2][2] = true;
                color = Colour.ORANGE;
                break;
            case Z:
                pixels[0][2] = true;
                pixels[1][2] = true;
                pixels[1][1] = true;
                pixels[2][1] = true;
                color = Colour.CYAN;
                break;
        }
    }

    public Block(Block b) {
        this.bType = b.bType;
        this.x = b.x;
        this.y = b.y;
        this.color = b.color;

        int size = getSize();

        pixels = new boolean[size][size];

        for (int i = 0; i < pixels.length; i++)
            System.arraycopy(b.pixels[i], 0,
                    pixels[i], 0, pixels[i].length);

    }


    public Block rotateCW() {
        Block b = new Block(this);
        MatrixStuff.rotateCW(b.pixels);
        return b;
    }

    public Block move(Direction direction) {
        Block b = new Block(this);

        switch (direction) {
            case DOWN:
                b = this.setXY(this.getX(), this.getY() - 1);
                break;
            case LEFT:
                b = this.setXY(this.getX() - 1, this.getY());
                break;
            case RIGHT:
                b = this.setXY(this.getX() + 1, this.getY());
                break;
        }

        return b;
    }

    public boolean getPixel(int x, int y) throws ArrayIndexOutOfBoundsException {
        int s = getSize();

        if (x >= s || x < 0 || y >= s || y < 0)
            throw new ArrayIndexOutOfBoundsException();

        return pixels[x][y];
    }

    public Block setXY(int x, int y) {
        Block b = new Block(this);
        b.x = x;
        b.y = y;
        return b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        switch (bType) {
            case O:
                return 2;
            case I:
                return 4;
            default:
                return 3;
        }
    }

    public Colour getColor() {
        return color;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();


        for (int j = getSize() - 1; j >= 0; j--) {
            for (int i = 0; i < getSize(); i++) {
                if (getPixel(i, j))
                    sb.append('*');
                else
                    sb.append('-');
            }

            sb.append('\n');
        }

        sb.append("x: " + x + ", y: " + y);

        return sb.toString();
    }
}
