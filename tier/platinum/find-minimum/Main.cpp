#include <iostream>
#include <queue>
#include <vector>

#define x first
#define y second

using namespace std;

deque<pair<int, int> > dq;

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);

    int N, L;
    cin >> N >> L;

    int n;
    for (int i = 0; i < N; ++i) {
        cin >> n;

        while (!dq.empty() && dq.front().x + L <= i) {
            dq.pop_front();
        }

        while (!dq.empty() && dq.back().y > n) {
            dq.pop_back();
        }

        dq.push_back(make_pair(i, n));
        cout << dq.front().y << " ";
    }


    return 0;
}