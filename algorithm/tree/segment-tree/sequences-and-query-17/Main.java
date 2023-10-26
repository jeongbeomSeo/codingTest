import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[] nums = new int[N + 1];

    StringTokenizer st = new StringTokenizer(br.readLine());

    int idx = 1;
    while (st.hasMoreTokens()) {
      nums[idx++] = Integer.parseInt(st.nextToken());
    }

    int h = (int)Math.ceil(Math.log(N) / Math.log(2));
    int tree_size = 1 << (h + 1);

    int[] tree = new int[tree_size];

    init(nums, tree, 1, 1, N);
    int M = Integer.parseInt(br.readLine());

    while (M != 0) {
      st = new StringTokenizer(br.readLine());
      int cs = Integer.parseInt(st.nextToken());
      switch (cs) {
        case 1:
          int index = Integer.parseInt(st.nextToken());
          int val = Integer.parseInt(st.nextToken());
          update(tree, 1, index, val, 1, N);
          break;
        case 2:
          int left = Integer.parseInt(st.nextToken());
          int right = Integer.parseInt(st.nextToken());
          int min = query(tree, 1, 1, N, left, right, INF);
          System.out.println(min);
          break;
      }
      M--;
    }


  }

  static void init(int[] nums, int[] tree, int node, int start, int end) {
    if (start == end) {
      tree[node] = nums[start];
    }
    else {
      init(nums, tree, node * 2, start, (start + end) / 2);
      init(nums, tree, node * 2 + 1, (start + end) / 2 + 1, end);
      tree[node] = Math.min(tree[node* 2], tree[node * 2 + 1]);
    }
  }

  static void update(int[] tree, int node, int index, int val, int start, int end) {
    if (index < start || end < index) {
      return;
    }
    if (start == end) {
      tree[node] = val;
      return;
    }
    update(tree, node * 2, index, val, start, (start + end) / 2);
    update(tree, node * 2 + 1, index, val, (start + end) / 2 + 1, end);
    tree[node] = Math.min(tree[node* 2] , tree[node * 2 + 1]);
  }

  static int query(int[] tree, int node, int start, int end, int left, int right, int min) {
    if (right < start || end < left) {
      return INF;
    }
    if (left <= start && end <= right) {
      return tree[node];
    }
    int lQuery = query(tree, node * 2, start, (start + end) / 2, left, right, min);
    int rQuery = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right, min);
    min = Math.min(min, Math.min(lQuery, rQuery));
    return min;
  }
}
