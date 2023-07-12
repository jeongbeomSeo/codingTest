import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] dr = {0, -1, 0, 1, 0};
  static int[] dc = {0, 0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    int gas = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N + 1][N + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());

    Taxi taxi = new Taxi(row, col, gas);
    Passenger[] passengers = new Passenger[M + 2];
    for (int i = 2; i < M + 2; i++) {
      st = new StringTokenizer(br.readLine());
      int startRow = Integer.parseInt(st.nextToken());
      int startCol = Integer.parseInt(st.nextToken());
      int endRow = Integer.parseInt(st.nextToken());
      int endCol = Integer.parseInt(st.nextToken());

      grid[startRow][startCol] = i;

      passengers[i] = new Passenger(startRow, startCol, endRow, endCol);
    }

    System.out.println(simulation(grid, passengers, taxi));

  }
  static int simulation(int[][] grid, Passenger[] passengers, Taxi taxi) {

    int count = 0;

    while (count < M) {

      int passengerIdx = findPassenger(grid, taxi);

      if (passengerIdx == -1) return -1;
      else grid[taxi.row][taxi.col] = 0;

      if (!activeTaxi(grid, passengers[passengerIdx], taxi)) return -1;

      count++;
    }

    return taxi.gas;
  }
  static boolean activeTaxi(int[][] grid, Passenger passenger, Taxi taxi) {

    Queue<Taxi> q = new ArrayDeque<>();
    boolean[][] isVisited = new boolean[N + 1][N + 1];
    q.add(new Taxi(taxi.row, taxi.col, taxi.gas));
    isVisited[taxi.row][taxi.col] = true;

    Taxi result = null;
    while (!q.isEmpty()) {
      Taxi curTaxi = q.poll();

      if (curTaxi.row == passenger.endRow && curTaxi.col == passenger.endCol) {
        result = curTaxi;
        break;
      }

      if (curTaxi.gas == 0) continue;

      for (int i = 1; i <= 4; i++) {
        int nextRow = curTaxi.row + dr[i];
        int nextCol = curTaxi.col + dc[i];

        if (isValidIdx(nextRow, nextCol)
                && !isVisited[nextRow][nextCol]
                && grid[nextRow][nextCol] != 1) {
          q.add(new Taxi(nextRow, nextCol, curTaxi.gas - 1));
          isVisited[nextRow][nextCol] = true;
        }
      }
    }

    if (result == null) return false;
    else {
      taxi.row = result.row;
      taxi.col = result.col;
      taxi.gas = result.gas + (taxi.gas - result.gas) * 2;
    }

    return true;
  }
  static int findPassenger(int[][] grid, Taxi taxi) {

    Queue<Taxi> q = new ArrayDeque<>();
    boolean[][] isVisited = new boolean[N + 1][N + 1];
    q.add(new Taxi(taxi.row, taxi.col, taxi.gas));
    isVisited[taxi.row][taxi.col] = true;

    Taxi result = null;
    while (!q.isEmpty()) {
      Taxi curTaxi = q.poll();

      if (result != null && result.gas != curTaxi.gas) break;

      if (grid[curTaxi.row][curTaxi.col] >= 2) {
        if (result == null) result = curTaxi;
        else if (result.compare(curTaxi) > 0) result = curTaxi;
        continue;
      }

      if (curTaxi.gas == 0) continue;

      for (int i = 1; i <= 4; i++) {
        int nextRow = curTaxi.row + dr[i];
        int nextCol = curTaxi.col + dc[i];

        if (isValidIdx(nextRow, nextCol)
                && !isVisited[nextRow][nextCol]
                && grid[nextRow][nextCol] != 1) {
          q.add(new Taxi(nextRow, nextCol, curTaxi.gas - 1));
          isVisited[nextRow][nextCol] = true;
        }
      }
    }

    if (result == null) return -1;
    else {
      taxi.row = result.row;
      taxi.col = result.col;
      taxi.gas = result.gas;
    }

    return grid[taxi.row][taxi.col];
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 1 && col >= 1 && row <= N && col <= N;
  }
}
class Taxi {
  int row;
  int col;
  int gas;

  Taxi(int row, int col, int gas) {
    this.row = row;
    this.col = col;
    this.gas = gas;
  }

  int compare(Taxi o) {
    if (this.row != o.row) return this.row - o.row;
    else return this.col - o.col;
  }
}
class Passenger {
  int startRow;
  int startCol;
  int endRow;
  int endCol;

  Passenger(int startRow, int startCol, int endRow, int endCol) {
    this.startRow = startRow;
    this.startCol = startCol;
    this.endRow = endRow;
    this.endCol = endCol;
  }
}