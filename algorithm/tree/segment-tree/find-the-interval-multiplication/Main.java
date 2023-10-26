import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int MOD = 1000000007;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] nums = new int[N + 1];
    for (int i = 1; i < N + 1; i++) {
      nums[i] = Integer.parseInt(br.readLine());
    }

    int C = M + K;

    int h = (int)Math.ceil(Math.log(N) / Math.log(2));
    int tree_size = 1 << (h + 1);

    long[] tree = new long[tree_size];

    init(nums, tree, 1, 1, N);

    while (C != 0) {
      st = new StringTokenizer(br.readLine());
      int what = Integer.parseInt(st.nextToken());
      switch (what) {
        case 1:
          int idx = Integer.parseInt(st.nextToken());
          int val = Integer.parseInt(st.nextToken());
          update(tree, 1, idx, val, 1, N);
          break;
        case 2:
          int left = Integer.parseInt(st.nextToken());
          int right = Integer.parseInt(st.nextToken());
          bw.write(query(tree, 1, 1, N, left, right)+ "\n");
      }
      C--;
    }
    bw.flush();
    bw.close();
  }
  static void init(int[] nums, long[] tree, int node, int start, int end) {
    if (start == end) {
      tree[node] = nums[start];
    }
    else {
      init(nums, tree, node * 2, start, (start + end) / 2);
      init(nums, tree, node * 2 + 1, (start + end) / 2 + 1, end);
      tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD;
    }
  }

  static void update(long[] tree, int node, int idx, int val, int start, int end) {
    if (idx < start || end < idx) {
      return;
    }
    if (start == end) {
      tree[node] = val;
      return;
    }
    update(tree, node * 2, idx, val, start, (start + end) / 2);
    update(tree, node * 2 + 1, idx, val, (start + end) / 2 + 1, end);
    tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD;
  }

  static long query(long[] tree, int node, int start, int end, int left, int right) {
    if (right < start || end < left) {
      return 1;
    }
    if (left <= start && end <= right) {
      return tree[node];
    }
    long lmul = query(tree, node * 2, start, (start + end) / 2, left, right);
    long rmul = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
    return (lmul * rmul) % MOD;
  }
}
