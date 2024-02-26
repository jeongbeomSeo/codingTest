import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    Number[] nums = new Number[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int num = Integer.parseInt(st.nextToken());

      nums[i] = new Number(i, num);
    }

    Arrays.sort(nums);

    System.out.println(query(nums, N));
  }
  private static long query(Number[] nums, int N) {

    int size = getSize(N);
    int[] segTable = new int[size];

    inputSegTable(segTable, 1, 0, N, nums[0].idx);

    long count = 0L;
    for (int i = 1; i < N; i++) {
      count += querySegTable(segTable, 1, 0, N, nums[i].idx, N);
      inputSegTable(segTable, 1, 0, N, nums[i].idx);
    }

    return count;
  }
  private static void inputSegTable(int[] segTable, int node, int start, int end, int idx) {
    if (idx < start || end < idx) return;
    else if (start == end) {
      segTable[node]++;
      return;
    }

    inputSegTable(segTable, 2 * node, start, (start + end) / 2, idx);
    inputSegTable(segTable, 2 * node + 1, (start + end) / 2 + 1, end, idx);

    segTable[node] = segTable[2 * node] + segTable[2 * node + 1];
  }
  private static int querySegTable(int[] segTable, int node, int start, int end, int left, int right) {
    if (right < start || end < left) return 0;
    else if (start <= left && right <= end) return segTable[node];

    int leftResult = querySegTable(segTable, 2 * node, start, (start + end) / 2, left, right);
    int rightResult = querySegTable(segTable, 2 * node + 1, (start + end) / 2 + 1, end, left, right);

    return leftResult + rightResult;
  }
  private static int getSize(int max) {

    int height = (int)Math.ceil(Math.log(max) / Math.log(2));

    return 1 << (height + 1);
  }
}
class Number implements Comparable<Number>{
  int idx;
  int num;

  Number(int idx, int num) {
    this.idx = idx;
    this.num = num;
  }

  @Override
  public int compareTo(Number o) {
    if (this.num - o.num != 0) return this.num - o.num;
    else {
      return this.idx - o.idx;
    }
  }
}