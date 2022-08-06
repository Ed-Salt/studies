function f = fitness(t)
for i = 1:size(t,1)
    x(i) = bin2dec(num2str(t(i,:)')')/100000;
end
f = abs(sin(x/10-0.2)).*sqrt(abs(x-2));
end