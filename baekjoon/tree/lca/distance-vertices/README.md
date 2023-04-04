# 정점들의 거리

**플래티넘 5**

|시간 제한	|메모리 제한|	제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초|	128 MB	|11070|	4409|	2859|	38.181%|

## 문제

N(2 ≤ N ≤ 40,000)개의 정점으로 이루어진 트리가 주어지고 M(1 ≤ M ≤ 10,000)개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력하라.

## 입력

첫째 줄에 노드의 개수 N이 입력되고 다음 N-1개의 줄에 트리 상에 연결된 두 점과 거리를 입력받는다. 그 다음 줄에 M이 주어지고, 다음 M개의 줄에 거리를 알고 싶은 노드 쌍이 한 줄에 한 쌍씩 입력된다. 두 점 사이의 거리는 10,000보다 작거나 같은 자연수이다.

정점은 1번부터 N번까지 번호가 매겨져 있다.

## 출력

M개의 줄에 차례대로 입력받은 두 노드 사이의 거리를 출력한다.

## 예제 입력 1

```
7
1 6 13
6 3 9
3 5 7
4 1 3
2 4 20
4 7 2
3
1 6
1 4
2 6
```

## 예제 출력 1

```
13
3
36
```

## 풀이 방식

일반적인 그래프와 달리, 트리에서는 두 정점 사이의 직선이 오직 한 개만 존재한다.

부모 노드를 하나씩만 받고, 비순환 구조를 이루고 있기 때문이다.

해당 문제의 관건은 다음이라고 판단 되어 진다.

1. 얼마나 직선은 빠르게 찾느냐(탐색)
2. 미리 탐색한 정보를 어떻게 처리할 것인가2
> 현재 문제에서 최대 10,000개의 두 노드 쌍을 입력 받아 직선을 찾기 때문에, 매번 새로 찾으면 굉장히 오랜 시간이 걸릴 것이다.
>
> 효율적인 방법을 생각하려면 빠른 탐색과 이전에 탐색한 정보 처리 이 두 부분을 신경 써야 할 것같다.

일단, 선택한 방법은 탐색은 DFS, 직선 거리 정보 저장은 이차배열로 하기로 했다.

## 코드

**MLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[][] dist = new int[N + 1][N + 1];

    ArrayList<Node>[] graph = new ArrayList[N + 1];

    for (int i = 1; i <= N; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int i = 1; i <= N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      graph[n1].add(new Node(n2, cost));
      graph[n2].add(new Node(n1, cost));
    }

    int M = Integer.parseInt(br.readLine());

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      if (n1 == n2) {
        System.out.println(0);
        continue;
      }
      if (dist[n1][n2] == 0) {
        boolean[] isVisited = new boolean[N + 1];
        DFS(graph, dist, isVisited, n1, n1, 0);
      }
      System.out.println(dist[n1][n2]);
    }
  }
  static void DFS(ArrayList<Node>[] graph, int[][] dist, boolean[] isVisited, int src, int node, int cost) {

    isVisited[node] = true;
    dist[src][node] = dist[node][src] = cost;

    for (Node adjNode : graph[node]) {
      if (!isVisited[adjNode.idx]) {
        DFS(graph, dist, isVisited, src, adjNode.idx, cost + adjNode.cost);
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

사실 해당 문제는 이와 같이 푸는것이 아닌 것 같다. 알고리즘 분류에서 보니 **최소 공통 조상**이라고 적혀 있다.

해당 부분을 다시 공부하고 도전해보자.

## 풀이 방식

필요한 정보를 생각해보자.

1. 루트 노드 찾기
2. LCA 배열 (부모 노드 이차원 배열 + 레벨 배열 + distance 배열)

문제는, 이차원 배열을 사용했을 경우 distance값을 어떻게 처리해야 하는 것이다. 

가능하나? 불가능할것 같다.

따라서, DP방식이 아닌 일반적인 LCA방식으로 구현해야 할 것 같다.

일반적인 방식인 경우에도 레벨이 담긴 배열은 필요로 하다. 

이것을 구할 때 루트 노드가 필요한 지 부터 생각해보자.

아무리 생각해봐도 필요로 해보인다. 그렇다면 어떻게 구할 것인가? 

일반적인 방식으론 힘들어 보인다.

다시 생각해보자. 루트 노드가 필요한가? 그림판에 그려놓고 다시 생각해본 결과 굳이 없어도 해당 방식을 사용할 수 있을 것 같다.

물론, 루트 노드를 알 경우에 더욱 효율적으로 풀 수 있겠지만, 편향 트리가 될 지언정 불가능해보이지는 않는다. 

즉, 루트 노드를 내가 임의로 지정한다고 생각해도 트리 자체에서 다른 것으로 바뀌지는 않는다. 

따라서 다음과 같은 순서로 풀어보자.

1. 그래프(트리)를 생성
2. 일단 임의로 루트 노드 지정(1번 노드)
3. 해당 노드를 기준으로 DFS 실행
4. 이후 입력 받은 노드로 LCA 실행

위와 같은 방식으로 한다면, LCA를 실행하는 과정에서 부모 노드를 찾고 cost를 구하는 과정에서 반복문을 돌려야 한다. 너무 구져보인다.

따라서 parent배열을 애초부터 2차원배열로 해서 부모 노드와 cost를 같이 넣어주는 방식을 활용하는 것이 효율적으로 보인다.

## 코드 

아래 코드는 DP방식을 사용하지 않은 방식이다.

**AC**

```java
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());

    StringTokenizer st;

    ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }


    for (int i = 0; i < N - 1; i++){
      st = new StringTokenizer(br.readLine());

      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      graph.get(n1).add(new Node(n2, cost));
      graph.get(n2).add(new Node(n1, cost));
    }

    boolean[] isVisited = new boolean[N + 1];
    int[] level = new int[N + 1];
    int[][] parent= new int[N + 1][2];

    // DFS
    // purpose: level[], parent[]
    DFS(graph, 1, level, parent, 1, isVisited);

    int M = Integer.parseInt(br.readLine());

    // LCA
    // purpose: find distance
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      bw.write(LCA(n1, n2, level, parent) + "\n");
    }

    bw.flush();
    bw.close();
  }
  static void DFS(ArrayList<ArrayList<Node>> graph, int node, int[] level, int[][] parent, int curLevel, boolean[] isVisited) {
    isVisited[node] = true;
    level[node] = curLevel;

    for (Node childNode : graph.get(node)) {
      if (!isVisited[childNode.idx]) {
        parent[childNode.idx][0] = node;
        parent[childNode.idx][1] = childNode.cost;
        DFS(graph, childNode.idx, level, parent, curLevel + 1, isVisited);
      }
    }
  }

  static int LCA (int n1, int n2, int[] level, int[][] parent) {
    if (level[n1] > level[n2]) {
      int temp = n1;
      n1 = n2;
      n2 = temp;
    }

    int cost1 = 0;
    int cost2 = 0;

    while (level[n1] != level[n2]) {
       cost2 += parent[n2][1];
       n2 = parent[n2][0];
    }

    if (n1 == n2) return cost2;

    while (n1 != n2) {
      cost1 += parent[n1][1];
      cost2 += parent[n2][1];

      n1 = parent[n1][0];
      n2 = parent[n2][0];
    }

    return cost1 + cost2;
  }
}

class Node {
  int idx;
  int cost;

  Node (int idx, int cost) {
    this.idx = idx;
    this.cost = cost;
  }
}
```

다음은 DP 방식을 활용한 풀이다. 

DP를 활용하기 이전부터 우리는 부모 배열에 2차원 배열을 사용하고 있었다. 여기서 k번째 부모를 넣기 위해서는 어떻게 처리해야 할 것인가

먼저 행에 필요한 것은 노드 번호고, 열에 필요한 것은 k번째 부모 노드를 의미하는 k일 것이다. 그리고 그 안에 들어가야 할 것은 부모 노드의 번호와 cost가 들어가야 할 것이다.

k번째 부모 노드를 넣어 주고 cost를 연산하는 과정도 문제없이 작동 될 수 있다. 

처음에는 오답이 나왔다. 그 이유는 LCA에 있다고 판단했다. 

```java
  static int LCA(int[][][] parent, int[] level, int k, int n1, int n2) {
    if (level[n1] > level[n2]) {
      int temp = n1;
      n1 = n2;
      n2 = temp;
    }

    int cost1 = 0;
    int cost2 = 0;

    for (int i = k - 1; i >= 0; i--) {
      if (level[n2] - level[n1] > Math.pow(2, i)) {
        cost2 += parent[n2][i][1];
        n2 = parent[n2][i][0];
      }
    }
    if (n1 == n2) return cost2;

    for (int i = k - 1; i >= 0; i--) {
      if (parent[n1][i][0] != parent[n2][i][0]) {
        cost1 += parent[n1][i][1];
        cost2 += parent[n2][i][1];

        n1 = parent[n1][i][0];
        n2 = parent[n2][i][0];
      }
    }
    cost1 += parent[n1][0][1];
    cost2 += parent[n2][0][1];

    return cost1 + cost2;
  }
```

여기서 해당 코드를 봐보자.

```java
    for (int i = k - 1; i >= 0; i--) {
      if (level[n2] - level[n1] > Math.pow(2, i)) {
        cost2 += parent[n2][i][1];
        n2 = parent[n2][i][0];
      }
    }
```

level[n2] - level[n1] >= Math.pow(2,i) 이와 같이 해줘야 한다. 

그 이유는 2진수 표현을 생각해보면 알 수 있다.

|사용 방식|메모리| 시간      |
|---|---|---------|
|DP를 사용하지 않았을 경우| 47172KB | 2328ms  |
|DP를 사용한 경우| 66672KB | 632ms   |


