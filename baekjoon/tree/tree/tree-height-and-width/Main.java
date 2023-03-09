import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int col = 1;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[] isRootNode = new int[N + 1];
    int[][] graph = new int[N + 1][2];
    for (int i = 1 ; i <= N; i++) {
      st = new StringTokenizer(br.readLine());

      int parNode = Integer.parseInt(st.nextToken());
      int leftNode = Integer.parseInt(st.nextToken());
      int rightNode = Integer.parseInt(st.nextToken());

      if (leftNode != -1) isRootNode[leftNode]++;
      if (rightNode != -1) isRootNode[rightNode]++;

      graph[parNode][0] = leftNode;
      graph[parNode][1] = rightNode;
    }

    int rootNode = 0;
    for (int i = 1; i <= N; i++) {
      if (isRootNode[i] == 0) {
        rootNode = i;
        break;
      }
    }

    ArrayList<Node>[] level = new ArrayList[N + 1];
    for (int i = 0 ; i < N + 1; i++) {
      level[i] = new ArrayList<>();
    }

    inOrder(graph, rootNode, 1, level);

    int i = 1;
    int maxLevel = 1;
    int maxWidth = 1;
    while (i <= N && !level[i].isEmpty()) {
      int size = level[i].size();
      if (size >= 2) {
        int width = level[i].get(size - 1).col - level[i].get(0).col + 1;
        if (maxWidth < width) {
          maxLevel = i;
          maxWidth = width;
        }
      }
      i++;
    }

    System.out.print(maxLevel + " " + maxWidth);
  }
  static void inOrder(int[][] graph, int node, int row, ArrayList<Node>[] level) {
    if(node != -1) {
      inOrder(graph, graph[node][0], row + 1, level);
      level[row].add(new Node(node, col++));
      inOrder(graph, graph[node][1], row + 1, level);
    }
  }

}

class Node {
  int val;
  int col;

  Node(int val, int col) {
    this.val = val;
    this.col = col;
  }
}
