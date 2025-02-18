import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];

        int res = minMaxAlgo(dp, N);
        System.out.println(res == 1 ? "SK" : "CY");
    }
    private static int minMaxAlgo(int[] dp, int N) {

        if (N == 1 || N == 3) {
            return dp[N] = 1;
        }

        if (dp[N] != 0) return dp[N];

        int res = -1;
        if (N > 3) res = Math.max(res, -minMaxAlgo(dp, N - 3));
        res = Math.max(res, -minMaxAlgo(dp, N - 1));

        return dp[N] = res;
    }
}
