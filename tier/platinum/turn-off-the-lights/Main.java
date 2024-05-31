import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static final boolean[][] table = {
            {false, true, false},
            {true, true, true},
            {false, true, false}
    };
    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[][] grid = new boolean[10][10];

        for (int i = 0; i < 10; i++) {
            String str = br.readLine();
            for (int j = 0; j < 10; j++) {
                char c = str.charAt(j);
                if (c == 'O') {
                    grid[i][j] = true;
                }
            }
        }

        int result = INF;
        for (int i = 0; i < (1 << 10); i++) {
            int cnt = 0;
            boolean[][] copy = copyGrid(grid);
            for (int j = 0; j < 10; j++) {
                if ((i & (1 << j)) != 0) {
                    turnOn(copy, 0, j);
                    cnt++;
                }
            }

            for (int row = 1; row < 10; row++) {
                for (int col = 0; col < 10; col++) {
                    if (copy[row - 1][col]) {
                        turnOn(copy, row, col);
                        cnt++;
                    }
                }
            }

            if (isSuccess(copy)) {
                result = Math.min(result, cnt);
            }
        }
        System.out.println(result != INF ? result : -1);
    }
    private static boolean isSuccess(boolean[][] grid) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j]) return false;
            }
        }
        return true;
    }
    private static void turnOn(boolean[][] grid, int row, int col) {
        for (int i = 0; i < 3; i++) {
            if (row == 0 && i == 0) continue;
            if (row == 9 && i == 2) continue;
            for (int j = 0; j < 3; j++) {
                if (col == 0 && j == 0) continue;
                if (col == 9 && j == 2) continue;
                grid[row + i - 1][col + j - 1] ^= table[i][j];
            }
        }
    }
    private static boolean[][] copyGrid(boolean[][] grid) {
        boolean[][] copy = new boolean[10][10];

        for (int i = 0; i < 10; i++) {
            copy[i] = Arrays.copyOf(grid[i], 10);
        }

        return copy;
    }
}
