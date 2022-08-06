size(600, 600);
background(255);
stroke(255, 0, 0);
fill(255, 0, 0);

for (int i = 0; i < 44; i++) {
  ellipse(300 + cos(i) * 200, 300 + sin(i) * 200, 8, 8);
}
