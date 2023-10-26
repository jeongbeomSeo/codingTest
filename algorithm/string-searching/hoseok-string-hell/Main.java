import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Node {
  static Node root;
  static Node current;

  Map<Character, Node> childNode = new HashMap<>();
  boolean isTerminal;
  int caseCount;

  Node() {
    if(root == null) root = this;
    isTerminal = false;
    caseCount = 0;
  }

  int insert(String txt) {
    Node curNode = root;
    for(int i = 0; i < txt.length(); i++) {
      curNode = curNode.childNode.computeIfAbsent(txt.charAt(i), key -> new Node());
    }
    if(curNode.isTerminal) return curNode.caseCount;
    else {
      current = curNode;
      curNode.isTerminal = true;
      return 0;
    }
  }
}

public class Main {
  static int N;
  static int M;

  static int count;
  static char[][] grid;

  /*
  static int modifyIdx(int idx, int length) {
    if(idx == length) return 0;
    else if(idx < 0) return length - 1;
    else return idx;
  }
  */

  static void check(String txt, int idx, int y, int x, int length) {
    if(idx == length) {
      count++;
      return;
    }

    int[] ax = {(x - 1 < 0 ? M - 1 : x - 1), x, (x + 1 == M ? 0 : x + 1)};
    int[] ay = {(y - 1 < 0 ? N - 1 : y - 1) , y, (y + 1 == N ? 0 : y + 1)};

    for(int i = 0; i < 3; i++) {
      for(int j = 0; j < 3; j++) {
        if(i != 1 || j != 1)
          if(grid[ay[i]][ax[j]] == txt.charAt(idx)) check(txt, idx + 1, ay[i], ax[j], length);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 격자 크기 N, M, K
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    grid = new char[N][M];

    for(int i = 0; i < N; i++) {
      String rowStr = br.readLine();
      for(int j = 0; j < M; j++) {
        grid[i][j] = rowStr.charAt(j);
      }
    }

    Node node = new Node();

    for(int god = 0; god < K; god++) {
      String txt = br.readLine();
      count = node.insert(txt);
      if(count != 0) {
        System.out.println(count);
        continue;
      }
      int txtLen = txt.length();
      char startChar = txt.charAt(0);
      for(int i = 0; i < N; i++) {
        for(int j = 0; j < M; j++) {
          if(grid[i][j] == startChar) {
            if(txtLen == 1) count++;
            else check(txt, 0 + 1, i, j, txtLen);
          }
        }
      }
      node.current.caseCount = count;
      System.out.println(count);
    }
  }
}
