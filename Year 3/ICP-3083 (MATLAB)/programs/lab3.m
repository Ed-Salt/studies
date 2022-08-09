clear, clc, close all

tl = [1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 3 3 3 3 3 3 3 4 4 4];
al = [1 1 1 1 1 1 3 3 4 4 2 2 2 2 1 3 3 3 3 3 2 1 4 4 4];

confusion_matrix(tl,al)

%Q2.a
data = [randn(100,2);randn(100,2)+1];
labels = [ones(100,1);ones(100,1)*2];
figure
scatter(data(:,1),data(:,2),15,labels,'filled')

%Q2.b
rp = randperm((2*Ncl),:);
training_data = data(rp(1:Ncl),:);
training_labels = labels(rp(1:Ncl));
testing_data = data(rp(Ncl+1:end),:);
testing_labels = labels(rp(Ncl+1:end));

%Q2.c
% assume the no. of objects in the data set divides by 5
N_in_fold =  2*Ncl / 5; % no. in each fold
index

%Q1.a
function cm = confusion_matrix(true_labels,assigned_labels)
c = max(true_labels); % no. of classes
cm = zeros(c);
for i = 1:c
    for j = 1:c
        cm(i,j) = sum(true_labels == i & assigned_labels ==j);
    end
end
end
