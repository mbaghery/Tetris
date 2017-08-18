/*
 * Written by Mehrdad Baghery
   First release on: 9.10.2007
 */

import java.awt.event.*;
import java.awt.*;
import java.applet.*;
import java.util.*;


public class Tetris extends Frame {
   final int cellDim = 26;
   final int cellSpace = 1;
   final int xOffset = 4;
   final int yOffset = 30;
   final int fontSize = 12;
   final int levelScore = 1500;
   final int radius = 10;
   final Color backColor = Color.GRAY;
   final Color backGroundColor = Color.BLACK;
   
   TetrisField myField = new TetrisField(11, 20);
   Block myBlock, nextBlock;;
   TetrisTimerTask ttt = new TetrisTimerTask();
   Timer t=new Timer();
   int score=0, level=1, lines=0;
   
   Tetris() {
      requestFocus();
      addKeyListener(new keyEventHandler());
      addWindowListener(new windowHandler());
      t.schedule(ttt, 1000, 1000);
      myBlock = new Block(RanBlockType());
      myField.addBlock(myBlock);
      nextBlock = new Block(RanBlockType());
      this.setSize(453, 601);
   }
   
   public void paint(Graphics g) {
      cleanScreen(g);
      writeTexts(g);
      paintField(myField, g);
      paintBlock(nextBlock, g);
      if(myField.isGameOver()) paintGameOver(g);
   }
   
   public static void main(String[] args) {
      Tetris myTetris=new Tetris();
      myTetris.setVisible(true);
   }
   
   BlockType RanBlockType() {
      int ranNum=(int)(Math.random()*7);
      switch(ranNum) {
         case 0:
            return BlockType.I;
         case 1:
            return BlockType.A;
         case 2:
            return BlockType.J;
         case 3:
            return BlockType.L;
         case 4:
            return BlockType.S;
         case 5:
            return BlockType.O;
         case 6:
            return BlockType.Z;
         default:
            return BlockType.O;
      }
   }
   
   private void paintBlock(Block b, Graphics g) {
      Colors[][] tempBlock=b.getBlock();
      for(int i=0; i<tempBlock.length; i++) {
         for(int j=0; j<tempBlock[i].length; j++) {
            if(!tempBlock[i][j].equals(Colors.COLOR0)) paintBlockCell(tempBlock[i][j], i, j, g);
         }
      }
   }
   
   private void cleanScreen(Graphics g) {
      g.setColor(backColor);
      g.fillRect(xOffset,yOffset,(cellDim+cellSpace)*(myField.getWidth()+1)*4/3+cellDim/2
                                ,(cellDim+cellSpace)*(myField.getHeight()+1));
      
      g.setColor(backGroundColor);
      g.fillRect(xOffset+cellDim/2,yOffset+cellDim/2,cellSpace+(cellDim+cellSpace)*myField.getWidth()   // The main area
                                                    ,cellSpace+(cellDim+cellSpace)*myField.getHeight());
      g.fillRect(xOffset+(cellDim+cellSpace)*(myField.getWidth()+1),yOffset+cellDim/2   // The next block area
                 ,(cellDim+cellSpace)*(myField.getWidth()+1)/3,(cellDim+cellSpace)*(myField.getHeight()+1)/4);
   }
   
   private void writeTexts(Graphics g) {
      g.setFont(new Font("Arial", Font.BOLD, fontSize));
      g.drawString("Lines: "+lines,xOffset+(cellDim+cellSpace)*(myField.getWidth()+1),yOffset+cellDim*3/2+(cellDim+cellSpace)*10);
      g.drawString("Score: "+score,xOffset+(cellDim+cellSpace)*(myField.getWidth()+1),yOffset+cellDim*3/2+(cellDim+cellSpace)*10+fontSize*2);
      g.drawString("Level: "+level,xOffset+(cellDim+cellSpace)*(myField.getWidth()+1),yOffset+cellDim*3/2+(cellDim+cellSpace)*10+fontSize*4);
   }
   
   private void paintField(Field f, Graphics g) {
      Colors[][] tempField = f.getField();

      for(int i=0; i<tempField.length; i++) {
         for(int j=0; j<tempField[i].length; j++) {
            if(!tempField[i][j].equals(Colors.COLOR0)) paintFieldCell(tempField[i][j], i, j, g);
         }
      }
   }
   
   private void paintBlockCell(Colors value, int x, int y, Graphics g) {
      Color c=value.getColor();
      
      g.setColor(c);
      g.fillRoundRect(xOffset+(cellDim+cellSpace)*(myField.getWidth()+1+x)+cellDim/3, yOffset+cellDim*3/2+(cellDim+cellSpace)*y, cellDim, cellDim, radius, radius);
   }
   
   private void paintFieldCell(Colors value, int x, int y, Graphics g) {
      Color c = value.getColor();
      
      g.setColor(c);
      g.fillRoundRect(xOffset+cellDim/2+cellSpace+(cellDim+cellSpace)*x, yOffset+cellDim/2+cellSpace+(cellDim+cellSpace)*y, cellDim, cellDim, radius, radius);
   }
   
   private void paintGameOver(Graphics g) {
      g.setFont(new Font("Arial", Font.BOLD, fontSize*3));
      g.drawString("Game Over", xOffset+cellDim/2+(cellDim+cellSpace)*2, yOffset+cellDim/2+(cellDim+cellSpace)*7);
   }
   
   class TetrisTimerTask extends TimerTask{
      public void run() {
         int rowScore=0;
         int temp=0;
         if(!myField.moveBlock(Arrows.DOWN)) {
            temp=myField.clearFullRows();  // !!! Scoring !!!
            lines+=temp;
            switch(temp) {
               case 1:
                  rowScore=40;
                  break;
               case 2:
                  rowScore=50;
                  break;
               case 3:
                  rowScore=100;
                  break;
               case 4:
                  rowScore=300;
                  break;
            }
            score+=temp*rowScore*level;
            if(score>(level*levelScore*level)) {
               level++;
               t=new Timer();
               ttt=new TetrisTimerTask();
               t.schedule(ttt, 0, 10000/(level+10));
            }
            if(myField.isGameOver()) {
               this.cancel();
            }else {
               myBlock=nextBlock;
               nextBlock = new Block(RanBlockType());
               myField.addBlock(myBlock);
            }
         }
         repaint();
      }
   }
   
   class keyEventHandler extends KeyAdapter {
      public void keyPressed(KeyEvent ke) {
         switch(ke.getKeyCode()) {
            case KeyEvent.VK_LEFT:
               myField.moveBlock(Arrows.LEFT);
               break;
            case KeyEvent.VK_RIGHT:
               myField.moveBlock(Arrows.RIGHT);
               break;
            case KeyEvent.VK_DOWN:
               myField.moveBlock(Arrows.DOWN);
               repaint();
               myField.moveBlock(Arrows.DOWN);
               break;
            case KeyEvent.VK_UP:
               myField.rotateBlock();
               break;
            case KeyEvent.VK_SPACE:
               while(myField.moveBlock(Arrows.DOWN));
               break;
            case KeyEvent.VK_F2:
               myField = new TetrisField(11, 20);
               score=0;
               level=1;
               lines=0;
               t=new Timer();
               ttt=new TetrisTimerTask();
               t.schedule(ttt, 0, 10000/(level+10));
               break;
         }
         repaint();
      }
   }
   
   class windowHandler extends WindowAdapter {
      public void windowClosing(WindowEvent we) {
         System.exit(0);
      }
   }
}
