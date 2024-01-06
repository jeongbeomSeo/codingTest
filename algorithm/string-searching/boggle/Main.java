import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  // size: 8 (8 방향)
  private static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
  private static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
  private static int maxScore = Integer.MIN_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int w = Integer.parseInt(br.readLine());

    Tree tree = new Tree();
    for (int i = 0; i < w; i++) {
      String str = br.readLine();

      tree.update(str);
    }

    // Enter 넘기기
    br.readLine();

    int b = Integer.parseInt(br.readLine());
    while (b-- != 0) {
      char[][] grid = new char[4][4];
      for (int i = 0; i < 4; i++) {
        String str = br.readLine();
        for (int j = 0; j < 4; j++) {
          grid[i][j] = str.charAt(j);
        }
      }

      // DFS
      allDfs(tree, grid);
      if (b != 0) {
        br.readLine();
      }
    }
  }
  private static int getScore(int length) {
    if (length == 1 || length == 2) return 0;
    else if (length == 3 || length == 4) return 1;
    else if (length == 5) return 2;
    else if (length == 6) return 3;
    else if (length == 7) return 5;
    else if (length == 8) return 11;

    System.out.println("로직 오류: getScore 범위 넘어섬");
    return 0;
  }
  private static void allDfs(Tree tree, char[][] grid) {

    Set<Info> result = new TreeSet<>(new InfoComparator());
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        List<Info> dfsResult = dfs(tree, grid, i, j);

        for (Info info : dfsResult) {
          result.add(info);
        }
      }
    }

    int size = result.size();
    List<Info> reusltList = new ArrayList<>(result);
    String strLongestLength = reusltList.get(0).str;
    maxScore = Integer.MIN_VALUE;
    getMaxScore(reusltList, new boolean[4][4], 0, size, 0);

    System.out.println(maxScore + " " + strLongestLength + " " + size);
  }
  private static void getMaxScore(List<Info> list, boolean[][] flag, int ptr, int size, int score) {
    if (ptr == size) {
      maxScore = Math.max(maxScore, score);
      return;
    }

    Info curInfo = list.get(ptr);

    if (compareBooleanTable(flag, curInfo.isVisited)) {
      boolean[][] newBooleanTable = copyBooleanTalbe(flag, curInfo.isVisited);
      getMaxScore(list, newBooleanTable, ptr + 1, size, score + getScore(curInfo.str.length()));
    }

    getMaxScore(list, flag, ptr + 1, size, score);
  }
  private static boolean[][] copyBooleanTalbe(boolean[][] b1, boolean[][] b2) {
    boolean[][] newBooleanTable = new boolean[4][4];

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (b1[i][j] || b2[i][j]) {
          newBooleanTable[i][j] = true;
        }
      }
    }

    return newBooleanTable;
  }
  private static boolean compareBooleanTable(boolean[][] b1, boolean[][] b2) {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (b1[i][j] && b2[i][j]) {
          return false;
        }
      }
    }
    return true;
  }
  private static List<Info> dfs(Tree tree, char[][] grid, int initRow, int initCol) {

    Queue<Info> q = new ArrayDeque<>();
    q.add(new Info(tree.root, initRow, initCol));

    List<Info> resultBuffer = new ArrayList<>();
    while (!q.isEmpty()) {
      Info curInfo = q.poll();

      if (curInfo.curNode.isEnd) {
        resultBuffer.add(curInfo);
      }

      for (int i = 0; i < 8; i++) {
        int nxtRow = curInfo.row + dr[i];
        int nxtCol = curInfo.col + dc[i];

        if (isValidPoint(nxtRow, nxtCol)) {
          char curChar = grid[nxtRow][nxtCol];

          if (!curInfo.isVisited[nxtRow][nxtCol]
                  && curInfo.curNode.childNode.containsKey(curChar)) {
            boolean[][] nxtIsVisited = copyIsVisited(curInfo.isVisited, nxtRow, nxtCol);
            Node nxtNode = curInfo.curNode.childNode.get(curChar);

            q.add(new Info(curInfo.str, nxtIsVisited, nxtNode, curChar, nxtRow, nxtCol));
          }
        }
      }
    }

    return resultBuffer;
  }
  private static boolean[][] copyIsVisited(boolean[][] isVisited, int row, int col) {
    boolean[][] copy = new boolean[4][4];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        copy[i][j] = isVisited[i][j];
      }
    }

    copy[row][col] = true;
    return copy;
  }
  private static boolean isValidPoint(int row, int col) {
    return row >= 0 && col >= 0 && row < 4 && col < 4;
  }
}

class InfoComparator implements Comparator<Info> {
  @Override
  public int compare(Info o1, Info o2) {
    String str1 = o1.str;
    String str2 = o2.str;
    if (str1.length() != str2.length()) {
      return str2.length() - str1.length();
    } else {
      for (int i = 0; i < str1.length(); i++) {
        char c1 = str1.charAt(i);
        char c2 = str2.charAt(i);
        if (c1 != c2) return c1 - c2;
      }
    }
    return 0;
  }
}
class Info {
  String str;
  boolean[][] isVisited;
  Node curNode;
  int row;
  int col;

  Info(Node root, int initRow, int initCol) {
    str = "";
    isVisited = initIsVisitedTable(initRow, initCol);
    curNode = root;
    row = initRow;
    col = initCol;
  }

  Info(String prevStr, boolean[][] copyIsVisited, Node nxtNode, char c, int nxtRow, int nxtCol) {
    str = prevStr + c;
    isVisited = copyIsVisited;
    curNode = nxtNode;
    row = nxtRow;
    col = nxtCol;
  }

  private boolean[][] initIsVisitedTable(int initRow, int initCol) {
    boolean[][] isVisted = new boolean[4][4];

    isVisted[initRow][initCol] = true;
    return isVisted;
  }

  @Override
  public int hashCode() {
    return str.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Info comp) {
      for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
          if (this.isVisited[i][j] != comp.isVisited[i][j]) {
            return false;
          }
        }
      }
      return true;
    }
    return false;
  }
}
class Tree {
  final Node root = new Node();

  public void update(String str) {
    Node curNode = root;

    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);

      curNode = curNode.childNode.computeIfAbsent(c, key -> new Node());
    }
    curNode.isEnd = true;
  }
}
class Node {
  Map<Character, Node> childNode;
  boolean isEnd;

  Node () {
    childNode = new HashMap<>();
    isEnd = false;
  }
}