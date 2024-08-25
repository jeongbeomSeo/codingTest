#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;

int idx = 1;
vector<vector<int> > v;
vector<bool> finished;
vector<int> d;
vector<vector<int> > graph;
stack<int> s;

int dfs(int node) {

    d[node] = idx++;
    int parent = d[node];

    s.push(node);
    for (int j = 0; j < graph[node].size(); ++j) {
        int nxtNode = graph[node][j];

        if (d[nxtNode] == 0) parent = min(parent, dfs(nxtNode));
        else if (!finished[nxtNode]) parent = min(parent, d[nxtNode]);
    }

    if (d[node] == parent) {
        vector<int> scc;
        while (1) {
            int popNode = s.top();
            s.pop();

            finished[popNode] = true;
            d[popNode] = parent;
            scc.push_back(popNode);
            if (popNode == node) break;
        }
        sort(scc.begin(), scc.end());
        v.push_back(scc);
    }

    return parent;
}

bool compare(vector<int> a, vector<int> b) {
    return a[0] < b[0];
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int V, E;
    cin >> V >> E;

    graph.resize(V + 1);
    for (int i = 0; i < E; ++i) {
        int src, dst;
        cin >> src >> dst;
        graph[src].push_back(dst);
    }


    finished.resize(V + 1, false);
    d.resize(V + 1, 0);
    for (int i = 1; i < V + 1; ++i) {
        if (d[i] == 0) dfs(i);
    }

    cout << v.size() << endl;
    sort(v.begin(), v.end(), compare);
    for (int i = 0; i < v.size(); ++i) {
        for (int j = 0; j < v[i].size(); ++j) {
            cout << v[i][j] << " ";
        }
        cout << -1 << endl;
    }

}