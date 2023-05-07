import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int R, C, T;
  // 북 -> 동 -> 남 -> 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};

  // 남 -> 동 -> 북 -> 서
  static int[] down_dr = {1, 0, -1, 0};
  static int[] down_dc = {0, 1, 0, -1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    T = Integer.parseInt(st.nextToken());

    int[][] grid = new int[R + 1][C + 1];
    Machine machine = null;

    for (int i = 1; i <= R; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= C; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
        if (grid[i][j] == -1 && machine == null) {
          machine = new Machine(i, i + 1);
        }
      }
    }

    System.out.println(simulation(grid, machine));
  }
  static int simulation(int[][] grid, Machine machine) {
    int time = 0;

    while (time < T) {

      int[][] curTime_diffusion = diffusion(grid);
      for (int i = 1; i <= R; i++) {
        for (int j = 1; j <= C; j++) {
          grid[i][j] += curTime_diffusion[i][j];
        }
      }

      int row = machine.upRow;
      int col = machine.col;
      activeMachine(grid, row, col, dr, dc, machine.upRow);

      row = machine.downRow;
      col = machine.col;
      activeMachine(grid, row, col, down_dr, down_dc, machine.downRow);

      time++;
    }

    int amount = 0;
    for (int i = 1; i <= R; i++) {
      for (int j = 1; j <= C; j++) {
        if (grid[i][j] != -1) amount += grid[i][j];
      }
    }

    return amount;

  }

  static void activeMachine(int[][] grid, int row, int col, int[] dr, int[] dc, int border) {
    for (int i = 0; i < 4; i++) {
      if (i == 0) {
        while (isValidIdx(row + dr[i], col + dc[i])) {
          if (grid[row][col] == -1)
            grid[row + dr[i]][col + dc[i]] = 0;
          else
            grid[row][col] = grid[row + dr[i]][col + dc[i]];
          row = row + dr[i];
          col = col + dc[i];
        }
      }
      else if (i == 1) {
        while (isValidIdx(row + dr[i], col + dc[i])) {
          grid[row][col] = grid[row + dr[i]][col + dc[i]];
          row = row + dr[i];
          col = col + dc[i];
        }
      }
      else if (i == 2) {
        while (isValidIdx(row + dr[i], col + dc[i])) {
          grid[row][col] = grid[row + dr[i]][col + dc[i]];
          row = row + dr[i];
          col = col + dc[i];
          if (row == border) break;
        }
      }
      else {
        while (isValidIdx(row + dr[i], col + dc[i])) {
          if (grid[row + dr[i]][col + dc[i]] == -1) {
            grid[row][col] = 0;
            break;
          }
          grid[row][col] = grid[row + dr[i]][col + dc[i]];
          row = row + dr[i];
          col = col + dc[i];
        }
      }
    }
  }

  static int[][] diffusion(int[][] grid) {

    int[][] diffusion_Amount = new int[R + 1][C + 1];

    for (int i = 1; i <= R; i++) {
      for (int j = 1; j <= C; j++) {
        if (grid[i][j] != 0 && grid[i][j] != -1) {
          int amount = grid[i][j] / 5;
          int count = 0;
          for (int k = 0; k < 4; k++) {
            if (isValidIdx(i + dr[k], j + dc[k]) &&
                    grid[i + dr[k]][j + dc[k]] != -1) {
              diffusion_Amount[i + dr[k]][j + dc[k]] += amount;
              count++;
            }
          }
          diffusion_Amount[i][j] -= amount * count;
        }
      }
    }
    return diffusion_Amount;
  }

  static boolean isValidIdx(int row, int col) {
    return row >= 1 && col >= 1 && row <= R && col <= C;
  }
}
class Machine {
  int upRow;
  int downRow;
  int col;
  int dustAmount;

  Machine(int upRow, int downRow) {
    this.upRow = upRow;
    this.downRow = downRow;
    this.col = 1;
    this.dustAmount = 0;
  }
}