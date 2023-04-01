import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
      int N = Integer.parseInt(br.readLine());

      int[] coins = new int[N];

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
        coins[i] = Integer.parseInt(st.nextToken());
      }
      int M = Integer.parseInt(br.readLine());

      int[] dp = new int[M + 1];

      dp[0] = 1;

      coins_dp(dp, coins, N, M);

      System.out.println(dp[M]);

    }
  }
  static void coins_dp(int[] dp, int[] coins, int N, int M) {
    for (int i = 0; i < N; i++) {
      for (int j = coins[i]; j < M + 1; j++) {
        dp[j] += dp[j- coins[i]];
      }
    }
  }
}
