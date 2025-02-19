import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = covert(st.nextToken());

            if (n == 0) break;

            int[][] dp = new int[n + 1][m + 1];

            int[][] candies = new int[n + 1][2];
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());

                int c = Integer.parseInt(st.nextToken());
                int p = covert(st.nextToken());

                candies[i][0] = c;
                candies[i][1] = p;
            }

            for (int i = 1; i <= n; i++) {
                int c = candies[i][0];
                int p = candies[i][1];

                for (int j = 0; j <= m; j++) {
                    dp[i][j] = dp[i - 1][j];

                    if (j >= p) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][j - p] + c);
                    }
                }
            }

            bw.write(dp[n][m] + "\n");
        }

        bw.flush();
        bw.close();
    }
    private static int covert(String num) {
        return Integer.parseInt(num.replace(".", ""));
    }
}
