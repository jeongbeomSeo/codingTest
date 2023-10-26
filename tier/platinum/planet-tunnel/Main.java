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

    Planet[] planets = new Planet[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int z = Integer.parseInt(st.nextToken());
      planets[i] = new Planet(x, y, z, i);
    }

    ArrayList<Edge> edges = new ArrayList<>();

    Arrays.sort(planets, (p1, p2) -> p1.x - p2.x);
    for (int i = 0; i < N - 1; i++) {
      edges.add(new Edge(planets[i].idx, planets[i + 1].idx, Math.abs(planets[i].x - planets[i + 1].x)));
    }

    Arrays.sort(planets, (p1, p2) -> p1.y - p2.y);
    for (int i = 0; i < N - 1; i++) {
      edges.add(new Edge(planets[i].idx, planets[i + 1].idx, Math.abs(planets[i].y - planets[i + 1].y)));
    }

    Arrays.sort(planets, (p1, p2) -> p1.z - p2.z);
    for (int i = 0; i < N - 1; i++) {
      edges.add(new Edge(planets[i].idx, planets[i + 1].idx, Math.abs(planets[i].z - planets[i + 1].z)));
    }

    Collections.sort(edges);

    int[] parent = new int[N];
    for (int i = 1; i < N; i++) parent[i] = i;

    int count = 0;
    int sum = 0;
    for (int i = 0; i < edges.size(); i++) {
      if (union_merge(parent, edges.get(i).src, edges.get(i).dst)) {
        count++;
        sum += edges.get(i).cost;
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
    else parent[x] = y;
    return true;
  }
}
class Edge implements Comparable<Edge>{
  int src;
  int dst;
  int cost;

  Edge(int src, int dst, int cost) {
    this.src = src;
    this.dst = dst;
    this.cost = cost;
  }

  @Override
  public int compareTo(Edge o) {
    return this.cost - o.cost;
  }
}

class Planet{
  int x;
  int y;
  int z;
  int idx;

  Planet(int x, int y, int z, int idx) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.idx = idx;
  }
}