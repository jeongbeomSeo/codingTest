#include <iostream>
#include <vector>
#include <math.h>

using namespace std;

typedef long long ll;

int N;
vector<ll> arr;
vector<ll> seg;

void updateSeg(int node, int start, int end) {
    if (start == end) {
        seg[node] = start;
        return;
    }

    int mid = (start + end) / 2;
    updateSeg(2 * node, start, mid);
    updateSeg(2 * node + 1, mid + 1, end);

    if (arr[seg[2 * node]] > arr[seg[2 * node + 1]]) {
        seg[node] = seg[2 * node + 1];
    } else {
        seg[node] = seg[2 * node];
    }
}

int findIndex(int node, int start, int end, int left, int right) {
    if (right < start || end < left) return -1;
    if (left <= start && end <= right) return seg[node];

    int mid = (start + end) / 2;
    int leftIdx = findIndex(2 * node, start, mid, left, right);
    int rightIdx = findIndex(2 * node + 1, mid + 1, end, left, right);

    if (leftIdx == -1) return rightIdx;
    if (rightIdx == -1) return leftIdx;

    if (arr[leftIdx] < arr[rightIdx]) return leftIdx;
    return rightIdx;
}

ll query(int start, int end) {

    int idx = findIndex(1, 1, N, start, end);
    ll result = (end - start + 1) * arr[idx];

    if (start < idx) {
        ll leftArea = query(start, idx - 1);
        result = max(result, leftArea);
    }
    if (idx < end) {
        ll rightArea = query(idx + 1, end);
        result = max(result, rightArea);
    }

    return result;
}

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    while (true) {
        cin >> N;
        if (N == 0) break;

        arr.clear();
        arr.resize(N + 1);
        for (int i = 1; i < N + 1; ++i) {
            cin >> arr[i];
        }

        int h = ceil(log2(N));
        int size = 1 << h + 1;

        seg.clear();
        seg.resize(size);

        updateSeg(1, 1, N);

        cout << query(1, N) << "\n";
    }

}