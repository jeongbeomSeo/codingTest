import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    ArrayList<Integer>[] graph = initGraph(N);

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());

      int node1 = Integer.parseInt(st.nextToken());
      int node2 = Integer.parseInt(st.nextToken());

      graph[node1].add(node2);
      graph[node2].add(node1);
    }

    int size = (int)Math.ceil(Math.log(N + 1) / Math.log(2));
    int[][] parent = new int[N + 1][size];
    int[] level = new int[N + 1];
    boolean[] isVisited = new boolean[N + 1];
    isVisited[1] = true;
    paintParentTableByDfs(graph, parent, level, isVisited, 1, 0);
    updateParentTable(parent, N, size);

    int M = Integer.parseInt(br.readLine());
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      int node1 = Integer.parseInt(st.nextToken());
      int node2 = Integer.parseInt(st.nextToken());

      if (i != M - 1) {
        bw.write(getParentNode(parent, level, node1, node2, size) + "\n");
      } else {
        bw.write(String.valueOf(getParentNode(parent, level, node1, node2, size)));
      }
    }

    bw.flush();
    bw.close();
  }
  private static void updateParentTable(int[][] parent, int N, int size) {

    for (int i = 1; i < size; i++) {
      for (int node = 1; node < N + 1; node++) {
        parent[node][i] = parent[parent[node][i - 1]][i - 1];
      }
    }
  }
  private static int getParentNode(int[][] parent, int[] level, int node1, int node2, int size) {

    if (level[node1] > level[node2]) {
      int temp = node2;
      node2 = node1;
      node1 = temp;
    }

    for (int i = size - 1; i >= 0; i--) {
      if (level[node2] - level[node1] >= (1 << i)) {
        node2 = parent[node2][i];
      }
    }

    if (node1 == node2) return node1;

    for (int i = size - 1; i >= 0; i--) {
      if (parent[node1][i] != parent[node2][i]) {
        node1 = parent[node1][i];
        node2 = parent[node2][i];
      }
    }

    return parent[node1][0];
  }
  private static void paintParentTableByDfs(ArrayList<Integer>[] graph, int[][] parent, int[] level, boolean[] isVisited, int curNode, int curLevel) {

    level[curNode] = curLevel;

    for (int i = 0; i < graph[curNode].size(); i++) {
      int childNode = graph[curNode].get(i);

      if (!isVisited[childNode]) {
        isVisited[childNode] = true;
        parent[childNode][0] = curNode;
        paintParentTableByDfs(graph, parent, level, isVisited, childNode, curLevel + 1);
      }
    }

  }
  private static ArrayList<Integer>[] initGraph(int N) {
    ArrayList<Integer>[] graph = new ArrayList[N + 1];

    for (int i = 0; i < N + 1; i++) {
      graph[i] = new ArrayList<>();
    }

    return graph;
  }
}