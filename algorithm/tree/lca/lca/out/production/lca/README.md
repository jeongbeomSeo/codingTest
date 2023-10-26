# LCA

**골드 3**

|시간 제한	|메모리 제한	|제출|	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|3 초|	256 MB|	17568|	7536|	4535|	41.366%|

## 문제 

N(2 ≤ N ≤ 50,000)개의 정점으로 이루어진 트리가 주어진다. 트리의 각 정점은 1번부터 N번까지 번호가 매겨져 있으며, 루트는 1번이다.

두 노드의 쌍 M(1 ≤ M ≤ 10,000)개가 주어졌을 때, 두 노드의 가장 가까운 공통 조상이 몇 번인지 출력한다.

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

## 코드

**WA(4%)**

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

    int[] parentList = new int[N + 1];
    int[] level = new int[N + 1];

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int parNode = Integer.parseInt(st.nextToken());
      int childNode = Integer.parseInt(st.nextToken());

      graph.get(parNode).add(childNode);
      graph.get(childNode).add(parNode);
    }

    boolean[] isVisited = new boolean[N + 1];

    DFS(graph, level, parentList, isVisited, 1, 1);

    int M = Integer.parseInt(br.readLine());

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      if (n1 == n2) {
        bw.write(parentList[n1] + "\n");
        continue;
      }
      bw.write(LCA(parentList, level, n1, n2) + "\n");
    }
    bw.flush();
    bw.close();
  }
  static void DFS(ArrayList<ArrayList<Integer>> graph, int[] level, int[] parentList, boolean[] isVisited, int curLevel, int node) {
    isVisited[node] = true;
    level[node] = curLevel;

    for (int childNode : graph.get(node)) {
      if (!isVisited[childNode]) {
        DFS(graph, level, parentList, isVisited, curLevel + 1, childNode);
        parentList[childNode] = node;
      }
    }
  }

  static int LCA(int[] parentList, int[] level, int n1, int n2) {

    if (level[n1] > level[n2]) {
      int temp = n1;
      n1 = n2;
      n2 = temp;
    }
    while (level[n1] != level[n2]) {
      n2 = parentList[n2];
    }
    while (n1 > 1 && n2 > 1 && n1 != n2) {
      n1 = parentList[n1];
      n2 = parentList[n2];
    }
    return n1;
  }
}

```