import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int M;

    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[][] grid = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for (int j = 0; j < M; j++) {
                grid[i][j] = str.charAt(j);
            }
        }

        int[][] dp = new int[N][M];

        System.out.println(dfs(grid, new boolean[N][M], dp, 0, 0));
    }
    private static int dfs(char[][] grid, boolean[][] isVisited, int[][] dp, int row, int col) {

        if (!isValid(row, col) || grid[row][col] == 'H') {
            return 0;
        }

        if (isVisited[row][col]) {
            return -1;
        }

        if (dp[row][col] != 0) {
            return dp[row][col];
        }

        isVisited[row][col] = true;

        int cnt = grid[row][col] - '0';
        for (int i = 0; i < 4; i++) {
            int nxtRow = row + DR[i] * cnt;
            int nxtCol = col + DC[i] * cnt;

            int res = dfs(grid, isVisited, dp, nxtRow, nxtCol);

            if (res == -1) return -1;

            dp[row][col] = Math.max(dp[row][col], res + 1);
        }

        isVisited[row][col] = false;

        return dp[row][col];
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < N && col < M;
    }
}
