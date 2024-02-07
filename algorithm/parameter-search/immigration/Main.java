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

    int[] nums = new int[N];

    int maxTime = Integer.MIN_VALUE;
    for (int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(br.readLine());

      maxTime = Math.max(maxTime, nums[i]);
    }

    long left = 0L;
    long right = maxTime * ((long)Math.ceil(M / N) + 1);

    System.out.println(getLowerBound(nums, left, right, M, N));
  }
  private static long getLowerBound(int[] nums, long left, long right, int target, int numsLen) {
    while (left < right) {
      long mid = (left + right) / 2;

      long count = 0;
      for (int i = 0; i < numsLen; i++) {
        count += mid / nums[i];
      }

      if (count < target) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return left;
  }
}