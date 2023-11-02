import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static int N;
  static int K;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    K = Integer.parseInt(br.readLine());

    System.out.println(lower_bound(0, N * N >= 0 ? N * N : (int)Math.pow(10, 9)));

  }
  private static int lower_bound(int left, int right) {
    while (left < right) {
      int mid = (left + right) / 2;

      long count = queryCount(mid);

      if (count < K) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left;
  }
  private static long queryCount(int target) {
    long count = 0;
    for (int i = 1; i <= N; i++) {
      int calc = target / i;

      if (calc == 0) break;

      count += Math.min(calc, N);
    }
    return count;
  }
}