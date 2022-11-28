# 특정한 최단 경로

|시간 제한|	메모리 제한|	제출	|정답	맞힌 사람|	정답 비율|
|---|---|---|---|---|
|1 초|	256 MB|	58690	|14836|	10001	|24.548%|

## 문제

방향성이 없는 그래프가 주어진다. 세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동하려고 한다. 또한 세준이는 두 가지 조건을 만족하면서 이동하는 특정한 최단 경로를 구하고 싶은데, 그것은 바로 임의로 주어진 두 정점은 반드시 통과해야 한다는 것이다.

세준이는 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다. 하지만 반드시 최단 경로로 이동해야 한다는 사실에 주의하라. 1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성하시오.

## 입력

첫째 줄에 정점의 개수 N과 간선의 개수 E가 주어진다. (2 ≤ N ≤ 800, 0 ≤ E ≤ 200,000) 둘째 줄부터 E개의 줄에 걸쳐서 세 개의 정수 a, b, c가 주어지는데, a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c라는 뜻이다. (1 ≤ c ≤ 1,000) 다음 줄에는 반드시 거쳐야 하는 두 개의 서로 다른 정점 번호 v1과 v2가 주어진다. (v1 ≠ v2, v1 ≠ N, v2 ≠ 1) 임의의 두 정점 u와 v사이에는 간선이 최대 1개 존재한다.

## 출력

첫째 줄에 두 개의 정점을 지나는 최단 경로의 길이를 출력한다. 그러한 경로가 없을 때에는 -1을 출력한다.

## 예제 입력 1
```
4 6
1 2 3
2 3 3
3 4 1
1 3 5
2 4 5
1 4 4
2 3
```
## 예제 출력 1
```
7
```

## 나의 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node {
  int idx;
  int cost;

  Node(int idx, int cost) {
    this.idx = idx;
    this.cost = cost;
  }
}
public class Main {
  static int INF = Integer.MAX_VALUE;
  static int E,V;
  static ArrayList<ArrayList<Node>> graph;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 정점 E 와 간선 V 입력 받기
    E = Integer.parseInt(st.nextToken());
    V = Integer.parseInt(st.nextToken());

    // 그래프 초기화
    graph = new ArrayList<>();
    for(int i = 0; i < E + 1; i++) {
      graph.add(new ArrayList<>());
    }

    // 간선 입력 받기
    for(int i = 0; i < V; i++) {
      st = new StringTokenizer(br.readLine());
      int node1 = Integer.parseInt(st.nextToken());
      int node2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      graph.get(node1).add(new Node(node2, cost));
      graph.get(node2).add(new Node(node1, cost));
    }

    // 임의의 두 정점 입력 받기
    st = new StringTokenizer(br.readLine());
    int v1 = Integer.parseInt(st.nextToken());
    int v2 = Integer.parseInt(st.nextToken());

    int[] dist = new int[E + 1];
    Arrays.fill(dist, INF);


    int v1Tov2 = ShortestPath(v1, v2, dist);
    if(v1Tov2 == -1) {
      System.out.print(-1);
      return;
    }
    Arrays.fill(dist, INF);
    int node1Tov1 = ShortestPath(1, v1, dist);
    if(node1Tov1 == -1) {
      System.out.print(-1);
      return;
    }
    Arrays.fill(dist, INF);
    int node1Tov2 = ShortestPath(1, v2, dist);
    if(node1Tov2 == -1) {
      System.out.print(-1);
      return;
    }
    Arrays.fill(dist, INF);
    int v1TonodeE = ShortestPath(v1, E, dist);
    if(v1TonodeE == -1) {
      System.out.print(-1);
      return;
    }
    Arrays.fill(dist, INF);
    int v2TonodeE = ShortestPath(v2, E, dist);
    if(v2TonodeE == -1) {
      System.out.print(-1);
      return;
    }

    int case1 = node1Tov1 + v2TonodeE;
    int case2 = node1Tov2 + v1TonodeE;

    if(case1 < case2) {
      System.out.print(case1 + v1Tov2);
      return;
    }
    else {
      System.out.print(case2 + v1Tov2);
      return;
    }

  }
  static int ShortestPath(int start, int end, int[] dist) {
    dist[start] = 0;

    PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

    q.add(new Node(start, 0));
    while(!q.isEmpty()) {
      Node curNode = q.poll();

      if(curNode.idx == end) {
        if(curNode.cost == INF) return -1;
        else return dist[curNode.idx];
      }

      if(dist[curNode.idx] < curNode.cost) continue;

       for(int i = 0; i < graph.get(curNode.idx).size(); i++) {
         Node adjNode = graph.get(curNode.idx).get(i);

         if(dist[adjNode.idx] > curNode.cost + adjNode.cost) {
           dist[adjNode.idx] = curNode.cost + adjNode.cost;
           q.add(new Node(adjNode.idx, dist[adjNode.idx]));
         }
       }

    }

    return -1;
  }
}

```