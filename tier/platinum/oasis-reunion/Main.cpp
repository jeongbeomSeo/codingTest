#include <iostream>
#include <stack>

typedef long long ll;

using namespace std;

stack<pair<int,int> > s;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    ll count = 0;

    int N;
    cin >> N;

    int num;

    for (int i = 0; i < N; ++i) {
        cin >> num;

        while (!s.empty() && s.top().first < num) {
            count += s.top().second;
            s.pop();
        }

        if (!s.empty()) {
            if (s.top().first == num) {
                count += s.top().second;
                s.top().second++;
                if (s.size() >= 2) {
                    count++;
                }
                continue;
            } else {
                count++;
            }
        }

        s.push(make_pair(num, 1));
    }

    cout << count;

    return 0;
}