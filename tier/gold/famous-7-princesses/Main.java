import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] ax = {-1, 0, 1, 0};
  static int[] ay = {0, -1, 0, 1};
  static int ans = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    char[][] grid = new char[5][5];
    for (int i = 0; i < 5; i++) {
      String str = br.readLine();
      for (int j = 0; j < 5; j++) {
        grid[i][j] = str.charAt(j);
      }
    }

    int[] arr = new int[7];
    combination(grid, 0, arr, 0);

    System.out.println(ans);


  }
  static int[][] convertNumToIdx(int[] arr) {
    int[][] member = new int[arr.length][2];
    for (int i = 0; i < arr.length; i++) {
      member[i][0] = arr[i] / 5;
      member[i][1] = arr[i] % 5;
    }
    return member;
  }
  static void combination(char[][] grid, int ptr, int[] arr, int size) {

    if (size == 7) {
      int[][] member = convertNumToIdx(arr);
      if (checkSom(member, grid)) {
        if (bfs(member)) ans++;
      }
    }

    else {
      if (ptr <= 24) {
        arr[size] = ptr;
        combination(grid,ptr + 1, arr, size + 1);
        arr[size] = 0;

        combination(grid, ptr + 1, arr, size);
      }
    }
  }

  static boolean checkSom(int[][] member, char[][] grid) {
    int som = 0;

    for (int i = 0; i < 7; i++) {
      if (grid[member[i][0]][member[i][1]] == 'S') som++;
    }

    if (som >= 4) return true;
    return false;
  }

  static boolean bfs(int[][] member) {
    boolean[] isVisited = new boolean[7];

    Queue<Integer> q = new ArrayDeque<>();
    q.add(0);
    isVisited[0] = true;

    while (!q.isEmpty()) {
      int idx = q.poll();
      int x = member[idx][1];
      int y = member[idx][0];

      for (int i = 0; i < 4; i++) {
        int nextX = x + ax[i];
        int nextY = y + ay[i];

        if (checkIdx(nextX, nextY)) {
          for (int j = 0; j < 7; j++) {
            if (!isVisited[j] && member[j][0] == nextY && member[j][1] == nextX) {
              isVisited[j] = true;
              q.add(j);
            }
          }
        }
      }
    }

    for (int i = 0; i < 7; i++) {
      if (isVisited[i] == false) return false;
    }
    return true;

  }
  static boolean checkIdx(int x, int y) {
    if (x < 0 || y < 0 || x > 4 || y > 4) return false;
    return true;
  }
}
