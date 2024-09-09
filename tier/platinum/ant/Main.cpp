#include <iostream>
#include <vector>
#define x first
#define y second

typedef long long ll;

const int SIZE = 100001;

using namespace std;

int n;

int energy[SIZE];
pair<int, ll> table[17][SIZE];
vector<vector<pair<int, int> > > graph;

int query(int node) {

    int curEnergy = energy[node];

    for (int i = 16; i >= 0; i--) {
        if (table[i][node].y <= curEnergy) {
            curEnergy -= table[i][node].y;
            node = table[i][node].x;
        }
    }

    return node;
}

void tableUpdate() {
    for (int i = 1; i < 17; ++i) {
        for (int j = 1; j < SIZE; ++j) {
            table[i][j] = make_pair(table[i - 1][table[i - 1][j].x].x, table[i - 1][table[i - 1][j].x].y + table[i - 1][j].y);
        }
    }
}

void dfs(int parNode, int node) {
    for (int i = 0; i < graph[node].size(); ++i) {
        pair<int, int> nxtNode = graph[node][i];

        if (nxtNode.x == parNode) continue;
        table[0][nxtNode.x] = make_pair(node, nxtNode.y);
        dfs(node, nxtNode.x);
    }
}

int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);

    cin >> n;

    graph = vector<vector<pair<int, int> > >(n + 1);

    for (int i = 1; i <= n; ++i) {
        cin >> energy[i];
    }

    int src, dst, cost;
    for (int i = 0; i < n - 1; ++i) {
        cin >> src >> dst >> cost;

        graph[src].push_back(make_pair(dst, cost));
        graph[dst].push_back(make_pair(src, cost));
    }

    table[0][1] = make_pair(1, 0);
    dfs(-1, 1);
    tableUpdate();

    for (int i = 1; i <= n; ++i) {
        cout << query(i) << "\n";
    }

    return 0;
}

