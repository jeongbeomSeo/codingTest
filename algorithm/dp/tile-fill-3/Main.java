import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static int MOD = 1000000007;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    if (N == 1) System.out.println(2);
    else if (N == 2) System.out.println(7);
    else {
      long[][] table = initDpTable(N);

      activeDp(table, N);

      System.out.println(table[N][0]);
    }
  }
  private static void activeDp(long[][] table, int N) {

    for (int i = 3; i < N + 1; i++) {
      table[i][0] = ((2 * table[i - 1][0]) % MOD + (3 * table[i - 2][0]) % MOD + (2 * table[i - 3][1]) % MOD) % MOD;

      table[i][1] = (table[i - 1][1] + table[i][0]) % MOD;
    }
  }
  private static long[][] initDpTable(int N) {

    long[][] table = new long[N + 1][2];

    table[0][0] = table[0][1] = 1;
    table[1][0] = 2;
    table[1][1] = table[0][1] + table[1][0];
    table[2][0] = 7;
    table[2][1] = table[1][1] + table[2][0];

    return table;
  }
}