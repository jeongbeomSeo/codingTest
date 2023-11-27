import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  private static final int[] dr = {-1, 0, 1, 0};
  private static final int[] dc = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    char[][] grid = new char[N][M];
    Ball red = null;
    Ball blue = null;
    for (int i = 0; i < N; i++) {
      String str = br.readLine();

      for (int j = 0; j < M; j++) {
        grid[i][j] = str.charAt(j);

        if (grid[i][j] == 'R') {
          red = new Ball(i, j);
          grid[i][j] = '.';
        } else if (grid[i][j] == 'B') {
          blue = new Ball(i, j);
          grid[i][j] = '.';
        }
      }
    }

    System.out.println(simulation(grid, red, blue));
  }
  private static int simulation(char[][] grid, Ball red, Ball blue) {

    Set<String> historySet = new HashSet<>();
    historySet.add(makeHistory(red, blue));

    Queue<Game> games = new ArrayDeque<>();
    games.add(new Game(red, blue, 0));
    while (!games.isEmpty()) {
      Game game = games.poll();

      if (game.count == 10) continue;

      for (int i = 0; i < 4; i++) {
        Ball nextRedPoint = moveNextPosition(game.red, grid, dr[i], dc[i]);
        Ball nextBluePoint = moveNextPosition(game.blue, grid, dr[i], dc[i]);

        if (isFail(grid, nextBluePoint)) continue;
        if (isEnd(grid, nextRedPoint)) {
          return game.count + 1;
        }

        modulatePoint(nextRedPoint, nextBluePoint, i, game.red, game.blue);
        String history = makeHistory(nextRedPoint, nextBluePoint);
        if (!historySet.contains(history)) {
          games.add(new Game(nextRedPoint, nextBluePoint, game.count + 1));
          historySet.add(history);
        }
      }
    }
    return -1;
  }
  private static void modulatePoint(Ball nextRedPoint, Ball nextBluePoint, int direction, Ball red, Ball blue) {
    if (isSamePoint(nextRedPoint, nextBluePoint)) {
      if (isPriority(red, blue, direction)) {
        modulateBallPoint(nextBluePoint, direction);
      } else {
        modulateBallPoint(nextRedPoint, direction);
      }
    }
  }
  private static void modulateBallPoint(Ball ball, int direction) {
    if (direction == 0) ball.row++;
    else if (direction == 1) ball.col--;
    else if (direction == 2) ball.row--;
    else ball.col++;
  }
  private static boolean isSamePoint(Ball red, Ball blue) {
    return (red.row == blue.row) && (red.col == blue.col);
  }
  private static boolean isFail(char[][] grid, Ball blue) {
    return grid[blue.row][blue.col] == 'O';
  }
  private static boolean isEnd(char[][] grid, Ball red) {
    return grid[red.row][red.col] == 'O';
  }
  private static Ball moveNextPosition(Ball ball, char[][] grid, int dr, int dc) {

    Ball newBall = new Ball(ball.row, ball.col);

    while (!cantMove(newBall, grid, dr, dc)) {
      newBall.row += dr;
      newBall.col += dc;
    }

    return newBall;
  }
  private static boolean cantMove(Ball ball, char[][] grid, int dr, int dc) {
    int nextRow = ball.row + dr;
    int nextCol = ball.col + dc;
    return isHole(grid, ball.row, ball.col) || isWall(grid, nextRow, nextCol);
  }
  private static boolean isHole(char[][] grid, int row, int col) {
    return grid[row][col] == 'O';
  }
  private static boolean isWall(char[][] grid, int nextRow, int nextCol) {
    return grid[nextRow][nextCol] == '#';
  }
  private static boolean isPriority(Ball red, Ball blue, int direction) {
    if (direction == 0) return red.row < blue.row;
    else if (direction == 1) return red.col > blue.col;
    else if (direction == 2) return red.row > blue.row;
    else return red.col < blue.col;
  }
  private static String makeHistory(Ball red, Ball blue) {
    return makeBallHistory('R', red.row, red.col)
            + makeBallHistory('B', blue.row, blue.col);
  }
  private static String makeBallHistory(char color, int row, int col) {
    return String.valueOf(color) + makeString(row) + makeString(col);
  }
  private static String makeString(int value) {
    String str = String.valueOf(value);

    if (str.length() == 1) return "0" + str;
    return str;
  }
}
class Game {
  Ball red;
  Ball blue;
  int count;

  Game(Ball red, Ball blue, int count) {
    this.red = red;
    this.blue = blue;
    this.count = count;
  }

}
class Ball {
  int row;
  int col;

  Ball(int row, int col) {
    this.row = row;
    this.col = col;
  }
}