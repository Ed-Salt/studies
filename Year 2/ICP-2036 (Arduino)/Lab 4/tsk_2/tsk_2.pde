void setup() {
 size(1050, 1050);
 background(255);
 stroke(195);
 int[][] myNewData = new int[50][50];
 myClearData(myNewData);
 myLine(myNewData, 0, 0, 50 , 10);
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

int[][] myLine(int[][] array, int x1, int y1, int x2, int y2) {
  double xn = x2 - x1;
  double yn = y2 - y1;
  double m = yn/xn;
  double c = y1 - m * x1;
  for (int i = x1; i < x2; ++i) {
    double j = m * i + c;
    if (i < array.length && (int) Math.round(j) < array.length) {
      array[(int) Math.round(j)][i] = 1;
    }
  }
  
  return array;
}
