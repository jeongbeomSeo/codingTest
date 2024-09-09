#include <iostream>
#include <math.h>

using namespace std;

const int SIZE = 200001;

int table[19][SIZE];

void tableUpdate(int m) {
    for (int i = 1; i < 19; ++i) {
        for (int j = 1; j <= m; ++j) {
            table[i][j] = table[i - 1][table[i - 1][j]];
        }
    }
}

int main() {

    ios_base::sync_with_stdio(0); cin.tie(0);

    int m;

    cin >> m;

    for (int i = 1; i <= m; ++i) {
        cin >> table[0][i];
    }

    tableUpdate(m);

    int Q;
    cin >> Q;

    int n, x;
    for (int i = 0; i < Q; ++i) {
        cin >> n >> x;

        for (int j = 18; j >= 0; j--) {
            if ((n & (1 << j)) != 0) {
                x = table[j][x];
            }
        }

        cout << x << "\n";
    }
}