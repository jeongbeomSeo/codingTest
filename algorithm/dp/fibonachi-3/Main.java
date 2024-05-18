import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MOD = 1_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());

        int p = (int) (15 * Math.pow(10, (Math.log10(MOD)) - 1));

        long[] dp = new long[p];
        dp[1] = dp[2] = 1;
        for (int i = 3; i < p; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }

        System.out.println(dp[(int)(N % p)]);
    }
}