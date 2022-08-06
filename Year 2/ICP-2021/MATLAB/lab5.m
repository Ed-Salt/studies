clear
close all
clc

%Q.1.a
x = linspace(0,2,100);
y = sin((x.^3)-2);
%plot line
figure, plot(x,y,'k-')
hold on
rannum = randperm(100,10);
%plot points
plot(x(rannum),y(rannum),'ro')

xlabel('x')
ylabel('y = sin(x^3 - 2)')
title('Random sample of size 10')
hold off

%Q.1.b.i
x = rand(200,1)*200-100;
y = rand(200,1)*200-100; 
figure, plot(x,y,'k.')

hold on
xlabel('x')
ylabel('y')
axis square
hold off

%Q.1.b.ii
xmean = mean(x);
ymean = mean(y);
figure, plot(xmean,ymean,'xr','LineWidth',3,'MarkerSize',16)

hold on
plot(x,y,'k.')
xlabel('x')
ylabel('y')
axis square
hold off

%Q.1.b.iii
figure, 
hold on
arrayfun(@(i,j) plot([i,xmean], [j,ymean],'g'),x,y)

plot(x,y,'k.')
plot(xmean,ymean,'xr','LineWidth',3,'MarkerSize',16)
xlabel('x')
ylabel('y')
axis square
hold off

%Q.1.b.iv
figure, 
hold on
arrayfun(@(i,j) howClose(i,j,xmean,ymean),x,y)

plot(x,y,'k.')
plot(xmean,ymean,'xr','LineWidth',3,'MarkerSize',16)
xlabel('x')
ylabel('y')
axis square
hold off

function howClose(i,j,n,m) 
    if pdist([i,j;n,m],'euclidean') < 50
        plot([i,n], [j,m],'b')
    end
end

