#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

typedef long long ll;

int n;
vector<ll> v;
const ll mod = 1e9 + 7;

ll pw(ll a, ll b) {
    ll ret = 1;
    while (b) {
        if (b & 1) ret = ret * a % mod;
        b >>= 1;
        a = a * a % mod;
    }
    return ret;
}

ll md(ll a, ll b) {
    return ((a % b)+b) % b;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;
    v.resize(n);
    for (auto &i : v) cin >> i;
    sort(v.begin(), v.end());

    ll ans = 0;
    for (int i = 0; i < n; ++i) {
        ll mxcnt = pw(2, i);
        ans += (v[i] % mod) * mxcnt % mod;
        ll mncnt = pw(2, n - i - 1);
        ans -= (v[i] % mod) * mncnt % mod;
        ans = md(ans, mod);
    }
    cout << ans;
}