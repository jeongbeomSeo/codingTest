import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int K = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());

    int[] lanCables = new int[K];
    long max = 0;
    for (int i = 0; i < K; i++) {
      lanCables[i] = Integer.parseInt(br.readLine());
      max = Math.max(lanCables[i], max);
    }

    max++;

    System.out.println(binarySearch(lanCables, N, max));

  }
  static long binarySearch(int[] lanCables, int N, long max) {
    long min = 0;

    while (min < max) {
      long mid = (min + max) / 2;

      long count = 0;
      for (int lancable : lanCables) {
        count += lancable / mid;
      }

      if (count >= N) {
        min = mid + 1;
      }
      else
         max = mid;
    }
    return min - 1;
  }
}