import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] nums = new int[N + 1];
    for (int i = 1; i < N + 1; i++) {
      nums[i] = Integer.parseInt(br.readLine());
    }

    int h = (int)Math.ceil(Math.log(N) / Math.log(2));
    int tree_size = 1 << (h + 1);

    int[] tree = new int[tree_size];

    init(nums, tree, 1, 1, N);

    for(int i = 0 ; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken());
      int right = Integer.parseInt(st.nextToken());
      bw.write(query(tree, 1, 1, N, left, right) + "\n");
    }

    bw.flush();
    bw.close();
  }
  static void init(int[] nums, int[] tree, int node, int start, int end) {
    if (start == end) {
      tree[node] = nums[start];
    }
    else {
      init(nums, tree, node * 2, start, (start + end) / 2);
      init(nums, tree, node * 2 + 1, (start + end) / 2 + 1, end);
      tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
    }
  }

  static int query(int[] tree, int node, int start, int end, int left, int right) {
    if (right < start || end < left) {
      return INF;
    }
    if (left <= start && end <= right) {
      return tree[node];
    }
    int lmin = query(tree, node * 2, start, (start + end) / 2, left, right);
    int rmin = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
    return Math.min(lmin, rmin);
  }
}