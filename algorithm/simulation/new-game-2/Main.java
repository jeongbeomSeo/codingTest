import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  static int N, K;
  static int[] dr = {0, 0, 0, -1, 1};
  static int[] dc = {0, 1, -1, 0, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N + 1][N + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    Horse[] horses = new Horse[K + 1];
    for (int i = 1; i <= K; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());
      horses[i] = new Horse(row, col, direction);
    }

    System.out.println(simulation(grid, horses));
  }
  static int simulation (int[][] gird, Horse[] horses) {

    int time = 1;

    ArrayList<Integer>[][] idxGrid = initIdxGrid(horses);

    while (time < 1001) {

      if(moveHorse(gird, horses, idxGrid)) break;

      time++;
    }

    if (time == 1001) return -1;
    return time;
  }
  static boolean moveHorse (int[][] grid, Horse[] horses, ArrayList<Integer>[][] idxGrid) {

    boolean result = false;
    for (int i = 1; i <= K; i++) {
      Horse horse = horses[i];

      int nextRow = horse.row + dr[horse.direction];
      int nextCol = horse.col + dc[horse.direction];

      int nextPointColor = -1;
      if (isValidIdx(nextRow, nextCol)) {
        nextPointColor = grid[nextRow][nextCol];
      }
      else nextPointColor = 2;

      if (nextPointColor == 0) result = moveWhitePoint(i, horse.row, horse.col, nextRow, nextCol, horses, idxGrid);
      else if (nextPointColor == 1) result = moveRedPoint(i, horse.row, horse.col, nextRow, nextCol, horses, idxGrid);
      else result = moveBluePoint(i, grid, horse, horses, idxGrid);

      if (result) break;
    }

    return result;
  }
  static int reverseDirection(int direction) {
    if (direction == 1) return 2;
    else if (direction == 2) return 1;
    else if (direction == 3) return 4;
    else return 3;
  }
  static boolean moveWhitePoint(int originIdx, int row, int col, int nextRow, int nextCol, Horse[] horses, ArrayList<Integer>[][] idxGrid) {

    int startIdx = 0;
    while (idxGrid[row][col].get(startIdx) != originIdx) startIdx++;

    for (int i = startIdx; i < idxGrid[row][col].size(); i++) {
      int idx = idxGrid[row][col].get(i);

      horses[idx].row = nextRow;
      horses[idx].col = nextCol;

      if (idxGrid[nextRow][nextCol] == null) idxGrid[nextRow][nextCol] = new ArrayList<>();
      idxGrid[nextRow][nextCol].add(idx);
    }

    int startPoint = startIdx;
    int endPoint = idxGrid[row][col].size();
    while(startPoint++ < endPoint) idxGrid[row][col].remove(startIdx);

    return idxGrid[nextRow][nextCol].size() >= 4;
  }
  static boolean moveBluePoint(int originIdx, int[][] grid, Horse horse, Horse[] horses, ArrayList<Integer>[][] idxGrid) {

    boolean result = false;
    horse.direction = reverseDirection(horse.direction);

    int row = horse.row;
    int col = horse.col;
    int nextRow = horse.row + dr[horse.direction];
    int nextCol = horse.col + dc[horse.direction];

    int nextPointColor = -1;
    if (isValidIdx(nextRow, nextCol)) {
      nextPointColor = grid[nextRow][nextCol];
    }
    else nextPointColor = 2;

    if (nextPointColor != 2) {
      if (nextPointColor == 0) result = moveWhitePoint(originIdx, row, col, nextRow, nextCol, horses, idxGrid);
      else if (nextPointColor == 1) result =  moveRedPoint(originIdx, row, col, nextRow, nextCol, horses, idxGrid);
    }

    return result;
  }
  static boolean moveRedPoint(int originIdx, int row, int col, int nextRow, int nextCol, Horse[] horses, ArrayList<Integer>[][] idxGrid) {

    int endIdx = idxGrid[row][col].size() - 1;
    while (idxGrid[row][col].get(endIdx) != originIdx) endIdx--;

    for (int i = idxGrid[row][col].size() - 1; i >= endIdx; i--) {
      int idx = idxGrid[row][col].get(i);

      horses[idx].row = nextRow;
      horses[idx].col = nextCol;

      if (idxGrid[nextRow][nextCol] == null) idxGrid[nextRow][nextCol] = new ArrayList<>();
      idxGrid[nextRow][nextCol].add(idx);
    }

    int startPoint = endIdx;
    int endPoint = idxGrid[row][col].size();
    while(startPoint++ < endPoint) idxGrid[row][col].remove(endIdx);
    return idxGrid[nextRow][nextCol].size() >= 4;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 1 && col >= 1 && row <= N && col <= N;
  }
  static ArrayList<Integer>[][] initIdxGrid(Horse[] horses) {

    ArrayList<Integer>[][] idxGrid = new ArrayList[N + 1][N + 1];
    for (int i = 1; i <= K; i++) {
      Horse horse = horses[i];

      if (idxGrid[horse.row][horse.col] == null) idxGrid[horse.row][horse.col] = new ArrayList<>();
      idxGrid[horse.row][horse.col].add(i);
    }

    return idxGrid;
  }
}
class Horse {
  int row;
  int col;
  int direction;

  Horse(int row, int col, int direction) {
    this.row = row;
    this.col = col;
    this.direction = direction;
  }
}