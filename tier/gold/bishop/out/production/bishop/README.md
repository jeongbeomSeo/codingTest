# 비숍

**골드 1**

|시간 제한|	메모리 제한	|제출|	정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|10 초	|128 MB|	24691	|6038|	4185|	24.244%|

## 문제 

서양 장기인 체스에는 대각선 방향으로 움직일 수 있는 비숍(bishop)이 있다. < 그림 1 >과 같은 정사각형 체스판 위에 B라고 표시된 곳에 비숍이 있을 때 비숍은 대각선 방향으로 움직여 O로 표시된 칸에 있는 다른 말을 잡을 수 있다.

![](https://upload.acmicpc.net/c3f4ac55-3e37-4bed-a381-7d407b2f9b4f/-/preview/)

< 그림 1 >

그런데 체스판 위에는 비숍이 놓일 수 없는 곳이 있다. < 그림 2 >에서 체스판에 색칠된 부분은 비숍이 놓일 수 없다고 하자. 이와 같은 체스판에 서로가 서로를 잡을 수 없도록 하면서 비숍을 놓는다면 < 그림 3 >과 같이 최대 7개의 비숍을 놓을 수 있다. 색칠된 부분에는 비숍이 놓일 수 없지만 지나갈 수는 있다.

![](https://upload.acmicpc.net/3d44f5a2-bd28-41bd-9959-0f8f8bfbff3f/-/preview/)

< 그림 2 >

![](https://upload.acmicpc.net/49405f78-09c9-4220-8687-ec3269dd6c1b/-/preview/)

< 그림 3 >

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

**AC(최적화 덜 된 코드)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int MAX = Integer.MIN_VALUE;
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
    private static int simulation(int[][] grid, int N) {

        boolean[] flag = new boolean[2 * N - 1];
        MAX = Integer.MIN_VALUE;
        dfs(grid, flag, N, 0, 0);

        return MAX;
    }
    private static void dfs(int[][] grid, boolean[] flag, int N, int ptr, int count) {

        if (MAX == 2 * N - 1) return;

        if (2 * N - 1 == ptr) {
            MAX = Math.max(MAX, count);
        } else {

            int row = Math.min(ptr, N - 1);
            int col = ptr < N - 1 ? 0 : ptr - (N - 1);

            if (canLoadBishop(grid, flag, row, col, N)) {
                while (isValidPoint(row, col, N)) {
                    if (grid[row][col] == 1 && !flag[(N - 1) - row + col]) {
                        flag[(N - 1) - row + col] = true;
                        dfs(grid, flag, N, ptr + 1, count + 1);
                        flag[(N - 1) - row + col] = false;
                    }
                    row--;
                    col++;
                }
            } else {
                dfs(grid, flag, N, ptr + 1, count);
            }
        }
    }
    private static boolean canLoadBishop(int[][] grid, boolean[] flag, int row, int col, int N) {
        while (isValidPoint(row, col, N)) {
            if (grid[row][col] == 1 && !flag[(N - 1) - row + col]) {
                return true;
            }
            row--;
            col++;
        }
        return false;
    }
    private static boolean isValidPoint(int row, int col, int N) {
        return row >= 0 && col >= 0 && row < N && col < N;
    }
}
```

**AC(좀 더 효율적인 코드)**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int MAX = Integer.MIN_VALUE;
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

        MAX = Integer.MIN_VALUE;
        dfs(grid, flag, 0, N, 0);
        int eventResult = MAX;

        MAX = Integer.MIN_VALUE;
        dfs(grid, flag, 1, N, 0);
        int oddResult = MAX;

        return eventResult + oddResult;
    }
    private static void dfs(int[][] grid, boolean[] flag, int ptr, int size, int count) {
        if (ptr >= 2 * size - 1) {
            MAX = Math.max(MAX, count);
        } else {
            int row = ptr <= (size - 1) ? ptr : (size - 1);
            int col = ptr <= (size - 1) ? 0 : ptr - (size - 1);

            if (canLoadBishop(grid, flag, row, col, size)) {
                while (isValidPoint(row, col, size)) {
                    if (grid[row][col] == 1 && !flag[(size - 1) - row + col]) {
                        flag[(size - 1) - row + col] = true;
                        dfs(grid, flag, ptr + 2, size, count + 1);
                        flag[(size - 1) - row + col] = false;
                    }
                    row--;
                    col++;
                }
            } else {
                dfs(grid, flag, ptr + 2, size, count);
            }
        }
    }
    private static boolean canLoadBishop(int[][] grid, boolean[] flag, int row, int col, int size) {

        while (isValidPoint(row, col, size)) {
            if (grid[row][col] == 1 && !flag[(size - 1) - row + col]) return true;

            row--;
            col++;
        }

        return false;
    }
    private static boolean isValidPoint(int row, int col, int size) {
        return row >= 0 && col >= 0 && row < size && col < size;
    }
}
```