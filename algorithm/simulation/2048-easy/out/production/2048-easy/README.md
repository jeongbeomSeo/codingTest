# 2048(Easy)

**골드 2**

|시간 제한	|메모리 제한	|제출	|정답	|맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|1 초	|512 MB	|75769|	21872	|12718	|26.107%|

## 문제 

2048 게임은 4×4 크기의 보드에서 혼자 즐기는 재미있는 게임이다. 이 링크를 누르면 게임을 해볼 수 있다.

이 게임에서 한 번의 이동은 보드 위에 있는 전체 블록을 상하좌우 네 방향 중 하나로 이동시키는 것이다. 이때, 같은 값을 갖는 두 블록이 충돌하면 두 블록은 하나로 합쳐지게 된다. 한 번의 이동에서 이미 합쳐진 블록은 또 다른 블록과 다시 합쳐질 수 없다. (실제 게임에서는 이동을 한 번 할 때마다 블록이 추가되지만, 이 문제에서 블록이 추가되는 경우는 없다)

| <그림 1>                                                                              | <그림 2>                                                                              | <그림 3>                                                                             |
|------------------------------------------------------------------------------------|------------------------------------------------------------------------------------|------------------------------------------------------------------------------------|
| ![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/12094/1.png) | ![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/12094/2.png) | ![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/12094/2.png) |

<그림 1>의 경우에서 위로 블록을 이동시키면 <그림 2>의 상태가 된다. 여기서, 왼쪽으로 블록을 이동시키면 <그림 3>의 상태가 된다.

| <그림 4> | <그림 5> | <그림 6> | <그림 7> |
|---|----|---|---|
|![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/12094/4.png)|![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/12094/5.png)|![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/12094/6.png)|![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/12094/7.png)|

<그림 4>의 상태에서 블록을 오른쪽으로 이동시키면 <그림 5>가 되고, 여기서 다시 위로 블록을 이동시키면 <그림 6>이 된다. 여기서 오른쪽으로 블록을 이동시켜 <그림 7>을 만들 수 있다.

| <그림 8> | <그림 9> |
|---|---|
|![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/12094/8.png)|![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/12094/10.png)|

<그림 8>의 상태에서 왼쪽으로 블록을 옮기면 어떻게 될까? 2가 충돌하기 때문에, 4로 합쳐지게 되고 <그림 9>의 상태가 된다.

| <그림 10> | <그림 11> | <그림 12> | <그림 13> |
|---|---|---|---|
|![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/12094/17.png)|![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/12094/18.png)|![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/12094/19.png)|![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/12094/20.png)|

<그림 10>에서 위로 블록을 이동시키면 <그림 11>의 상태가 된다.

<그림 12>의 경우에 위로 블록을 이동시키면 <그림 13>의 상태가 되는데, 그 이유는 한 번의 이동에서 이미 합쳐진 블록은 또 합쳐질 수 없기 때문이다.

| <그림 14> | <그림 15> |
|---|---|
|![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/12094/21.png)|![](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/12094/22.png)|

마지막으로, 똑같은 수가 세 개가 있는 경우에는 이동하려고 하는 쪽의 칸이 먼저 합쳐진다. 예를 들어, 위로 이동시키는 경우에는 위쪽에 있는 블록이 먼저 합쳐지게 된다. <그림 14>의 경우에 위로 이동하면 <그림 15>를 만든다.

이 문제에서 다루는 2048 게임은 보드의 크기가 N×N 이다. 보드의 크기와 보드판의 블록 상태가 주어졌을 때, 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값을 구하는 프로그램을 작성하시오.

## 입력 

첫째 줄에 보드의 크기 N (1 ≤ N ≤ 20)이 주어진다. 둘째 줄부터 N개의 줄에는 게임판의 초기 상태가 주어진다. 0은 빈 칸을 나타내며, 이외의 값은 모두 블록을 나타낸다. 블록에 쓰여 있는 수는 2보다 크거나 같고, 1024보다 작거나 같은 2의 제곱꼴이다. 블록은 적어도 하나 주어진다.

## 출력 

최대 5번 이동시켜서 얻을 수 있는 가장 큰 블록을 출력한다.

## 예제 입력 1

```
3
2 2 2
4 4 4
8 8 8
```

## 예제 출력 1

```
16
```

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  // 북, 동, 남, 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N;
  static int maxValue = 0;
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

    simulation(grid);
    System.out.println(maxValue);
  }
  static void simulation(int[][] initGird) {
    Queue<Case> q = new LinkedList<>();
    q.add(new Case(initGird, 0));

    while (!q.isEmpty()) {
      Case curCase = q.poll();
      int[][] grid = curCase.grid;

      if (curCase.time == 5) {
        int maxNum = 0;
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++) {
            maxNum = Math.max(grid[i][j], maxNum);
          }
        }
        maxValue = Math.max(maxValue, maxNum);
        continue;
      }

      for (int i = 0; i < 4; i++) {
        // 북
        if (i == 0) {
          for (int col = 0; col < N; col++) {
            for (int row = 0; row < N; row++) {
              if (grid[row][col] != 0) {
                int ptr = 1;
                while (row + ptr < N && grid[row + ptr][col] == 0) ptr++;
                if (row + ptr < N && grid[row][col] == grid[row + ptr][col]) {
                  grid[row][col] *= 2;
                  grid[row + ptr][col] = 0;
                }
                ptr = 1;
                while (row - ptr >= 0 && grid[row - ptr][col] == 0) ptr++;
                if (ptr != 1) {
                  grid[row - (ptr - 1)][col] = grid[row][col];
                  grid[row][col] = 0;
                }
              }
            }
            /*
            for (int row = 0; row < N - 1; row++) {
              if (grid[row][col] == 0) {
                int notZeroRow = row + 1;
                while (notZeroRow < N && grid[notZeroRow][col] != 0) notZeroRow++;
                if (notZeroRow < N) {
                  grid[row][col] = grid[notZeroRow][col];
                  grid[notZeroRow][col] = 0;
                }
              }
            }
            */
          }
        }
        // 동
        else if (i == 1) {
          for (int row = 0; row < N; row++) {
            for (int col = N - 1; col >= 0; col--) {
              if (grid[row][col] != 0) {
                int ptr = 1;
                while (col - ptr >= 0 && grid[row][col - ptr] == 0) ptr++;
                if (col - ptr >= 0 && grid[row][col] == grid[row][col - ptr]) {
                  grid[row][col] *= 2;
                  grid[row][col - ptr] = 0;
                }
                ptr = 1;
                while (col + ptr < N && grid[row][col + ptr] == 0) ptr++;
                if (ptr != 1) {
                  grid[row][col + (ptr - 1)] = grid[row][col];
                  grid[row][col] = 0;
                }
              }
            }
          }
        }
        // 남
        else if (i == 2) {
          for (int col = 0; col < N; col++) {
            for (int row = N - 1; row >= 0; row--) {
              if (grid[row][col] != 0) {
                int ptr = 1;
                while (row - ptr >= 0 && grid[row - ptr][col] == 0) ptr++;
                if (row - ptr >= 0 && grid[row - ptr][col] == grid[row][col]) {
                  grid[row][col] *= 2;
                  grid[row - ptr][col] = 0;
                }
                ptr = 1;
                while (row + ptr < N && grid[row + ptr][col] == 0) ptr++;
                if (ptr != 1) {
                  grid[row + (ptr - 1)][col] = grid[row][col];
                  grid[row][col] = 0;
                }
              }
            }
          }
        }
        // 서
        else {
          for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
              if (grid[row][col] != 0) {
                int ptr = 1;
                while (col + ptr < N && grid[row][col + ptr] == 0) ptr++;
                if (col + ptr < N && grid[row][col] == grid[row][col + ptr]) {
                  grid[row][col] *= 2;
                  grid[row][col + ptr] = 0;
                }
                ptr = 1;
                while (col - ptr >= 0 && grid[row][col - ptr] == 0) ptr++;
                if (ptr != 1) {
                  grid[row][col - (ptr - 1)] = grid[row][col];
                  grid[row][col] = 0;
                }
              }
            }
          }
        }

        q.add(new Case(grid, curCase.time + 1));
      }
    }
  }
}
class Case {
  int[][] grid;
  int time;

  Case(int[][] grid, int time) {
    int size = grid.length;
    this.grid = new int[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        this.grid[i][j] = grid[i][j];
      }
    }
    this.time = time;
  }
}
```

**반례**

**입력**

```
7
2 2 2 2 2 2 2
2 0 2 2 2 2 2
2 0 2 2 2 2 2
2 0 2 2 2 2 2
2 2 2 0 2 2 2 
2 2 2 2 2 2 0
2 2 2 2 2 2 0
```

**정답**

```
32
```

**출력**

```
64
```

일단 위의 코드에서 고려하지 않은 것이 두가지다.

1. **시물레이션에서 for 문 + grid 배열을 같이 사용할 때 초기화가 재대로 이루어지는지 확인**
2. **만약 아무런 변화가 없을 때는 기존의 주어진 값중에 가장 높은 값 출력**

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  // 북, 동, 남, 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N;
  static int maxValue = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());
    int[][] grid = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
        maxValue = Math.max(maxValue, grid[i][j]);
      }
    }

    simulation(grid);
    System.out.println(maxValue);
  }
  static void simulation(int[][] initGird) {
    Queue<Case> q = new LinkedList<>();
    q.add(new Case(initGird, 0));

    while (!q.isEmpty()) {
      Case curCase = q.poll();

      if (curCase.time == 5) {
        int maxNum = 0;
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++) {
            maxNum = Math.max(curCase.grid[i][j], maxNum);
          }
        }
        maxValue = Math.max(maxValue, maxNum);
        continue;
      }

      boolean change = false;
      for (int i = 0; i < 4; i++) {
        int[][] grid = new int[N][N];

        for (int row = 0; row < N; row++) {
          for (int col = 0; col < N; col++) {
            grid[row][col] = curCase.grid[row][col];
          }
        }

        // 북
        if (i == 0) {
          for (int col = 0; col < N; col++) {
            for (int row = 0; row < N; row++) {
              if (grid[row][col] != 0) {
                int ptr = 1;
                while (row + ptr < N && grid[row + ptr][col] == 0) ptr++;
                if (row + ptr < N && grid[row][col] == grid[row + ptr][col]) {
                  grid[row][col] *= 2;
                  grid[row + ptr][col] = 0;
                  change = true;
                }
                ptr = 1;
                while (row - ptr >= 0 && grid[row - ptr][col] == 0) ptr++;
                if (ptr != 1) {
                  grid[row - (ptr - 1)][col] = grid[row][col];
                  grid[row][col] = 0;
                  change = true;
                }
              }
            }
          }
        }
        // 동
        else if (i == 1) {
          for (int row = 0; row < N; row++) {
            for (int col = N - 1; col >= 0; col--) {
              if (grid[row][col] != 0) {
                int ptr = 1;
                while (col - ptr >= 0 && grid[row][col - ptr] == 0) ptr++;
                if (col - ptr >= 0 && grid[row][col] == grid[row][col - ptr]) {
                  grid[row][col] *= 2;
                  grid[row][col - ptr] = 0;
                  change = true;
                }
                ptr = 1;
                while (col + ptr < N && grid[row][col + ptr] == 0) ptr++;
                if (ptr != 1) {
                  grid[row][col + (ptr - 1)] = grid[row][col];
                  grid[row][col] = 0;
                  change = true;
                }
              }
            }
          }
        }
        // 남
        else if (i == 2) {
          for (int col = 0; col < N; col++) {
            for (int row = N - 1; row >= 0; row--) {
              if (grid[row][col] != 0) {
                int ptr = 1;
                while (row - ptr >= 0 && grid[row - ptr][col] == 0) ptr++;
                if (row - ptr >= 0 && grid[row - ptr][col] == grid[row][col]) {
                  grid[row][col] *= 2;
                  grid[row - ptr][col] = 0;
                  change = true;
                }
                ptr = 1;
                while (row + ptr < N && grid[row + ptr][col] == 0) ptr++;
                if (ptr != 1) {
                  grid[row + (ptr - 1)][col] = grid[row][col];
                  grid[row][col] = 0;
                  change = true;
                }
              }
            }
          }
        }
        // 서
        else {
          for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
              if (grid[row][col] != 0) {
                int ptr = 1;
                while (col + ptr < N && grid[row][col + ptr] == 0) ptr++;
                if (col + ptr < N && grid[row][col] == grid[row][col + ptr]) {
                  grid[row][col] *= 2;
                  grid[row][col + ptr] = 0;
                  change = true;
                }
                ptr = 1;
                while (col - ptr >= 0 && grid[row][col - ptr] == 0) ptr++;
                if (ptr != 1) {
                  grid[row][col - (ptr - 1)] = grid[row][col];
                  grid[row][col] = 0;
                  change = true;
                }
              }
            }
          }
        }
        if (change) q.add(new Case(grid, curCase.time + 1));
      }
    }
  }
}
class Case {
  int[][] grid;
  int time;

  Case(int[][] grid, int time) {
    this.grid = grid;
    this.time = time;
  }
}
```

**좀 더 직관적인 코드**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  private static final int MINUS_INF = Integer.MIN_VALUE;
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

    System.out.println(simulation(grid, N));
  }
  private static int simulation(int[][] grid, int size) {

    Queue<Board> q = new ArrayDeque<>();
    q.add(new Board(grid, 0));

    int max = MINUS_INF;

    while (!q.isEmpty()) {
      Board board = q.poll();

      if (board.count == 5) {
        max = Math.max(max, getMaxValue(board.grid, size));
        continue;
      }

      for (int i = 0; i < 4; i++) {
        Board nextBoard = getNextBoard(board, i, size);

        if (!isEqualGrid(board.grid, nextBoard.grid, size)) {
          q.add(nextBoard);
        }
      }
    }

    return max != MINUS_INF ? max : getMaxValue(grid, size);
  }
  private static boolean isEqualGrid(int[][] grid, int[][] nextGird, int size) {

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (grid[i][j] != nextGird[i][j]) return false;
      }
    }

    return true;
  }
  private static Board getNextBoard(Board board, int direction, int size) {

    int[][] nextGrid = getNextGrid(board.grid, direction, size);

    return new Board(nextGrid, board.count + 1);
  }
  private static int[][] getNextGrid(int[][] grid, int direction, int size) {

    int[][] nextGrid = copyGrid(grid, size);

    switch (direction) {
      case 0:
        moveUp(nextGrid, size);
        break;
      case 1:
        moveRight(nextGrid, size);
        break;
      case 2:
        moveDown(nextGrid, size);
        break;
      case 3:
        moveLeft(nextGrid, size);
        break;
    }

    return nextGrid;
  }
  private static void moveUp(int[][] grid, int size) {

    boolean[][] isCrush = new boolean[size][size];

    for (int j = 0; j < size; j++) {
      for (int i = 1; i < size; i++) {
        if (grid[i][j] != 0) {
          int prevRow = i - 1;

          while (prevRow >= 0 && grid[prevRow][j] == 0) prevRow--;

          if (prevRow == -1) {
            grid[prevRow + 1][j] = grid[i][j];
            grid[i][j] = 0;
          } else if (grid[prevRow][j] == grid[i][j] && !isCrush[prevRow][j]) {
            grid[prevRow][j] *= 2;
            grid[i][j] = 0;
            isCrush[prevRow][j] = true;
          } else if (prevRow + 1 != i) {
            grid[prevRow + 1][j] = grid[i][j];
            grid[i][j] = 0;
          }
        }
      }
    }
  }
  private static void moveDown(int[][] grid, int size) {

    boolean[][] isCrush = new boolean[size][size];

    for (int j = 0; j < size; j++) {
      for (int i = size - 2; i >= 0; i--) {
        if (grid[i][j] != 0) {
          int prevRow = i + 1;
          while (prevRow <= size - 1 && grid[prevRow][j] == 0) prevRow++;

          if (prevRow == size) {
            grid[size - 1][j] = grid[i][j];
            grid[i][j] = 0;
          } else if (grid[prevRow][j] == grid[i][j] && !isCrush[prevRow][j]) {
            grid[prevRow][j] *= 2;
            grid[i][j] = 0;
            isCrush[prevRow][j] = true;
          } else if (prevRow - 1 != i) {
            grid[prevRow - 1][j] = grid[i][j];
            grid[i][j] = 0;
          }
        }
      }
    }
  }
  private static void moveRight(int[][] grid, int size) {

    boolean[][] isCrush = new boolean[size][size];

    for (int i = 0; i < size; i++) {
      for (int j = size - 2; j >= 0; j--) {
        int prevCol = j + 1;
        while (prevCol <= size - 1 && grid[i][prevCol] == 0) prevCol++;

        if (prevCol == size) {
          grid[i][prevCol - 1] = grid[i][j];
          grid[i][j] = 0;
        } else if (grid[i][prevCol] == grid[i][j] && !isCrush[i][prevCol]) {
          grid[i][prevCol] *= 2;
          grid[i][j] = 0;
          isCrush[i][prevCol] = true;
        } else if (prevCol - 1 != j) {
          grid[i][prevCol - 1] = grid[i][j];
          grid[i][j] = 0;
        }
      }
    }
  }
  private static void moveLeft(int[][] grid, int size) {

    boolean[][] isCrush = new boolean[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 1; j < size; j++) {
        int prevCol = j - 1;
        while (prevCol >= 0 && grid[i][prevCol] == 0) prevCol--;

        if (prevCol == -1) {
          grid[i][prevCol + 1] = grid[i][j];
          grid[i][j] = 0;
        } else if (grid[i][prevCol] == grid[i][j] && !isCrush[i][prevCol]) {
          grid[i][prevCol] *= 2;
          grid[i][j] = 0;
          isCrush[i][prevCol] = true;
        } else if (prevCol + 1 != j) {
          grid[i][prevCol + 1] = grid[i][j];
          grid[i][j] = 0;
        }
      }
    }
  }
  private static int getMaxValue(int[][] grid, int size) {
    int max = MINUS_INF;

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        max = Math.max(max, grid[i][j]);
      }
    }

    return max;
  }
  private static int[][] copyGrid(int[][] grid, int size) {

    int[][] copy = new int[size][size];
    for (int i = 0; i < size; i++) {
      copy[i] = Arrays.copyOf(grid[i], size);
    }

    return copy;
  }
}
class Board {
  int[][] grid;
  int count;

  Board(int[][] grid, int count) {
    this.grid = grid;
    this.count = count;
  }
}
```