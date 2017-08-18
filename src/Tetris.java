/*
 * Written by Mehrdad Baghery 1386/6/18
 */

import java.awt.event.*;
import java.awt.*;
import java.applet.*;
import java.util.*;

enum Colors {
   COLOR0(Color.WHITE), COLOR1(Color.GREEN), COLOR2(Color.RED), COLOR3(Color.BLUE)
   , COLOR4(Color.YELLOW), COLOR5(Color.PINK), COLOR6(Color.ORANGE), COLOR7(Color.CYAN);
   
   Color c;
   
   Colors(Color c) {
      this.c=c;
   }
   
   Color getColor() {
      return c;
   }
}

enum BlockType {
   O, L, J, I, A, S, Z;
}

enum Arrows {
   DOWN, LEFT, RIGHT;
}

/* These are the shapes and their acronyms
 * O: **
 *    **
 *
 * L: *
 *    *
 *    **
 *
 * J:  *
 *     *
 *    **
 *
 * I: *
 *    *
 *    *
 *    *
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

class Block{
   BlockType bType;
   private Colors[][] block;
   private int x, y;
   private int status=1;
   
   Block(BlockType bType) {
      this.bType = bType;
      
      switch(bType) {
         case O:
            block = new Colors[2][2];
            block[0][0] = Colors.COLOR1;
            block[0][1] = Colors.COLOR1;
            block[1][0] = Colors.COLOR1;
            block[1][1] = Colors.COLOR1;
            break;
         case L:
            block = new Colors[3][3];
            block[0][0] = Colors.COLOR0;
            block[0][1] = Colors.COLOR0;
            block[0][2] = Colors.COLOR0;
            block[1][0] = Colors.COLOR2;
            block[1][1] = Colors.COLOR2;
            block[1][2] = Colors.COLOR2;
            block[2][0] = Colors.COLOR0;
            block[2][1] = Colors.COLOR0;
            block[2][2] = Colors.COLOR2;
            break;
         case J:
            block = new Colors[3][3];
            block[0][0] = Colors.COLOR0;
            block[0][1] = Colors.COLOR0;
            block[0][2] = Colors.COLOR3;
            block[1][0] = Colors.COLOR3;
            block[1][1] = Colors.COLOR3;
            block[1][2] = Colors.COLOR3;
            block[2][0] = Colors.COLOR0;
            block[2][1] = Colors.COLOR0;
            block[2][2] = Colors.COLOR0;
            break;
         case I:
            block = new Colors[4][4];
            block[0][0] = Colors.COLOR0;
            block[0][1] = Colors.COLOR0;
            block[0][2] = Colors.COLOR0;
            block[0][3] = Colors.COLOR0;
            block[1][0] = Colors.COLOR4;
            block[1][1] = Colors.COLOR4;
            block[1][2] = Colors.COLOR4;
            block[1][3] = Colors.COLOR4;
            block[2][0] = Colors.COLOR0;
            block[2][1] = Colors.COLOR0;
            block[2][2] = Colors.COLOR0;
            block[2][3] = Colors.COLOR0;
            block[3][0] = Colors.COLOR0;
            block[3][1] = Colors.COLOR0;
            block[3][2] = Colors.COLOR0;
            block[3][3] = Colors.COLOR0;
            break;
         case A:
            block = new Colors[3][3];
            block[0][0] = Colors.COLOR0;
            block[0][1] = Colors.COLOR5;
            block[0][2] = Colors.COLOR0;
            block[1][0] = Colors.COLOR5;
            block[1][1] = Colors.COLOR5;
            block[1][2] = Colors.COLOR0;
            block[2][0] = Colors.COLOR0;
            block[2][1] = Colors.COLOR5;
            block[2][2] = Colors.COLOR0;
            break;
         case S:
            block = new Colors[3][3];
            block[0][0] = Colors.COLOR0;
            block[0][1] = Colors.COLOR6;
            block[0][2] = Colors.COLOR0;
            block[1][0] = Colors.COLOR6;
            block[1][1] = Colors.COLOR6;
            block[1][2] = Colors.COLOR0;
            block[2][0] = Colors.COLOR6;
            block[2][1] = Colors.COLOR0;
            block[2][2] = Colors.COLOR0;
            break;
         case Z:
            block = new Colors[3][3];
            block[0][0] = Colors.COLOR7;
            block[0][1] = Colors.COLOR0;
            block[0][2] = Colors.COLOR0;
            block[1][0] = Colors.COLOR7;
            block[1][1] = Colors.COLOR7;
            block[1][2] = Colors.COLOR0;
            block[2][0] = Colors.COLOR0;
            block[2][1] = Colors.COLOR7;
            block[2][2] = Colors.COLOR0;
            break;
      }
   }
   
   void rotate() {
      Colors[][] tempBlock;
      
      switch(bType) {
         case O:
            // Do nothing
            break;
         case L:
            switch(status) {
               case 1:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR2;
                  block[0][2] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR0;
                  block[1][1] = Colors.COLOR2;
                  block[1][2] = Colors.COLOR0;
                  block[2][0] = Colors.COLOR2;
                  block[2][1] = Colors.COLOR2;
                  block[2][2] = Colors.COLOR0;
                  status=2;
                  break;
               case 2:
                  block[0][0] = Colors.COLOR2;
                  block[0][1] = Colors.COLOR0;
                  block[0][2] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR2;
                  block[1][1] = Colors.COLOR2;
                  block[1][2] = Colors.COLOR2;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR0;
                  block[2][2] = Colors.COLOR0;
                  status=3;
                  break;
               case 3:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR2;
                  block[0][2] = Colors.COLOR2;
                  block[1][0] = Colors.COLOR0;
                  block[1][1] = Colors.COLOR2;
                  block[1][2] = Colors.COLOR0;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR2;
                  block[2][2] = Colors.COLOR0;
                  status=4;
                  break;
               case 4:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR0;
                  block[0][2] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR2;
                  block[1][1] = Colors.COLOR2;
                  block[1][2] = Colors.COLOR2;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR0;
                  block[2][2] = Colors.COLOR2;
                  status=1;
                  break;
            }
            break;
         case J:
            switch(status) {
               case 1:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR3;
                  block[0][2] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR0;
                  block[1][1] = Colors.COLOR3;
                  block[1][2] = Colors.COLOR0;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR3;
                  block[2][2] = Colors.COLOR3;
                  status=2;
                  break;
               case 2:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR0;
                  block[0][2] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR3;
                  block[1][1] = Colors.COLOR3;
                  block[1][2] = Colors.COLOR3;
                  block[2][0] = Colors.COLOR3;
                  block[2][1] = Colors.COLOR0;
                  block[2][2] = Colors.COLOR0;
                  status=3;
                  break;
               case 3:
                  block[0][0] = Colors.COLOR3;
                  block[0][1] = Colors.COLOR3;
                  block[0][2] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR0;
                  block[1][1] = Colors.COLOR3;
                  block[1][2] = Colors.COLOR0;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR3;
                  block[2][2] = Colors.COLOR0;
                  status=4;
                  break;
               case 4:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR0;
                  block[0][2] = Colors.COLOR3;
                  block[1][0] = Colors.COLOR3;
                  block[1][1] = Colors.COLOR3;
                  block[1][2] = Colors.COLOR3;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR0;
                  block[2][2] = Colors.COLOR0;
                  status=1;
                  break;
            }
            break;
         case I:
            switch(status) {
               case 1:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR4;
                  block[0][2] = Colors.COLOR0;
                  block[0][3] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR0;
                  block[1][1] = Colors.COLOR4;
                  block[1][2] = Colors.COLOR0;
                  block[1][3] = Colors.COLOR0;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR4;
                  block[2][2] = Colors.COLOR0;
                  block[2][3] = Colors.COLOR0;
                  block[3][0] = Colors.COLOR0;
                  block[3][1] = Colors.COLOR4;
                  block[3][2] = Colors.COLOR0;
                  block[3][3] = Colors.COLOR0;
                  status=2;
                  break;
               case 2:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR0;
                  block[0][2] = Colors.COLOR0;
                  block[0][3] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR4;
                  block[1][1] = Colors.COLOR4;
                  block[1][2] = Colors.COLOR4;
                  block[1][3] = Colors.COLOR4;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR0;
                  block[2][2] = Colors.COLOR0;
                  block[2][3] = Colors.COLOR0;
                  block[3][0] = Colors.COLOR0;
                  block[3][1] = Colors.COLOR0;
                  block[3][2] = Colors.COLOR0;
                  block[3][3] = Colors.COLOR0;
                  status=1;
                  break;
            }
            break;
         case A:
            switch(status) {
               case 1:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR5;
                  block[0][2] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR5;
                  block[1][1] = Colors.COLOR5;
                  block[1][2] = Colors.COLOR5;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR0;
                  block[2][2] = Colors.COLOR0;
                  status=2;
                  break;
               case 2:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR5;
                  block[0][2] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR0;
                  block[1][1] = Colors.COLOR5;
                  block[1][2] = Colors.COLOR5;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR5;
                  block[2][2] = Colors.COLOR0;
                  status=3;
                  break;
               case 3:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR0;
                  block[0][2] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR5;
                  block[1][1] = Colors.COLOR5;
                  block[1][2] = Colors.COLOR5;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR5;
                  block[2][2] = Colors.COLOR0;
                  status=4;
                  break;
               case 4:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR5;
                  block[0][2] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR5;
                  block[1][1] = Colors.COLOR5;
                  block[1][2] = Colors.COLOR0;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR5;
                  block[2][2] = Colors.COLOR0;
                  status=1;
                  break;
            }
            break;
         case S:
            switch(status) {
               case 1:
                  block[0][0] = Colors.COLOR6;
                  block[0][1] = Colors.COLOR6;
                  block[0][2] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR0;
                  block[1][1] = Colors.COLOR6;
                  block[1][2] = Colors.COLOR6;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR0;
                  block[2][2] = Colors.COLOR0;
                  status=2;
                  break;
               case 2:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR0;
                  block[0][2] = Colors.COLOR6;
                  block[1][0] = Colors.COLOR0;
                  block[1][1] = Colors.COLOR6;
                  block[1][2] = Colors.COLOR6;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR6;
                  block[2][2] = Colors.COLOR0;
                  status=3;
                  break;
               case 3:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR0;
                  block[0][2] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR6;
                  block[1][1] = Colors.COLOR6;
                  block[1][2] = Colors.COLOR0;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR6;
                  block[2][2] = Colors.COLOR6;
                  status=4;
                  break;
               case 4:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR6;
                  block[0][2] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR6;
                  block[1][1] = Colors.COLOR6;
                  block[1][2] = Colors.COLOR0;
                  block[2][0] = Colors.COLOR6;
                  block[2][1] = Colors.COLOR0;
                  block[2][2] = Colors.COLOR0;
                  status=1;
                  break;
            }
            break;
         case Z:
            switch(status) {
               case 1:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR7;
                  block[0][2] = Colors.COLOR7;
                  block[1][0] = Colors.COLOR7;
                  block[1][1] = Colors.COLOR7;
                  block[1][2] = Colors.COLOR0;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR0;
                  block[2][2] = Colors.COLOR0;
                  status=2;
                  break;
               case 2:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR7;
                  block[0][2] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR0;
                  block[1][1] = Colors.COLOR7;
                  block[1][2] = Colors.COLOR7;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR0;
                  block[2][2] = Colors.COLOR7;
                  status=3;
                  break;
               case 3:
                  block[0][0] = Colors.COLOR0;
                  block[0][1] = Colors.COLOR0;
                  block[0][2] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR0;
                  block[1][1] = Colors.COLOR7;
                  block[1][2] = Colors.COLOR7;
                  block[2][0] = Colors.COLOR7;
                  block[2][1] = Colors.COLOR7;
                  block[2][2] = Colors.COLOR0;
                  status=4;
                  break;
               case 4:
                  block[0][0] = Colors.COLOR7;
                  block[0][1] = Colors.COLOR0;
                  block[0][2] = Colors.COLOR0;
                  block[1][0] = Colors.COLOR7;
                  block[1][1] = Colors.COLOR7;
                  block[1][2] = Colors.COLOR0;
                  block[2][0] = Colors.COLOR0;
                  block[2][1] = Colors.COLOR7;
                  block[2][2] = Colors.COLOR0;
                  status=1;
                  break;
            }
            break;
      }
   }
   
   Colors[][] getBlock() {
      return block;
   }
   
   void setX(int x) {
      this.x = x;
   }
   
   void setY(int y) {
      this.y = y;
   }
   
   int getX() {
      return x;
   }
   
   int getY() {
      return y;
   }
}

class Field {
   private int width, height;
   private Colors[][] field;
   
   Field(int width, int height) {
      this.width = width;
      this.height = height;
      field = new Colors[width][height];
      for(int i=0; i<width; i++) {
         for(int j=0; j<height; j++) {
            field[i][j] = Colors.COLOR0;
         }
      }
   }
   
   boolean fillCell(Colors value, int x, int y) {
      if(x < width && y < height) {
         field[x][y] = value;
         return true;
      }else {
         return false;
      }
   }
   
   int getWidth() {
      return width;
   }
   
   int getHeight() {
      return height;
   }
   
   Colors[][] getField() {
      Colors[][] tempField = new Colors[width][height];
      for(int i=0; i<width; i++) {
         for(int j=0; j<height; j++) {
            tempField[i][j]=field[i][j];
         }
      }
      return tempField;
   }
}

class TetrisField extends Field {
   private Block block;
   
   TetrisField(int width, int height) {
      super(width, height);
   }
   
   boolean addBlock(Block block) {
      if(isBlockInside()) {
         return false;
      }else {
         block.setX(((int)getWidth()/2)-1);
         block.setY(0);
         this.block = block;
         return true;
      }
   }
   
   boolean moveBlock(Arrows direction) {
      if(!isBlockInside()) return false;
      
      Colors[][] tempField = super.getField();
      Colors[][] tempBlock = block.getBlock();
      boolean canMove = true;
      switch(direction) {
         case DOWN:
            block.setY(block.getY()+1);
            if(doesBlockConflict()) {
               block.setY(block.getY()-1);
               for(int i=0; i<tempBlock.length; i++) {
                  for(int j=0; j<tempBlock[i].length; j++) {
                     if(!tempBlock[i][j].equals(Colors.COLOR0)) {
                        fillCell(tempBlock[i][j], block.getX()+i, block.getY()+j);
                     }
                  }
               }
               block=null;
            }
            break;
         case LEFT:
            block.setX(block.getX()-1);
            if(doesBlockConflict()) block.setX(block.getX()+1);
            break;
         case RIGHT:
            block.setX(block.getX()+1);
            if(doesBlockConflict()) block.setX(block.getX()-1);
            break;
      }
      
      if(canMove)
         return true;
      else
         return false;
   }
   
   Colors[][] getField() {
      if(isBlockInside()) {
         Colors[][] tempField = super.getField();
         Colors[][] tempBlock = block.getBlock();
         for(int i=0; i<tempBlock.length; i++) {
            for(int j=0; j<tempBlock[i].length; j++) {
               if(!tempBlock[i][j].equals(Colors.COLOR0)) {
                  tempField[block.getX()+i][block.getY()+j]=tempBlock[i][j];
               }
            }
         }
         return tempField;
      }else {
         return super.getField();
      }
   }
   
   boolean rotateBlock() {
      if(!isBlockInside()) return false;
      
      block.rotate();
      if(doesBlockConflict()) {
         block.rotate();
         block.rotate();
         block.rotate();
         return false;
      }else {
         return true;
      }
   }
   
   private boolean doesBlockConflict() {
      Colors[][] tempBlock=block.getBlock();
      Colors[][] tempField=super.getField();
      int x=block.getX(), y=block.getY();
      
      for(int i=0; i<tempBlock.length; i++) {
         for(int j=0; j<tempBlock[i].length; j++) {
            if(!tempBlock[i][j].equals(Colors.COLOR0)) {
               if(x+i<0 || x+i>=getWidth() || y+j>=getHeight() || !tempField[x+i][y+j].equals(Colors.COLOR0)) return true;
            }
         }
      }
      return false;
   }
   
   boolean isGameOver() {
      boolean gameOver=false;
      
      for(int i=0; i<getWidth(); i++) {
         if(!super.getField()[i][0].equals(Colors.COLOR0)) gameOver=true;
      }
      
      if(gameOver==true && block==null) {
         return true;
      }else {
         return false;
      }
   }
   
   int clearFullRows() {
      int counter=0;
      
      while(getFullRow() != -1) {
         for(int i=getFullRow(); i>0; i--) {
            for(int j=0; j<getWidth(); j++) {
               fillCell(super.getField()[j][i-1], j, i);
            }
         }
         for(int i=0; i<getWidth(); i++) {
            fillCell(Colors.COLOR0, i, 0);
         }
         counter++;
      }
      
      return counter;
   }
   
   private int getFullRow() {
      boolean fullRow;
      
      for(int i=0; i<getHeight(); i++) {
         fullRow=true;
         for(int j=0; j<getWidth(); j++) {
            if(super.getField()[j][i].equals(Colors.COLOR0)) fullRow=false;
         }
         if(fullRow) return i;
      }
      
      return -1;
   }
   
   private boolean isBlockInside() {
      if(block==null) {
         return false;
      }else {
         return true;
      }
   }
}

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