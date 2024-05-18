import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[N + 1];

        System.out.println(factorial(N) * query(dp, N) % MOD);
    }
    private static long query(long[] dp, int N) {

        if (N == 0) return 1;
        else if (N == 1) return 0;

        if (dp[N] != 0) return dp[N];

        return dp[N] = (N - 1) * (query(dp, N - 2) + query(dp, N - 1)) % MOD;
    }
    private static long factorial(int N) {

        long result = 1L;
        for (int i = 2; i <= N; i++) {
            result *= i;
            result %= MOD;
        }

        return result;
    }
}