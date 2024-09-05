#include <iostream>

typedef long long ll;

const ll MOD = 1000000007;

using namespace std;

ll fac[4000001];

ll query(ll num, int power) {
    if (power == 1) {
        return num;
    }

    if (power % 2 == 0) {
        ll result = query(num, power / 2);
        return result * result % MOD;
    } else {
        ll result = query(num, power - 1);
        return result * num % MOD;
    }
}

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);

    fac[0] = 1;
    for (int i = 1; i < 4000001; ++i) {
        fac[i] = (fac[i - 1] * i) % MOD;
    }

    int M;
    cin >> M;

    int N, K;
    for (int i = 0; i < M; ++i) {
        cin >> N >> K;

        ll result = fac[N] * query(fac[K] * fac[N - K] % MOD, MOD - 2) % MOD;
        cout << result << "\n";
    }

    return 0;
}