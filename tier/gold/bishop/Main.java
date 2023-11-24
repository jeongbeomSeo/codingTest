import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int MAX = Integer.MIN_VALUE;
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

        System.out.println(queryResult(grid, N));
    }
    private static int queryResult(int[][] grid, int N) {
        boolean[] flag = new boolean[2 * N - 1];

        MAX = Integer.MIN_VALUE;
        dfs(grid, flag, 0, N, 0);
        int eventResult = MAX;

        MAX = Integer.MIN_VALUE;
        dfs(grid, flag, 1, N, 0);
        int oddResult = MAX;

        return eventResult + oddResult;
    }
    private static void dfs(int[][] grid, boolean[] flag, int ptr, int size, int count) {
        if (ptr >= 2 * size - 1) {
            MAX = Math.max(MAX, count);
        } else {
            int row = ptr <= (size - 1) ? ptr : (size - 1);
            int col = ptr <= (size - 1) ? 0 : ptr - (size - 1);

            if (canLoadBishop(grid, flag, row, col, size)) {
                while (isValidPoint(row, col, size)) {
                    if (grid[row][col] == 1 && !flag[(size - 1) - row + col]) {
                        flag[(size - 1) - row + col] = true;
                        dfs(grid, flag, ptr + 2, size, count + 1);
                        flag[(size - 1) - row + col] = false;
                    }
                    row--;
                    col++;
                }
            } else {
                dfs(grid, flag, ptr + 2, size, count);
            }
        }
    }
    private static boolean canLoadBishop(int[][] grid, boolean[] flag, int row, int col, int size) {

        while (isValidPoint(row, col, size)) {
            if (grid[row][col] == 1 && !flag[(size - 1) - row + col]) return true;

            row--;
            col++;
        }

        return false;
    }
    private static boolean isValidPoint(int row, int col, int size) {
        return row >= 0 && col >= 0 && row < size && col < size;
    }
}