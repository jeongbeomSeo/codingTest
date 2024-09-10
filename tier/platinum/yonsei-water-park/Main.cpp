#include <iostream>
#include <queue>
#include <vector>
#include <math.h>
#include <algorithm>

using namespace std;

typedef long long ll;

deque<pair<int, ll> > q;

typedef long long ll;

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);

    int N, D;
    cin >> N >> D;

    ll ans = -1e9;
    ll num;
    for (int i = 0; i < N; ++i) {
        cin >> num;

        while (!q.empty() && q.front().first < i - D) {
            q.pop_front();
        }
        if (!q.empty()) {
            num = max(num, num + q.front().second);
        }
        while (!q.empty() && q.back().second < num) {
            q.pop_back();
        }
        ans = max(ans, num);
        q.push_back(make_pair(i, num));
    }

    cout << ans;

    return 0;
}