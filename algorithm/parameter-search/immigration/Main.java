import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[] times = new int[N];
    long MAX = Integer.MIN_VALUE;
    for (int i = 0; i < N; i++) {
      int time = Integer.parseInt(br.readLine());

      times[i] = time;
      MAX = Math.max(MAX, time);
    }

    System.out.println(lower_bound(times, 0L, MAX * (M / N + 1), M));
  }
  private static long lower_bound(int[] times, long left, long right, int target) {
    while (left < right) {
      long mid = (left + right) / 2;

      long count = getCount(times, mid);

      if (count < target) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left;
  }
  private static long getCount(int[] times, long mid) {

    long count = 0;

    for (int time : times) {
      count += mid / time;
    }

    return count;
  }
}