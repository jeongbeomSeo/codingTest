import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
  static int N, M, K;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    Queue<Fireball> q = new ArrayDeque<>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      int row = Integer.parseInt(st.nextToken()) - 1;
      int col = Integer.parseInt(st.nextToken()) - 1;
      int mass = Integer.parseInt(st.nextToken());
      int speed = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());

      q.add(new Fireball(row, col, mass, speed, direction));
    }

    System.out.println(simulation(q));
  }
  static int simulation(Queue<Fireball> q) {
    Queue<Fireball>[][] grid = new Queue[N][N];
    Queue<Point> idxList = new ArrayDeque<>();

    while (K-- > 0) {
      active_fireball(q, grid, idxList);
      check_fireball(q, grid, idxList);
    }

    int sum = 0;
    while (!q.isEmpty()) sum += q.poll().mass;
    return sum;
  }
  static void active_fireball(Queue<Fireball> q, Queue<Fireball>[][] grid, Queue<Point> idxList) {

    while (!q.isEmpty()) {
      Fireball fireball = q.poll();

      // 이와 같이 처리하면 안됩니다.
      int remainMoveCount = fireball.speed % N;

      int nextRow = fireball.row + dr[fireball.direction] * remainMoveCount;
      int nextCol = fireball.col + dc[fireball.direction] * remainMoveCount;


      if (nextRow >= N) fireball.row = nextRow - N;
      else if(nextRow < 0) fireball.row = nextRow + N;
      else fireball.row = nextRow;

      if (nextCol >= N) fireball.col = nextCol - N;
      else if (nextCol < 0) fireball.col = nextCol + N;
      else fireball.col = nextCol;

      if (grid[fireball.row][fireball.col] == null) grid[fireball.row][fireball.col] = new ArrayDeque<>();

      if (grid[fireball.row][fireball.col].isEmpty()) {
        grid[fireball.row][fireball.col].add(fireball);
        idxList.add(new Point(fireball.row, fireball.col));
      }
      else grid[fireball.row][fireball.col].add(fireball);
    }
  }
  static void check_fireball(Queue<Fireball> q, Queue<Fireball>[][] grid, Queue<Point> idxList) {
    while (!idxList.isEmpty()) {
      Point point = idxList.poll();

      if (grid[point.row][point.col].size() == 1) {
        q.add(grid[point.row][point.col].poll());
      }
      else {
        int sumMass = 0;
        int sumSpeed = 0;
        int checkDirection = -1;
        boolean direction = true;
        int count = 0;
        while (!grid[point.row][point.col].isEmpty()) {
          Fireball fireball = grid[point.row][point.col].poll();

          sumMass += fireball.mass;
          sumSpeed += fireball.speed;
          if (checkDirection == -1) checkDirection = fireball.direction % 2;
          else {
            direction = (direction && checkDirection == fireball.direction % 2);
          }
          count++;
        }

        int resultMass = sumMass / 5;
        int resultSpeed = sumSpeed / count;
        if (resultMass == 0) continue;

        for (int i = 0; i <= 6; i += 2) {
          if (direction) q.add(new Fireball(point.row, point.col, resultMass, resultSpeed, i));
          else q.add(new Fireball(point.row, point.col, resultMass,  resultSpeed, i + 1));
        }
      }
    }
  }
}
class Point {
  int row;
  int col;

  Point(int row, int col) {
    this.row = row;
    this.col = col;
  }
}
class Fireball {
  int row;
  int col;
  int mass;
  int direction;
  int speed;

  Fireball(int row, int col, int mass, int speed,  int direction) {
    this.row = row;
    this.col = col;
    this.mass = mass;
    this.speed = speed;
    this.direction = direction;
  }
}
