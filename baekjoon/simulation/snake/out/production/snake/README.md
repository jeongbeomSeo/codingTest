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

## 해당 문제에서 주의할 점

- **덱**을 사용해서 구현을 할 경우 이전의 값들을 넘겨주는 시점에서 현재 값들을 저장하는 과정(temp)를 생략하면 안됩니다.
- 시뮬레이션 문제에서는 공통적으로 보통 time 혹은 turn을 쓰기 때문에 이때 고정적으로 이 변수가 바뀌기 전에 고정적인 값들과 바뀌는 값들을 확실히 구분해야 한다.
- 문자열 비교에서는 ```==```가 아닌 ```equals()```를 사용해야 합니다. 
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

## 코드 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
  // 북, 동, 남, 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int K = Integer.parseInt(br.readLine());

    int[][] apple = new int[K][2];
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      apple[i] = new int[]{r, c};
    }

    int L = Integer.parseInt(br.readLine());

    String[] rotate = new String[101];
    for (int i = 0; i < L; i++) {
      st = new StringTokenizer(br.readLine());
      int idx  = Integer.parseInt(st.nextToken());
      String d = st.nextToken();
      rotate[idx] = d;
    }

    System.out.println(simulation(apple, rotate, N, K));
  }
  static int simulation(int[][] apple, String[] rotate, int N, int K) {

    boolean[][] flag = new boolean[N][N];
    Deque<Node> deque = new ArrayDeque<>();
    deque.add(new Node(0, 0));
    flag[0][0] = true;
    int direction = 1;

    int t;
    for (t = 1; t < N * N; t++) {
      int size = deque.size();
      boolean head = true;
      int nextIdxRow = deque.peekFirst().row;
      int nextIdxCol = deque.peekFirst().col;

      for (int i = 0; i < size; i++) {
        if (head) {
          Node curNode = deque.pollFirst();
          flag[curNode.row][curNode.col] = false;

          curNode.row += dr[direction];
          curNode.col += dc[direction];

          if (!isValidIdx(curNode.row, curNode.col, N) || flag[curNode.row][curNode.col]) return t;

          for (int appleIdx = 0; appleIdx < K; appleIdx++) {
            if (apple[appleIdx][0] != -1) {
              if (apple[appleIdx][0] == curNode.row && apple[appleIdx][1] == curNode.col) {
                Node addLastNode = deque.peekLast();
                if (size == 1) deque.addLast(new Node(nextIdxRow, nextIdxCol));
                else deque.addLast(new Node(addLastNode.row, addLastNode.col));
                size++;
                apple[appleIdx][0] = -1;
                break;
              }
            }
          }

          if (rotate[t] != null) {
            if (rotate[t] == "L") {
              if (direction != 0) direction--;
              else direction = 3;
            }
            else
              if (direction != 3) direction++;
              else direction = 0;
          }

          deque.addLast(new Node(curNode.row, curNode.col));
          flag[curNode.row][curNode.col] = true;
          head = false;
        }
        else {
          Node curNode = deque.pollFirst();
          flag[curNode.row][curNode.col] = false;
          deque.addLast(new Node(nextIdxRow, nextIdxCol));
          flag[nextIdxRow][nextIdxCol] = true;
          nextIdxRow = curNode.row;
          nextIdxCol = curNode.col;

        }
      }
    }
    return t;
  }
  static boolean isValidIdx(int row, int col, int N) {
    if (row < 0 || col < 0 || row > N - 1 || col > N - 1) return false;
    return true;
  }
}

class Node {
  int row;
  int col;

  Node(int row, int col) {
    this.row = row;
    this.col = col;
  }
}
```

해당 코드에서 예제 입력 2의 경우 16이 출력되고, 예제 입력 3의 경우 16이 출력됩니다.

일단 해당 코드에서 문제는 본인의 경우 처음 맨 왼쪽 맨 위 Idx를 (0,0)으로 잡고 갔는데, 문제에선 (1,1)로 잡아 놨다.

이부분을 수정하니깐 예제 입력 3의 경우에는 올바르게 출력되었습니다.

하지만, 예제 입력 2의 경우 16이 나왔습니다.

문자열 비교에서 == 쓰지말고 equals()를 사용하자.

## 코드 

**EC(ArrayIndexOutOfBounds)** 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
  // 북, 동, 남, 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int K = Integer.parseInt(br.readLine());

    int[][] apple = new int[K][2];
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken()) - 1;
      apple[i] = new int[]{r, c};
    }

    int L = Integer.parseInt(br.readLine());

    String[] rotate = new String[101];
    for (int i = 0; i < L; i++) {
      st = new StringTokenizer(br.readLine());
      int idx  = Integer.parseInt(st.nextToken());
      String d = st.nextToken();
      rotate[idx] = d;
    }

    System.out.println(simulation(apple, rotate, N, K));
  }
  static int simulation(int[][] apple, String[] rotate, int N, int K) {

    boolean[][] flag = new boolean[N][N];
    Deque<Node> deque = new ArrayDeque<>();
    deque.add(new Node(0, 0));
    flag[0][0] = true;
    int direction = 1;

    int t;
    for (t = 1; t < N * N; t++) {
      int size = deque.size();
      boolean head = true;
      int nextIdxRow = deque.peekFirst().row;
      int nextIdxCol = deque.peekFirst().col;

      for (int i = 0; i < size; i++) {
        if (head) {
          Node curNode = deque.pollFirst();
          flag[curNode.row][curNode.col] = false;

          curNode.row += dr[direction];
          curNode.col += dc[direction];

          if (!isValidIdx(curNode.row, curNode.col, N) || flag[curNode.row][curNode.col]) return t;

          for (int appleIdx = 0; appleIdx < K; appleIdx++) {
            if (apple[appleIdx][0] != -1) {
              if (apple[appleIdx][0] == curNode.row && apple[appleIdx][1] == curNode.col) {
                Node addLastNode = deque.peekLast();
                if (size == 1) deque.addLast(new Node(nextIdxRow, nextIdxCol));
                else deque.addLast(new Node(addLastNode.row, addLastNode.col));
                size++;
                apple[appleIdx][0] = -1;
                break;
              }
            }
          }

          if (rotate[t] != null) {
            if (rotate[t].equals("L")) {
              if (direction != 0) direction--;
              else direction = 3;
            }
            else
              if (direction != 3) direction++;
              else direction = 0;
          }

          deque.addLast(new Node(curNode.row, curNode.col));
          flag[curNode.row][curNode.col] = true;
          head = false;
        }
        else {
          Node curNode = deque.pollFirst();
          flag[curNode.row][curNode.col] = false;
          deque.addLast(new Node(nextIdxRow, nextIdxCol));
          flag[nextIdxRow][nextIdxCol] = true;
          nextIdxRow = curNode.row;
          nextIdxCol = curNode.col;

        }
      }
    }
    return t;
  }
  static boolean isValidIdx(int row, int col, int N) {
    if (row < 0 || col < 0 || row > N - 1 || col > N - 1) return false;
    return true;
  }
}

class Node {
  int row;
  int col;

  Node(int row, int col) {
    this.row = row;
    this.col = col;
  }
}
```

> **시물레이션에서 time 혹은 turn을 배열의 크기로 설정할 때 주의 사항**
> 
> 해당 변수들을 크기로 설정할 것이면 처음에 크기를 잡을 때 신중하게 적어줘야 한다.
> 
> 예를 들어, 방향을 전환하는 횟수가 L번인데, 이것이 0 ~ N * N Time동안 이루어 진다고 해보자.
>
> 내가 L을 사용해서 매번 시뮬레이션에서 확인할 것인가, 아니면 N * N의 크기로 설정을 해서 바로 접근할 것인가를 신중하게 선택하고 선택을 하고 나서부터는 내가 무엇을 배열의 크기로 설정했는지 주의해야 한다.

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
  // 북, 동, 남, 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int K = Integer.parseInt(br.readLine());

    int[][] apple = new int[K][2];
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken()) - 1;
      apple[i] = new int[]{r, c};
    }

    int L = Integer.parseInt(br.readLine());

    String[] rotate = new String[100001];
    for (int i = 0; i < L; i++) {
      st = new StringTokenizer(br.readLine());
      int idx  = Integer.parseInt(st.nextToken());
      String d = st.nextToken();
      rotate[idx] = d;
    }

    System.out.println(simulation(apple, rotate, N, K));
  }
  static int simulation(int[][] apple, String[] rotate, int N, int K) {

    boolean[][] flag = new boolean[N][N];
    Deque<Node> deque = new ArrayDeque<>();
    deque.add(new Node(0, 0));
    flag[0][0] = true;
    int direction = 1;

    int t;
    for (t = 1; t < N * N; t++) {
      int size = deque.size();
      boolean head = true;
      int nextIdxRow = deque.peekFirst().row;
      int nextIdxCol = deque.peekFirst().col;

      for (int i = 0; i < size; i++) {
        if (head) {
          Node curNode = deque.pollFirst();
          flag[curNode.row][curNode.col] = false;

          curNode.row += dr[direction];
          curNode.col += dc[direction];

          if (!isValidIdx(curNode.row, curNode.col, N) || flag[curNode.row][curNode.col]) return t;

          for (int appleIdx = 0; appleIdx < K; appleIdx++) {
            if (apple[appleIdx][0] != -1) {
              if (apple[appleIdx][0] == curNode.row && apple[appleIdx][1] == curNode.col) {
                Node addLastNode = deque.peekLast();
                if (size == 1) deque.addLast(new Node(nextIdxRow, nextIdxCol));
                else deque.addLast(new Node(addLastNode.row, addLastNode.col));
                size++;
                apple[appleIdx][0] = -1;
                break;
              }
            }
          }

          if (rotate[t] != null) {
            if (rotate[t].equals("L")) {
              if (direction != 0) direction--;
              else direction = 3;
            }
            else
              if (direction != 3) direction++;
              else direction = 0;
          }

          deque.addLast(new Node(curNode.row, curNode.col));
          flag[curNode.row][curNode.col] = true;
          head = false;
        }
        else {
          Node curNode = deque.pollFirst();
          flag[curNode.row][curNode.col] = false;
          deque.addLast(new Node(nextIdxRow, nextIdxCol));
          flag[nextIdxRow][nextIdxCol] = true;
          nextIdxRow = curNode.row;
          nextIdxCol = curNode.col;

        }
      }
    }
    return t;
  }
  static boolean isValidIdx(int row, int col, int N) {
    if (row < 0 || col < 0 || row > N - 1 || col > N - 1) return false;
    return true;
  }
}

class Node {
  int row;
  int col;

  Node(int row, int col) {
    this.row = row;
    this.col = col;
  }
}
```

다른 풀이 틀림

해당 풀이에서는 로직이 방향 전환이 사과를 먹는 유무와 상관없이 이루어져야 하는데 사과를 먹는 경우 턴 자체를 넘겨 버리므로 문제가 발생합니다.

**WA**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
  // 북, 동, 남, 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int K = Integer.parseInt(br.readLine());

    int[][] apple = new int[K][2];
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      apple[i][0] = Integer.parseInt(st.nextToken()) - 1;
      apple[i][1] = Integer.parseInt(st.nextToken()) - 1;
    }

    int L = Integer.parseInt(br.readLine());

    String[] rotate = new String[N * N + 1];

    for (int i = 0; i < L; i++) {
      st = new StringTokenizer(br.readLine());
      int idx = Integer.parseInt(st.nextToken());
      String val = st.nextToken();
      rotate[idx] = val;
    }

    System.out.println(simulation(N, apple, K, rotate));
  }
  static int simulation(int N, int[][] apple,  int K, String[] rotate) {

    int time;
    Deque<Node> deque = new ArrayDeque<>();
    boolean[][] isSnake = new boolean[N][N];
    deque.add(new Node(0, 0, true));
    isSnake[0][0] = true;
    int direction = 1;

    for (time = 1; time < N * N; time++) {
      boolean eatApple = false;

      int size = deque.size();
      boolean success = true;

      int saveRow = -1;
      int saveCol = -1;
      while (size-- > 0) {
        Node curNode = deque.pollFirst();
        isSnake[curNode.row][curNode.col] = false;
        // 머리인 경우
        if (curNode.head) {
          saveRow = curNode.row;
          saveCol = curNode.col;
          // 헤드 부분 현재 방향 앞으로 이동
          curNode.row += dr[direction];
          curNode.col += dc[direction];

          // 이동 후 제약 조건 두 개 확인
          if (!isValidIdx(curNode.row, curNode.col, N) || isSnake[curNode.row][curNode.col] ) {
            success = false;
            break;
          }

          // 사과 위치 확인
          for (int i = 0; i < K; i++) {
            int appleRow = apple[i][0];
            int appleCol = apple[i][1];

            // 사과를 먹은 경우 뒷부분은 바뀌지 않는다고 생각하고
            // 이전 머리 부분에 위치하고 있던 자리에 새로 노드 하나 만들어 주고, 기존 헤드는 위치를 옮겼으므로 그대로 삽입
            // 사과를 먹은 경우 해당 과정 처리후 다음 시간을 바로 이동
            if (curNode.row == appleRow && curNode.col == appleCol) {
              deque.addFirst(new Node(saveRow, saveCol, false));
              deque.addFirst(curNode);
              isSnake[saveRow][saveCol] = true;
              isSnake[curNode.row][curNode.col] = true;
              apple[i][0] = -1;
              eatApple = true;
              break;
            }
          }

          if (eatApple) break;

          // 일반적인 경우 덱의 맨 뒤에 넣어주기
          deque.addLast(curNode);
          isSnake[curNode.row][curNode.col] = true;

          // 방향 전환 유뮤 확인
          if (rotate[time] != null) {
            if (rotate[time].equals("L"))
              direction = (direction + 3) % 4;
            else
              direction = (direction + 1) % 4;
          }
        }
        // 머리가 아닌 경우
        else {
          // saveRow에는 당연히 현재 위치를 넣어줘야 하는 부분
          int tempRow = curNode.row;
          int tempCol = curNode.col;
          // 이전 위치 받아서 노드에 넣어주기
          curNode.row = saveRow;
          curNode.col = saveCol;
          saveRow = tempRow;
          saveCol = tempCol;
          deque.addLast(curNode);
          isSnake[curNode.row][curNode.col] = true;
        }

        if (eatApple) break;
      }
      if (!success) break;
    }
    return time;
  }
  static boolean isValidIdx(int row, int col, int N) {
    if (row < 0 || col < 0 || row > N - 1 || col > N - 1) return false;
    return true;
  }
}
class Node {
  int row;
  int col;
  boolean head;

  Node(int row, int col, boolean head) {
    this.row = row;
    this.col = col;
    this.head = head;
  }

}
```