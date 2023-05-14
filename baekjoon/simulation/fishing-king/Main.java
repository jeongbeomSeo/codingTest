import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static int R, C, M;
  static int[] dr = {-1, 1, 0, 0};
  static int[] dc = {0, 0, 1, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    List<Shark> sharks = new ArrayList<>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken()) - 1;
      int col = Integer.parseInt(st.nextToken()) - 1;
      int speed = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken()) - 1;
      int size = Integer.parseInt(st.nextToken());

      sharks.add(new Shark(row, col, speed, direction, size, R, C));
    }

    System.out.println(simulation(sharks));
  }
  static int simulation(List<Shark> sharks) {

    int col = 0;
    int sum = 0;
    while (col < C) {
      sum += fishing(sharks, col++);
      active(sharks);
    }
    return sum;
  }
  static void active(List<Shark> sharks) {

    int[][] buffer = new int[R][C];
    ArrayList<Integer> removeIdx = new ArrayList<>();

    for (int i = 0; i < sharks.size(); i++) {
      Shark shark = sharks.get(i);

      active_move(shark);
      if (buffer[shark.row][shark.col] == 0) buffer[shark.row][shark.col] = i + 1;
      else {
        int buffer_idx = buffer[shark.row][shark.col] - 1;
        if (sharks.get(buffer_idx).size < shark.size) {
          buffer[shark.row][shark.col] = i + 1;
          removeIdx.add(buffer_idx);
        }
        else removeIdx.add(i);
      }
    }

    removeIdx.sort(Collections.reverseOrder());
    for (int idx : removeIdx) {
      sharks.remove(idx);
    }
  }
  static void active_move(Shark shark) {

    int speed = shark.speed;

    while (speed-- > 0) {
      if (!isValidIdx(shark.row + dr[shark.direction], shark.col + dc[shark.direction])) {
        shark.direction = change_direction(shark.direction);
      }
      shark.row += dr[shark.direction];
      shark.col += dc[shark.direction];
    }

  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < R && col < C;
  }
  static int change_direction(int direction) {
    if (direction == 0) return 1;
    else if (direction == 1) return 0;
    else if (direction == 2) return 3;
    else return 2;
  }
  static int fishing(List<Shark> sharks, int col) {

    int minIdx = -1;
    for (int i = 0; i < sharks.size(); i++) {
      if (sharks.get(i).col == col) {
        if (minIdx == -1) minIdx = i;
        else if (sharks.get(i).row < sharks.get(minIdx).row) minIdx = i;
      }
    }

    if (minIdx == -1) return 0;

    int size = sharks.get(minIdx).size;
    sharks.remove(minIdx);
    return size;
  }
}
class Shark {
  int row;
  int col;
  int speed;
  int direction;
  int size;

  Shark(int row, int col, int speed, int direction, int size, int R, int C) {
    this.row = row;
    this.col = col;
    this.direction = direction;
    this.size = size;
    if (direction == 0 || direction == 1)
      this.speed = speed % ((R - 1) * 2);
    else
      this.speed = speed % ((C - 1) * 2);
  }
}