# 뱀 

**골드 4**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초|	128 MB	|61734	|25721	|17198|	39.960%|

## 문제 

'Dummy' 라는 도스게임이 있다. 이 게임에는 뱀이 나와서 기어다니는데, 사과를 먹으면 뱀 길이가 늘어난다. 뱀이 이리저리 기어다니다가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.

게임은 NxN 정사각 보드위에서 진행되고, 몇몇 칸에는 사과가 놓여져 있다. 보드의 상하좌우 끝에 벽이 있다. 게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1 이다. 뱀은 처음에 오른쪽을 향한다.

뱀은 매 초마다 이동을 하는데 다음과 같은 규칙을 따른다.

- 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
- 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
- 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.

사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산하라.

## 입력 

첫째 줄에 보드의 크기 N이 주어진다. (2 ≤ N ≤ 100) 다음 줄에 사과의 개수 K가 주어진다. (0 ≤ K ≤ 100)

다음 K개의 줄에는 사과의 위치가 주어지는데, 첫 번째 정수는 행, 두 번째 정수는 열 위치를 의미한다. 사과의 위치는 모두 다르며, 맨 위 맨 좌측 (1행 1열) 에는 사과가 없다.

다음 줄에는 뱀의 방향 변환 횟수 L 이 주어진다. (1 ≤ L ≤ 100)

다음 L개의 줄에는 뱀의 방향 변환 정보가 주어지는데,  정수 X와 문자 C로 이루어져 있으며. 게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻이다. X는 10,000 이하의 양의 정수이며, 방향 전환 정보는 X가 증가하는 순으로 주어진다.

## 출력 

첫째 줄에 게임이 몇 초에 끝나는지 출력한다.

## 예제 입력 1

```
6
3
3 4
2 5
5 3
3
3 D
15 L
17 D
```

## 예제 출력 1

```
9
```

## 예제 입력 2

```
10
4
1 2
1 3
1 4
1 5
4
8 D
10 D
11 D
13 L
```

## 예제 출력 2

```
21
```

## 예제 입력 3

```
10
5
1 5
1 3
1 2
1 6
1 7
4
8 D
10 D
11 D
13 L
```

## 예제 출력 3

```
13
```

## 문제 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
  // 동, 남, 서, 북;
  static int[] dr = {0, 1, 0, -1};
  static int[] dc = {1, 0, -1, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int K = Integer.parseInt(br.readLine());

    // [K 개의 사과][row, col]
    int[][] apple = new int[K][2];

    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      apple[i][0] = Integer.parseInt(st.nextToken());
      apple[i][1] = Integer.parseInt(st.nextToken());
    }

    int L = Integer.parseInt(br.readLine());

    String[] changeDirection = new String[100];

    for (int i = 0; i < L; i++) {
      st = new StringTokenizer(br.readLine());
      int idx = Integer.parseInt(st.nextToken());
      String d = st.nextToken();
      changeDirection[idx] = d;
    }

    int result = simulation(apple, changeDirection, N, K);

    System.out.println(result);

  }
  static int simulation(int[][] apple, String[] changeDirection, int N, int K) {

    // {row, col}
    Deque<Integer[]> deque = new ArrayDeque<>();
    boolean[][] flag = new boolean[N][N];

    deque.add(new Integer[]{0, 0});
    flag[0][0] = true;

    int size = 1;
    int curDirection = 0;
    for (int time = 1; time < N * N; time++) {
      int nextRow = -1;
      int nextCol = -1;
      boolean head = true;

      for (int i = 0; i < size; i++) {
        Integer[] curState = deque.getFirst();
        int curRow = curState[0];
        int curCol = curState[1];

        // 움직일 것이니깐 해당 부분 마킹 지워놓기
        flag[curRow][curCol] = false;

        // 머리일 경우 해당 과정 수행
        if (head) {
          nextRow = curRow;
          nextCol = curCol;

          curRow = curRow + dr[curDirection];
          curCol = curCol + dc[curDirection];

          // 움직인 부분에 벽이나 몸통이 있는 경우 종료
          if (flag[curRow][curCol] && !validIdx(curRow, curCol, N)) return time;

          // 사과 있는 위치로 이동한 것인지 확인
          for (int appIdx = 0; appIdx < K; appIdx++) {
            // 먹지 않은 것 중에 확인
            if (apple[appIdx][0] != -1) {
              if (nextRow == apple[appIdx][0] && nextCol == apple[appIdx][1]) {
                apple[appIdx][0] = -1;
                size++;
              }
            }
          }

          // 처리 끝난 후 머리일 경우 방향 전환
          if (changeDirection[time] != "") {
            if (changeDirection[time] == "L") {
              curDirection = curDirection != 0 ? curDirection - 1 : 3;
            }
            else
              curDirection = curDirection != 3 ? curDirection + 1 : 0;
          }
          // 모든 처리 끝난 후 해당 부분 위치 마킹
          deque.addLast(new Integer[]{curRow, curCol});
          flag[curRow][curCol] = true;
          head = false;
        }
        // 머리가 아닌 경우
        else {
          deque.addLast(new Integer[]{nextRow, nextCol});
          flag[nextRow][nextCol] = true;
          nextRow = curRow;
          nextCol = curCol;
        }

      }
    }
    return N * N;
  }
  static boolean validIdx(int row, int col, int N) {
    if (row < 0 || col < 0 || row > N - 1 || col > N - 1) return false;
    return true;
  }
}
```

이와 같이 로직을 구현했을 경우 생기는 문제가 보인다.

1. 사과를 먹고나서 처리하는 부분이 허술하다.
   1. 사과를 먹고나서 size++가 되는데 이것은 쓸데 없이 반복문을 한 번 더 돌게한다.
   2. 그 과정에서 생기는 Deque의 poll과정이 문제가 발생한다.
2. 사과를 먹고나서 처리하는 부분도 별로다. 