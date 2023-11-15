# 오민식의 고민

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|128 MB|	10367	|2000|	1161|	16.614%|

## 문제

오민식은 세일즈맨이다. 오민식의 회사 사장님은 오민식에게 물건을 최대한 많이 팔아서 최대 이윤을 남기라고 했다.

오민식은 고민에 빠졌다.

어떻게 하면 최대 이윤을 낼 수 있을까?

이 나라에는 N개의 도시가 있다. 도시는 0번부터 N-1번까지 번호 매겨져 있다. 오민식의 여행은 A도시에서 시작해서 B도시에서 끝난다.

오민식이 이용할 수 있는 교통수단은 여러 가지가 있다. 오민식은 모든 교통수단의 출발 도시와 도착 도시를 알고 있고, 비용도 알고 있다. 게다가, 오민식은 각각의 도시를 방문할 때마다 벌 수 있는 돈을 알고있다. 이 값은 도시마다 다르며, 액수는 고정되어있다. 또, 도시를 방문할 때마다 그 돈을 벌게 된다.

오민식은 도착 도시에 도착할 때, 가지고 있는 돈의 액수를 최대로 하려고 한다. 이 최댓값을 구하는 프로그램을 작성하시오.

오민식이 버는 돈보다 쓰는 돈이 많다면, 도착 도시에 도착할 때 가지고 있는 돈의 액수가 음수가 될 수도 있다. 또, 같은 도시를 여러 번 방문할 수 있으며, 그 도시를 방문할 때마다 돈을 벌게 된다. 모든 교통 수단은 입력으로 주어진 방향으로만 이용할 수 있으며, 여러 번 이용할 수도 있다.

## 입력

첫째 줄에 도시의 수 N과 시작 도시, 도착 도시 그리고 교통 수단의 개수 M이 주어진다. 둘째 줄부터 M개의 줄에는 교통 수단의 정보가 주어진다. 교통 수단의 정보는 “시작 끝 가격”과 같은 형식이다. 마지막 줄에는 오민식이 각 도시에서 벌 수 있는 돈의 최댓값이 0번 도시부터 차례대로 주어진다.

N과 M은 50보다 작거나 같고, 돈의 최댓값과 교통 수단의 가격은 1,000,000보다 작거나 같은 음이 아닌 정수이다

## 출력

첫째 줄에 도착 도시에 도착할 때, 가지고 있는 돈의 액수의 최댓값을 출력한다. 만약 오민식이 도착 도시에 도착하는 것이 불가능할 때는 "gg"를 출력한다. 그리고, 오민식이 도착 도시에 도착했을 때 돈을 무한히 많이 가지고 있을 수 있다면 "Gee"를 출력한다.

## 예제 입력 1
```
5 0 4 7
0 1 13
1 2 17
2 4 20
0 3 22
1 3 4747
2 0 10
3 4 10
0 0 0 0 0
```

## 예제 출력 1
```
-32
```

## 예제 입력 2
```
5 0 4 5
0 1 10
1 2 10
2 3 10
3 1 10
2 4 10
0 10 10 110 10
```

## 예제 출력 2
```
Gee
```

## 예제 입력 3
```
3 0 2 3
0 1 10
1 0 10
2 1 10
1000 1000 47000
```

## 예제 출력 3
```
gg
```

## 예제 입력 4
```
2 0 1 2
0 1 1000
1 1 10
11 11
```

## 예제 출력 4
```
Gee
```

## 예제 입력 5
```
1 0 0 1
0 0 10
7
```

## 예제 출력 5
```
7
```

## 예제 입력 6
```
5 0 4 7
0 1 13
1 2 17
2 4 20
0 3 22
1 3 4747
2 0 10
3 4 10
8 10 20 1 100000
```

## 예제 출력 6
```
99988
```

## 풀이 방식

**기본 풀이 과정**
1. 모든 정보 입력 받기
2. Bellman-Ford 이용해서 음의 사이클 확인
3. 없다면 도착 도시에 도달했을 때 얻는 비용, 있다면 두가지 Case
   1. 음의 사이클이 있고 음의 사이클을 돌고 나서 도착 노드에 도달 가능
   2. 음의 사이클이 있고 음의 사이클을 돌고 나서 도착 노드에 도달 불가능

**고려할 점**
1. 주어진 문제에서 무한히 돈을 가질 수 있다면 "Gee"를 출력하라고 했고, 이것을 음의 사이클로 생각할 것이므로 코딩할 땐 모든 비용의 부호 반대
> 즉, 버스 이용 비용이 +, 도시 도착시 얻는 비용이 -
2. 시작 도시에서 얻을 수 있는 비용은 먼저 적용 후 시작
> 즉, dist[start] = cityFee[start]
3. Max값: N X M X Cost = 50 X 50 X 1,000,000 = 25억
> int형의 최대 값이 21억이므로 dist[]의 자료형으로 long자료형 사용
4. 음의 사이클이 도는 부분에서 도착 노드까지 도달할 수 있는 **경로만** Check하면 되므로 DFS 혹은 BFS 사용
   1. 가능하면 "Gee"출력
   2. 불가능면 dist[end]출력
> 단, 음의 사이클을 도는 모든 노드 확인
5. N번쨰 순회에서 dist값이 수전되는 부분이 곧, 음의 사이클을 도는 경로 혹은 거쳐가는 노드
> cf) https://4legs-study.tistory.com/26

## 풀이 코드 
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

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
  static int INF = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 도시의 수, 시작 도시, 도착 도시, 교통 수단의 개수
    int N = Integer.parseInt(st.nextToken());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    // graph 생성
    ArrayList<Node> graph = new ArrayList<>();
    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      graph.add(new Node(n1, n2, cost));
    }

    // 도시별 획득 비용 (-값을 넣어줌)
    int[] cityCost = new int[N];
    st = new StringTokenizer(br.readLine());
    for(int i = 0 ; i < cityCost.length; i++) {
      cityCost[i] = -Integer.parseInt(st.nextToken());
    }

    // 한 간선을 거칠 때 버스 비용을 소모하고 도시 도착시 돈을 획득 하는 시스템
    // 방향 그래프이기 때문에 w만 신경 써서 미리 cost 수정
    for(int i = 0; i < graph.size(); i++) {
      Node node = graph.get(i);
      node.cost += cityCost[node.w];
    }

    // dist[] 초기화
    long[] dist = new long[N];
    Arrays.fill(dist, INF);
    dist[start] = cityCost[start];

    // 출력 할 때 현재 버스 비용과 도시 도착시 획득 비용의 부호가 반대인 것에 주의
    if(BellmanFord(N, start, dist, graph, end)) {
      // start와 end가 같은데 움직이면 수익이 손해인 경우
      if(start == end && -cityCost[start] > dist[start] ) System.out.println(-cityCost[start]);
      // 도착 자체 불가인 경우
      else if(dist[end] == INF) System.out.println("gg");
      else System.out.println(-dist[end]);
    }
  }
  static boolean BellmanFord(int N, int start, long[] dist, ArrayList<Node> graph, int end) {
    // 경로 넣어 줄 ArrayList 생성
    ArrayList<LinkedHashSet<Integer>> path = new ArrayList<>();
    for(int i = 0; i < N; i++) {
      path.add(new LinkedHashSet<>());
    }
    // 출발 노드를 경로에 미리 넣어두면 안된다. 순환이 가능한 경우에 넣어줘야 한다.
    // 만약 넣어준다면 마지막 경로 확인하는 과정에서 adjNode.w가 시작 노드인 경우 오류 발생 가능성 있음.
    //path.get(start).add(start);

    // 벨만 포드 N번 순회
    for(int count = 0; count < N; count++) {
      boolean update = false;

      for(int i = 0; i < graph.size(); i++) {
        Node adjNode = graph.get(i);
        if(dist[adjNode.v] != INF) {
          if(dist[adjNode.w] > dist[adjNode.v] + adjNode.cost) {
            if(count == N -1) {
              // 음의 사이클이 존재하지만, end로 도착 못할 경우 고려
              if(dist[end] == INF) {
                System.out.println("gg");
                return false;
              }
              // 여기서 고려해야 할 것은 음의 사이클을 도는 경로에 end 지점으로 가능 경로가 포함 되느냐, 안되는냐가 Point!
              /*
              해당 코드의 문제는 음의 사이클이 도는 부분만을 신경 써야 되는데 모든 경로를 확인한 것이 문제
              즉, end까지는 무리없이 가다가 그 이후 뒤에서 순환된 경우 모든 경로를 확인하면 안된다.
              if(path.get(adjNode.w).contains(end)) {
                System.out.println("Gee");
                return false;
              }
              */

              if(path.get(end).contains(adjNode.w) || adjNode.w == end) {
                System.out.println("Gee");
                return false;
              }
            }
            dist[adjNode.w] = dist[adjNode.v] + adjNode.cost;
            // 최적 경로 업데이트
            if(!path.get(adjNode.w).isEmpty()) path.get(adjNode.w).clear();
            path.get(adjNode.v).forEach(idx -> path.get(adjNode.w).add(idx));
            path.get(adjNode.w).add(adjNode.w);
            update = true;
          }
        }
      }
      // 더이상 dist[]가 바뀌지 않는 경우
      if(!update) return true;
    }
  return true;
  }
}

```

**좀 더 직관적인 코드**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MIN_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    Edge[] edges = new Edge[M];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int src = Integer.parseInt(st.nextToken());
      int dst = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      edges[i] = new Edge(src, dst, cost);
    }

    int[] city = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      city[i] = Integer.parseInt(st.nextToken());
    }

    bellmanFord(edges, city, start, end, N, M);
  }
  private static void bellmanFord(Edge[] edges, int[] city, int start, int end, int N, int M) {

    long[] dist = initDistTable(city, N, start);

    for (int time = 0; time < N; time++) {

      for (int i = 0; i < M; i++) {
        Edge curEdge = edges[i];

        if (dist[curEdge.src] != INF && dist[curEdge.dst] < dist[curEdge.src] + getMoneyWhenGoNextCity(city, curEdge)) {
          dist[curEdge.dst] = dist[curEdge.src] + getMoneyWhenGoNextCity(city, curEdge);
        }
      }
    }

    if (!canGoTargetCity(dist, end)) {
      System.out.println("gg");
      return;
    }

    if (hasInfiniteCycle(edges, city, dist, end, N, M)) {
      System.out.println("Gee");
      return;
    }

    System.out.println(dist[end]);
  }
  private static boolean hasInfiniteCycle(Edge[] edges, int[] city, long[] dist, int end, int N, int M) {

    boolean[] isVisited = new boolean[N];
    for (int i = 0; i < M; i++) {
      Edge curEdge = edges[i];

      if (dist[curEdge.src] != INF && dist[curEdge.dst] < dist[curEdge.src] + getMoneyWhenGoNextCity(city, curEdge)) {
        if (!isVisited[curEdge.src] && dfs(edges, isVisited, curEdge.src, end, M)) {
          return true;
        }
      }
    }
    return false;
  }
  private static boolean dfs(Edge[] edges, boolean[] isVisited, int idx, int end, int M) {

    isVisited[idx] = true;

    Deque<Integer> stack = new ArrayDeque<>();
    stack.add(idx);

    while (!stack.isEmpty()) {
      int curIdx = stack.peek();

      if (curIdx == end) return true;

      boolean hasNearNode = false;
      for (int i = 0; i < M; i++) {
        if (edges[i].src == curIdx && !isVisited[edges[i].dst]) {
          stack.add(edges[i].dst);
          isVisited[edges[i].dst] = true;
          hasNearNode = true;
          break;
        }
      }
      if (!hasNearNode) stack.pop();
    }
    return false;
  }
  private static boolean canGoTargetCity(long[] dist, int end) {
    return dist[end] != INF;
  }
  private static int getMoneyWhenGoNextCity(int[] city, Edge edge) {
    return city[edge.dst] - edge.cost;
  }
  private static long[] initDistTable(int[] city, int N, int start) {

    long[] dist = new long[N];

    Arrays.fill(dist, INF);

    dist[start] = city[start];
    return dist;
  }
}
class Edge {
  int src;
  int dst;
  int cost;

  Edge (int src, int dst, int cost) {
    this.src = src;
    this.dst = dst;
    this.cost = cost;
  }
}
```