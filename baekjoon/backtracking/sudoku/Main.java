import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Zero {
  int y;
  int x;

  Zero(int y, int x) {
    this.y = y;
    this.x = x;
  }
}
public class Main {
  static int[][] sudoku;
  static ArrayList<Zero> zero;
  static boolean success = false;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    zero = new ArrayList<>();
    sudoku = new int[9][9];

    for(int i = 0; i < 9; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < 9; j++) {
        sudoku[i][j] = Integer.parseInt(st.nextToken());
        if(sudoku[i][j] == 0) zero.add(new Zero(i, j));
      }
    }

    backtracking(0);
    for(int i = 0; i < 9; i++) {
      for(int j = 0; j < 9; j++) {
        System.out.print(sudoku[i][j] + " ");
      }
      System.out.println();
    }
  }
  static void backtracking(int ptr) {
    int x = zero.get(ptr).x;
    int y = zero.get(ptr).y;

    for(int num = 1; num <= 9; num++) {

      if(check(x ,y, num)) {
        sudoku[y][x] = num;
        if(ptr == zero.size() - 1) {
          success = true;
          return;
        }
        else
          backtracking(ptr + 1);
      }
      if(success) return;
      sudoku[y][x] = 0;
    }
  }
  static boolean check(int x, int y, int num) {
    // 같은 행 열 확인
    for(int i = 0; i < 9; i++) {
      if(i != y) if(sudoku[i][x] == num) return false;
      if(i != x) if(sudoku[y][i] == num) return false;
    }
    int boxStartX = (x / 3) * 3;
    int boxStartY = (y / 3) * 3;
    for(int i = boxStartY; i < boxStartY + 3; i++) {
      for(int j = boxStartX; j < boxStartX + 3; j++) {
        if(i != y && j != x) if(sudoku[i][j] == num) return false;
      }
    }
    return true;
  }
}
