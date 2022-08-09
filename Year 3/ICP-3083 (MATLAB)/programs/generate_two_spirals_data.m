function [data,labels] = generate_two_spirals_data(N,noise,...
    revolutions,randomise_step)

if nargin == 3
    randomise_step = true;
end

if numel(N) == 1
    N1 = round(N/2); % # points from class 1
    N2 = N - N1; % # points from class 2
else
    N1 = N(1); N2 = N(2);
end

if randomise_step
    theta1 = sort(rand(N1,1))*2*pi*revolutions;
    theta2 = sort(rand(N2,1))*2*pi*revolutions + pi;
    r1 = theta1/(2*pi*revolutions);
    r2 = (theta2 - pi)/(2*pi*revolutions);
else
    theta1 = linspace(0,2*pi*revolutions,N1)'; % carrier
    theta2 = linspace(pi,2*pi*revolutions+pi,N2)'; 
    r1 = linspace(0,1,N1)';
    r2 = linspace(0,1,N2)';
end

data1 =  [sin(theta1).*r1, cos(theta1).*r1];
data2 =  [sin(theta2).*r2, cos(theta2).*r2];
data = [data1;data2];
data = data + randn(size(data))*noise;
labels = [ones(N1,1);ones(N2,1)*2];

