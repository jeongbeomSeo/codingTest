import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] nums = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(nums);

    int M = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      int target = Integer.parseInt(st.nextToken());
      bw.write(
              String.valueOf( upper_bound_binarySearch(nums, target)
                      - lower_bound_binarySearch(nums, target))

      );
      if (i != M - 1) bw.write(" ");
    }
    bw.flush();
    bw.close();
  }
  static int lower_bound_binarySearch(int[] nums, int target) {
    int lo = 0;
    int hi = nums.length;

    while (lo < hi) {
      int mid = (lo + hi) / 2;

      if (nums[mid] < target) lo = mid + 1;
      else hi = mid;
    }
    return lo;
  }
  static int upper_bound_binarySearch(int[] nums, int target) {
    int lo = 0;
    int hi = nums.length;

    while (lo < hi) {
      int mid = (lo + hi) / 2;

      if (nums[mid] <= target) lo = mid + 1;
      else hi = mid;
    }
    return lo;
  }
}