clear, clc, close all

%Q1 Functions

a = rand(3,4); b = randi(3,3,1);
a
b
[c,d] = plug_inl(a,b)

%Q2 Sampling
%a) sub-sampling



% Functions
function [a,b] = plug_inl(data, labels)
    a = data;
    b = labels;
end