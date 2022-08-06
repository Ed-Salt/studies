clear, clc, close all
a = imread('Peppa.jpg');
[rows, cols] = deal(80,60);
aa = imresize(a,[rows, cols]);
q = 5; % block size

w = ones(q,q,3)/(q^2*3); % weights

% create padded image
ps = (q-1)/2; % pad size
ap = padarray(aa, [2,20]);
figure, imshow(ap, 'initialmagnification','fit')
title('Padded image')

o = zeros(rows,cols); % output
for i = ps+1:ps+cols
    for j = ps+1:ps+rows
        
    end
end