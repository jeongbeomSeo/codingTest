# 낚시왕 

**골드 1**

|시간 제한	|메모리 제한	|제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|512 MB	|43259	|12882|	7396	|26.423%|

## 문제 

낚시왕이 상어 낚시를 하는 곳은 크기가 R×C인 격자판으로 나타낼 수 있다. 격자판의 각 칸은 (r, c)로 나타낼 수 있다. r은 행, c는 열이고, (R, C)는 아래 그림에서 가장 오른쪽 아래에 있는 칸이다. 칸에는 상어가 최대 한 마리 들어있을 수 있다. 상어는 크기와 속도를 가지고 있다.

![](https://upload.acmicpc.net/85c2ccad-e4b8-4397-9bd6-0ec73b0f44f8/-/preview/)

낚시왕은 처음에 1번 열의 한 칸 왼쪽에 있다. 다음은 1초 동안 일어나는 일이며, 아래 적힌 순서대로 일어난다. 낚시왕은 가장 오른쪽 열의 오른쪽 칸에 이동하면 이동을 멈춘다.

1. 낚시왕이 오른쪽으로 한 칸 이동한다.
2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
3. 상어가 이동한다.

상어는 입력으로 주어진 속도로 이동하고, 속도의 단위는 칸/초이다. 상어가 이동하려고 하는 칸이 격자판의 경계를 넘는 경우에는 방향을 반대로 바꿔서 속력을 유지한채로 이동한다.

왼쪽 그림의 상태에서 1초가 지나면 오른쪽 상태가 된다. 상어가 보고 있는 방향이 속도의 방향, 왼쪽 아래에 적힌 정수는 속력이다. 왼쪽 위에 상어를 구분하기 위해 문자를 적었다.

![](https://upload.acmicpc.net/d03be3c0-057d-47f7-9808-202ae36a3da3/-/preview/)

상어가 이동을 마친 후에 한 칸에 상어가 두 마리 이상 있을 수 있다. 이때는 크기가 가장 큰 상어가 나머지 상어를 모두 잡아먹는다.

낚시왕이 상어 낚시를 하는 격자판의 상태가 주어졌을 때, 낚시왕이 잡은 상어 크기의 합을 구해보자.

## 입력 

첫째 줄에 격자판의 크기 R, C와 상어의 수 M이 주어진다. (2 ≤ R, C ≤ 100, 0 ≤ M ≤ R×C)

둘째 줄부터 M개의 줄에 상어의 정보가 주어진다. 상어의 정보는 다섯 정수 r, c, s, d, z (1 ≤ r ≤ R, 1 ≤ c ≤ C, 0 ≤ s ≤ 1000, 1 ≤ d ≤ 4, 1 ≤ z ≤ 10000) 로 이루어져 있다. (r, c)는 상어의 위치, s는 속력, d는 이동 방향, z는 크기이다. d가 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽을 의미한다.

두 상어가 같은 크기를 갖는 경우는 없고, 하나의 칸에 둘 이상의 상어가 있는 경우는 없다.

## 출력 

낚시왕이 잡은 상어 크기의 합을 출력한다.

## 예제 입력 1

```
4 6 8
4 1 3 3 8
1 3 5 2 9
2 4 8 4 1
4 5 0 1 4
3 3 1 2 7
1 5 8 4 3
3 6 2 1 2
2 2 2 3 5
```

## 예제 출력 1

```
22
```

각 칸의 왼쪽 아래에 적힌 수는 속력, 오른쪽 아래는 크기, 왼쪽 위는 상어를 구분하기 위한 문자이다. 오른쪽 위에 ❤️는 낚시왕이 잡은 물고기 표시이다.

![](https://upload.acmicpc.net/2cdb3192-ef2b-4a73-a10e-4eca1680d45f/-/preview/)
초기 상태 

![](https://upload.acmicpc.net/6d04f922-513e-4999-9e55-b900eb5daa26/-/preview/)
1초

![](https://upload.acmicpc.net/6dde494f-feb0-4d4d-9e3a-212fe512d086/-/preview/)
2초 (E번 상어는 B번에게 먹혔다)

![](https://upload.acmicpc.net/746cf841-05d5-4f5c-83c4-a4ddaf6e48d1/-/preview/)
3초 

![](https://upload.acmicpc.net/3341d3d9-6ce5-486f-a1d4-310c0acd43bc/-/preview/)
4초

![](https://upload.acmicpc.net/92605c98-a0ae-4d57-bce4-abb6dab2a7bf/-/preview/)
5초

![](https://upload.acmicpc.net/a4cea7b1-aa0d-4caa-b6ca-c4b3221601bd/-/preview/)
6초

## 예제 입력 2

```
100 100 0
```

## 예제 출력 2

```
0
```

## 예제 입력 3

```
4 5 4
4 1 3 3 8
1 3 5 2 9
2 4 8 4 1
4 5 0 1 4
```

## 예제 출력 3

```
22
```

## 예제 입력 4

```
2 2 4
1 1 1 1 1
2 2 2 2 2
1 2 1 2 3
2 1 2 1 4
```

## 예제 출력 4

```
4
```

## 복습 포인트

어떤 로직 오류를 범하고 있나요?

```java
private static int[] getNextPoint(int row, int col, int direction, int speed, int R, int C) {

    int nextRow = row;
    int nextCol = col;
    
    while (speed-- != 0) {
      nextRow += dr[direction];
      nextCol += dc[direction];
      if (!isValidPoint(nextRow, nextCol, R, C)) {
        direction = reverseDirection(direction);
        nextRow += dr[direction];
        nextCol += dc[direction];
      }
    }

    return new int[]{nextRow, nextCol, direction};
}
```

내부적으로 로직을 끊내놓고 현재 위치에 적용하는 방식으로 로직을 구성하는 습관을 들이자.

즉, 함수의 형태로 만들 수 있도록 확장성을 고려하고 기존 변수의 수정이 로직 안에서 이루어 지는 것을 최대한 피하는 코드를 구성하는 것이 실수를 덜하는 길일 것 같다.


## 코드 

**TLE**

```java
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
```

Array를 ArrayList로 바꿔서 죽었을 경우 요소를 삭제하는 방식으로 구현을 해보았지만 해당 방식도 시간 초과가 나왔습니다.

**TLE**

```java
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

    ArrayList<Shark> sharks = new ArrayList<>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      int speed = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());
      int size = Integer.parseInt(st.nextToken());

      sharks.add(new Shark(row, col, speed, direction, size));
    }

    Grid grid = new Grid(R, C, sharks);
    FishingMan fishingMan = new FishingMan();

    System.out.println(simulation(grid, fishingMan, sharks, C));
  }
  static int simulation(Grid grid, FishingMan fishingMan, ArrayList<Shark> sharks, int C) {

    while (fishingMan.col <= C) {
      fishingMan.count += grid.fishing(fishingMan.row, fishingMan.col, sharks);
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

  Grid(int R, int C, ArrayList<Shark> sharks) {
    squares = new Square[R + 1][C + 1];
    this.R = R;
    this.C = C;
    for (int i = 1; i <= R; i++)
      for (int j = 1; j <= C; j++) squares[i][j] = new Square();
    for (Shark shark : sharks) squares[shark.getRow()][shark.getCol()].init(shark);
  }

  int fishing(int row, int col, ArrayList<Shark> sharks) {

    while (row <= R && squares[row][col].isEmpty()) row++;
    if (row <= R) {
      return squares[row][col].removeShark(sharks);
    }
    else return 0;
  }

  void active(ArrayList<Shark> sharks) {
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
  private Shark shark;

  Square() {
    this.size = 0;
    idList = new ArrayList<>();
    shark = null;
  }

  void init(Shark shark) {
    this.shark = shark;
  }

  int removeShark(ArrayList<Shark> sharks) {
    int size = shark.getSize();
    shark.dead(sharks);
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

  private void mealTime(ArrayList<Shark> sharks) {
    if (oneMoreShark()) {
      for (int i = 0; i < size; i++) {
        int cur_id = idList.get(i);
        for (Shark eachShark : sharks) {
          if (eachShark.get_id() == cur_id) {
            if (eachShark.isDead()) break;
            if (shark == null) shark = eachShark;
            else if (eachShark.getSize() > shark.getSize()) {
              shark.dead(sharks);
              shark = eachShark;
            }
            else eachShark.dead(sharks);
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

  void meal_active(ArrayList<Shark> sharks) {
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

  public void dead(ArrayList<Shark> sharks) {
    for (int i = 0; i < sharks.size(); i++) {
      if (sharks.get(i).get_id() == this._id) {
        sharks.remove(i);
        break;
      }
    }
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
```

grid 배열 탐색을 사용하지 않고, Shark의 정보를 저장하고 있는 배열을 탐색하는 방식으로 진행했으며, 이동 과정에서 배열의 인덱스 값을 int[][] 표에 저장해 두었다가 같은 위치로 이동하는 상어가 나오면 크기를 비교해서 삭제해주는 방식을 선택했다.

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
  // 공백, 북, 남, 동, 서
  static int[] dr = {0, -1, 1, 0, 0};
  static int[] dc = {0, 0, 0, 1, -1};

  static int R, C;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    ArrayList<Shark> sharks = new ArrayList<>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      int speed = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());
      int size = Integer.parseInt(st.nextToken());

      sharks.add(new Shark(row, col, speed, direction, size));
    }

    System.out.println(simulation(sharks, M));
  }
  static int simulation(ArrayList<Shark> sharks, int M) {

    int count_size = 0;
    int col = 1;
    for (; col <= C; col++) {

      // 상어 잡이
      int idx = fishing(sharks, col);

      if (idx != -1) {
        count_size += sharks.get(idx).size;
        sharks.remove(idx);
      }

      // 상어 이동
      active_move(sharks);
    }
    return count_size;
  }
  static int fishing(ArrayList<Shark> sharks, int col) {
    // 상어 잡이
    int idx = -1;
    for (int i = 0; i < sharks.size(); i++) {
      if (sharks.get(i).col == col) {
        if (idx == -1) idx = i;
        else {
          if (sharks.get(idx).row > sharks.get(i).row) idx = i;
        }
      }
    }
    return idx;
  }
  static void active_move(ArrayList<Shark> sharks) {

    int[][] buffer = new int[R + 1][C + 1];
    ArrayList<Integer> removeList = new ArrayList<>();

    for (int i = 0; i < sharks.size(); i++) {
      Shark shark = sharks.get(i);

      int count = 1;
      while (count++ <= shark.speed) {
        int row = shark.row + dr[shark.direction];
        int col = shark.col + dc[shark.direction];
        if (!isValidIdx(row, col)) {
          shark.direction = reverse(shark.direction);
          row = shark.row + dr[shark.direction];
          col = shark.col + dc[shark.direction];
        }

        shark.row = row;
        shark.col = col;
      }
      if (buffer[shark.row][shark.col] == 0) buffer[shark.row][shark.col] = i + 1;
      else {
        int prevSharkIdx = buffer[shark.row][shark.col] - 1;
        if (sharks.get(prevSharkIdx).size < shark.size) {
          buffer[shark.row][shark.col] = i + 1;
          removeList.add(prevSharkIdx);
        }
        else removeList.add(i);
      }
    }
    removeList.sort(Collections.reverseOrder());
    for (Integer integer : removeList) {
      sharks.remove((int) integer);
    }
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 1 && col >= 1 && row <= R && col <= C;
  }
  static int reverse(int direction) {
    if (direction == 1) return 2;
    else if (direction == 2) return 1;
    else if (direction == 3) return 4;
    else return 3;
  }
}

class Shark {
  int row;
  int col;
  int speed;
  int direction;
  int size;

  Shark(int row, int col, int speed, int direction, int size) {
    this.row = row;
    this.col = col;
    this.speed = speed;
    this.direction = direction;
    this.size = size;
  }
}
```

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int R, C;
  static int[] dr = {-1, 1, 0, 0};
  static int[] dc = {0, 0, 1, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    List<Shark> sharks = new LinkedList<>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken()) - 1;
      int col = Integer.parseInt(st.nextToken()) - 1;
      int speed = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken()) - 1;
      int size = Integer.parseInt(st.nextToken());

      sharks.add(new Shark(row, col, speed, direction, size));
    }

    System.out.println(simulation(sharks));
  }
  static int simulation (List<Shark> sharks) {

    int col = 0;
    int sum = 0;
    while (col < C) {
       sum += fishing(sharks, col++);

       active(sharks);
    }
    return sum;
  }
  static void active(List<Shark> sharks) {
    Shark[][] shark_Grid = new Shark[R][C];

    for (int i = sharks.size() - 1; i >= 0; i--) {
      Shark shark = sharks.get(i);

      active_move(shark);

      active_competition(sharks, shark, shark_Grid);
    }
  }
  static void active_competition (List<Shark> sharks, Shark shark, Shark[][] shark_Grid){
    if (shark_Grid[shark.row][shark.col] == null)
      shark_Grid[shark.row][shark.col] = shark;
    else {
      if (shark.size > shark_Grid[shark.row][shark.col].size) {
        sharks.remove(shark_Grid[shark.row][shark.col]);
        shark_Grid[shark.row][shark.col] = shark;
      }
      else {
        sharks.remove(shark);
      }
    }
  }
  static void active_move(Shark shark) {
    int speed = shark.speed;

    while (speed-- > 0) {
      if (!isValidIdx(shark.row + dr[shark.direction], shark.col + dc[shark.direction])) {
        shark.direction = changeDirection(shark.direction);
      }

      shark.row += dr[shark.direction];
      shark.col += dc[shark.direction];
    }
  }
  static int fishing(List<Shark> sharks, int col) {

    int minIdx = -1;
    for (int i = 0; i < sharks.size(); i++) {
      if (sharks.get(i).col == col) {
        if (minIdx == -1) minIdx = i;
        else if (sharks.get(minIdx).row > sharks.get(i).row)
          minIdx = i;
      }
    }
    if (minIdx != -1) {
      int size = sharks.get(minIdx).size;
      sharks.remove(minIdx);
      return size;
    }
    else return 0;
  }
  static int changeDirection(int direction) {
    if (direction == 0) return 1;
    else if (direction == 1) return 0;
    else if (direction == 2) return 3;
    else return 2;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < R && col < C;
  }
}
class Shark {
  int row;
  int col;
  int speed;
  int direction;
  int size;

  Shark (int row, int col, int speed, int direction, int size) {
    this.row = row;
    this.col = col;
    this.direction = direction;
    this.speed = speed;
    this.size = size;
  }
}
```

그냥 **Speed**를 최적화 시켜주면 되었다.. 비슷한 문제에서 이동 문제가 나올 때 한 턴에 몇번 이동하는지 잘 확인하자.

**AC**

```java
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
```

**더 직관적인 코드**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  private static int[] dr = {0, -1, 1, 0, 0};
  private static int[] dc = {0, 0, 0, 1, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int R = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    Shark[] sharks = new Shark[M + 1];
    int[][] sharkGrid = new int[R + 1][C + 1];

    for (int i = 1; i <= M; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      int speed = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());
      int size = Integer.parseInt(st.nextToken());

      sharks[i] = new Shark(i, row, col, speed, direction, size, R, C);
      sharkGrid[row][col] = i;
    }

    System.out.println(simulation(sharks, sharkGrid, R, C, M));
  }
  private static long simulation(Shark[] sharks, int[][] sharkGrid, int R, int C, int M) {

    King king = new King();

    while (king.col != C) {

      king.move();

      int sharkIdx = getLiveSharkIdxWhenSpecCol(sharks, sharkGrid, king.col, R);
      if (sharkIdx != 0) king.count += deadShark(sharks, sharkGrid, sharkIdx);

      activeSharks(sharks, sharkGrid, R, C, M);
    }

    return king.count;
  }
  private static void activeSharks(Shark[] sharks, int[][] sharkGrid, int R, int C, int M) {

    int[][] sharkBuffer = new int[R + 1][C + 1];
    for (int i = 1; i <= M; i++) {
      if (sharks[i].isDead) continue;

      Shark shark = sharks[i];

      int[] nextPoint = getNextPoint(shark.row, shark.col, shark.direction, shark.speed, R, C);

      if (isSharkInPoint(nextPoint[0], nextPoint[1], sharkBuffer)) {
        Shark pointShark = sharks[sharkBuffer[nextPoint[0]][nextPoint[1]]];
        if (compareSize(shark, pointShark) > 0) {
          pointShark.isDead = true;
          sharkBuffer[nextPoint[0]][nextPoint[1]] = 0;
          moveShark(shark, sharkBuffer, nextPoint);
        } else {
          shark.isDead = true;
        }
      } else {
        moveShark(shark, sharkBuffer, nextPoint);
      }
    }
    gridCopy(sharkGrid, sharkBuffer, R, C);
  }
  private static void gridCopy(int[][] origin, int[][] copy, int R, int C) {

    for (int i = 0; i <= R; i++) {
      for (int j = 0; j <= C; j++) {
        origin[i][j] = copy[i][j];
      }
    }
  }
  private static void moveShark(Shark shark, int[][] sharkBuffer, int[] nextPoint) {

    shark.row = nextPoint[0];
    shark.col = nextPoint[1];
    shark.direction = nextPoint[2];

    sharkBuffer[shark.row][shark.col] = shark.idx;
  }
  private static boolean isSharkInPoint(int row, int col, int[][] sharkBuffer) {
    return sharkBuffer[row][col] != 0;
  }
  private static int compareSize(Shark shark, Shark compareShark) {
    return shark.size - compareShark.size;
  }
  private static int deadShark(Shark[] sharks, int[][] sharkGrid, int idx) {

    Shark deadShark = sharks[idx];
    deadShark.isDead = true;
    sharkGrid[deadShark.row][deadShark.col] = 0;

    return deadShark.size;
  }
  private static int getLiveSharkIdxWhenSpecCol(Shark[] sharks, int[][] sharkGrid, int col, int R) {

    for (int i = 1; i <= R; i++) {
      if (sharkGrid[i][col] != 0 && !sharks[sharkGrid[i][col]].isDead) {
        return sharkGrid[i][col];
      }
    }

    return 0;
  }
  private static int[] getNextPoint(int row, int col, int direction, int speed, int R, int C) {

    while (speed-- != 0) {
      int nextRow = row + dr[direction];
      int nextCol = col + dc[direction];
      if (!isValidPoint(nextRow, nextCol, R, C)) {
        direction = reverseDirection(direction);
        nextRow = row + dr[direction];
        nextCol = col + dc[direction];
      }

      row = nextRow;
      col = nextCol;
    }

    return new int[]{row, col, direction};
  }
  private static int reverseDirection(int direction) {
    if (direction == 1) return 2;
    if (direction == 2) return 1;
    if (direction == 3) return 4;
    return 3;
  }
  private static boolean isValidPoint(int row, int col, int R, int C) {
    return row >= 1 && col >= 1 && row <= R && col <= C;
  }
}
class King {
  int col;
  long count;

  King () {
    this.col = 0;
    this.count = 0;
  }

  public void move() {
    this.col += 1;
  }
}
class Shark {
  int idx;
  int row;
  int col;
  int speed;
  int direction;
  int size;
  boolean isDead;

  Shark (int idx, int row, int col, int speed, int direction, int size, int R, int C) {
    this.idx = idx;
    this.row = row;
    this.col = col;
    this.speed = moduleateSpeed(speed, direction, R, C);
    this.direction = direction;
    this.size = size;
    this.isDead = false;
  }

  private static int moduleateSpeed(int speed, int direction, int R, int C) {
    if (direction == 1 || direction == 2) {
      return speed % (2 * (R - 1));
    }

    return speed % (2 * (C - 1));
  }
}
```