int [][] tileA ={
  {0,0,0,0,0},
  {0,1,1,1,1},
  {0,1,1,1,1},
  {0,1,1,1,1},
  {0,1,1,1,1},
  };
int [][] tileB ={
  {1,1,1,1,1},
  {1,0,0,0,0},
  {1,0,0,0,0},
  {1,0,0,0,0},
  {1,0,0,0,0},
  };
boolean overBox = false; //updates when mouse hovering over specified locations
boolean leftClick = true;//updates whether click was right or left
boolean locked = true;   //updates when mouse is clicked over specified locations
int init = 16;          //initial size which code is built on (size of a single
                        //point in a tile)
int size = init*5;      //size of a tile (5x5 collection of points) 
int next = size+init*2; //used for giving accurate spacing between (ui) tiles
                        //based on the initial size
int order = 0;          //holds current value for order (decides how
                        //grid is drawn)

void setup() {
  size(1160, 840);
  background(255);
  PFont f = 
    createFont("Arial",init,true);
  textFont(f,init);
  fill(0);
  text("Recolour",size,size+init*3);
  text("Reorder",size*1.08,next*3+init);
}

void draw() {
  //detects mouse overing over a ui tile
  mouseOver();
  
  //draws ui tile a
  startPattern(tileA);
  pushMatrix();
  //draws ui tile b
  translate(next, 0);
  startPattern(tileB);
  popMatrix();
  
  //draws ui order tile (changes how drawn depending on order value)
  if (order % 2 == 0) {
    checkOrder(tileA, tileB);
  } else {
    checkOrder(tileB, tileA);
  }
  
  //draws the grid
  myDraw();
}

void mousePressed() {
  if (mouseButton == LEFT) {
    leftClick = true;
  } else {
    leftClick = false;
  }
  //if hovering over a box, register a mouse click
  if(overBox) { 
    locked = false; 
  } else {
    locked = true;
  }
}

void mouseOver() {
  if (mouseX >= init && mouseX < init+size && 
    mouseY >= init && mouseY < init+size) {
    //triggers when a point on tile A is clicked
    recolour(tileA, init, init);
  } else if (mouseX >= next+init && mouseX < next+init+size && 
    mouseY >= init && mouseY < init+size) {
    //triggers when a point on tile B is clicked
    recolour(tileB, next+init, init);
  } else if (mouseX >= next && mouseX < next+size && 
    mouseY >= next*2 && mouseY < next*2+size) {
    //triggers when the reorder tile is clicked
    reorder();
  } else {
    overBox = false;
  }
}

void checkOrder(int [][] myData, int [][] nextData) {
  //used to create the reorder tile in the ui portion of the program
  //uses the changes that will be applied to the grid
  pushMatrix();
  translate(init*3.5, next*2);
  if (order <= 1) {
    //normal pattern (for A/B)
    startPattern(myData);
  } else if (order <= 5) {
    //patterns for alternating A/B's
    scale(0.5);
    translate(init, init);
    myPattern(myData);
    translate(size, 0);
    myPattern(nextData);
    translate(-size, size);
    if (order <= 3) {
      //pattern for the parallel alternation
      myPattern(myData);
      translate(size, 0);
      myPattern(nextData);
    } else if (order <= 5) {
      //pattern for the alternating lead tile on each line
      myPattern(nextData);
      translate(size, 0);
      myPattern(myData);
    }
  } else if (order <= 9) {
    //patterns for the reversing A/B tiles
    scale(0.5);
    translate(init, init);
    myPattern(myData);
    scale(-1, 1);
    translate(-next-size, 0);
    myPattern(myData);
    translate(0, size);
    if (order <= 7) {
      //flipping only A or B's
      myPattern(myData);
      scale(-1, 1);
      translate(-next-size, 0);
      myPattern(myData);
    } else {
      //flipping alternating A and B's
      myPattern(nextData);
      scale(-1, 1);
      translate(-next-size, 0);
      myPattern(nextData);
    }
  }
  popMatrix();
}

void reorder() {
  overBox = true;
  //if there has been a mouse click over the tile
  if(!locked) {
    if (leftClick) {
      //change to the next order in the cycle
      //cycle resets after 10th pattern
      if (order < 9) {
        order += 1;
      } else {
        order = 0; 
      }
    } else {
      //change to the previous order in the cycle
      //cycle resets after first pattern      
      if (order > 0) {
        order -= 1;
      } else {
        order = 9; 
      }
    }
    //lock to prevent multiple runs,
    //unlocked when reclicked
    locked = true; 
  }
}

void recolour(int [][] myData, int minX, int minY) {
  overBox = true;  
  //if there has been a mouse click over the tile
  if(!locked) {
    //get x and y on cursor and calculate
    //which point on the tile was clicked
    int x = (int) (long) ((mouseX-minX)/init);
    int y = (int) (long) ((mouseY-minY)/init);
    if (leftClick) {
      //change to the next colour in the cycle
      //for the clicked point - cycle resets after 7th colour
      if (myData[y][x] < 6) {
        myData[y][x] += 1;
      } else {
        myData[y][x] = 0; 
      }
    } else { 
      //change to the previous colour in the cycle
      //for the clicked point - cycle resets after first colour
      if (myData[y][x] > 0) {
        myData[y][x] -= 1;
      } else {
        myData[y][x] = 6; 
      }
    }
    //lock to prevent multiple runs,
    //unlocked when reclicked
    locked = true;
  } 
}

void startPattern(int [][] myData) {
  //draws a smaller version of a single grid tile
  //with a blue square behind to highlight the tile
  stroke(0,0,255);
  fill(0,0,255);
  rect(init-1, init-1, size+2, size+2);
  stroke(195);
  myPattern(myData);
}

void myPattern(int [][] myData) {
  //draw tile from array
  for (int i = 0; i < myData.length; i++) {
    for (int j = 0; j < myData.length; j++) {
      //change colour depending on array value
      switch (myData[j][i]) {
        case 1:
          fill(127); break;
        case 2:
          fill(255,0,0); break;
        case 3:
          fill(255,255,0); break;
        case 4:
          fill(0,255,0); break;
        case 5:
          fill(0,0,255); break;
        case 6:
          fill(0); break;
        default:
          fill(255);
      }
      rect(init+(init*i), init+(init*j), init, init);
    }
  }
}

void checkReversed(int [][] myData, int [][] nextData, int num) {
  if (order >= num) {
    //draw reversed tile
    scale(-1, 1);
    translate(-next, 0);
    myPattern(myData);
  } else {
    //draw unaltered opposing tile
    myPattern(nextData);
  }
}

void myDraw() {
  //draw whole grid
  for (int i = 0; i < 10; i++) {
    for (int j = 0; j < 10; j++) {
      pushMatrix();
      translate(240+size*j, size*i);
      if (order <= 9) {
        //change lead tile depending on odd/even order value
        if (order % 2 == 0) {
          myPattern(tileA);
        } else {
          myPattern(tileB); 
        } 
        if (order >= 2) {
          //if order requires a different second tile, 
          //this performs an extra loop cycle
          popMatrix();
          j++;
          pushMatrix();
          translate(240+size*j, size*i);
          //draw tiles flipped in y-axis
          if (order % 2 == 0) {
            checkReversed(tileA, tileB, 6);
          } else {
            checkReversed(tileB, tileA, 7);
          }
        }
      }
      popMatrix();
      //alters order value for specific orders
      //allows the program to perform more intricate patterns
      //with less code to run/check through in the loop
      if (order == 8) {
        order = 9;
      } else if (order == 9) {
        order = 8;
      }
    }
    //''
    if (order == 4) {
      order = 5;
    } else if (order == 5) {
      order = 4;
    }
  }
}
