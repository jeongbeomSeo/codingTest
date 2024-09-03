#include <iostream>
#include <math.h>
#include <string.h>
#include <algorithm>

using namespace std;

typedef long long ll;

int N, M, D[201][201], S[20001], E[20001]; ll X[20001];

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    cin >> N >> M;
    memset(D, 0x3f, sizeof D);
    for (int i = 1; i <= N; ++i) {
        D[i][i] = 0;
    }
    for (int i = 1; i <= M; ++i) {
        cin >> S[i] >> E[i] >> X[i];
        D[S[i]][E[i]] = min<int>(D[S[i]][E[i]], X[i]);
        D[E[i]][S[i]] = min<int>(D[E[i]][S[i]], X[i]);
    }

    for (int k = 1; k <= N; ++k) for (int i = 1; i <= N; i++) for (int j = 1; j <= N; j++) D[i][j] = min(D[i][j], D[i][k] + D[k][j]);

    ll res = 0x3f3f3f3f3f3f3f;
    for (int i = 1; i <= N; i++) {
        ll mx = 0;
        for (int j = 1; j <= M; j++) mx = max(mx, D[i][S[j]] + D[i][E[j]] + X[j]);
        res = min(res, mx);
    }

    cout << res / 2 << "." << res % 2 * 5;

    return 0;
}