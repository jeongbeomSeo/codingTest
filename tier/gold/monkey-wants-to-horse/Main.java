import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int W;
    private static int H;
    private static int[][] grid;
    private static int[][] history;
    private static int result = Integer.MAX_VALUE;

    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};
    private static final int[] HORSE_DR = {-1, -2, -2, -1, 1, 2, 2, 1};
    private static final int[] HORSE_DC = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        grid = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        history = new int[H][W];
        for (int i = 0; i < H; i++) {
            Arrays.fill(history[i], INF);
        }

        dfs(new boolean[H][W], 0, 0, K, 0);

        System.out.println(result != INF ? result : -1);
    }
    private static void dfs(boolean[][] isVisited, int row, int col, int k, int count) {

        if (row == H - 1 && col == W - 1) {
            result = Math.min(result, count);
            return;
        }

        if (history[row][col] < (1 << k)) {
            return;
        }

        history[row][col] = (1 << k);
        isVisited[row][col] = true;

        for (int i = 0; i < 4; i++) {
            int nxtRow = row + DR[i];
            int nxtCol = col + DC[i];

            if (isValidPoint(nxtRow, nxtCol) && !isVisited[nxtRow][nxtCol] && grid[nxtRow][nxtCol] != 1) {
                dfs(isVisited, nxtRow, nxtCol, k, count + 1);
            }
        }

        if (k > 0) {
            for (int i = 0; i < 8; i++) {
                int nxtRow = row + HORSE_DR[i];
                int nxtCol = col + HORSE_DC[i];

                if (isValidPoint(nxtRow, nxtCol) && !isVisited[nxtRow][nxtCol] && grid[nxtRow][nxtCol] != 1) {
                    dfs(isVisited, nxtRow, nxtCol, k - 1, count + 1);
                }
            }
        }
        isVisited[row][col] = false;
    }
    private static boolean isValidPoint(int row, int col) {
        return row >= 0 && col >= 0 && row < H && col < W;
    }
}
