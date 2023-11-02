import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] trees = new int[N];

    st = new StringTokenizer(br.readLine());
    long max = Integer.MIN_VALUE;
    for (int i = 0; i < N; i++) {
      trees[i] = Integer.parseInt(st.nextToken());
      max = Math.max(trees[i], max);
    }


    System.out.println(upper_bound(trees, 0, max + 1, M, N));

  }
  private static int upper_bound(int[] trees, long left, long right, int target, int trees_length) {
    while (left < right) {
      long mid = (left + right) / 2;

      long allTreeLength = getAllTreeLength(trees, trees_length, mid);

      if (allTreeLength >= target) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return (int)(left - 1);
  }
  private static long getAllTreeLength (int[] trees, int size, long target) {
    long length = 0;

    for (int i = 0; i < size; i++) {
      if (trees[i] > target) {
        length += trees[i] - target;
      }
    }

    return length;
  }
}