# LCA 2

**플래티넘 5**

|시간 제한|메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1.5 초 (추가 시간 없음) (하단 참고)|	256 MB	|28293	|10314	|5177|	32.578%|

## 문제 

N(2 ≤ N ≤ 100,000)개의 정점으로 이루어진 트리가 주어진다. 트리의 각 정점은 1번부터 N번까지 번호가 매겨져 있으며, 루트는 1번이다.

두 노드의 쌍 M(1 ≤ M ≤ 100,000)개가 주어졌을 때, 두 노드의 가장 가까운 공통 조상이 몇 번인지 출력한다.

## 입력 

첫째 줄에 노드의 개수 N이 주어지고, 다음 N-1개 줄에는 트리 상에서 연결된 두 정점이 주어진다. 그 다음 줄에는 가장 가까운 공통 조상을 알고싶은 쌍의 개수 M이 주어지고, 다음 M개 줄에는 정점 쌍이 주어진다.

## 출력 

M개의 줄에 차례대로 입력받은 두 정점의 가장 가까운 공통 조상을 출력한다.

## 예제 입력 1

```
15
1 2
1 3
2 4
3 7
6 2
3 8
4 9
2 5
5 11
7 13
10 4
11 15
12 5
14 7
6
6 11
10 9
2 6
7 6
8 13
8 15
```

## 예제 출력 1

```
2
4
2
1
3
1
```

## 시간 제한 

- Java 8: 2.5 초
- Java 8 (OpenJDK): 2.5 초
- Java 11: 2.5 초
- Kotlin (JVM): 2.5 초

# 코드

**AC**

```java
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      graph.get(n1).add(n2);
      graph.get(n2).add(n1);
    }

    boolean[] isVisited = new boolean[N + 1];
    int[] depth = new int[N + 1];
    int k = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
    int[][] parent = new int[N + 1][k];

    DFS(graph, isVisited, depth, parent, 0, 1);

    set_spaTable(parent, N, k);

    int M = Integer.parseInt(br.readLine());

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      bw.write(LCA(n1, n2, parent, depth, k) + "\n");
    }

    bw.flush();
    bw.close();
  }

  static int LCA(int n1, int n2, int[][] parent, int[] depth, int k) {

    if (depth[n1] > depth[n2]) {
      int temp = n1;
      n1 = n2;
      n2 = temp;
    }

    for (int i = k - 1; i >= 0; i--) {
      if (depth[n2] - depth[n1] >= Math.pow(2,i)) {
        n2 = parent[n2][i];
      }
    }

    if (n1 == n2) return n1;

    for (int i = k - 1; i>= 0; i--) {
      if (parent[n1][i] != parent[n2][i]) {
        n1 = parent[n1][i];
        n2 = parent[n2][i];
      }
    }

    return parent[n1][0];
  }
  static void DFS(ArrayList<ArrayList<Integer>> graph, boolean[] isVisited, int[] depth, int[][] parent, int curDepth, int node) {
    isVisited[node] = true;
    depth[node] = curDepth;

    for (int childNode : graph.get(node)) {
      if (!isVisited[childNode]) {
        parent[childNode][0] = node;
        DFS(graph, isVisited, depth, parent, curDepth + 1, childNode);
      }
    }
  }
  static void set_spaTable(int[][] parent, int N, int k) {
    for (int i = 1; i < k; i++) {
      for (int node = 1; node <= N; node++) {
        parent[node][i] = parent[parent[node][i - 1]][i - 1];
      }
    }
  }
}

```