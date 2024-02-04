import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int RESULT_EVEN = Integer.MIN_VALUE;
    private static int RESULT_ODD = Integer.MIN_VALUE;
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

        boolean[] flag = new boolean[2 * N - 1];

        query(grid, flag, 0, 0, N, 0);
        query(grid, flag, 1, 0, N, 0);

        System.out.println(RESULT_EVEN + RESULT_ODD);
    }
    private static void query(int[][] grid, boolean[] flag, int row, int col, int N, int count) {

        if (row == N - 1 && col > N - 1) {
            if ((row + col) % 2 == 0) {
                RESULT_EVEN = Math.max(RESULT_EVEN, count);
            } else {
                RESULT_ODD = Math.max(RESULT_ODD, count);
            }
            return;
        }

        int curRow = row;
        int curCol = col;

        int[] nextRowAndCol = getNextRowAndCol(row, col, N);

        boolean isActive = false;
        while (curRow >= 0 && curCol < N) {
            int idx = (N - 1) - curRow + curCol;

            if (!flag[idx] && grid[curRow][curCol] == 1) {
                isActive = true;
                flag[idx] = true;
                query(grid, flag, nextRowAndCol[0], nextRowAndCol[1], N, count + 1);
                flag[idx] = false;
            }

            curRow--;
            curCol++;
        }
        if (!isActive) {
            query(grid, flag, nextRowAndCol[0], nextRowAndCol[1], N, count);
        }
    }
    private static int[] getNextRowAndCol(int row, int col, int N) {

        if (row <= (N - 1 - 2)) {
            return new int[]{row + 2, col};
        } else if (row == N - 2) {
            return new int[]{row + 1, col + 1};
        } else {
            return new int[]{row, col + 2};
        }
    }
}