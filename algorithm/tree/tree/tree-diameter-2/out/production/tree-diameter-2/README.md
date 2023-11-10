# 트리의 지름

**골드 2**

|시간 제한	|메모리 제한|	제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	256 MB|	38754|	14081|	10081|	33.778%|

## 문제 

트리의 지름이란, 트리에서 임의의 두 점 사이의 거리 중 가장 긴 것을 말한다. 트리의 지름을 구하는 프로그램을 작성하시오.

## 입력

트리가 입력으로 주어진다. 먼저 첫 번째 줄에서는 트리의 정점의 개수 V가 주어지고 (2 ≤ V ≤ 100,000)둘째 줄부터 V개의 줄에 걸쳐 간선의 정보가 다음과 같이 주어진다. 정점 번호는 1부터 V까지 매겨져 있다.

먼저 정점 번호가 주어지고, 이어서 연결된 간선의 정보를 의미하는 정수가 두 개씩 주어지는데, 하나는 정점번호, 다른 하나는 그 정점까지의 거리이다. 예를 들어 네 번째 줄의 경우 정점 3은 정점 1과 거리가 2인 간선으로 연결되어 있고, 정점 4와는 거리가 3인 간선으로 연결되어 있는 것을 보여준다. 각 줄의 마지막에는 -1이 입력으로 주어진다. 주어지는 거리는 모두 10,000 이하의 자연수이다.

## 출력 

첫째 줄에 트리의 지름을 출력한다.

## 예제 입력 1

```
5
1 3 2 -1
2 4 4 -1
3 1 2 4 3 -1
4 2 4 3 3 5 6 -1
5 4 6 -1
```

## 예제 출력 1

```
11
```

## 코드

**TLE**

```java
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  static int max = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());

    ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    StringTokenizer st;
    for (int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      int parNode = Integer.parseInt(st.nextToken());
      while (true) {
        int node = Integer.parseInt(st.nextToken());
        if (node == -1) break;
        int cost = Integer.parseInt(st.nextToken());

        graph.get(parNode).add(new Node(node, cost));
        graph.get(node).add(new Node(parNode, cost));
      }
    }


    for (int i = 1; i < N + 1; i++) {
      boolean[] isVisited = new boolean[N + 1];
      Dfs(graph, i, 0, isVisited);
    }
    System.out.println(max);
  }
  static void Dfs(ArrayList<ArrayList<Node>> graph, int node, int cost, boolean[] isVisited ) {
    isVisited[node] = true;

    if (max < cost) {
      max = cost;
    }

    for (Node childNode: graph.get(node)) {
      if (!isVisited[childNode.idx]) {
        Dfs(graph, childNode.idx, cost + childNode.cost, isVisited);
      }
    }
  }
}


class Node {
  int idx;
  int cost;

  Node(int idx, int cost) {
    this.idx = idx;
    this.cost = cost;
  }
}

```