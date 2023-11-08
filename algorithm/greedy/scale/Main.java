import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());

    int[] nums = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(nums);

    System.out.println(queryResult(nums));
  }
  private static int queryResult(int[] nums) {

    int MAX = 1;

    if (nums[0] != 1) return MAX;

    for (int i = 1; i < N; i++) {
      int num = nums[i];

      if (MAX >= num - 1) {
        MAX += num;
      } else {
        break;
      }
    }
    return MAX + 1;
  }
}