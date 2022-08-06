clear, clc, close all

r = linspace(-15,15,200);
[x1,x2] = meshgrid(r,r);

g1 = ones(size(x1))*6;
g2 = x1 - 2*x1.*x2 - x1.^3;
g3 = -2*x1 + 9*x2 - 11;

[~,Labels] = max([g1(:) g2(:) g3(:)], [], 2);

figure, hold on, axis equal
plot(x1(Labels == 1),x2(Labels == 1),'k.')
plot(x1(Labels == 2),x2(Labels == 2),'g.')
plot(x1(Labels == 3),x2(Labels == 3),'r.')

axis tight

%Q1.b
m1 = [0,0]; m2 = [5,-4];

class1 = randn(100,2);
class2 = [randn(100,1)*0.3 + m2(1), randn(100,1)*2 + m2(2)];
figure, hold on, grid on, axis equal
plot(class1(:,1),class1(:,2),'g.','markersize',15)
plot(class2(:,1),class2(:,2),'k.','markersize',15)

A = [0,-9]; B = [5,3];
plot([A(1),B(1)],[A(2),B(2)],'k.-')

% (x - x1) / (x2 - x1) = (y - y1) / (y2 - y1)
%(x - A(1)) / (B(1) - A(1)) = (y - A(2)) / (B(2) - A(2))

[x,y] = meshgrid(-6:0.05:8,-9:0.05:3);

% discriminant function
G = (x - A(1)) / (B(1) - A(1)) - (y - A(2)) / (B(2) - A(2));
figure, hold on, axis equal
plot(x(G>0),y(G>0),'k.')
plot(x(G<=0),y(G<=0),'g.')





