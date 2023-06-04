import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
  static int R, C, K;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken()) - 1;
    C = Integer.parseInt(st.nextToken()) - 1;
    K = Integer.parseInt(st.nextToken());

    int[][] grid = new int[3][3];
    for (int i = 0; i < 3; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(simulation(grid));
  }
  static int simulation(int[][] grid) {

    int time = 0;
    while (time != 101) {

      if (isValidIdx(grid.length, grid[0].length) && grid[R][C] == K) break;

      if (grid.length >= grid[0].length) grid = RCalc(grid);
      else grid = CClac(grid);
      time++;

    }
    if (time == 101) return -1;
    return time;
  }
  static int[][] RCalc(int[][] grid) {
    int rowLen = grid.length;
    int colLen = grid[0].length;

    ArrayList<Number>[] numberList = new ArrayList[rowLen];
    int maxSize = 0;
    for (int i = 0; i < rowLen; i++) {
      numberList[i] = new ArrayList<>();
      for (int j = 0; j < colLen; j++) {
        if (grid[i][j] == 0) continue;
        int k = 0;
        for (;k < numberList[i].size(); k++) {
          if (numberList[i].get(k).value == grid[i][j]) {
            numberList[i].get(k).count++;
            break;
          }
        }
        if (k == numberList[i].size()) numberList[i].add(new Number(grid[i][j]));
      }
      maxSize = Math.max(maxSize, numberList[i].size());
    }

    int newColLen = Math.min(maxSize * 2, 100);
    int[][] newGrid = new int[rowLen][newColLen];

    for (int i = 0; i < rowLen; i++) {
      Collections.sort(numberList[i]);
      for (int j = 0; j < numberList[i].size(); j++) {
        newGrid[i][2 * j] = numberList[i].get(j).value;
        newGrid[i][2 * j + 1] = numberList[i].get(j).count;
      }
    }
    return newGrid;
  }
  static int[][] CClac(int[][] grid) {
    int rowLen = grid.length;
    int colLen = grid[0].length;

    int maxSize = 0;
    ArrayList<Number>[] numberList = new ArrayList[colLen];
    for (int j = 0; j < colLen; j++) {
      numberList[j] = new ArrayList<>();
      for (int i = 0; i < rowLen; i++) {
        if (grid[i][j] == 0) continue;
        int k = 0;
        for (; k < numberList[j].size(); k++) {
          if (numberList[j].get(k).value == grid[i][j]) {
            numberList[j].get(k).count++;
            break;
          }
        }
        if (k == numberList[j].size()) numberList[j].add(new Number(grid[i][j]));
      }
      maxSize = Math.max(maxSize, numberList[j].size());
    }

    int newRowLen = Math.min(maxSize * 2, 100);
    int[][] newGrid = new int[newRowLen][colLen];

    for (int j = 0; j < colLen; j++) {
      Collections.sort(numberList[j]);
      for (int i = 0; i < numberList[j].size(); i++) {
        newGrid[2 * i][j] = numberList[j].get(i).value;
        newGrid[2 * i + 1][j] = numberList[j].get(i).count;
      }
    }
    return newGrid;
  }
  static boolean isValidIdx(int rowLen, int colLen) {
    return R < rowLen && C < colLen;
  }
}
class Number implements Comparable<Number>{
  int value;
  int count;

  Number(int value) {
    this.value = value;
    this.count = 1;
  }

  @Override
  public int compareTo(Number o) {
    if (this.count > o.count) return 1;
    else if (this.count < o.count) return -1;
    else {
      return this.value - o.value;
    }
  }
}