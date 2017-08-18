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
