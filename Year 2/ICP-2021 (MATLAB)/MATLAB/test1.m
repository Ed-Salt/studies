clear
close all
clc

% A = [1 2 3 4;5 6 7 8;9 10 11 12];
% figure
% imagesc(A)
% axis equal off


%Question 1
sin(10)^2+cos(10)^2
sin(4096.5)^2+cos(4096.5)^2
sin(6)^2+cos(6)^2

%Question 2
Z1 = zeros(5, 5)
Z2 = ones(5, 10)
Z3 = [0:14;0:14;0:14]
Z = [Z1 Z2;Z3]
figure
imagesc(Z)
axis equal off

%Question 3
a = [ -2:6 ]
b = [ 12:-1:3 ]
c = [ 0:0.1:0.5 ]
d = [ 0.4:-0.6:-4 ]

%Question 4
A = [4:-1:2 ; 5:7 ; 8:10]
B = [9:-1:7 ; 1:3 ; 6:-1:4]
A*B
B*A
A = [9:-1:7 ; 1:3 ; 6:-1:4 ; 5:7 ; 8:10 ; 4:-1:2]
B = [5:9 ; 12:-1:8 ; 2:6]
C = [4:-1:1 ; 4:7 ; 8:11 ; 5:8 ; 2:5]
size(A)
size(B)
size(C)
A*B

%Question 5
im = rgb2gray(imread('peppers.png'));
size(im)
%remove around onion
im(163:477,:) = []
im(1:110,:) = []
im(:, 350:686) = []
im(:, 1:275) = []
imshow(im)
%add frame
k = 30
z = zeros(k+52+k, k+74+k)
z(k:52+k-1, k:74+k-1) = im;
%imshow(z)
imshow(uint8(z))

