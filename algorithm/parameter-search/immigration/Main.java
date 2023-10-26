import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] oprTime = new int[N];
    long max = 0;
    for (int i = 0; i < N; i++) {
      oprTime[i] = Integer.parseInt(br.readLine());
      max = Math.max(max, oprTime[i]);
    }

    Arrays.sort(oprTime);

    System.out.println(binarySearch(oprTime, 1, max * (M / N + 1), N, M));
  }
  static long binarySearch(int[] oprTime, long left, long right, int N, int M) {
    while (left < right) {
      long mid = (left + right) / 2;

      long sum = 0;
      for (int i = 0; i < N; i++) {
        sum += mid / oprTime[i];

        if (sum >= M) break;
      }

      if (sum < M) left = mid + 1;
      else right = mid;
    }
    return left;
  }
}