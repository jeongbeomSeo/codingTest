import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] grid = new int[3][3];
        int zero = 0;
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 0) zero++;
            }
        }

        int result = query(grid, zero % 2 == 0 ? 2 : 1, true);

        if (result == 1) System.out.println("W");
        else if (result == 0) System.out.println("D");
        else System.out.println("L");
    }
    private static int query(int[][] grid, int turn, boolean max) {

        if (isEnd(grid)) {
            if (max) return -1;
            else return 1;
        }

        if (isFull(grid)) {
            return 0; // 무승부
        }

        int result;
        if (max) {
            result = Integer.MIN_VALUE;
        } else {
            result = Integer.MAX_VALUE;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 0) {
                    grid[i][j] = turn;

                    int cur = query(grid, turn == 2 ? 1 : 2, !max);

                    if (max) {
                        result = Math.max(result, cur);
                    } else {
                        result = Math.min(result, cur);
                    }
                    grid[i][j] = 0;
                }
            }
        }

        return result;
    }

    private static boolean isFull(int[][] grid) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i][j] == 0) return false;
        return true;
    }
    private static boolean isEnd(int[][] grid) {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] != 0 && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) return true;
            if (grid[0][i] != 0 && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) return true;
        }

        if (grid[0][0] != 0 && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) return true;
        if (grid[2][0] != 0 && grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2]) return true;

        return false;
    }
}