#include <iostream>
#include <string.h>

using namespace std;

int d[5001]; int d1[799995]; int d2[799995];

int main() {

    ios_base::sync_with_stdio(0); cin.tie(0);

    int W, A;

    cin >> W >> A;

    for (int i = 0; i < A; ++i) {
        cin >> d[i];
    }

    memset(d1, -1, sizeof (d1));
    memset(d2, -1, sizeof (d2));

    for (int i = 0; i < A - 1; ++i) {
        for (int j = i + 1; j < A; ++j) {
            int sum = d[i] + d[j];

            if (d1[sum] == -1) {
                d1[sum] = i;
                d2[sum] = j;
            }
        }
    }

    for (int i = 0; i < A - 1; ++i) {
        for (int j = i + 1; j < A; ++j) {
            int weight = W - d[i] - d[j];

            if (weight < 0 || weight > 400000 || d1[weight] == -1) continue;

            if (d1[weight] != i && d1[weight] != j && d2[weight] != i && d2[weight] != j) {
                cout << "YES";
                return 0;
            }
        }
    }

    cout << "NO";
    return 0;
}