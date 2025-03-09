import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] nums = new int[N + 1][2];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());

            nums[i][0] = Integer.parseInt(st.nextToken());
            nums[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][T + 1];

        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < T + 1; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j - nums[i][0] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - nums[i][0]] + nums[i][1]);
                }
            }
        }

        System.out.println(dp[N][T]);
    }

}
