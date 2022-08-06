size(200, 200);
background(255);
stroke(255, 0, 0);
for (int i = 0; i < 10; i++) {
triangle(0, 200, 50, 80+(i*6), 100, 200); 
}
stroke(0, 255, 0);
for (int i = 0; i < 10; i++) {
triangle(200, 0, 150, 120-(i*6), 100, 0); 
}
stroke(0, 0, 255);
for (int i = 0; i < 10; i++) {
triangle(0, 0, 120-(i*6), 50, 0, 100); 
}
stroke(255, 0, 255);
for (int i = 0; i < 10; i++) {
triangle(200, 200, 80+(i*6), 150, 200, 100); 
}
