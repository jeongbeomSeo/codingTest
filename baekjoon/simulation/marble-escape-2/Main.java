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
  static int N, M;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    String[][] grid = new String[N][M];
    int rRow = 0;
    int rCol = 0;
    int bRow = 0;
    int bCol = 0;

    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      for (int j = 0; j < M; j++) {
        grid[i][j] = str.substring(j, j + 1);
        if (grid[i][j].equals("R")) {
          rRow = i;
          rCol = j;
          grid[i][j] = ".";
        }
        if (grid[i][j].equals("B")) {
          bRow = i;
          bCol = j;
          grid[i][j] = ".";
        }
      }
    }

    System.out.println(simulation(grid, rRow, rCol, bRow, bCol));
  }
  static int simulation(String[][] grid, int rRow, int rCol, int bRow, int bCol) {

    Queue<Case> q = new LinkedList();
    boolean[][][][] isVistied = new boolean[N][M][N][M];
    q.add(new Case(rRow, rCol, bRow, bCol, 0));
    isVistied[rRow][rCol][bRow][bCol] = true;


    while (!q.isEmpty()) {
      Case currentCase = q.poll();

      if (currentCase.time >= 10) break;

      Marble redMarble = currentCase.redMarble;
      Marble blueMarble = currentCase.blueMarble;

      for (int i = 0; i < 4; i++) {

        grid[redMarble.row][redMarble.col] = "R";
        grid[blueMarble.row][blueMarble.col] = "B";

        int rNum = 0;
        int bNum = 0;

        boolean fail = false;
        boolean isMove_RedMarble;
        boolean isMove_BlueMarble;

        do {
          isMove_RedMarble = false;
          isMove_BlueMarble = false;
          while (grid[redMarble.row + dr[i] * (rNum + 1)][redMarble.col + dc[i] * (rNum + 1)].equals(".")) {
            grid[redMarble.row + dr[i] * rNum][redMarble.col + dc[i] * rNum] = ".";
            isMove_RedMarble = true;
            rNum++;
            grid[redMarble.row + dr[i] * rNum][redMarble.col + dc[i] * rNum] = "R";
          }
          if (grid[redMarble.row + dr[i] * (rNum + 1)][redMarble.col + dc[i] * (rNum + 1)].equals("O")) {
            grid[redMarble.row + dr[i] * rNum][redMarble.col + dc[i] * rNum] = ".";
            boolean conCurrencyCheck = false;
            while (grid[blueMarble.row + dr[i] * (bNum + 1)][blueMarble.col + dc[i] * (bNum + 1)].equals(".")) {
              grid[blueMarble.row + dr[i] * bNum][blueMarble.col + dc[i] * bNum] = ".";
              bNum++;
              grid[blueMarble.row + dr[i] * bNum][blueMarble.col + dc[i] * bNum] = "B";
            }
            if (grid[blueMarble.row + dr[i] * (bNum + 1)][blueMarble.col + dc[i] * (bNum + 1)].equals("O")) conCurrencyCheck = true;
            if (!conCurrencyCheck) return currentCase.time + 1;
            else {
              fail = true;
              break;
            }
          }

          while (grid[blueMarble.row + dr[i] * (bNum + 1)][blueMarble.col + dc[i] * (bNum + 1)].equals(".")) {
            grid[blueMarble.row + dr[i] * bNum][blueMarble.col + dc[i] * bNum] = ".";
            isMove_BlueMarble = true;
            bNum++;
            grid[blueMarble.row + dr[i] * bNum][blueMarble.col + dc[i] * bNum] = "B";
          }
          if (grid[blueMarble.row + dr[i] * (bNum + 1)][blueMarble.col + dc[i] * (bNum + 1)].equals("O")) {
            fail = true;
            break;
          }
        } while (isMove_RedMarble || isMove_BlueMarble);

        if (fail) {
          grid[redMarble.row + dr[i] * rNum][redMarble.col + dc[i] * rNum] = ".";
          grid[blueMarble.row + dr[i] * bNum][blueMarble.col + dc[i] * bNum] = ".";
          continue;
        }

        int next_RedMarbleRow = redMarble.row + dr[i] * rNum;
        int next_RedMarbleCol = redMarble.col + dc[i] * rNum;
        int next_BlueMarbleRow = blueMarble.row + dr[i] * bNum;
        int next_BlueMarbleCol = blueMarble.col + dc[i] * bNum;

        if (!isVistied[next_RedMarbleRow][next_RedMarbleCol][next_BlueMarbleRow][next_BlueMarbleCol]) {
          q.add(new Case(next_RedMarbleRow, next_RedMarbleCol,
                  next_BlueMarbleRow, next_BlueMarbleCol, currentCase.time + 1));
          isVistied[next_RedMarbleRow][next_RedMarbleCol][next_BlueMarbleRow][next_BlueMarbleCol] = true;
        }
        grid[next_RedMarbleRow][next_RedMarbleCol] = ".";
        grid[next_BlueMarbleRow][next_BlueMarbleCol] = ".";
      }
    }
    return -1;
  }
}

class Case {
  Marble redMarble;
  Marble blueMarble;
  int time;

  Case(int rRow, int rCol, int bRow, int bCol, int time) {
    redMarble = new Marble(rRow, rCol);
    blueMarble = new Marble(bRow, bCol);
    this.time = time;
  }
}

class Marble {
  int row;
  int col;

  Marble(int row, int col) {
    this.row = row;
    this.col = col;
  }
}