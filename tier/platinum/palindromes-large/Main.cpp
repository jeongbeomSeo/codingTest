#include <iostream>
#include <string>
#include <vector>

using namespace std;

const int MOD = 10007;

int main() {

    ios_base::sync_with_stdio(0); cin.tie(0);

    string s;

    cin >> s;

    vector<vector<int> > dp(s.length(), vector<int>(s.length(), 0));

    for (int i = 0; i < s.length(); ++i) {
        dp[i][i] = 1;
    }

    for (int i = 0; i < s.length() - 1; ++i) {
        dp[i][i + 1] = 2;
        if (s[i] == s[i + 1]) dp[i][i + 1]++;
    }

    for (int k = 2; k < s.length(); ++k) {

        for (int i = 0; i + k < s.length(); ++i) {
            int j = i + k;

            dp[i][j] += dp[i + 1][j];
            dp[i][j] += dp[i][j - 1];
            dp[i][j] -= dp[i + 1][j - 1];

            while (dp[i][j] < 0) dp[i][j] += MOD;
            dp[i][j] %= MOD;

            if (s[i] == s[j]) dp[i][j] += dp[i + 1][j - 1] + 1;
            dp[i][j] %= MOD;
        }
    }

    cout << dp[0][s.length() - 1];

    return 0;
}