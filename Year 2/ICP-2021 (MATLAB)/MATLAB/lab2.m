
%Q1.1
%pt.a
A = repmat(1:7,5)
%pt.b
B = reshape(1:18,[6,3])
%pt.c
C = reshape(1:30,[6,5])'
%pt.d
D = zeros(10,10)
D(2:9, 2:9) = 1
%pt.e
E = eye(10,10)
E = (E+flip(E-1))*-5

%Q1.2
%I predict it will result in 66. This is because it will count through
%3 complete columns of 20 (3x20=60)and then 6 extra rows to find the
%element (60+6=66)
A = zeros(20,5)
A(6,4) = 5
find(A)

%Q1.3
%pt.a
im = rgb2gray(imread('yellowCar.jpg'))
im(floor(size(im,1)/2):end, floor(size(im,2)/2):end) = 0
figure, imshow(im)
%pt.b
im = rgb2gray(imread('yellowCar.jpg'))
im(floor(size(im,1)/2):end, floor(size(im,2)/2):end) = ...
    255 - im(floor(size(im,1)/2):end, floor(size(im,2)/2):end)
figure, imshow(im)

%Q2.1
range = [20, 40]
M = randi(range,50,80)
figure, imagesc(M,range)
M(25 < M & M < 30) = -2
figure, imagesc(M,range)

%Q2.2
A = rgb2ind(imresize(imread('yellowCar.jpg'),0.2),4); figure
cm = [0 0 0;0.3 0.3 0.3;0.7 0.7 0.7;1 1 1;0 0 1;1 0 1;0 1 0];
imshow(A,cm,'initialmagnification','fit')
%pt.a
size(A)
%pt.b
A(A >= 3) = 4
figure, imshow(A,cm,'initialmagnification','fit')
