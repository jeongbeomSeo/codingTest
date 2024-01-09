import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
  private static final int[] DR = {-1, -1, 0, 1, 1, 1, 0, -1};
  private static final int[] DC = {0, 1, 1, 1, 0, -1, -1, -1};
  private static int count = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    char[][] grid = new char[N + 1][M + 1];
    Map<String, Integer> history = new HashMap<>();

    for (int i = 1; i <= N; i++) {
      String str = br.readLine();

      for (int j = 1; j <= M; j++) {
        grid[i][j] = str.charAt(j - 1);
      }
    }

    for (int k = 0; k < K; k++) {
      count = 0;
      String curStr = br.readLine();

      if (history.containsKey(curStr)) {
        if (k != K - 1) bw.write(history.get(curStr) + "\n");
        else bw.write(String.valueOf(history.get(curStr)));
        continue;
      }
      char startChar = curStr.charAt(0);
      for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= M; j++) {
          if (grid[i][j] == startChar) {
            dfs(grid, 1, curStr.length(), i, j, curStr, N, M);
          }
        }
      }

      history.put(curStr, count);
      if (k != K - 1) bw.write(count + "\n");
      else bw.write(String.valueOf(count));
    }

    bw.flush();
    bw.close();
  }
  private static void dfs(char[][] grid, int ptr, int size, int row, int col, String target, int N, int M) {
    if (ptr == size) {
      count++;
      return;
    }

    for (int i = 0; i < 8; i++) {
      int nxtRow = row + DR[i];
      int nxtCol = col + DC[i];

      int[] point = checkPoint(nxtRow, nxtCol, N, M);
      if (grid[point[0]][point[1]] == target.charAt(ptr)) {
        dfs(grid, ptr + 1, size, point[0], point[1], target, N, M);
      }
    }
  }
  private static int[] checkPoint(int nxtRow, int nxtCol, int N, int M) {
    if (nxtRow == 0) nxtRow = N;
    else if (nxtRow == N + 1) nxtRow = 1;

    if (nxtCol == 0) nxtCol = M;
    else if (nxtCol == M + 1) nxtCol = 1;

    return new int[]{nxtRow, nxtCol};
  }
}