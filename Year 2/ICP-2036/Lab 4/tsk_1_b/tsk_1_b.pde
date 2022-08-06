void setup() {
 size(1050, 1050);
 background(255);
 stroke(195);
 int[][] myNewData = new int[50][50];
 myClearData(myNewData);
 myRect(myNewData, 30, 30, 20 , 20);
 myDraw(myNewData);
}

void myDraw(int[][] array){
  for (int i = 0; i < array.length; i++) {
    for (int j = 0; j < array.length; j++) {
      if (array[j][i] == 1) {
        fill(127);
      }
      else {
        fill(255); 
      }
      rect(10+20*i, 10+20*j, 20, 20);
    }
  }
}

int[][] myClearData(int[][] array) {
  for (int i = 0; i < array.length; i++) {
    for (int j = 0; j < array.length; j++){
      array[i][j] = 0;
    }
  }
  return array;
}

int[][] myRect(int[][] array, int xPos, int yPos, int wid, int hei) {
  for (int i = xPos; i < xPos + wid && i < array.length*array.length; i++) {
    for (int j = yPos; j < yPos + hei && j < array.length*array.length; j++) {
      if (j >= 0 && i >= 0) {
        array[j][i] = 1;
      }
    }
  }
  return array;
}
