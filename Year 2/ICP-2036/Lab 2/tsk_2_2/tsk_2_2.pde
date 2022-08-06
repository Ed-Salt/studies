size(600, 600);
background(255);

float x = 0;
float y = 0;
float xPrev = 300 + cos(radians(90)) * (200);
float yPrev = 300 + sin(radians(90)) * (200);

fill(255, 0, 0);
for (int i = 0; i < 36; i++) {
x = 300 + cos(radians(i*10+90)) * (200-i);
y = 300 + sin(radians(i*10+90)) * (200-i);
line(x, y, xPrev, yPrev);
ellipse(x, y, 8, 8);
xPrev = x;
yPrev = y;
}

fill(0, 255, 0);
for (int i = 0; i < 36; i++) {
x = 300 + cos(radians(i*10+90)) * (200-(i+36));
y = 300 + sin(radians(i*10+90)) * (200-(i+36));
line(x, y, xPrev, yPrev);
ellipse(x, y, 8, 8);
xPrev = x;
yPrev = y;
}

fill(0, 0, 255);
for (int i = 0; i < 36; i++) {
x = 300 + cos(radians(i*10+90)) * (200-(i+72));
y = 300 + sin(radians(i*10+90)) * (200-(i+72));
line(x, y, xPrev, yPrev);
ellipse(x, y, 8, 8);
xPrev = x;
yPrev = y;
}
