size(501, 501);
background(255);
stroke(0, 0, 127);
fill(127);

for (int i = 0; i < 51; i++) {
strokeWeight(3);
line(0, i*10, i*10, 500);
line(i*10, 0, 500, i*10);
rect(i*10, i*10, 10, 10);

i++;
strokeWeight(1);
line(0, i*10, i*10, 500);
line(i*10, 0, 500, i*10);
rect(i*10, i*10, 10, 10);
}
