import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] trees = new int[N];
    st = new StringTokenizer(br.readLine());
    int right = 0;
    for (int i = 0; i < N; i++) {
      trees[i] = Integer.parseInt(st.nextToken());
      right = Math.max(trees[i], right);
    }

    System.out.println(binarySearch(trees, M, right));
  }
  static int binarySearch(int[] trees, int M, int right) {

    int left = 0;
    int result = 0;

    while (left < right) {
      int mid = (left + right) / 2;

      long woodCut = 0;
      for (int tree : trees) {
        if (tree > mid) woodCut += (tree - mid);
      }

      if (woodCut >= M) {
        result = mid;
        left = mid + 1;
      }
      else {
        right = mid;
      }
    }
    return result;
  }
}