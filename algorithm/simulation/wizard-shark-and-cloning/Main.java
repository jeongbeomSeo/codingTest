import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int[] DR = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] DC = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] MOVE_SHARK_DR = {0, -1, 0, 1, 0};
    private static final int[] MOVE_SHARK_DC = {0, 0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        List<Fish>[][] fishGrid = initFishGrid();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            fishGrid[row][col].add(new Fish(new Point(row, col), direction));
        }

        st = new StringTokenizer(br.readLine());
        int sharkPointRow = Integer.parseInt(st.nextToken());
        int sharkPointCol = Integer.parseInt(st.nextToken());
        Point sharkPoint = new Point(sharkPointRow, sharkPointCol);

        System.out.println(simulation(fishGrid, sharkPoint, S));
    }
    private static long simulation(List<Fish>[][] fishGrid, Point sharkPoint, int S) {

        int[][] smellCountGrid = new int[5][5];
        while (S-- != 0) {
            List<Fish>[][] copyFishGrid = copyFishList(fishGrid);

            fishGrid = MoveFishGrid(fishGrid, smellCountGrid, sharkPoint);

            List<Point> moveSharkResult = moveShark(fishGrid, sharkPoint);
            activeEatFish(fishGrid, moveSharkResult, smellCountGrid);
            smellCountGrid = getNextSmellCountGrid(smellCountGrid);
            mergeGrid(fishGrid, copyFishGrid);
        }

        return getResult(fishGrid);
    }
    private static long getResult(List<Fish>[][] fishGrid) {
        long result = 0L;

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                result += fishGrid[i][j].size();
            }
        }

        return result;
    }
    private static void mergeGrid(List<Fish>[][] fishGrid, List<Fish>[][] copyFishGrid) {

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                fishGrid[i][j].addAll(copyFishGrid[i][j]);
            }
        }
    }
    private static int[][] getNextSmellCountGrid(int[][] smellCountGrid) {

        int[][] nextSmellCountGrid = new int[5][5];

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                if (smellCountGrid[i][j] != 0) {
                    nextSmellCountGrid[i][j] = smellCountGrid[i][j] - 1;
                }
            }
        }

        return nextSmellCountGrid;
    }
    private static void activeEatFish(List<Fish>[][] fishGrid, List<Point> pointList, int[][] smellCountGrid) {
        for (Point point : pointList) {
            if (!fishGrid[point.row][point.col].isEmpty()) {
                smellCountGrid[point.row][point.col] = 3;
            }
            fishGrid[point.row][point.col].clear();
        }
    }
    private static List<Point> moveShark(List<Fish>[][] fishGrid, Point sharkPoint) {

        Queue<MoveShark> q = new ArrayDeque<>();
        q.add(new MoveShark(sharkPoint));

        MoveShark result = null;
        while (!q.isEmpty()) {
            MoveShark curMoveShark = q.poll();

            if (curMoveShark.moveCount == 3) {
                if (result == null) {
                    result = curMoveShark;
                } else {
                    result = priorityCheck(result, curMoveShark);
                }
                continue;
            }

            for (int i = 1; i <= 4; i++) {
                int nextRow = curMoveShark.point.row + MOVE_SHARK_DR[i];
                int nextCol = curMoveShark.point.col + MOVE_SHARK_DC[i];
                Point nextPoint = new Point(nextRow, nextCol);

                if (isValidPoint(nextPoint) /*&& !curMoveShark.isVisited[nextPoint.row][nextPoint.col]*/) {
                    int nextEatCount = curMoveShark.eatCount;
                    if (!curMoveShark.isVisited[nextPoint.row][nextPoint.col]) nextEatCount += fishGrid[nextPoint.row][nextPoint.col].size();
                    MoveShark nextMoveShark =
                            new MoveShark(nextPoint, nextEatCount,
                                    curMoveShark.moveCount + 1, curMoveShark.directionHistory, i,
                                    curMoveShark.pointHistory, curMoveShark.isVisited);

                    q.add(nextMoveShark);
                }
            }
        }

        if (result == null) {
            System.out.println("Shark Move Logic Error");
            return null;
        }

        sharkPoint.row = result.point.row;
        sharkPoint.col = result.point.col;

        // System.out.println(sharkPoint.row + " " + sharkPoint.col);
        return result.pointHistory;
    }
    private static MoveShark priorityCheck(MoveShark moveShark, MoveShark comp) {

        if (moveShark.eatCount > comp.eatCount) return moveShark;
        else if (moveShark.eatCount < comp.eatCount) return comp;
        else {
            for (int i = 0; i < 3; i++) {
                if (moveShark.directionHistory.get(i) < comp.directionHistory.get(i)) return moveShark;
                else if (moveShark.directionHistory.get(i) > comp.directionHistory.get(i)) return comp;
            }
        }

        // Logic Error
        System.out.println("Logic Error");
        return moveShark;
    }
    private static List<Fish>[][] MoveFishGrid(List<Fish>[][] fishGrid, int[][] smellCountGrid, Point sharkPoint) {

        List<Fish>[][] nextFishGrid = initFishGrid();
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (Fish curFish : fishGrid[i][j]) {

                    boolean isSuccessMove = false;
                    int direction = curFish.direction;

                    do {
                        Point nextPoint = new Point(curFish.point.row + DR[direction], curFish.point.col + DC[direction]);
                        if (canMovePoint(nextPoint, sharkPoint, smellCountGrid)) {
                            curFish.point = nextPoint;
                            curFish.direction = direction;
                            nextFishGrid[curFish.point.row][curFish.point.col].add(curFish);
                            isSuccessMove = true;
                            break;
                        }

                        direction = getNextDirection(direction);
                    } while (curFish.direction != direction);

                    if (!isSuccessMove) nextFishGrid[curFish.point.row][curFish.point.col].add(curFish);
                }
            }
        }

/*        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                System.out.print(nextFishGrid[i][j].size() + " ");
            }
            System.out.println();
        }*/

        return nextFishGrid;
    }
    private static boolean canMovePoint(Point point, Point sharkPoint, int[][] smellCountGrid) {
        return isValidPoint(point) && !isSharkPoint(point, sharkPoint) && smellCountGrid[point.row][point.col] == 0;
    }
    private static boolean isSharkPoint(Point point, Point sharkPoint) {
        return (point.row == sharkPoint.row) && (point.col == sharkPoint.col);
    }
    private static boolean isValidPoint(Point point) {
        return point.row >= 1 && point.col >= 1 && point.row <= 4 && point.col <= 4;
    }
    private static int getNextDirection(int direction) {
        return direction - 1 != 0 ? direction - 1 : 8;
    }
    private static List<Fish>[][] copyFishList(List<Fish>[][] fishGrid) {
        List<Fish>[][] copy = initFishGrid();

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (Fish fish : fishGrid[i][j]) {
                    Point fishPoint = new Point(fish.point.row, fish.point.col);
                    Fish copyFish = new Fish(fishPoint, fish.direction);

                    copy[copyFish.point.row][copyFish.point.col].add(copyFish);
                }
            }
        }

        return copy;
    }
    private static List<Fish>[][] initFishGrid() {
        List<Fish>[][] fishGrid = new List[5][5];

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                fishGrid[i][j] = new ArrayList<>();
            }
        }

        return fishGrid;
    }
}
class MoveShark{
    Point point;
    int eatCount;
    int moveCount;
    List<Integer> directionHistory;
    List<Point> pointHistory;
    boolean[][] isVisited;

    MoveShark(Point point) {
        this.point = point;
        this.eatCount = 0;
        this.moveCount = 0;
        this.directionHistory = new ArrayList<>();
        this.pointHistory = new ArrayList<>();
        isVisited = new boolean[5][5];
    }
    MoveShark(Point point, int eatCount, int moveCount, List<Integer> directionHistory, int direction, List<Point> prevPointHistory, boolean[][] prevIsVisited) {
        this.point = point;
        this.eatCount = eatCount;
        this.moveCount = moveCount;
        this.directionHistory = new ArrayList<>(directionHistory);
        this.directionHistory.add(direction);
        this.pointHistory = new ArrayList<>(prevPointHistory);
        this.pointHistory.add(point);
        this.isVisited = initIsVisitedTable(prevIsVisited);
        this.isVisited[point.row][point.col] = true;
    }

    private static boolean[][] initIsVisitedTable(boolean[][] prevIsVisited) {
        boolean[][] newIsVisited = new boolean[5][5];

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                newIsVisited[i][j] = prevIsVisited[i][j];
            }
        }

        return newIsVisited;
    }
}
class Fish {
    Point point;
    int direction;

    Fish(Point point, int direction) {
        this.point = point;
        this.direction = direction;
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