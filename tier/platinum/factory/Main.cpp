#include <iostream>
#include <vector>
#include <math.h>

using namespace std;

typedef long long ll;

const int MAX_SIZE = 1000001;
int map[MAX_SIZE];
vector<int> v;
vector<int> seg;
int N;

void update(int node, int start, int end, int idx) {
    if (idx < start || end < idx) return;
    if (start == end) {
        seg[node] = 1;
        return;
    }

    int mid = (start + end) / 2;
    update(2 * node, start, mid, idx);
    update(2 * node + 1, mid + 1, end, idx);

    seg[node] = seg[2 * node] + seg[2 * node + 1];
}

ll query(int node, int start, int end, int idx) {
    if (end < idx) return 0;
    if (idx <= start) {
        return seg[node];
    }

    int mid = (start + end) / 2;
    ll lr = query(2 * node, start, mid, idx);
    ll rr = query(2 * node + 1, mid + 1, end, idx);

    return lr + rr;
}

int main() {

    ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    cin >> N;

    v.resize(N);
    for (auto &i : v) cin >> i;

    int n;
    for (int i = 1; i <= N; ++i) {
        cin >> n;
        map[n] = i;
    }

    int h = ceil(log2(N));
    int size = (1 << (h + 1));

    seg.resize(size);

    ll count = 0;
    for (auto &n : v) {
        int idx = map[n];

        count += query(1, 1, N, idx);
        update(1, 1, N, idx);
    }

    cout << count;
}