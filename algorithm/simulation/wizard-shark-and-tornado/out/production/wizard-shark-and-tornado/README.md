# 마법사 상어와 토네이도 

**골드 3**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|512 MB|	8246	|5861|	3915|	71.027%|

## 문제 

시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
1 초	512 MB	8246	5861	3915	71.027%
문제
마법사 상어가 토네이도를 배웠고, 오늘은 토네이도를 크기가 N×N인 격자로 나누어진 모래밭에서 연습하려고 한다. 위치 (r, c)는 격자의 r행 c열을 의미하고, A[r][c]는 (r, c)에 있는 모래의 양을 의미한다.

토네이도를 시전하면 격자의 가운데 칸부터 토네이도의 이동이 시작된다. 토네이도는 한 번에 한 칸 이동한다. 다음은 N = 7인 경우 토네이도의 이동이다.

![](https://upload.acmicpc.net/37e7aa13-0f2b-49d6-af68-e745537b1ea3/-/preview/)

토네이도가 한 칸 이동할 때마다 모래는 다음과 같이 일정한 비율로 흩날리게 된다.

![](https://upload.acmicpc.net/33b01ca0-4659-49f1-b126-8e042e17d3f1/-/preview/)

토네이도가 x에서 y로 이동하면, y의 모든 모래가 비율과 α가 적혀있는 칸으로 이동한다. 비율이 적혀있는 칸으로 이동하는 모래의 양은 y에 있는 모래의 해당 비율만큼이고, 계산에서 소수점 아래는 버린다. α로 이동하는 모래의 양은 비율이 적혀있는 칸으로 이동하지 않은 남은 모래의 양과 같다. 모래가 이미 있는 칸으로 모래가 이동하면, 모래의 양은 더해진다. 위의 그림은 토네이도가 왼쪽으로 이동할 때이고, 다른 방향으로 이동하는 경우는 위의 그림을 해당 방향으로 회전하면 된다.

토네이도는 (1, 1)까지 이동한 뒤 소멸한다. 모래가 격자의 밖으로 이동할 수도 있다. 토네이도가 소멸되었을 때, 격자의 밖으로 나간 모래의 양을 구해보자.

## 입력 

첫째 줄에 격자의 크기 N이 주어진다. 둘째 줄부터 N개의 줄에는 격자의 각 칸에 있는 모래가 주어진다. r번째 줄에서 c번째 주어지는 정수는 A[r][c] 이다.

## 출력 

격자의 밖으로 나간 모래의 양을 출력한다.

## 제한 

- 3 ≤ N ≤ 499
- N은 홀수
- 0 ≤ A[r][c] ≤ 1,000
- 가운데 칸에 있는 모래의 양은 0

##  예제 입력 1

```
5
0 0 0 0 0
0 0 0 0 0
0 10 0 0 0
0 0 0 0 0
0 0 0 0 0
```

## 예제 출력 1

```
10
```

## 코드

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {0, 1, 0, -1};
  static int[] dc = {-1, 0, 1, 0};
  static int N;
  static int amount = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    N = Integer.parseInt(br.readLine());

    int[][] grid = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(simulation(grid));
  }
  static int simulation(int[][] grid) {

    int row = N / 2;
    int col = N / 2;
    boolean[][] isVisited = new boolean[N][N];
    isVisited[row][col] = true;

    while (!(row == 0 && col == 0)) {
      for (int d = 0; d < 4; d++) {

        int nextD = d + 1 != 4 ? d + 1 : 0;
        do {
          if (row == 0 && col == 0) break;
          int nextRow = row + dr[d];
          int nextCol = col + dc[d];

          if (isValidIdx(nextRow, nextCol)) {
            Ratio[] ratios = createTornadoRatio(d);
            active_tornado(grid, nextRow, nextCol, ratios);
            row = nextRow;
            col = nextCol;
            isVisited[row][col] = true;
          }
        } while (isVisited[row + dr[nextD]][col + dc[nextD]]);
      }
    }
    return amount;
  }
  static void active_tornado(int[][] grid, int row, int col, Ratio[] ratios) {

    int outAmount = 0;
    int originAmount = grid[row][col];
    int remainAmount = originAmount;
    if (originAmount == 0) return;
    for (int i = 0; i <= 8; i++) {
      if (isValidIdx(row + ratios[i].row, col + ratios[i].col)) {
        grid[row + ratios[i].row][col + ratios[i].col] += (int)(originAmount * (double)ratios[i].ratio / 100);
      }
      else outAmount += (int)(originAmount * (double)ratios[i].ratio / 100);

      remainAmount -= (int)(originAmount * (double)ratios[i].ratio / 100);
    }
    if (isValidIdx(row + ratios[9].row, col + ratios[9].col)) grid[row + ratios[9].row][col + ratios[9].col] += remainAmount;
    else outAmount += remainAmount;

    amount += outAmount;
    grid[row][col] = 0;
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
  static Ratio[] createTornadoRatio(int direction) {
    Ratio[] newRatio = new Ratio[10];

    if (direction == 0 || direction == 2) {
      int pow = direction == 0 ? 1 : -1;
      newRatio[0] = new Ratio(0, -2, 5, pow);
      newRatio[1] = new Ratio(-1, -1, 10, pow);
      newRatio[2] = new Ratio(1, -1, 10, pow);
      newRatio[3] = new Ratio(-1, 0, 7, pow);
      newRatio[4] = new Ratio(1, 0, 7, pow);
      newRatio[5] = new Ratio(-2, 0, 2, pow);
      newRatio[6] = new Ratio(2, 0, 2, pow);
      newRatio[7] = new Ratio(-1, 1, 1, pow);
      newRatio[8] = new Ratio(1, 1, 1, pow);
      newRatio[9] = new Ratio(0, -1, 0, pow);
    }
    else {
      int pow = direction == 1 ? 1 : -1;
      newRatio[0] = new Ratio(2, 0, 5, pow);
      newRatio[1] = new Ratio(1, 1, 10, pow);
      newRatio[2] = new Ratio(1, -1, 10, pow);
      newRatio[3] = new Ratio(0, 1, 7, pow);
      newRatio[4] = new Ratio(0, -1, 7, pow);
      newRatio[5] = new Ratio(0, 2, 2, pow);
      newRatio[6] = new Ratio(0, -2, 2, pow);
      newRatio[7] = new Ratio(-1, -1, 1, pow);
      newRatio[8] = new Ratio(-1, 1, 1, pow);
      newRatio[9] = new Ratio(1, 0, 0, pow);
    }
    return newRatio;
  }

}
class Ratio {
  int row;
  int col;
  int ratio;

  Ratio(int row, int col, int ratio, int pow) {
    this.row = row * pow;
    this.col = col * pow;
    this.ratio = ratio;
  }
}
```