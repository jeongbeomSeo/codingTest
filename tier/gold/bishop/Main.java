import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int oddCount = 0;
    private static int evenCount = 0;
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

        System.out.println(query(grid, N));
    }
    public static int query(int[][] grid, int N) {
        oddCount = 0;
        evenCount = 0;

        boolean[] flag = new boolean[2 * N - 1];
        //Even Calc
        dfs(grid, flag, 0, 0, 0, N);
        // Odd Calc
        dfs(grid, flag, 1, 0, 0, N);

        return evenCount + oddCount;
    }
    public static void dfs(int[][] grid, boolean[] flag, int startRow, int startCol, int count, int N) {

        if (startRow + startCol >= 2 * N - 1) {
            if ((startRow + startCol) % 2 == 0) {
                evenCount = Math.max(evenCount, count);
            } else {
                oddCount = Math.max(oddCount, count);
            }
            return;
        }

        int curRow = startRow;
        int curCol = startCol;

        boolean checkOk = false;
        int[] nxtPoint = getNxtPoint(startRow, startCol, N);
        while (isValidPoint(curRow, curCol, N)) {
            if (!flag[(N - 1) - curRow + curCol] && grid[curRow][curCol] == 1) {
                flag[(N - 1) - curRow + curCol] = true;
                dfs(grid, flag, nxtPoint[0], nxtPoint[1], count + 1, N);
                flag[(N - 1) - curRow + curCol] = false;

                checkOk = true;
            }
            curRow--;
            curCol++;
        }
        if (!checkOk) dfs(grid, flag, nxtPoint[0], nxtPoint[1], count, N);
    }
    private static int[] getNxtPoint(int row, int col, int N) {
        if (row + 2 <= N - 1) {
            return new int[]{row + 2, 0};
        } else if (row + 2 == N) {
            return new int[]{N - 1, 1};
        } else if (row == N - 1) {
            return new int[]{N - 1, col + 2};
        }

        System.out.println("Logic Error");
        return new int[]{-1, -1};
    }
    private static boolean isValidPoint(int row, int col, int N) {
        return row >= 0 && col >= 0 && row < N && col < N;
    }
}