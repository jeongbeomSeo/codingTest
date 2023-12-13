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

    List<Fish> fishList = new ArrayList<>();

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());

      Fish fish = new Fish(row, col, direction);
      fishList.add(fish);
    }


  }
  private static int simulation(List<Integer>[][] idxListGrid, List<Fish> fishList, int S) {

    boolean[][] isSmellGrid = new boolean[4 + 1][4 + 1];
    Queue<Smell> smellQueue = new ArrayDeque<>();

    while (S-- != 0) {

      Queue<Fish> copyFishBuffer = initCopyFishBuffer(fishList);



    }
  }
  private static List<Integer>[][] initIdxListGrid() {
    List<Integer>[][] idxListGrid = new List[5][5];
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        idxListGrid[i][j] = new ArrayList<>();
      }
    }

    return idxListGrid;
  }
  private static void moveAllFish(List<Integer>[][] idxListGrid, List<Fish> fishList, boolean[][] isSmellGrid) {

  }
  private static Queue<Fish> initCopyFishBuffer(List<Fish> fishList) {

    Queue<Fish> buffer = new ArrayDeque<>();
    for (int i = 0; i < fishList.size(); i++) {
      Fish curFish = fishList.get(i);
      Fish copyFish = new Fish(curFish.row, curFish.col, curFish.direction);

      buffer.add(copyFish);
    }

    return buffer;
  }
  private static boolean isValidPoint(int row, int col) {
    return row >= 1 && col >= 1 && row <= 4 && col <= 4;
  }
  private static List<Integer>[][] initIdxList(Fish[] fishList) {

    List<Integer>[][]
  }
}
class Situation {
  int[][] grid;



}
class MoveingShark {
  int row;
  int col;
  int movedCount;
  int eatingCount;
  List<Integer> directionList;

  MoveingShark(int row, int col, int movedCount, int eatingCount, List<Integer> directionList, int curMoveDirection) {
    this.row = row;
    this.col = col;
    this.movedCount = movedCount;
    this.eatingCount = eatingCount;
    this.directionList = initDirectionList(directionList, curMoveDirection);
  }
  private List<Integer> initDirectionList(List<Integer> directionList, int curMoveDirection) {
    List<Integer> list = new ArrayList<>(directionList);

    list.add(curMoveDirection);
    return list;
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
class Smell {
  int row;
  int col;
  int age;

  Smell(int row, int col) {
    this.row = row;
    this.col = col;
    this.age = 2;
  }
}
class Fish {
  private static int PRI_IDX = 1;
  int idx;
  int row;
  int col;
  int direction;

  Fish(int row, int col, int direction) {
    this.idx = PRI_IDX++;
    this.row = row;
    this.col = col;
    this.direction = direction;
  }
}