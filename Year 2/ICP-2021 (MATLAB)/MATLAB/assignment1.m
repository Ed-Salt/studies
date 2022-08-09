%Edward Salt
%dns18cch
clear
close all
clc

%Q1.a
mtrx = [1 2 3 4;
     2 3 4 5;
     3 4 5 6;
     4 5 6 7;]
 %should be true
isTrue = checkSymmetric(mtrx)
mtrx = [1 2 6 4;
     2 3 4 5;
     3 3 5 2;
     4 5 6 7;]
 %should be false
isTrue = checkSymmetric(mtrx)
mtrx = [1 2 3 4;
     2 3 4 5;
     3 4 5 6;
     4 5 6 7;
     5 6 7 8;]
 %should error: not square
isTrue = checkSymmetric(mtrx)

%Q1.b
n = 50;
m = 2;
mtrxList = zeros(m, m, n);
range = [-3, 3];
count = 0;
disp("Of " + n + " matrices of size " + m + "x" + m + ",")
for k = 1 : n
    mtrxList(:,:,k) = randi(range,m,m);
    count = count + checkSymmetric(mtrxList(:,:,k));
end
disp(count + "/" + n + " (" + (count/n)*100 + "%) are symmetric")


%Q2.a
n = 0;
while ((n < 10 || n > 100) || floor(n) ~= n || mod(n,2) ~= 0)
    disp(' ')
    disp("Enter an even integer between 10 and 100:")
    n = input('> ');
end
%2 outer rows 1
mtrx = ones(n,n);
%create ascending mid section
i = n-2;
j = n-4;
mid = repmat(3:i,j,1);
%set mid 4 cells to 1
m = j/2;
mid(m:m+1,m:m+1) = 1;
%add mid section to the 1's matrix
mtrx(3:i,3:i) = mid;

%Q2.b
values = unique(mtrx);
gradient = numel(values)+1;
grn = linspace(0.4,1,gradient);
rb = linspace(0,1,gradient);

mycolors = [rb', grn', rb'];
figure, imshow(mtrx,mycolors,'initialmagnification','fit')


%Q3
figure,
hold on
x = 600;
y = 300;
%drawings base all measurements off x, y values
%drawing should scale with different x and y values
%(assuming aspect ratio stays the same)
%draws box, cliff & rope
drawBg(x, y)
%draws top person and text
drawPerson(x, y, 0)
%draws bottom person and text
drawPerson(x, y/2, 1)
%copyright
text((ceil(x*2/3)*0.85),(y*0.05),'(c) Ed Salt',...
            'FontSize', 8)
xlim([0 ceil(x*2/3)])
ylim([0 y])
axis off
hold off


%Q4.a
img = imread('mimikyu.jpg');
figure, imshow(img)
grim = rgb2gray(img);
grim(ceil(size(grim,1)/2):end, 1:end) =...
    imcomplement(grim(ceil(size(grim,1)/2):end, 1:end));
figure, imshow(grim)

%Q4.b
grim = rgb2gray(img);
grimTrim = grim(ceil(size(grim,1)/2):end, 1:end);
img(ceil(size(grim,1)/2):end, 1:end, :) =...
    cat(3, grimTrim, grimTrim, grimTrim);
figure, imshow(img)

function val = checkSymmetric(A)
    [n,m] = size(A);
    if (n == m)
        if (A == A.')
            val = 1;
        else
            val = 0;
        end
    else
        disp("Error: Matrix is not square.")
        val = 0;
    end
end

function drawBg(xMax, yMax)
    rectangle('Position', [0,0,ceil(xMax*2/3),yMax]);
    %draw cliff face
    xDist = ceil(xMax/4);
    yDist = yMax;
    range = [0,floor(xDist/50)];
    %randomly generate receding lines
    %until reach x or y axis at 0
    while xDist > 0 && yDist > 0
        %store previous point to start new line from
        yPrev = yDist;
        xPrev = xDist;
        %randomly generate new line
        %either straight down or down + left
        xDist = xDist - randi(range, 1, 1);
        yDist = yDist - ceil(yMax/60);
        plot([xPrev,xDist], [yPrev,yDist], 'k');
    end
    %draw rope
    xDist = ceil(xMax*0.45);
    yDist = yMax;
    xSize = floor(xMax/200);
    ySize = floor(yMax/60);
    while yDist > 0
        %draw rope "cross" pattern
        plot([xDist,xDist+xSize], [yDist,yDist-ySize], 'k');
        %draw each side of the rope
        plot([xDist,xDist], [yDist,yDist-ySize], 'k');
        plot([xDist+xSize,xDist+xSize], [yDist,yDist-ySize], 'k');
        yDist = yDist-ySize;
    end
end

function drawPerson(xMax, yMax, position)
    xPos = xMax/2;
    yPos = yMax*0.9;
    cSize = xPos/12;
    %head
    viscircles([xPos,yPos],cSize, 'Color',[0,0,0]);
    %body
    plot([xPos,xPos-cSize*1.5],[yPos-cSize,yPos-xPos*0.25],...
        'k', 'LineWidth', 2);
    %arm
    plot([xPos*0.96,xPos-cSize*1.1],[yPos-cSize*1.7,yPos-cSize*0.4],...
        'k', 'LineWidth', 2);
    %legs
    plot([xPos-cSize*1.5,xPos-cSize*2.5],...
        [yPos-xPos*0.25,yPos-cSize*2.5], 'k', 'LineWidth', 2);
    plot([xPos-cSize*1.5,xPos-cSize*2.4],...
        [yPos-xPos*0.25,yPos-cSize*3.6], 'k', 'LineWidth', 2);
    %unique face and text
    if position == 0
        plot(xPos+cSize*0.7, yPos-cSize*0.5, 'k.', 'MarkerSize', 16);
        plot([xPos+cSize*0.4,xPos], [yPos-cSize*0.9,yPos-cSize*0.2], 'k')
        text((xPos+cSize*0.8),(yPos-cSize),'How high are we?',...
            'FontSize', 8)
    else
        viscircles([xPos-cSize*0.4, yPos+cSize*0.5],cSize*0.2,...
            'Color',[0,0,0], 'LineWidth', 1);
        plot(xPos-cSize*0.4, yPos+cSize*0.55, 'k.', 'MarkerSize', 10);
        viscircles([xPos-cSize*0.7, yPos-cSize*0.4],cSize*0.16,...
            'Color',[0,0,0], 'LineWidth', 1);      
        text((xPos-cSize*4.5),(yPos),'Don''t look down!',...
            'FontSize', 8)
    end
end
