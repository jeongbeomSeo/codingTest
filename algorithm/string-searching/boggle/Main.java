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

    final Tree tree = new Tree();

    int w = Integer.parseInt(br.readLine());
    for (int i = 0; i < w; i++) {
      String str = br.readLine();

      tree.update(str);
    }

    // 띄어쓰기 제거
    br.readLine();

    int b = Integer.parseInt(br.readLine());
    while(b-- != 0) {

      char[][] board = new char[4][4];
      for (int i = 0; i < 4; i++) {
        String str = br.readLine();

        for (int j = 0; j < 4; j++) {
          board[i][j] = str.charAt(j);
        }
      }

      String output = query(tree, board);

      System.out.println(output);

      // 띄어쓰기 제거
      if (b != 0) {
        br.readLine();
      }
    }
  }
  private static String query(Tree tree, char[][] board) {

    Set<String> result = new TreeSet<>(new CustomStringComparator());

    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 4; col++) {
        if (tree.root.childNode.containsKey(board[row][col])) {
          Set<String> dfsResult = dfs(tree.root.childNode.get(board[row][col]), board, row, col);

          result.addAll(dfsResult);
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    sb.append(getMaxScore(result) + " ");
    sb.append(result.toArray()[0] + " ");
    sb.append(result.size());

    return sb.toString();
  }
  private static int getMaxScore(Set<String> result) {
    int score = 0;

    for (String str : result) {
      score += getScore(str.length());
    }

    return score;
  }
  private static int getScore(int strLen) {
    if (strLen == 1 || strLen == 2) return 0;
    else if (strLen == 3 || strLen == 4) return 1;
    else if (strLen == 5) return 2;
    else if (strLen == 6) return 3;
    else if (strLen == 7) return 5;

    return 11;
  }
  private static Set<String> dfs(Node initNode, char[][] board, int initRow, int initCol) {

    Queue<Status> q = new ArrayDeque<>();
    boolean[][] isVisited = new boolean[4][4];
    isVisited[initRow][initCol] = true;
    q.add(new Status(initRow, initCol, isVisited, board[initRow][initCol], initNode));

    Set<String> result = new HashSet<>();
    while (!q.isEmpty()) {
      Status curStatus = q.poll();

      if (curStatus.node.isEnd) {
        result.add(curStatus.str);
      }

      for (int i = 0; i < 8; i++) {
        int nxtRow = curStatus.row + DR[i];
        int nxtCol = curStatus.col + DC[i];

        if (isValidPoint(nxtRow, nxtCol)
                && !curStatus.isVisited[nxtRow][nxtCol]
                && curStatus.node.childNode.containsKey(board[nxtRow][nxtCol])) {

          char curChar = board[nxtRow][nxtCol];
          boolean[][] nxtIsVisited = copyBooleanGrid(curStatus.isVisited);
          nxtIsVisited[nxtRow][nxtCol] = true;
          q.add(new Status(nxtRow, nxtCol, nxtIsVisited,
                  curStatus.str + curChar, curStatus.node.childNode.get(curChar)));
        }
      }
    }

    return result;
  }
  private static boolean[][] copyBooleanGrid(boolean[][] grid) {
    int rowLen = grid.length;
    int colLen = grid[0].length;
    boolean[][] copy = new boolean[rowLen][colLen];

    for (int i = 0; i < rowLen; i++) {
      for (int j = 0; j < colLen; j++) {
        copy[i][j] = grid[i][j];
      }
    }

    return copy;
  }
  private static boolean isValidPoint(int row, int col) {
    return row >= 0 && col >= 0 && row < 4 && col < 4;
  }
}
class Status {
  int row;
  int col;
  boolean[][] isVisited;
  String str;
  Node node;

  Status(int row, int col, boolean[][] isVisited, char initChar, Node node) {
    this.row = row;
    this.col = col;
    this.isVisited = isVisited;
    str = "" + initChar;
    this.node = node;
  }

  Status(int row, int col, boolean[][] isVisited, String str, Node node) {
    this.row = row;
    this.col = col;
    this.isVisited = isVisited;
    this.str = str;
    this.node = node;
  }


}
class CustomStringComparator implements Comparator<String> {
  @Override
  public int compare(String o1, String o2) {
    if (o1.length() != o2.length()) return o2.length() - o1.length();
    else {
      for (int i = 0; i < o1.length(); i++) {
        char c1 = o1.charAt(i);
        char c2 = o2.charAt(i);

        if (c1 != c2) return c1 - c2;
      }
    }
    return 0;
  }
}
class Tree {
  final Node root = new Node();

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

  Node() {
    childNode = new HashMap<>();
    value = ' ';
    isEnd = false;
  }
  Node(char value) {
    childNode = new HashMap<>();
    value = value;
    isEnd = false;
  }
}