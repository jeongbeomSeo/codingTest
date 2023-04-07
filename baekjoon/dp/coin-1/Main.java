import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] coins = new int[N];
    for (int i = 0; i < N; i++) {
      coins[i] = Integer.parseInt(br.readLine());
    }

    int[] dp = new int[K + 1];
    dp[0] = 1;

    coin_dp(dp, coins, N, K);

    System.out.println(dp[K]);

  }
  static void coin_dp(int[] dp, int[] coins, int N, int K) {
    for (int i = 0; i < N; i++) {
      for (int j = coins[i]; j < K + 1; j++) {
        dp[j] += dp[j - coins[i]];
      }
    }
  }
}