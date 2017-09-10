package main.java;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class TetrisEngine {
    private TetrisCanvas tc;
    private Block
            currentBlock,
            nextBlock;

    private Timer timer;

    private int
            score,
            level,
            lines;

    private int period;

    private int
            width,
            height;

    private int levelScore;

    private Painter painter;


    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getLines() {
        return lines;
    }

    public boolean isGameOver() {
        boolean output;

        try {
            output = tc.isCanvasFull();
        } catch (Exception e) {
            output = false;
        }

        return output;
    }

    TetrisEngine(Painter painter, Component component) {
        this.painter = painter;
        component.addKeyListener(new AWTKeyEventHandler());

        initialise();
    }

    private void initialise() {
        // read all the parameters from some file --- TODO
        score = 0;
        level = 0;
        lines = 0;

        period = 10000;

        width = 10;
        height = 20;

        levelScore = 500;



        sortScoring(0);


        currentBlock = new Block();
        nextBlock = new Block();

        tc = new TetrisCanvas(width, height);
        try {
            tc = tc.addBlock(currentBlock);
        } catch (Exception e) {

        }
    }


    private void sortScoring(int noLinesCleared) {
        int rowScore;

        lines += noLinesCleared;

        switch (noLinesCleared) {
            case 1:
                rowScore = 10;
                break;
            case 2:
                rowScore = 12;
                break;
            case 3:
                rowScore = 18;
                break;
            case 4:
                rowScore = 25;
                break;
            default:
                rowScore = 0;
        }


        score += noLinesCleared * rowScore * level;

        if (score >= (level * levelScore * level)) {
            level++;

            timer = new Timer();
            timer.schedule(
                    new TetrisTimerTask(),
                    10,
                    period / (level + 10));
        }
    }


    private class TetrisTimerTask extends TimerTask {
        public void run() {

            int noLinesCleared;

            if (tc.isBlockActive()) {
                try {
                    tc = tc.moveBlock(Direction.DOWN);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {

                Pair<TetrisCanvas, Integer> pair;

                try {
                    pair = tc.clearFullRows();
                    tc = pair._1();
                    noLinesCleared = pair._2();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    noLinesCleared = 0;
                }


                sortScoring(noLinesCleared);


                if (isGameOver()) {
                    this.cancel();
                } else {
                    currentBlock = nextBlock;
                    nextBlock = new Block();
                    try {
                        tc = tc.addBlock(currentBlock);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

            }

            painter.refresh(tc, nextBlock);

        }
    }


    private class AWTKeyEventHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent ke) {
            try {
                switch (ke.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        tc = tc.moveBlock(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        tc = tc.moveBlock(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_DOWN:
                        tc = tc.moveBlock(Direction.DOWN);
                        break;
                    case KeyEvent.VK_UP:
                        tc = tc.rotateBlockCW();
                        break;
                    case KeyEvent.VK_SPACE:
                        tc = tc.dropBlock();
                        break;

                }

                painter.refresh(tc, nextBlock);
            } catch (Exception e) {

            }
        }
    }

}
