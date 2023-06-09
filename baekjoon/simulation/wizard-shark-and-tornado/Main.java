import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {0, 1, 0, -1};
  static int[] dc = {-1, 0, 1, 0};
  static int N;
  static int amount = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    N = Integer.parseInt(br.readLine());

    int[][] grid = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(simulation(grid));
  }
  static int simulation(int[][] grid) {

    int row = N / 2;
    int col = N / 2;
    boolean[][] isVisited = new boolean[N][N];
    isVisited[row][col] = true;

    while (!(row == 0 && col == 0)) {
      for (int d = 0; d < 4; d++) {

        int nextD = d + 1 != 4 ? d + 1 : 0;
        do {
          if (row == 0 && col == 0) break;
          int nextRow = row + dr[d];
          int nextCol = col + dc[d];

          if (isValidIdx(nextRow, nextCol)) {
            Ratio[] ratios = createTornadoRatio(d);
            active_tornado(grid, nextRow, nextCol, ratios);
            row = nextRow;
            col = nextCol;
            isVisited[row][col] = true;
          }
        } while (isVisited[row + dr[nextD]][col + dc[nextD]]);
      }
    }
    return amount;
  }
  static void active_tornado(int[][] grid, int row, int col, Ratio[] ratios) {

    int outAmount = 0;
    int originAmount = grid[row][col];
    int remainAmount = originAmount;
    if (originAmount == 0) return;
    for (int i = 0; i <= 8; i++) {
      if (isValidIdx(row + ratios[i].row, col + ratios[i].col)) {
        grid[row + ratios[i].row][col + ratios[i].col] += (int)(originAmount * (double)ratios[i].ratio / 100);
      }
      else outAmount += (int)(originAmount * (double)ratios[i].ratio / 100);

      remainAmount -= (int)(originAmount * (double)ratios[i].ratio / 100);
    }
    if (isValidIdx(row + ratios[9].row, col + ratios[9].col)) grid[row + ratios[9].row][col + ratios[9].col] += remainAmount;
    else outAmount += remainAmount;

    amount += outAmount;
    grid[row][col] = 0;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
  static Ratio[] createTornadoRatio(int direction) {
    Ratio[] newRatio = new Ratio[10];

    if (direction == 0 || direction == 2) {
      int pow = direction == 0 ? 1 : -1;
      newRatio[0] = new Ratio(0, -2, 5, pow);
      newRatio[1] = new Ratio(-1, -1, 10, pow);
      newRatio[2] = new Ratio(1, -1, 10, pow);
      newRatio[3] = new Ratio(-1, 0, 7, pow);
      newRatio[4] = new Ratio(1, 0, 7, pow);
      newRatio[5] = new Ratio(-2, 0, 2, pow);
      newRatio[6] = new Ratio(2, 0, 2, pow);
      newRatio[7] = new Ratio(-1, 1, 1, pow);
      newRatio[8] = new Ratio(1, 1, 1, pow);
      newRatio[9] = new Ratio(0, -1, 0, pow);
    }
    else {
      int pow = direction == 1 ? 1 : -1;
      newRatio[0] = new Ratio(2, 0, 5, pow);
      newRatio[1] = new Ratio(1, 1, 10, pow);
      newRatio[2] = new Ratio(1, -1, 10, pow);
      newRatio[3] = new Ratio(0, 1, 7, pow);
      newRatio[4] = new Ratio(0, -1, 7, pow);
      newRatio[5] = new Ratio(0, 2, 2, pow);
      newRatio[6] = new Ratio(0, -2, 2, pow);
      newRatio[7] = new Ratio(-1, -1, 1, pow);
      newRatio[8] = new Ratio(-1, 1, 1, pow);
      newRatio[9] = new Ratio(1, 0, 0, pow);
    }
    return newRatio;
  }

}
class Ratio {
  int row;
  int col;
  int ratio;

  Ratio(int row, int col, int ratio, int pow) {
    this.row = row * pow;
    this.col = col * pow;
    this.ratio = ratio;
  }
}
