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
