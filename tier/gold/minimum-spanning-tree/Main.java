import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int V = Integer.parseInt(st.nextToken());
    final int E = Integer.parseInt(st.nextToken());

    Edge[] edgeArray = new Edge[E];
    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());

      int node1 = Integer.parseInt(st.nextToken());
      int node2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      edgeArray[i] = new Edge(node1, node2, cost);
    }

    Arrays.sort(edgeArray);

    int[] parentTable = initParentTable(V);

    int connectionCount = 1;
    int minCost = 0;
    for (int i = 0; i < E; i++) {
      if (getRootParent(parentTable, parentTable[edgeArray[i].node1]) != getRootParent(parentTable, parentTable[edgeArray[i].node2])) {
        union(parentTable, edgeArray[i].node1, parentTable[edgeArray[i].node2]);
        minCost += edgeArray[i].cost;
        if (++connectionCount == E) break;
      }
    }

    System.out.println(minCost);
  }
  private static int getRootParent(int[] parent, int node) {
    if (parent[node] == node) return node;

    return getRootParent(parent, parent[node]);
  }
  private static void union(int[] parent, int node1, int node2) {
    int node1RootParent = getRootParent(parent, node1);
    int node2RootParent = getRootParent(parent, node2);

    if (node1RootParent != node2RootParent) {
      if (node1RootParent > node2RootParent) {
        parent[node1RootParent] = node2RootParent;
      }
      else parent[node2RootParent] = node1RootParent;
    }
  }
  private static int[] initParentTable(int V) {
    int[] parentTable = new int[V + 1];

    for (int i = 1; i <= V; i++) {
      parentTable[i] = i;
    }

    return parentTable;
  }
}
class Edge implements Comparable<Edge>{
  int node1;
  int node2;
  int cost;

  Edge (int node1, int node2, int cost) {
    this.node1 = node1;
    this.node2 = node2;
    this.cost = cost;
  }

  @Override
  public int compareTo(Edge o) {
    return this.cost - o.cost;
  }
}