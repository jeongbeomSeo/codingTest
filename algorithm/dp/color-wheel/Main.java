import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static int MOD = 1000000003;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int K = Integer.parseInt(br.readLine());

    int[][] table = initDpTable(N, K);

    System.out.println((table[N - 1][K] + table[N - 3][K - 1]) % MOD);
  }
  private static int[][] initDpTable(int N, int K) {
    int[][] table = new int[N + 1][K + 1];

    for (int i = 1; i <= N; i++) {
      table[i][0] = 1;
      table[i][1] = i;
    }

    for (int i = 3; i <= N; i++) {
      for (int j = 2; j <= (i + 1) / 2 && j < K + 1; j++) {
        table[i][j] = (table[i - 1][j] + table[i - 2][j - 1]) % MOD;
      }
    }

    return table;
  }
}