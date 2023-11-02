import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int L = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());
    int X = Integer.parseInt(st.nextToken());

    int[] levels = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      levels[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(queryResult(levels, N, L, R, X));
  }
  private static int queryResult(int[] levels, int N, int L, int R, int X) {

    int count = 0;
    for (int i = 0; i < (1 << N); i++) {
      if (Integer.bitCount(i) >= 2) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int j = 0; j < N; j++) {
          if ((i & (1 << j)) != 0) {
            min = Math.min(min, levels[j]);
            max = Math.max(max, levels[j]);
            sum += levels[j];
          }
        }
        int diff = max - min;
        if (sum < L || sum > R) continue;
        if (diff < X) continue;

        count++;
      }
    }
    return count;
  }
}