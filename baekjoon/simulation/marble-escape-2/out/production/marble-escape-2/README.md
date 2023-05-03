# 구슬 탈출 2

**골드 1**

|시간 제한	|메모리 제한|	제출|	정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|2 초|	512 MB	|77381|	23198	|12970	|27.463%|

## 문제 

스타트링크에서 판매하는 어린이용 장난감 중에서 가장 인기가 많은 제품은 구슬 탈출이다. 구슬 탈출은 직사각형 보드에 빨간 구슬과 파란 구슬을 하나씩 넣은 다음, 빨간 구슬을 구멍을 통해 빼내는 게임이다.

보드의 세로 크기는 N, 가로 크기는 M이고, 편의상 1×1크기의 칸으로 나누어져 있다. 가장 바깥 행과 열은 모두 막혀져 있고, 보드에는 구멍이 하나 있다. 빨간 구슬과 파란 구슬의 크기는 보드에서 1×1크기의 칸을 가득 채우는 사이즈이고, 각각 하나씩 들어가 있다. 게임의 목표는 빨간 구슬을 구멍을 통해서 빼내는 것이다. 이때, 파란 구슬이 구멍에 들어가면 안 된다.

이때, 구슬을 손으로 건드릴 수는 없고, 중력을 이용해서 이리 저리 굴려야 한다. 왼쪽으로 기울이기, 오른쪽으로 기울이기, 위쪽으로 기울이기, 아래쪽으로 기울이기와 같은 네 가지 동작이 가능하다.

각각의 동작에서 공은 동시에 움직인다. 빨간 구슬이 구멍에 빠지면 성공이지만, 파란 구슬이 구멍에 빠지면 실패이다. 빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다. 빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다. 또, 빨간 구슬과 파란 구슬의 크기는 한 칸을 모두 차지한다. 기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지이다.

보드의 상태가 주어졌을 때, 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 구하는 프로그램을 작성하시오.

## 입력 

첫 번째 줄에는 보드의 세로, 가로 크기를 의미하는 두 정수 N, M (3 ≤ N, M ≤ 10)이 주어진다. 다음 N개의 줄에 보드의 모양을 나타내는 길이 M의 문자열이 주어진다. 이 문자열은 '.', '#', 'O', 'R', 'B' 로 이루어져 있다. '.'은 빈 칸을 의미하고, '#'은 공이 이동할 수 없는 장애물 또는 벽을 의미하며, 'O'는 구멍의 위치를 의미한다. 'R'은 빨간 구슬의 위치, 'B'는 파란 구슬의 위치이다.

입력되는 모든 보드의 가장자리에는 모두 '#'이 있다. 구멍의 개수는 한 개 이며, 빨간 구슬과 파란 구슬은 항상 1개가 주어진다.

## 출력 

최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 출력한다. 만약, 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1을 출력한다.

## 예제 입력 1

```
5 5
#####
#..B#
#.#.#
#RO.#
#####
```

## 예제 출력 1

```
1
```

## 예제 입력 2

```
7 7
#######
#...RB#
#.#####
#.....#
#####.#
#O....#
#######
```

## 예제 출력 2

```
5
```

## 예제 입력 3

```
7 7
#######
#..R#B#
#.#####
#.....#
#####.#
#O....#
#######
```

## 예제 출력 3

```
5
```

## 예제 입력 4

```
10 10
##########
#R#...##B#
#...#.##.#
#####.##.#
#......#.#
#.######.#
#.#....#.#
#.#.#.#..#
#...#.O#.#
##########
```

## 예제 출력 4

```
-1
```

## 예제 입력 5

```
3 7
#######
#R.O.B#
#######
```

## 예제 출력 5

```
1
```

## 예제 입력 6

```
10 10
##########
#R#...##B#
#...#.##.#
#####.##.#
#......#.#
#.######.#
#.#....#.#
#.#.##...#
#O..#....#
##########
```

## 예제 출력 6

```
7
```

## 예제 입력 7

```
3 10
##########
#.O....RB#
##########
```

## 예제 출력 7

```
-1
```

## 코드 

해당 풀이는 구슬이 옮겨지는 과정에서 초기화 작업을 생략해서 재대로 작동하지 않은 코드이다.

```java
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
```

**WA(57%)**

```java
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
    boolean[][] isVisited_RedMarble = new boolean[N][M];
    q.add(new Case(rRow, rCol, bRow, bCol, 0));
    isVisited_RedMarble[rRow][rCol] = true;

    while (!q.isEmpty()) {
      Case currentCase = q.poll();

      if (currentCase.time == 10) continue;

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
              bNum++;
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

        if (rNum != 0 || bNum != 0) {
          q.add(new Case(next_RedMarbleRow, next_RedMarbleCol,
                  next_BlueMarbleRow, next_BlueMarbleCol, currentCase.time + 1));
          isVisited_RedMarble[next_RedMarbleRow][next_RedMarbleCol] = true;
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
```