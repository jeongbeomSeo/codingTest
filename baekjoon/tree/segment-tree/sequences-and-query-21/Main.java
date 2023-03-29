import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int DEFAULT_VALUE = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] arr= new int[N + 1];

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i < N + 1; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int h = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
    int tree_size = 1 << h;

    int[] tree = new int[tree_size];
    int[] lazy = new int[tree_size];

    init(arr, tree, 1, 1, N);

    int M = Integer.parseInt(br.readLine());

    while (M-- > 0) {
      st = new StringTokenizer(br.readLine());
      int what = Integer.parseInt(st.nextToken());

      switch (what) {
        case 1:
          int left = Integer.parseInt(st.nextToken());
          int right = Integer.parseInt(st.nextToken());
          int diff = Integer.parseInt(st.nextToken());

          update_tree(tree, lazy, 1, 1, N, left, right, diff);
          break;
        case 2:
          while (st.hasMoreTokens()) {
            int target = Integer.parseInt(st.nextToken());
            int ans = query(tree, lazy, 1, 1, N, target);
            bw.write(ans+"\n");
          }
      }
    }
    bw.flush();
    bw.close();
  }
  static void init(int[] arr, int[] tree, int node, int nodeLeft, int nodeRight) {
    if (nodeLeft == nodeRight) {
      tree[node] = arr[nodeLeft];
    } else {
      init(arr, tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2);
      init(arr, tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight);
    }
  }

  static void update_laze(int[] tree, int[] lazy, int node, int nodeLeft, int nodeRight) {
    if (lazy[node] != 0) {
      tree[node] += lazy[node];
      if (nodeLeft != nodeRight) {
        lazy[node * 2] += lazy[node];
        lazy[node * 2 + 1] += lazy[node];
      }
      lazy[node] = 0;
    }
  }

  static void update_tree(int[] tree, int[] lazy, int node, int nodeLeft, int nodeRight, int left, int right, int diff) {
    update_laze(tree, lazy, node, nodeLeft, nodeRight);

    if (right < nodeLeft || nodeRight < left)
      return;

    if (left <= nodeLeft && nodeRight <= right) {
      tree[node] += diff;
      if (nodeRight != nodeLeft) {
        lazy[node * 2] += diff;
        lazy[node * 2 + 1] += diff;
      }
      return;
    }

    update_tree(tree, lazy, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, left, right, diff);
    update_tree(tree, lazy, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, left, right, diff);
  }

  static int query(int[] tree, int[] lazy, int node, int nodeLeft, int nodeRight, int target) {
    update_laze(tree, lazy, node, nodeLeft, nodeRight);

    if (target < nodeLeft || nodeRight < target) {
      return DEFAULT_VALUE;
    }

    if (nodeLeft == nodeRight) {
      return tree[node];
    }

    int lValue = query(tree, lazy, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, target);
    int rValue = query(tree, lazy, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, target);

    if (lValue != 0) return lValue;
    return rValue;
  }
}