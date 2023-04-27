import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] nums = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0 ; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(nums);

    int M = Integer.parseInt(br.readLine());
    int[] findNums = new int[M];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      findNums[i] = Integer.parseInt(st.nextToken());
    }

    for (int i = 0; i < M; i++) {
      if (binarySearch(nums, 0, N - 1, findNums[i]) != -1) System.out.println(1);
      else System.out.println(0);
    }
  }
  static int binarySearch(int[] nums, int left, int right, int target) {
    if (left < right) {
      int mid = (left + right) / 2;

      if (nums[mid] < target) left = mid + 1;
      else right = mid;
      return binarySearch(nums, left, right, target);
    }
    else {
      if (nums[left] == target) return left;
      else return -1;
    }
  }
}
