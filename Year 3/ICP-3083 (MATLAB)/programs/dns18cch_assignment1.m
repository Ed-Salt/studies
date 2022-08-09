clear, clc, close all

% Q1.a
data=[0,1;1,2;3,0;-1,-2;2,0;1,-2;-2,-2]; % [x,y;...]
labels=[1;1;1;1;2;2;2];
M=3;
[data1, labels1, data2, labels2] = data_split2(data,labels,M)
size(data,1)

% Q1.b
data=[0,1;1,2;3,0;-1,-2;2,0;1,-2;-2,-2];
labels=[1;1;1;1;2;2;2];
testData=[0,1;1,2;3,0;-1,-2;-1,-2;1,-2;-2,-2];
testLabels=[1;1;1;1;1;2;2];
[e,assigned_labels] = ...
    nearest_mean_classifier(data, labels, testData, testLabels)

% Q1.c
% mean of each class from data1 and then data2??
inp = readmatrix('data_banknote_authentication.txt');
bankData = inp(:,1:4);
bankLabels = inp(:,5) + 1;
M=677;
[data1, labels1, data2, labels2] = data_split2(bankData,bankLabels,M)
bankTestData = bankData;
bankTestLabels = bankLabels;
[e,assigned_labels] = nearest_mean_classifier(bankData, bankLabels,...
    bankTestData, bankTestLabels)


% Q2.a
data=[0,1;1,2;3,0;-1,-2;2,0;1,-2;-2,-2];
labels=[1;1;1;1;2;2;2];
testData=[1,1;2,2;3,1;-2,0;3,-2;2,-2;-3,-2];
testLabels=[1;1;1;1;2;2;2];
[e,assigned_labels] = one_nn(data, labels, testData, testLabels)

% Q2.b
inp = readmatrix('data_banknote_authentication.txt');
bankData = inp(:,1:4);
bankLabels = inp(:,5) + 1;
bankTestData = bankData;
bankTestLabels = bankLabels;
assigned_labels = zeros(size(bankData,1),size(bankData,2))
for y = 1:size(bankData,2)
    [e,assigned_labels(:,y)] = one_nn(bankData(:,y), bankLabels,...
        bankTestData(:,y), bankTestLabels)
end
figure
scatter(bankData(:,2),bankData(:,4),15,bankLabels,'filled')
title('Bank Authentication data')
xlabel('Feature # 2')
ylabel('Feature # 4')
grid on

% 1.a
function [data1, labels1, data2, labels2] = data_split2(data, labels, M)
    max=numel(labels);
    % if M exceeds max, or if labels and data are different sizes
    % then error
    if (M <= max)
        % n has the random positions
        n=randperm(max,M);
        % N has remaing positions
        % calculate remaing by removing n from N
        N=1:max; N=setdiff(N,n);
        % converts the data and labels arrays to single dimensional arrays
        
        % gets randomly selected data
        for i = 1:M
            x=n(i);
            data1(i,:) = data(x,:);
            labels1(i) = labels(x);
        end
        % gets remaining data
        for i = 1:max-M
            x=N(i);
            data2(i,:) = data(x,:);
            labels2(i) = labels(x);
        end
    else
        error('Variable M exceeds the number of objects in the data.');
    end    
end

% 1.b
function [e,assigned_labels] = nearest_mean_classifier(training_data,...
    training_labels, testing_data, testing_labels)
    maxClasses = size(training_data,2);
    maxSize = size(training_data,1);
    classValues = unique(training_labels); % unique class values
    maxStates = size(classValues,1);
    m = zeros(maxClasses);
    assigned_labels = zeros(maxSize,1);
    %finding mean points
    for i = 1:maxStates
        classes = zeros(sum(training_labels(:)==classValues(i)),...
            maxClasses);
        count = 1;
        for j = 1:maxSize
            if training_labels(j) == classValues(i)
                classes(count,:) = training_data(j,:);
                count = count+1;
            end
        end
        m(i,:) = mean(classes);
    end
    testSize = size(testing_data,1);
    % calculating distances from mean points
    for i = 1:testSize       
       x=0;
       X=0;
       eucDist = zeros(maxClasses,1); 
       for j = 1:maxClasses
           eucDist(j) = pdist([testing_data(i,:);m(j,:)],'euclidean');
       end
       % assigning labels
       aimClass = testing_labels(i);
       X = find(classValues==aimClass);
       if eucDist(X)==min(eucDist)
           x = X;
       else
           x = find(eucDist==min(eucDist),1);
       end
       if x>maxStates
           assigned_labels(i)=max(classValues);
       else
           assigned_labels(i)=classValues(x);
       end
    end
    % generating error message
    message = "Point(s) ";
    for i = 1:testSize
        if testing_labels(i)~=assigned_labels(i)
            message = message + "#" + i + " at (";
            for j = 1:maxClasses
               message = message + testing_data(i,j);
               if j+1<=maxClasses
                  message = message + ",";
               end
            end
            message = message + "), ";
        end
    end
    message = message + "found an error.";
    if testing_labels==assigned_labels
        e = [];
    else
        e = message;
    end
end

%Q2.a
function [e,assigned_labels] = one_nn(training_data,...
    training_labels, testing_data, testing_labels)
    dataSize = size(training_data,1);
    classSize = size(training_data,2);
    testSize = size(testing_data,1);
    assigned_labels = zeros(dataSize,1);
    % calculate the distances to all objects in the set
    for i = 1:testSize   
       eucDist = zeros(dataSize,1);
       for j = 1:dataSize
           eucDist(j) = pdist([testing_data(i,:);...
               training_data(j,:)],'euclidean');
       end
       % identify the minimal distance and nearest neighbour object
       minDist = min(eucDist);
       nnObj = find(eucDist==minDist,1);
       % get label of the nearest neighbour object and add it
       % to assigned_labels
       assigned_labels(i) = training_labels(nnObj);
    end
    e = [];
end