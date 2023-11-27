import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        while (tc-- != 0) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            char[][] grid = initGridByInputStream(br, h, w);

            String keyStr = br.readLine();
            boolean[] keyList = initKeyList(keyStr);

            List<Point> startPointList = initStartPointList(grid, keyList, h, w);
            List<Point> doorPointList = initDoorBoundary(grid, h, w);

            System.out.println(simulation(grid, startPointList, doorPointList, keyList, h, w));
        }
    }
    private static int simulation(char[][] grid, List<Point> startPointList, List<Point> doorPointList, boolean[] keyList, int h, int w) {

        boolean getKeyThisTurn;
        int count = 0;

        do {
            getKeyThisTurn = false;
            boolean[][] isVisited = new boolean[h][w];

            for (Point startPoint : startPointList) {
                int[] result = search(grid, keyList, isVisited, startPoint, h, w);
                if (result[1] != 0) getKeyThisTurn = true;

                count += result[0];
            }

            // 가장자리에 있는 문 중에 들어갈 수 있는 문있다면 추가
            if (getKeyThisTurn) {
                getNewEnteringPoint(grid, doorPointList, startPointList, keyList);
            }
        } while (getKeyThisTurn);

        return count;
    }
    // return: int[]{get item(문서) count, get key count}
    private static int[] search(char[][] grid, boolean[] keyList, boolean[][] isVisited, Point startPoint, int h, int w) {

        Queue<Point> buffer = new ArrayDeque<>();
        buffer.add(startPoint);
        isVisited[startPoint.row][startPoint.col] = true;
        int getItemCount = 0;
        int getKeyCount = 0;

        while (!buffer.isEmpty()) {
            Point curPoint = buffer.poll();

            // Get Key Where Not Having
            if (isKey(grid[curPoint.row][curPoint.col])) {
                int keyIdx = getKeyIdx(grid[curPoint.row][curPoint.col]);
                if (!keyList[keyIdx]) {
                    keyList[keyIdx] = true;
                    getKeyCount++;
                }
                grid[curPoint.row][curPoint.col] = '.';
            }
            // Get Item
            if (grid[curPoint.row][curPoint.col] == '$') {
                getItemCount++;
                grid[curPoint.row][curPoint.col] = '.';
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = curPoint.row + dr[i];
                int nextCol = curPoint.col + dc[i];

                if (isValidPoint(nextRow, nextCol, h, w)
                        && !isVisited[nextRow][nextCol]
                        && canEnterInnerSpace(grid, keyList, nextRow, nextCol)) {
                    buffer.add(new Point(nextRow, nextCol));
                    isVisited[nextRow][nextCol] = true;
                }
            }
        }

        return new int[]{getItemCount, getKeyCount};
    }

    private static boolean isValidPoint(int row, int col, int h, int w) {
        return row >= 0 && col >= 0 && row < h && col < w;
    }
    private static List<Point> initDoorBoundary(char[][] grid, int h, int w) {

        List<Point> list = new ArrayList<>();

        int initRow = 0;
        int initCol = 0;

        Point point = new Point(initRow, initCol);
        do {
            if (isDoor(grid[point.row][point.col])) {
                list.add(point);
            }

            point = getNextPointBoundary(point.row, point.col, h, w);
        } while (!isEndBoundaryCheck(point, initRow, initCol));

        return list;
    }
    private static void getNewEnteringPoint(char[][] grid, List<Point> doorPointList, List<Point> startPointList, boolean[] keyList) {

        List<Point> removePointList = new ArrayList<>();
        for (Point doorPoint : doorPointList) {
            if (canEnterUsingKey(grid, keyList, doorPoint.row, doorPoint.col)) {
                startPointList.add(doorPoint);
                // 이렇게 삭제해도 되는지 의문임 => 예외 발생(ConcurrentModification)
                //doorPointList.remove(doorPoint);
                removePointList.add(doorPoint);
            }
        }
        for (Point removePoint : removePointList) {
            doorPointList.remove(removePoint);
        }
    }
    private static List<Point> initStartPointList(char[][] grid, boolean[] keyList, int h, int w) {

        List<Point> list = new ArrayList<>();

        int initRow = 0;
        int initCol = 0;

        Point point = new Point(initRow, initCol);
        do {
            if (canEnterInnerSpace(grid, keyList, point.row, point.col)) {
                list.add(point);
            }

            point = getNextPointBoundary(point.row, point.col, h, w);
        } while (!isEndBoundaryCheck(point, initRow, initCol));

        return list;
    }
    private static boolean canEnterInnerSpace(char[][] grid, boolean[] keyList, int row, int col) {

        char pointChar = grid[row][col];
        return pointChar == '.' || pointChar == '$' || isKey(pointChar) || canEnterUsingKey(grid, keyList, row, col);
    }
    private static boolean canEnterUsingKey(char[][] grid, boolean[] keyList, int row, int col) {
        if (isDoor(grid[row][col])) {
            int keyIdx = getDoorIdx(grid[row][col]);

            if (keyList[keyIdx]) {
                grid[row][col] = '.';
            }

            return keyList[keyIdx];
        }
        return false;
    }
    private static boolean isKey(char c) {
        return c >= 'a' && c <= 'z';
    }
    private static boolean isDoor(char c) {
        return c >= 'A' && c <= 'Z';
    }
    private static boolean isEndBoundaryCheck(Point point, int initRow, int initCol) {
        return point.row == initRow && point.col == initCol;
    }
    private static Point getNextPointBoundary(int row, int col, int h, int w) {

        int nextRow = row;
        int nextCol = col;
        if (row == 0) {
            if (col == w - 1) nextRow = row + 1;
            else nextCol = col + 1;
        } else if (col == w - 1) {
            if (row == h - 1) nextCol = col - 1;
            else nextRow = row + 1;
        } else if (row == h - 1) {
            if (col == 0) nextRow = row - 1;
            else nextCol = col - 1;
        } else nextRow = row - 1;

        return new Point(nextRow, nextCol);
    }
    private static boolean[] initKeyList(String keyStr) {
        boolean[] keyList = new boolean['z' - 'a' + 1];

        if (!keyStr.equals("0")) {
            for (int i = 0; i < keyStr.length(); i++) {
                keyList[getKeyIdx(keyStr.charAt(i))] = true;
            }
        }

        return keyList;
    }
    private static int getKeyIdx(char c) {
        return c - 'a';
    }
    private static int getDoorIdx(char c) {
        return c - 'A';
    }
    private static char[][] initGridByInputStream(BufferedReader br, int h, int w) throws IOException{
        char[][] grid = new char[h][w];
        for (int i = 0; i < h; i++) {
            String str = br.readLine();
            for (int j = 0; j < w; j++) {
                grid[i][j] = str.charAt(j);
            }
        }

        return grid;
    }
}
class Point {
    int row;
    int col;

    Point (int row, int col) {
        this.row = row;
        this.col = col;
    }
}