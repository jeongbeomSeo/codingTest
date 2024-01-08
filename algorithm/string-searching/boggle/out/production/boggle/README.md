# Boggle

**플래티넘 5**

|시간 제한	|메모리 제한|	제출	|정답|	맞힌 사람|	정답 비율|
|---|---|---|---|---|---|
|10 초	|512 MB|	6449|	1797|	1020	|25.836%|

## 문제 

상근이는 보드 게임 "Boggle"을 엄청나게 좋아한다. Boggle은 글자가 쓰여 있는 주사위로 이루어진 4×4 크기의 그리드에서 최대한 많은 단어를 찾는 게임이다.

상근이는 한 번도 부인을 Boggle로 이겨본 적이 없다. 이렇게 질 때마다 상근이는 쓰레기 버리기, 설거지와 같은 일을 해야 한다. 이제 상근이는 프로그램을 작성해서 부인을 이겨보려고 한다.

Boggle에서 단어는 인접한 글자(가로, 세로, 대각선)를 이용해서 만들 수 있다. 하지만, 한 주사위는 단어에 한 번만 사용할 수 있다. 단어는 게임 사전에 등재되어 있는 단어만 올바른 단어이다.

1글자, 2글자로 이루어진 단어는 0점, 3글자, 4글자는 1점, 5글자는 2점, 6글자는 3점, 7글자는 5점, 8글자는 11점이다. 점수는 자신이 찾은 단어에 해당하는 점수의 총 합이다.

단어 사전에 등재되어 있는 단어의 목록과 Boggle 게임 보드가 주어졌을 때, 얻을 수 있는 최대 점수, 가장 긴 단어, 찾은 단어의 수를 구하는 프로그램을 작성하시오.

## 입력

첫째 줄에 단어 사전에 들어있는 단어의 수 w가 주어진다. (1 < w < 300,000) 다음 w개 줄에는 단어가 주어진다. 단어는 최대 8글자이고, 알파벳 대문자로만 이루어져 있다. 단어 사전에 대한 정보가 모두 주어진 이후에는 빈 줄이 하나 주어진다.

그 다음에는 Boggle 보드의 개수 b가 주어진다. (1 < b < 30) 모든 Boggle은 알파벳 대문자로 이루어져 있고, 4줄에 걸쳐 주어진다. 각 Boggle의 사이에는 빈 줄이 하나  있다.

## 출력

각각의 Boggle에 대해, 얻을 수 있는 최대 점수, 가장 긴 단어, 찾은 단어의 개수를 출력한다. 한 Boggle에서 같은 단어를 여러 번 찾은 경우에는 한 번만 찾은 것으로 센다. 가장 긴 단어가 여러 개인 경우에는 사전 순으로 앞서는 것을 출력한다. 각 Boggle에 대해서 찾을 수 있는 단어가 적어도 한 개인 경우만 입력으로 주어진다.

## 예제 입력 1

```
5
ICPC
ACM
CONTEST
GCPC
PROGRAMM

3
ACMA
APCA
TOGI
NEST

PCMM
RXAI
ORCN
GPCG

ICPC
GCPC
ICPC
GCPC
```

## 예제 출력 1

```
8 CONTEST 4
14 PROGRAMM 4
2 GCPC 2
```

## 나의 코드


**CE(Compile Error)**
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static boolean find(char[][] grid, boolean[][] isVisitied, String txt, int idx, int y, int x, int txtLen) {

    if(idx == txtLen) return true;

    boolean success = false;

    for(int i = y - 1; i <= y + 1; i++) {
      if(i < 0 || i > 3) continue;

      for(int j = x - 1; j <= x + 1; j++) {
        if(j < 0 || j > 3 || (i == y && j == x)) continue;

        if(!isVisitied[i][j]) {
          if(grid[i][j] == txt.charAt(idx)){
            isVisitied[i][j] = true;
            success = find(grid, isVisitied, txt, idx + 1, i, j, txtLen);
            isVisitied[i][j] = false;
          }
        }
        if(success) return true;
      }
    }
    return false;
  }

  static int calcScore(int txtLen) {
    switch (txtLen) {
      case 1, 2:
        return 0;
      case 3, 4:
        return 1;
      case 5:
        return 2;
      case 6:
        return 3;
      case 7:
        return 5;
      case 8:
        return 11;
    }
    return 0;
  }

  static String maxLengthString(String maxLengthStr, String txt) {
    int maxLength = maxLengthStr.length();
    int txtLen = txt.length();
    if(maxLength == txtLen) {
      for(int i = 0; i < maxLength; i++) {
        if(maxLengthStr.charAt(i) > txt.charAt(i)) {
          return txt;
        } else if(maxLengthStr.charAt(i) == txt.charAt(i)) continue;
        else
          return maxLengthStr;
      }
    }
    if(maxLength < txtLen) return txt;
    else return maxLengthStr;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int w = Integer.parseInt(br.readLine());

    String[] dictionary = new String[w];

    for(int i = 0 ; i < w; i++)
      dictionary[i] = br.readLine();

    br.readLine();

    int boardNum = Integer.parseInt(br.readLine());
    for(int board = 0; board < boardNum; board++) {

      char[][] grid = new char[4][4];
      for(int i = 0; i < 4; i++) {
        String txts = br.readLine();
        for(int j = 0; j < 4; j++)
          grid[i][j] = txts.charAt(j);
      }

      int maxScore = 0;
      String maxLengthStr = "";
      int findString = 0;

      for(int dict = 0; dict < dictionary.length; dict++) {

        String txt = dictionary[dict];
        int txtLen = txt.length();
        char startChar = txt.charAt(0);

        boolean success = false;
        for(int i = 0; i < 4; i++) {
          for(int j = 0; j < 4; j++) {
            if(grid[i][j] == startChar) {
              boolean[][] isVisitied = new boolean[4][4];
              isVisitied[i][j] = true;
              success = find(grid, isVisitied, txt, 0 + 1, i, j, txtLen);
              if(success) break;
            }
          }
          if(success) break;
        }
        // 성공 시
        if(success) {
          findString++;
          maxScore += calcScore(txtLen);
          maxLengthStr = maxLengthString(maxLengthStr, txt);
        }
      }
      System.out.println(maxScore + " " + maxLengthStr + " " + findString);

      if(board != boardNum - 1) br.readLine();
    }
  }
}

```

**AC**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static boolean find(char[][] grid, boolean[][] isVisitied, String txt, int idx, int y, int x, int txtLen) {

    if(idx == txtLen) return true;

    boolean success = false;

    for(int i = y - 1; i <= y + 1; i++) {
      if(i < 0 || i > 3) continue;

      for(int j = x - 1; j <= x + 1; j++) {
        if(j < 0 || j > 3 || (i == y && j == x)) continue;

        if(!isVisitied[i][j]) {
          if(grid[i][j] == txt.charAt(idx)){
            isVisitied[i][j] = true;
            success = find(grid, isVisitied, txt, idx + 1, i, j, txtLen);
            isVisitied[i][j] = false;
          }
        }
        if(success) return true;
      }
    }
    return false;
  }

  static int calcScore(int txtLen) {
    switch (txtLen) {
      case 1:
      case 2:
        return 0;
      case 3:
      case 4:
        return 1;
      case 5:
        return 2;
      case 6:
        return 3;
      case 7:
        return 5;
      case 8:
        return 11;
    }
    return 0;
  }

  static String maxLengthString(String maxLengthStr, String txt) {
    int maxLength = maxLengthStr.length();
    int txtLen = txt.length();
    if(maxLength == txtLen) {
      for(int i = 0; i < maxLength; i++) {
        if(maxLengthStr.charAt(i) > txt.charAt(i)) {
          return txt;
        } else if(maxLengthStr.charAt(i) == txt.charAt(i)) continue;
        else
          return maxLengthStr;
      }
    }
    if(maxLength < txtLen) return txt;
    else return maxLengthStr;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int w = Integer.parseInt(br.readLine());

    String[] dictionary = new String[w];

    for(int i = 0 ; i < w; i++)
      dictionary[i] = br.readLine();

    br.readLine();

    int boardNum = Integer.parseInt(br.readLine());
    for(int board = 0; board < boardNum; board++) {

      char[][] grid = new char[4][4];
      for(int i = 0; i < 4; i++) {
        String txts = br.readLine();
        for(int j = 0; j < 4; j++)
          grid[i][j] = txts.charAt(j);
      }

      int maxScore = 0;
      String maxLengthStr = "";
      int findString = 0;

      for(int dict = 0; dict < dictionary.length; dict++) {

        String txt = dictionary[dict];
        int txtLen = txt.length();
        char startChar = txt.charAt(0);

        boolean success = false;
        for(int i = 0; i < 4; i++) {
          for(int j = 0; j < 4; j++) {
            if(grid[i][j] == startChar) {
              boolean[][] isVisitied = new boolean[4][4];
              isVisitied[i][j] = true;
              success = find(grid, isVisitied, txt, 0 + 1, i, j, txtLen);
              if(success) break;
            }
          }
          if(success) break;
        }
        // 성공 시
        if(success) {
          findString++;
          maxScore += calcScore(txtLen);
          maxLengthStr = maxLengthString(maxLengthStr, txt);
        }
      }
      System.out.println(maxScore + " " + maxLengthStr + " " + findString);

      if(board != boardNum - 1) br.readLine();
    }
  }
}

```

**잘못된 중첩 반복문 사용 문제 코드**

사실 이것을 해결한다고 해도 잘못된 풀이다.

문제에서는 한단어에 주사위 한번만 사용가능하고, 추가적으로 여러 단어에서 반복적으로 주사위를 사용할 수 있다.

결국, 또 문제를 잘못 읽은 것이다.

| 예제를 직접 확인하고 풀어보고 코드를 짜는 습관을 들이자.

```java
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

        for (int i = 0; i < resultList.size(); i++) {
          Result curResult = resultList.get(i);

          if (!curResult.isVisited[row][col] && tree.root.childNode.containsKey(board[row][col])) {
            // dfs할 때 어떤 Node를 건네주냐에 따라서 root Node를 건네줄지, root의 child Node를 건네주는지 바뀜.
            List<Status> dfsResult = dfs(tree.root.childNode.get(board[row][col]), board, row, col, curResult.isVisited);

            for (int k = 0; k < dfsResult.size(); k++) {
              Status curDfsResult = dfsResult.get(k);
              // 반복문 돌리고 있는 target List에 요소를 추가할 경우 생각치 못한 로직으로 돌아갈 가능성이 높음 매우 주의
              resultList.add(new Result(
                      curResult.findedStrList, curDfsResult.curStr, curDfsResult.isVisited, curResult.score + getScore(curDfsResult.curStr)));
            }
          }
        }
      }
    }

    Collections.sort(resultList);
    Result finalResult = resultList.get(0);

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
```