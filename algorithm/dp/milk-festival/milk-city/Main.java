import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
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

        int[][][] dpTable = new int[N][N][3];

        queryDp(grid, dpTable, N);

        System.out.println(Math.max(dpTable[N - 1][N - 1][0], Math.max(dpTable[N - 1][N - 1][1], dpTable[N- 1][N - 1][2])));
    }
    private static void queryDp(int[][] grid, int[][][] dpTable, int N) {

        if (grid[0][0] == 0) {
            dpTable[0][0][0] = 1;
        }

        for (int j = 1; j < N; j++) {
            int milk = grid[0][j];

            dpTable[0][j][0] = dpTable[0][j - 1][0];
            dpTable[0][j][1] = dpTable[0][j - 1][1];
            dpTable[0][j][2] = dpTable[0][j - 1][2];

            if (milk == 0) {
                dpTable[0][j][0] = Math.max(dpTable[0][j][0], dpTable[0][j - 1][2] + 1);
            }
            if (milk == 1 && dpTable[0][j - 1][0] > 0) {
                dpTable[0][j][1] = Math.max(dpTable[0][j][1], dpTable[0][j - 1][0] + 1);
            }
            if (milk == 2 && dpTable[0][j - 1][1] > 0) {
                dpTable[0][j][2] = Math.max(dpTable[0][j][2], dpTable[0][j - 1][1] + 1);
            }
        }

        for (int i = 1; i < N; i++) {
            int milk = grid[i][0];

            dpTable[i][0][0] = dpTable[i - 1][0][0];
            dpTable[i][0][1] = dpTable[i - 1][0][1];
            dpTable[i][0][2] = dpTable[i - 1][0][2];

            if (milk == 0) {
                dpTable[i][0][0] = Math.max(dpTable[i][0][0], dpTable[i - 1][0][2] + 1);
            }
            if (milk == 1 && dpTable[i][0][0] > 0) {
                dpTable[i][0][1] = Math.max(dpTable[i][0][1], dpTable[i - 1][0][0] + 1);
            }
            if (milk == 2 && dpTable[i][0][1] > 0) {
                dpTable[i][0][2] = Math.max(dpTable[i][0][2], dpTable[i - 1][0][1] + 1);
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                dpTable[i][j][0] = Math.max(dpTable[i - 1][j][0], dpTable[i][j - 1][0]);
                dpTable[i][j][1] = Math.max(dpTable[i - 1][j][1], dpTable[i][j - 1][1]);
                dpTable[i][j][2] = Math.max(dpTable[i - 1][j][2], dpTable[i][j - 1][2]);

                int milk = grid[i][j];

                if (milk == 0) {
                    dpTable[i][j][0] = Math.max(dpTable[i][j][0], Math.max(dpTable[i - 1][j][2] + 1, dpTable[i][j - 1][2] + 1));
                } else if (milk == 1) {
                    if (dpTable[i - 1][j][0] > 0) {
                        dpTable[i][j][1] = Math.max(dpTable[i][j][1], dpTable[i - 1][j][0] + 1);
                    }
                    if (dpTable[i][j - 1][0] > 0) {
                        dpTable[i][j][1] = Math.max(dpTable[i][j][1], dpTable[i][j - 1][0] + 1);
                    }
                } else {
                    if (dpTable[i - 1][j][1] > 0) {
                        dpTable[i][j][2] = Math.max(dpTable[i][j][2], dpTable[i - 1][j][1] + 1);
                    }
                    if (dpTable[i][j - 1][1] > 0) {
                        dpTable[i][j][2] = Math.max(dpTable[i][j][2], dpTable[i][j - 1][1] + 1);
                    }
                }
            }
        }
    }
}
