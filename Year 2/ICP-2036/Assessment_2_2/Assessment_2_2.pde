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
boolean overBox = false;
boolean locked = true;
int init = 16;
int size = init*5;
int next = size+init*2;
int x = 0;
int y = 0;
int order = 0;
int rotation = 3;

void setup() {
  size(1160, 840);
  background(255);
  PFont f = 
    createFont("Arial",init,true);
  textFont(f,init);
  fill(0);
  text("Recolour",size,size+init*3);
  text("Reorder",size*1.08,next*3+init);
  text("Rotate",size*1.14,next*5+init);
}

void draw() {
  mouseOver();
  
  startPattern(tileA);
  pushMatrix();
  translate(next, 0);
  startPattern(tileB);
  popMatrix();
  
  if (order % 2 == 0) {
    checkOrder(tileA, tileB, false);
    checkOrientation(tileA, tileB);
  } else {
    checkOrder(tileB, tileA, false);
    checkOrientation(tileB, tileA);
  }
  
  //myDraw();
}

void mousePressed() {
  if(overBox) { 
    locked = false; 
  } else {
    locked = true;
  }
}

void mouseOver() {
  if (mouseX >= init && mouseX < init+size && 
    mouseY >= init && mouseY < init+size) {
    recolour(tileA, init, init);
  } else if (mouseX >= next+init && mouseX < next+init+size && 
    mouseY >= init && mouseY < init+size) {
    recolour(tileB, next+init, init);
  } else if (mouseX >= next && mouseX < next+size && 
    mouseY >= next*2 && mouseY < next*2+size) {
    reorder();
  } else if (mouseX >= next && mouseX < next+size && 
    mouseY >= next*4 && mouseY < next*4+size) {
    println(mouseX + ", " + mouseY);
    reorientate();
  } else {
    overBox = false;
  }
}

void checkOrientation(int [][] myData, int [][]nextData) {
  pushMatrix();
  //translate(0, next*2);
  switch (rotation) {
    case 1:
      translate((next*3+init*3.5), -(next*3+init*3.5));
      rotate(radians(90)); break;
    case 2:
      translate(-next*1.5, next*5.5);
      rotate(radians(180)); break;
    case 3:
      rotate(radians(270));
      translate(0, next*12); break;
    default:
      translate(0, next*2);
  }
  checkOrder(myData, nextData, true);
}

void reorientate() {
  overBox = true;
  if(!locked) { 
    if (rotation < 3) {
      rotation += 1;
    } else {
      rotation = 0; 
    }
    locked = true;
    println(rotation);
  }
}

void checkOrder(int [][] myData, int [][] nextData, boolean followUp) {
  if (!followUp) {
    pushMatrix();
  }
  translate(init*3.5, next*2);
  if (order <= 1) {
    startPattern(myData);
  } else if (order <= 5) {
    scale(0.5);
    translate(init, init);
    myPattern(myData);
    translate(size, 0);
    myPattern(nextData);
    translate(-size, size);
    if (order <= 3) {
      myPattern(myData);
      translate(size, 0);
      myPattern(nextData);
    } else if (order <= 5) {
      myPattern(nextData);
      translate(size, 0);
      myPattern(myData);
    }
  } else if (order <= 9) {
    scale(0.5);
    translate(init, init);
    myPattern(myData);
    scale(-1, 1);
    translate(-next-size, 0);
    myPattern(myData);
    translate(0, size);
    if (order <= 7) {
      myPattern(myData);
      scale(-1, 1);
      translate(-next-size, 0);
      myPattern(myData);
    } else {
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
  if(!locked) { 
    if (order < 9) {
      order += 1;
    } else {
      order = 0; 
    }
    locked = true;
  }
}

void recolour(int [][] myData, int minX, int minY) {
  overBox = true;  
  if(!locked) { 
    x = (int) (long) ((mouseX-minX)/init);
    y = (int) (long) ((mouseY-minY)/init);
    if (myData[y][x] < 6) {
      myData[y][x] += 1;
    } else {
      myData[y][x] = 0; 
    }
    locked = true;
  } 
}

void startPattern(int [][] myData) {
  stroke(0,0,255);
  fill(0,0,255);
  rect(init-1, init-1, size+2, size+2);
  stroke(195);
  myPattern(myData);
}

void myPattern(int [][] myData) {
  for (int i = 0; i < myData.length; i++) {
    for (int j = 0; j < myData.length; j++) {
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

void myDraw() {
  for (int i = 0; i < 10; i++) {
    for (int j = 0; j < 10; j++) {
      pushMatrix();
      translate(240+size*j, size*i);
      if (order <= 9) {
        if (order % 2 == 0) {
          myPattern(tileA);
        } else {
          myPattern(tileB); 
        } 
        if (order >= 2) {
          popMatrix();
          j++;
          pushMatrix();
          translate(240+size*j, size*i);
          if (order % 2 == 0) {
            if (order >= 6) {
              scale(-1, 1);
              translate(-next, 0);
              myPattern(tileA);
            } else {
              myPattern(tileB);
            }
          } else {
            if (order >= 7) {
              scale(-1, 1);
              translate(-next, 0);
              myPattern(tileB);
            } else {
              myPattern(tileA);
            }
          }
        }
      }
      popMatrix();
      if (order == 8) {
        order = 9;
      } else if (order == 9) {
        order = 8;
      }
    }
    if (order == 4) {
      order = 5;
    } else if (order == 5) {
      order = 4;
    }
  }
}
