#include <iostream>, <string>

using namespace std;

int main(void)
{
    // Start the block with the declaration and initialisation of the local variables
    int i = -1;
    string my_string;

    cout << "Please enter an integer" << endl;
    cin >> i;
    cout << "You typed " << i << endl;

    cout << "Please enter a string (in other words anything)" << endl;
    cin >> my_string;
    cout << "You typed " << my_string << endl;

    return 0;
}