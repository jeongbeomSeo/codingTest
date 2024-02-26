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

    long count = 0L;
    for (int i = 0; i < N; i++) {
      count += querySegTable(segTable, 1, 0, N - 1, nums[i].idx + 1, N - 1);
      inputSegTable(segTable, 1, 0, N - 1, nums[i].idx);
    }

    return count;
  }
  private static void inputSegTable(int[] segTable, int node, int left, int right, int idx) {
    if (idx < left || right < idx) return;
    else if (left == right) {
      segTable[node] = 1;
      return;
    }

    int mid = (left + right) / 2;
    inputSegTable(segTable, 2 * node, left, mid, idx);
    inputSegTable(segTable, 2 * node + 1, mid + 1, right, idx);

    segTable[node] = segTable[2 * node] + segTable[2 * node + 1];
  }
  private static int querySegTable(int[] segTable, int node, int left, int right, int start, int end) {
    if (end < left || right < start) return 0;
    else if (start <= left && right <= end) return segTable[node];

    int mid = (left + right) / 2;
    int leftResult = querySegTable(segTable, 2 * node, left, mid, start, end);
    int rightResult = querySegTable(segTable, 2 * node + 1, mid + 1, right, start, end);

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
    return this.num - o.num;
  }
}