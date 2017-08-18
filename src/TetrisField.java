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
