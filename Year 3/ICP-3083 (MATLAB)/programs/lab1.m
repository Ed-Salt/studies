% clear, clc, close all
% 
% %Q1.a
% a = randn(1000,3);
% plot3(a(:,1),a(:,2),a(:,3),'.k')
% grid on
% axis equal
% 
% %Q1.b
% x2 = rand(2000,1)-1;
% y2 = rand(2000,1)+3;
% z2 = rand(2000,1)-3;
% 
% Data = [a;[x2 y2 z2]];
% Labels = [ones(1000,1);ones(2000,1)*2];
% 
% plot(Labels)
% 
% figure, hold on
% plot3(Data,'k.')
% 
% %/
% hold off
% 
% 
% %Q1.c
% figure, hold on, axis equal, grid on
% 
% [Data, Labels] = deal([]);
% 
% for i = 1:8
%     x = randn(200,1)*rand*3 + rand*40 - 20;
%     % x is squashed or expanded
%     y = randn(200,1)*rand*3 + rand*40 - 20;
%     
%     plot(x,y,'k.','markersize',15,'color',rand(1,3))
% end


%Q2

b = load('iris.txt');
data = b(:,1:end-1);
labels = b(:,end);

plot_index = 1;
for i = 1:4 %row
    for j = 1:4 %column
        subplot(4,4,plot_index)
        plot_index = plot_index + 1;
        hold on, grid on
        plot(data(labels == 1, i),data(labels == 1,j),'k.',...
            'markersize',10)
        plot(data(labels == 2, i),data(labels == 2,j),'r.',...
            'markersize',10)
        plot(data(labels == 3, i),data(labels == 3,j),'b.',...
            'markersize',10)
    end
end
