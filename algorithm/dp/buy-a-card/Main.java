import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] costs = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        long[] dp = new long[N + 1];

        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            int cost = costs[i];

            for (int j = i; j <= N; j++) {
                if (dp[j - i] != INF) {
                    dp[j] = Math.max(dp[j], dp[j - i] + cost);
                }
            }
        }

        System.out.println(dp[N]);
    }
}
