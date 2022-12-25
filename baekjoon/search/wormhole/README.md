# 웜홀

|시간 제한|	메모리 제한|	제출	|정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|2 초	|128 MB|	33915|	7756|	4784|	22.066%|

## 문제

때는 2020년, 백준이는 월드나라의 한 국민이다. 월드나라에는 N개의 지점이 있고 N개의 지점 사이에는 M개의 도로와 W개의 웜홀이 있다. (단 도로는 방향이 없으며 웜홀은 방향이 있다.) 웜홀은 시작 위치에서 도착 위치로 가는 하나의 경로인데, 특이하게도 도착을 하게 되면 시작을 하였을 때보다 시간이 뒤로 가게 된다. 웜홀 내에서는 시계가 거꾸로 간다고 생각하여도 좋다.

시간 여행을 매우 좋아하는 백준이는 한 가지 궁금증에 빠졌다. 한 지점에서 출발을 하여서 시간여행을 하기 시작하여 다시 출발을 하였던 위치로 돌아왔을 때, 출발을 하였을 때보다 시간이 되돌아가 있는 경우가 있는지 없는지 궁금해졌다. 여러분은 백준이를 도와 이런 일이 가능한지 불가능한지 구하는 프로그램을 작성하여라.

## 입력

첫 번째 줄에는 테스트케이스의 개수 TC(1 ≤ TC ≤ 5)가 주어진다. 그리고 두 번째 줄부터 TC개의 테스트케이스가 차례로 주어지는데 각 테스트케이스의 첫 번째 줄에는 지점의 수 N(1 ≤ N ≤ 500), 도로의 개수 M(1 ≤ M ≤ 2500), 웜홀의 개수 W(1 ≤ W ≤ 200)이 주어진다. 그리고 두 번째 줄부터 M+1번째 줄에 도로의 정보가 주어지는데 각 도로의 정보는 S, E, T 세 정수로 주어진다. S와 E는 연결된 지점의 번호, T는 이 도로를 통해 이동하는데 걸리는 시간을 의미한다. 그리고 M+2번째 줄부터 M+W+1번째 줄까지 웜홀의 정보가 S, E, T 세 정수로 주어지는데 S는 시작 지점, E는 도착 지점, T는 줄어드는 시간을 의미한다. T는 10,000보다 작거나 같은 자연수 또는 0이다.

두 지점을 연결하는 도로가 한 개보다 많을 수도 있다. 지점의 번호는 1부터 N까지 자연수로 중복 없이 매겨져 있다.

## 출력

TC개의 줄에 걸쳐서 만약에 시간이 줄어들면서 출발 위치로 돌아오는 것이 가능하면 YES, 불가능하면 NO를 출력한다.

## 예제 입력 1
```
2
3 3 1
1 2 2
1 3 4
2 3 1
3 1 3
3 2 1
1 2 3
2 3 4
3 1 8
```

## 예제 출력 1
```
NO
YES
```

## 오류 코드

**TLE**
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
    StringTokenizer st;

    int TC = Integer.parseInt(br.readLine());

    for (int count = 0; count < TC; count++) {
      st = new StringTokenizer(br.readLine());
      // 지점의 수, 도로의 개수, 웜홀의 개수
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      int W = Integer.parseInt(st.nextToken());

      // dist[] 초기화
      long[] dist = new long[N + 1];

      // graph 초기화
      ArrayList<ArrayList<Node>> graph = new ArrayList<>();
      for(int i = 0; i < N + 1; i++) {
        graph.add(new ArrayList<>());
      }

      // 도로의 정보
      for(int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        // 도로는 무방향
        graph.get(n1).add(new Node(n1, n2, cost));
        graph.get(n2).add(new Node(n2, n1, cost));
      }

      // 웜홀의 정보
      for(int i = 0; i < W; i++) {
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        // 웜홀은 방향 and 음수 Cost
        graph.get(n1).add(new Node(n1, n2, -cost));
      }

      boolean check = false;

      // 모든 지점을 다 확인(사직점 미지정)
      for(int i = 1; i < N + 1; i++) {
        if(BellmanFord(N, i, dist, graph)) {
          System.out.println("YES");
          check = true;
          break;
        }
      }
      // 음의 사이클 발견 안될 시
      if(!check) System.out.println("NO");
    }
  }
  static boolean BellmanFord(int N, int start, long[] dist, ArrayList<ArrayList<Node>> graph) {
    // 음의 사이클 발견시 true 아니면 false Return
    Arrays.fill(dist, INF);
    dist[start] = 0;

    // 벨만-포드 N - 1 회 실행
    for(int count = 0; count < N -1; count++) {
      for(int i = 1; i < graph.size(); i++) {
        if(dist[i] != INF) {
          for(int j = 0; j < graph.get(i).size(); j++) {
            // adjNode.v == i
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
          if(dist[adjNode.w] > dist[adjNode.v] + adjNode.cost) return true;
        }
      }
    }
    return false;
  }
}

```

**TLE**
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
    StringTokenizer st;

    int TC = Integer.parseInt(br.readLine());

    for (int count = 0; count < TC; count++) {
      st = new StringTokenizer(br.readLine());
      // 지점의 수, 도로의 개수, 웜홀의 개수
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      int W = Integer.parseInt(st.nextToken());

      // dist[] 초기화
      long[] dist = new long[N + 1];

      // graph 초기화
      ArrayList<Node> graph = new ArrayList<>();

      // 도로의 정보
      for(int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        // 도로는 무방향
        graph.add(new Node(n1, n2, cost));
        graph.add(new Node(n2, n1, cost));
      }

      // 웜홀의 정보
      for(int i = 0; i < W; i++) {
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        // 웜홀은 방향 and 음수 Cost
        graph.add(new Node(n1, n2, -cost));
      }

      boolean check = false;

      // 모든 지점을 다 확인(사직점 미지정)
      for(int i = 1; i < N + 1; i++) {
        if(BellmanFord(N, i, dist, graph)) {
          System.out.println("YES");
          check = true;
          break;
        }
      }
      // 음의 사이클 발견 안될 시
      if(!check) System.out.println("NO");
    }
  }
  static boolean BellmanFord(int N, int start, long[] dist, ArrayList<Node> graph) {
    // 음의 사이클 발견시 true 아니면 false Return
    Arrays.fill(dist, INF);
    dist[start] = 0;

    // 벨만-포드 N회 실행
    for(int count = 1; count < N + 1; count++) {
      for(int i = 0; i < graph.size(); i++) {
        Node adjNode = graph.get(i);
        if(dist[adjNode.v] != INF) {
          if(dist[adjNode.w] > dist[adjNode.v] + adjNode.cost) {
            if(count == N) return true;
            dist[adjNode.w] = dist[adjNode.v] + adjNode.cost;
          }
        }
      }
    }
    return false;
  }
}


```

## 나의 코드

해당 문제의 경우 출발 지점을 다시 돌아올 때 시간이 보다 되돌아 간 경우가 있는 경우에 "YES"를 출력하라고 했고, 모든 지점에서 검사를 하라 했다.

즉, **음의 사이클만 존재한다면** 다시 돌아오는 과정을 구현할 필요 없이 "YES"를 출력하면 된다.

중간에 **update가 일어 나지 않는 상황**에 무의미하게 반복문이 돌아가므로 break로 끊어주면서 문제를 해결했다.

**Bellman-Ford Algorithm**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
    StringTokenizer st;

    int TC = Integer.parseInt(br.readLine());

    for (int count = 0; count < TC; count++) {
      st = new StringTokenizer(br.readLine());
      // 지점의 수, 도로의 개수, 웜홀의 개수
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      int W = Integer.parseInt(st.nextToken());

      // dist[] 초기화
      long[] dist = new long[N + 1];

      // graph 초기화
      ArrayList<ArrayList<Node>> graph = new ArrayList<>();
      for(int i = 0; i < N + 1; i++) {
        graph.add(new ArrayList<>());
      }

      // 도로의 정보
      for(int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        // 도로는 무방향
        graph.get(n1).add(new Node(n1, n2, cost));
        graph.get(n2).add(new Node(n2, n1, cost));
      }

      // 웜홀의 정보
      for(int i = 0; i < W; i++) {
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        // 웜홀은 방향 and 음수 Cost
        graph.get(n1).add(new Node(n1, n2, -cost));
      }

      boolean check = false;

      // 모든 지점을 다 확인(사직점 미지정)
      for(int i = 1; i < N + 1; i++) {
        if(BellmanFord(N, i, dist, graph)) {
          System.out.println("YES");
          check = true;
          break;
        }
      }
      // 음의 사이클 발견 안될 시
      if(!check) System.out.println("NO");
    }
  }
  static boolean BellmanFord(int N, int start, long[] dist, ArrayList<ArrayList<Node>> graph) {
    // 음의 사이클 발견시 true 아니면 false Return
    Arrays.fill(dist, INF);
    dist[start] = 0;

    // 벨만-포드 N 회 실행
    for(int count = 1; count < N + 1; count++) {
      boolean update = false;
      for(int i = 1; i < graph.size(); i++) {
        if(dist[i] != INF) {
          for(int j = 0; j < graph.get(i).size(); j++) {
            // adjNode.v == i
            Node adjNode = graph.get(i).get(j);
            if(dist[adjNode.w] > dist[adjNode.v] + adjNode.cost) {
              if(count == N || dist[start] < 0) return true;
              dist[adjNode.w] = dist[adjNode.v] + adjNode.cost;
              update = true;
            }
          }
        }
      }
      if(!update) break;
    }
    return false;
  }
}
```

**SPFA**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int TC = Integer.parseInt(br.readLine());
    for(int count = 0; count < TC; count++) {

      st = new StringTokenizer(br.readLine());

      // 지점의 수, 도로의 개수, 웜홀의 개수
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      int W = Integer.parseInt(st.nextToken());

      ArrayList<ArrayList<Node>> graph = new ArrayList<>();
      for(int i = 0; i < N + 1; i++) {
        graph.add(new ArrayList<>());
      }

      // 도로 정보
      for(int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        graph.get(n1).add(new Node(n2, cost));
        graph.get(n2).add(new Node(n1, cost));
      }

      for(int i = 0; i < W; i++) {
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        graph.get(n1).add(new Node(n2, -cost));
      }

      boolean hasNegativeCycle = false;
      for(int i = 1; i < N + 1; i++) {
        int[] dist = new int[N + 1];
        if(SPFA(N, i, dist, graph)) {
          hasNegativeCycle = true;
          break;
        }
      }
      if(hasNegativeCycle) System.out.println("YES");
      else System.out.println("NO");
    }
  }

  static boolean SPFA(int N, int start, int[] dist, ArrayList<ArrayList<Node>> graph) {
    Arrays.fill(dist, INF);
    dist[start] = 0;

    boolean[] inQueue = new boolean[N + 1];
    int[] cycle = new int[N + 1];

    Queue<Node> q = new LinkedList<>();
    q.add(new Node(start, 0));
    inQueue[start] = true;

    while (!q.isEmpty()) {
      Node curNode = q.poll();
      inQueue[curNode.idx] = false;

      for(int i = 0; i < graph.get(curNode.idx).size(); i++) {
        Node adjNode = graph.get(curNode.idx).get(i);

        if(dist[adjNode.idx] > dist[curNode.idx] + adjNode.cost) {
          dist[adjNode.idx] = dist[curNode.idx] + adjNode.cost;
          cycle[adjNode.idx]++;
          if(cycle[adjNode.idx] >= N) return true;

          if(!inQueue[adjNode.idx]) {
            q.add(new Node(adjNode.idx, dist[adjNode.idx]));
            inQueue[adjNode.idx] = true;
          }
        }
      }
    }
    return false;
  }
}
```

아래 코드는 **오류 코드**이다.

이미 음의 사이클을 발견 했는데 break를 하지 않아서 메모리 초과가 생긴 경우다.


**MLE**

```java

  ...

      boolean hasNegativeCycle = false;
      for(int i = 1; i < N + 1; i++) {
        int[] dist = new int[N + 1];
        if(SPFA(N, i, dist, graph)) hasNegativeCycle = true;
      }
      if(hasNegativeCycle) System.out.println("YES");
      else System.out.println("NO");
    }
  }

  ...
```


