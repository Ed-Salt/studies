num = 3
Dice(num)

function Dice(k)
    p = [num2str(k) '.png']
    i = imread(p)
    imshow(i)
end