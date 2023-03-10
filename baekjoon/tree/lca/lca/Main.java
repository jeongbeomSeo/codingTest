import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] parentList = new int[N + 1];
    int[] level = new int[N + 1];

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < N + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int parNode = Integer.parseInt(st.nextToken());
      int childNode = Integer.parseInt(st.nextToken());

      graph.get(parNode).add(childNode);
      graph.get(childNode).add(parNode);
    }

    boolean[] isVisited = new boolean[N + 1];

    DFS(graph, level, parentList, isVisited, 1, 1);

    int M = Integer.parseInt(br.readLine());

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());

      if (n1 == n2) {
        bw.write(n1 + "\n");
        continue;
      }
      bw.write(LCA(parentList, level, n1, n2) + "\n");
    }
    bw.flush();
    bw.close();
  }
  static void DFS(ArrayList<ArrayList<Integer>> graph, int[] level, int[] parentList, boolean[] isVisited, int curLevel, int node) {
    isVisited[node] = true;
    level[node] = curLevel;

    for (int childNode : graph.get(node)) {
      if (!isVisited[childNode]) {
        DFS(graph, level, parentList, isVisited, curLevel + 1, childNode);
        parentList[childNode] = node;
      }
    }
  }

  static int LCA(int[] parentList, int[] level, int n1, int n2) {

    if (level[n1] > level[n2]) {
      int temp = n1;
      n1 = n2;
      n2 = temp;
    }
    while (level[n1] != level[n2]) {
      n2 = parentList[n2];
    }
    while (n1 > 1 && n1 != n2) {
      n1 = parentList[n1];
      n2 = parentList[n2];
    }
    return n1;
  }
}
