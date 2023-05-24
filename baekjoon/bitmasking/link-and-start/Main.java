import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[][] sinergy = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        sinergy[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int start = (1 << N - 1) + 1;
    int end = 1 << N;
    int min = Integer.MAX_VALUE;
    for (; start < end; start++) {
      if (Integer.bitCount(start) <= N - 2) {
        int aSum = 0;
        int bSum = 0;
        for (int i = N - 1; i >= 1; i--) {
          if (((1 << i) & start) == (1 << i)) {
            for (int j = i - 1; j >= 0; j--) {
              if (((1 << j) & start) == (1 << j)) aSum += (sinergy[i][j] + sinergy[j][i]);
            }
          }
          else {
            for (int j = i - 1; j >= 0; j--) {
              if (((1 << j) & start) == 0) bSum += (sinergy[i][j] + sinergy[j][i]);
            }
          }
        }
        min = Math.min(min, Math.abs(aSum - bSum));
      }
    }
    System.out.println(min);
  }
}
