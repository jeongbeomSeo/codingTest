#include <iostream>
#include <vector>
#include <algorithm>

typedef long long ll;

using namespace std;
ll N;
ll M;

vector<ll> v;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    cin >> M;

    v.resize(N);
    for (auto &i : v) cin >> i;

    ll ans = 0;
    for (int i = 1; i < (1 << N); ++i) {
        ll result = 1;
        for (int j = 0; j < N; ++j) {
            if ((i & (1 << j)) != 0) {
                result *= v[j];
            }
        }
        if (__builtin_popcount(i) & 1) {
            ans += M / result;
        } else {
            ans -= M / result;
        }
    }

    cout << ans;

}