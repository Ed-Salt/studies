clear, clc, close all

% Q2.c
a = k_means(2,[1;2;3;4;5;6;7;8;9;10])

function a = k_means(c,q)
    y = size(q,1);
    g = [2,6];%randperm(y,c);
    l = q(g,:);
    f = 1;
    while f
        t = l;
        for i = 1:y
            m = q(i,:);
            u = sum((l - m).^2,2);
            [~,a(i,1)] = min(u);
        end
        for i = 1:c
            l(i,:) = mean(q(a == i,:),1);
        end
        f = ~all(t(:) == l(:));
    end
end