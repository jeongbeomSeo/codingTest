import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  // 북, 동, 남, 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    String[][] grid = new String[N][M];
    Marble red = new Marble();
    Marble blue = new Marble();

    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      for (int j = 0; j < M; j++) {
        grid[i][j] = str.substring(j, j + 1);
        if (grid[i][j].equals("R")) red.setPosition(i, j);
        if (grid[i][j].equals("B")) blue.setPosition(i, j);
      }
    }
    System.out.println(simulation(grid, red, blue, N, M));
  }

  static int simulation(String[][] grid, Marble red, Marble blue, int N, int M) {

    Queue<Marble> q = new LinkedList<>();
    q.add(red);
    q.add(blue);

    boolean[][] isVisitedRedMarble = new boolean[N][M];
    isVisitedRedMarble[red.row][red.col] = true;
    while (!q.isEmpty()) {
      Marble redMarble = q.poll();
      Marble blueMarble = q.poll();

      for (int i = 0; i < 4; i++) {
        int nextRedMoveRow = dr[i];
        int nextBlueMoveRow = dr[i];
        int nextRedMoveCol = dc[i];
        int nextBlueMoveCol = dc[i];
        int r = 0;
        int b = 0;
        while (grid[redMarble.row + nextRedMoveRow * (r + 1)][redMarble.col + nextRedMoveCol * (r + 1)].equals(".")) r++;
        while (grid[blueMarble.row + nextBlueMoveRow * (b + 1)][blueMarble.col+ nextBlueMoveCol * (b + 1)].equals(".")) b++;
        while (grid[redMarble.row + nextRedMoveRow * (r + 1)][redMarble.col + nextRedMoveCol * (r + 1)].equals(".")) r++;
        while (grid[blueMarble.row + nextBlueMoveRow * (b + 1)][blueMarble.col+ nextBlueMoveCol * (b + 1)].equals(".")) b++;

        int nextRedRow = redMarble.row + nextRedMoveRow * r;
        int nextRedCol = redMarble.col + nextRedMoveCol * r;
        int nextBlueRow = blueMarble.row + nextBlueMoveRow * b;
        int nextBlueCol = blueMarble.col+ nextBlueMoveCol * b;

        if (grid[nextBlueRow][nextBlueCol].equals("O")) continue;
        if (grid[nextRedRow][nextRedCol].equals("O")) return redMarble.time + 1;

        if (!isVisitedRedMarble[nextRedRow][nextRedCol]) {
          q.add(new Marble(nextRedRow, nextRedCol, redMarble.time + 1));
          q.add(new Marble(nextBlueRow, nextBlueCol, blueMarble.time + 1));
          isVisitedRedMarble[nextRedRow][nextRedCol] = true;
        }
      }
    }
    return -1;
  }
}

class Marble {
  int row;
  int col;
  int time = 0;

  Marble() {
    time = 0;
  };

  Marble(int row, int col, int time) {
    this.row = row;
    this.col = col;
    this.time = time;
  }

  void setPosition(int row, int col) {
    this.row = row;
    this.col = col;
  }
}


