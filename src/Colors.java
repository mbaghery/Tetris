enum Colors {
   COLOR0(Color.WHITE),
   COLOR1(Color.GREEN),
   COLOR2(Color.RED),
   COLOR3(Color.BLUE),
   COLOR4(Color.YELLOW),
   COLOR5(Color.PINK),
   COLOR6(Color.ORANGE),
   COLOR7(Color.CYAN);
   
   Color c;
   
   Colors(Color c) {
      this.c=c;
   }
   
   Color getColor() {
      return c;
   }
}
