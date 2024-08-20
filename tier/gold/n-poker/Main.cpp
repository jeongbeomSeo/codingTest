#include <iostream>

using namespace std;

typedef long long ll;

const int mod = 1e4 + 7;
ll nCk[53][53];

int md(ll a, int b) {
   return ((a % b) + b) % b;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int N; cin >> N;

    for (int n = 0; n <= 52; n++) {
        nCk[n][0] = nCk[n][n] = 1;
        for (int k = 1; k < n; ++k) {
            nCk[n][k] = (nCk[n - 1][k - 1] + nCk[n - 1][k]) % mod;
        }
    }

    int ans = 0;
    for (int i = 4; i <= N; i += 4) {
        if ((i / 4) % 2 == 1) {
            ans += nCk[13][i / 4] * nCk[52 - i][N - i];
        } else {
            ans -= nCk[13][i / 4] * nCk[52 - i][N - i];
        }
        ans = md(ans, mod);
    }

    cout << ans << endl;
}