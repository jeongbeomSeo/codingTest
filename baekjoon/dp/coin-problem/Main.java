import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());

    int[] dp = new int[101];
    dp[0] = 0;
    coins_dp(dp);

    for (int tc = 0; tc < T; tc++) {
      long N = Long.parseLong(br.readLine());

      int count = 0;
      while (N != 0) {
        count += dp[(int)(N % 100)];
        N /= 100;
      }
      System.out.println(count);
    }
  }
  static void coins_dp(int[] dp) {
    for (int i = 1; i <= 100; i++) {
      dp[i] = dp[i - 1] + 1;
      if (i >= 10) dp[i] = Math.min(dp[i], dp[i - 10] + 1);
      if (i >= 25) dp[i] = Math.min(dp[i], dp[i - 25] + 1);
    }
  }
}