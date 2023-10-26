# 감시

**골드 4**

|시간 제한|	메모리 제한|	제출|	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초|	512 MB	|41313	|19421	|11688	|43.792%|

## 문제 

스타트링크의 사무실은 1×1크기의 정사각형으로 나누어져 있는 N×M 크기의 직사각형으로 나타낼 수 있다. 사무실에는 총 K개의 CCTV가 설치되어져 있는데, CCTV는 5가지 종류가 있다. 각 CCTV가 감시할 수 있는 방법은 다음과 같다.

| 1번 | 2번 | 3번 | 4번 | 5번 |
|---|---|---|---|---|
|![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15683/1.png)| ![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15683/2.png) | ![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15683/3.png) | ![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15683/4.png) | ![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15683/5.png) |

1번 CCTV는 한 쪽 방향만 감시할 수 있다. 2번과 3번은 두 방향을 감시할 수 있는데, 2번은 감시하는 방향이 서로 반대방향이어야 하고, 3번은 직각 방향이어야 한다. 4번은 세 방향, 5번은 네 방향을 감시할 수 있다.

CCTV는 감시할 수 있는 방향에 있는 칸 전체를 감시할 수 있다. 사무실에는 벽이 있는데, CCTV는 벽을 통과할 수 없다. CCTV가 감시할 수 없는 영역은 사각지대라고 한다.

CCTV는 회전시킬 수 있는데, 회전은 항상 90도 방향으로 해야 하며, 감시하려고 하는 방향이 가로 또는 세로 방향이어야 한다.

```
0 0 0 0 0 0
0 0 0 0 0 0
0 0 1 0 6 0
0 0 0 0 0 0
```

지도에서 0은 빈 칸, 6은 벽, 1~5는 CCTV의 번호이다. 위의 예시에서 1번의 방향에 따라 감시할 수 있는 영역을 '#'로 나타내면 아래와 같다.

| →	                                                                 | ← |	↑ |	↓ |
|--------------------------------------------------------------------|---|---|---|
| `0 0 0 0 0 0`<br> `0 0 0 0 0 0`<br> `0 0 1 # 6 0` <br> `0 0 0 0 0 0` | `0 0 0 0 0 0`<br>`0 0 0 0 0 0`<br>`# # 1 0 6 0`<br>`0 0 0 0 0 0`|`0 0 # 0 0 0`<br>`0 0 # 0 0 0`<br>`0 0 1 0 6 0`<br>`0 0 0 0 0 0`| `0 0 0 0 0 0`<br>`0 0 0 0 0 0`<br>`0 0 1 0 6 0`<br>`0 0 # 0 0 0`|

CCTV는 벽을 통과할 수 없기 때문에, 1번이 → 방향을 감시하고 있을 때는 6의 오른쪽에 있는 칸을 감시할 수 없다.

```
0 0 0 0 0 0
0 2 0 0 0 0
0 0 0 0 6 0
0 6 0 0 2 0
0 0 0 0 0 0
0 0 0 0 0 5
```

위의 예시에서 감시할 수 있는 방향을 알아보면 아래와 같다.

|왼쪽 상단 2: ↔, 오른쪽 하단 2: ↔ |	왼쪽 상단 2: ↔, 오른쪽 하단 2: ↕	| 왼쪽 상단 2: ↕, 오른쪽 하단 2: ↔	| 왼쪽 상단 2: ↕, 오른쪽 하단 2: ↕ |
|---|---|---|---|
|`0 0 0 0 0 #`<br> `# 2 # # # #`<br> `0 0 0 0 6 #`<br>`0 6 # # 2 #`<br>`0 0 0 0 0 #`<br>`# # # # # 5`<br>|`0 0 0 0 0 #`<br>`# 2 # # # #`<br>`0 0 0 0 6 #`<br>`0 6 0 0 2 #`<br>`0 0 0 0 # #`<br>`# # # # # 5`|`0 # 0 0 0 #`<br>`0 2 0 0 0 #`<br>`0 # 0 0 6 #`<br>`0 6 # # 2 #`<br>`0 0 0 0 0 #`<br>`# # # # # 5`|`0 # 0 0 0 #`<br>`0 2 0 0 0 #`<br>`0 # 0 0 6 #`<br>`0 6 0 0 2 #`<br>`0 0 0 0 # #`<br>`# # # # # 5`|

CCTV는 CCTV를 통과할 수 있다. 아래 예시를 보자.

```
0 0 2 0 3
0 6 0 0 0
0 0 6 6 0
0 0 0 0 0
```

위와 같은 경우에 2의 방향이 ↕ 3의 방향이 ←와 ↓인 경우 감시받는 영역은 다음과 같다.

```
# # 2 # 3
0 6 # 0 #
0 0 6 6 #
0 0 0 0 #
```

사무실의 크기와 상태, 그리고 CCTV의 정보가 주어졌을 때, CCTV의 방향을 적절히 정해서, 사각 지대의 최소 크기를 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 사무실의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 8)

둘째 줄부터 N개의 줄에는 사무실 각 칸의 정보가 주어진다. 0은 빈 칸, 6은 벽, 1~5는 CCTV를 나타내고, 문제에서 설명한 CCTV의 종류이다.

CCTV의 최대 개수는 8개를 넘지 않는다.

## 출력 

첫째 줄에 사각 지대의 최소 크기를 출력한다.

## 예제 입력 1

```
4 6
0 0 0 0 0 0
0 0 0 0 0 0
0 0 1 0 6 0
0 0 0 0 0 0
```

## 예제 출력 1

```
20
```

## 예제 입력 2

```
6 6
0 0 0 0 0 0
0 2 0 0 0 0
0 0 0 0 6 0
0 6 0 0 2 0
0 0 0 0 0 0
0 0 0 0 0 5
```

## 예제 출력 2

```
15
```

## 예제 입력 3

```
6 6
1 0 0 0 0 0
0 1 0 0 0 0
0 0 1 0 0 0
0 0 0 1 0 0
0 0 0 0 1 0
0 0 0 0 0 1
```

## 예제 출력 3

```
6
```

## 예제 입력 4

```
6 6
1 0 0 0 0 0
0 1 0 0 0 0
0 0 1 5 0 0
0 0 5 1 0 0
0 0 0 0 1 0
0 0 0 0 0 1
```

## 예제 출력 4

```
2
```

## 예제 입력 5

```
1 7
0 1 2 3 4 5 6
```

## 예제 출력 5

```
0
```

## 예제 입력 6

```
3 7
4 0 0 0 0 0 0
0 0 0 2 0 0 0
0 0 0 0 0 0 4
```

## 예제 출력 6

```
0
```

## 코드 

**AC**

해당 풀이는 코드 최적화가 정말 안되어 있고, 코드의 중복도 너무 많기 때문에 다시 풀어서 최대한 줄여봐야한다.
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int countBlindSpot = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][M];
    // cctv 번호, cctv Row, cctv Col
    int[][] cctvList = new int[8][3];
    int size = 0;

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        int n = Integer.parseInt(st.nextToken());
        grid[i][j] = n;
        if (n != 0 && n != 6) {
          cctvList[size][0] = n;
          cctvList[size][1] = i;
          cctvList[size][2] = j;
          size++;
        }
      }
    }
    System.out.println(simulation(grid, cctvList, size));
  }
  static int simulation(int[][] baseGrid, int[][] cctvList, int size) {

    backTracking(baseGrid, cctvList, 0, size);

    return countBlindSpot;

  }
  static void backTracking(int[][] baseGrid, int[][] cctvList, int ptr, int size) {

    if (ptr == size) {
      int count = 0;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (baseGrid[i][j] == 0) count++;
        }
      }
      countBlindSpot = Math.min(countBlindSpot, count);
    }
    else {
      int curCCTVNumber = cctvList[ptr][0];

      if (curCCTVNumber == 5) {
        int[][] grid = new int[N][M];
        initGrid(grid, baseGrid);

        int row = cctvList[ptr][1];
        int col = cctvList[ptr][2];

        // 북
        for (int i = 1; row - i >= 0; i++) {
          if (grid[row - i][col] == 6) break;
          grid[row - i][col] = -1;
        }
        // 동
        for (int i = 1; col + i < M; i++) {
          if (grid[row][col + i] == 6) break;
          grid[row][col + i] = -1;
        }
        // 남
        for (int i = 1; row + i < N; i++) {
          if (grid[row + i][col] == 6) break;
          grid[row + i][col] = -1;
        }
        // 서
        for (int i = 1; col - i >= 0; i++) {
          if (grid[row][col - i] == 6) break;
          grid[row][col - i] = -1;
        }

        backTracking(grid, cctvList, ptr + 1, size);
      }

      if (curCCTVNumber == 1) {
        for (int direction = 0; direction < 4; direction++) {
          int[][] grid = new int[N][M];
          initGrid(grid, baseGrid);

          int row = cctvList[ptr][1];
          int col = cctvList[ptr][2];

          if (direction == 0) {
            for (int i = 1; row - i >= 0; i++) {
              if (grid[row - i][col] == 6) break;
              grid[row - i][col] = -1;
            }
          }

          else if (direction == 1) {
            for (int i = 1; col + i < M; i++) {
              if (grid[row][col + i] == 6) break;
              grid[row][col + i] = -1;
            }
          }

          else if (direction == 2) {
            for (int i = 1; row + i < N; i++) {
              if (grid[row + i][col] == 6) break;
              grid[row + i][col] = -1;
            }
          }

          else {
            for (int i = 1; col - i >= 0; i++) {
              if (grid[row][col - i] == 6) break;
              grid[row][col - i] = -1;
            }
          }
          backTracking(grid, cctvList, ptr + 1, size);
        }
      }

      if (curCCTVNumber == 2) {
        for (int direction = 0; direction < 2; direction++) {
          int[][] grid = new int[N][M];
          initGrid(grid, baseGrid);

          int row = cctvList[ptr][1];
          int col = cctvList[ptr][2];

          // row
          if (direction == 0) {
            for (int i = 1; row - i >= 0; i++) {
              if (grid[row - i][col] == 6) break;
              grid[row - i][col] = -1;
            }
            for (int i = 1; row + i < N; i++) {
              if (grid[row + i][col] == 6) break;
              grid[row + i][col] = -1;
            }
          }
          // col
          else {
            for (int i = 1; col + i < M; i++) {
              if (grid[row][col + i] == 6) break;
              grid[row][col + i] = -1;
            }
            for (int i = 1; col - i >= 0; i++) {
              if (grid[row][col - i] == 6) break;
              grid[row][col - i] = -1;
            }
          }
          backTracking(grid, cctvList, ptr + 1, size);
        }
      }

      if (curCCTVNumber == 3) {
        for (int direction = 0; direction < 4; direction++) {
          int[][] grid = new int[N][M];
          initGrid(grid, baseGrid);

          int row = cctvList[ptr][1];
          int col = cctvList[ptr][2];

          // 북, 동
          if (direction == 0) {
            for (int i = 1; row - i >= 0; i++) {
              if (grid[row - i][col] == 6) break;
              grid[row - i][col] = -1;
            }
            for (int i = 1; col + i < M; i++) {
              if (grid[row][col + i] == 6) break;
              grid[row][col + i] = -1;
            }
          }

          // 동, 남
          else if (direction == 1) {
            for (int i = 1; col + i < M; i++) {
              if (grid[row][col + i] == 6) break;
              grid[row][col + i] = -1;
            }
            for (int i = 1; row + i < N; i++) {
              if (grid[row + i][col] == 6) break;
              grid[row + i][col] = -1;
            }
          }

          // 남, 서
          else if (direction == 2) {
            for (int i = 1; row + i < N; i++) {
              if (grid[row + i][col] == 6) break;
              grid[row + i][col] = -1;
            }
            for (int i = 1; col - i >= 0; i++) {
              if (grid[row][col - i] == 6) break;
              grid[row][col - i] = -1;
            }
          }

          // 서, 북
          else {
            for (int i = 1; col - i >= 0; i++) {
              if (grid[row][col - i] == 6) break;
              grid[row][col - i] = -1;
            }
            for (int i = 1; row - i >= 0; i++) {
              if (grid[row - i][col] == 6) break;
              grid[row - i][col] = -1;
            }
          }
          backTracking(grid, cctvList, ptr + 1, size);
        }
      }

      if (curCCTVNumber == 4) {
        for (int direction = 0; direction < 4; direction++) {
          int[][] grid = new int[N][M];
          initGrid(grid, baseGrid);

          int row = cctvList[ptr][1];
          int col = cctvList[ptr][2];

          // 북 제외
          if (direction == 0) {
            for (int i = 1; col + i < M; i++) {
              if (grid[row][col + i] == 6) break;
              grid[row][col + i] = -1;
            }
            for (int i = 1; row + i < N; i++) {
              if (grid[row + i][col] == 6) break;
              grid[row + i][col] = -1;
            }
            for (int i = 1; col - i >= 0; i++) {
              if (grid[row][col - i] == 6) break;
              grid[row][col - i] = -1;
            }
          }

          // 동 제외
          else if (direction == 1) {
            for (int i = 1; row - i >= 0; i++) {
              if (grid[row - i][col] == 6) break;
              grid[row - i][col] = -1;
            }
            for (int i = 1; row + i < N; i++) {
              if (grid[row + i][col] == 6) break;
              grid[row + i][col] = -1;
            }
            for (int i = 1; col - i >= 0; i++) {
              if (grid[row][col - i] == 6) break;
              grid[row][col - i] = -1;
            }

          }

          // 남 제외
          else if (direction == 2) {
            for (int i = 1; row - i >= 0; i++) {
              if (grid[row - i][col] == 6) break;
              grid[row - i][col] = -1;
            }
            for (int i = 1; col + i < M; i++) {
              if (grid[row][col + i] == 6) break;
              grid[row][col + i] = -1;
            }
            for (int i = 1; col - i >= 0; i++) {
              if (grid[row][col - i] == 6) break;
              grid[row][col - i] = -1;
            }

          }

          // 서 제외
          else {
            for (int i = 1; row - i >= 0; i++) {
              if (grid[row - i][col] == 6) break;
              grid[row - i][col] = -1;
            }
            for (int i = 1; col + i < M; i++) {
              if (grid[row][col + i] == 6) break;
              grid[row][col + i] = -1;
            }
            for (int i = 1; row + i < N; i++) {
              if (grid[row + i][col] == 6) break;
              grid[row + i][col] = -1;
            }
          }
          backTracking(grid, cctvList, ptr + 1, size);
        }
      }

    }
  }
  static void initGrid(int[][] grid, int[][] baseGrid) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        grid[i][j] = baseGrid[i][j];
      }
    }
  }
}
```