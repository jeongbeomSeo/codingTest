import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int MAX_SCORE = Integer.MIN_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] dice = new int[10];
    for (int i = 0; i < 10; i++) {
      dice[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(simulation(dice));
  }
  private static int simulation(int[] dice) {

    Board board = Board.getInstance();
    Horse[] horses = initHorseTable(board.pointList[0]);

    backtracking(dice, 0, horses, board, 0);

    return MAX_SCORE;
  }
  private static Horse[] initHorseTable(Point head) {

    Horse[] horses = new Horse[4];
    for (int i = 0; i < 4; i++) {
      horses[i] = new Horse(head);
    }

    return horses;
  }
  private static void backtracking(int[] dice, int ptr, Horse[] horses, Board board, int score) {

    if (ptr == 10) {
      MAX_SCORE = Math.max(MAX_SCORE, score);
      return;
    }

    for (int i = 0; i < 4; i++) {
      if (!horses[i].isEnd() &&
              (horses[i].getCurrentPosition().idx != 0 || (i == 0 || horses[i - 1].getCurrentPosition().idx != 0))) {

        int backupIdx = horses[i].getCurrentPosition().idx;
        if (moveHorse(board, horses[i], dice[ptr])) {
          backtracking(dice, ptr + 1, horses, board, score + getPoint(horses[i]));
          backup(board, horses[i], backupIdx);
        }
      }
    }

  }
  private static void backup(Board board, Horse horse, int backupIdx) {

    horse.getCurrentPosition().removeHorse(horse);
    horse.backupPoint(board.pointList, backupIdx);
    horse.getCurrentPosition().addHorse(horse);
  }
  private static boolean moveHorse(Board board, Horse horse, int steps) {

    int curIdx = horse.getCurrentPosition().idx;

    int nextIdx = board.getNextPointIdx(curIdx, steps);

    if (board.pointList[nextIdx].hasHorse()) return false;

    board.pointList[curIdx].removeHorse(horse);
    horse.move(steps);
    board.pointList[horse.getCurrentPosition().idx].addHorse(horse);

    return true;
  }
  private static int getPoint(Horse horse) {
    return horse.getCurrentPosition().score;
  }
}
class Horse {
  private Point currentPosition;
  private boolean isEnd;

  Horse (Point head) {
    this.currentPosition = head;
  }
  public void move(int steps) {
    if (currentPosition.hasShortPath) {
      currentPosition = currentPosition.shortPath;
      steps--;
    }

    while(currentPosition.idx != 33 && steps-- != 0) {
      currentPosition = currentPosition.next;
    }

    if (currentPosition.idx == 33) this.isEnd = true;
  }

  public Point getCurrentPosition() {
    return currentPosition;
  }

  public boolean isEnd() {
    return isEnd;
  }
  public void backupPoint(Point[] pointList, int backupIdx) {
    this.currentPosition = pointList[backupIdx];
    isEnd = false;
  }
}
class Board {
  Point[] pointList = new Point[34];

  private Board() {
    pointList[0] = new Point(0, 0);

    for (int i = 1; i <= 20; i++) {
      pointList[i] = new Point(i, 2 * i);
      pointList[i - 1].next = pointList[i];
    }

    pointList[21] = new Point(21, 13);
    pointList[5].shortPath = pointList[21];

    pointList[22] = new Point(22, 16);
    pointList[21].next = pointList[22];

    pointList[23] = new Point(23, 19);
    pointList[22].next = pointList[23];

    // 중앙 Point
    pointList[24] = new Point(24, 25);
    pointList[23].next = pointList[24];

    pointList[25] = new Point(25, 22);
    pointList[10].shortPath = pointList[25];

    pointList[26] = new Point(26, 24);
    pointList[25].next = pointList[26];
    pointList[26].next = pointList[24];

    pointList[27] = new Point(27, 28);
    pointList[15].shortPath = pointList[27];

    pointList[28] = new Point(28, 27);
    pointList[27].next = pointList[28];

    pointList[29] = new Point(29, 26);
    pointList[28].next = pointList[29];
    pointList[29].next = pointList[24];

    pointList[30] = new Point(30, 30);
    pointList[24].next = pointList[30];

    pointList[31] = new Point(31, 35);
    pointList[30].next = pointList[31];
    pointList[31].next = pointList[20];

    pointList[32] = new Point(32, 0);
    pointList[20].next = pointList[32];

    pointList[33] = new Point(33, 0);
    pointList[32].next = pointList[33];
  }

  private static class ClassHolder {
    private static final Board INSTANCE = new Board();
  }

  public int getNextPointIdx(int startIdx, int steps) {

    int curIdx = startIdx;
    if (pointList[curIdx].hasShortPath) {
      curIdx = pointList[curIdx].shortPath.idx;
      steps--;
    }

    while (curIdx != 33 && steps-- != 0) {
      curIdx = pointList[curIdx].next.idx;
    }

    return curIdx;
  }

  public static Board getInstance() {
    return ClassHolder.INSTANCE;
  }
}
class Point {
  int idx;
  int score;
  Point next;
  Point shortPath;
  boolean hasShortPath;
  List<Horse> horsesOnPoint;

  Point (int idx, int score) {
    this.idx = idx;
    this.score = score;
    this.hasShortPath = (idx == 5 || idx == 10 || idx == 15);
    this.horsesOnPoint = new ArrayList<>();
  }

  public boolean hasHorse() {
    return idx != 33 && !horsesOnPoint.isEmpty();
  }

  public void addHorse(Horse horse) {
    horsesOnPoint.add(horse);
  }

  public void removeHorse(Horse horse) {
      horsesOnPoint.remove(horse);
  }
}