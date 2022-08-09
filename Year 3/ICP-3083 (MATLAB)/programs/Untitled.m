clear, clc, close all

r = linspace(-15,15,200);
[x1,x2] = meshgrid(r,r);

% g1(x) = 6
% g2(x) = x1 - 2x1 x2 - x1
% 3
% g3(x) = -2x1 + 9x2 - 11

g1 = ones(size(x1))*6;
g2 = x1 - 2*x1.*x2 - x1.^3;
g3 = -2*x1 + 9*x2 - 11;