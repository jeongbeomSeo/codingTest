import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  static long INF = Long.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());
    long[] dp = new long[101];
    int[] arr = {1, 10, 25, 100};

    Arrays.fill(dp, INF);
    dp[0] = 0;

    init_dp(dp, arr);

    for (int tc = 0; tc < T; tc++) {
       long K = Long.parseLong(br.readLine());

      System.out.println(coin_dp(dp, K));
     }
  }
  static void init_dp(long[] dp, int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = arr[i]; j < 101; j++) {
        dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
      }
    }
  }

  static long coin_dp(long[] dp, long K) {
    long ans = 0;

    while (K > 0) {
      ans += dp[(int)(K % 100)];
      K /= 100;
    }

    return ans;
  }
}