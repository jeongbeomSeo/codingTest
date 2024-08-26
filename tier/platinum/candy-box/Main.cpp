#include <iostream>
#include <vector>
#include <math.h>

int SIZE = 1000001;

using namespace std;

int n;

vector<int> seg;

void update(int node, int left, int right, int value, int count) {
    if (value < left || right < value) {
        return;
    }
    if (left == right) {
        seg[node] += count;
        return;
    }

    int mid = (left + right) / 2;
    update(2 * node, left, mid, value, count);
    update(2 * node + 1, mid + 1, right, value, count);

    seg[node] = seg[2 * node] + seg[2 * node + 1];
}

int query(int node, int left, int right, int num) {
    if (left == right) {
        seg[node]--;
        return left;
    }
    int result;
    int mid = (left + right) / 2;
    if (num > seg[2 * node]) {
        result = query(2 * node + 1, mid + 1, right, num - seg[2 * node]);
    } else {
        result = query(2 * node, left, mid, num);
    }

    seg[node] = seg[2 * node] + seg[2 * node + 1];

    return result;
}

int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    ios_base::sync_with_stdio(false);

    int h = ceil(log2(SIZE)) + 1;
    seg.resize((int)pow(2, h), 0);

    cin >> n;
    for (int i = 0; i < n; ++i) {
        int A;

        cin >> A;
        if (A == 1) {
            int B;
            cin >> B;
            int r = query(1, 1, SIZE, B);
            cout << r << '\n';
        } else {
            int B, C;
            cin >> B >> C;
            update(1, 1, SIZE, B, C);
        }
    }
    return 0;
}