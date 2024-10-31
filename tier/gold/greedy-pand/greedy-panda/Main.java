import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] == -1) {
                    max = Math.max(max, dfs(grid, dp, i, j));
                }
            }
        }

        System.out.println(max);
    }
    private static int dfs(int[][] grid, int[][] dp, int row, int col) {

        if (dp[row][col] != -1) return dp[row][col];

        int count = 0;

        int cur = grid[row][col];
        for (int i = 0; i < 4; i++) {
            int nxtRow = row + DR[i];
            int nxtCol = col + DC[i];

            if (!isValid(nxtRow, nxtCol, grid.length, grid.length)) continue;

            if (cur < grid[nxtRow][nxtCol]) {
                int result = dfs(grid, dp, nxtRow, nxtCol);
                count = Math.max(count, result);
            }
        }

        return dp[row][col] = count + 1;
    }
    private static boolean isValid(int row, int col, int rowSize, int colSize) {
        return row >= 0 && col >= 0 && row < rowSize && col < colSize;
    }
}
