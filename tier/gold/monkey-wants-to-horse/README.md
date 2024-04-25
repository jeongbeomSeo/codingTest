# 말이 되고픈 원숭이

**골드 3**

## 문제 

동물원에서 막 탈출한 원숭이 한 마리가 세상구경을 하고 있다. 그 녀석은 말(Horse)이 되기를 간절히 원했다. 그래서 그는 말의 움직임을 유심히 살펴보고 그대로 따라 하기로 하였다. 말은 말이다. 말은 격자판에서 체스의 나이트와 같은 이동방식을 가진다. 다음 그림에 말의 이동방법이 나타나있다. x표시한 곳으로 말이 갈 수 있다는 뜻이다. 참고로 말은 장애물을 뛰어넘을 수 있다.

| 	|x|	 	|x|	|
|---|---|---|---|---|
|x|	| |	| 	 	x|
| | |말| | |	 	 
|x| | | | x|
| |x|	| 	x| |	 
근데 원숭이는 한 가지 착각하고 있는 것이 있다. 말은 저렇게 움직일 수 있지만 원숭이는 능력이 부족해서 총 K번만 위와 같이 움직일 수 있고, 그 외에는 그냥 인접한 칸으로만 움직일 수 있다. 대각선 방향은 인접한 칸에 포함되지 않는다.

이제 원숭이는 머나먼 여행길을 떠난다. 격자판의 맨 왼쪽 위에서 시작해서 맨 오른쪽 아래까지 가야한다. 인접한 네 방향으로 한 번 움직이는 것, 말의 움직임으로 한 번 움직이는 것, 모두 한 번의 동작으로 친다. 격자판이 주어졌을 때, 원숭이가 최소한의 동작으로 시작지점에서 도착지점까지 갈 수 있는 방법을 알아내는 프로그램을 작성하시오.

## 입력

첫째 줄에 정수 K가 주어진다. 둘째 줄에 격자판의 가로길이 W, 세로길이 H가 주어진다. 그 다음 H줄에 걸쳐 W개의 숫자가 주어지는데, 0은 아무것도 없는 평지, 1은 장애물을 뜻한다. 장애물이 있는 곳으로는 이동할 수 없다. 시작점과 도착점은 항상 평지이다. W와 H는 1이상 200이하의 자연수이고, K는 0이상 30이하의 정수이다.

## 출력 

첫째 줄에 원숭이의 동작수의 최솟값을 출력한다. 시작점에서 도착점까지 갈 수 없는 경우엔 -1을 출력한다.

## 예제 입력 1

```
1
4 4
0 0 0 0
1 0 0 0
0 0 1 0
0 1 0 0
```

## 예제 출력 1

```
4
```

## 예제 입력 2

```
2
5 2
0 0 1 1 0
0 0 1 1 0
```

## 예제 출력 2

```
-1
```

## 오답 풀이

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int W;
    private static int H;
    private static int[][] grid;
    private static final int INF = Integer.MAX_VALUE;
    private static int result = INF;
    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};
    private static final int[] HORSE_DR = {-1, -2, -2, -1, 1, 2, 2, 1};
    private static final int[] HORSE_DC = {-2, -1, 1, 2, 2, 1, -1, -2};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        grid = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(new boolean[H][W], 0, 0, 0, K);

        System.out.println(result != INF ? result : -1);
    }
    private static void dfs(boolean[][] isVisited, int row, int col, int count, int k) {

        if (row == H - 1 && col == W - 1) {
            result = Math.min(result, count);
            return;
        }

        isVisited[row][col] = true;

        for (int i = 0; i < 4; i++) {
            int nxtRow = row + DR[i];
            int nxtCol = col + DC[i];

            if (isValidPoint(nxtRow, nxtCol) && !isVisited[nxtRow][nxtCol] && grid[nxtRow][nxtCol] != 1) {
                dfs(isVisited, nxtRow, nxtCol, count + 1, k);
            }
        }

        if (k > 0) {
            for (int i = 0; i < 8; i++) {
                int nxtRow = row + HORSE_DR[i];
                int nxtCol = col + HORSE_DC[i];

                if (isValidPoint(nxtRow, nxtCol) && !isVisited[nxtRow][nxtCol] && grid[nxtRow][nxtCol] != 1) {
                    dfs(isVisited, nxtRow, nxtCol, count + 1, k - 1);
                }
            }
        }

        isVisited[row][col] = false;
    }
    private static boolean isValidPoint(int row, int col) {
        return row >= 0 && col >= 0 && row < H && col < W;
    }
}
```

**TLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int W;
    private static int H;
    private static int[][] grid;
    private static int[][][] history;
    private static int result = Integer.MAX_VALUE;

    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};
    private static final int[] HORSE_DR = {-1, -2, -2, -1, 1, 2, 2, 1};
    private static final int[] HORSE_DC = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        grid = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        history = new int[H][W][2];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Arrays.fill(history[i][j], INF);
            }
        }

        dfs(0, 0, K, 0);

        System.out.println(result != INF ? result : -1);
    }
    private static void dfs(int row, int col, int k, int count) {

        if (row == H - 1 && col == W - 1) {
            result = Math.min(result, count);
            return;
        }

        if (history[row][col][1] >= k && history[row][col][0] <= count) {
            return;
        }

        history[row][col][0] = count;
        history[row][col][1] = k;

        for (int i = 0; i < 4; i++) {
            int nxtRow = row + DR[i];
            int nxtCol = col + DC[i];

            if (isValidPoint(nxtRow, nxtCol) && grid[nxtRow][nxtCol] != 1) {
                dfs(nxtRow, nxtCol, k, count + 1);
            }
        }

        if (k > 0) {
            for (int i = 0; i < 8; i++) {
                int nxtRow = row + HORSE_DR[i];
                int nxtCol = col + HORSE_DC[i];

                if (isValidPoint(nxtRow, nxtCol) && grid[nxtRow][nxtCol] != 1) {
                    dfs(nxtRow, nxtCol, k - 1, count + 1);
                }
            }
        }
    }
    private static boolean isValidPoint(int row, int col) {
        return row >= 0 && col >= 0 && row < H && col < W;
    }
}
```

**MLE**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};

    private static final int[] HORSE_DR = {-1, -2, -2, -1, 1, 2, 2, 1};
    private static final int[] HORSE_DC = {-2, -1, 1, 2, 2, 1, -1, -2};

    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[][] grid = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = bfs(grid, W, H, K);
        System.out.println(result != INF ? result : -1);
    }
    private static int bfs(int[][] grid, int W, int H, int K) {
        boolean[][][] isVisited = new boolean[H][W][K + 1];

        Queue<Item> q = new ArrayDeque<>();
        q.add(new Item(0, 0, 0, K));
        isVisited[0][0][K] = true;

        while (!q.isEmpty()) {
            Item curItem = q.poll();

            if (curItem.row == H - 1 && curItem.col == W - 1) {
                return curItem.count;
            }

            for (int i = 0; i < 4; i++) {
                int nxtRow = curItem.row + DR[i];
                int nxtCol = curItem.col + DC[i];

                if (isValidPoint(nxtRow, nxtCol, H, W) && !isVisited[nxtRow][nxtCol][curItem.k] && grid[nxtRow][nxtCol] != 1) {
                    isVisited[nxtRow][nxtCol][curItem.k] = true;
                    q.add(new Item(nxtRow, nxtCol, curItem.count + 1, curItem.k));
                }
            }
            if (curItem.k > 0) {
                for (int i = 0; i < 8; i++) {
                    int nxtRow = curItem.row + HORSE_DR[i];
                    int nxtCol = curItem.col + HORSE_DC[i];

                    if (isValidPoint(nxtRow, nxtCol, H, W) && !isVisited[nxtRow][nxtCol][curItem.k] && grid[nxtRow][nxtCol] != 1) {
                        isVisited[nxtRow][nxtCol][curItem.k - 1] = true;
                        q.add(new Item(nxtRow, nxtCol, curItem.count + 1, curItem.k - 1));
                    }
                }
            }
        }

        return INF;
    }
    private static boolean isValidPoint(int row, int col, int H, int W) {
        return row >= 0 && col >= 0 && row < H && col < W;
    }
}
class Item {
    int row;
    int col;
    int count;
    int k;

    Item(int row, int col, int count, int k) {
        this.row = row;
        this.col = col;
        this.count = count;
        this.k = k;
    }
}
```