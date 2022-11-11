# 파티

|시간 제한|메모리 제한|제출|정답|맞힌 사람|정답 비율|
|---|---|---|---|---|---|
|1초|128 MB|31277|15477|10279|47.595%|

## 문제

N개의 숫자로 구분된 각각의 마을에 한 명의 학생이 살고 있다.

어느 날 이 N명의 학생이 X (1 ≤ X ≤ N)번 마을에 모여서 파티를 벌이기로 했다. 이 마을 사이에는 총 M개의 단방향 도로들이 있고 i번째 길을 지나는데 Ti(1 ≤ Ti ≤ 100)의 시간을 소비한다.

각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다. 하지만 이 학생들은 워낙 게을러서 최단 시간에 오고 가기를 원한다.

이 도로들은 단방향이기 때문에 아마 그들이 오고 가는 길이 다를지도 모른다. N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구일지 구하여라.

## 입력

첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 10,000), X가 공백으로 구분되어 입력된다. 두 번째 줄부터 M+1번째 줄까지 i번째 도로의 시작점, 끝점, 그리고 이 도로를 지나는데 필요한 소요시간 Ti가 들어온다. 시작점과 끝점이 같은 도로는 없으며, 시작점과 한 도시 A에서 다른 도시 B로 가는 도로의 개수는 최대 1개이다.

모든 학생들은 집에서 X에 갈수 있고, X에서 집으로 돌아올 수 있는 데이터만 입력으로 주어진다.

## 출력

첫 번째 줄에 N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력한다.

## 예제 입력 1

4 8 2

1 2 4

1 3 2

1 4 7

2 1 1

2 3 5

3 1 2

3 4 4

4 2 3

## 예제 출력 1

10

## 문제 풀이

해당 문제를 풀 떄 주의할 점은 오고 가는 것, 즉 왔다 가는 것에 유의 해야한다.

## 나의 코드

```java
// List로 Dijkstra를 구현

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  // 노드, 간선, 시작점
  static int INF = Integer.MAX_VALUE;
  static int N, M, X;

  static int[] EndtoStartDist;
  static int[] dist;


  static ArrayList<ArrayList<Node>> graph;

  static class Node {
    // 도작 지점과 Cost
    int idx;
    int cost;

    Node(int idx, int cost) {
      this.idx = idx;
      this.cost = cost;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // Vertex, Edge, Start Point
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    X = Integer.parseInt(st.nextToken());

    graph = new ArrayList<>();
    // 노드의 수만큼 초기화
    for(int i = 0; i < N + 1; i ++) {
      graph.add(new ArrayList<>());
    }

    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      // 시작 노드는 graph의 Index
      graph.get(n).add(new Node(w, cost));
    }

    EndtoStartDist = new int[N + 1];
    Arrays.fill(EndtoStartDist, INF);

    returnDijkstra(X);

    dist = new int[N + 1];
    for(int i = 1; i < N + 1; i++) {
      dist[i] = startDijkstra(i, X) + EndtoStartDist[i];
    }

    int max = Integer.MIN_VALUE;
    for(int i = 1; i < N + 1; i++) {
      if(max < dist[i]) max = dist[i];
    }
    System.out.println(max);
  }
  static void returnDijkstra(int start) {

    EndtoStartDist[start] = 0;
    boolean[] isVisited = new boolean[N + 1];

    for (int i = 0; i < N; i++) {
      int nodeIdx = 0;
      int nodeValue = INF;

      for (int j = 1; j < EndtoStartDist.length; j++) {
        if (!isVisited[j] && EndtoStartDist[j] < nodeValue) {
          nodeIdx = j;
          nodeValue = EndtoStartDist[j];
        }
      }

      isVisited[nodeIdx] = true;

      for (int j = 0; j < graph.get(nodeIdx).size(); j++) {
        Node adjNode = graph.get(nodeIdx).get(j);

        if (EndtoStartDist[adjNode.idx] > EndtoStartDist[nodeIdx] + adjNode.cost) {
          EndtoStartDist[adjNode.idx] = EndtoStartDist[nodeIdx] + adjNode.cost;
        }
      }
    }
  }

  static int startDijkstra(int start, int end) {

    int[] StarttoEndDist = new int[N + 1];
    Arrays.fill(StarttoEndDist, INF);
    StarttoEndDist[start] = 0;
    boolean[] isVisited = new boolean[N + 1];

    for (int i = 0; i < N; i++) {
      int nodeIdx = 0;
      int nodeValue = INF;

      for (int j = 1; j < StarttoEndDist.length; j++) {
        if (!isVisited[j] && StarttoEndDist[j] < nodeValue) {
          nodeIdx = j;
          nodeValue = StarttoEndDist[j];
        }
      }

      if(nodeIdx == end) {
       return  StarttoEndDist[nodeIdx];
      }

      isVisited[nodeIdx] = true;

      for (int j = 0; j < graph.get(nodeIdx).size(); j++) {
        Node adjNode = graph.get(nodeIdx).get(j);

        if (StarttoEndDist[adjNode.idx] > StarttoEndDist[nodeIdx] + adjNode.cost) {
          StarttoEndDist[adjNode.idx] = StarttoEndDist[nodeIdx] + adjNode.cost;
        }
      }
    }
    return  StarttoEndDist[end];
  }
}
```

```java
// 오직 Priority Queue로만 했을 경우

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static int INF = Integer.MAX_VALUE;
 static int V, E;
 static int[] dist;

 static ArrayList<ArrayList<Node>> graph;

 static class Node {
   int idx;
   int cost;

   Node(int idx, int cost) {
     this.idx = idx;
     this.cost = cost;
   }
 }

 public static void main(String[] args) throws IOException {
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   StringTokenizer st = new StringTokenizer(br.readLine());

   V = Integer.parseInt(st.nextToken());
   E = Integer.parseInt(st.nextToken());
   int partyNode = Integer.parseInt(st.nextToken());

   graph = new ArrayList<>();
   for(int i = 0 ; i < V + 1; i++) {
     graph.add(new ArrayList<>());
   }

   for(int i = 0; i < E; i++) {
     st = new StringTokenizer(br.readLine());
     int n1 = Integer.parseInt(st.nextToken());
     int n2 = Integer.parseInt(st.nextToken());
     int cost = Integer.parseInt(st.nextToken());

     graph.get(n1).add(new Node(n2, cost));
   }

   dist = new int[V + 1];
   Arrays.fill(dist, INF);

   Dijkstra(partyNode);

   int max = Integer.MIN_VALUE;
   for(int i = 1; i < dist.length; i++) {
     max = max < dist[i] + goParty(i, partyNode) ? dist[i] + goParty(i, partyNode) : max;
   }

   System.out.println(max);

 }
 static void Dijkstra(int start) {

   dist[start] = 0;
   PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

   q.offer(new Node(start, 0));

   while (!q.isEmpty()) {
     Node curNode = q.poll();

     if(dist[curNode.idx] < curNode.cost) continue;

     for(int i = 0; i < graph.get(curNode.idx).size(); i++) {
       Node adjNode = graph.get(curNode.idx).get(i);

       if(dist[adjNode.idx] > dist[curNode.idx] + adjNode.cost) {
         dist[adjNode.idx] = dist[curNode.idx] + adjNode.cost;
         q.offer(new Node(adjNode.idx, dist[adjNode.idx]));
       }
     }
   }
 }

 static int goParty(int start, int end) {
   PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

   int[] dist = new int[V + 1];
   Arrays.fill(dist, INF);
   dist[start] = 0;

   q.offer(new Node(start, 0));

   while (!q.isEmpty()) {
     Node curNode = q.poll();

     if(end == curNode.idx) {
       return dist[curNode.idx];
     }

     if(dist[curNode.idx] < curNode.cost) continue;

     for(int i = 0; i < graph.get(curNode.idx).size(); i++) {
       Node adjNode = graph.get(curNode.idx).get(i);

       if(dist[adjNode.idx] > dist[curNode.idx] + adjNode.cost) {
         dist[adjNode.idx] = dist[curNode.idx] + adjNode.cost;
         q.offer(new Node(adjNode.idx, dist[adjNode.idx]));
       }
     }
   }
   return dist[end];
 }
}
```

오히려 더 높은 메모리 사용과 더 많은 실행 시간이 나왔다.