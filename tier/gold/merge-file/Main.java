import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (T-- != 0) {
            int K = Integer.parseInt(br.readLine());

            int[] nums = new int[K];
            int[] prefixSum = new int[K + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }

            int[][] dp = new int[K][K];
            for (int len = 2; len <= K; len++) {
                for (int i = 0; i <= K - len; i++) {
                    int j = i + len - 1;
                    dp[i][j] = INF;
                    int sum = prefixSum[j + 1] - prefixSum[i];
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum);
                    }
                }
            }

            bw.write(dp[0][K - 1] + "\n");
        }
        bw.flush();
        bw.close();
    }
}