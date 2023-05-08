import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  // 동, 북, 서, 남
  static int[] dr = {0, -1, 0, 1};
  static int[] dc = {1, 0, -1, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[][] curve = new int[N][4];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int col = Integer.parseInt(st.nextToken());
      int row = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());
      int level = Integer.parseInt(st.nextToken());
      curve[i][0] = row;
      curve[i][1] = col;
      curve[i][2] = direction;
      curve[i][3] = level;
    }

    System.out.println(simulation(curve, N));
  }
  static int simulation(int[][] curve, int N) {

    boolean[][] isVisited = new boolean[101][101];

    int count = 0;
    for (int i = 0; i < N; i++) {
      int[][] coordinates = dragonCurve(curve[i]);

      for (int j = 0; j < coordinates.length; j++) {
        int row = coordinates[j][0];
        int col = coordinates[j][1];
        if (isValidIdx(row, col)) isVisited[row][col] = true;
      }
    }

    for (int i = 0; i < 100; i++) {
      for (int j = 0; j < 100; j++) {
        if (isVisited[i][j] && isVisited[i + 1][j] && isVisited[i][j + 1] && isVisited[i + 1][j + 1]) count++;
      }
    }

    return count;

  }
  static int[][] dragonCurve (int[] curve) {
    int curLevel = 1;

    int[][] coordinates = new int[2][2];
    coordinates[0][0] = curve[0];
    coordinates[0][1] = curve[1];
    coordinates[1][0] = curve[0] + dr[curve[2]];
    coordinates[1][1] = curve[1] + dc[curve[2]];

    while (curLevel <= curve[3]) {

      int beforeLength = coordinates.length;
      int lastRow = coordinates[beforeLength - 1][0];
      int lastCol = coordinates[beforeLength - 1][1];

      int[][] temp = new int[2 * beforeLength - 1][2];
      for (int i = 0; i < beforeLength; i++) {
        temp[i][0] = coordinates[i][0];
        temp[i][1] = coordinates[i][1];
      }
      for (int i = 0; i < beforeLength - 1; i++) {
          temp[beforeLength + i][0] = lastRow - (coordinates[i][1] - lastCol);
          temp[beforeLength + i][1] = lastCol + (coordinates[i][0]- lastRow);
      }
      coordinates = temp;
      curLevel++;
    }
    return coordinates;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row <= 100 && col <= 100;
  }
}
