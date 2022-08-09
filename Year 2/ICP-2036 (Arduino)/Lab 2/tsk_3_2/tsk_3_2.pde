void setup(){
  size(600, 600);
  background(255);
  rectMode(CENTER);
  
  pushMatrix();
  translate(50, 50);
  scale(0.05);
  myPattern();
  popMatrix();
  
  pushMatrix();
  translate(350, 400);
  scale(0.75);
  myPattern();
  popMatrix();
  
  pushMatrix();
  translate(200, 120);
  scale(0.4);
  myPattern();
  popMatrix();
}

void myPattern() {
  fill(0, 0, 200, 25);
  tint(127);
  for (int i = 0; i < 20; i++) {
    int size = 500 - i*20;
    rect(0, 0, size, size);
    rotate(-0.02*i);
  }
}
