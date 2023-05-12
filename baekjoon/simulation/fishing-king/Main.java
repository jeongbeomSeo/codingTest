import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int R = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

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

    Grid grid = new Grid(R, C, sharks);
    FishingMan fishingMan = new FishingMan();

    System.out.println(simulation(grid, fishingMan, sharks, C));
  }
  static int simulation(Grid grid, FishingMan fishingMan, Shark[] sharks, int C) {

    while (fishingMan.col <= C) {
      fishingMan.count += grid.fishing(fishingMan.row, fishingMan.col);
      grid.active(sharks);
      fishingMan.col++;
    }

    return fishingMan.count;
  }
}
class FishingMan {
  int row;
  int col;
  int count;

  FishingMan() {
    this.row = 1;
    this.col = 1;
    this.count = 0;
  }

}

class Grid {

  Square[][] squares;
  int R;
  int C;

  Grid(int R, int C, Shark[] sharks) {
    squares = new Square[R + 1][C + 1];
    this.R = R;
    this.C = C;
    for (int i = 1; i <= R; i++)
      for (int j = 1; j <= C; j++) squares[i][j] = new Square();
    for (Shark shark : sharks) squares[shark.getRow()][shark.getCol()].init(shark);
  }

  int fishing(int row, int col) {

    while (row <= R && squares[row][col].isEmpty()) row++;
    if (row <= R) {
      return squares[row][col].removeShark();
    }
    else return 0;
  }

  void active(Shark[] sharks) {
    for (int i = 1; i <= R; i++) {
      for (int j = 1; j <= C; j++) {
        squares[i][j].move_active(squares, R, C);
      }
    }

    for (int i = 1; i <= R; i++) {
      for (int j = 1; j <= C; j++) {
        squares[i][j].meal_active(sharks);
      }
    }
  }



}

class Square {
  private int size;
  private ArrayList<Integer> idList;
  private Shark shark = null;

  Square() {
    this.size = 0;
    idList = new ArrayList<>();
    shark = null;
  }

  void init(Shark shark) {
    this.shark = shark;
  }

  int removeShark() {
    int size = shark.getSize();
    shark.dead();
    shark = null;
    return size;
  }

  boolean isEmpty(){
    return shark == null;
  }

  private void add(Shark shark) {
    size++;
    idList.add(shark.get_id());
  }

  private void goAway() {
    shark = null;
  }

  private boolean oneMoreShark () {
    return size >= 2;
  }

  private void mealTime(Shark[] sharks) {
    if (oneMoreShark()) {
      for (int i = 0; i < size; i++) {
        int cur_id = idList.get(i);
        for (Shark eachShark : sharks) {
          if (eachShark.get_id() == cur_id) {
            if (eachShark.isDead()) break;
            if (shark == null) shark = eachShark;
            else if (eachShark.getSize() > shark.getSize()) {
              shark.dead();
              shark = eachShark;
            }
            else eachShark.dead();
            break;
          }
        }
      }
      idList.clear();
      size = 0;
    }
  }

  void move_active(Square[][] squares, int R, int C) {
    if (isEmpty()) return;
    int origin_row = shark.getRow();
    int origin_col = shark.getCol();
    shark.move(R, C);
    squares[shark.getRow()][shark.getCol()].add(shark);
    squares[origin_row][origin_col].goAway();
  }

  void meal_active(Shark[] sharks) {
    if (size == 1) {
      for (Shark eachShark : sharks) {
        if (eachShark.get_id() == idList.get(0)) {
          if (eachShark.isDead()) break;
          shark = eachShark;
          idList.clear();
          size = 0;
          break;
        }
      }
    }
    else if (size > 1) mealTime(sharks);
  }
}

class Shark {
  // 공백, 북, 남, 동, 서
  private static final int[] dr = {0, -1, 1, 0, 0};
  private static final int[] dc = {0, 0, 0, 1, -1};
  private static int id = 0;
  private final int _id;
  private int row;
  private int col;
  private final int speed;
  private int direction;
  private final int size;
  private boolean isDead;

  Shark(int row, int col, int speed, int direction, int size) {
    _id = id++;
    this.row = row;
    this.col = col;
    this.speed = speed;
    this.direction = direction;
    this.size = size;
    this.isDead = false;
  }

  public void dead() {
    isDead = true;
  }

  public boolean isDead() {
    return isDead;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public int get_id() {
    return _id;
  }

  public int getSize() {
    return size;
  }

  void move (int R, int C){
    int count = speed;
    while (count-- > 0) {
      int nextRow = row + dr[direction];
      int nextCol = col + dc[direction];

      if (isValidIdx(nextRow, nextCol, R, C)) {
        row = nextRow;
        col = nextCol;
      }
      else {
        reverseDirection();
        nextRow = row + dr[direction];
        nextCol = col + dc[direction];
        row = nextRow;
        col = nextCol;
      }
    }
  }

  void reverseDirection() {
    if (direction == 1) direction = 2;
    else if (direction == 2) direction = 1;
    else if (direction == 3) direction = 4;
    else direction = 3;
  }

  private boolean isValidIdx(int row, int col, int R, int C) {
    return  row >= 1 && col >= 1 && row <= R && col <= C;
  }

}
