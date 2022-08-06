clear, clc, close all

N = 500;

[data,labels] = generate_two_spirals_data(N,0.02,3);
scatter(data(:,1),data(:,2),20,labels,'filled')
axis equal, grid on

%%
% ground truth
[gt_data,gt_labels] = generate_two_spirals_data(10000,0.04,3);
figure
scatter(gt_data(:,1),gt_data(:,2),8,gt_labels,'filled')
axis equal, grid on

L = 3; % ensemble size

IndLabels = zeros(10000,L);
IndTraining = zeros(N,L);

for i = 1:L
    % take bootstrap sample
    bi = randi(N,N,1); % bootstrap index
    training_data = data(bi,:);
    training_labels = labels(bi);
    
    % train tree classifier
    T = fitctree(training_data,training_labels);
    
    % resubstitution
    assigned_labels = predict(T,data);
    IndTraining(:,i) = assigned_labels;
    
    % testing with ground truth
    assigned_labels = predict(T,gt_data);
    IndLabels(:,i) = assigned_labels;
end
   
ens_labels_gt = mode(IndLabels,2); % labels of ground truth
ens_labels_resub = mode(IndTraining,2); % labels from resubstitution

% individual resubstitution error
e_ind_resub = mean(IndTraining ~= labels)
av_e_ind_resub = mean(e_ind_resub)

% ensemble resubstitution error
e_ens_resub = mean(ens_labels_resub ~= labels)

% individual testing error
e_ind_test = mean(IndLabels ~= gt_labels)
av_e_ind_test = mean(e_ind_test)

% ensemble testing error
e_ens_tst = mean(ens_labels_gt ~= gt_labels)




