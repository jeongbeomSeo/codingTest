import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());

    int[] nums = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    List<Integer> sumList = new ArrayList<>();

    querySumList(sumList, nums, N);

    Collections.sort(sumList);

    int size = sumList.size();
    int upperIdx = upper_bound(sumList, 0, size, S);
    int lowerIdx = lower_bound(sumList, 0, size, S);

    int result = upperIdx - lowerIdx;

    System.out.println(result);
  }
  private static int upper_bound(List<Integer> sumList, int left, int right, int target) {
    while (left < right) {
      int mid = (left + right) / 2;

      if (sumList.get(mid) <= target) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left;
  }
  private static int lower_bound(List<Integer> sumList, int left, int right, int target) {
    while (left < right) {
      int mid = (left + right) / 2;

      if (sumList.get(mid) < target) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left;
  }
  private static void querySumList(List<Integer> sumList, int[] nums, int size) {

    for (int i = 0; i < size; i++) {
      int sum = nums[i];
      sumList.add(sum);
      for (int j = i + 1; j < size; j++) {
        sum += nums[j];
        sumList.add(sum);
      }
    }
  }
}