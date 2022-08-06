void setup(){
  size(600, 600);
  background(255);
  rectMode(CENTER);
  translate(300, 300);
  myPattern();
}

void myPattern() {
  fill(0, 0, 200, 25);
  tint(127);
  int x = 300;
  int y = 300;
  for (int i = 0; i < 20; i++) {
    int size = 500 - i*20;
    rect(0, 0, size, size);
    rotate(-0.02*i);
    //translate(size/2, size/2);
    //rotate(radians(1));
  }
}
