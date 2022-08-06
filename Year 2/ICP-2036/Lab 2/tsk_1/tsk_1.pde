void setup(){
size(600, 600);
background(255);
 
}

void draw() {
float c1 = random(255);
float c2 = random(255);
float c3 = random(255);
float x1 = random(600);
float y1 = random(600);
float x2 = random(600);
float y2 = random(600);

stroke(c1, c2, c3);
line(x1, y1, x2, y2);
}
