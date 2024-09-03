#include <iostream>
#include <vector>

using namespace std;

vector<int> v;

int N, K;

int gcd(int a, int b) {
    if (b == 0) return a;

    return gcd(b, a % b);
}

int main() {

    ios_base::sync_with_stdio(0); cin.tie(0);

    cin >> N >> K;

    v = vector<int>(N);
    for (auto &i : v) cin >> i;

    v.push_back(360);

    int c;
    for (int k = 0; k < K; ++k) {
        bool success = false;

        cin >> c;

        for (int i = 0; i < v.size() - 1; ++i) {
            for (int j = i + 1; j < v.size(); ++j) {
                int gcdValue = gcd(v[i], v[j]);
                if (c % gcdValue == 0) {
                    success = true;
                    break;
                }
            }
            if (success) break;
        }

        if (success) cout << "YES" << "\n";
        else cout << "NO" << "\n";
    }


    return 0;
}