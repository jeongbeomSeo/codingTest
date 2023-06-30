# 상어 중학교

**골드 2**

|시간 제한	|메모리 제한|	제출	|정답	|맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|1 초 (추가 시간 없음)	|1024 MB|	10323	|3972|	2475|	35.121%|

## 문제 

상어 중학교의 코딩 동아리에서 게임을 만들었다. 이 게임은 크기가 N×N인 격자에서 진행되고, 초기에 격자의 모든 칸에는 블록이 하나씩 들어있고, 블록은 검은색 블록, 무지개 블록, 일반 블록이 있다. 일반 블록은 M가지 색상이 있고, 색은 M이하의 자연수로 표현한다. 검은색 블록은 -1, 무지개 블록은 0으로 표현한다. (i, j)는 격자의 i번 행, j번 열을 의미하고, |r1 - r2| + |c1 - c2| = 1을 만족하는 두 칸 (r1, c1)과 (r2, c2)를 인접한 칸이라고 한다.

블록 그룹은 연결된 블록의 집합이다. 그룹에는 일반 블록이 적어도 하나 있어야 하며, 일반 블록의 색은 모두 같아야 한다. 검은색 블록은 포함되면 안 되고, 무지개 블록은 얼마나 들어있든 상관없다. 그룹에 속한 블록의 개수는 2보다 크거나 같아야 하며, 임의의 한 블록에서 그룹에 속한 인접한 칸으로 이동해서 그룹에 속한 다른 모든 칸으로 이동할 수 있어야 한다. 블록 그룹의 기준 블록은 무지개 블록이 아닌 블록 중에서 행의 번호가 가장 작은 블록, 그러한 블록이 여러개면 열의 번호가 가장 작은 블록이다.

오늘은 이 게임에 오토 플레이 기능을 만드려고 한다. 오토 플레이는 다음과 같은 과정이 블록 그룹이 존재하는 동안 계속해서 반복되어야 한다.

1. 크기가 가장 큰 블록 그룹을 찾는다. 그러한 블록 그룹이 여러 개라면 포함된 무지개 블록의 수가 가장 많은 블록 그룹, 그러한 블록도 여러개라면 기준 블록의 행이 가장 큰 것을, 그 것도 여러개이면 열이 가장 큰 것을 찾는다.
2. 1에서 찾은 블록 그룹의 모든 블록을 제거한다. 블록 그룹에 포함된 블록의 수를 B라고 했을 때, B2점을 획득한다.
3. 격자에 중력이 작용한다.
4. 격자가 90도 반시계 방향으로 회전한다.
5. 다시 격자에 중력이 작용한다.

격자에 중력이 작용하면 검은색 블록을 제외한 모든 블록이 행의 번호가 큰 칸으로 이동한다. 이동은 다른 블록이나 격자의 경계를 만나기 전까지 계속 된다.

참고: https://www.acmicpc.net/problem/21609

## 코드 

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  // 북, 동, 남, 서
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int DELETE = -9999;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[][] grid = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(simulation(grid));
  }
  static int simulation(int[][] grid) {

    int point = 0;
    while(true) {
      Result finalResult = null;

      boolean[][] notCheckZeroPointIsVisited = new boolean[N][N];

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (!notCheckZeroPointIsVisited[i][j] && (grid[i][j] != 0 && grid[i][j] != -1 && grid[i][j] != DELETE)) {
            Result result = findBlockGroup(grid, notCheckZeroPointIsVisited, i, j);

            if (result == null) continue;

            if (finalResult == null) finalResult = result;
            else if (finalResult.compare(result)) finalResult = result;
          }
        }
      }

      if (finalResult == null) break;
      point += activeFinalResult(finalResult, grid);

      activeGravity(grid);

      grid = rotateGrid(grid);

      activeGravity(grid);
    }

    return point;
  }
  static int[][] rotateGrid(int[][] grid) {
    int[][] newGrid = new int[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        newGrid[i][j] = grid[j][N - 1 - i];
      }
    }

    return newGrid;
  }
  static void activeGravity(int[][] grid) {

    for (int j = 0; j < N; j++) {
      for (int i = N - 1; i>= 0; i--) {
        if (grid[i][j] == DELETE) {
          int nextRow = i - 1;
          while (nextRow >= 0 && grid[nextRow][j] == DELETE) nextRow--;
          if (nextRow >= 0 && grid[nextRow][j] != -1) {
            grid[i][j] = grid[nextRow][j];
            grid[nextRow][j] = DELETE;
          }
        }
      }
    }
  }
  static int activeFinalResult(Result result, int[][] grid) {

    Queue<Block> q = result.resultQueue;

    while(!q.isEmpty()) {
      Block block = q.poll();

      grid[block.row][block.col] = DELETE;
    }

    return result.blockSize * result.blockSize;
  }
  static Result findBlockGroup(int[][] grid, boolean[][] notCheckZeroPointIsVisited, int row, int col) {

    boolean[][] isVisited = new boolean[N][N];
    Queue<Block> q = new ArrayDeque<>();
    Queue<Block> resultQueue = new ArrayDeque<>();
    Block initBlock = new Block(row, col);
    q.add(initBlock);
    resultQueue.add(initBlock);
    int color = grid[row][col];
    int rainbowBlockCount = 0;
    int totalCount = 1;
    isVisited[row][col] = true;
    notCheckZeroPointIsVisited[row][col] = true;

    Block mainBlock = null;
    while (!q.isEmpty()) {
      Block block = q.poll();

      if (grid[block.row][block.col] == color) {
        if (mainBlock == null) mainBlock = block;
        else if (mainBlock.compareBlock(block)) mainBlock = block;
        notCheckZeroPointIsVisited[block.row][block.col] = true;
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = block.row + dr[i];
        int nextCol = block.col + dc[i];

        if (isValidIdx(nextRow, nextCol) && (grid[nextRow][nextCol] == color || grid[nextRow][nextCol] == 0) &&
        !isVisited[nextRow][nextCol]) {
          Block nextBlock = new Block(nextRow, nextCol);
          q.add(nextBlock);
          resultQueue.add(nextBlock);
          isVisited[nextRow][nextCol] = true;
          if (grid[nextRow][nextCol] == 0) rainbowBlockCount++;
          totalCount++;
        }
      }
    }

    if (totalCount == 1) return null;

    return new Result(resultQueue, totalCount, rainbowBlockCount, mainBlock);
  }
  static boolean isValidIdx(int row, int col) {
    return row >= 0 && col >= 0 && row < N && col < N;
  }
}
class Result {
  Queue<Block> resultQueue;
  int blockSize;
  int rainbowBlockSize;
  Block mainBlock;

  Result(Queue<Block> resultQueue, int blockSize, int rainbowBlockSize, Block mainBlock) {
    this.resultQueue = resultQueue;
    this.blockSize = blockSize;
    this.rainbowBlockSize = rainbowBlockSize;
    this.mainBlock = mainBlock;
  }

  boolean compare(Result o) {
    if (this.blockSize < o.blockSize) return true;
    else if (this.blockSize > o.blockSize) return false;
    else {
      if (this.rainbowBlockSize < o.rainbowBlockSize) return true;
      else if (this.rainbowBlockSize > o.rainbowBlockSize) return false;
      else {
        if (this.mainBlock.row < o.mainBlock.row) return true;
        else if (this.mainBlock.row > o.mainBlock.row) return false;
        else {
          return this.mainBlock.col < o.mainBlock.col;
        }
      }
    }
  }
}
class Block {
  int row;
  int col;

  Block(int row, int col) {
    this.row = row;
    this.col = col;
  }

  boolean compareBlock(Block o) {
    if (this.row > o.row) return true;
    else if (this.row < o.row) return false;
    else return this.col > o.col;
  }
}
```