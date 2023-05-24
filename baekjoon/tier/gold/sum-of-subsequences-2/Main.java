import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
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

    ArrayList<Integer> aSum = new ArrayList<>();
    ArrayList<Integer> bSum = new ArrayList<>();

    combination(nums, aSum, 0, N / 2, 0);
    combination(nums, bSum, N / 2, N, 0);

    Collections.sort(bSum);
    long count = 0;
    int size = bSum.size();
    for (int i = 0; i < aSum.size(); i++) {
      int target = S - aSum.get(i);
      count += upper_bound(bSum, 0, size, target) - lower_bound(bSum, 0, size, target);
    }

    if (S == 0) System.out.println(count - 1);
    else System.out.println(count);

  }
  static int lower_bound(ArrayList<Integer> sum, int left, int right, int target) {
    while (left < right) {
      int mid = (left + right) / 2;

      if (sum.get(mid) < target) left = mid + 1;
      else right = mid;
    }
    return left;
  }
  static int upper_bound(ArrayList<Integer> sum, int left, int right, int target) {
    while (left < right) {
      int mid = (left + right) / 2;

      if(sum.get(mid) <= target) left = mid + 1;
      else right = mid;
    }
    return left;
  }
  static void combination(int[] nums, ArrayList<Integer> sumList, int ptr, int size, int sum) {
    if(ptr == size) {
      sumList.add(sum);
    }
    else {
      combination(nums, sumList, ptr + 1, size, sum + nums[ptr]);
      combination(nums, sumList, ptr + 1, size, sum);
    }
  }
}