# 청소년 상어

**골드 2**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초 (추가 시간 없음)	|512 MB	|11210|	7022|	4467	|64.580%|

## 문제 

아기 상어가 성장해 청소년 상어가 되었다.

4×4크기의 공간이 있고, 크기가 1×1인 정사각형 칸으로 나누어져 있다. 공간의 각 칸은 (x, y)와 같이 표현하며, x는 행의 번호, y는 열의 번호이다. 한 칸에는 물고기가 한 마리 존재한다. 각 물고기는 번호와 방향을 가지고 있다. 번호는 1보다 크거나 같고, 16보다 작거나 같은 자연수이며, 두 물고기가 같은 번호를 갖는 경우는 없다. 방향은 8가지 방향(상하좌우, 대각선) 중 하나이다.

오늘은 청소년 상어가 이 공간에 들어가 물고기를 먹으려고 한다. 청소년 상어는 (0, 0)에 있는 물고기를 먹고, (0, 0)에 들어가게 된다. 상어의 방향은 (0, 0)에 있던 물고기의 방향과 같다. 이후 물고기가 이동한다.

물고기는 번호가 작은 물고기부터 순서대로 이동한다. 물고기는 한 칸을 이동할 수 있고, 이동할 수 있는 칸은 빈 칸과 다른 물고기가 있는 칸, 이동할 수 없는 칸은 상어가 있거나, 공간의 경계를 넘는 칸이다. 각 물고기는 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다. 만약, 이동할 수 있는 칸이 없으면 이동을 하지 않는다. 그 외의 경우에는 그 칸으로 이동을 한다. 물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾸는 방식으로 이동한다.

물고기의 이동이 모두 끝나면 상어가 이동한다. 상어는 방향에 있는 칸으로 이동할 수 있는데, 한 번에 여러 개의 칸을 이동할 수 있다. 상어가 물고기가 있는 칸으로 이동했다면, 그 칸에 있는 물고기를 먹고, 그 물고기의 방향을 가지게 된다. 이동하는 중에 지나가는 칸에 있는 물고기는 먹지 않는다. 물고기가 없는 칸으로는 이동할 수 없다. 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다. 상어가 이동한 후에는 다시 물고기가 이동하며, 이후 이 과정이 계속해서 반복된다.

![](https://upload.acmicpc.net/1c7c473e-5e2c-4c45-9c88-b3b7cd06a360/-/preview/)
<그림 1>

<그림 1>은 청소년 상어가 공간에 들어가기 전 초기 상태이다. 상어가 공간에 들어가면 (0, 0)에 있는 7번 물고기를 먹고, 상어의 방향은 ↘이 된다. <그림 2>는 상어가 들어간 직후의 상태를 나타낸다.

![](https://upload.acmicpc.net/8f26df12-6f68-43a3-9f6e-7416144e91dc/-/preview/)
<그림 2>

이제 물고기가 이동해야 한다. 1번 물고기의 방향은 ↗이다. ↗ 방향에는 칸이 있고, 15번 물고기가 들어있다. 물고기가 있는 칸으로 이동할 때는 그 칸에 있는 물고기와 위치를 서로 바꿔야 한다. 따라서, 1번 물고기가 이동을 마치면 <그림 3>과 같아진다.

![](https://upload.acmicpc.net/75315b3c-ee04-4ae8-9422-5b1137f86117/-/preview/)
<그림 3>

2번 물고기의 방향은 ←인데, 그 방향에는 상어가 있으니 이동할 수 없다. 방향을 45도 반시계 회전을 하면 ↙가 되고, 이 칸에는 3번 물고기가 있다. 물고기가 있는 칸이니 서로 위치를 바꾸고, <그림 4>와 같아지게 된다.

![](https://upload.acmicpc.net/7be317c7-b8b5-4b83-becb-ffd8550311fb/-/preview/)
<그림 4>

3번 물고기의 방향은 ↑이고, 존재하지 않는 칸이다. 45도 반시계 회전을 한 방향 ↖도 존재하지 않으니, 다시 회전을 한다. ← 방향에는 상어가 있으니 또 회전을 해야 한다. ↙ 방향에는 2번 물고기가 있으니 서로의 위치를 교환하면 된다. 이런 식으로 모든 물고기가 이동하면 <그림 5>와 같아진다.

![](https://upload.acmicpc.net/a58fbda0-bb64-4773-b5f9-2da0bd3f0fd2/-/preview/)
<그림 5>

물고기가 모두 이동했으니 이제 상어가 이동할 순서이다. 상어의 방향은 ↘이고, 이동할 수 있는 칸은 12번 물고기가 있는 칸, 15번 물고기가 있는 칸, 8번 물고기가 있는 칸 중에 하나이다. 만약, 8번 물고기가 있는 칸으로 이동하면, <그림 6>과 같아지게 된다.

![](https://upload.acmicpc.net/2431d117-fab6-4de9-8d76-2fb41d471ee7/-/crop/651x656/1,12/-/preview/)
<그림 6>

상어가 먹을 수 있는 물고기 번호의 합의 최댓값을 구해보자.

## 입력 

첫째 줄부터 4개의 줄에 각 칸의 들어있는 물고기의 정보가 1번 행부터 순서대로 주어진다. 물고기의 정보는 두 정수 ai, bi로 이루어져 있고, ai는 물고기의 번호, bi는 방향을 의미한다. 방향 bi는 8보다 작거나 같은 자연수를 의미하고, 1부터 순서대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗ 를 의미한다.

## 출력 

상어가 먹을 수 있는 물고기 번호의 합의 최댓값을 출력한다.

## 예제 입력 1

```
7 6 2 3 15 6 9 8
3 1 1 8 14 7 10 1
6 1 13 6 4 3 11 4
16 1 8 7 5 2 12 2
```

## 예제 출력 1

```
33
```

## 예제 입력 2

```
16 7 1 4 4 3 12 8
14 7 7 6 3 4 10 2
5 2 15 2 8 3 6 4
11 8 2 4 13 5 9 4
```

## 예제 출력 2

```
43
```

## 예제 입력 3

```
12 6 14 5 4 5 6 7
15 1 11 7 3 7 7 5
10 3 8 3 16 6 1 1
5 8 2 7 13 6 9 2
```

## 예제 출력 3

```
76
```

## 예제 입력 4

```
2 6 10 8 6 7 9 4
1 7 16 6 4 2 5 8
3 7 8 6 7 6 14 8
12 7 15 4 11 3 13 3
```

## 예제 출력 4

```
39
```

## 복습 포인즈

어떤 로직 오류를 범하고 있나요?

```java
private static int[] getNextPoint(Fish fish, Shark shark) {

    int row = fish.row;
    int col = fish.col;
    int direction = fish.direction;
    do {
      row = fish.row + dr[direction];
      col = fish.col + dc[direction];

      if (canMove(row, col, shark)) break;

      direction = nextDirection(direction);
    } while (direction != fish.direction);

    if (direction != fish.direction) return new int[]{row, col, direction};

    return new int[]{fish.row, fish.col, fish.direction};
  }
```

```java
  private static boolean canProgressNextSimulation(int[][] fishIdxGrid, Shark shark) {

    int row = shark.row;
    int col = shark.col;

    while (isValidPoint(row + dr[shark.direction], col + dc[shark.direction]) &&
            fishIdxGrid[row + dr[shark.direction]][col + dc[shark.direction]] == 0) {
      row = row + dr[shark.direction];
      col = col + dc[shark.direction];
    }

    return isValidPoint(row, col);
  }
```

변수의 이름을 목적에 맞게 정확히 사용하도록 하여서 실수를 줄이고, 위의 로직과 같은 구성으로 구현하는 것은 피하자.

왜냐하면, 변수의 이름이 너무나도 모호해지는 상황이 발생하는 것 같아서 그렇다. 

그리고 되도록이면 반복되는 로직이더라도 한번의 로직이 끝날 때 결과 값을 도출해내고 그것을 기존 변수에 적용하는 식으로 구현하자.

## 코드

**AC**

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MAX_VALUE;
  static int max = 0;
  static int[] dr = {INF, -1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dc = {INF, 0, -1, -1, -1, 0, 1, 1, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int[][] fish_id_grid = new int[4][4];
    Fish[] fishes = new Fish[4 * 4 + 1];

    for (int i = 0; i < 4; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 4; j++) {
        int id = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());
        fish_id_grid[i][j] = id;
        fishes[id] = new Fish(i, j, direction);
      }
    }

    System.out.println(simulation(fish_id_grid, fishes));
  }
  static int simulation(int[][] fish_id_grid, Fish[] fishes) {

    backtracking(fish_id_grid, fishes, new Shark(0, 0, 0), 0);

    return max;
  }
  static void backtracking(int[][] fish_id_grid, Fish[] fishes, Shark shark, int sum) {

    int id = fish_id_grid[shark.row][shark.col];
    sum += id;
    fish_id_grid[shark.row][shark.col] = 0;
    fishes[id].isDead = true;
    shark.direction = fishes[id].direction;

    fishMove(fish_id_grid, fishes, shark);

    boolean cantMove = true;
    while (isValidIdx(shark.row + dr[shark.direction], shark.col + dc[shark.direction])) {
      shark.row += dr[shark.direction];
      shark.col += dc[shark.direction];

      if (fish_id_grid[shark.row][shark.col] != 0) {
        cantMove = false;
        int[][] copy_grid = copy_grid(fish_id_grid);
        Fish[] copy_fish = copy_fish(fishes);
        backtracking(copy_grid, copy_fish, new Shark(shark.row, shark.col, shark.direction), sum);
      }
    }

    if (cantMove) max = Math.max(max, sum);
  }
  static int[][] copy_grid(int[][] fish_id_grid) {
    int[][] grid = new int[4][4];

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        grid[i][j] = fish_id_grid[i][j];
      }
    }
    return grid;
  }
  static Fish[] copy_fish (Fish[] fishes){
    Fish[] newFishList = new Fish[17];

    for (int i = 1; i <= 16; i++) newFishList[i] = new Fish(fishes[i].row, fishes[i].col, fishes[i].direction, fishes[i].isDead);

    return newFishList;
  }
  static void fishMove(int[][] fish_id_grid, Fish[] fishes, Shark shark) {

    for (int i = 1; i <= 16; i++) {
      Fish fish = fishes[i];

      if (!fish.isDead) {
        int baseDirection = fish.direction;
        do {
          int shark.row = fish.row + dr[fish.direction];
          int shark.col = fish.col + dc[fish.direction];

          if (isValidIdx(shark.row, shark.col) && !(shark.row == shark.row && shark.col == shark.col)) {
            if (fish_id_grid[shark.row][shark.col] != 0) {
              int otherFishId = fish_id_grid[shark.row][shark.col];
              fishes[otherFishId].row = fish.row;
              fishes[otherFishId].col = fish.col;
            }
            fish_id_grid[fish.row][fish.col] = fish_id_grid[shark.row][shark.col];
            fish.row = shark.row;
            fish.col = shark.col;
            fish_id_grid[fish.row][fish.col] = i;
            break;
          }
          else {
            fish.direction++;
            if (fish.direction == 9) fish.direction = 1;
          }
        } while (fish.direction != baseDirection);
      }
    }
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < 4 && col < 4;
  }
}
class Shark {
  int row;
  int col;
  int direction;

  Shark(int row, int col, int direction) {
    this.row = row;
    this.col = col;
    this.direction = direction;
  }
}
class Fish{
  int row;
  int col;
  int direction;
  boolean isDead;


  Fish(int row, int col, int direction) {
    this.row = row;
    this.col = col;
    this.direction = direction;
    isDead = false;
  }

  Fish (int row, int col, int direction, boolean isDead) {
    this(row, col, direction);
    this.isDead = isDead;
  }
}
```

**추가 코드(AC)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int INF = Integer.MAX_VALUE;
  static int[] dr = {INF, -1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dc = {INF, 0, -1, -1, -1, 0, 1, 1, 1};
  static int max = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    Fish[] fishes = new Fish[4 * 4 + 1];
    int[][] idx_grid = new int[4][4];
    for (int i = 0; i < 4; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 4; j++) {
        int idx = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());
        fishes[idx] = new Fish(i ,j, direction);
        idx_grid[i][j] = idx;
      }
    }

    System.out.println(simulation(fishes, idx_grid));
  }
  static int simulation(Fish[] fishes, int[][] idx_grid) {

    active_shark(fishes, new Shark(0, 0, 0), idx_grid, 0);

    return max;
  }
  static void active_shark(Fish[] fishes, Shark shark, int[][] idx_grid, int sum) {

    int idx = idx_grid[shark.row][shark.col];
    shark.direction = fishes[idx].direction;
    sum += idx;
    fishes[idx].isDead = true;
    idx_grid[shark.row][shark.col] = 0;

    active_fishes(fishes, shark, idx_grid);

    boolean isMove = false;
    while (isValidIdx(shark.row + dr[shark.direction], shark.col + dc[shark.direction])) {
      shark.row += dr[shark.direction];
      shark.col += dc[shark.direction];

      if (idx_grid[shark.row][shark.col] != 0) {
        isMove = true;
        int[][] newGrid = copy_grid(idx_grid);
        Fish[] newFishes = copy_fish(fishes);

        active_shark(newFishes, new Shark(shark.row, shark.col, shark.direction), newGrid, sum);
      }
    }

    if (!isMove) max = Math.max(max, sum);
  }
  static int[][] copy_grid(int[][] idx_grid) {
    //int[][] grid = Arrays.copyOf(idx_grid, 4);
    int[][] grid = new int[4][4];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        grid[i][j] = idx_grid[i][j];
      }
    }
    return grid;
  }
  static Fish[] copy_fish(Fish[] fishes) {

    Fish[] newFishes = new Fish[17];
    for (int i = 1; i <= 16; i++)
      newFishes[i] = new Fish(fishes[i].row, fishes[i].col, fishes[i].direction, fishes[i].isDead);

    return newFishes;
  }
  static void active_fishes(Fish[] fishes, Shark shark, int[][] idx_grid) {

    for (int i = 1; i <= 16; i++) {
      Fish fish = fishes[i];
      if (!fish.isDead) {
        int baseDirection = fish.direction;
        do {
          int nextRow = fish.row + dr[fish.direction];
          int nextCol = fish.col + dc[fish.direction];

          if (isValidIdx(nextRow, nextCol) && !(shark.row == nextRow && shark.col == nextCol)) {
            int otherIdx = idx_grid[nextRow][nextCol];
            if(otherIdx != 0) {
              fishes[otherIdx].row = fish.row;
              fishes[otherIdx].col = fish.col;
            }
            idx_grid[fish.row][fish.col] = otherIdx;

            fish.row = nextRow;
            fish.col = nextCol;
            idx_grid[nextRow][nextCol] = i;
            break;
          }

          if (++fish.direction == 9) fish.direction = 1;
        } while (fish.direction != baseDirection);
      }
    }
  }

  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < 4 && col < 4;
  }
}
class Shark {
  int row;
  int col;
  int direction;

  Shark(int row, int col, int direction) {
    this.row = row;
    this.col = col;
    this.direction = direction;
  }
}
class Fish {
  int row;
  int col;
  int direction;
  boolean isDead;

  Fish(int row, int col, int direction) {
    this.row = row;
    this.col = col;
    this.direction = direction;
    isDead = false;
  }
  Fish(int row, int col, int direction, boolean isDead) {
    this(row, col, direction);
    this.isDead = isDead;
  }
}
```