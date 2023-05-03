import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    long N = Long.parseLong(br.readLine());
    long K = Long.parseLong(br.readLine());

    System.out.println(binarySearch(1, N * N, N, K));

  }
  static long binarySearch(long left, long right, long N, long K) {

    while (left < right) {
      long mid = (left + right) / 2;

      long count = 0;
      for (int i = 1; i <= N; i++) {
        long divNum = mid / i;
        if (divNum == 0) break;
        count += Math.min(divNum, N);
      }

      if (count < K) left = mid + 1;
      else right = mid;
    }
    return left;
  }
}