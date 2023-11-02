# 비숍

**골드 1**

|시간 제한	|메모리 제한|	제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|10 초	|128 MB	|21298	|5226	|3610|	23.979%|

## 문제 

서양 장기인 체스에는 대각선 방향으로 움직일 수 있는 비숍(bishop)이 있다. < 그림 1 >과 같은 정사각형 체스판 위에 B라고 표시된 곳에 비숍이 있을 때 비숍은 대각선 방향으로 움직여 O로 표시된 칸에 있는 다른 말을 잡을 수 있다.

![](https://upload.acmicpc.net/c3f4ac55-3e37-4bed-a381-7d407b2f9b4f/-/preview/)

<그림 1>

그런데 체스판 위에는 비숍이 놓일 수 없는 곳이 있다. < 그림 2 >에서 체스판에 색칠된 부분은 비숍이 놓일 수 없다고 하자. 이와 같은 체스판에 서로가 서로를 잡을 수 없도록 하면서 비숍을 놓는다면 < 그림 3 >과 같이 최대 7개의 비숍을 놓을 수 있다.  색칠된 부분에는 비숍이 놓일 수 없지만 지나갈 수는 있다.

![](https://upload.acmicpc.net/3d44f5a2-bd28-41bd-9959-0f8f8bfbff3f/-/preview/)

<그림 2>

![](https://upload.acmicpc.net/49405f78-09c9-4220-8687-ec3269dd6c1b/-/preview/)

<그림 3>

정사각형 체스판의 한 변에 놓인 칸의 개수를 체스판의 크기라고 한다. 체스판의 크기와 체스판 각 칸에 비숍을 놓을 수 있는지 없는지에 대한 정보가 주어질 때, 서로가 서로를 잡을 수 없는 위치에 놓을 수 있는 비숍의 최대 개수를 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 체스판의 크기가 주어진다. 체스판의 크기는 10이하의 자연수이다. 둘째 줄부터 아래의 예와 같이 체스판의 각 칸에 비숍을 놓을 수 있는지 없는지에 대한 정보가 체스판 한 줄 단위로 한 줄씩 주어진다. 비숍을 놓을 수 있는 곳에는 1, 비숍을 놓을 수 없는 곳에는 0이 빈칸을 사이에 두고 주어진다.

## 출력 

첫째 줄에 주어진 체스판 위에 놓을 수 있는 비숍의 최대 개수를 출력한다.

## 예제 입력 1

```
5
1 1 0 1 1
0 1 0 0 0
1 0 1 0 1
1 0 0 0 0
1 0 1 1 1
```

## 예제 출력 1

```
7
```

## 코드 

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int maxBishop = Integer.MIN_VALUE;

  static boolean[] flag_BR;
  static int[][] chess;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    chess = new int[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        chess[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    flag_BR = new boolean[2 * N - 1];

    bishop(N, 0, 0, 0);

    System.out.println(maxBishop);

  }
  // 기울기 + 선분(line)을 기준으로 백트래킹
  // 선분 기준으로 맨 오른쪽 위의 값부터 대각선을 내려가면서 놓을 수 있는 부분 탐색
  static void bishop(int N, int line, int ptr, int count) {

    if (line == 2 * N - 1) {
      maxBishop = Math.max(maxBishop, count);
      return;
    }

    // line 의 값이 N을 넘어가면 i값이 늘어나기 시작하고, 열값은 N - 1부터 시작 고정
    int i = (line >= N) ? (line % (N - 1) != 0 ? line % (N - 1) : N - 1) : 0;
    boolean isValid = false;
    int nextPtr = (line >= N - 1) ? N - 1: line + 1;

    // 놓을 수 있는 곳이 없다면 그냥 넘어가기
    for (; i >= 0 && ptr >= 0 && i < N && ptr < N; ptr--, i++) {
      isValid = false;
      // 놓을 수 있는 자리와 기울기 -인 대각선에 놓여있는 비숍 체크
      if (chess[i][ptr] == 1 && !flag_BR[N - 1 - i + ptr]) {
        flag_BR[N - 1 - i + ptr] = true;
        bishop(N, line + 1, nextPtr, count + 1);
        flag_BR[N - 1 - i + ptr] = false;
        isValid = true;
      }
    }
    if (!isValid) bishop(N, line + 1, nextPtr, count);
  }
}
```

**주의할 점**

여기서 일반적인 방식으로 boolean 배열의 flag를 2개로 만들어서 기울기가 +, -인 경우로 만들어 두고 비숍을 놓을 수 있는 위치(1)를 전부 체크해 가면서 하는 풀이는 시간 초과가 나옵니다.

해당 풀이는 아래와 같습니다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    List<Node> nodeList = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        if (Integer.parseInt(st.nextToken()) == 1) {
          nodeList.add(new Node(i, j));
        }
      }
    }

    System.out.println(queryResult(nodeList, N));
  }
  private static int queryResult(List<Node> nodeList, int N) {

    boolean[] flag_XR = new boolean[2 * N - 1];
    boolean[] flag_XL = new boolean[2 * N - 1];

    return recursive(nodeList, flag_XR, flag_XL, 0, 0, nodeList.size(), N);
  }
  private static int recursive(List<Node> nodeList, boolean[] flag_XR, boolean[] flag_XL, int ptr, int count, int size, int N) {
    int max = Integer.MIN_VALUE;
    if (ptr == size) {
      max = count;
      return max;
    } else {
      Node node = nodeList.get(ptr);

      if (!flag_XR[node.row + node.col] && !flag_XL[(N - 1) - node.row + node.col]) {
        flag_XR[node.row + node.col] =
                flag_XL[(N - 1) - node.row + node.col] = true;
        max = Math.max(max, recursive(nodeList, flag_XR, flag_XL, ptr + 1, count + 1, size, N));
        flag_XR[node.row + node.col] =
                flag_XL[(N - 1) - node.row + node.col] = false;
      }

      if (max == Integer.MIN_VALUE || (max < count + (size - (ptr + 1)))) {
        max = Math.max(max, recursive(nodeList, flag_XR, flag_XL, ptr + 1, count, size, N));
      }
    }

    return max;
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

다음은 좀 더 최적화 된 코드입니다.

**최적화 코드**
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[][] grid = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(queryResult(grid, N));
  }
  private static int queryResult(int[][] grid, int N) {
    boolean[] flag = new boolean[2 * N - 1];

    return backTracking(grid, flag, 0, 0, N);
  }
  private static int backTracking(int[][] grid, boolean[] flag, int ptr, int count, int N) {
    int max = Integer.MIN_VALUE;

    if (ptr == 2 * N - 1) {
      return count;
    } else {
      int row = ptr < N ? ptr : N - 1;
      int col = ptr < N ? 0 : ptr - (N - 1);

      boolean active = false;
      while (row >= 0 && col < N) {
        if (grid[row][col] == 1) {
          if (!flag[(N - 1) - row + col]) {
            active = true;

            flag[(N - 1) - row + col] = true;
            max = Math.max(max, backTracking(grid, flag, ptr + 1, count + 1, N));
            flag[(N - 1) - row + col] = false;
          }
        }
        row--;
        col++;
      }
      if (!active) max = Math.max(max, backTracking(grid, flag, ptr + 1, count, N));
    }
    return max;
  }
}
```

이 문제는 사실 이와 같은 방식으로 푸는 문제는 아닌 것 같습니다. 왜 이 방식의 풀이가 통과되는 것인지 모르겠습디만

정석적이라면 놓을 수 있는 경우에도 일부러 놓지 않는 경우도 고려해야 됩니다. 즉, active가 true일지라도 실행해야 되는 상황이 발생하는 것입니다. 

다음 풀이가 정석적인 풀이이고 참고 사이트 링크는 아래에 달아놨습니다.

**가장 최적화된 풀이**
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(queryResult(grid, N));
    }
    private static int queryResult(int[][] grid, int N) {
        boolean[] flag = new boolean[2 * N - 1];

        int evenResult = backTracking(grid, flag, 0, 0, N);
        int oddResult = backTracking(grid, flag, 1, 0, N);

        return evenResult + oddResult;
    }
    private static int backTracking(int[][] grid, boolean[] flag, int ptr, int count, int size) {
        int max = Integer.MIN_VALUE;

        if (ptr >= 2 * size - 1) {
            return count;
        } else {
            int row = ptr < size ? ptr : size - 1;
            int col = ptr < size ? 0 : ptr - (size - 1);

            while (row >= 0 && col < size) {
                if (grid[row][col] == 1 && !flag[(size - 1) - row + col]) {
                    flag[(size - 1) - row + col] = true;
                    max = Math.max(max, backTracking(grid, flag, ptr + 2, count + 1, size));
                    flag[(size - 1) - row + col] = false;
                }
                row--;
                col++;
            }
            max = Math.max(max, backTracking(grid, flag, ptr + 2, count, size));
        }

        return max;
    }
}
```

**참고한 사이트**
- [[백준] 1799번 비숍](https://velog.io/@ddongh1122/%EB%B0%B1%EC%A4%80-1799%EB%B2%88-%EB%B9%84%EC%88%8D)