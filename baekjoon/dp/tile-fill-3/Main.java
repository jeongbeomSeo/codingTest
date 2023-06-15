import java.util.Scanner;

public class Main {
  static int MOD = 1000000007;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();

    long[][] dp = new long[1000001][2];

    dp[0][0] = 0;
    dp[1][0] = 2;
    dp[2][0] = 7;
    dp[2][1] = 1;

    for (int i = 3; i <= N; i++) {
      dp[i][1] = (dp[i - 3][0] + dp[i - 1][1]) % MOD;

      dp[i][0] = (2 * dp[i - 1][0] + 3 * dp[i - 2][0] + 2 * dp[i][1]) % MOD;
    }

    System.out.println(dp[N][0]);
  }
}
