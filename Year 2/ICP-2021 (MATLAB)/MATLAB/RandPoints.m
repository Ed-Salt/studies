function RandPoints
    clc
    clear 
    close all
    
    %size (k*k)
    k = 150;
    %population
    n = 30;
    %max distance moved
    d = 20;
    %random starting points
    x=(k/2)*rand(1,n).*(-1).^ceil(2*rand(1,n));
    y=(k/2)*rand(1,n).*(-1).^ceil(2*rand(1,n));
    
    figure,
    axis off
    axis([-k k -k k])
    ax = axes;
    %loops for 100 days
    day = 0;
    while day<100
        plot(x,y,'k.','MarkerSize',20)
        axis([-k k -k k])
        set(ax,'xtick',[], 'ytick', [])
        %algorithm for calculating movemetn
        x = getPoints(n,k,d,x);
        y = getPoints(n,k,d,y);
        pause(.1)
    
        day=day+1
    end
end

function [xy]=getPoints(n,k,d,p)
    xy(n) = zeros;
    i = 1;
    %loops through p(x/y) coord of all points
    while i<=n
        %uses the current position (p) and adds
        %a random number between -20 and 20 ((0 and 40)-20)
        xy(i)=p(i)+(randi(d*2)-d);
        %if the new point is outside the axis,
        %then it will find a new random number for the point
        if (xy(i)<k) && (xy(i)>-k)
            i=i+1;
        end
    end
end