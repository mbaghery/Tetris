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
