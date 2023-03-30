import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String[] input = br.readLine().split(" ");

    int N = Integer.parseInt(input[0]);
    int K = Integer.parseInt(input[1]);

    int h = (int)Math.ceil(Math.log(N) / Math.log(2));
    int tree_size = 1 << (h + 1);
    int[] tree = new int[tree_size];

    init(tree, 1, 1, N);

    int num = K;
    bw.write("<");
    for (int i = N; i >= 1;) {
      int findNum = query(tree, 1, 1, N, K);
      if (i != 1) bw.write(findNum + ", ");
      else {
        bw.write(findNum + ">");
        break;
      }
      K = (K + num - 2) % --i + 1;
    }
    bw.flush();
    bw.close();
  }
  static void init(int[] tree, int node, int nodeLeft, int nodeRight) {
    if (nodeLeft == nodeRight) {
      tree[node] = 1;
    }
    else {
      init(tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2);
      init(tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight);
      tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
  }

  static int query(int[] tree, int node, int nodeLeft, int nodeRight, int target) {
    tree[node]--;

    if (nodeLeft == nodeRight) {
      return nodeLeft;
    }

    if (target > tree[node * 2] + tree[node * 2 + 1]) {
      int sum = tree[node * 2] + tree[node * 2 + 1];
      while (target < sum) target -= sum;
    }
    if (tree[node * 2] >= target) {
      return query(tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, target);
    } else {
      return query(tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, target - tree[node * 2]);
    }
  }
}
