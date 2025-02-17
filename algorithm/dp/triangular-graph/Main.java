import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int[] DR = {0, 1, 1, 1};
    private static final int[] DC = {1, 1, 0, -1};

    private static int[][] grid;
    private static int[][] dp;
    private static int N;

    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = 1;

        while ((N = Integer.parseInt(br.readLine())) != 0) {
            grid = new int[N][3];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < 3; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp = new int[N][3];
            for (int i = 0; i < N; i++) Arrays.fill(dp[i], INF);

            dfs(0, 1);

            System.out.println(tc + ". " + dp[0][1]);

            tc++;
        }
    }
    private static int dfs(int row, int col) {

        if (row == N - 1) {
            if (col == 1) {
                return grid[row][col];
            }
            if (col == 2) {
                return INF;
            }
        }

        if (dp[row][col] != INF) {
            return dp[row][col];
        }

        int cost = INF;

        for (int i = 0; i < 4; i++) {
            int nxtRow = row + DR[i];
            int nxtCol = col + DC[i];

            if (!isValid(nxtRow, nxtCol)) continue;

            cost = Math.min(cost, dfs(nxtRow, nxtCol));
        }

        return dp[row][col] = cost + grid[row][col];
    }
    private static boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < N && col < 3;
    }
}
