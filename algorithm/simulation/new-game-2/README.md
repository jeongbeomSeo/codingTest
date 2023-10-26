# 새로운 게임 2

**골드 2**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|0.5 초	|512 MB	|12223|	6010	|3712|	47.119%|

## 문제 

재현이는 주변을 살펴보던 중 체스판과 말을 이용해서 새로운 게임을 만들기로 했다. 새로운 게임은 크기가 N×N인 체스판에서 진행되고, 사용하는 말의 개수는 K개이다. 말은 원판모양이고, 하나의 말 위에 다른 말을 올릴 수 있다. 체스판의 각 칸은 흰색, 빨간색, 파란색 중 하나로 색칠되어있다.

게임은 체스판 위에 말 K개를 놓고 시작한다. 말은 1번부터 K번까지 번호가 매겨져 있고, 이동 방향도 미리 정해져 있다. 이동 방향은 위, 아래, 왼쪽, 오른쪽 4가지 중 하나이다.

턴 한 번은 1번 말부터 K번 말까지 순서대로 이동시키는 것이다. 한 말이 이동할 때 위에 올려져 있는 말도 함께 이동한다. 말의 이동 방향에 있는 칸에 따라서 말의 이동이 다르며 아래와 같다. 턴이 진행되던 중에 말이 4개 이상 쌓이는 순간 게임이 종료된다.

- A번 말이 이동하려는 칸이
  - 흰색인 경우에는 그 칸으로 이동한다. 이동하려는 칸에 말이 이미 있는 경우에는 가장 위에 A번 말을 올려놓는다.
    - A번 말의 위에 다른 말이 있는 경우에는 A번 말과 위에 있는 모든 말이 이동한다.
    - 예를 들어, A, B, C로 쌓여있고, 이동하려는 칸에 D, E가 있는 경우에는 A번 말이 이동한 후에는 D, E, A, B, C가 된다.
  - 빨간색인 경우에는 이동한 후에 A번 말과 그 위에 있는 모든 말의 쌓여있는 순서를 반대로 바꾼다.
    - A, B, C가 이동하고, 이동하려는 칸에 말이 없는 경우에는 C, B, A가 된다.
  - A, D, F, G가 이동하고, 이동하려는 칸에 말이 E, C, B로 있는 경우에는 E, C, B, G, F, D, A가 된다.
  - 파란색인 경우에는 A번 말의 이동 방향을 반대로 하고 한 칸 이동한다. 방향을 반대로 바꾼 후에 이동하려는 칸이 파란색인 경우에는 이동하지 않고 가만히 있는다.
- 체스판을 벗어나는 경우에는 파란색과 같은 경우이다.

다음은 크기가 4×4인 체스판 위에 말이 4개 있는 경우이다.

![](https://upload.acmicpc.net/0aec7e3d-e8f5-428a-bebc-6a0fd514b387/-/preview/)

첫 번째 턴은 다음과 같이 진행된다.

![](https://upload.acmicpc.net/46796304-b486-4420-9d2c-ea49e2d5665b/-/preview/)
![](https://upload.acmicpc.net/04643ced-fdfd-46f5-a07e-374704dbb1c5/-/preview/)
![](https://upload.acmicpc.net/46f4bfab-841b-41c8-842e-56027816f846/-/preview/)
![](https://upload.acmicpc.net/fcccf76c-9431-4ff5-8a05-7dbd2feff142/-/preview/)

두 번째 턴은 다음과 같이 진행된다.

![](https://upload.acmicpc.net/36568153-8c2a-4fe9-b45f-72036c97f5aa/-/preview/)
![](https://upload.acmicpc.net/babead43-4acc-425d-917a-54dcc6f45414/-/preview/)
![](https://upload.acmicpc.net/1edd5ed8-0f4c-4c6d-b304-3b7642f42c6f/-/preview/)
![](https://upload.acmicpc.net/028a5dd2-5524-4475-8439-9e7794e28ee4/-/preview/)

체스판의 크기와 말의 위치, 이동 방향이 모두 주어졌을 때, 게임이 종료되는 턴의 번호를 구해보자.

## 입력 

첫째 줄에 체스판의 크기 N, 말의 개수 K가 주어진다. 둘째 줄부터 N개의 줄에 체스판의 정보가 주어진다. 체스판의 정보는 정수로 이루어져 있고, 각 정수는 칸의 색을 의미한다. 0은 흰색, 1은 빨간색, 2는 파란색이다.

다음 K개의 줄에 말의 정보가 1번 말부터 순서대로 주어진다. 말의 정보는 세 개의 정수로 이루어져 있고, 순서대로 행, 열의 번호, 이동 방향이다. 행과 열의 번호는 1부터 시작하고, 이동 방향은 4보다 작거나 같은 자연수이고 1부터 순서대로 →, ←, ↑, ↓의 의미를 갖는다.

같은 칸에 말이 두 개 이상 있는 경우는 입력으로 주어지지 않는다.

## 출력 

게임이 종료되는 턴의 번호를 출력한다. 그 값이 1,000보다 크거나 절대로 게임이 종료되지 않는 경우에는 -1을 출력한다.

## 제한 

- 4 ≤ N ≤ 12
- 4 ≤ K ≤ 10

## 예제 입력 1

```
4 4
0 0 2 0
0 0 1 0
0 0 1 2
0 2 0 0
2 1 1
3 2 3
2 2 1
4 1 2
```

## 예제 출력 1

```
-1
```

## 코드

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  static int N, K;
  static int[] dr = {0, 0, 0, -1, 1};
  static int[] dc = {0, 1, -1, 0, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N + 1][N + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    Horse[] horses = new Horse[K + 1];
    for (int i = 1; i <= K; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());
      horses[i] = new Horse(row, col, direction);
    }

    System.out.println(simulation(grid, horses));
  }
  static int simulation (int[][] gird, Horse[] horses) {

    int time = 1;

    ArrayList<Integer>[][] idxGrid = initIdxGrid(horses);

    while (time < 1001) {

      if(moveHorse(gird, horses, idxGrid)) break;

      time++;
    }

    if (time == 1001) return -1;
    return time;
  }
  static boolean moveHorse (int[][] grid, Horse[] horses, ArrayList<Integer>[][] idxGrid) {

    boolean result = false;
    for (int i = 1; i <= K; i++) {
      Horse horse = horses[i];

      int nextRow = horse.row + dr[horse.direction];
      int nextCol = horse.col + dc[horse.direction];

      int nextPointColor = -1;
      if (isValidIdx(nextRow, nextCol)) {
        nextPointColor = grid[nextRow][nextCol];
      }
      else nextPointColor = 2;

      if (nextPointColor == 0) result = moveWhitePoint(i, horse.row, horse.col, nextRow, nextCol, horses, idxGrid);
      else if (nextPointColor == 1) result = moveRedPoint(i, horse.row, horse.col, nextRow, nextCol, horses, idxGrid);
      else result = moveBluePoint(i, grid, horse, horses, idxGrid);

      if (result) break;
    }

    return result;
  }
  static int reverseDirection(int direction) {
    if (direction == 1) return 2;
    else if (direction == 2) return 1;
    else if (direction == 3) return 4;
    else return 3;
  }
  static boolean moveWhitePoint(int originIdx, int row, int col, int nextRow, int nextCol, Horse[] horses, ArrayList<Integer>[][] idxGrid) {

    int startIdx = 0;
    while (idxGrid[row][col].get(startIdx) != originIdx) startIdx++;

    for (int i = startIdx; i < idxGrid[row][col].size(); i++) {
      int idx = idxGrid[row][col].get(i);

      horses[idx].row = nextRow;
      horses[idx].col = nextCol;

      if (idxGrid[nextRow][nextCol] == null) idxGrid[nextRow][nextCol] = new ArrayList<>();
      idxGrid[nextRow][nextCol].add(idx);
    }

    int startPoint = startIdx;
    int endPoint = idxGrid[row][col].size();
    while(startPoint++ < endPoint) idxGrid[row][col].remove(startIdx);

    return idxGrid[nextRow][nextCol].size() >= 4;
  }
  static boolean moveBluePoint(int originIdx, int[][] grid, Horse horse, Horse[] horses, ArrayList<Integer>[][] idxGrid) {

    boolean result = false;
    horse.direction = reverseDirection(horse.direction);

    int row = horse.row;
    int col = horse.col;
    int nextRow = horse.row + dr[horse.direction];
    int nextCol = horse.col + dc[horse.direction];

    int nextPointColor = -1;
    if (isValidIdx(nextRow, nextCol)) {
      nextPointColor = grid[nextRow][nextCol];
    }
    else nextPointColor = 2;

    if (nextPointColor != 2) {
      if (nextPointColor == 0) result = moveWhitePoint(originIdx, row, col, nextRow, nextCol, horses, idxGrid);
      else if (nextPointColor == 1) result =  moveRedPoint(originIdx, row, col, nextRow, nextCol, horses, idxGrid);
    }

    return result;
  }
  static boolean moveRedPoint(int originIdx, int row, int col, int nextRow, int nextCol, Horse[] horses, ArrayList<Integer>[][] idxGrid) {

    int endIdx = idxGrid[row][col].size() - 1;
    while (idxGrid[row][col].get(endIdx) != originIdx) endIdx--;

    for (int i = idxGrid[row][col].size() - 1; i >= endIdx; i--) {
      int idx = idxGrid[row][col].get(i);

      horses[idx].row = nextRow;
      horses[idx].col = nextCol;

      if (idxGrid[nextRow][nextCol] == null) idxGrid[nextRow][nextCol] = new ArrayList<>();
      idxGrid[nextRow][nextCol].add(idx);
    }

    int startPoint = endIdx;
    int endPoint = idxGrid[row][col].size();
    while(startPoint++ < endPoint) idxGrid[row][col].remove(endIdx);
    return idxGrid[nextRow][nextCol].size() >= 4;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 1 && col >= 1 && row <= N && col <= N;
  }
  static ArrayList<Integer>[][] initIdxGrid(Horse[] horses) {

    ArrayList<Integer>[][] idxGrid = new ArrayList[N + 1][N + 1];
    for (int i = 1; i <= K; i++) {
      Horse horse = horses[i];

      if (idxGrid[horse.row][horse.col] == null) idxGrid[horse.row][horse.col] = new ArrayList<>();
      idxGrid[horse.row][horse.col].add(i);
    }

    return idxGrid;
  }
}
class Horse {
  int row;
  int col;
  int direction;

  Horse(int row, int col, int direction) {
    this.row = row;
    this.col = col;
    this.direction = direction;
  }
}
```