package main.java;

import org.apache.commons.lang3.ArrayUtils;

public class TetrisCanvas extends Canvas {
    private Block block;

    public TetrisCanvas(int width, int height) {
        super(width, height);
    }

    public TetrisCanvas(Canvas cnv) {
        super(cnv);
    }

    public boolean isBlockActive() {
        return block != null;
    }

    public TetrisCanvas addBlock(Block block) throws Exception {
        if (isBlockActive())
            throw new Exception("addBlock. There shouldn't be a block on the canvas");

        TetrisCanvas tc = new TetrisCanvas(this);

        tc.block = block.setXY((getWidth() - block.getSize()) / 2,
                getHeight() - block.getSize());

        return tc;
    }

    private TetrisCanvas fixBlock() throws Exception {
        if (!isBlockActive())
            throw new Exception("fixBlock. There is no block on the canvas!");

        TetrisCanvas tc = null;

        int s = block.getSize();

        int[] xs = new int[0];
        int[] ys = new int[0];
        Colour[] cs = new Colour[0];

        int xBlock = block.getX();
        int yBlock = block.getY();
        Colour colorBlock = block.getColor();

        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                if (block.getPixel(i, j)) {
                    xs = ArrayUtils.add(xs, i + xBlock);
                    ys = ArrayUtils.add(ys, j + yBlock);
                    cs = ArrayUtils.add(cs, colorBlock);
                }
            }
        }

        try {
            tc = new TetrisCanvas(writePixels(cs, xs, ys));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("fixBlock. Index out of bound");
        } catch (Exception e) {
            System.out.println("fixBlock. Matrices not the same size");
        }

        return tc;

    }

    public TetrisCanvas rotateBlockCW() throws Exception {
        if (!isBlockActive())
            throw new Exception("rotateBlockCW. There is no active block in on the canvas yet.");

        Block b = block.rotateCW();

        if (!doesBlockConflict(b)) {
            TetrisCanvas tc = new TetrisCanvas(this);
            tc.block = b;

            return tc;
        }


        Block bLeft = b.move(Direction.LEFT);

        if (!doesBlockConflict(bLeft)) {
            TetrisCanvas tc = new TetrisCanvas(this);
            tc.block = bLeft;

            return tc;
        }


        Block bRight = b.move(Direction.RIGHT);

        if (!doesBlockConflict(bRight)) {
            TetrisCanvas tc = new TetrisCanvas(this);
            tc.block = bRight;

            return tc;
        }


        return this;

    }

    private boolean doesBlockConflict(Block b) {
        int x = b.getX();
        int y = b.getY();

        int size = b.getSize();

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (b.getPixel(i, j))
                    if (x + i < 0 || x + i >= getWidth() ||
                            y + j < 0 || y + j >= getHeight() ||
                            !super.readPixel(x + i, y + j).equals(Colour.WHITE))
                        return true;

        return false;
    }

    @Override
    public Colour readPixel(int x, int y) throws ArrayIndexOutOfBoundsException {
        if (x >= getWidth() || x < 0 || y >= getHeight() || y < 0)
            throw new ArrayIndexOutOfBoundsException();

        if (isBlockActive()) {

            int s = block.getSize();

            if (x >= block.getX() && x < block.getX() + s &&
                    y >= block.getY() && y < block.getY() + s)
                if (block.getPixel(x - block.getX(), y - block.getY()))
                    return block.getColor();
        }

        return super.readPixel(x, y);
    }

    private int[] getFullRowsYs() {

        int[] ys = new int[0];

        outer:
        for (int j = 0; j < getHeight(); j++) {
            for (int i = 0; i < getWidth(); i++)
                if (super.readPixel(i, j).equals(Colour.WHITE))
                    continue outer;

            ys = ArrayUtils.add(ys, j);
        }

        return ys;
    }


    public Pair<TetrisCanvas, Integer> clearFullRows() throws Exception {
        if (isBlockActive())
            throw new Exception("clearFullRows. There shouldn't be any active block.");

        int[] fullRowsYs = getFullRowsYs();

        if (fullRowsYs.length == 0)
            return new Pair<>(this, 0);


        Colour[] cs;
        int[] xs;
        int[] ys;

        Canvas cnv = this;


        for (int rowIndex = fullRowsYs.length - 1; rowIndex >= 0; rowIndex--) {
            xs = new int[0];
            ys = new int[0];
            cs = new Colour[0];

            for (int j = fullRowsYs[rowIndex] + 1; j < getHeight(); j++) {
                for (int i = 0; i < getWidth(); i++) {
                    xs = ArrayUtils.add(xs, i);
                    ys = ArrayUtils.add(ys, j - 1);
                    cs = ArrayUtils.add(cs, cnv.readPixel(i, j));
                }
            }

            for (int i = 0; i < getWidth(); i++) {
                xs = ArrayUtils.add(xs, i);
                ys = ArrayUtils.add(ys, getHeight() - 1);
                cs = ArrayUtils.add(cs, Colour.WHITE);
            }

            try {
                cnv = writePixels(cs, xs, ys);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("clearFullRows. Index out of bound");
            } catch (Exception e) {
                System.out.println("clearFullRows. Matrices not the same size");
            }
        }

        return new Pair<>(new TetrisCanvas(cnv), fullRowsYs.length);
    }


    public boolean isCanvasFull() throws Exception {
        if (isBlockActive())
            throw new Exception("isCanvasFull. There shouldn't be any active block.");


        boolean isFull = false;

        for (int i = getWidth() / 2 - 1; i < getWidth() / 2 + 1; i++)
            if (!super.readPixel(i, getHeight() - 1).equals(Colour.WHITE)) {
                isFull = true;
                break;
            }

        return isFull;
    }

    public TetrisCanvas dropBlock() throws Exception {
        if (!isBlockActive())
            throw new Exception("dropBlock. There isn't any active block to move.");
        TetrisCanvas tc = this;

        while (tc.isBlockActive())
            tc = tc.moveBlock(Direction.DOWN);

        return tc;
    }

    public TetrisCanvas moveBlock(Direction direction) throws Exception {
        if (!isBlockActive())
            throw new Exception("moveBlock. There isn't any active block to move.");


        TetrisCanvas tc;

        Block b = block.move(direction);

        if (doesBlockConflict(b)) {
            switch (direction) {
                case DOWN:
                    tc = fixBlock();
                    break;
                default:
                    tc = this;
            }
        } else {
            tc = new TetrisCanvas(this);
            tc.block = b;
        }

        return tc;

    }

}
