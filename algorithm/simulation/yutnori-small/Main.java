import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int tc = Integer.parseInt(st.nextToken());

    while(tc-- != 0) {
      st = new StringTokenizer(br.readLine());

      int canUseHorseCount = Integer.parseInt(st.nextToken());
      int yutCount = Integer.parseInt(st.nextToken());
      int AHorseCount = Integer.parseInt(st.nextToken());
      int BHorseCount = Integer.parseInt(st.nextToken());

      int[] yutArray = new int[yutCount];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < yutCount; i++) {
        yutArray[i] = convertYutString(st.nextToken());
      }

      Arrays.sort(yutArray);

      List<Integer[]> list = new ArrayList<>();
      getNumberOfCase(list, yutArray, new Integer[yutCount], new boolean[yutCount], 0, yutCount);


    }
  }
  private static void getNumberOfCase(List<Integer[]> list, int[] yutArray, Integer[] buffer, boolean[] isUsed, int ptr, int size) {

    if (ptr == size) {
      list.add(Arrays.copyOf(buffer, size));
      return;
    }

    for (int i = 0; i < size; i++) {
      if (isUsed[i] ||
              (!isUsed[i] && yutArray[i - 1] == yutArray[i] && !isUsed[i - 1])) continue;

      buffer[i] = yutArray[i];
      isUsed[i] = true;
      getNumberOfCase(list, yutArray, buffer, isUsed, ptr + 1, size);
      buffer[i] = 0;
      isUsed[i] = false;
    }
  }
  private static int convertYutString(String str) {

    if (str.equals("Do")) return 1;
    if (str.equals("Gae")) return 2;
    if (str.equals("Gal")) return 3;
    if (str.equals("Yut")) return 4;

    return 5;
  }
}
// 게임 진행하기
// Yut, Mo인 경우에는 Turn이 바뀌면 안됨
// 말을 잡는 경우에는 Yut, Mo 판정이랑 겹치더라도 한번만 더 던짐
// 그 외의 경우에는 말이 이동한 후에는 Turn이 바뀜
// 말이 이동할 때 이동하는 칸에 말이 있는지 없는지 체크
// 있다면 내 말인지 아닌지 체크 -> 업기 / 잡기 (1번 더)
// 말이 이동할 때 업혀있는 말인지 아닌지 체크 -> 암튼 전부 제거
// 말은 선택하는 과정에서 업혀있는 말은 굳이 선택 X
// 상대방 말을 잡는 경우 상대방 말 reset 시키기
// 모은 Array를 사용한 후에는 다음 Array가 있다면 reset시키기
class Game {

  private final Board board;
  private final User userA;
  private final User userB;
  private User thisTurnUser;
  private Integer[] yutArray;
  private int turnIdx;

  private Game (User userA, User userB) {
    this.board = new Board();
    this.userA = userA;
    this.userB = userB;
    this.thisTurnUser = userA;
    this.turnIdx = 0;
  }

  private static class SingletonHolder {
    private static final Game SINGLETON_GAME = new Game();
  }

  public Game getInstance() {
    return SingletonHolder.SINGLETON_GAME;
  }

  public void reset() {
    userA.reset(board.getHead());
    userB.reset(board.getHead());
    thisTurnUser = userA;
    this.yutArray = null;
  }

  public boolean isValidGame(Integer[] yutArray) {
    setYutArray(yutArray);


  }

  private void setYutArray(Integer[] yutArray) {
    this.yutArray = yutArray;
  }
  private void playThisTurn () {

    int steps = yutArray[turnIdx++];
    List<Horse> canChooseHorseList = thisTurnUser.canChooseHorseList(board, steps);

    for (int
  }
}
class Turn {
  private final Board board;
  private User thisTurnUser;
  private int turnIdx;

}
class Board {
  private final Point[] pointList;

  public Board() {
    this.pointList = initPointList();
  }

  private Point[] initPointList () {
    Point[] pointList = new Point[31];
    pointList[0] = new Point(0);
    for (int i = 1; i < 20; i++) {
      pointList[i] = new Point(i);
      pointList[i - 1].setNext(pointList[i - 1]);
    }

    pointList[20] = new Point(20);
    pointList[5].setShortCut(pointList[20]);

    for (int i = 21; i < 25; i++) {
      pointList[i] = new Point(i);
      pointList[i - 1].setNext(pointList[i - 1]);
    }

    pointList[25] = new Point(25);
    pointList[10].setShortCut(pointList[25]);

    pointList[26] = new Point(26);
    pointList[25].setNext(pointList[25]);

    pointList[30] = new Point(30);
    pointList[26].setNext(pointList[30]);

    pointList[27] = new Point(27);
    pointList[30].setNext(pointList[27]);
    pointList[22].setShortCut(pointList[27]);

    pointList[28] = new Point(28);
    pointList[27].setShortCut(pointList[28]);

    pointList[29] = new Point(29);
    pointList[28].setNext(pointList[29]);
    pointList[19].setNext(pointList[29]);

    pointList[30] = new Point(30);
    pointList[29].setNext(pointList[30]);

    return pointList;
  }

  public Point getHead() {
    return pointList[0];
  }

  public Point getTargetPoint(Point curPoint, Integer steps) {

    if (curPoint.isHasShortCut()) {
      curPoint = curPoint.getShortCut();
      steps--;
    }

    while (steps-- != 0 || !isEndPoint(curPoint)) {
      curPoint = curPoint.getNext();
    }

    return curPoint;
  }

  public boolean isEndPoint(Point point) {
    return point.getIdx() == 30;
  }
}
class Point {
  private final int idx;
  private Point next;
  private final boolean hasShortCut;
  private Point shortCut;
  private List<Horse> horses;

  Point (int idx) {
    this.idx = idx;
    this.hasShortCut = (idx == 5 || idx == 10 || idx == 22);
  }

  public int getIdx() {
    return idx;
  }

  public Point getNext() {
    return next;
  }

  public void setNext(Point nextPoint) {
    this.next = nextPoint;
  }

  public boolean isHasShortCut() {
    return hasShortCut;
  }

  public Point getShortCut() {
    return shortCut;
  }

  public void setShortCut(Point shortCut) {
    this.shortCut = shortCut;
  }

  public boolean isEmpty() {
    return horses.isEmpty();
  }

  public List<Horse> getHorses() {
    return horses;
  }

  public void removeHorse(Horse horse) {
    horses.remove(horse);
  }

  public void addHorse(Horse horse) {
    if (!isEmpty()) {
      horse.isPiggyBacking = true;
    }
    horses.add(horse);
  }

}
class User {
  Horse[] horses;

  User(int size) {
    horses = new Horse[size];
  }

  public List<Horse> canChooseHorseList(Board board, Integer steps) {
    List<Horse> list = new ArrayList<>();
      for (Horse horse : horses) {
          if (horse.isEnd || horse.isPiggyBacking) continue;

          Point targetPoint = board.getTargetPoint(horse.currentPosition, steps);
          if (!targetPoint.isEmpty()) {
            List<Horse> horses = targetPoint.getHorses();

            if(!horses.get(0).user.equals(horse.user)) continue;
          }
          list.add(horse);
      }

    return list;
  }


  public void reset(Point head) {
      for (Horse horse : horses) {
        horse.reset(head);
      }
  }
}
class Horse {
  Point currentPosition;
  User user;
  boolean isEnd;
  boolean isPiggyBacking;

  Horse (Point head, User user) {
    this.currentPosition = head;
    this.user = user;
    this.isEnd = false;
    this.isPiggyBacking = false;
  }

  public void reset(Point head) {
    this.currentPosition.removeHorse(this);
    this.isPiggyBacking = false;
    this.currentPosition = head;
    this.isEnd = false;
  }
}