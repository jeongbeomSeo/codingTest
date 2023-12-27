import java.io.*;
import java.util.*;

public class Main {
    private static final int[] DR = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] DC = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] SHARK_DR = {0, -1, 0, 1, 0};
    private static final int[] SHARK_DC = {0, 0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        List<Fish>[][] fishListGrid = initFishListGrid();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            fishListGrid[row][col].add(new Fish(row, col, direction));
        }

        st = new StringTokenizer(br.readLine());
        int sharkRow = Integer.parseInt(st.nextToken());
        int sharkCol = Integer.parseInt(st.nextToken());

        Point sharkPoint = new Point(sharkRow, sharkCol);

        System.out.println(simulation(fishListGrid, sharkPoint, S));
    }
    private static int simulation(List<Fish>[][] fishListGrid, Point sharkPoint, int S) {

        Map<Integer, Integer> smellCountMap = initSmellCountMap();
        while (S != 0) {

            long startTime = System.currentTimeMillis();

            List<Fish>[][] copy = copyFishListGrid(fishListGrid);

            long endTime = System.currentTimeMillis();
            System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");

            fishListGrid = moveFishList(fishListGrid, sharkPoint, smellCountMap);

            // System.out.println("S is " + S);
            //printFishCountGrid(fishListGrid);


            sharkPoint = moveShark(fishListGrid, sharkPoint, smellCountMap);


            downSmellCount(smellCountMap);

            integrateFishListGrid(fishListGrid, copy);

            S--;
        }

        return getFishCount(fishListGrid);
    }
    private static void printFishCountGrid(List<Fish>[][] fishListGrid) {

        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                System.out.print(fishListGrid[i][j].size() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private static Point moveShark(List<Fish>[][] fishListGrid, Point sharkPoint, Map<Integer, Integer> smellCountMap) {

        Queue<MovingShark> bufferMovingShark = new ArrayDeque<>();
        bufferMovingShark.add(new MovingShark(sharkPoint.row, sharkPoint.col));

        MovingShark result = null;
        while (!bufferMovingShark.isEmpty()) {
            MovingShark curMovingShark = bufferMovingShark.poll();

            if (curMovingShark.moveCount == 3) {
                if (result == null) {
                    result = curMovingShark;
                } else {
                    if (result.compareTo(curMovingShark) > 0) {
                        result = curMovingShark;
                    }
                }
                continue;
            }

            for (int i = 1; i <= 4; i++) {
                int nextRow = curMovingShark.row + SHARK_DR[i];
                int nextCol = curMovingShark.col + SHARK_DC[i];

                if (isValidPoint(nextRow, nextCol)) {
                    bufferMovingShark.add(new MovingShark(
                            nextRow, nextCol, i, curMovingShark.moveCount + 1,
                            getEatingCount(nextRow, nextCol, curMovingShark.eatingCount, fishListGrid[nextRow][nextCol].size(), curMovingShark.pointHistory),
                            curMovingShark.pointHistory, curMovingShark.directionHistory
                    ));
                }
            }
        }

        if (result == null) {
            System.out.println("Logic Error: Result Is Empty");
        }

        eatingFish(fishListGrid, result.pointHistory, smellCountMap);

        return new Point(result.row, result.col);
    }

    private static void eatingFish(List<Fish>[][] fishListGrid, List<Point> pointHistory, Map<Integer, Integer> smellCountMap) {

        for (Point point : pointHistory) {
            if (!fishListGrid[point.row][point.col].isEmpty()) {
                updateSmellCount(smellCountMap, point.row, point.col);
            }
            fishListGrid[point.row][point.col].clear();
        }
    }
/*  Poing History가 전부 Smell Count로 처리되는 것이 아닌 먹이감이 있는 경우에만 Smell이 생성되는 것임.
  private static void eatingFish(List<Fish>[][] fishListGrid, List<Point> pointHistory, Map<Integer, Integer> smellCountMap) {

        for (Point point : pointHistory) {
            fishListGrid[point.row][point.col].clear();
            updateSmellCount(smellCountMap, point.row, point.col);
        }
    }*/
    private static int getEatingCount(int row, int col, int eatingCount, int fishCount, List<Point> pointHistory) {

        if (isVisitedPoint(row, col, pointHistory)) return eatingCount;

        return eatingCount + fishCount;
    }
    private static boolean isVisitedPoint(int row, int col, List<Point> pointHistory) {

        for (Point point : pointHistory) {
            if (point.row == row && point.col == col) return true;
        }

        return false;
    }
    private static List<Fish>[][] moveFishList(List<Fish>[][] fishListGrid, Point sharkPoint, Map<Integer, Integer> smellCountMap) {

        List<Fish>[][] newFishListGrid = initFishListGrid();
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                for (Fish curFish : fishListGrid[i][j]) {

                    Fish movedFish = moveFish(curFish, sharkPoint, smellCountMap);

                    newFishListGrid[movedFish.row][movedFish.col].add(
                            new Fish(movedFish.row, movedFish.col, movedFish.direction));
                }
            }
        }

        return newFishListGrid;
    }
    private static Fish moveFish(Fish fish, Point sharkPoint, Map<Integer, Integer> smellCountMap) {

        int curDirection = fish.direction;

        do {
            int nextRow = fish.row + DR[curDirection];
            int nextCol = fish.col + DC[curDirection];

            if (canGoNextPoint(nextRow, nextCol, sharkPoint, smellCountMap)) {
                return new Fish(nextRow, nextCol, curDirection);
            }

            curDirection = getNextDirection(curDirection);
        } while (curDirection != fish.direction);

        return new Fish(fish.row, fish.col, fish.direction);
    }
    private static boolean canGoNextPoint(int nextRow, int nextCol, Point sharkPoint, Map<Integer, Integer> smellCountMap) {
        return isValidPoint(nextRow, nextCol) && !isSharkPoint(nextRow, nextCol, sharkPoint) && !isSmellInPoint(nextRow, nextCol, smellCountMap);
    }
    private static boolean isSmellInPoint(int row, int col, Map<Integer, Integer> smellCountMap) {
        int idx = getMapIdx(row, col);

        return smellCountMap.get(idx) != 0;
    }
    private static boolean isSharkPoint(int row, int col, Point sharkPoint) {
        return row == sharkPoint.row && col == sharkPoint.col;
    }
    private static boolean isValidPoint(int row, int col) {
        return row >= 1 && col >= 1 && row <= 4 && col <= 4;
    }
    private static int getNextDirection(int direction) {
        return direction - 1 != 0 ? direction - 1 : 8;
    }

    private static int getFishCount(List<Fish>[][] fishListGrid) {

        int count = 0;
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                count += fishListGrid[i][j].size();
            }
        }

        return count;
    }
    private static void integrateFishListGrid(List<Fish>[][] fishListGrid, List<Fish>[][] copy) {

        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                fishListGrid[i][j].addAll(copy[i][j]);
            }
        }
    }
    private static List<Fish>[][] copyFishListGrid(List<Fish>[][] fishListGrid) {

        List<Fish>[][] copy = initFishListGrid();
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                for (int k = 0; k < fishListGrid[i][j].size(); k++) {
                    Fish fish = fishListGrid[i][j].get(k);
                    copy[i][j].add(new Fish(fish.row, fish.col, fish.direction));
                }
            }
        }

        return copy;
    }
    private static void downSmellCount(Map<Integer, Integer> smellCountMap) {

        int MAX_IDX = 4 * 4;
        for (int i = 1; i < MAX_IDX + 1; i++) {
            if (smellCountMap.get(i) != 0) {
                smellCountMap.put(i, smellCountMap.get(i) - 1);
            }
        }
    }
    private static void updateSmellCount(Map<Integer, Integer> smellCountMap, int row, int col) {
        smellCountMap.put(getMapIdx(row, col), 3);
    }
    private static Map<Integer, Integer> initSmellCountMap() {
        Map<Integer, Integer> smellCountMap = new HashMap<>();

        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                int idx = getMapIdx(i, j);
                smellCountMap.put(idx, 0);
            }
        }

        return smellCountMap;
    }
    private static int getMapIdx(int row, int col) {
        return 4 * (row - 1) + col;
    }
    private static List<Fish>[][] initFishListGrid() {

        List<Fish>[][] fishListGrid = new List[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                fishListGrid[i][j] = new ArrayList<>();
            }
        }
        return fishListGrid;
    }
}
class MovingShark implements Comparable<MovingShark>{
    int row;
    int col;
    int moveCount;
    int eatingCount;
    List<Point> pointHistory;
    List<Integer> directionHistory;
    MovingShark(int row, int col) {
        this.row = row;
        this.col = col;
        this.moveCount = 0;
        this.eatingCount = 0;
        pointHistory = new ArrayList<>();
        directionHistory = new ArrayList<>();
    }

    MovingShark(int row, int col, int direction, int moveCount, int eatingCount, List<Point> pointHistory, List<Integer> directionHistory) {
        this.row = row;
        this.col = col;
        this.moveCount = moveCount;
        this.eatingCount = eatingCount;
        this.pointHistory = initPointHistory(row, col, pointHistory);
        this.directionHistory = initDirectionHistory(direction, directionHistory);
    }
    private List<Point> initPointHistory(int row, int col, List<Point> pointHistory) {

        List<Point> newPointHistory = new ArrayList<>(pointHistory);
        newPointHistory.add(new Point(row, col));

        return newPointHistory;
    }
    private List<Integer> initDirectionHistory(int direction, List<Integer> directionHistory) {
        List<Integer> newDirectionHistory = new ArrayList<>(directionHistory);
        newDirectionHistory.add(direction);

        return newDirectionHistory;
    }

    @Override
    public int compareTo(MovingShark o) {
        if (this.eatingCount != o.eatingCount) return o.eatingCount - this.eatingCount;
        else {
            int size = this.directionHistory.size();
            if (size != o.directionHistory.size()) {
                System.out.println("Can't compare");
                return 0;
            } else {
                for (int i = 0; i < size; i++) {
                    int directionIdx = this.directionHistory.get(i);
                    int comp = o.directionHistory.get(i);
                    if (directionIdx != comp) return directionIdx - comp;
                }
            }
        }
        return 0;
    }
}
class Fish {
    int row;
    int col;
    int direction;

    Fish(int row, int col, int direction) {
        this.row = row;
        this.col = col;
        this.direction = direction;
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