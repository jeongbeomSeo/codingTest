import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  private static final int MAXCOUNT = 65536 + 1;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int size = getSize(MAXCOUNT);
    int[] segTree = new int[size];

    int[] nums = new int[N];
    for (int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(br.readLine());
    }
    for (int i = 0; i < K - 1; i++) {
      update(segTree, 1, 0, MAXCOUNT, nums[i], true);
    }

    int medianIdx = (K + 1) / 2;
    long result = 0L;
    for (int i = K - 1; i < N; i++) {
      update(segTree, 1, 0, MAXCOUNT, nums[i], true);
      result += query(segTree, 1, 0, MAXCOUNT, medianIdx);
      update(segTree, 1, 0, MAXCOUNT, nums[i - (K - 1)], false);
    }

    System.out.println(result);
  }
  private static int query(int[] segTree, int node, int left, int right, int count) {
    if (left == right) {
      return left;
    }

    int mid = (left + right) / 2;
    if (segTree[node * 2] >= count) return query(segTree, 2 * node, left, mid, count);
    else {
      return query(segTree, 2 * node + 1, mid + 1, right, count - segTree[2 * node]);
    }
  }
  private static void update(int[] segTree, int node, int left, int right, int num, boolean isAdd) {
    if (num < left || right < num) return;
    else if (left == right) {
      if (isAdd) segTree[node]++;
      else segTree[node]--;
      return;
    }

    int mid = (left + right) / 2;
    update(segTree, 2 * node, left, mid, num, isAdd);
    update(segTree, 2 * node + 1, mid + 1, right, num, isAdd);
    segTree[node] = segTree[2 * node] + segTree[2 * node + 1];
  }
  private static int getSize(int maxCount) {
    int h = (int)Math.ceil(Math.log(maxCount) / Math.log(2));

    return 1 << (h + 1);
  }
}