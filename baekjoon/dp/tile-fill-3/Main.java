import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static int MOD = 1000000007;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    long[][] dp = new long[1000001][2];

    dp[0][0] = 1;
    dp[1][0] = 2;
    dp[2][0] = 4 + 3;
    dp[0][1] = 1;
    dp[1][1] = 3;
    dp[2][1] = 10;

    for (int i = 3; i <= N; i++) {
      long count = (dp[i - 1][0] * 2 + dp[i - 2][0] * 3) % MOD;

      count = (count + 2 * dp[i - 3][1]) % MOD;

      dp[i][0] = count;
      dp[i][1] = (dp[i - 1][1] + dp[i][0]) % MOD;
    }

    System.out.println(dp[N][0]);
  }
}