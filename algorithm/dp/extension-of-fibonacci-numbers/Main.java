import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MOD = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if (N == 0) {
            System.out.println(0+"\n"+0);
            return;
        }
        if (N > 0) {
            int[] dp = new int[N + 1];
            dp[0] = 0; dp[1] = 1;
            for (int i = 2; i <= N; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
            }

            System.out.println(1);
            System.out.println(dp[N] % MOD);
            return;
        }

        N = -N;
        int[] dp = new int[N + 1];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = (dp[i - 2] - dp[i - 1]) % MOD;
        }

        if (dp[N] < 0) System.out.println(-1);
        else if (dp[N] == 0) System.out.println(0);
        else System.out.println(1);
        System.out.println(Math.abs(dp[N]));

    }
}
