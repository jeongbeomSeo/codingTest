import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
  static int r, c, k;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    r = Integer.parseInt(st.nextToken()) - 1;
    c = Integer.parseInt(st.nextToken()) - 1;
    k = Integer.parseInt(st.nextToken());

    int[][] grid = new int[3][3];
    for (int i = 0; i < 3; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    if (isValidIdx(3, 3) && grid[r][c] == k) System.out.println(0);
    else System.out.println(simulation(grid));

  }
  static int simulation(int[][] grid) {
    int time = 0;

    while (time <= 100) {

      int rowLen = grid.length;
      int colLen = grid[0].length;

      if (isValidIdx(rowLen, colLen) && grid[r][c] == k) break;

      if (rowLen >= colLen) {
        grid = RCalc(grid, rowLen, colLen);
      }
      else {
        grid = CCalc(grid, rowLen, colLen);
      }
      time++;
    }

    if (time > 100) return -1;
    return time;
  }
  static int[][] RCalc(int[][] grid, int rowLen, int colLen) {
    ArrayList<Sorting>[] lists = new ArrayList[rowLen];
    int maxSize = 0;

    for (int i = 0; i < rowLen; i++) {
      lists[i] = new ArrayList<>();
      for (int j = 0; j < colLen; j++) {
        int num = grid[i][j];
        if (num == 0) continue;
        int k;
        for (k = 0; k < lists[i].size(); k++) {
          if (lists[i].get(k).num == num) {
            lists[i].get(k).count++;
            break;
          }
        }
        if (k == lists[i].size()) lists[i].add(new Sorting(num, 1));
      }
      maxSize = Math.max(maxSize, lists[i].size());
    }

    int newColSize = Math.min(maxSize * 2, 100);

    int[][] newGrid = new int[rowLen][newColSize];
    for (int i = 0; i < rowLen; i++) {
      Collections.sort(lists[i]);
      for (int j = 0; j < lists[i].size(); j++) {
        newGrid[i][2 * j] = lists[i].get(j).num;
        newGrid[i][2 * j + 1] = lists[i].get(j).count;
      }
    }
    return newGrid;
  }
  static int[][] CCalc(int[][] grid, int rowLen, int colLen) {
    ArrayList<Sorting>[] lists = new ArrayList[colLen];
    int maxSize = 0;

    for (int j = 0; j < colLen; j++) {
      lists[j] = new ArrayList<>();
      for (int i = 0; i < rowLen; i++) {
        int num = grid[i][j];
        if (num == 0) continue;
        int k;
        for (k = 0; k < lists[j].size(); k++) {
          if (lists[j].get(k).num == num) {
            lists[j].get(k).count++;
            break;
          }
        }
        if (k == lists[j].size()) lists[j].add(new Sorting(num, 1));
      }
      maxSize = Math.max(maxSize, lists[j].size());
    }

    int newRowSize = Math.min(maxSize * 2, 100);
    int[][] newGrid = new int[newRowSize][colLen];
    for (int j = 0; j < colLen; j++) {
      Collections.sort(lists[j]);
      for (int i = 0; i < lists[j].size(); i++) {
        newGrid[2 * i][j] = lists[j].get(i).num;
        newGrid[2 * i + 1][j] = lists[j].get(i).count;
      }
    }

    return newGrid;
  }
  static boolean isValidIdx(int rowLen, int colLen) {
    return r < rowLen && c < colLen;
  }
}
class Sorting implements Comparable<Sorting>{
  int num;
  int count;

  Sorting(int num, int count) {
    this.num = num;
    this.count = count;
  }

  @Override
  public int compareTo(Sorting o) {
    if (this.count > o.count) return 1;
    else if (this.count < o.count) return -1;
    else {
      return this.num - o.num;
    }
  }
}