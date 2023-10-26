import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MAX_VALUE;
  static int[] dr = {INF, -1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dc = {INF, 0, -1, -1, -1, 0, 1, 1, 1};
  static int max = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    Fish[] fishes = new Fish[4 * 4 + 1];
    int[][] idx_grid = new int[4][4];
    for (int i = 0; i < 4; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 4; j++) {
        int idx = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());
        fishes[idx] = new Fish(i ,j, direction);
        idx_grid[i][j] = idx;
      }
    }

    System.out.println(simulation(fishes, idx_grid));
  }
  static int simulation(Fish[] fishes, int[][] idx_grid) {

    active_shark(fishes, new Shark(0, 0, 0), idx_grid, 0);

    return max;
  }
  static void active_shark(Fish[] fishes, Shark shark, int[][] idx_grid, int sum) {

    int idx = idx_grid[shark.row][shark.col];
    shark.direction = fishes[idx].direction;
    sum += idx;
    fishes[idx].isDead = true;
    idx_grid[shark.row][shark.col] = 0;

    active_fishes(fishes, shark, idx_grid);

    boolean isMove = false;
    while (isValidIdx(shark.row + dr[shark.direction], shark.col + dc[shark.direction])) {
      shark.row += dr[shark.direction];
      shark.col += dc[shark.direction];

      if (idx_grid[shark.row][shark.col] != 0) {
        isMove = true;
        int[][] newGrid = copy_grid(idx_grid);
        Fish[] newFishes = copy_fish(fishes);

        active_shark(newFishes, new Shark(shark.row, shark.col, shark.direction), newGrid, sum);
      }
    }

    if (!isMove) max = Math.max(max, sum);
  }
  static int[][] copy_grid(int[][] idx_grid) {
    //int[][] grid = Arrays.copyOf(idx_grid, 4);
    int[][] grid = new int[4][4];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        grid[i][j] = idx_grid[i][j];
      }
    }
    return grid;
  }
  static Fish[] copy_fish(Fish[] fishes) {

    Fish[] newFishes = new Fish[17];
    for (int i = 1; i <= 16; i++)
      newFishes[i] = new Fish(fishes[i].row, fishes[i].col, fishes[i].direction, fishes[i].isDead);

    return newFishes;
  }
  static void active_fishes(Fish[] fishes, Shark shark, int[][] idx_grid) {

    for (int i = 1; i <= 16; i++) {
      Fish fish = fishes[i];
      if (!fish.isDead) {
        int baseDirection = fish.direction;
        do {
          int nextRow = fish.row + dr[fish.direction];
          int nextCol = fish.col + dc[fish.direction];

          if (isValidIdx(nextRow, nextCol) && !(shark.row == nextRow && shark.col == nextCol)) {
            int otherIdx = idx_grid[nextRow][nextCol];
            if(otherIdx != 0) {
              fishes[otherIdx].row = fish.row;
              fishes[otherIdx].col = fish.col;
            }
            idx_grid[fish.row][fish.col] = otherIdx;

            fish.row = nextRow;
            fish.col = nextCol;
            idx_grid[nextRow][nextCol] = i;
            break;
          }

          if (++fish.direction == 9) fish.direction = 1;
        } while (fish.direction != baseDirection);
      }
    }
  }

  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < 4 && col < 4;
  }
}
class Shark {
  int row;
  int col;
  int direction;

  Shark(int row, int col, int direction) {
    this.row = row;
    this.col = col;
    this.direction = direction;
  }
}
class Fish {
  int row;
  int col;
  int direction;
  boolean isDead;

  Fish(int row, int col, int direction) {
    this.row = row;
    this.col = col;
    this.direction = direction;
    isDead = false;
  }
  Fish(int row, int col, int direction, boolean isDead) {
    this(row, col, direction);
    this.isDead = isDead;
  }
}