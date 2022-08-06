close, clc, close all

load wine
[N,n] = size(data);
c = max(labels);
fprintf('Number of objects N = %i\n', N)
fprintf('Number of features n = %i\n', n)
fprintf('Number of classes c = %i\n', c)

SELECTED = []; 
F = zeros(1,n); % criterion function
% (the smaller, the better)
for i = 1:n
    F(i) = kfoldLoss(crossval(fitcknn(data(:,i),labels)));
end
[~,bestf] = min(F);
SELECTED = [SELECTED, bestf];

F = zeros(1,n); % criterion function
% (the smaller, the better)
for i = 1:n
    F(i) = kfoldLoss(crossval(fitcknn(...
        data(:,[SELECTED, i]),labels)));
end
F(SELECTED) = 2;
[~,bestf] = min(F);
SELECTED = [SELECTED, bestf];

