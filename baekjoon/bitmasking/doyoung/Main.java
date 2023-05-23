import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int MIN = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());


    int[][] ingredients = new int[N][2];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      ingredients[i][0] = Integer.parseInt(st.nextToken());
      ingredients[i][1] = Integer.parseInt(st.nextToken());
    }

    combination(ingredients, 1, 0, 0, 0);

    System.out.println(MIN);
  }
  static void combination(int[][] ingredients, int aSum, int bSum, int ptr, int size) {

    if (ptr == N) {
      if (size != 0) MIN = Math.min(Math.abs(aSum - bSum), MIN);
    }
    else {
      combination(ingredients, aSum * ingredients[ptr][0], bSum + ingredients[ptr][1], ptr + 1, size + 1);

      combination(ingredients, aSum, bSum, ptr + 1, size);
    }
  }
}