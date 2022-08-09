clear
close all
clc

%Q1.a.i
range = [0, 255];
im = randi(range,100,50,3);
figure, imshow(uint8(im),'InitialMagnification','fit')

%Q1.a.ii
im = randi(range,100,50);
figure, imshow(uint8(im),'InitialMagnification','fit')

%Q1.b.i
im = randi(range,100,50);
%204 is almost 80% of 256
im(im <= 204) = 0;
im(im > 204) = 255;
figure, imshow(uint8(im),'InitialMagnification','fit')

%Q1.b.i
im = randi(range,100,50);
%76 is almost 30% of 256
im(im <= 76) = 0;
im(im > 76) = 255;
figure, imshow(uint8(im),'InitialMagnification','fit')

%Q1.c.i
im = rgb2gray(imread('owl.jpg'));
figure, imshow(im)

%Q1.c.ii
im(im < 35) = 255;
figure, imshow(im)

%Q1.d
im = imread('owl.jpg');
im(1:90,:,:) = [];
im(640:960,:,:) = [];
im(:,1:420,:) = [];
im(:,580:1260,:) = [];
figure, imshow(im)

%Q1.e
grayIm = rgb2gray(im);
bluIm = grayIm;
grayIm(grayIm < 35) = 0;
bluIm(bluIm < 35) = 255;
im = cat(3, grayIm, grayIm, bluIm);
figure, imshow(im)

%Q2.a
grayIm = rgb2gray(imread('owl.jpg'));
[row,col] = brightest_pixel(grayIm);

function [r,c] = brightest_pixel(grey_im)
    found = false
    check = 256
    while found == false
        check = check - 1
        if ismember(check, grey_im)
            [r,c] = find(grey_im == check);
            found = true
        end
    end
end

