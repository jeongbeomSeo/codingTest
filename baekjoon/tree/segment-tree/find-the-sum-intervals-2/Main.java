import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static long DEFAULT_VALUE = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    long[] arr = new long[N + 1];

    for (int i = 1; i < N + 1; i++) {
      arr[i] = Long.parseLong(br.readLine());
    }

    int h = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
    int tree_size = 1 << h;
    long[] tree = new long[tree_size];
    long[] lazy = new long[tree_size];

    init(arr, tree, 1, 1, N);

    while (M > 0 || K > 0) {
      st = new StringTokenizer(br.readLine());
      int what = Integer.parseInt(st.nextToken());
      int left = Integer.parseInt(st.nextToken());
      int right = Integer.parseInt(st.nextToken());
      if (left > right) {
        int temp = left;
        left = right;
        right = temp;
      }

      long diff = 0;
      if (what == 1) diff = Long.parseLong(st.nextToken());

      switch (what) {
        case 1:
          update_range(tree, lazy, 1, 1, N, left, right, diff);
          M--;
          break;
        case 2:
          long sum = query(tree, lazy, 1, 1, N, left, right);
          bw.write(sum+"\n");
          K--;
          break;
      }
    }
    bw.flush();
    bw.close();
   }
   static void init(long[] arr, long[] tree, int node, int nodeLeft, int nodeRight) {
      if (nodeLeft == nodeRight) {
        tree[node] = arr[nodeLeft];
      } else {
        init(arr, tree, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2);
        init(arr, tree, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
      }
   }

   static void update_laze(long[] tree, long[] lazy, int node, int nodeLeft, int nodeRight) {
      if (lazy[node] != 0) {
        tree[node] += (nodeRight - nodeLeft + 1) * lazy[node];
        if (nodeLeft != nodeRight) {
          lazy[node * 2] += lazy[node];
          lazy[node * 2 + 1] += lazy[node];
        }
        lazy[node] = 0;
      }
   }

  static void update_range(long[] tree, long[] lazy, int node, int nodeLeft, int nodeRight, int left, int right, long diff) {
    update_laze(tree, lazy, node, nodeLeft, nodeRight);
    if (right < nodeLeft || nodeRight < left) {
      return;
    }

    if (left <= nodeLeft && nodeRight <= right) {
      tree[node] += (nodeRight - nodeLeft + 1) * diff;
      if (nodeLeft != nodeRight) {
        lazy[node * 2] += diff;
        lazy[node * 2 + 1] += diff;
      }
      return;
    }

    update_range(tree, lazy, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, left, right, diff);
    update_range(tree, lazy, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, left, right, diff);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
  }

  static long query(long[] tree, long[] lazy, int node, int nodeLeft, int nodeRight, int left, int right) {
    update_laze(tree, lazy, node, nodeLeft, nodeRight);

    if (right < nodeLeft || nodeRight < left) {
      return DEFAULT_VALUE;
    }

    if (left <= nodeLeft && nodeRight <= right) {
      return tree[node];
    }

    long lSum = query(tree, lazy, node * 2, nodeLeft, (nodeLeft + nodeRight) / 2, left, right);
    long rSum = query(tree, lazy, node * 2 + 1, (nodeLeft + nodeRight) / 2 + 1, nodeRight, left, right);

    return lSum + rSum;
  }
}
