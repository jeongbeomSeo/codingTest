import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] DR = {-1, 0, 1, 0};
    private static int[] DC = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] lake = new int[R][C];

        int color = 0;
        Point[] startPoints = new Point[2];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();

            for (int j = 0; j < C; j++) {
                char c = str.charAt(j);
                if (c == '.') lake[i][j] = 0;
                else if (c == 'L') {
                    startPoints[color] = new Point(i, j);
                    lake[i][j] = ++color;
                }
                else lake[i][j] = -1;
            }
        }

//        printLake(lake);

        for (int i = 0; i < 2; i++) {
            if(paints(lake, startPoints[i].row, startPoints[i].col, lake[startPoints[i].row][startPoints[i].col])) {
                System.out.println(0);
                return;
            }
        }
//        printLake(lake);

        // 시물레이트
        System.out.println(simulate(lake));

    }
    private static int simulate(int[][] lake) {

        int time = 0;

        // start Point 탐색 작업
        List<Point> points = findStartPoints(lake);

        while (time < 1500 * 1500) {

            // melt
            points = melt(points, lake);

//            printLake(lake);

            // time up
            time++;

            // paint 필요 위치 확인 후 bfs 탐색 페인트
            boolean isEnd = false;
            for (Point point : points) {
                int color;
                if ((color = needPainting(lake, point.row, point.col)) != 0) {
                    if (paints(lake, point.row, point.col, color)) {
                        isEnd = true;
                        break;
                    }
                }
            }

            if (isEnd) break;
        }

        return time;
    }
    private static int needPainting(int[][] lake, int row, int col) {

        if (lake[row][col] != 0) return 0;

        for (int i = 0; i < 4; i++) {
            int nxtRow = row + DR[i];
            int nxtCol = col + DC[i];

            if (!isValid(nxtRow, nxtCol, lake.length, lake[0].length)) continue;

            if (lake[nxtRow][nxtCol] == 1 || lake[nxtRow][nxtCol] == 2) return lake[nxtRow][nxtCol];
        }

        return 0;
    }
    private static List<Point> melt(List<Point> points, int[][] lake) {
        List<Point> nxtPoints = new ArrayList<>();

        for (Point cur : points) {
            for (int j = 0; j < 4; j++) {
                int nxtRow = cur.row + DR[j];
                int nxtCol = cur.col + DC[j];

                if (isValid(nxtRow, nxtCol, lake.length, lake[0].length)) {
//                    if (lake[nxtRow][nxtCol] == lake[cur.row][cur.col]) continue;

                    if (lake[nxtRow][nxtCol] == -1) {
                        lake[nxtRow][nxtCol] = 0;
                        nxtPoints.add(new Point(nxtRow, nxtCol));
                    }
                }
            }
        }

        return nxtPoints;
    }
    private static List<Point> findStartPoints(int[][] lake) {

        List<Point> startPoints = new ArrayList<>();

        for (int i = 0; i < lake.length; i++) {
            for (int j = 0; j < lake[0].length; j++) {

                if (lake[i][j] == -1) continue;

                for (int k = 0; k < 4; k++) {
                    int nxtRow = i + DR[k];
                    int nxtCol = j + DC[k];

                    if (!isValid(nxtRow, nxtCol, lake.length, lake[0].length)) continue;

                    if (lake[nxtRow][nxtCol] == -1) {
                        startPoints.add(new Point(i, j));
                        break;
                    }
                }
            }
        }

        return startPoints;
    }
    private static boolean paints(int[][] lake, int startRow, int startCol, int color) {

        Queue<Point> q = new ArrayDeque<>();

        q.add(new Point(startRow, startCol));

        while (!q.isEmpty()) {
            Point point = q.poll();

            lake[point.row][point.col] = color;

            for (int i = 0; i < 4; i++) {
                int nxtRow = point.row + DR[i];
                int nxtCol = point.col + DC[i];

                if (isValid(nxtRow, nxtCol, lake.length, lake[0].length)) {
                    if (lake[nxtRow][nxtCol] == color) continue;

                    if (lake[nxtRow][nxtCol] == 0) {
                        lake[nxtRow][nxtCol] = color;
                        q.add(new Point(nxtRow, nxtCol));
                    } else if (lake[nxtRow][nxtCol] + lake[point.row][point.col] == 3) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    private static boolean isValid(int row, int col, int rowSize, int colSize) {
        return row >= 0 && col >= 0 && row < rowSize && col < colSize;
    }

    private static void printLake(int[][] lake) {
        for (int i = 0; i < lake.length; i++) {
            for (int j = 0; j < lake[0].length; j++) {
                System.out.print(lake[i][j] + " ");
            }
            System.out.println();
        }
    }
}
class Point {
    int row;
    int col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
