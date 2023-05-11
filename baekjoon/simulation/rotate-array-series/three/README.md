# 배열 돌리기 3

**실버 1**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|2 초	|512 MB|	7860|	4045|	2965	|50.823%|

## 문제 

크기가 N×M인 배열이 있을 때, 배열에 연산을 R번 적용하려고 한다. 연산은 총 6가지가 있다.

1번 연산은 배열을 상하 반전시키는 연산이다.

```
1 6 2 9 8 4 → 4 2 9 3 1 8
7 2 6 9 8 2 → 9 2 3 6 1 5
1 8 3 4 2 9 → 7 4 6 2 3 1
7 4 6 2 3 1 → 1 8 3 4 2 9
9 2 3 6 1 5 → 7 2 6 9 8 2
4 2 9 3 1 8 → 1 6 2 9 8 4
   <배열>       <연산 결과>
```

2번 연산은 배열을 좌우 반전시키는 연산이다.

```
1 6 2 9 8 4 → 4 8 9 2 6 1
7 2 6 9 8 2 → 2 8 9 6 2 7
1 8 3 4 2 9 → 9 2 4 3 8 1
7 4 6 2 3 1 → 1 3 2 6 4 7
9 2 3 6 1 5 → 5 1 6 3 2 9
4 2 9 3 1 8 → 8 1 3 9 2 4
   <배열>       <연산 결과>
```

3번 연산은 오른쪽으로 90도 회전시키는 연산이다.

```
1 6 2 9 8 4 → 4 9 7 1 7 1
7 2 6 9 8 2 → 2 2 4 8 2 6
1 8 3 4 2 9 → 9 3 6 3 6 2
7 4 6 2 3 1 → 3 6 2 4 9 9
9 2 3 6 1 5 → 1 1 3 2 8 8
4 2 9 3 1 8 → 8 5 1 9 2 4
   <배열>       <연산 결과>
```

4번 연산은 왼쪽으로 90도 회전시키는 연산이다.

```
1 6 2 9 8 4 → 4 2 9 1 5 8
7 2 6 9 8 2 → 8 8 2 3 1 1
1 8 3 4 2 9 → 9 9 4 2 6 3
7 4 6 2 3 1 → 2 6 3 6 3 9
9 2 3 6 1 5 → 6 2 8 4 2 2
4 2 9 3 1 8 → 1 7 1 7 9 4
   <배열>       <연산 결과>
```

5, 6번 연산을 수행하려면 배열을 크기가 N/2×M/2인 4개의 부분 배열로 나눠야 한다. 아래 그림은 크기가 6×8인 배열을 4개의 그룹으로 나눈 것이고, 1부터 4까지의 수로 나타냈다.

```
1 1 1 1 2 2 2 2
1 1 1 1 2 2 2 2
1 1 1 1 2 2 2 2
4 4 4 4 3 3 3 3
4 4 4 4 3 3 3 3
4 4 4 4 3 3 3 3
```

5번 연산은 1번 그룹의 부분 배열을 2번 그룹 위치로, 2번을 3번으로, 3번을 4번으로, 4번을 1번으로 이동시키는 연산이다.

```
3 2 6 3 1 2 9 7 → 2 1 3 8 3 2 6 3
9 7 8 2 1 4 5 3 → 1 3 2 8 9 7 8 2
5 9 2 1 9 6 1 8 → 4 5 1 9 5 9 2 1
2 1 3 8 6 3 9 2 → 6 3 9 2 1 2 9 7
1 3 2 8 7 9 2 1 → 7 9 2 1 1 4 5 3
4 5 1 9 8 2 1 3 → 8 2 1 3 9 6 1 8
     <배열>            <연산 결과>
```

6번 연산은 1번 그룹의 부분 배열을 4번 그룹 위치로, 4번을 3번으로, 3번을 2번으로, 2번을 1번으로 이동시키는 연산이다.

```
3 2 6 3 1 2 9 7 → 1 2 9 7 6 3 9 2
9 7 8 2 1 4 5 3 → 1 4 5 3 7 9 2 1
5 9 2 1 9 6 1 8 → 9 6 1 8 8 2 1 3
2 1 3 8 6 3 9 2 → 3 2 6 3 2 1 3 8
1 3 2 8 7 9 2 1 → 9 7 8 2 1 3 2 8
4 5 1 9 8 2 1 3 → 5 9 2 1 4 5 1 9
     <배열>            <연산 결과>
```

## 입력 

첫째 줄에 배열의 크기 N, M과 수행해야 하는 연산의 수 R이 주어진다.

둘째 줄부터 N개의 줄에 배열 A의 원소 A<sub>ij</sub>가 주어진다.

마지막 줄에는 수행해야 하는 연산이 주어진다. 연산은 공백으로 구분되어져 있고, 문제에서 설명한 연산 번호이며, 순서대로 적용시켜야 한다.

## 출력 

입력으로 주어진 배열에 R개의 연산을 순서대로 수행한 결과를 출력한다.

## 제한 

- 2 ≤ N, M ≤ 100
- 1 ≤ R ≤ 1,000
- N, M은 짝수
- 1 ≤ A<sub>ij</sub> ≤ 108

## 예제 입력 1

```
6 8 1
3 2 6 3 1 2 9 7
9 7 8 2 1 4 5 3
5 9 2 1 9 6 1 8
2 1 3 8 6 3 9 2
1 3 2 8 7 9 2 1
4 5 1 9 8 2 1 3
1
```

## 예제 출력 1

```
4 5 1 9 8 2 1 3
1 3 2 8 7 9 2 1
2 1 3 8 6 3 9 2
5 9 2 1 9 6 1 8
9 7 8 2 1 4 5 3
3 2 6 3 1 2 9 7
```

## 예제 입력 2

```
6 8 1
3 2 6 3 1 2 9 7
9 7 8 2 1 4 5 3
5 9 2 1 9 6 1 8
2 1 3 8 6 3 9 2
1 3 2 8 7 9 2 1
4 5 1 9 8 2 1 3
2
```

## 예제 출력 2

```
7 9 2 1 3 6 2 3
3 5 4 1 2 8 7 9
8 1 6 9 1 2 9 5
2 9 3 6 8 3 1 2
1 2 9 7 8 2 3 1
3 1 2 8 9 1 5 4
```

## 예제 입력 3

```
6 8 1
3 2 6 3 1 2 9 7
9 7 8 2 1 4 5 3
5 9 2 1 9 6 1 8
2 1 3 8 6 3 9 2
1 3 2 8 7 9 2 1
4 5 1 9 8 2 1 3
3
```

## 예제 출력 3

```
4 1 2 5 9 3
5 3 1 9 7 2
1 2 3 2 8 6
9 8 8 1 2 3
8 7 6 9 1 1
2 9 3 6 4 2
1 2 9 1 5 9
3 1 2 8 3 7
```

## 예제 입력 4

```
6 8 1
3 2 6 3 1 2 9 7
9 7 8 2 1 4 5 3
5 9 2 1 9 6 1 8
2 1 3 8 6 3 9 2
1 3 2 8 7 9 2 1
4 5 1 9 8 2 1 3
4
```

## 예제 출력 4

```
7 3 8 2 1 3
9 5 1 9 2 1
2 4 6 3 9 2
1 1 9 6 7 8
3 2 1 8 8 9
6 8 2 3 2 1
2 7 9 1 3 5
3 9 5 2 1 4
```

## 예제 입력 5

```
6 8 1
3 2 6 3 1 2 9 7
9 7 8 2 1 4 5 3
5 9 2 1 9 6 1 8
2 1 3 8 6 3 9 2
1 3 2 8 7 9 2 1
4 5 1 9 8 2 1 3
5
```

## 예제 출력 5

```
2 1 3 8 3 2 6 3
1 3 2 8 9 7 8 2
4 5 1 9 5 9 2 1
6 3 9 2 1 2 9 7
7 9 2 1 1 4 5 3
8 2 1 3 9 6 1 8
```

## 예제 입력 6

```
6 8 1
3 2 6 3 1 2 9 7
9 7 8 2 1 4 5 3
5 9 2 1 9 6 1 8
2 1 3 8 6 3 9 2
1 3 2 8 7 9 2 1
4 5 1 9 8 2 1 3
6
```

## 예제 출력 6

```
1 2 9 7 6 3 9 2
1 4 5 3 7 9 2 1
9 6 1 8 8 2 1 3
3 2 6 3 2 1 3 8
9 7 8 2 1 3 2 8
5 9 2 1 4 5 1 9
```

## 예제 입력 7

```
6 8 6
3 2 6 3 1 2 9 7
9 7 8 2 1 4 5 3
5 9 2 1 9 6 1 8
2 1 3 8 6 3 9 2
1 3 2 8 7 9 2 1
4 5 1 9 8 2 1 3
1 2 3 4 5 6
```

## 예제 출력 7

```
3 1 2 8 9 1 5 4
1 2 9 7 8 2 3 1
2 9 3 6 8 3 1 2
8 1 6 9 1 2 9 5
3 5 4 1 2 8 7 9
7 9 2 1 3 6 2 3
```

## 코드

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M, R;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[] rotate = new int[R];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < R; i++) {
      rotate[i] = Integer.parseInt(st.nextToken());
    }

    int[][] result = simulation(grid, rotate);

    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[i].length; j++) {
        System.out.print(result[i][j] + " ");
      }
      System.out.println();
    }
  }
  static int[][] simulation(int[][] grid, int[] rotate) {

    for (int rotate_number : rotate) {
      int[][] rotate_grid = null;
      switch (rotate_number) {
        case 1 :
          rotate_grid = rotateOne(grid);
          break;
        case 2:
          rotate_grid = rotateTwo(grid);
          break;
        case 3:
          rotate_grid = rotateThree(grid);
          break;
        case 4:
          rotate_grid = rotateFour(grid);
          break;
        case 5:
          rotate_grid = rotateFive_Six(grid, 5);
          break;
        case 6:
          rotate_grid = rotateFive_Six(grid, 6);
          break;
      }
      grid = rotate_grid;
    }

    return grid;
  }
  static int[][] rotateOne(int[][] baseGrid) {
    int row_length = baseGrid.length;
    int col_length = baseGrid[0].length;
    int[][] grid = new int[row_length][col_length];

    for (int i = 0; i < row_length; i++) {
      for (int j = 0; j < col_length; j++) {
        grid[i][j] = baseGrid[row_length - 1 - i][j];
      }
    }
    return grid;
  }
  static int[][] rotateTwo(int[][] baseGrid) {
    int row_length = baseGrid.length;
    int col_length = baseGrid[0].length;
    int[][] grid = new int[row_length][col_length];

    for (int i = 0; i < row_length; i++) {
      for (int j = 0; j < col_length; j++) {
        grid[i][j] = baseGrid[i][col_length - 1 - j];
      }
    }
    return grid;
  }
  static int[][] rotateThree(int[][] baseGrid) {
    int row_length = baseGrid[0].length;
    int col_length = baseGrid.length;
    int[][] grid = new int[row_length][col_length];

    for (int i = 0; i < row_length; i++) {
      for (int j = 0; j < col_length; j++) {
        grid[i][j] = baseGrid[col_length - 1 - j][i];
      }
    }
    return grid;
  }
  static int[][] rotateFour(int[][] baseGrid) {
    int row_length = baseGrid[0].length;
    int col_length = baseGrid.length;
    int[][] grid = new int[row_length][col_length];

    for (int i = 0; i < row_length; i++) {
      for (int j = 0; j < col_length; j++) {
        grid[i][j] = baseGrid[j][row_length - 1 - i];
      }
    }
    return grid;
  }
  static int[][] rotateFive_Six(int[][] baseGrid, int num) {
    int row_length = baseGrid.length;
    int col_length = baseGrid[0].length;
    int[][] grid = new int[row_length][col_length];

    int[] dr;
    int[] dc;
    // 남, 동, 북, 서 (N / 2, M / 2)
    dr = new int[]{row_length / 2, 0, -row_length / 2, 0};
    dc = new int[]{0, col_length / 2, 0, -col_length / 2};
    if (num == 6) {
      // 동, 남, 서, 북
      dr = new int[]{0, row_length / 2, 0, -row_length / 2};
      dc = new int[]{col_length / 2, 0, -col_length / 2, 0};
    }

      for (int i = 0; i < row_length / 2; i++) {
        for (int j = 0; j < col_length / 2; j++) {
          int row = i;
          int col = j;
          for (int k = 0; k < 4; k++) {
            grid[row][col] = baseGrid[row + dr[k]][col + dc[k]];
            row += dr[k];
            col += dc[k];
          }
        }
      }
      return grid;
  }
}
```