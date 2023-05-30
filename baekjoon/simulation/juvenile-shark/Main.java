import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MAX_VALUE;
  static int max = 0;
  static int[] dr = {INF, -1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dc = {INF, 0, -1, -1, -1, 0, 1, 1, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int[][] fish_id_grid = new int[4][4];
    Fish[] fishes = new Fish[4 * 4 + 1];

    for (int i = 0; i < 4; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 4; j++) {
        int id = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());
        fish_id_grid[i][j] = id;
        fishes[id] = new Fish(i, j, direction);
      }
    }

    System.out.println(simulation(fish_id_grid, fishes));
  }
  static int simulation(int[][] fish_id_grid, Fish[] fishes) {

    backtracking(fish_id_grid, fishes, new Shark(0, 0, 0), 0);

    return max;
  }
  static void backtracking(int[][] fish_id_grid, Fish[] fishes, Shark shark, int sum) {

    int id = fish_id_grid[shark.row][shark.col];
    sum += id;
    fish_id_grid[shark.row][shark.col] = 0;
    fishes[id].isDead = true;
    shark.direction = fishes[id].direction;

    fishMove(fish_id_grid, fishes, shark);

    boolean cantMove = true;
    while (isValidIdx(shark.row + dr[shark.direction], shark.col + dc[shark.direction])) {
      shark.row += dr[shark.direction];
      shark.col += dc[shark.direction];

      if (fish_id_grid[shark.row][shark.col] != 0 && !fishes[fish_id_grid[shark.row][shark.col]].isDead) {
        cantMove = false;
        int[][] copy_grid = copy_grid(fish_id_grid);
        Fish[] copy_fish = copy_fish(fishes);
        backtracking(copy_grid, copy_fish, new Shark(shark.row, shark.col, shark.direction), sum);
      }
    }

    if (cantMove) max = Math.max(max, sum);
  }
  static int[][] copy_grid(int[][] fish_id_grid) {
    int[][] grid = new int[4][4];

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        grid[i][j] = fish_id_grid[i][j];
      }
    }
    return grid;
  }
  static Fish[] copy_fish (Fish[] fishes){
    Fish[] newFishList = new Fish[17];

    for (int i = 1; i <= 16; i++) newFishList[i] = new Fish(fishes[i].row, fishes[i].col, fishes[i].direction, fishes[i].isDead);

    return newFishList;
  }
  static void fishMove(int[][] fish_id_grid, Fish[] fishes, Shark shark) {

    for (int i = 1; i <= 16; i++) {
      Fish fish = fishes[i];

      if (!fish.isDead) {
        int baseDirection = fish.direction;
        do {
          int nextRow = fish.row + dr[fish.direction];
          int nextCol = fish.col + dc[fish.direction];

          if (isValidIdx(nextRow, nextCol) && !(shark.row == nextRow && shark.col == nextCol)) {
            fish_id_grid[fish.row][fish.col] = fish_id_grid[nextRow][nextCol];
            fish.row = nextRow;
            fish.col = nextCol;
            fish_id_grid[fish.row][fish.col] = i;
            break;
          }
          else {
            fish.direction++;
            if (fish.direction == 9) fish.direction = 1;
          }
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
class Fish{
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

  Fish (int row, int col, int direction, boolean isDead) {
    this(row, col, direction);
    this.isDead = isDead;
  }
}
