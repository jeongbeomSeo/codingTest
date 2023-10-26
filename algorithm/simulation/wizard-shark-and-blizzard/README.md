# 마법사 상어와 블리자드

**골드 1**

|시간 제한	|메모리 제한	|제출|	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초 (추가 시간 없음)	|1024 MB|	12531|	3208	|1879|	23.767%|

## 문제 

마법사 상어는 파이어볼, 토네이도, 파이어스톰, 물복사버그, 비바라기 마법을 할 수 있다. 오늘 새로 배운 마법은 블리자드이고, 크기가 N×N인 격자에서 연습하려고 한다. N은 항상 홀수이고, (r, c)는 격자의 r행 c열을 의미한다. 격자의 가장 왼쪽 윗 칸은 (1, 1)이고, 가장 오른쪽 아랫 칸은 (N, N)이며 마법사 상어는 ((N+1)/2, (N+1)/2)에 있다.

일부 칸과 칸 사이에는 벽이 세워져 있으며, 다음은 N = 3, 5, 7인 경우의 예시이다. 실선은 벽이고, 점선은 벽이 아니다. 칸에 적혀있는 수는 칸의 번호이다.

 ![](https://upload.acmicpc.net/443a20c6-fbd7-4951-9b68-bf78b12b27fb/-/preview/) | ![](https://upload.acmicpc.net/7781df31-3999-4234-a032-32cb6fd439e9/-/preview/) |![](https://upload.acmicpc.net/50b901d5-47ec-4504-bce1-122e8282284a/-/preview/)|
|---------------------------------------------------------------------------------|---------------------------------------------------------------------------------|---|
| N = 3	                                                                          | N = 5	                                                                          | N = 7                                                                           |

가장 처음에 상어가 있는 칸을 제외한 나머지 칸에는 구슬이 하나 들어갈 수 있다. 구슬은 1번 구슬, 2번 구슬, 3번 구슬이 있다. 같은 번호를 가진 구슬이 번호가 연속하는 칸에 있으면, 그 구슬을 연속하는 구슬이라고 한다. 다음은 N = 7인 경우 예시이다. 

![](https://upload.acmicpc.net/2c31bd47-ddc9-40f2-9830-05bef469fb4a/-/preview/)

블리자드 마법을 시전하려면 방향 di와 거리 si를 정해야 한다. 총 4가지 방향 ↑, ↓, ←, →가 있고, 정수 1, 2, 3, 4로 나타낸다. 상어는 di 방향으로 거리가 si 이하인 모든 칸에 얼음 파편을 던져 그 칸에 있는 구슬을 모두 파괴한다. 구슬이 파괴되면 그 칸은 구슬이 들어있지 않은 빈 칸이 된다. 얼음 파편은 벽의 위로 떨어지기 때문에, 벽은 파괴되지 않는다.

다음 예시는 방향은 아래, 거리는 2인 경우이다.

| ![](https://upload.acmicpc.net/ca05f21a-bd15-4fbd-80a5-899712db2beb/-/preview/) | ![](https://upload.acmicpc.net/4768a8c6-c935-430d-9ce3-7fde346b0830/-/preview/) |
|--------------------------------------------------------------------------------|---------------------------------------------------------------------------------|
| 빨간색으로 표시된 칸에 얼음 파편이 떨어진다.| 구슬이 파괴된 후                                                                       |

만약 어떤 칸 A의 번호보다 번호가 하나 작은 칸이 빈 칸이면, A에 있는 구슬은 그 빈 칸으로 이동한다. 이 이동은 더 이상 구슬이 이동하지 않을 때까지 반복된다. 따라서, 구슬이 파괴된 후에는 빈 칸이 생겨 구슬이 이동하고, 구슬이 모두 이동한 결과는 다음과 같다.

![](https://upload.acmicpc.net/28dcbbe3-7035-49ad-afed-642218adee39/-/preview/)

이제 구슬이 폭발하는 단계이다. 폭발하는 구슬은 4개 이상 연속하는 구슬이 있을 때 발생한다. 다음은 왼쪽 그림은 위의 상태에서 폭발하는 구슬이 들어있는 칸을 파란색과 초록색으로 표시한 것이고, 오른쪽 그림은 구슬이 폭발한 후의 상태이다.

|![](https://upload.acmicpc.net/41c500e1-e82c-440c-afcc-f351af9ea1dc/-/preview/)| ![](https://upload.acmicpc.net/cf990414-2eb8-4f60-bd4c-bf8dfd35665d/-/preview/) |
|---|---------------------------------------------------------------------------------|
|구슬이 폭발하기 전	| 구슬이 폭발한 후                                                                       |

구슬이 폭발해 빈 칸이 생겼으니 다시 구슬이 이동한다. 구슬이 이동한 후에는 다시 구슬이 폭발하는 단계이고, 이 과정은 더 이상 폭발하는 구슬이 없을때까지 반복된다. 구슬이 폭발한 후의 상태에서 구슬이 이동하면 다음과 같다.

![](https://upload.acmicpc.net/6fe3b9cf-b6a2-4ad1-9014-3b99852996b5/-/preview/)

위의 상태는 4개 이상 연속하는 구슬이 있기 때문에 구슬이 다시 폭발하게 된다.

|![](https://upload.acmicpc.net/5fb62a98-2cea-4c4d-9b6f-9c540b459290/-/preview/)|![](https://upload.acmicpc.net/0f70b5e5-3111-4f16-a699-42638a17c540/-/preview/)|
|---|---|
|구슬이 폭발하기 전	|구슬이 폭발하고 이동한 후|

이제 더 이상 폭발한 구슬이 없기 때문에, 구슬이 변화하는 단계가 된다. 연속하는 구슬은 하나의 그룹이라고 한다. 다음은 1번 구슬은 빨간색, 2번 구슬은 파란색, 3번 구슬은 보라색으로 표시한 그림이다.

![](https://upload.acmicpc.net/0d0b2e68-960c-4bb7-a950-da389183ea88/-/preview/)

하나의 그룹은 두 개의 구슬 A와 B로 변한다. 구슬 A의 번호는 그룹에 들어있는 구슬의 개수이고, B는 그룹을 이루고 있는 구슬의 번호이다. 구슬은 다시 그룹의 순서대로 1번 칸부터 차례대로 A, B의 순서로 칸에 들어간다. 다음 그림은 구슬이 변화한 후이고, 색은 구분하기 위해 위의 그림에 있는 그룹의 색을 그대로 사용했다. 만약, 구슬이 칸의 수보다 많아 칸에 들어가지 못하는 경우 그러한 구슬은 사라진다.

![](https://upload.acmicpc.net/c72823d6-95b2-424f-b9d8-84c423685b3d/-/preview/)

마법사 상어는 블리자드를 총 M번 시전했다. 시전한 마법의 정보가 주어졌을 때, 1×(폭발한 1번 구슬의 개수) + 2×(폭발한 2번 구슬의 개수) + 3×(폭발한 3번 구슬의 개수)를 구해보자.

## 입력 

첫째 줄에 N, M이 주어진다. 둘째 줄부터 N개의 줄에는 격자에 들어있는 구슬의 정보가 주어진다. r번째 행의 c번째 정수는 (r, c)에 들어있는 구슬의 번호를 의미한다. 어떤 칸에 구슬이 없으면 0이 주어진다. 상어가 있는 칸도 항상 0이 주어진다.

다음 M개의 줄에는 블리자드 마법의 방향 di와 거리 si가 한 줄에 하나씩 마법을 시전한 순서대로 주어진다.

## 출력 

첫째 줄에 1×(폭발한 1번 구슬의 개수) + 2×(폭발한 2번 구슬의 개수) + 3×(폭발한 3번 구슬의 개수)를 출력한다.

## 제한 

- 3 ≤ N ≤ 49
- N은 홀수
- 1 ≤ M ≤ 100
- 1 ≤ d<sub>i</sub> ≤ 4
- 1 ≤ s<sub>i</sub> ≤ (N-1)/2
- 칸에 들어있는 구슬이 K개라면, 구슬이 들어있는 칸의 번호는 1번부터 K번까지이다.
- 입력으로 주어진 격자에는 4개 이상 연속하는 구슬이 없다.

## 예제 입력 1

```
7 1
0 0 0 0 0 0 0
3 2 1 3 2 3 0
2 1 2 1 2 1 0
2 1 1 0 2 1 1
3 3 2 3 2 1 2
3 3 3 1 3 3 2
2 3 2 2 3 2 3
2 2
```

## 예제 출력 1

```
28
```

## 예제 입력 2

```
7 4
0 0 0 2 3 2 3
1 2 3 1 2 3 1
2 3 1 2 3 1 2
1 2 3 0 2 3 1
2 3 1 2 3 1 2
3 1 2 3 1 2 3
1 2 3 1 2 3 1
1 3
2 2
3 1
4 3
```

## 예제 출력 2

```
0
```

|![](https://upload.acmicpc.net/06bbc8cd-ba47-4e76-9169-36165a168ba4/-/preview/)|![](https://upload.acmicpc.net/9f8795c6-2013-439a-b539-6c785b59f7de/-/preview/)|
|---|---|
|첫 번째 블리자드 후|두 번째 블리자드 후|
|![](https://upload.acmicpc.net/c8ed66f8-ae0f-40c6-9ff6-3638444086a2/-/preview/)|![](https://upload.acmicpc.net/2416b631-2cde-4311-91e6-58ae5a278ea9/-/preview/)|
|세 번째 블리자드 후	|네 번째 블리자드 후|

## 잘못 짠 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static int N, M;
  static int[] dr = {0, -1, 1, 0, 0};
  static int[] dc = {0, 0, 0, -1, 1};
  static int sharkRow, sharkCol;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    sharkRow = sharkCol = (N + 1) / 2;

    int[][] grid = new int[N + 1][N + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] commands = new int[M][2];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      commands[i][0] = Integer.parseInt(st.nextToken());
      commands[i][1] = Integer.parseInt(st.nextToken());
    }

    System.out.println(simulation(grid, commands));

  }
  static int simulation (int[][] grid, int[][] commands) {

    int time = 0;
    int[] count = new int[4];

    while (time < M) {

      active_throwBall(grid, commands[time], count);

      sorting_grid(grid);

      while (burst_grid(grid, count)) {
        sorting_grid(grid);
      }

      last_grid(grid);

      time++;
    }

    return count[1] + 2 * count[2] + 3 * count[3];
  }
  static void last_grid(int[][] grid) {

    boolean[][] isVisited = new boolean[N + 1][N + 1];
    isVisited[sharkRow][sharkCol] = true;

    // 남, 동, 북, 서
    int[] move_dr = {0, 1, 0, -1, 0};
    int[] move_dc = {0, 0, 1, 0, -1};

    int curRow = sharkRow;
    int curCol = sharkCol - 1;
    int direction = 1;

    isVisited[curRow][curCol] = true;

    Deque<Counter> counters = new ArrayDeque<>();
    counters.add(new Counter(grid[curRow][curCol]));
    while (grid[curRow][curCol] != 0) {
      int nextRow = curRow;
      int nextCol = curCol;

      int leftDirection = direction + 1 != 5 ? direction + 1 : 1;
      int leftRow = nextRow + move_dr[leftDirection];
      int leftCol = nextCol + move_dc[leftDirection];

      if (!isVisited[leftRow][leftCol]) {
        nextRow = leftRow;
        nextCol = leftCol;
        direction = leftDirection;
      }
      else {
        nextRow += move_dr[direction];
        nextCol += move_dc[direction];
      }

      if (counters.getLast().number == grid[nextRow][nextCol]) {
        counters.getLast().count++;
      }
      else {
        if (grid[nextRow][nextCol] == 0) break;
        counters.addLast(new Counter(grid[nextRow][nextCol]));
      }
      curRow = nextRow;
      curCol = nextCol;
      isVisited[curRow][curCol] = true;
      if (curRow == 1 && curCol == 1) break;
    }

    for (int i = 0; i < N + 1; i++) Arrays.fill(isVisited[i], false);

    isVisited[sharkRow][sharkCol] = true;

    curRow = sharkRow;
    curCol = sharkCol - 1;
    direction = 1;
    isVisited[curRow][curCol] = true;

    while (!(curRow == 1 && curCol == 0)) {
      if (counters.isEmpty()) break;
      Counter counter = counters.pollFirst();

      grid[curRow][curCol] = counter.count;
      if (curRow == 1 && curCol == 1) break;

      for (int i = 0; i < 2; i++) {
        int nextRow = curRow;
        int nextCol = curCol;

        int leftDirection = direction + 1 != 5 ? direction + 1 : 1;
        int leftRow = nextRow + move_dr[leftDirection];
        int leftCol = nextCol + move_dc[leftDirection];

        if (!isVisited[leftRow][leftCol]) {
          nextRow = leftRow;
          nextCol = leftCol;
          direction = leftDirection;
        }
        else {
          nextRow += move_dr[direction];
          nextCol += move_dc[direction];
        }

        curRow = nextRow;
        curCol = nextCol;
        isVisited[curRow][curCol] = true;
        if (i == 0) {
          grid[curRow][curCol] = counter.number;
        }
        if (curRow == 1 && curCol == 1) break;
      }
    }
  }
  static boolean burst_grid(int[][] grid, int[] count) {

    boolean isFunk = false;
    boolean[][] isVisited = new boolean[N + 1][N + 1];
    isVisited[sharkRow][sharkCol] = true;

    // 남, 동, 북, 서
    int[] move_dr = {0, 1, 0, -1, 0};
    int[] move_dc = {0, 0, 1, 0, -1};

    int curRow = sharkRow;
    int curCol = sharkCol - 1;
    isVisited[curRow][curCol] = true;
    int direction = 1;

    while (grid[curRow][curCol] != 0) {

      int nextRow = curRow;
      int nextCol = curCol;
      int curNumber = grid[curRow][curCol];

      Queue<Point> burst = new ArrayDeque<>();
      burst.add(new Point(curRow, curCol));
      do {
        int leftDirection = direction + 1 != 5 ? direction + 1 : 1;
        int leftRow = nextRow + move_dr[leftDirection];
        int leftCol = nextCol + move_dc[leftDirection];

        if (!isVisited[leftRow][leftCol]) {
          nextRow = leftRow;
          nextCol = leftCol;
          direction = leftDirection;
        } else {
          nextRow += move_dr[direction];
          nextCol += move_dc[direction];
        }

        isVisited[nextRow][nextCol] = true;

        if (grid[nextRow][nextCol] == curNumber) {
          burst.add(new Point(nextRow, nextCol));
        }
        else break;
      } while (grid[nextRow][nextCol] != 0);

      if (burst.size() >= 4) {
        isFunk = true;
        while (!burst.isEmpty()) {
          Point point = burst.poll();
          count[curNumber]++;
          grid[point.row][point.col] = 0;
        }
      }
      else while (!burst.isEmpty()) burst.poll();
      curRow = nextRow;
      curCol = nextCol;
    }

    return isFunk;
  }
  static void sorting_grid(int[][] grid) {

    boolean[][] isVisited = new boolean[N + 1][N + 1];
    isVisited[sharkRow][sharkCol] = true;

    // 남, 동, 북, 서
    int[] move_dr = {0, 1, 0, -1, 0};
    int[] move_dc = {0, 0, 1, 0, -1};

    int curRow = sharkRow;
    int curCol = sharkCol - 1;
    int direction = 1;
    isVisited[curRow][curCol] = true;

    while (!(curRow == 1 && curCol == 1)) {

      int nextRow = curRow;
      int nextCol = curCol;
      int leftDirection = (direction + 1 != 5) ? direction + 1 : 1;

      int leftRow = nextRow + move_dr[leftDirection];
      int leftCol = nextCol + move_dc[leftDirection];

      if(!isVisited[leftRow][leftCol]) {
        nextRow = leftRow;
        nextCol = leftCol;
        direction = leftDirection;
      }
      else {
        nextRow += move_dr[direction];
        nextCol += move_dc[direction];
      }

      if (grid[nextRow][nextCol] == 0) {
        int copyRow = nextRow;
        int copyCol = nextCol;
        int copyDirection = direction;
        do {
          leftDirection = copyDirection + 1 != 5 ? copyDirection + 1 : 1;

          leftRow = copyRow + move_dr[leftDirection];
          leftCol = copyCol + move_dc[leftDirection];

          if (!isVisited[leftRow][leftCol]) {
            copyRow = leftRow;
            copyCol = leftCol;
            copyDirection = leftDirection;
          }
          else {
            copyRow += move_dr[copyDirection];
            copyCol += move_dc[copyDirection];
          }

          if (copyRow == 1 && copyCol == 1) break;

        } while (grid[copyRow][copyCol] == 0);

        if (grid[copyRow][copyCol] == 0) break;

        grid[nextRow][nextCol] = grid[copyRow][copyCol];
        grid[copyRow][copyCol] = 0;
      }
      curRow = nextRow;
      curCol = nextCol;
      isVisited[curRow][curCol] = true;
    }
  }
  static void active_throwBall(int[][] grid, int[] command, int[] count) {

    int direction = command[0];
    int speed = 0;
    int curRow = sharkRow;
    int curCol = sharkCol;
    while (speed++ < command[1]) {

      int nextRow = curRow + dr[direction];
      int nextCol = curCol + dc[direction];

      grid[nextRow][nextCol] = 0;

      curRow = nextRow;
      curCol = nextCol;
    }
  }
}
class Point {
  int row;
  int col;

  Point (int row, int col) {
    this.row = row;
    this.col = col;
  }
}
class Counter {
  int number;
  int count;

  Counter(int number) {
    this.number = number;
    this.count = 1;
  }
}
```

상어부터 시작하지 않고 상어 왼쪽 부터 아래 방향으로 이동하는 것으로 시작해서 왼쪽부터 구슬이 터지는 경우를 대처하지 못했다.

## 코드 

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static int N, M;
  static int sharkRow, sharkCol;
  // 북, 남, 서, 동
  static int[] throw_dr = {0, -1, 1, 0, 0};
  static int[] throw_dc = {0, 0, 0, -1, 1};
  // 남, 동, 북, 서
  static int[] move_dr = {0, 1, 0, -1, 0};
  static int[] move_dc = {0, 0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    sharkRow = sharkCol = (N + 1) / 2;

    int[][] grid = new int[N + 1][N + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] rotate_commands = new int[M][2];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      rotate_commands[i][0] = Integer.parseInt(st.nextToken());
      rotate_commands[i][1] = Integer.parseInt(st.nextToken());
    }

    System.out.println(simulation(grid, rotate_commands));

  }
  static int simulation(int[][] grid, int[][] rotate_commands) {

    int time = 0;
    int[] burstMarbleNumber = new int[4];
    while (time < M) {

      activeThrow(grid, rotate_commands[time]);

      sortingGrid(grid);

      while (active_burst(grid, burstMarbleNumber)) {
        sortingGrid(grid);
      }

      finalSortingGrid(grid);

      time++;
    }

    return burstMarbleNumber[1] + 2 * burstMarbleNumber[2] + 3 * burstMarbleNumber[3];
  }
  static void finalSortingGrid(int[][] grid) {
    Deque<Counter> counters = new ArrayDeque<>();

    boolean[][] isVisited = new boolean[N + 1][N + 1];
    isVisited[sharkRow][sharkCol] = true;

    int curRow = sharkRow;
    int curCol = sharkCol - 1;
    int curDirection = 1;

    while (grid[curRow][curCol] != 0) {

      isVisited[curRow][curCol] = true;

      if (counters.isEmpty()) counters.addLast(new Counter(grid[curRow][curCol]));
      else {
        if (counters.peekLast().number == grid[curRow][curCol]) counters.peekLast().count++;
        else counters.addLast(new Counter(grid[curRow][curCol]));
      }

      int[] result = findNextIdx(isVisited, curRow, curCol, curDirection);

      curRow = result[0];
      curCol = result[1];
      curDirection = result[2];
    }

    curRow = sharkRow;
    curCol = sharkCol - 1;
    curDirection = 1;

    for (int i = 0; i <= N; i++) Arrays.fill(isVisited[i], false);
    isVisited[sharkRow][sharkCol] = true;

    while (!counters.isEmpty()) {
      Counter counter = counters.pollFirst();

      grid[curRow][curCol] = counter.count;
      isVisited[curRow][curCol] = true;
      if (curRow == 1 && curCol == 1) break;

      int[] result = findNextIdx(isVisited, curRow, curCol, curDirection);

      curRow = result[0];
      curCol = result[1];
      curDirection = result[2];

      grid[curRow][curCol] = counter.number;
      isVisited[curRow][curCol] = true;
      if (curRow == 1 && curCol == 1) break;

      result = findNextIdx(isVisited, curRow, curCol, curDirection);

      curRow = result[0];
      curCol = result[1];
      curDirection = result[2];
    }

  }
  static boolean active_burst(int[][] grid, int[] burstMarbleNumber) {

    Queue<Point> points = new ArrayDeque<>();
    boolean[][] isVisited = new boolean[N + 1][N + 1];
    isVisited[sharkRow][sharkCol] = true;

    int curRow = sharkRow;
    int curCol = sharkCol - 1;
    int curDirection = 1;
    int curNumber = grid[curRow][curCol];

    boolean isActiveBurst = false;

    while (!(curRow == 1 && curCol == 1)) {

      isVisited[curRow][curCol] = true;

      if (grid[curRow][curCol] == curNumber) points.add(new Point(curRow, curCol));
      else {
        if (points.size() >= 4) {
          isActiveBurst = true;
          while (!points.isEmpty()) {
            Point point = points.poll();
            grid[point.row][point.col] = 0;
            burstMarbleNumber[curNumber]++;
          }
        }
        else while (!points.isEmpty()) points.poll();

        curNumber = grid[curRow][curCol];
        points.add(new Point(curRow, curCol));
      }

      int[] result = findNextIdx(isVisited, curRow, curCol, curDirection);

      curRow = result[0];
      curCol = result[1];
      curDirection = result[2];
    }

    return isActiveBurst;
  }
  static void sortingGrid(int[][] grid) {

    boolean[][] isVisited = new boolean[N + 1][N + 1];
    isVisited[sharkRow][sharkCol] = true;

    int curRow = sharkRow;
    int curCol = sharkCol - 1;
    int curDirection = 1;

    while (!(curRow == 1 && curCol == 1)) {

      int nextRow;
      int nextCol;
      int nextDirection;

      isVisited[curRow][curCol] = true;
      boolean curValueIsZero = (grid[curRow][curCol] == 0);

      int[] result = findNextIdx(isVisited, curRow, curCol, curDirection);
      nextRow = result[0];
      nextCol = result[1];
      nextDirection = result[2];

      // 현재 위치 0일 경우 처리
      if (curValueIsZero) {
        if(!paintZeroToOtherNumber(grid, isVisited, curRow, curCol, nextRow, nextCol, nextDirection)) break;
      }

      curRow = nextRow;
      curCol = nextCol;
      curDirection = nextDirection;
    }
  }
  static boolean paintZeroToOtherNumber(int[][] grid, boolean[][] isVisited, int curRow, int curCol, int nextRow, int nextCol, int nextDirection) {

    if (grid[nextRow][nextCol] != 0) {
      grid[curRow][curCol] = grid[nextRow][nextCol];
      grid[nextRow][nextCol] = 0;
    }
    else {
      if (nextRow == 1 && nextCol == 1) return false;

      int copyRow = nextRow;
      int copyCol = nextCol;
      int copyDirection = nextDirection;
      do {
        int[] findNextResult = findNextIdx(isVisited, copyRow, copyCol, copyDirection);

        copyRow = findNextResult[0];
        copyCol = findNextResult[1];
        copyDirection = findNextResult[2];

        if(copyRow == 1 && copyCol == 1) break;

      } while (grid[copyRow][copyCol] == 0);

      if (grid[copyRow][copyCol] == 0) return false;

      grid[curRow][curCol] = grid[copyRow][copyCol];
      grid[copyRow][copyCol] = 0;
    }
    return true;
  }
  static int[] findNextIdx(boolean[][] isVisited, int curRow, int curCol, int curDirection) {
    int[] result = new int[3];

    int leftDirection = getLeftDirection(curDirection);

    int leftRow = curRow + move_dr[leftDirection];
    int leftCol = curCol + move_dc[leftDirection];

    if (!isVisited[leftRow][leftCol]) {
      result[0] = leftRow;
      result[1] = leftCol;
      result[2] = leftDirection;
    }
    else {
      result[0] = curRow + move_dr[curDirection];
      result[1] = curCol + move_dc[curDirection];
      result[2] = curDirection;
    }
    return result;
  }
  static int getLeftDirection(int direction) {
    return direction + 1 != 5 ? direction + 1 : 1;
  }
  static void activeThrow(int[][] grid, int[] rotate_command) {

    int direction = rotate_command[0];
    int targetSpeed = rotate_command[1];

    int curRow = sharkRow;
    int curCol = sharkCol;

    while (targetSpeed-- > 0) {
      int nextRow = curRow + throw_dr[direction];
      int nextCol = curCol + throw_dc[direction];

      grid[nextRow][nextCol] = 0;

      curRow = nextRow;
      curCol = nextCol;
    }
  }
}
class Point {
  int row;
  int col;

  Point (int row, int col) {
    this.row = row;
    this.col = col;
  }
}
class Counter {
  int number;
  int count;

  Counter (int number) {
    this.number = number;
    this.count = 1;
  }
}
```

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;

  static int[] throw_dr = {0, -1, 1, 0, 0};
  static int[] throw_dc = {0, 0, 0, -1, 1};

  // 북, 서, 남, 동
  static int[] move_dr = {0, -1, 0, 1, 0};
  static int[] move_dc = {0, 0, -1, 0, 1};
  static int sharkRow, sharkCol;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    sharkRow = sharkCol = N / 2;

    int[] oneDimensionGrid = new int[N * N];
    int[][] numberTagGrid = new int[N][N];
    paintNumberTagGrid(numberTagGrid);

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int idx = numberTagGrid[i][j];
        oneDimensionGrid[idx] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] throwCommands = new int[M][2];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      throwCommands[i][0] = Integer.parseInt(st.nextToken());
      throwCommands[i][1] = Integer.parseInt(st.nextToken());
    }

    System.out.println(simulation(numberTagGrid, oneDimensionGrid, throwCommands));
  }
  static int simulation (int[][] numberTagGrid, int[] oneDimensionGrid, int[][] throwCommands) {

    int time = 0;
    int[] burstMarbleCount = new int[4];
    while (time < M) {

      active_throwMarble(numberTagGrid, oneDimensionGrid, throwCommands[time]);

      while (active_burst(oneDimensionGrid, burstMarbleCount));

      //sortingGrid(oneDimensionGrid);
      oneDimensionGrid = finalSortingGrid(oneDimensionGrid);
      time++;
    }

    return burstMarbleCount[1] + 2 * burstMarbleCount[2] + 3 * burstMarbleCount[3];
  }
  static int[] finalSortingGrid(int[] oneDimensionGrid) {

    Deque<Counter> counters = new ArrayDeque<>();

    for (int i = 1; i < N * N; i++) {
      if (oneDimensionGrid[i] != 0) {
        if (counters.isEmpty()) counters.addLast(new Counter(oneDimensionGrid[i]));
        else if (counters.peekLast().number == oneDimensionGrid[i]) counters.peekLast().count++;
        else counters.addLast(new Counter(oneDimensionGrid[i]));
      }
    }

    int[] newOneDimensionGrid = new int[N * N];
    int ptr = 1;
    while (!counters.isEmpty()) {
      Counter counter = counters.pollFirst();

      newOneDimensionGrid[ptr++] = counter.count;
      if (ptr == N * N) break;
      newOneDimensionGrid[ptr++] = counter.number;
      if (ptr == N * N) break;
    }
    return newOneDimensionGrid;
  }
  static void sortingGrid (int[] oneDimensionGrid) {

    for (int i = 1; i < N * N - 1; i++) {
      if (oneDimensionGrid[i] == 0) {
        int j;
        for (j = i + 1; j < N * N && oneDimensionGrid[j] == 0; j++);
        if (j == N * N) break;
        oneDimensionGrid[i] = oneDimensionGrid[j];
        oneDimensionGrid[j] = 0;
      }
    }
  }
  static boolean active_burst(int[] oneDimensionGrid, int[] burstMarbleCount) {

    Queue<Point> q = new ArrayDeque<>();
    boolean isActiveBurst = false;

    for (int i = 1; i < N * N; i++) {
      if (oneDimensionGrid[i] != 0) {
        if (q.isEmpty()) q.add(new Point(i, oneDimensionGrid[i]));
        else if (q.peek().number == oneDimensionGrid[i]) q.add(new Point(i, oneDimensionGrid[i]));
        else {
          if (q.size() >= 4) {
            isActiveBurst = true;
            burstMarbleCount[q.peek().number] += q.size();
            while (!q.isEmpty()) oneDimensionGrid[q.poll().idx] = 0;
          }
          else while (!q.isEmpty()) q.poll();
          q.add(new Point(i, oneDimensionGrid[i]));
        }
      }
    }
    if (q.size() >= 4) {
      burstMarbleCount[q.peek().number] += q.size();
      while (!q.isEmpty()) {
        oneDimensionGrid[q.poll().idx] = 0;
      }
    }
    return isActiveBurst;
  }
  static void active_throwMarble(int[][] numberTagGrid, int[] oneDimensionGrid, int[] throwCommand) {

    int direction = throwCommand[0];
    int speed = throwCommand[1];

    int curRow = sharkRow;
    int curCol = sharkCol;
    while (speed-- > 0) {

      curRow += throw_dr[direction];
      curCol += throw_dc[direction];

      int idx = numberTagGrid[curRow][curCol];
      oneDimensionGrid[idx] = 0;
    }
  }
  static void paintNumberTagGrid(int[][] grid) {

    boolean[][] isVisited = new boolean[N][N];

    int curRow = sharkRow;
    int curCol = sharkCol;
    int direction = 1;

    int numberTag = 0;

    do {
      isVisited[curRow][curCol] = true;
      grid[curRow][curCol] = numberTag++;

      int leftDirection = direction + 1 != 5 ? direction + 1 : 1;

      int leftRow = curRow + move_dr[leftDirection];
      int leftCol = curCol + move_dc[leftDirection];

      if (!isVisited[leftRow][leftCol]) {
        curRow = leftRow;
        curCol = leftCol;
        direction = leftDirection;
      }
      else {
        curRow += move_dr[direction];
        curCol += move_dc[direction];
      }

    } while (curRow >= 0 && curCol >= 0);
  }
}

class Point {
  int idx;
  int number;

  Point(int idx, int number) {
    this.idx = idx;
    this.number = number;
  }
}

class Counter {
  int number;
  int count;

  Counter(int number) {
    this.number = number;
    this.count = 1;
  }
}
```

**Better Solution(AC)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[] dr = {0, -1, 1, 0, 0};
  static int[] dc = {0, 0, 0, -1, 1};
  static int[] move_dr = {0, 0, 1, 0, -1};
  static int[] move_dc = {0, -1, 0, 1, 0};
  static int sharkRow, sharkCol;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    sharkRow = sharkCol = (N + 1) / 2;

    int[] grid = new int[N * N];
    int[][] tagGrid = initOneDimensionGrid();
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        grid[tagGrid[i][j]] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] throwCommands = new int[M][2];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      throwCommands[i][0] = Integer.parseInt(st.nextToken());
      throwCommands[i][1] = Integer.parseInt(st.nextToken());
    }

    System.out.println(simulation(grid, throwCommands, tagGrid));

  }
  static int simulation(int[] grid, int[][] throwCommands, int[][] tagGrid) {
    int[] burstMarbleCount = new int[4];

    int time = 0;
    while (time < M) {

      throwMarble(grid, tagGrid, throwCommands[time]);

      sortGrid(grid);

      while (burstMarble(grid, burstMarbleCount)) sortGrid(grid);

      grid = newGrid(grid);
      time++;
    }

    return burstMarbleCount[1] + 2 * burstMarbleCount[2] + 3 * burstMarbleCount[3];
  }
  static int[] newGrid(int[] grid) {

    Deque<Counter> counters = new ArrayDeque<>();

    for (int i = 1; i < N * N; i++) {
      if (grid[i] != 0) {
        if (counters.isEmpty()) counters.add(new Counter(grid[i]));
        else {
          if (counters.peekLast().value == grid[i]) counters.peekLast().count++;
          else counters.addLast(new Counter(grid[i]));
        }
      }
    }

    int[] newGrid = new int[N * N];
    Counter counter = null;
    if (!counters.isEmpty()) {
      for (int i = 1; i < N * N; i++) {

        if (i % 2 == 1)  {
          counter = counters.pollFirst();
          newGrid[i] = counter.count;
        }
        else {
          newGrid[i] = counter.value;
          if (counters.isEmpty()) break;
        }
      }
    }

    return newGrid;
  }
  static boolean burstMarble (int[] grid, int[] burstMarbleCount) {

    boolean isActive = false;
    Queue<Integer> idxs = new ArrayDeque<>();

    for (int i = 1; i < N * N; i++) {
      if (grid[i] != 0) {
        if (idxs.isEmpty()) idxs.add(i);
        else {
          if (grid[idxs.peek()] == grid[i]) idxs.add(i);
          else {
            if (idxs.size() >= 4) {
              burstMarbleCount[grid[idxs.peek()]] += idxs.size();
              while (!idxs.isEmpty()) grid[idxs.poll()] = 0;
              isActive = true;
            }
            else while (!idxs.isEmpty()) idxs.poll();

            idxs.add(i);
          }
        }
      }
    }

    if (idxs.size() >= 4) {
      burstMarbleCount[grid[idxs.peek()]] += idxs.size();
      while (!idxs.isEmpty()) grid[idxs.poll()] = 0;
    }
    return isActive;
  }
  static void sortGrid(int[] grid) {

    for (int i = 1; i < N * N - 1; i++) {
      if (grid[i] == 0) {
        int j;
        for (j = i + 1; j < N * N && grid[j] == 0; j++);

        if (j == N * N) break;
        grid[i] = grid[j];
        grid[j] = 0;
      }
    }
  }
  static void throwMarble(int[] grid, int[][] tagGrid, int[] throwCommand) {

    int direction = throwCommand[0];
    int speed = throwCommand[1];

    int row = sharkRow;
    int col = sharkCol;

    while (speed-- > 0) {
      row += dr[direction];
      col += dc[direction];

      grid[tagGrid[row][col]] = 0;
    }
  }
  static int[][] initOneDimensionGrid() {
    boolean[][] isVisited = new boolean[N + 1][N + 1];
    int[][] tagNumber = new int[N + 1][N + 1];

    isVisited[sharkRow][sharkCol] = true;

    int row = sharkRow;
    int col = sharkCol - 1;
    int direction = 1;

    int num = 1;
    while (!(row == 1 && col == 0)) {

      isVisited[row][col] = true;
      tagNumber[row][col] = num++;

      int[] nextPoint = findNextPoint(isVisited, row, col, direction);

      row = nextPoint[0];
      col = nextPoint[1];
      direction = nextPoint[2];
    }

    return tagNumber;
  }
  static int[] findNextPoint(boolean[][] isVisited, int row, int col, int direction) {

    int[] nextPoint = new int[3];

    int leftDirection = getLeftDirection(direction);
    int leftRow = row + move_dr[leftDirection];
    int leftCol = col + move_dc[leftDirection];

    if (!isVisited[leftRow][leftCol]) {
      nextPoint[0] = leftRow;
      nextPoint[1] = leftCol;
      nextPoint[2] = leftDirection;
    }
    else {
      nextPoint[0] = row + move_dr[direction];
      nextPoint[1] = col + move_dc[direction];
      nextPoint[2] = direction;
    }

    return nextPoint;
  }
  static int getLeftDirection(int direction) {
    return direction + 1 != 5 ? direction + 1 : 1;
  }

}

class Counter {
  int value;
  int count;

  Counter(int value) {
    this.value = value;
    this.count = 1;
  }
}
```