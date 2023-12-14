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
    Shark shark = new Shark(sharkRow, sharkCol);

    System.out.println(simulation(shark, fishList, S));
  }
  private static int simulation(Shark shark, List<Fish> fishList, int S) {

    List<Smell> smellList = new ArrayList<>();
    boolean[][] smellGrid = new boolean[5][5];
    while (S != 0) {

      List<Fish> copyFishList = getCopyFishList(fishList);

      List<Fish>[][] fishGrid = moveFishes(fishList, smellGrid, shark);

      List<Point> pointList = moveShark(fishGrid, shark);

      smellList = countDown(smellList);

      delete(fishList, fishGrid, pointList, smellList);

      smellGrid = getNewSmellGrid(smellList);

      //System.out.println(fishList.size());
      fishList.addAll(copyFishList);
      //System.out.println(fishList.size());
      //System.out.println();
      S--;
    }

    return fishList.size();
  }
  private static boolean[][] getNewSmellGrid(List<Smell> smellList) {

    boolean[][] newGrid = new boolean[5][5];

    for (Smell smell : smellList) {
        newGrid[smell.row][smell.col] = true;
    }

    return newGrid;
  }
  private static List<Smell> countDown(List<Smell> smellList) {

    List<Smell> list = new ArrayList<>();

    boolean[][] isSmellGrid = new boolean[5][5];
    for (int i = smellList.size() - 1; i >= 0; i--) {
      Smell smell = smellList.get(i);

      if (isSmellGrid[smell.row][smell.col] || --smell.count == 0) continue;

      list.addFirst(smell);
      isSmellGrid[smell.row][smell.col] = true;
    }

    return list;
  }
  private static void delete(List<Fish> fishList, List<Fish>[][] fishGrid, List<Point> pointList, List<Smell> smellList) {

    for (Point point : pointList) {

      //System.out.println("row: " + point.row + " col: " + point.col);
      int i = 0;
      for (; i < fishGrid[point.row][point.col].size(); i++) {
        Fish fish = fishGrid[point.row][point.col].get(i);

        fishList.remove(fish);
      }
      if (i != 0) {
        smellList.add(new Smell(point.row, point.col));
      }
    }
  }
  private static List<Point> moveShark(List<Fish>[][] fishGrid, Shark shark) {

    Queue<MovingShark> buffer = new ArrayDeque<>();
    MovingShark result = null;

    buffer.add(new MovingShark(shark.row, shark.col));
    while (!buffer.isEmpty()) {
      MovingShark curShark = buffer.poll();

      if (curShark.count == 3) {
        if (result == null) {
          result = curShark;
        } else {
          if (result.compareTo(curShark) > 0) result = curShark;
        }

        continue;
      }

      for (int i = 1; i <= 4; i++) {
        int nextRow = curShark.row + shark_dr[i];
        int nextCol = curShark.col + shark_dc[i];

        if (isValidPoint(nextRow, nextCol)) {
          Point nextPoint = new Point(nextRow, nextCol);
          if (curShark.count == 2 && isVisited(curShark.pointHistory, nextPoint)) {
            buffer.add(
                    new MovingShark(nextRow, nextCol, curShark.count + 1,
                            curShark.eatingPoint, curShark.directionHistory, curShark.pointHistory, i));
          } else {
            buffer.add(
                    new MovingShark(nextRow, nextCol, curShark.count + 1,
                            curShark.eatingPoint + fishGrid[nextRow][nextCol].size(), curShark.directionHistory, curShark.pointHistory, i));
          }
        }
      }
    }

    shark.row = result.row;
    shark.col = result.col;

    return result.pointHistory;
  }
  private static List<Fish>[][] moveFishes(List<Fish> fishList, boolean[][] smellGrid, Shark shark) {

    //System.out.println("Fish Move Result Print");
    List<Fish>[][] fishGrid = initFishGrid();
    for (Fish fish : fishList) {

      Integer[] nextPoint = getNextPoint(fish, smellGrid, shark);

      fish.row = nextPoint[0];
      fish.col = nextPoint[1];
      fish.direction = nextPoint[2];

      //System.out.println("row: " + fish.row + " col: " + fish.col);
      fishGrid[fish.row][fish.col].add(fish);
    }
    //System.out.println("===========End============");

    return fishGrid;
  }
  private static List<Fish>[][] initFishGrid() {

    List<Fish>[][] grid = new List[5][5];
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        grid[i][j] = new ArrayList<>();
      }
    }

    return grid;
  }
  private static Integer[] getNextPoint(Fish fish, boolean[][] smellGrid, Shark shark) {

    int direction = fish.direction;
    do {
      int nextRow = fish.row + dr[direction];
      int nextCol = fish.col + dc[direction];

      if (canMovePoint(nextRow, nextCol, smellGrid, shark)) {
        return new Integer[]{nextRow, nextCol, direction};
      }

      direction = getNextDirection(direction);
    } while (!(direction == fish.direction));

    return new Integer[]{fish.row, fish.col, fish.direction};
  }
  private static int getNextDirection(int direction) {
    return direction == 1 ? 8 : direction - 1;
  }
  private static boolean canMovePoint(int nextRow, int nextCol, boolean[][] smellGrid, Shark shark) {
    return isValidPoint(nextRow, nextCol) && !smellGrid[nextRow][nextCol] && !(shark.row == nextRow && shark.col == nextCol);
  }
  private static boolean isValidPoint(int row, int col) {
    return row >= 1 && col >= 1 && row <= 4 && col <= 4;
  }
  private static List<Fish> getCopyFishList(List<Fish> fishList) {

    List<Fish> copy = new ArrayList<>();

      for (Fish fish : fishList) {
          copy.add(new Fish(fish.row, fish.col, fish.direction));
      }

    return copy;
  }
  private static boolean isVisited(List<Point> pointList, Point nextPoint) {

    for (Point point : pointList) {
      if (nextPoint.equals(point)) return true;
    }

    return false;
  }
}
class Smell {
  int row;
  int col;
  int count;

  Smell(int row, int col) {
    this.row = row;
    this.col = col;
    this.count = 2;
  }
  Smell(int row, int col, int count) {
    this.row = row;
    this.col = col;
    this.count = count;
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
class Shark {
  int row;
  int col;

  Shark(int row, int col) {
    this.row = row;
    this.col = col;
  }
}
class MovingShark implements Comparable<MovingShark> {
  int row;
  int col;
  int count;
  int eatingPoint;
  List<Integer> directionHistory;
  List<Point> pointHistory;

  MovingShark(int row, int col) {
    this.row = row;
    this.col = col;
    this.count = 0;
    this.eatingPoint = 0;
    this.directionHistory = new ArrayList<>();
    this.pointHistory = new ArrayList<>();
    pointHistory.add(new Point(row, col));
  }

  MovingShark(int row, int col, int count, int eatingPoint, List<Integer> directionHistory, List<Point> pointHistory, int direction) {
    this.row = row;
    this.col = col;
    this.count = count;
    this.eatingPoint = eatingPoint;
    this.directionHistory = initDirectionHistory(directionHistory, direction);
    this.pointHistory = initPointHistory(pointHistory, row, col);
  }
  private List<Point> initPointHistory(List<Point> pointHistory, int row, int col) {

    List<Point> list = new ArrayList<>(pointHistory);
    list.add(new Point(row, col));

    return list;
  }

  private List<Integer> initDirectionHistory(List<Integer> directionHistory, int direction) {

    List<Integer> list = new ArrayList<>(directionHistory);
    list.add(direction);

    return list;
  }

  @Override
  public int compareTo(MovingShark o) {
    if (this.eatingPoint != o.eatingPoint) return o.eatingPoint - this.eatingPoint;
    else {
      for (int i = 0; i < 3; i++) {
        int directionIdx = this.directionHistory.get(i);
        int compareIdx = o.directionHistory.get(i);
        if (directionIdx != compareIdx) return directionIdx - compareIdx;
      }
    }
    return 0;
  }
}
class Point {
  int row;
  int col;

  Point (int row, int col) {
    this.row = row;
    this.col = col;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Point) {
      Point p = (Point) obj;

      return this.row == p.row && this.col == p.col;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.row, this.col);
  }
}