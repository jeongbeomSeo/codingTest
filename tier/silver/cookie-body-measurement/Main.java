import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[][] grid = new char[N][N];

        int row = -1;
        int col = -1;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = str.charAt(j);

                grid[i][j] = c;
                if (c == '*') {
                    int count = 0;

                    for (int d = 0; d < 2; d++) {
                        int nxtRow = i + DR[d];
                        int nxtCol = j + DC[d];

                        if (isValid(nxtRow, nxtCol, N, N) && grid[nxtRow][nxtCol] == '*') count++;
                    }
                    if (count == 2) {
                        row = i;
                        col = j;
                    }
                }
            }
        }

        int leftCount = dfs(grid, row, col, 1, 0, N);
        int rightCount = dfs(grid, row, col, 3, 0, N);
        int downCount = dfs(grid, row, col, 2, 0, N);
        int leftLegCount = dfs(grid, row + DR[2] * downCount, col - 1, 2, 0, N);
        int rightLegCount = dfs(grid, row + DR[2] * downCount, col + 1, 2, 0, N);

        System.out.println((row + 1) + " " + (col + 1));
        System.out.println(leftCount + " " + rightCount + " " + downCount + " " + leftLegCount + " " + rightLegCount);
    }
    private static int dfs(char[][] grid, int row, int col, int direction, int count, int N) {

        int nxtRow = row + DR[direction];
        int nxtCol = col + DC[direction];

        if (!isValid(nxtRow, nxtCol, N, N) || grid[nxtRow][nxtCol] != '*') return count;

        return dfs(grid, nxtRow, nxtCol, direction, count + 1, N);
    }
    private static boolean isValid(int row, int col, int rowSize, int colSize) {
        return row >= 0 && col >= 0 && row < rowSize && col < colSize;
    }
}
