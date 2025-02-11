import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int[] DR = {1, 0};
    private static final int[] DC = {0, 1};
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

        long[][] dp = new long[N][N];

        System.out.println(dfs(grid, dp, 0, 0));
    }
    private static long dfs(int[][] grid, long[][] dp, int row, int col) {

        if (dp[row][col] != 0) {
            if (dp[row][col] == -1) return 0;
            return dp[row][col];
        }

        if (isEnd(row, col, grid.length)) {
            return dp[row][col] = 1;
        }

        long count = 0;

        int move = grid[row][col];
        if (move == 0) {
            dp[row][col] = -1;
            return 0;
        }

        for (int i = 0; i < 2; i++) {
            int nxtRow = row + DR[i] * move;
            int nxtCol = col + DC[i] * move;

            if (!isValid(nxtRow, nxtCol, grid.length)) continue;

            count += dfs(grid, dp, nxtRow, nxtCol);
        }

        if (count == 0) {
            dp[row][col] = -1;
            return 0;
        }

        return dp[row][col] = count;
    }
    private static boolean isEnd(int row, int col, int N) {
        return row == N - 1 && col == N - 1;
    }
    private static boolean isValid(int row, int col, int N) {
        return row >= 0 && col >= 0 && row < N && col < N;
    }
}
