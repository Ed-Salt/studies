#include <iostream>
#include <string>

using namespace std;

void printArray(const string& aName, float* anArray, unsigned int aNumberOfElements);


int main()
{
    float a[5] = { 1.0, 2.0, 4.4, 8.0, 16.0 };
    //float b[5] = { 1.0, 2.0, 4.4, 8.0, 16.0, 32.0 };
    float c[] = { 1.0, 2.0, 4.4, 8.0, 16.00 };
    float d[5];
    float e[5] = {};
    printArray("a", a, 5);
    cout << endl;
    printArray("a", c, 5);
    cout << endl;
    printArray("a", d, 5);
    cout << endl;
    printArray("a", e, 5);
    cout << endl;
    return 0;
}


void printArray(const string& aName, float* anArray, unsigned int aNumberOfElements)
{
    for (int i = 0; i < aNumberOfElements; i++) {
        cout << aName << "[" << i << "]: " << anArray[i] << endl;
    }
}