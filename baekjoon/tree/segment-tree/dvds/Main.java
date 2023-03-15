import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MAX_VALUE;
  static int MIN = Integer.MIN_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
      st = new StringTokenizer(br.readLine());

      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());

      int h = (int) Math.ceil(Math.log(N) / Math.log(2));
      int tree_size = 1 << (h + 1);

      // Max, Min
      int[][] tree = new int[tree_size][2];

      initST(tree, 1, 0, N - 1);

      for (int i = 0; i < K; i++) {
        st = new StringTokenizer(br.readLine());

        int Q = Integer.parseInt(st.nextToken());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());

        if (n1 > n2) {
          int temp = n1;
          n1 = n2;
          n2 = temp;
        }
        switch (Q) {
          case 0:
            int val = peek(tree, 1, 0, N - 1, n1);
            val = update(tree, 1, 0, N - 1, n2, val);
            update(tree, 1, 0, N - 1, n1, val);
            break;
          case 1:
            int[] result = query(tree, 1, 0, N - 1, n1, n2);
            if (result[0] == n2 && result[1] == n1) bw.write("YES\n");
            else bw.write("NO\n");
            break;
          default:
            break;
        }
      }
      bw.flush();
    }
    bw.close();
  }

  static void initST(int[][] tree, int node, int start, int end) {
    if (start == end) {
      tree[node][0] = tree[node][1] = start;
    } else {
      initST(tree, node * 2, start, (start + end) / 2);
      initST(tree, node * 2 + 1, (start + end) / 2 + 1, end);

      tree[node][0] = Math.max(tree[node * 2][0], tree[node * 2 + 1][0]);
      tree[node][1] = Math.min(tree[node * 2][1], tree[node * 2 + 1][1]);
    }
  }

  static int peek(int[][] tree, int node, int start, int end, int idx) {
    if (idx < start || end < idx) {
      return -1;
    }
    if (start == end) {
      return tree[node][0];
    }
    int val = peek(tree, node * 2, start, (start + end) / 2, idx);
    if (val != -1) return val;
    val = peek(tree, node * 2 + 1, (start + end) / 2 + 1, end, idx);

    return val;
  }

  static int update(int[][] tree, int node, int start, int end, int idx, int val) {
    if (idx < start || end < idx) {
      return -1;
    }
    if (start == end) {
      int temp = tree[node][0];
      tree[node][0] = val;
      tree[node][1] = val;
      return temp;
    }
    int lVal = update(tree, node * 2, start, (start + end) / 2, idx, val);
    int rVal = update(tree, node * 2 + 1, (start + end) / 2 + 1, end, idx, val);

    tree[node][0] = Math.max(tree[node * 2][0], tree[node * 2 + 1][0]);
    tree[node][1] = Math.min(tree[node * 2][0], tree[node * 2 + 1][1]);

    if (lVal != -1) return lVal;
    return rVal;
  }

  static int[] query(int[][] tree, int node, int start, int end, int left, int right) {
    if (right < start || end < left) {
      return new int[]{MIN, INF};
    }
    if (left <= start && end <= right) {
      return tree[node];
    }
    int[] lQuery = query(tree, node * 2, start, (start + end) / 2, left, right);
    int[] rQuery = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);

    int max = Math.max(lQuery[0], rQuery[0]);
    int min = Math.min(lQuery[1], rQuery[1]);

    return new int[]{max, min};
  }
}