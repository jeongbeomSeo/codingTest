# 행성 터널 

**플래티넘 5**

|시간 제한	|메모리 제한|	제출|	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초|	128 MB|	21329	|7797	|5443|	34.755%|

## 문제 

때는 2040년, 이민혁은 우주에 자신만의 왕국을 만들었다. 왕국은 N개의 행성으로 이루어져 있다. 민혁이는 이 행성을 효율적으로 지배하기 위해서 행성을 연결하는 터널을 만들려고 한다.

행성은 3차원 좌표위의 한 점으로 생각하면 된다. 두 행성 A(xA, yA, zA)와 B(xB, yB, zB)를 터널로 연결할 때 드는 비용은 min(|xA-xB|, |yA-yB|, |zA-zB|)이다.

민혁이는 터널을 총 N-1개 건설해서 모든 행성이 서로 연결되게 하려고 한다. 이때, 모든 행성을 터널로 연결하는데 필요한 최소 비용을 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 행성의 개수 N이 주어진다. (1 ≤ N ≤ 100,000) 다음 N개 줄에는 각 행성의 x, y, z좌표가 주어진다. 좌표는 -109보다 크거나 같고, 109보다 작거나 같은 정수이다. 한 위치에 행성이 두 개 이상 있는 경우는 없다. 

## 출력 

첫째 줄에 모든 행성을 터널로 연결하는데 필요한 최소 비용을 출력한다.

## 예제 입력 1

```
5
11 -15 -15
14 -5 -15
-1 -1 -5
10 -4 -1
19 -4 19
```

## 예제 출력 1

```
4
```

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    Point[] points = new Point[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int z = Integer.parseInt(st.nextToken());

      points[i] = new Point(i, x, y, z);
    }

    ArrayList<Edge> edges = new ArrayList<>();

    Arrays.sort(points, (p1, p2) -> p1.x - p2.x);
    for (int i = 0; i < N - 1; i++) {
      edges.add(new Edge(points[i].idx, points[i + 1].idx, Math.abs(points[i].x - points[i + 1].x)));
    }

    Arrays.sort(points, (p1, p2) -> p1.y - p2.y);
    for (int i = 0; i < N - 1; i++) {
      edges.add(new Edge(points[i].idx, points[i + 1].idx, Math.abs(points[i].y - points[i + 1].y)));
    }

    Arrays.sort(points, (p1, p2) -> p1.z - p2.z);
    for (int i = 0; i < N - 1; i++) {
      edges.add(new Edge(points[i].idx, points[i + 1].idx, Math.abs(points[i].z - points[i + 1].z)));
    }

    Collections.sort(edges, (e1, e2) -> e1.cost - e2.cost);

    int[] parent = new int[N];
    for (int i = 1; i < N; i++) parent[i] = i;

    int sum = 0;
    int count = 0;
    for (int i = 0; i < edges.size(); i++) {
      if (union_merge(parent, edges.get(i).src, edges.get(i).dst)) {
        sum += edges.get(i).cost;
        count++;
        if (count == N - 1) break;
      }
    }
    System.out.println(sum);
  }
  static int union_find(int[] parent, int x) {
    if (parent[x] == x) return x;

    return parent[x] = union_find(parent, parent[x]);
  }
  static boolean union_merge(int[] parent, int x, int y) {
    x = union_find(parent, x);
    y = union_find(parent, y);

    if (x == y) return false;
    parent[x] = y;
    return true;
  }
}
class Edge {
  int src;
  int dst;
  int cost;

   Edge(int src, int dst, int cost) {
     this.src = src;
     this.dst = dst;
     this.cost = cost;
   }
}
class Point {
  int idx;
  int x;
  int y;
  int z;

  Point(int idx, int x, int y, int z) {
    this.idx = idx;
    this.x = x;
    this.y = y;
    this.z = z;
  }
}
```