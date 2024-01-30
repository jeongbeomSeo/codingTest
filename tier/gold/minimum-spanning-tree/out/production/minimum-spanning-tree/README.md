# 최소 스패닝 트리 

**골드 4**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|128 MB	|67040	|28830	|16286	|40.900%|

## 문제 

그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.

최소 스패닝 트리는, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리를 말한다.

## 입력 

첫째 줄에 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)가 주어진다. 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다. 이는 A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미이다. C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.

그래프의 정점은 1번부터 V번까지 번호가 매겨져 있고, 임의의 두 정점 사이에 경로가 있다. 최소 스패닝 트리의 가중치가 -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은 데이터만 입력으로 주어진다.

## 출력 

첫째 줄에 최소 스패닝 트리의 가중치를 출력한다.

## 예제 입력 1

```
3 3
1 2 1
2 3 2
1 3 3
```

## 예제 출력 1

```
3
```

## 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int V = Integer.parseInt(st.nextToken());
    int E = Integer.parseInt(st.nextToken());

    Edge[] edges = new Edge[E];
    for (int i = 0 ; i < E; i++) {
        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        edges[i] = new Edge(n1, n2, cost);
    }

    Arrays.sort(edges);
    int[] parent = new int[V + 1];
    for (int i = 1; i <= V; i++) {
      parent[i] = i;
    }

    int sum_cost = 0;
    int connecting_count = 0;
    for (int i = 0; i < E; i++) {
      Edge edge = edges[i];

      if (connecting_count == V - 1) break;
      if (union_find(parent, edge.n1) == union_find(parent, edge.n2)) continue;
      union_merge(parent, edge.n1, edge.n2);
      sum_cost += edge.cost;
      connecting_count++;
    }

    System.out.println(sum_cost);

  }
  static int union_find(int[] parent, int x) {
    if (parent[x] == x) return x;

    return parent[x] = union_find(parent, parent[x]);
  }
  static void union_merge(int[] parent, int n1, int n2) {
    n1 = union_find(parent, n1);
    n2 = union_find(parent, n2);

    if (n1 != n2) {
      parent[n1] = n2;
    }
  }
}

class Edge implements Comparable<Edge>{
  int n1;
  int n2;
  int cost;

  Edge(int n1, int n2, int cost) {
    this.n1 = n1;
    this.n2 = n2;
    this.cost = cost;
  }

  @Override
  public int compareTo(Edge o) {
    return this.cost - o.cost;
  }
}
```