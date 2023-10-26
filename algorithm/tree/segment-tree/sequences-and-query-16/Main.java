import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[] nums = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i < N + 1; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    int h = (int)Math.ceil(Math.log(N) / Math.log(2));
    int tree_size = 1 << (h + 1);

    int[][] tree = new int[tree_size][2];

    int M = Integer.parseInt(br.readLine());

    init(nums, tree, 1, 1, N);

    for (int i = 0; i < M; i++) {
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
          bw.write(query(tree, 1, 1, N, left, right)[1] + "\n");
      }
    }
    bw.flush();
    bw.close();
  }
  static void init(int[] nums, int[][] tree, int node, int start, int end) {
    if (start == end) {
      tree[node][0] = nums[start];
      tree[node][1] = start;
    }
    else {
      init(nums, tree, node * 2, start, (start + end) / 2);
      init(nums, tree, node * 2 + 1, (start + end) / 2 + 1, end);
      calc(tree, node);
    }
  }
  static void update(int[][] tree, int node, int idx, int val, int start, int end){
    if (idx < start || end < idx) {
      return;
    }
    if (start == end) {
      tree[node][0] = val;
      return;
    }
    update(tree, node * 2, idx, val, start, (start + end) / 2);
    update(tree, node * 2 + 1, idx, val, (start + end) / 2 + 1, end);
    calc(tree, node);
  }

  static int[] query(int[][] tree, int node, int start, int end, int left, int right) {
    if (right < start || end < left) {
      return new int[]{INF, 0};
    }
    if (left <= start && end <= right) {
      return tree[node];
    }
    int[] lmin = query(tree, node * 2, start, (start + end) / 2, left, right);
    int[] rmin = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
    if (lmin[0] < rmin[0]) {
      return lmin;
    }
    else if(lmin[0] > rmin[0]) {
      return rmin;
    }
    else {
      if (lmin[1] < rmin[1]) return lmin;
      else return rmin;
    }
  }

  static void calc(int[][] tree, int node) {
    if (tree[node * 2][0] < tree[node * 2 + 1][0]) {
      tree[node][0] = tree[node * 2][0];
      tree[node][1] = tree[node * 2][1];
    } else if (tree[node * 2][0] > tree[node * 2 + 1][0]) {
      tree[node][0] = tree[node * 2 + 1][0];
      tree[node][1] = tree[node * 2 + 1][1];
    } else {
      if (tree[node * 2][1] < tree[node * 2 + 1][1]) {
        tree[node][0] = tree[node * 2][0];
        tree[node][1] = tree[node * 2][1];
      } else {
        tree[node][0] = tree[node * 2 + 1][0];
        tree[node][1] = tree[node * 2 + 1][1];
      }
    }
  }
}
