import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N, M;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    int gas = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    st = new StringTokenizer(br.readLine());
    int initRow = Integer.parseInt(st.nextToken()) - 1;
    int initCol = Integer.parseInt(st.nextToken()) - 1;

    Taxi taxi = new Taxi(initRow, initCol, gas);
    int idx = 2;
    Passenger[] passengers = new Passenger[M + 2];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      int startRow = Integer.parseInt(st.nextToken()) - 1;
      int startCol = Integer.parseInt(st.nextToken()) - 1;
      int endRow = Integer.parseInt(st.nextToken()) - 1;
      int endCol = Integer.parseInt(st.nextToken()) - 1;

      grid[startRow][startCol] = idx;
      passengers[idx++] = new Passenger(startRow, startCol, endRow, endCol);
    }

    System.out.println(simulation(grid, passengers, taxi));
  }
  static int simulation(int[][] grid, Passenger[] passengers, Taxi taxi) {

    int count = 0;
    while (count < M) {

      int passengerIdx = findPassenger(grid, taxi);

      if (passengerIdx == -1) break;

      if (!goDest(grid, taxi, passengers[passengerIdx])) break;

      count++;
    }

    if (count != M) return -1;

    return taxi.gas;
  }
  static boolean goDest(int[][] grid, Taxi taxi, Passenger passenger) {

    boolean[][] isVisited = new boolean[N][N];
    Queue<Taxi> q = new ArrayDeque<>();
    q.add(new Taxi(taxi.row, taxi.col, taxi.gas));
    isVisited[taxi.row][taxi.col] = true;

    Taxi result = null;
    while (!q.isEmpty()) {
      Taxi curTaxi = q.poll();

      if (curTaxi.row == passenger.endPoint.row && curTaxi.col == passenger.endPoint.col) {
        result = curTaxi;
        break;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = curTaxi.row + dr[i];
        int nextCol = curTaxi.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && !isVisited[nextRow][nextCol] && grid[nextRow][nextCol] != 1) {
          q.add(new Taxi(nextRow, nextCol, curTaxi.gas - 1, curTaxi.moveCount + 1));
          isVisited[nextRow][nextCol] = true;
        }
      }
    }

    if (result == null || result.gas < 0) return false;
    else {
      taxi.row = result.row;
      taxi.col = result.col;
      taxi.gas = result.gas + 2 * result.moveCount;
      return true;
    }
  }
  // return Passenger Idx
  static int findPassenger(int[][] grid, Taxi taxi) {

    boolean[][] isVisited = new boolean[N][N];
    Queue<Taxi> q = new ArrayDeque<>();
    q.add(new Taxi(taxi.row, taxi.col, taxi.gas));
    isVisited[taxi.row][taxi.col] = true;

    Taxi result = null;
    while (!q.isEmpty()) {
      Taxi curTaxi = q.poll();

      if (result != null && result.gas != curTaxi.gas) break;

      if (grid[curTaxi.row][curTaxi.col] >= 2) {
        if (result == null) result = curTaxi;
        else {
          if (result.compare(curTaxi)) result = curTaxi;
        }
        continue;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = curTaxi.row + dr[i];
        int nextCol = curTaxi.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && !isVisited[nextRow][nextCol] && grid[nextRow][nextCol] != 1) {
          q.add(new Taxi(nextRow, nextCol, curTaxi.gas - 1));
          isVisited[nextRow][nextCol] = true;
        }
      }
    }


    if (result == null || result.gas < 0) return -1;
    else {
      int idx = grid[result.row][result.col];
      grid[result.row][result.col] = 0;
      taxi.row = result.row;
      taxi.col = result.col;
      taxi.gas = result.gas;
      return idx;
    }
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
}
class Taxi {
  int row;
  int col;
  int gas;
  int moveCount;

  Taxi (int row, int col, int gas) {
    this.row = row;
    this.col = col;
    this.gas = gas;
    this.moveCount = 0;
  }

  Taxi (int row, int col, int gas, int moveCount) {
    this(row, col, gas);
    this.moveCount = moveCount;
  }

  boolean compare(Taxi o) {
    if (this.row > o.row) return true;
    else if (this.row < o.row) return false;
    else return this.col > o.col;
  }
}
class Point {
  int row;
  int col;
  int gas;

  Point (int row, int col, int gas) {
    this.row = row;
    this.col = col;
    this.gas = gas;
  }
}
class Passenger {
  Point startPoint;
  Point endPoint;

  Passenger(int startRow, int startCol, int endRow, int endCol) {
    this.startPoint = new Point(startRow, startCol, 0);
    this.endPoint = new Point(endRow, endCol, 0);
  }
}