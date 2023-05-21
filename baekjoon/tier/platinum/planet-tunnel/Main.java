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