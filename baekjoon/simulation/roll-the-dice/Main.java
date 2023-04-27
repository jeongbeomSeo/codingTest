import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  // 주사위 숫자
  static int[] dice = new int[6 + 1];
  // Index: 동, 서, 북, 남
  static int[] dx = {0, 0, 0, -1, 1};
  static int[] dy = {0, 1, -1, 0, 0};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int x = Integer.parseInt(st.nextToken());
    int y = Integer.parseInt(st.nextToken());

    int K = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[] events = new int[K];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < K; i++) {
      events[i] = Integer.parseInt(st.nextToken());
    }

    simulation(grid, events, x, y, N, M, K);
  }
  static void simulation(int[][] grid, int[] events, int x, int y, int N, int M, int K) {

     for (int turn = 0; turn < K; turn++) {
       int direction = events[turn];

       int ax = x + dx[direction];
       int ay = y + dy[direction];

       if (!isValidIdx(ax, ay, N, M)) continue;
       else {
         x = ax;
         y = ay;
       }

       moveDice(direction);
       if (grid[x][y] != 0) {
         dice[1] = grid[x][y];
         grid[x][y] = 0;
       }
       else grid[x][y] = dice[1];
       System.out.println(dice[6]);
     }

  }

  static void moveDice(int direction) {
    int temp = dice[1];
    switch (direction) {
      case 1:
        dice[1] = dice[3];
        dice[3] = dice[6];
        dice[6] = dice[4];
        dice[4] = temp;
        break;
      case 2:
        dice[1] = dice[4];
        dice[4] = dice[6];
        dice[6] = dice[3];
        dice[3] = temp;
        break;
      case 3:
        dice[1] = dice[2];
        dice[2] = dice[6];
        dice[6] = dice[5];
        dice[5] = temp;
        break;
      case 4:
        dice[1] = dice[5];
        dice[5] = dice[6];
        dice[6] = dice[2];
        dice[2] = temp;
        break;
    }
  }
  static boolean isValidIdx(int x, int y, int N, int M) {
    if (x < 0 || y < 0 || x > N - 1 || y > M - 1) return false;
    else return true;
  }
}
