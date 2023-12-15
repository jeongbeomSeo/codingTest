import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] shark_dr = {0, -1, 0, 1, 0};
    private static final int[] shark_dc = {0, 0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        List<Fish> fishList = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            fishList.add(new Fish(row, col, direction));
        }

        st = new StringTokenizer(br.readLine());
        int sharkRow = Integer.parseInt(st.nextToken());
        int sharkCol = Integer.parseInt(st.nextToken());
        Point shark = new Point(sharkRow, sharkCol);

        System.out.println(simulation(fishList, shark, S));
    }
    private static int simulation(List<Fish> fishList, Point shark, int S) {

        int time = S;
        boolean[][] smellGrid = new boolean[5][5];
        Deque<Smell> smellDeque = new ArrayDeque<>();
        while (time != 0) {

            Queue<Fish> copyFishBuffer = getCopyFishBuffer(fishList);

            List<Integer>[][] fishIdxGrid = moveFishes(fishList, smellGrid, shark);

            List<Point> pointHistory = moveShark(fishIdxGrid, shark);

            eatingFish(fishIdxGrid, fishList, pointHistory, smellDeque);

            ResultProcessSmell resultProcessSmell = processSmell(smellDeque);
            smellGrid = resultProcessSmell.smellGrid;;
            smellDeque = resultProcessSmell.smellDeque;

            addCopyFish(fishList, copyFishBuffer);
            time--;
        }

        return fishList.size();
    }
    private static void addCopyFish(List<Fish> fishList, Queue<Fish> copyFishBuffer) {

        while (!copyFishBuffer.isEmpty()) {
            fishList.add(copyFishBuffer.poll());
        }
    }
    private static ResultProcessSmell processSmell(Deque<Smell> smellDeque) {

        boolean[][] newSmellGrid = new boolean[5][5];
        Deque<Smell> newSmellDeque = new ArrayDeque<>();
        while (!smellDeque.isEmpty()) {
            Smell smell = smellDeque.pollLast();

            if (--smell.count != 0 && !newSmellGrid[smell.row][smell.col]) {
                newSmellDeque.addFirst(smell);
                newSmellGrid[smell.row][smell.col] = true;
            }
         }

        return new ResultProcessSmell(newSmellGrid, newSmellDeque);
    }
    private static void eatingFish(List<Integer>[][] fishIdxGrid, List<Fish> fishList, List<Point> pointHistory, Deque<Smell> smellDeque) {

        Set<Integer> idxList = new TreeSet<>(Collections.reverseOrder());

        // 재방문하는 경우에 idx가 중복될 수 있다는 것을 고려해야함.
        for (Point point : pointHistory) {
            List<Integer> fishIdxListInGrid = fishIdxGrid[point.row][point.col];
            idxList.addAll(fishIdxListInGrid);
        }

        for (int idx : idxList) {
            Fish fish = fishList.get(idx);
            fishList.remove(idx);

            smellDeque.addLast(new Smell(fish.row, fish.col));
        }
    }
    private static List<Point> moveShark(List<Integer>[][] fishIdxGrid, Point shark) {

        Queue<MovingShark> movingSharkQueue = new ArrayDeque<>();
        movingSharkQueue.add(new MovingShark(shark.row, shark.col));

        MovingShark result = null;
        while(!movingSharkQueue.isEmpty()) {
            MovingShark curMovingShark = movingSharkQueue.poll();

            if (curMovingShark.movingCount == 3) {
                if (result == null) {
                    result = curMovingShark;
                } else {
                    if (result.compareTo(curMovingShark) > 0) result = curMovingShark;
                }
                continue;
            }

            for (int i = 1; i <= 4; i++) {
                int nextRow = curMovingShark.row + shark_dr[i];
                int nextCol = curMovingShark.col + shark_dc[i];

                if (isValidPoint(nextRow, nextCol)) {
                    Point nextPoint = new Point(nextRow, nextCol);
                    if (isVisitedPoint(curMovingShark.pointHistory, nextPoint)) {
                        movingSharkQueue.add(new MovingShark(
                                nextRow, nextCol, curMovingShark.eatingCount,
                                curMovingShark.movingCount + 1, curMovingShark.directionHistory, curMovingShark.pointHistory, i
                        ));
                    } else {
                        movingSharkQueue.add(new MovingShark(
                                nextRow, nextCol, curMovingShark.eatingCount + fishIdxGrid[nextRow][nextCol].size(),
                                curMovingShark.movingCount + 1, curMovingShark.directionHistory, curMovingShark.pointHistory, i
                        ));
                    }
                }
            }
        }

        if (result == null) {
            System.out.println("로직 오류 발생: result is null!");
            return null;
        }

        shark.row = result.row;
        shark.col = result.col;

        return result.pointHistory;
    }
    private static boolean isVisitedPoint(List<Point> pointHistory, Point point) {
        for (Point p : pointHistory) {
            if (p.row == point.row && p.col == point.col) return true;
        }

        return false;
    }
    private static List<Integer>[][] moveFishes(List<Fish> fishList, boolean[][] smellGrid, Point shark) {

        List<Integer>[][] fishIdxGrid = initFishIdxGrid();
        for (int i = 0; i < fishList.size(); i++) {
            Fish fish = fishList.get(i);
            Integer[] nextPoint = getNextPoint(fish, smellGrid, shark);

            fish.row = nextPoint[0];
            fish.col = nextPoint[1];
            fish.direction = nextPoint[2];

            fishIdxGrid[fish.row][fish.col].add(i);
        }

        return fishIdxGrid;
    }
    private static List<Integer>[][] initFishIdxGrid() {
        List<Integer>[][] grid = new List[5][5];

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }

        return grid;
    }
    private static Integer[] getNextPoint(Fish fish, boolean[][] smellGrid, Point shark) {

        int curDirection = fish.direction;
        do {
            int nextRow = fish.row + dr[curDirection];
            int nextCol = fish.col + dc[curDirection];

            if (canMoveNextPoint(nextRow, nextCol, smellGrid, shark)) {
                return new Integer[]{nextRow, nextCol, curDirection};
            }

            curDirection = getNextDirection(curDirection);
        } while (curDirection != fish.direction);

        return new Integer[]{fish.row, fish.col, fish.direction};
    }
    private static int getNextDirection(int direction) {
        return direction == 1 ? 8 : direction - 1;
    }
    private static boolean canMoveNextPoint(int row, int col, boolean[][] smellGrid, Point shark) {
        return isValidPoint(row, col) && !smellGrid[row][col] && !isSharkPoint(row, col, shark);
    }
    private static boolean isSharkPoint(int row, int col, Point shark) {
        return row == shark.row && col == shark.col;
    }
    private static boolean isValidPoint(int row, int col) {
        return row >= 1 && col >= 1 && row <= 4 && col <= 4;
    }
    private static Queue<Fish> getCopyFishBuffer(List<Fish> fishList) {

        Queue<Fish> copyFishBuffer = new ArrayDeque<>();
        for (Fish fish : fishList) {
            copyFishBuffer.add(new Fish(fish.row, fish.col, fish.direction));
        }

        return copyFishBuffer;
    }
}
class MovingShark implements Comparable<MovingShark>{
    int row;
    int col;
    int eatingCount;
    int movingCount;
    List<Integer> directionHistory;
    List<Point> pointHistory;

    MovingShark(int row, int col) {
        this.row = row;
        this.col = col;
        this.eatingCount = 0;
        this.movingCount = 0;
        this.directionHistory = new ArrayList<>();
        this.pointHistory = initPointHistory(row, col);
    }

    MovingShark(int row, int col, int eatingCount, int movingCount, List<Integer> directionHistory, List<Point> pointHistory, int direction) {
        this.row = row;
        this.col = col;
        this.eatingCount = eatingCount;
        this.movingCount = movingCount;
        this.directionHistory = getNewDirectionHistory(directionHistory, direction);
        this.pointHistory = getNewPointHistory(pointHistory, row, col);
    }
    private List<Integer> getNewDirectionHistory(List<Integer> directionHistory, int direction) {
        List<Integer> newDirectionHistory = new ArrayList<>(directionHistory);

        newDirectionHistory.add(direction);
        return newDirectionHistory;
    }
    private List<Point> getNewPointHistory(List<Point> pointHistory, int row, int col) {
        // 해당 기능이 재대로 Deep Copy가 되는지 확인 필요!!
        List<Point> newPointHistory = new ArrayList<>(pointHistory);

        newPointHistory.add(new Point(this.row, this.col));
        return newPointHistory;
    }
    private List<Point> initPointHistory(int row, int col) {
        List<Point> pointHistory = new ArrayList<>();

        pointHistory.add(new Point(row, col));
        return pointHistory;
    }

    @Override
    public int compareTo(MovingShark o) {
        if (this.eatingCount != o.eatingCount) return o.eatingCount - this.eatingCount;
        else {
            int thisIdx = Convert.getHistoryDirection(directionHistory);
            int compareIdx = Convert.getHistoryDirection(o.directionHistory);

            return thisIdx - compareIdx;
        }
    }
    private static class Convert {
        private static int getHistoryDirection(List<Integer> directionHistory) {
            StringBuilder sb = new StringBuilder();
            for (Integer integer : directionHistory) {
                sb.append(integer);
            }

            return Integer.parseInt(sb.toString());
        }
    }
}
class ResultProcessSmell {
    boolean[][] smellGrid;
    Deque<Smell> smellDeque;

    ResultProcessSmell(boolean[][] smellGrid, Deque<Smell> smellDeque) {
        this.smellGrid = smellGrid;
        this.smellDeque = smellDeque;
    }
}
class Smell {
    int row;
    int col;
    int count;

    Smell(int row, int col) {
        this.row = row;
        this.col = col;
        this.count = 3;
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