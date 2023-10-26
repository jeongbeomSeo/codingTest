import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int MAX_NUMBER = 2000000;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int h = (int)Math.ceil(Math.log(MAX_NUMBER) / Math.log(2));
    int tree_size = 1 << (h + 1);
    int[] tree = new int[tree_size];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int t = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());

      switch (t) {
        case 1:
          insert(tree, 1, 1, MAX_NUMBER, x);
          break;
        case 2:
          bw.write(query(tree, 1, 1, MAX_NUMBER, x)+"\n");
          break;
      }
    }
    bw.flush();
    bw.close();
  }
  static void insert(int[] tree, int node, int nodeLeft, int nodeRight, int target) {
    if (target < nodeLeft || nodeRight < target) {
      return;
    }

    if (nodeLeft == nodeRight) {
      tree[node] += 1;
      return;
    }

    insert(tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, target);
    insert(tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, target);

    tree[node] = tree[node * 2] + tree[node * 2 + 1];
  }

  static int query(int[] tree, int node, int nodeLeft, int nodeRight, int target) {
    tree[node]--;

    if (nodeLeft == nodeRight) {
      return nodeLeft;
    }

    if (tree[node * 2] >= target) {
      return query(tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, target);
    } else {
      return query(tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, target - tree[node * 2]);
    }
  }
}