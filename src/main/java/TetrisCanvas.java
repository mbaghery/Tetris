import org.apache.commons.lang3.ArrayUtils;

class TetrisCanvas extends Canvas {
    private Block block;

    TetrisCanvas(int width, int height) {
        super(width, height);
    }

//    TetrisCanvas(TetrisCanvas ts) {
//        super(ts);
//
//        block = new Block(ts.block);
//    }

    TetrisCanvas(Canvas cnv) {
        super(cnv);
    }

    boolean isThereAnActiveBlock() {
        return block != null;
    }

    TetrisCanvas addBlock(Block block) throws Exception {
        if (isThereAnActiveBlock())
            throw new Exception("There is a block on the canvas already");

        TetrisCanvas ts = new TetrisCanvas(this);
        ts.block = block.setXY(((getWidth() - block.getSize()) / 2) - 1, 0);

        return ts;
    }

    TetrisCanvas fixBlock() throws Exception {
        if (!isThereAnActiveBlock())
            throw new Exception("There is NO block on the canvas!");

        TetrisCanvas ts = null;

        int s = block.getSize();

        int[] xs = null;
        int[] ys = null;
        Colors[] cs = null;

        int xBlock = block.getX();
        int yBlock = block.getY();
        Colors colorBlock = block.getColor();

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
            ts = new TetrisCanvas(writePixels(cs, xs, ys));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not good. Index out of bound");
        } catch (Exception e) {
            System.out.println("Not good. Matrices not the same size");
        }

        return ts;

    }

    TetrisCanvas rotateBlock() throws Exception {
        if (!isThereAnActiveBlock())
            throw new Exception("There is no active block in on the canvas yet.");

        Block b = block.rotateCW();

        if (!doesBlockConflict(b)) {
            TetrisCanvas ts = new TetrisCanvas(this);
            ts.block = b;

            return ts;
        } else {
            return this;
        }
    }

    private boolean doesBlockConflict(Block b) {
        int x = block.getX(), y = block.getY();

        int size = b.getSize();

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (b.getPixel(i, j))
                    if (x + i < 0 || x + i >= getWidth() ||
                            y + j < 0 || y + j >= getHeight() ||
                            !super.readPixel(x + i, y + j).equals(Colors.WHITE))
                        return true;

        return false;
    }

    @Override
    Colors readPixel(int x, int y) throws ArrayIndexOutOfBoundsException {
        if (x >= getWidth() || x < 0 || y >= getHeight() || y < 0)
            throw new ArrayIndexOutOfBoundsException();

        if (isThereAnActiveBlock()) {

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
            inner:
            for (int i = 0; i < getWidth(); i++)
                if (super.readPixel(i, j).equals(Colors.WHITE))
                    continue outer;

            ArrayUtils.add(ys, j);
        }

        return ys;
    }


    Pair<TetrisCanvas, Integer> clearFullRows() throws UnsupportedOperationException {
        if (isThereAnActiveBlock())
            throw new UnsupportedOperationException("There shouldn't be any active block.");

        int[] fullRowsYs = getFullRowsYs();

        if (fullRowsYs.length == 0)
            return new Pair<>(this, 0);

        Colors[] cs;
        int[] xs;
        int[] ys;

        Canvas cnv = this;


        for (int rowIndex = fullRowsYs.length; rowIndex >= 0; rowIndex--) {
            cs = null;
            xs = null;
            ys = null;

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
                cs = ArrayUtils.add(cs, Colors.WHITE);
            }

            try {
                cnv = writePixels(cs, xs, ys);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Not good. Index out of bound");
            } catch (Exception e) {
                System.out.println("Not good. Matrices not the same size");
            }
        }

        return new Pair<>(new TetrisCanvas(cnv), fullRowsYs.length);
    }


    boolean isCanvasFull() throws UnsupportedOperationException {
        if (isThereAnActiveBlock())
            throw new UnsupportedOperationException("There shouldn't be any active block.");


        boolean isFull = false;

        for (int i = getWidth() / 2 - 1; i < getWidth() / 2 + 1; i++)
            if (!super.readPixel(i, getHeight()).equals(Colors.WHITE)) {
                isFull = true;
                break;
            }

        return isFull;
    }


    TetrisCanvas moveBlock(Arrows direction) throws UnsupportedOperationException {
        if (!isThereAnActiveBlock())
            throw new UnsupportedOperationException("There isn't any active block to move.");

        Block b = null;

        switch (direction) {
            case DOWN:
                b = block.setXY(block.getX(), block.getY() + 1);
                if (doesBlockConflict(b)) {
                    TetrisCanvas ts = null;
                    try {
                        ts = fixBlock();
                    } catch (Exception e) {
                        System.out.println("Something went wrong.");
                    }
                    return ts;
                }
//                block.setY(block.getY() - 1);
//                for (int i = 0; i < tempBlock.length; i++) {
//                    for (int j = 0; j < tempBlock[i].length; j++) {
//                        if (!tempBlock[i][j].equals(Colors.WHITE)) {
//                            writePixels(tempBlock[i][j], block.getX() + i, block.getY() + j);
//                        }
//                    }
//                }
//                block = null;
            case LEFT:
                b = block.setXY(block.getX() - 1, block.getY());
                if (doesBlockConflict(b))
                    return this;
            case RIGHT:
                b = block.setXY(block.getX() + 1, block.getY());
                if (doesBlockConflict(b))
                    return this;
        }


        TetrisCanvas ts = new TetrisCanvas(this);
        ts.block = b;

        return ts;

    }

}
