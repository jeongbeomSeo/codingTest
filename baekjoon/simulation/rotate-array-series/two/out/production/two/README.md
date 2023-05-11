# 배열 돌리기 2

**골드 5**

|시간 제한|	메모리 제한|	제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초	|512 MB	|5442	|1815|	1429|	34.777%|

## 문제 

크기가 N×M인 배열이 있을 때, 배열을 돌려보려고 한다. 배열은 다음과 같이 반시계 방향으로 돌려야 한다.

```
A[1][1] ← A[1][2] ← A[1][3] ← A[1][4] ← A[1][5]
   ↓                                       ↑
A[2][1]   A[2][2] ← A[2][3] ← A[2][4]   A[2][5]
   ↓         ↓                   ↑         ↑
A[3][1]   A[3][2] → A[3][3] → A[3][4]   A[3][5]
   ↓                                       ↑
A[4][1] → A[4][2] → A[4][3] → A[4][4] → A[4][5]
```

예를 들어, 아래와 같은 배열을 2번 회전시키면 다음과 같이 변하게 된다.

```
1 2 3 4       2 3 4 8       3 4 8 6
5 6 7 8       1 7 7 6       2 7 8 2
9 8 7 6   →   5 6 8 2   →   1 7 6 3
5 4 3 2       9 5 4 3       5 9 5 4
 <시작>         <회전1>        <회전2>
```

배열과 정수 R이 주어졌을 때, 배열을 R번 회전시킨 결과를 구해보자.

## 입력 

첫째 줄에 배열의 크기 N, M과 수행해야 하는 회전의 수 R이 주어진다.

둘째 줄부터 N개의 줄에 배열 A의 원소 A<sub>ij</sub>가 주어진다.

## 출력 

입력으로 주어진 배열을 R번 회전시킨 결과를 출력한다.

## 제한 

- 2 ≤ N, M ≤ 300
- 1 ≤ R ≤ 109
- min(N, M) mod 2 = 0
- 1 ≤ A<sub>ij</sub> ≤ 10<sup>8</sup>

## 예제 입력 1

```
4 4 2
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
```

## 예제 출력 1

```
3 4 8 12
2 11 10 16
1 7 6 15
5 9 13 14
```

## 예제 입력 2

```
5 4 7
1 2 3 4
7 8 9 10
13 14 15 16
19 20 21 22
25 26 27 28
```

## 예제 출력 2

```
28 27 26 25
22 9 15 19
16 8 21 13
10 14 20 7
4 3 2 1
```

## 예제 입력 3

```
2 2 3
1 1
1 1
```

## 예제 출력 3

```
1 1
1 1
```

## 문제  풀이

해당 문제는 **배열 돌리기 1**에서 회전 수를 급격하게 늘리면서 최적화를 요구하는 문제였습니다.

두 가지 방식을 써서 최적화를 했습니다.

1. 회전하는 사각형에 있는 숫자들의 개수 이상의 회전 수는 n바퀴 도는 것이므로 나머지 값으로 줄이기
2. r번의 회전을 요구할 때 r번의 회전을 하지 말고 r번 회전할 경우 나오는 숫자 바로 옮기기

## 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M, R;
  // 동, 남, 서, 북
  static int[] dr = {0, 1, 0, -1};
  static int[] dc = {1, 0, -1, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N + 1][M + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= M; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    simulation(grid);

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= M; j++) {
        System.out.print(grid[i][j] + " ");
      }
      System.out.println();
    }
  }
  static void simulation(int[][] grid) {

    int num = 1;
    int size_R = N;
    int size_C = M;
    int[][] buffer = initBuffer(grid);

    while (size_R - num >= 1 && size_C - num >= 1) {
      int number_count = ((size_R - num + 1) - 1) * 2 + ((size_C - num + 1) - 1) * 2;
      int cur_Rotate = R % number_count;

      if (cur_Rotate == 0) {
        size_C--;
        size_R--;
        num++;
        continue;
      }

      int row = num;
      int col = num;
      Pointer prevPtr = new Pointer(row, col, 0);
      for (int i = 0; i < cur_Rotate; i++) prevPtr.move(dr, dc, num, size_R, size_C);
      Pointer curPtr = new Pointer(row, col, 0);

      for (int i = 0; i < number_count; i++) {
        grid[curPtr.row][curPtr.col] = buffer[prevPtr.row][prevPtr.col];
        curPtr.move(dr, dc, num, size_R, size_C);
        prevPtr.move(dr, dc, num, size_R, size_C);
      }

      size_C--;
      size_R--;
      num++;
    }
  }
  static int[][] initBuffer(int[][] grid) {
    int[][] buffer = new int[N + 1][M + 1];
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= M; j++) {
        buffer[i][j] = grid[i][j];
      }
    }
    return buffer;
  }
}
class Pointer {
  int row;
  int col;
  int direction;

  Pointer(int row, int col, int direction) {
    this.row = row;
    this.col = col;
    this.direction = direction;
  }

  void move(int[] dr, int[] dc, int num, int bound_R, int bound_C) {
    int nextRow = row + dr[direction];
    int nextCol = col + dc[direction];
    int nextDirection = direction;

    if (!isValidIdx(nextRow, nextCol, num, bound_R, bound_C)) {
      nextDirection = (direction + 1) % 4;
      nextRow = row + dr[nextDirection];
      nextCol = col + dc[nextDirection];
    }

    direction = nextDirection;
    row = nextRow;
    col = nextCol;
  }

  private boolean isValidIdx(int row, int col, int num, int bound_R, int bound_C) {
    return row >= num && col >= num && row <= bound_R && col <= bound_C;
  }
}
```