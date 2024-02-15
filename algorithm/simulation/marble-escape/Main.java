import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] grid = new char[N][M];
        Point initRedBallPoint = null;
        Point initBlueBallPoint = null;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                grid[i][j] = str.charAt(j);
                if (grid[i][j] == 'R') {
                    initRedBallPoint = new Point(i, j);
                    grid[i][j] = '.';
                }
                if (grid[i][j] == 'B') {
                    initBlueBallPoint = new Point(i, j);
                    grid[i][j] = '.';
                }
            }
        }

        boolean result = simulation(grid, N, M, initRedBallPoint, initBlueBallPoint);

        System.out.println(result ? 1 : 0);
    }
    private static boolean simulation(char[][] grid, int N, int M, Point initRedBallPoint, Point initBlueBallPoint) {

        Queue<Status> q = new ArrayDeque<>();
        q.add(new Status(initRedBallPoint, initBlueBallPoint, 0));

        boolean[][][][] isVisited = new boolean[N][M][N][M];
        isVisited[initRedBallPoint.row][initRedBallPoint.col]
                [initBlueBallPoint.row][initBlueBallPoint.col] = true;
        while (!q.isEmpty()) {
            Status curStatus = q.poll();

            if (curStatus.count == 10) break;

            for (int i = 0; i < 4; i++) {
                Point nextRedBallPoint = movePoint(grid, curStatus.redBallPoint, i);
                Point nextBlueBallPoint = movePoint(grid, curStatus.blueBallPoint, i);

                if (grid[nextBlueBallPoint.row][nextBlueBallPoint.col] == 'O') continue;

                if (isEnd(grid, nextRedBallPoint)) {
                    // System.out.println(curStatus.count + 1);
                    return true;
                }

                if (isSamePoint(nextRedBallPoint, nextBlueBallPoint)) {
                    if (priorityCheck(curStatus.redBallPoint, curStatus.blueBallPoint, i)) {
                        nextBlueBallPoint = adjustPoint(nextBlueBallPoint, i);
                    } else {
                        nextRedBallPoint = adjustPoint(nextRedBallPoint, i);
                    }
                }

                if (!isVisited[nextRedBallPoint.row][nextRedBallPoint.col]
                        [nextBlueBallPoint.row][nextBlueBallPoint.col]) {
                    q.add(new Status(nextRedBallPoint, nextBlueBallPoint, curStatus.count + 1));
                    isVisited[nextRedBallPoint.row][nextRedBallPoint.col]
                            [nextBlueBallPoint.row][nextBlueBallPoint.col] = true;
                }
            }
        }

        return false;
    }
    private static Point adjustPoint(Point point, int direction) {
        Point copyPoint = copyBallPoint(point);
        if (direction == 0) copyPoint.row++;
        else if (direction == 1) copyPoint.col--;
        else if (direction == 2) copyPoint.row--;
        else copyPoint.col++;

        return copyPoint;
    }
    private static boolean isEnd(char[][] grid, Point redBallPoint) {
        return grid[redBallPoint.row][redBallPoint.col] == 'O';
    }
    private static boolean isSamePoint(Point point1, Point point2) {
        return point1.row == point2.row && point1.col == point2.col;
    }
    private static Point movePoint(char[][] grid, Point ballPoint, int direction) {

        Point copyBallPoint = copyBallPoint(ballPoint);
        if (direction == 0) {
            int nextRow = copyBallPoint.row - 1;
            while (grid[nextRow][copyBallPoint.col] != '#' && grid[nextRow][copyBallPoint.col] != 'O')  {
                copyBallPoint.row = nextRow;
                nextRow--;
            }
            if (grid[nextRow][copyBallPoint.col] == 'O') copyBallPoint.row = nextRow;
        } else if (direction == 1) {
            int nextCol = copyBallPoint.col + 1;
            while (grid[copyBallPoint.row][nextCol] != '#' && grid[copyBallPoint.row][nextCol] != 'O') {
                copyBallPoint.col = nextCol;
                nextCol++;
            }
            if (grid[copyBallPoint.row][nextCol] == 'O') copyBallPoint.col = nextCol;
        } else if (direction == 2) {
            int nextRow = copyBallPoint.row + 1;
            while (grid[nextRow][copyBallPoint.col] != '#' && grid[nextRow][copyBallPoint.col] != 'O')  {
                copyBallPoint.row = nextRow;
                nextRow++;
            }
            if (grid[nextRow][copyBallPoint.col] == 'O') copyBallPoint.row = nextRow;
        } else {
            int nextCol = copyBallPoint.col - 1;
            while (grid[copyBallPoint.row][nextCol] != '#' && grid[copyBallPoint.row][nextCol] != 'O') {
                copyBallPoint.col = nextCol;
                nextCol--;
            }
            if (grid[copyBallPoint.row][nextCol] == 'O') copyBallPoint.col = nextCol;
        }

        return copyBallPoint;
    }
    // Red가 우선순위가 높다면 true 아니면 False
    private static boolean priorityCheck(Point redBallPoint, Point blueBallPoint, int direction) {
        if (direction == 0) {
            return redBallPoint.row < blueBallPoint.row;
        } else if (direction == 1) {
            return redBallPoint.col > blueBallPoint.col;
        } else if (direction == 2) {
            return redBallPoint.row > blueBallPoint.row;
        } else {
            return redBallPoint.col < blueBallPoint.col;
        }
    }
    private static Point copyBallPoint(Point point) {
        return new Point(point.row, point.col);
    }
}
class Status {
    Point redBallPoint;
    Point blueBallPoint;
    int count;

    Status(Point redBallPoint, Point blueBallPoint, int count) {
        this.redBallPoint = redBallPoint;
        this.blueBallPoint = blueBallPoint;
        this.count = count;
    }
}
class Point {
    int row;
    int col;

    Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}