import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    long[] nums = new long[N + 1];
    for (int i = 1; i < N + 1; i++) {
      nums[i] = Long.parseLong(br.readLine());
    }

    int h = (int)Math.ceil(Math.log(N) / Math.log(2));
    int tree_size = (1 << (h + 1));

    long[] tree = new long[tree_size];

    init(nums, tree, 1, 1, N);

    while (M != 0 || K != 0) {
      st = new StringTokenizer(br.readLine());
      int cs = Integer.parseInt(st.nextToken());
      switch (cs) {
        case 1:
          int index = Integer.parseInt(st.nextToken());
          long val = Long.parseLong(st.nextToken());
          update(nums, tree, 1, index, val, 1, N);
          M--;
          break;
        case 2:
          int left = Integer.parseInt(st.nextToken());
          int right = Integer.parseInt(st.nextToken());
          long sum = query(tree, 1, 1, N, left, right);
          System.out.println(sum);
          K--;
          break;
      }
    }
  }

  static void init(long[] nums, long[] tree, int node, int start, int end) {
    if (start == end) {
      tree[node] = nums[start];
    }
    else {
      init(nums, tree, node * 2, start, (start + end) / 2);
      init(nums, tree, node * 2 + 1, (start + end) / 2 + 1, end);
      tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
  }

  static void update(long[] nums, long[] tree, int node, int index, long val, int start, int end) {
    if (index < start || end < index) {
      return;
    }
    if (start == end) {
      nums[index] = val;
      tree[node] = val;
      return;
    }
    update(nums, tree, node * 2, index, val, start, (start + end) / 2);
    update(nums, tree, node * 2 + 1, index, val, (start + end) / 2 + 1, end);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
  }

  static long query(long[] tree, int node, int start, int end, int left, int right) {
    if (right < start || end < left) {
      return 0;
    }
    if (left <= start && end <= right) {
      return tree[node];
    }
    long lsum = query(tree, node * 2, start, (start + end) / 2, left, right);
    long rsum = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
    return lsum + rsum;
  }
}
