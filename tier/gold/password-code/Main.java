import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MOD = 1_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String num = br.readLine();

        int N = num.length();
        int[] dp = new int[N + 1];

        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            int now = num.charAt(i - 1) - '0';

            if (now >= 1 && now <= 9) {
                dp[i] = dp[i - 1];
            }

            if (i == 1) continue;

            int prev = (num.charAt(i - 2) - '0');

            if (prev == 0) continue;

            int value = prev * 10 + now;

            if (value >= 10 && value <= 26) {
                dp[i] += dp[i - 2];
            }

            dp[i] %= MOD;
        }

        System.out.println(dp[N]);
    }
}
