%Edward Salt
%dns18cch
clear
close all
clc

%Q1.a
%size
k = 300;
%population
n = 30;
%max move distance
d = 20;
migration(k,n,d)
pause(.5)

%Q1.b
%infected
m = 5;
%max spread distance
l = 10;
infection(k,n,d,m,l)

%Q1.c
run1 = 565
run2 = 222
run3 = 606
run4 = 714
run5 = 249
runs = [run1,run2,run3,run4,run5]
mean = mean(runs)
median = median(runs)
minimum = min(runs)
maximum = max(runs)
range = range(runs)
pause(1)


%Q2
figure,
getMouseClick()
pause(.5)

%Q3
c = 25;
ps = 10;
t = randi([0,1],ps,c);
%figure,
f = fitness(t)
%genetics(t,ps)

%Q1
function infection(k,n,d,m,l)
    %random starting points
    x=(k/2)*rand(1,n).*(-1).^ceil(2*rand(1,n));
    y=(k/2)*rand(1,n).*(-1).^ceil(2*rand(1,n));
    %draw setup
    figure,
    axis off
    axis([-k k -k k])
    ax = gca;
    %virus setup
    v(3,n) = zeros();
    v(1,:) = x;
    v(2,:) = y;
    i = 0;
    %loops until 'm' have been infected
    while i < m
        j=randi(n);
        %if 'j' is already infected, pick another to infect
        if v(3,j)==0
            v(3,j)=1;
            i=i+1;
        end
    end
    %loops for 100 days
    day = 0;
    vcount = m;
    while vcount < n
        v = spread(v,k,n,d,ax,l);
        [v(1,:),v(2,:)] = movement(v(1,:),v(2,:),k,n,d,ax,v(3,:));
        day=day+1;
        vcount=0;
        i=1;
        while i <= n
            if v(3,i)==1
                vcount = vcount+1;
            end
            i=i+1;
        end
    end
    ax.Title.String = sprintf('Day: %d', day);
end

function v=spread(v,k,n,d,ax,l)
    i = 1;
    while i <= n
        if v(3,i)== 1
            j = 1;
            while j <= n
                if (v(3,j)==0)
                    v(3,j)=howClose(v(:,i),v(:,j),l);
                end
                j = j+1;
            end
        end
        i = i+1;
    end
end

function m=howClose(i,j,l)
    m=0;
    %if pdist([i(1),i(2);j(1),j(2)],'euclidean') < l
    if sqrt((i(1)-j(1))^2 + (i(2)-j(2))^2) < l
        m=1;
    end
end

function migration(k,n,d)
    %random starting points
    x=(k/2)*rand(1,n).*(-1).^ceil(2*rand(1,n));
    y=(k/2)*rand(1,n).*(-1).^ceil(2*rand(1,n));
    %draw setup
    figure,
    axis off
    axis([-k k -k k])
    ax = axes;
    %loops for 100 days
    day = 0;
    while day<100
        [x,y]=movement(x,y,k,n,d,ax,zeros(1,n));
        day=day+1
    end
end

function [x,y]=movement(x,y,k,n,d,ax,h)
    cla reset
    i = 1;
    hold on
    while i <= n
        if h(i)==0
            plot(x(i),y(i),'g.','MarkerSize',20)
        else
            plot(x(i),y(i),'r.','MarkerSize',20)
        end
        i=i+1;
    end
    hold off
    axis([-k k -k k])
    set(ax,'xtick',[], 'ytick', [])
    %algorithm for calculating movement
    x = getPoints(x,k,n,d);
    y = getPoints(y,k,n,d);
    pause(.01)
end

function [p]=getPoints(xy,k,n,d)
    p(n) = zeros;
    i = 1;
    %loops through x/y coord of all points
    while i<=n
        %uses the current xy position and adds
        %a random number between -20 and 20 ((0 and 40)-20)
        p(i)=xy(i)+(randi(d*2)-d);
        %if the new point is outside the axis,
        %then it will find a new random number for the point
        if (p(i)<k) && (p(i)>-k)
            i=i+1;
        end
    end
end

%Q2
function getMouseClick()
    %draws figure with black square
    %figure size
    k = 600;
    img(k,k) = zeros;
    handle = imshow(img);
    axis off
    axis([0 k 0 k])
    %listens for click on the square
    set(handle,'ButtonDownFcn',@ImageRecall);
end

function ImageRecall (image , eventData)
   %get current position on square where clicked
   axh  = get(image,'Parent');
   c = get(axh,'CurrentPoint'); 
   c = c(1,1:2);
   %plot randomized polygon
   ShapePlot(c)
   %reset listener
   set(image,'ButtonDownFcn',@ImageRecall);
end

function ShapePlot(c)
   %vertices
   v = 8;
   %holds x/y points 
   %holds one extra point to bring the line back to the start
   p(2,v+1) = zeros;
   %max distance from position clicked
   d = 100;
   i = 1;
   while i <= 2
       j = 1;
        while j <= v
            %get a random x/y value between 0 and 'd'
            %distance from cursor
            p(i,j)=c(i)+(randi(d*2)-d);
            %if it's outside the view, try again
            if (p(i,j)<600) && (p(i,j)>0)
                j=j+1;
            end
        end
        i=i+1;
   end
   %set the extra point to the first point
   p(:,v+1) = p(:,1);
   hold all
   %pick a random colour for the polygon
   colour = rand(1,3);
   %draw polygon from random points
   poly = patch(p(1,:), p(2,:),colour,'LineWidth',2.5,'EdgeColor','w');
   %listen for click on polygon
   set(poly,'ButtonDownFcn',@ShapeSelect);
   hold off
   %play a random sound (C5-C6) after polygon is drawn
   PlaySound
end

function ShapeSelect (poly, eventData)
   %delete the polygon clicked
   delete(poly);
end

function PlaySound
    %frequency sample
    fs = 8000;
    %duration (sec)
    T = 2;
    t = linspace(0,T,fs*T);
    %note frequency
    F = 0;
    switch randi(8)
        case 1
            F = 523.25;
        case 2
            F = 587.33;
        case 3
            F = 659.25;
        case 4
            F = 698.46;
        case 5
            F = 783.99;
        case 6
            F = 880.00;
        case 7
            F = 987.77;
        case 8
            F = 1046.50;
    end
    %amplitude
    A = (T - t)/T;
    y = A .* sin(F*2*pi*t);
    sound(y,fs)
end

%Q3
function genetics

end


function setGlobalx(val)
    global x
    x = val;
end

function r = getGlobalx
    global x
    r = x;
end

