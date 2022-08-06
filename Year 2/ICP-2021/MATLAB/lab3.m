clear
close all
clc

%Q1.a
result = 1
plays = 0
wins = 0
clc
while result > 0
    disp("Guessing game! Enter a number between 1 and 10:")
    result = input('Enter your guess >')
    if result > 0
        num = randi(10)
        plays = plays + 1
        clc
        if num == result
            wins = wins + 1
            clc
            disp("Success!")
        else
            disp("Unlucky! Try again.")
        end
        disp("")
    end    
end
clc
disp("Rounds played: " + plays)
disp("Rounds won: " + wins)

%Q1.b
im = rgb2gray(imread('yellowCar.jpg'))
figure, imshow(im)
% i = 1
% while i <= size(im,1)
%     j = 1
%     while j <= size(im,2)
%         num = randi([0,1])
%         im(i,j) = im(i,j) * num
%         j = j + 1
%     end
%     i = i + 1
% end
im = arrayfun(@(e) e*randi([0,1]),im)
figure, imshow(im)


    
