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

루트 노드를 몰라서 **max_Idx**변수를 사용하면 안될 줄 알았는데,

**결국 어떤 지점에서 시작하던 간에 해당 노드로 부터 가장 긴 경로의 리프 노드를 찾으면 그것이 가장 긴 지름의 한 노드**라는 것을 알았다.

수학적으로 증명한 것은 아니지만, 이와 같은 방식으로 생각해 보았다.

임의의 점의 위치를 다음과 같은 경우로 나누어 생각해 보았다.

1. 가장 긴 지름의 서브 트리 안에 존재하는 점일 경우
2. 가장 긴 지름의 서브 트리 밖에 존재하는 점일 경우

먼저, **1번의 경우** 결국 임의의 한 점에서 **서브 트리의 루트 노드 까지 나오고 거기서 가장 긴 경로로 찾아 갈 것**이다.

여기서도 두가지 경우로 나눌 수 있다.
1. 가장 긴 지름의 한 노드로 이동
2. 서브 트리 밖의 리프 노드로 이동

1번의 경우 원했던 답이기 때문에 넘기고 2번의 경우를 살펴보자.

서브 트리의 루트 노드로부터 밖의 리프 노드 까지의 경로를 a, 서브 트리로부터의 가장 긴 지름의 두 노드 까지의 거리를 각각 b, c라고 해봅시다.

2번의 경우는 a > b,c라는 것인데, 이것은 말이 안된다. 

여기서 우리가 가장 먼저 가정했던 것은 **서브 트리에서 가장 긴 지름이 존재**한다. 그리고 그것은 **b + c**에 해당한다.

하지만 a > b,c라면 a + b || a + c > b + c를 의미하기 때문에 처음 가정했던 것이 무너진다.

그래서 서브 트리 안에 존재하는 점일 경우 무조건 가장 긴 지름의 한 노드로 경로가 이동할 것입니다.

**2번의 경우** 서브 트리 밖에 존재하는 임의의 점일경우에도 비슷한 방식으로 증명된다.

가장 가능성 있는 경우는 서브 트리의 루트 노드의 부모 노드를 택했을 경우일 텐데, 거기서부터 서브 트리 밖의 경로중 가장 긴 경로를 a 라고 하고, 해당 노드로부터 서브 트리의 루트 노드까지 거리를 b, 거기서부터 각자의 지름 노드까지의 거리를 c, d라고 해봅시다.

a > b + c > b + d(c > d라고 가정)를 의미하는 것인데, 선택한 노드 -> 루트 노드 -> c노드 까지 가는 경로가 가능하기 때문에 a + b + c > c + d에 해당 하므로 결국 서브 트리 밖의 임의의 한 점에서도 무조건 지름 노드 중 하나로 경로를 찾게 됩니다.

**AC**

```java
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int max = 0;
  static int max_Idx = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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


    boolean[] isVisited = new boolean[N + 1];
    Dfs(graph, 1, 0, isVisited);

    Arrays.fill(isVisited, false);
    Dfs(graph, max_Idx, 0, isVisited);
    System.out.println(max);
  }
  static void Dfs(ArrayList<ArrayList<Node>> graph, int node, int cost, boolean[] isVisited ) {
    isVisited[node] = true;

    if (max < cost) {
      max = cost;
      max_Idx = node;
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