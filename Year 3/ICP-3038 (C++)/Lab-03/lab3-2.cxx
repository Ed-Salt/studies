#include <iostream>

using namespace std;

int main()
{
    int a = 5;
    int divisor = 3;
    float division = a / divisor;
    float remainder = a % divisor;

    cout << a << " / " << divisor << " = " << division << endl;
    cout << a << " % " << divisor << " = " << remainder << endl;

    float b = division * divisor + remainder;

    cout << division << " * " << divisor << " + " << remainder << " = " << b << endl;

    return 0;
}