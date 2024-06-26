import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private final static int MOD = 1_000_003;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken()) - 1;
        int E = Integer.parseInt(st.nextToken()) - 1;
        int T = Integer.parseInt(st.nextToken());

        long[][] grid = initGrid(N);

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for (int j = 0; j < N; j++) {
                int c = str.charAt(j) - '0';

                if (c != 0) {
                    grid[5 * i][5 * j + (c - 1)] = 1;
                }
            }
        }

        System.out.println(solution(grid, S, E, T));
    }
    private static long solution(long[][] grid, int S, int E, int T) {

        long[][] result = solve(grid, T);

        return result[5 * S][5 * E];
    }
    private static long[][] solve(long[][] baseGrid, int T) {

        if (T == 1) {
            return baseGrid;
        }

        if (T % 2 == 0) {
            long[][] result = solve(baseGrid, T / 2);
            return mulMat(result, result);
        } else {
            long[][] result = solve(baseGrid, (T - 1) / 2);
            return mulMat(mulMat(result, result), baseGrid);
        }
    }
    private static long[][] mulMat(long[][] grid1, long[][] grid2) {

        long[][] result = new long[grid1.length][grid1[0].length];

        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[i].length; j++) {
                for (int k = 0; k < grid1.length; k++) {
                    result[i][j] += (grid1[i][k] * grid2[k][j]) % MOD;
                    result[i][j] %= MOD;
                }
            }
        }

        return result;
    }
    private static long[][] initGrid(int N) {

        long[][] grid = new long[5 * N][5 * N];

        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= 4; j++) {
                grid[5 * i + j][5 * i + (j - 1)] = 1;
            }
        }

        return grid;
    }
}