import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static final int MOD = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[N + 1][10];

        Arrays.fill(dp[1], 1);
        dp[1][0] = 0;

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                if (j > 0) {
                    dp[i][j] += dp[i - 1][j - 1];
                }

                if (j < 9) {
                    dp[i][j] += dp[i - 1][j + 1];
                }

                dp[i][j] %= MOD;
            }
        }

        long sum = 0L;
        for (int i = 0; i < 10; i++) sum += dp[N][i];

        System.out.println(sum % MOD);
    }
}
