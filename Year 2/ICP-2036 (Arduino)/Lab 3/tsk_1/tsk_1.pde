void setup() {
  size(400, 400);
  background(255);
}

void draw() {
  for (int i = 1; i <= 10; i++) {
    fill(255, 0, 0);
    for (int j = 1; j <= 10; j++) {
       rect(25*i, 25*j, 20, 20); 
    }
    i++;
    fill(0, 255, 0);
    for (int j = 1; j <= 10; j++) {
       rect(25*i, 25*j, 20, 20); 
    }
  }
}
