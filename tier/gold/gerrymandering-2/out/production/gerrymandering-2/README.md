# 게리맨더링 2

|시간 제한|	메모리 제한|	제출|	정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|512 MB|	11284|	6656	|4174|	57.178%|

## 문제 

재현시의 시장 구재현은 지난 몇 년간 게리맨더링을 통해서 자신의 당에게 유리하게 선거구를 획정했다. 견제할 권력이 없어진 구재현은 권력을 매우 부당하게 행사했고, 심지어는 시의 이름도 재현시로 변경했다. 이번 선거에서는 최대한 공평하게 선거구를 획정하려고 한다.

재현시는 크기가 N×N인 격자로 나타낼 수 있다. 격자의 각 칸은 구역을 의미하고, r행 c열에 있는 구역은 (r, c)로 나타낼 수 있다. 구역을 다섯 개의 선거구로 나눠야 하고, 각 구역은 다섯 선거구 중 하나에 포함되어야 한다. 선거구는 구역을 적어도 하나 포함해야 하고, 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 한다. 구역 A에서 인접한 구역을 통해서 구역 B로 갈 수 있을 때, 두 구역은 연결되어 있다고 한다. 중간에 통하는 인접한 구역은 0개 이상이어야 하고, 모두 같은 선거구에 포함된 구역이어야 한다.

선거구를 나누는 방법은 다음과 같다.

1. 기준점 (x, y)와 경계의 길이 d1, d2를 정한다. (d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N)
2. 다음 칸은 경계선이다
   1. (x, y), (x+1, y-1), ..., (x+d<sub>1</sub>, y-d<sub>1</sub>)
   2. (x, y), (x+1, y+1), ..., (x+d<sub>1</sub>, y+d<sub>1</sub>)
   3. (x+d<sub>1</sub>, y-d<sub>1</sub>), (x+d<sub>1</sub>+1, y-d<sub>1</sub>+1), ... (x+d<sub>1</sub>+d<sub>1</sub>, y-d<sub>1</sub>+d<sub>1</sub>)
   4. (x+d<sub>1</sub>, y+d<sub>1</sub>), (x+d<sub>1</sub>+1, y+d<sub>1</sub>-1), ..., (x+d<sub>1</sub>+d<sub>1</sub>, y+d<sub>1</sub>-d<sub>1</sub>)
3. 경계선과 경계선의 안에 포함되어있는 곳은 5번 선거구이다.
4. 5번 선거구에 포함되지 않은 구역 (r, c)의 선거구 번호는 다음 기준을 따른다.
   - 1번 선거구: 1 ≤ r < x+d<sub>1</sub>, 1 ≤ c ≤ y
   - 2번 선거구: 1 ≤ r ≤ x+d<sub>2</sub>, y < c ≤ N
   - 3번 선거구: x+d<sub>1</sub> ≤ r ≤ N, 1 ≤ c < y-d<sub>1</sub>+d<sub>1</sub>
   - 4번 선거구: x+d<sub>2</sub> < r ≤ N, y-d<sub>1</sub> + d<sub>2</sub> ≤ c ≤ N

아래는 크기가 7×7인 재현시를 다섯 개의 선거구로 나눈 방법의 예시이다.

|![](https://upload.acmicpc.net/c144c31e-db45-4094-9c1d-0656a690aef0/-/preview/)| ![](https://upload.acmicpc.net/813c38e0-3197-4589-bc96-17d96eb9ed14/-/preview/) | ![](https://upload.acmicpc.net/892417dd-b824-4d4e-8bce-2faf341a9f66/-/preview/) |
|---|-------|-------|
|x = 2, y = 4, d<sub>1</sub> = 2, d<sub>2</sub> = 2|x = 2, y = 5, d<sub>1</sub> = 3, d<sub>2</sub> = 2| x = 4, y = 3, d<sub>1</sub> = 1, d<sub>2</sub> = 1|

구역 (r, c)의 인구는 A[r][c]이고, 선거구의 인구는 선거구에 포함된 구역의 인구를 모두 합한 값이다. 선거구를 나누는 방법 중에서, 인구가 가장 많은 선거구와 가장 적은 선거구의 인구 차이의 최솟값을 구해보자.

## 입력 

첫째 줄에 재현시의 크기 N이 주어진다.

둘째 줄부터 N개의 줄에 N개의 정수가 주어진다. r행 c열의 정수는 A[r][c]를 의미한다.

## 출력 

첫째 줄에 인구가 가장 많은 선거구와 가장 적은 선거구의 인구 차이의 최솟값을 출력한다.

## 제한

- 5 ≤ N ≤ 20
- 1 ≤ A[r][c] ≤ 100

## 예제 입력 1

```
6
1 2 3 4 1 6
7 8 9 1 4 2
2 3 4 1 1 3
6 6 6 6 9 4
9 1 9 1 9 5
1 1 1 1 9 9
```

## 예제 출력 1

```
18
```


![](https://upload.acmicpc.net/4ed91e95-51eb-461b-979a-fce236c79094/-/preview/)

## 예제 입력 2

```
6
5 5 5 5 5 5
5 5 5 5 5 5
5 5 5 5 5 5
5 5 5 5 5 5
5 5 5 5 5 5
5 5 5 5 5 5
```

## 예제 출력 2

```
20
```

## 예제 입력 3

```
8
1 2 3 4 5 6 7 8
2 3 4 5 6 7 8 9
3 4 5 6 7 8 9 1
4 5 6 7 8 9 1 2
5 6 7 8 9 1 2 3
6 7 8 9 1 2 3 4
7 8 9 1 2 3 4 5
8 9 1 2 3 4 5 6
```

## 예제 출력 3

```
23
```

![](https://upload.acmicpc.net/760daa25-5f4b-4077-825c-ba208a99ab6f/-/preview/)

## 오류 코드

해당 코드는 **ArrayIndexOutOfBounds**가 나옵니다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
   static int[] ax = {0, -1, 0, 1};
   static int[] ay = {-1, 0, 1, 0};
   static int INF = 40001;
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;

      int N = Integer.parseInt(br.readLine());
      int[][] A = new int[N + 1][N + 1];

      for (int i = 0; i < N; i++) {
         st = new StringTokenizer(br.readLine());
         for (int j = 0; j < N; j++) {
            A[i][j] = Integer.parseInt(st.nextToken());
         }
      }

      System.out.println(simulation(A, N));

   }
   static int simulation(int[][] A, int N) {
      int min_diff = INF;
      for (int d1 = 1; d1 <= N; d1++) {
         for (int d2 = 1; d2 <= N; d2++) {
            for (int x = 1; x + d1 + d2 <= N; x++) {
               for (int y = d1 + 1; y + d2 <= N; y++) {

                  // 격자 생성
                  int[][] grid = new int[N + 1][N + 1];

                  // 경계선 채우기
                  for (int i = 0; i <= d1; i++) {
                     grid[i + i][y - i] = 5;
                     grid[x + d2 + i][y + d2 - i] = 5;
                  }
                  for (int i = 0; i <= d2; i++) {
                     grid[x + i][y + i] = 5;
                     grid[x + d1 + i][y - d1 + i] = 5;
                  }

                  // 격자 꼭지점 늘려주기
                  // 1구역, 2구역, 3구역, 4구역
                  for (int i = x - 1; i >= 1; i--)
                     grid[i][y] = 1;
                  for (int j = y + d2 + 1; j <= N; j++)
                     grid[x + d2][j] = 2;
                  for (int j = y - d1 - 1; j >= 1; j--)
                     grid[x + d1][j] = 3;
                  for (int i = x + d1 + d2 + 1; i <= N; i++)
                     grid[i][y - d1 + d2] = 4;


                  // 5를 제외한 나머지 구역 채우기
                  boolean[][] isVisited = new boolean[N + 1][N + 1];
                  // 1구역
                  dfs(grid, isVisited, N, x, y - 1, 1);
                  // 2구역
                  dfs(grid, isVisited, N, x, y + 1, 2);
                  // 3구역
                  dfs(grid, isVisited, N, x + d1 + d2, y - d1 + d2 - 1, 3);
                  // 4구역
                  dfs(grid, isVisited, N, x + d1 + d2 + 1, y - d1 + d2, 4);

                  for (int i = 1; i <= N; i++) {
                     for (int j = 1; j <= N; j++) {
                        if (grid[i][j] == 0) grid[i][j] = 5;
                     }
                  }

                  int[] areaSum = new int[6];

                  for (int i = 1; i <= N ; i++) {
                     for (int j = 1; j <= N; j++) {
                        int areaNum = grid[i][j];
                        areaSum[areaNum] += A[i][j];
                     }
                  }

                  int max = 0;
                  int min = INF;

                  for (int i = 1; i <= 5; i++) {
                     max = Math.max(areaSum[i], max);
                     min = Math.min(areaSum[i], min);
                  }

                  min_diff = Math.min(max - min, min_diff);
               }
            }
         }
      }
      return min_diff;
   }

   static void dfs(int[][] grid, boolean[][] isVisited, int N, int x, int y, int num) {

      isVisited[x][y] = true;
      grid[x][y] = num;

      for (int i = 0; i < 4; i++) {
         int nextX = x + ax[i];
         int nextY = y + ay[i];

         if (isValidIdx(nextX, nextY, N) && grid[nextX][nextY] == 0 && !isVisited[nextX][nextY]) {
            dfs(grid, isVisited, N, nextX, nextY, num);
         }
      }
   }

   static boolean isValidIdx(int x, int y, int N) {
      if (x < 1 || y < 1 || x > N || y > N) return false;
      return true;
   }
}
```

일단 첫번째로, 해당 부분을 봐보자.

```java
dfs(grid, isVisited, N, x + d1 + d2 + 1, y - d1 + d2, 4);
```

여기서 dfs를 처리할 때 시작점의 위치를 저런 식으로 해버릴 경우 범위를 벗어나버리는 경우가 발생할 수 있습니다.

따라서 x쪽에서 +1을 해서 처리하는 것이 아니라 y쪽에서 + 1을 처리하는 것이 안전합니다.



## 코드

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
   static int[] ax = {0, -1, 0, 1};
   static int[] ay = {-1, 0, 1, 0};

   static int INF = 400000;
   static int MIN = 0;

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;

      int N = Integer.parseInt(br.readLine());

      int[][] A = new int[N + 1][N + 1];
      for (int i = 1; i < N + 1; i++) {
         st = new StringTokenizer(br.readLine());
         for (int j = 1; j < N + 1; j++) {
            A[i][j] = Integer.parseInt(st.nextToken());
         }
      }

      System.out.println(Simulation(A, N));
   }

   // 시물레이션
   /*
    * 1. x, y, d1, d2 결정 (x가 행이고 y가 열)
    * 2. 경계선위의 좌표들 5로 채워주고 DFS를 이용해서 경계선 밖의 구역 채워주기
    * 3. 경계선 안의 구역 5로 채워주고 구역간의 인구수 차이 계산
    * 4. 반복
    */

   static int Simulation(int[][] A, int N) {
      int min_diff_population = INF;

      for (int d1 = 1; d1 <= N; d1++) {
         for (int d2 = 1; d2 <= N; d2++) {
            for (int x = 1; x + d1 + d2 <= N; x++) {
               for (int y = d1 + 1; y + d2 <= N; y++) {
                  // 구역 초기화
                  int[][] grid = new int[N + 1][N + 1];

                  // 경계선 채우기
                  for (int i = 0; i <= d1; i++) {
                     grid[x + i][y - i] = 5;
                     grid[x + d2 + i][y + d2 - i] = 5;
                  }
                  for (int i = 0; i <= d2; i++) {
                     grid[x + i][y + i] = 5;
                     grid[x + d1 + i][y - d1 + i] = 5;
                  }

                  // 경계선 밖 채워주기
                  for (int i = x - 1; i >= 0; i--) grid[i][y] = 1;
                  for (int i = y + d2 + 1; i <= N; i++) grid[x + d2][i] = 2;
                  for (int i = y - d1 - 1; i >= 0; i--) grid[x + d1][i] = 3;
                  for (int i = x + d1 + d2 + 1; i <= N; i++) grid[i][y - d1 + d2] = 4;

                  boolean[][] isVisited = new boolean[N + 1][N + 1];

                  // 1구역
                  dfs(grid, isVisited, N, x, y - 1, 1);
                  // 2구역
                  dfs(grid,isVisited, N, x, y + 1, 2);
                  // 3구역
                  dfs(grid, isVisited, N, x + d1 + d2, y - d1 + d2 - 1, 3);
                  // 4구역
                  dfs(grid, isVisited, N, x + d1 + d2, y - d1 + d2 + 1, 4);

                  // 경계 선안의 5구역 채워주기
                  for (int i = 1; i <= N; i++) {
                     for (int j = 1; j <= N; j++) {
                        if (grid[i][j] == 0) grid[i][j] = 5;
                     }
                  }

                  // 크기가 N + 1이 아니다. 무작정 크기를 설정하지 말자.
                  int[] areaSum = new int[6];

                  for (int i = 1; i <= N; i++) {
                     for (int j = 1; j <= N; j++) {
                        areaSum[grid[i][j]] += A[i][j];
                     }
                  }

                  int max = MIN;
                  int min = INF;

                  // 영역이 몇 개인지 주의!
                  for (int i = 1; i <= 5; i++) {
                     max = Math.max(areaSum[i], max);
                     min = Math.min(areaSum[i], min);
                  }

                  min_diff_population = Math.min(min_diff_population, Math.abs(max - min));
               }
            }
         }
      }
      return min_diff_population;
   }

   static void dfs(int[][] grid, boolean[][] isVisited, int N, int x, int y, int num) {

      grid[x][y] = num;
      isVisited[x][y] = true;

      for (int i = 0; i < 4; i++) {
         int nextX = x + ax[i];
         int nextY = y + ay[i];
         if (isValidIdx(nextX, nextY, N) && grid[nextX][nextY] == 0 && !isVisited[nextX][nextY]) {
            dfs(grid, isVisited, N, nextX, nextY, num);
         }
      }
   }

   static boolean isValidIdx(int x, int y, int N) {
      if (x < 1 || y < 1 || x > N || y > N) return false;
      return true;
   }
}
```

## 참고 

- [BOJ 17779. 게리맨더링 2](https://velog.io/@polynomeer/BOJ-17779.-%EA%B2%8C%EB%A6%AC%EB%A7%A8%EB%8D%94%EB%A7%81-2)
