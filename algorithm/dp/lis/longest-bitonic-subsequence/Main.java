import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[] nums = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(queryResult(nums, N));
  }
  private static int queryResult(int[] nums, int N) {
    int[] table_front = initTableFront(nums, N);
    int[] table_back = initTableBack(nums, N);

    return getLogestLength(table_front, table_back, N);
  }
  private static int getLogestLength(int[] table_front, int[] table_back, int N) {

    int maxValue = Integer.MIN_VALUE;
    for (int i = 0; i < N; i++) {
      if (maxValue < (table_front[i] + table_back[i] - 1)) {
        maxValue = (table_front[i] + table_back[i] - 1);
      }
    }
    return maxValue;
  }
  private static int[] initTableFront(int[] nums, int N) {
    int[] table_front = new int[N];

    for (int i = 0; i < N; i++) {
      table_front[i] = 1;
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          table_front[i] = Math.max(table_front[i], table_front[j] + 1);
        }
      }
    }
    return table_front;
  }
  private static int[] initTableBack(int[] nums, int N) {
    int[] table_back = new int[N];

    for (int i = N - 1; i >= 0; i--) {
      table_back[i] = 1;
      for (int j = N - 1; j > i; j--) {
        if (nums[i] > nums[j]) {
          table_back[i] = Math.max(table_back[i], table_back[j] + 1);
        }
      }
    }

    return table_back;
  }
}