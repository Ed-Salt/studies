#include <iostream>, <string>

using namespace std;

int main(void)
{
    float cel = -1;

    cout << "Please enter an temperature in celcius" << endl;
    cin >> cel;
    cout << "You typed " << cel << endl;

    float fah = (cel * 1.8) + 32;

    cout << cel << " degrees Celsius is the same as " << fah << " degrees Fahrenheit." << endl;

    return 0;
}