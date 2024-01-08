import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  // 8방향: size: 8
  private static final int[] DR = {-1, -1, 0, 1, 1, 1, 0, -1};
  private static final int[] DC = {0, 1, 1, 1, 0, -1, -1, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int w = Integer.parseInt(br.readLine());

    final Tree tree = new Tree();
    for (int i = 0; i < w; i++) {
      String str = br.readLine();

      tree.update(str);
    }

    // 띄어쓰기 제거
    br.readLine();
    int b = Integer.parseInt(br.readLine());
    while (b-- != 0) {

      char[][] board = new char[4][4];
      for (int i = 0; i < 4; i++) {
        String str = br.readLine();

        for (int j = 0; j < 4; j++) {
          board[i][j] = str.charAt(j);
        }
      }

      String result = getResult(tree, board);

      System.out.println(result);

      // 띄어쓰기 제거
      if (b != 0) {
        br.readLine();
      }
    }

  }
  private static String getResult(Tree tree, char[][] board) {

    List<Result> resultList = new ArrayList<>();

    // Size: 1부터 시작
    resultList.add(new Result("", new boolean[4][4],0));

    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 4; col++) {

        Queue<Result> buffer = new ArrayDeque<>();
        for (int i = 0; i < resultList.size(); i++) {
          Result curResult = resultList.get(i);

          if (!curResult.isVisited[row][col] && tree.root.childNode.containsKey(board[row][col])) {
            // dfs할 때 어떤 Node를 건네주냐에 따라서 root Node를 건네줄지, root의 child Node를 건네주는지 바뀜.
            List<Status> dfsResult = dfs(tree.root.childNode.get(board[row][col]), board, row, col, curResult.isVisited);

            for (int k = 0; k < dfsResult.size(); k++) {
              Status curDfsResult = dfsResult.get(k);
              // 반복문 돌리고 있는 target List에 요소를 추가할 경우 생각치 못한 로직으로 돌아갈 가능성이 높음 매우 주의
              buffer.add(new Result(
                      curResult.findedStrList, curDfsResult.curStr, curDfsResult.isVisited, curResult.score + getScore(curDfsResult.curStr)));
            }
          }
        }
        while (!buffer.isEmpty()) resultList.add(buffer.poll());
      }
    }

    Collections.sort(resultList);
    Result finalResult = resultList.get(0);
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        System.out.print(finalResult.isVisited[i][j] ? "1" : "0");
      }
      System.out.println();
    }
    StringBuilder sb = new StringBuilder();
    sb.append(finalResult.score + " ");

    Collections.sort(finalResult.findedStrList, new CustomStringCompartor());
    sb.append(finalResult.findedStrList.get(0) + " ");
    sb.append(finalResult.findedStrList.size() - 1);

    return sb.toString();
  }
  private static int getScore(String value) {
    int len = value.length();

    if (len == 1 || len == 2) return 0;
    else if (len == 3 || len == 4) return 1;
    else if (len == 5) return 2;
    else if (len == 6) return 3;
    else if (len == 7) return 5;
    else if (len == 8) return 11;

    System.out.println("getScore 로직 오류");
    return 0;
  }
  private static List<Status> dfs(Node initNode, char[][] board, int initRow, int initCol, boolean[][] initIsVisited) {

    Queue<Status> q = new ArrayDeque<>();
    Point initPoint = new Point(initRow, initCol);
    boolean[][] newIsVisited = copyBooleanGrid(initIsVisited);
    newIsVisited[initRow][initCol] = true;
    q.add(new Status(initPoint, "" + board[initRow][initCol], newIsVisited, initNode));

    List<Status> result = new ArrayList<>();
    while (!q.isEmpty()) {
      Status curStatus = q.poll();

      if (curStatus.curNode.isEnd) {
        result.add(curStatus);
      }

      for (int i = 0; i < 8; i++) {
        int nxtRow = curStatus.point.row + DR[i];
        int nxtCol = curStatus.point.col + DC[i];

        if (isValidPoint(nxtRow, nxtCol)
                && !curStatus.isVisited[nxtRow][nxtCol]
                && curStatus.curNode.childNode.containsKey(board[nxtRow][nxtCol])) {
          char nxtKey = board[nxtRow][nxtCol];
          boolean[][] nxtIsVisited = copyBooleanGrid(curStatus.isVisited);
          nxtIsVisited[nxtRow][nxtCol] = true;
          q.add(new Status(new Point(nxtRow, nxtCol), curStatus.curStr + nxtKey,
                  nxtIsVisited, curStatus.curNode.childNode.get(nxtKey)));
        }
      }
    }

    return result;
  }
  private static boolean isValidPoint(int row, int col) {
    return row >= 0 && col >= 0 && row < 4 && col < 4;
  }
  private static boolean[][] copyBooleanGrid(boolean[][] origin) {
    int rowLen = origin.length;
    int colLen = origin[0].length;
    boolean[][] copy = new boolean[rowLen][colLen];

    for (int i = 0; i < rowLen; i++) {
      for (int j = 0; j < colLen; j++) {
        copy[i][j] = origin[i][j];
      }
    }

    return copy;
  }
}
class CustomStringCompartor implements Comparator<String> {
  @Override
  public int compare(String o1, String o2) {
    int len1 = o1.length();
    int len2 = o2.length();

    if (len1 != len2) return len2 - len1;
    else {
      for (int i = 0; i < len1; i++) {
        char c1 = o1.charAt(i);
        char c2 = o2.charAt(i);

        if (c1 != c2) return c1 - c2;
      }
    }
    return 0;
  }
}
class Result implements Comparable<Result>{
  List<String> findedStrList;
  boolean[][] isVisited;
  int score;

  Result(String value, boolean[][] isVisited, int score) {
    this.findedStrList = new ArrayList<>();
    this.findedStrList.add(value);
    this.isVisited = isVisited;
    this.score = score;
  }

  Result(List<String> prevFindedStrList, String value, boolean[][] isVisited, int score) {
    this.findedStrList = new ArrayList<>(prevFindedStrList);
    findedStrList.add(value);
    this.isVisited = isVisited;
    this.score = score;
  }

  @Override
  public int compareTo(Result o) {
    // 점수가 같은 결과가 여러 개인 경우는 주어져 있지 않음.
    return o.score - this.score;
  }
}
class Status {
  Point point;
  String curStr;
  boolean[][] isVisited;
  Node curNode;

  // 외부에서 전부 initializing 해서 건네주기
  Status(Point point, String curStr, boolean[][] isVisted, Node curNode) {
    this.point = point;
    this.curStr = curStr;
    this.isVisited = isVisted;
    this.curNode = curNode;
  }

/*  private List<Point> initVisitedHistory(Point point) {
    List<Point> visitHistory = new ArrayList<>();

    visitHistory.add(point);
    return visitHistory;
  }*/

/*  private boolean[][] initIsVisited(Point point) {
    boolean[][] isVisited = new boolean[4][4];

    isVisited[point.row][point.col] = true;
    return isVisited;
  }*/

}
class Point {
  int row;
  int col;

  Point(int row, int col) {
    this.row = row;
    this.col = col;
  }

  @Override
  public int hashCode() {
    return row * 39 + col;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Point) {
      Point p = (Point) obj;

      return this.row == p.row && this.col == p.col;
    }
    return false;
  }
}
class Tree {
  final Node root = new Node(' ');

  public void update(String str) {
    Node curNode = root;

    for (int i = 0; i < str.length(); i++) {
      char curChar = str.charAt(i);

      curNode = curNode.childNode.computeIfAbsent(curChar, key -> new Node(curChar));
    }

    curNode.isEnd = true;
  }
}
class Node {
  Map<Character, Node> childNode;
  char value;
  boolean isEnd;

  Node(char value) {
    this.value = value;
    this.childNode = new HashMap<>();
    isEnd = false;
  }
}