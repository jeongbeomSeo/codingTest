# 원판 돌리기 

**골드 2**

|시간 제한	|메모리 제한|	제출|	정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|512 MB|	18514|	6858|	4336|	33.326%|

## 문제 

반지름이 1, 2, ..., N인 원판이 크기가 작아지는 순으로 바닥에 놓여있고, 원판의 중심은 모두 같다. 원판의 반지름이 i이면, 그 원판을 i번째 원판이라고 한다. 각각의 원판에는 M개의 정수가 적혀있고, i번째 원판에 적힌 j번째 수의 위치는 (i, j)로 표현한다. 수의 위치는 다음을 만족한다.

- (i, 1)은 (i, 2), (i, M)과 인접하다.
- (i, M)은 (i, M-1), (i, 1)과 인접하다.
- (i, j)는 (i, j-1), (i, j+1)과 인접하다. (2 ≤ j ≤ M-1)
- (1, j)는 (2, j)와 인접하다.
- (N, j)는 (N-1, j)와 인접하다.
- (i, j)는 (i-1, j), (i+1, j)와 인접하다. (2 ≤ i ≤ N-1)
 
아래 그림은 N = 3, M = 4인 경우이다.

![](https://upload.acmicpc.net/5968435b-a1af-4e2a-a612-baff989f44b2/-/preview/)

원판의 회전은 독립적으로 이루어진다. 2번 원판을 회전했을 때, 나머지 원판은 회전하지 않는다. 원판을 회전시킬 때는 수의 위치를 기준으로 하며, 회전시킨 후의 수의 위치는 회전시키기 전과 일치해야 한다.

다음 그림은 원판을 회전시킨 예시이다.

|![](https://upload.acmicpc.net/977a4e67-5aa7-40d4-92ee-5f59ac75aadb/-/preview/)|![](https://upload.acmicpc.net/f2c1e70b-0a84-46c3-b38d-f7395219b00a/-/preview/)|![](https://upload.acmicpc.net/39d57771-6162-49f5-97b7-0d9fd8911222/-/preview/)|
|---|---|---|
|1번 원판을 시계 방향으로 1칸 회전|2, 3번 원판을 반시계 방향으로 3칸 회전|1, 3번 원판을 시계 방향으로 2칸 회전|

원판을 아래와 같은 방법으로 총 T번 회전시키려고 한다. 원판의 회전 방법은 미리 정해져 있고, i번째 회전할때 사용하는 변수는 xi, di, ki이다.

1. 번호가 xi의 배수인 원판을 di방향으로 ki칸 회전시킨다. di가 0인 경우는 시계 방향, 1인 경우는 반시계 방향이다.
2. 원판에 수가 남아 있으면, 인접하면서 수가 같은 것을 모두 찾는다. 
   1. 그러한 수가 있는 경우에는 원판에서 인접하면서 같은 수를 모두 지운다.
   2. 없는 경우에는 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.

원판을 T번 회전시킨 후 원판에 적힌 수의 합을 구해보자.

## 입력 

첫째 줄에 N, M, T이 주어진다.

둘째 줄부터 N개의 줄에 원판에 적힌 수가 주어진다. i번째 줄의 j번째 수는 (i, j)에 적힌 수를 의미한다.

다음 T개의 줄에 x<sub>i</sub>, d<sub>i</sub>, k<sub>i</sub>가 주어진다.

## 출력 

원판을 T번 회전시킨 후 원판에 적힌 수의 합을 출력한다.

## 제한 

- 2 ≤ N, M ≤ 50
- 1 ≤ T ≤ 50
- 1 ≤ 원판에 적힌 수 ≤ 1,000
- 2 ≤ x<sub>i</sub> ≤ N
- 0 ≤ d<sub>i</sub> ≤ 1
- 1 ≤ k<sub>i</sub> < M

## 예제 입력 1

```
4 4 1
1 1 2 3
5 2 4 2
3 1 3 5
2 1 3 2
2 0 1
```

## 예제 출력 1

```
30
```

원판의 초기 상태는 다음과 같다.

![](https://upload.acmicpc.net/3306b622-c885-4b6e-abab-baa52eaf2d22/-/preview/)

|![](https://upload.acmicpc.net/6374fb88-a46d-40b7-b692-dbc9d2abe75f/-/preview/) |![](https://upload.acmicpc.net/196cd4ac-1c4e-4cd3-b714-0672e115aa69/-/preview/) |
|---|---|
|x1 = 2, d1 = 0, k1 = 1<br>2번, 4번 원판을 시계 방향으로 1칸 돌린 후| 인접하면서 수가 같은 것을 모두 지운 후 |

## 예제 입력 2

```
4 4 2
1 1 2 3
5 2 4 2
3 1 3 5
2 1 3 2
2 0 1
3 1 3
```

## 예제 출력 2

```
22
```

예제 1에서 이어진다.

|![](https://upload.acmicpc.net/8dbd0c76-cfac-4852-bbb1-77763051e26b/-/preview/)|![](https://upload.acmicpc.net/955577a2-d3ec-413d-8341-59dbf1bf23c3/-/preview/)|
|---|---|
|x2 = 3, d2 = 1, k2 = 3<br>3번 원판을 반시계 방향으로 3칸 돌린 후|인접하면서 수가 같은 것을 모두 지운 후|

## 코드

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M, T;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    T = Integer.parseInt(st.nextToken());

    int[][] disc = new int[N + 1][M + 1];
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= M; j++) {
        disc[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] rotateList = new int[T][3];
    for (int i = 0; i < T; i++) {
      st = new StringTokenizer(br.readLine());
      rotateList[i][0] = Integer.parseInt(st.nextToken());
      rotateList[i][1] = Integer.parseInt(st.nextToken());
      rotateList[i][2] = Integer.parseInt(st.nextToken());
    }

    System.out.println(simulation(disc, rotateList));
  }
  static int simulation(int[][] disc, int[][] rotateList){

    int num = 0;
    while (num < T) {

      for (int i = 1; i <= N; i++) {
        if (i % rotateList[num][0] == 0) rotate(disc[i], rotateList[num][1], rotateList[num][2]);
      }

      boolean[][] delete = new boolean[N + 1][M + 1];

      boolean notDelete = true;
      for (int i = 1; i <= N; i++) {
        for (int j = 1; j < M; j++) {
          if (disc[i][j] != 0 && disc[i][j] == disc[i][j + 1]) {
            delete[i][j] = delete[i][j + 1] = true;
            notDelete = false;
          }
        }
        if (disc[i][1] != 0 && disc[i][1] == disc[i][M]) {
          delete[i][1] = delete[i][M] = true;
          notDelete = false;
        }
      }
      for (int j = 1; j <= M; j++) {
        for (int i = 1; i < N; i++) {
          if (disc[i][j] != 0 && disc[i][j] == disc[i + 1][j]) {
            delete[i][j] = delete[i + 1][j] = true;
            notDelete = false;
          }
        }
      }

      if (!notDelete) {
        for (int i = 1; i <= N; i++) {
          for (int j = 1; j <= M; j++) {
            if (delete[i][j]) disc[i][j] = 0;
          }
        }
      }
      else {
        int totalNumberSum = 0;
        int notZero = 0;
        for (int i = 1; i <= N; i++) {
          for (int j = 1; j <= M; j++) {
            if (disc[i][j] != 0) {
              notZero++;
              totalNumberSum += disc[i][j];
            }
          }
        }
        double average = (double)totalNumberSum / notZero;
        for (int i = 1; i <= N; i++) {
          for (int j = 1; j <= M; j++) {
            if (disc[i][j] != 0) {
              if (disc[i][j] > average) disc[i][j]--;
              else if (disc[i][j] < average) disc[i][j]++;
            }
          }
        }
      }
      num++;
    }

    int sum = 0;
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= M; j++) {
        sum += disc[i][j];
      }
    }
    return sum;
  }
  static void rotate(int[] disc, int direction, int count) {

    while (count-- > 0) {
      int temp;
      switch (direction) {
        case 0:
          temp = disc[M];
          for (int i = M; i > 1; i--) disc[i] = disc[i - 1];
          disc[1] = temp;
          break;
        case 1:
          temp = disc[1];
          for (int i = 1; i < M; i++) disc[i] = disc[i + 1];
          disc[M] = temp;
          break;
        default:
          break;
      }
    }
  }
}
```