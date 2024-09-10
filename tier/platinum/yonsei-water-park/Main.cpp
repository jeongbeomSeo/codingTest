#include <iostream>
#include <math.h>

using namespace std;

typedef long long ll;

const int SIZE = 100001;

ll tree[4 * SIZE];
ll arr[SIZE];

ll query(int node, int left, int right, int start, int end) {
    if (end < left || right < start) return -1e9;
    if (start <= left && right <= end) return tree[node];

    int mid = (left + right) / 2;
    return max(query(2 * node, left, mid, start, end), query(2 * node + 1, mid + 1, right, start, end));
}

void updateSeg(int node, int left, int right, int idx, ll value) {
    if (idx < left || right < idx) return;
    if (left == right) {
        tree[node] = value;
        return;
    }

    int mid = (left + right) / 2;
    updateSeg(2 * node, left, mid, idx, value);
    updateSeg(2 * node + 1, mid + 1, right, idx, value);

    tree[node] = max(tree[2 * node], tree[2 * node + 1]);
}

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);

    int N, D;

    cin >> N >> D;

    for (int i = 1; i <= N; ++i) {
        cin >> arr[i];
    }

    ll ans = arr[1];
    updateSeg(1, 1, N, 1, arr[1]);
    for (int i = 2; i <= N; ++i) {
        ll temp = arr[i] + max(0LL, query(1, 1, N, max(1, i - D), i - 1));
        ans = max(ans, temp);
        updateSeg(1, 1, N, i, temp);
    }

    cout << ans;
}