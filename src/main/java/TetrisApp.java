package main.java;

/*
 * Written by Mehrdad Baghery
   First release on: 9.10.2007
 */

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Font;


public class TetrisApp extends Frame implements Painter {
    private final static int
            cellSize = 26,
            cellGap = 1,
            xOffset = 20,
            yOffset = 43,
            fontSize = 12,
            radius = 10;

    private final static Color canvasBackground = Color.BLACK;

    private TetrisEngine te;
    private TetrisCanvas tc;
    private Block nextBlock;


    public static void main(String[] args) {
        TetrisApp tetrisApp = new TetrisApp();

        tetrisApp.setSize(
                2 * (xOffset + cellGap) + (cellSize + cellGap) * 16,
                2 * (xOffset + cellGap) + (cellSize + cellGap) * 21);
        tetrisApp.setVisible(true);
    }

    public TetrisApp() {
        super.requestFocus();

        te = new TetrisEngine(this, this);

        super.addWindowListener(new windowHandler());
    }

    public void refresh(TetrisCanvas tc, Block nextBlock) {
        this.tc = tc;
        this.nextBlock = nextBlock;

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        clearScreen(g);

        g.fillRect(xOffset, yOffset, 10, 10);

        writeTexts(g);
        drawCanvas(g);
        drawNextBlock(g);

        if (te.isGameOver())
            writeGameOver(g);
    }

    private void clearScreen(Graphics g) {
        g.setColor(canvasBackground);
        // the main canvas
        g.fillRect(
                xOffset,
                yOffset,
                cellGap + (cellSize + cellGap) * tc.getWidth(),
                cellGap + (cellSize + cellGap) * tc.getHeight());
        // the next block
        g.fillRect(
                xOffset + (cellSize + cellGap) * (tc.getWidth() + 1),
                yOffset,
                (cellSize + cellGap) * 5,
                (cellSize + cellGap) * 5);
    }

    private void writeTexts(Graphics g) {
        g.setColor(canvasBackground);
        g.setFont(new Font("Arial", Font.BOLD, fontSize));
        g.drawString(
                "Lines: " + te.getLines(),
                xOffset + (cellSize + cellGap) * (tc.getWidth() + 1),
                yOffset + cellSize + (cellSize + cellGap) * 10);
        g.drawString(
                "Score: " + te.getScore(),
                xOffset + (cellSize + cellGap) * (tc.getWidth() + 1),
                yOffset + cellSize + (cellSize + cellGap) * 10 + fontSize * 2);
        g.drawString(
                "Level: " + te.getLevel(),
                xOffset + (cellSize + cellGap) * (tc.getWidth() + 1),
                yOffset + cellSize + (cellSize + cellGap) * 10 + fontSize * 4);
    }

    private void writeGameOver(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, fontSize * 3));
        g.drawString(
                "Game Over",
                xOffset + (cellSize + cellGap) * 2,
                yOffset + (cellSize + cellGap) * 7);
    }

    private void drawCanvas(Graphics g) {
        Colour c;

        for (int i = 0; i < tc.getWidth(); i++) {
            for (int j = 0; j < tc.getHeight(); j++) {
                c = tc.readPixel(i, j);

                if (!c.equals(Colour.WHITE))
                    drawCanvasPixel(c, i, tc.getHeight() - 1 - j, g);
            }
        }
    }

    private void drawCanvasPixel(Colour c, int x, int y, Graphics g) {
        g.setColor(c.getColor());
        g.fillRoundRect(
                xOffset + cellGap + (cellSize + cellGap) * x,
                yOffset + cellGap + (cellSize + cellGap) * y,
                cellSize,
                cellSize,
                radius,
                radius);
    }

    private void drawNextBlock(Graphics g) {
        Colour c = nextBlock.getColor();

        for (int i = 0; i < nextBlock.getSize(); i++)
            for (int j = 0; j < nextBlock.getSize(); j++)
                if (nextBlock.getPixel(i, j))
                    drawNextBlockPixel(c, i, nextBlock.getSize() - 1 - j, g);

    }

    private void drawNextBlockPixel(Colour c, int x, int y, Graphics g) {
        g.setColor(c.getColor());
        g.fillRoundRect(
                (int) (xOffset + cellGap + (cellSize + cellGap) *
                        (tc.getWidth() + 1 + x + 2.5 - nextBlock.getSize() / 2.0)),
                (int) (yOffset + cellGap + (cellSize + cellGap) *
                        (y + 2.5 - nextBlock.getSize() / 2.0)),
                cellSize,
                cellSize,
                radius,
                radius);
    }


    private class windowHandler extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent we) {
            System.exit(0);
        }
    }
}
