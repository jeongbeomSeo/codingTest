#include <iostream>
#include <math.h>

typedef long long ll;

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    ll N;
    cin >> N;

    ll ans = N;
    for (int i = 2; i <= sqrt(N); ++i) {
        if (N % i == 0) {
            ans = (ans - ans/i);
            while (N % i == 0) N /= i;
        }
    }

    if (N > 1) ans = (ans - ans / N);

    cout << ans;
}