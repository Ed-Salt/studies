void setup() {
 size(500, 500);
 background(255);
 stroke(195);
 int[][] myData = new int[10][10];
 myClearData(myData);
 myRandomSet(myData, 105);
 myDraw(myData);
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

int[][] myRandomSet(int[][] array, int count) {
  for (int i = 0; i < count && i < array.length*array.length; i++) {
    int x = int(random(array.length));
    int y = int(random(array.length));
    if (array[x][y] == 0) {
      array[x][y] = 1;
    }
    else {
     i--; 
    }
  }
  return array;
}
