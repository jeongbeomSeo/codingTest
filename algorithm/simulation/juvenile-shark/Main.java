import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  private static final int[] dr = {0, -1, -1, 0, 1, 1, 1, 0, -1};
  private static final int[] dc = {0, 0, -1, -1, -1, 0, 1, 1, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    Fish[] fishes = new Fish[17];
    int[][] fishIdxGrid = new int[4][4];

    for (int i = 0; i < 4; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 4; j++) {
        int idx = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());
        fishes[idx] = new Fish(idx, i, j, direction);
        fishIdxGrid[i][j] = idx;
      }
    }

    System.out.println(simulation(fishes, fishIdxGrid));
  }
  private static int simulation(Fish[] fishes, int[][] fishIdxGrid) {

    int startFishIdx = fishIdxGrid[0][0];
    Shark shark = new Shark(0, 0, fishes[startFishIdx].direction, startFishIdx);
    deadFish(fishes, fishIdxGrid, startFishIdx);

    return getMaxSimulation(shark, fishes, fishIdxGrid);
  }
  private static int getMaxSimulation(Shark shark, Fish[] fishes, int[][] fishIdxGrid) {

    Queue<Simulation> q = new ArrayDeque<>();
    q.add(new Simulation(shark, fishes, fishIdxGrid));

    int max = shark.count;
    while (!q.isEmpty()) {
      Simulation simulation = q.poll();

      Fish[] curFishes = simulation.fishes;
      int[][] curFishIdxGrid = simulation.fishIdxGrid;
      Shark curShark = simulation.shark;

      activeFish(curFishes, curFishIdxGrid, curShark);

      if (canProgressNextSimulation(curFishIdxGrid, curShark)) {
        int row = curShark.row;
        int col = curShark.col;

        while (isValidPoint(row + dr[curShark.direction], col + dc[curShark.direction])) {
          row += dr[curShark.direction];
          col += dc[curShark.direction];

          if (curFishIdxGrid[row][col] != 0) {
            int[][] nextFishIdxGrid = copyFishIdxGrid(curFishIdxGrid);
            Fish[] nextFishes = copyFishes(curFishes);
            Shark nextSimulationShark = getNextSimulationShark(curShark, row, col, nextFishes, nextFishIdxGrid);
            q.add(new Simulation(nextSimulationShark, nextFishes, nextFishIdxGrid));
          }
        }
      } else {
        max = Math.max(max, curShark.count);
      }
    }

    return max;
  }
  private static int[][] copyFishIdxGrid(int[][] fishIdxGrid) {
    int[][] copy = new int[4][4];

    for (int i = 0; i < 4; i++) {
      copy[i] = Arrays.copyOf(fishIdxGrid[i], 4);
    }

    return copy;
  }
  private static Fish[] copyFishes(Fish[] fishes) {

    Fish[] copy = new Fish[17];
    for (int i = 1; i <= 16; i++) {
      copy[i] = new Fish(i, fishes[i].row, fishes[i].col, fishes[i].direction, fishes[i].isDead);
    }

    return copy;
  }
  private static Shark getNextSimulationShark(Shark shark, int nextRow, int nextCol, Fish[] fishes, int[][] fishIdxGrid) {

    int fishIdx = fishIdxGrid[nextRow][nextCol];
    deadFish(fishes, fishIdxGrid, fishIdx);
    return new Shark(nextRow, nextCol, fishes[fishIdx].direction, shark.count + fishIdx);
  }
  private static boolean canProgressNextSimulation(int[][] fishIdxGrid, Shark shark) {

    int row = shark.row + dr[shark.direction];
    int col = shark.col + dc[shark.direction];

    while (isValidPoint(row, col) &&
            fishIdxGrid[row][col] == 0) {
      row += dr[shark.direction];
      col += dc[shark.direction];
    }

    return isValidPoint(row, col);
  }
  private static void activeFish(Fish[] fishes, int[][] fishIdxGrid, Shark shark) {

    for (int i = 1; i <= 16; i++) {
      if (fishes[i].isDead) continue;

      Fish fish = fishes[i];

      moveFish(fishes, fish, fishIdxGrid, shark);
    }
  }
  private static void moveFish(Fish[] fishes, Fish fish, int[][] fishIdxGrid, Shark shark) {

    int[] nextPoint = getNextPoint(fish, shark);

    if (isMove(nextPoint, fish)) {
      if (isFishAtNextPoint(fishIdxGrid, nextPoint)) {
        swapFish(fishes, fish, fishIdxGrid, nextPoint);
      } else {
        moveFish(fish, fishIdxGrid, nextPoint);
      }
    }
  }
  private static void swapFish(Fish[] fishes, Fish fish, int[][] fishIdxGrid, int[] nextPoint) {
    int pointFishIdx = fishIdxGrid[nextPoint[0]][nextPoint[1]];
    fishes[pointFishIdx].row = fish.row;
    fishes[pointFishIdx].col = fish.col;
    moveFish(fish, fishIdxGrid, nextPoint);
    fishIdxGrid[fishes[pointFishIdx].row][fishes[pointFishIdx].col] = pointFishIdx;
  }
  private static void moveFish(Fish fish, int[][] fishIdxGrid, int[] nextPoint) {
    fishIdxGrid[fish.row][fish.col] = 0;
    fish.row = nextPoint[0];
    fish.col = nextPoint[1];
    fish.direction = nextPoint[2];
    fishIdxGrid[fish.row][fish.col] = fish.idx;
  }
  private static boolean isFishAtNextPoint(int[][] fishIdxGrid, int[] nextPoint) {
    return fishIdxGrid[nextPoint[0]][nextPoint[1]] != 0;
  }
  private static boolean isMove(int[] nextPoint, Fish fish) {
    return !(nextPoint[0] == fish.row && nextPoint[1] == fish.col);
  }
  private static int[] getNextPoint(Fish fish, Shark shark) {

    int direction = fish.direction;
    do {
      int nextRow = fish.row + dr[direction];
      int nextCol = fish.col + dc[direction];

      if (canMove(nextRow, nextCol, shark)) {
        return new int[] {nextRow, nextCol, direction};
      }

      direction = nextDirection(direction);
    } while (direction != fish.direction);

    return new int[]{fish.row, fish.col, fish.direction};
  }

  private static int nextDirection (int direction) {
    if (direction + 1 != 9) return direction + 1;

    return 1;
  }
  private static boolean canMove(int nextRow, int nextCol, Shark shark) {
    return isValidPoint(nextRow, nextCol) && !isSharkPoint(nextRow, nextCol, shark);
  }
  private static boolean isSharkPoint(int row, int col, Shark shark) {
    return (row == shark.row) && (col == shark.col);
  }
  private static boolean isValidPoint(int row, int col) {
    return row >= 0 && col >= 0 && row < 4 && col < 4;
  }
  private static void deadFish(Fish[] fishes, int[][] fishIdxGrid, int idx){
    fishes[idx].isDead = true;
    fishIdxGrid[fishes[idx].row][fishes[idx].col] = 0;
  }
}
class Simulation {
  Shark shark;
  Fish[] fishes;
  int[][] fishIdxGrid;

  Simulation(Shark shark, Fish[] fishes, int[][] fishIdxGrid) {
    this.shark = shark;
    this.fishes = fishes;
    this.fishIdxGrid = fishIdxGrid;
  }
}
class Shark {
  int row;
  int col;
  int direction;
  int count;

  Shark (int row, int col, int direction, int count) {
    this.row = row;
    this.col = col;
    this.direction = direction;
    this.count = count;
  }
}
class Fish {
  int idx;
  int row;
  int col;
  int direction;
  boolean isDead;


  Fish (int idx, int row, int col, int direction) {
    this.idx = idx;
    this.row = row;
    this.col = col;
    this.direction = direction;
    this.isDead = false;
  }

  Fish (int idx, int row, int col, int direction, boolean isDead) {
    this(idx, row, col, direction);
    this.isDead = isDead;
  }
}