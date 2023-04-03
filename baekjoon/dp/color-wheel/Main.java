import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static int MOD = 1000000003;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[] dp = new int[N + 1];

    int K = Integer.parseInt(br.readLine());

    color_wheel_dp(dp, N, K);

    System.out.println(dp[N]);
  }

  static void color_wheel_dp(int[] dp, int N, int K) {
    if (N / 2 < K) return;

    dp[2 * K] = 2;
    int plusNum = 3;
    for (int i = 2 * K + 1; i <= N; i++) {
      dp[i] = dp[i - 1] + plusNum++;
    }
  }
}
