# 타임머신

|시간 제한	|메모리 제한|	제출	|정답	맞힌 사람|	정답 비율|
|---|---|---|---|---|
|1 초|	256 MB	|46448	9274|	5798	22.532%|

## 문제

N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 버스가 M개 있다. 각 버스는 A, B, C로 나타낼 수 있는데, A는 시작도시, B는 도착도시, C는 버스를 타고 이동하는데 걸리는 시간이다. 시간 C가 양수가 아닌 경우가 있다. C = 0인 경우는 순간 이동을 하는 경우, C < 0인 경우는 타임머신으로 시간을 되돌아가는 경우이다.

1번 도시에서 출발해서 나머지 도시로 가는 가장 빠른 시간을 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 도시의 개수 N (1 ≤ N ≤ 500), 버스 노선의 개수 M (1 ≤ M ≤ 6,000)이 주어진다. 둘째 줄부터 M개의 줄에는 버스 노선의 정보 A, B, C (1 ≤ A, B ≤ N, -10,000 ≤ C ≤ 10,000)가 주어진다. 

## 출력

만약 1번 도시에서 출발해 어떤 도시로 가는 과정에서 시간을 무한히 오래 전으로 되돌릴 수 있다면 첫째 줄에 -1을 출력한다. 그렇지 않다면 N-1개 줄에 걸쳐 각 줄에 1번 도시에서 출발해 2번 도시, 3번 도시, ..., N번 도시로 가는 가장 빠른 시간을 순서대로 출력한다. 만약 해당 도시로 가는 경로가 없다면 대신 -1을 출력한다.

## 예제 입력1

```
3 4
1 2 4
1 3 3
2 3 -1
3 1 -2
```

## 예제 출력1

```
4
3
```

## 예제 입력2

```
3 4
1 2 4
1 3 3
2 3 -4
3 1 -2
```

## 예제 출력2

```
-1
```

## 예제 입력3

```
3 2
1 2 4
1 2 3
```

## 예제 출력3

```
3
-1
```

## 코드 오류 

**출력 초과**
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
  int v;
  int w;
  int cost;

  Node(int v, int w, int cost) {
    this.v = v;
    this.w = w;
    this.cost = cost;
  }
}

public class Main {
  static ArrayList<ArrayList<Node>> graph;
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 도시의 개수 와 버스
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    graph = new ArrayList<>();
    for(int i  = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }


    // 버스의 정보
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int v = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      graph.get(v).add(new Node(v, w, cost));
    }

    // dist[] 초기화
    long[] dist = new long[N + 1];
    Arrays.fill(dist, INF);
    dist[1] = 0;

    // 벨만-포드 알고리즘

    BellmanFord(N, dist);

  }
  static void BellmanFord(int N, long[] dist) {
    // 순환 사이클을 도는 도시 넣어줄 Collection 생성
    Set<Integer> cycle = new LinkedHashSet<>();
    int INFCount = 0;
    // N - 1 번 실행
    for(int count = 0; count < N - 1; count++) {
      // 모든 버스 정보에 대해서 BellMan Ford 실행
      for(int i = 1; i < graph.size(); i++) {
        if(dist[i] != INF) {
          for(int j = 0; j < graph.get(i).size(); j++) {
            // cf) adjNode.v == i
            Node adjNode = graph.get(i).get(j);
            dist[adjNode.w] = Math.min(dist[adjNode.w], dist[adjNode.v] + adjNode.cost);
          }
        }
      }
    }
    for(int i = 1; i < graph.size(); i++) {
      if(dist[i] != INF) {
        for(int j = 0; j < graph.get(i).size(); j++) {
          Node adjNode = graph.get(i).get(j);
          if (dist[adjNode.w] > dist[adjNode.v] + adjNode.cost) {
            dist[adjNode.w] = dist[adjNode.v] + adjNode.cost;
            cycle.add(adjNode.w);
          }
        }
      }
      else INFCount++;
    }
    // 도시로 가는 경로가 전부 없는 경우
    if(INFCount + cycle.size() >= N - 1) System.out.println(-1);
    else {
      for(int i = 2; i < dist.length; i++) {
        // 해당 도시로 가는 경로조차 없는 경우
        if(dist[i] == INF) System.out.println(-1);
        else {
          // 해당 도시로 가는 경로가 음수 사이클 경로중 하나 일 때
          if(cycle.contains(i)) System.out.println(-1);
            // 정상적인 경로
          else System.out.println(dist[i]);
        }
      }
    }
  }
}

```

문제를 똑바로 읽는 버릇을 들이자. 음의 사이클이 존재하기만 한다면 -1을 첫째 줄에 출력하는 것이고 없는 경우에만 다른 것들을 출력하는 것.
즉 음의 사이클이 모든 경로든 아니든 상관없이 있기만 하다면 -1만 출력하는 것이다.

## 나의 코드

**Bellman-Ford Algorithm**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
  int v;
  int w;
  int cost;

  Node(int v, int w, int cost) {
    this.v = v;
    this.w = w;
    this.cost = cost;
  }
}

public class Main {
  static ArrayList<ArrayList<Node>> graph;
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 도시의 개수 와 버스
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    graph = new ArrayList<>();
    for(int i  = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }


    // 버스의 정보
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int v = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      graph.get(v).add(new Node(v, w, cost));
    }

    // dist[] 초기화
    // N이 최대 500, M이 최대 6,000, 음의 간선 최소 수치가 -10,000라는 것을 고려
    long[] dist = new long[N + 1];
    Arrays.fill(dist, INF);
    dist[1] = 0;

    // 벨만-포드 알고리즘
    BellmanFord(N, dist);
  }
  static void BellmanFord(int N, long[] dist) {
    // 순환 사이클을 도는 도시 넣어줄 Collection 생성
    boolean cycle = false;
    // N - 1 번 실행
    for(int count = 0; count < N - 1; count++) {
      // 모든 버스 정보에 대해서 BellMan Ford 실행
      for(int i = 1; i < graph.size(); i++) {
        if(dist[i] != INF) {
          for(int j = 0; j < graph.get(i).size(); j++) {
            // cf) adjNode.v == i
            Node adjNode = graph.get(i).get(j);
            dist[adjNode.w] = Math.min(dist[adjNode.w], dist[adjNode.v] + adjNode.cost);
          }
        }
      }
    }
    for(int i = 1; i < graph.size(); i++) {
      if(dist[i] != INF) {
        for(int j = 0; j < graph.get(i).size(); j++) {
          Node adjNode = graph.get(i).get(j);
          if (dist[adjNode.w] > dist[adjNode.v] + adjNode.cost) {
            dist[adjNode.w] = dist[adjNode.v] + adjNode.cost;
            cycle = true;
          }
        }
      }
    }
    // 도시로 가는 경로가 무한히 오래전으로 되돌릴 수 있는 경우 즉, 모든 경로가 음의 사이클 존재
    if(cycle) System.out.println(-1);
    else {
      for(int i = 2; i < dist.length; i++) {
        // 해당 도시로 가는 경로조차 없는 경우
        if(dist[i] == INF) System.out.println(-1);
        // 정상적인 경로
        else System.out.println(dist[i]);
      }
    }
  }
}

```

**SPFA(Shortest Path Faster Algorith)**

```java

```