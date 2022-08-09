void setup() {
 size(1050, 1050);
 background(255);
 stroke(195);
 int[][] myNewData = new int[50][50];
 myClearData(myNewData);
 myCircle(myNewData, 20, 20, 10);
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

int[][] myCircle(int[][] array, int xPos, int yPos, float rad) {
  for (int i = 0; i < array.length; i++) {
        array[(int)(yPos+sin(i)*rad)][(int)(xPos+cos(i)*rad)] = 1;
      }
  return array;
}
