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

    List<Integer> sumListA = new ArrayList<>();
    List<Integer> sumListB = new ArrayList<>();

    getSumCombination(sumListA, nums, 0, N / 2, 0);
    getSumCombination(sumListB, nums, N / 2, N, 0);

    Collections.sort(sumListB);

    int sizeA = sumListA.size();
    int sizeB = sumListB.size();

    long result = 0;
    for (int i = 0; i < sizeA; i++) {
      int sumA = sumListA.get(i);

      int upperBoundIdx = getUpperBound(sumListB, 0, sizeB, S - sumA);
      int lowerBoundIdx = getLowerBound(sumListB, 0, sizeB, S - sumA);

      result += (upperBoundIdx - lowerBoundIdx);
    }

    if (S == 0) result--;
    System.out.println(result);
  }
  private static int getUpperBound(List<Integer> sumList, int left, int right, int target) {
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
  private static int getLowerBound(List<Integer> sumList, int left, int right, int target) {
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
  private static void getSumCombination(List<Integer> sumList, int[] nums, int ptr, int end, int sum) {

    if (ptr == end) {
      sumList.add(sum);
    } else {
      getSumCombination(sumList, nums, ptr + 1, end, sum + nums[ptr]);

      getSumCombination(sumList, nums, ptr + 1, end, sum);
    }
  }
}