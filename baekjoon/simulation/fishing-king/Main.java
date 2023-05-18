import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int R, C, M;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    Shark.R = R = Integer.parseInt(st.nextToken());
    Shark.C = C = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    Shark[] sharks = new Shark[M];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      int speed = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());
      int size = Integer.parseInt(st.nextToken());

      sharks[i] = new Shark(row, col, speed, direction, size);
    }

    System.out.println(simulation(sharks));
  }
  static int simulation(Shark[] sharks) {

    int sum = 0;
    int col = 1;

    while (col <= C) {
      sum += fishing(sharks, col++);

      active(sharks);
    }
    return sum;
  }
  static int fishing(Shark[] sharks, int col) {

    int minIdx = -1;
    for (int i = 0; i < M; i++) {
      if (!sharks[i].dead && sharks[i].col == col) {
        if (minIdx == -1) minIdx = i;
        else if (sharks[i].row < sharks[minIdx].row) minIdx = i;
      }
    }

    if (minIdx == -1) return 0;

    sharks[minIdx].dead = true;
    return sharks[minIdx].size;
  }
  static void active(Shark[] sharks) {

    int[][] buffer = new int[R + 1][C + 1];

    for (int i = 0; i < M; i++) {
      if (sharks[i].dead) continue;

      Shark curShark = sharks[i];

      curShark.move();

      if (buffer[curShark.row][curShark.col] == 0) buffer[curShark.row][curShark.col] = i + 1;
      else {
        int otherSharkIdx = buffer[curShark.row][curShark.col] - 1;
        if (sharks[otherSharkIdx].size < curShark.size) {
          sharks[otherSharkIdx].dead = true;
          buffer[curShark.row][curShark.col] = i + 1;
        }
        else curShark.dead = true;
      }
    }
  }
}
class Shark {
  static final int[] dr = {0, -1, 1, 0, 0};
  static final int[] dc = {0, 0, 0, 1, -1};
  boolean dead = false;
  static int R;
  static int C;
  int row;
  int col;
  int speed;
  int direction;
  int size;

  Shark(int row, int col, int speed, int direction, int size) {
    this.row = row;
    this.col = col;
    this.direction = direction;
    this.size = size;
    if (direction == 1 || direction == 2) {
      this.speed = speed % ((R - 1) * 2);
    }
    else {
      this.speed = speed % ((C - 1) * 2);
    }
  }

  void move() {
    int speed = this.speed;

    while (speed-- > 0) {
      if (!isValidIdx(row + dr[direction],col + dc[direction])) direction = changeDirection(direction);
      row += dr[direction];
      col += dc[direction];
    }
  }

  boolean isValidIdx(int row, int col) {
    return row >= 1 && col >= 1 && row <= R && col <= C;
  }

  int changeDirection(int direction) {
    if (direction == 1) return 2;
    else if (direction == 2) return 1;
    else if (direction == 3) return 4;
    else return 3;
  }

}