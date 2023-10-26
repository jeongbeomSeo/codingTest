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
    for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

    ArrayList<Integer> aSum = new ArrayList<>();
    ArrayList<Integer> bSum = new ArrayList<>();

    combination(aSum, nums, 0, 0, N / 2);
    combination(bSum, nums, N / 2, 0, N);

    Collections.sort(bSum);
    int size = bSum.size();
    long count = 0;
    for (int i = 0; i < aSum.size(); i++) {
      int target = S - aSum.get(i);

      count += (upper_bound(bSum, 0 , size, target) - lower_bound(bSum, 0, size, target));
    }
    if (S == 0) System.out.println(count - 1);
    else System.out.println(count);
  }
  static int upper_bound(ArrayList<Integer> bSum, int left, int right, int target) {
    while (left < right) {
      int mid = (left + right) / 2;

      if (bSum.get(mid) <= target) left = mid + 1;
      else right = mid;
    }
    return left;
  }
  static int lower_bound(ArrayList<Integer> bSum, int left, int right, int target) {
    while (left < right) {
      int mid = (left + right) / 2;

      if (bSum.get(mid) < target) left = mid + 1;
      else right = mid;
    }
    return left;
  }
  static void combination(ArrayList<Integer> sumList, int[] nums, int ptr, int sum, int size) {

    if (ptr == size) {
      sumList.add(sum);
    }
    else {
      combination(sumList, nums, ptr + 1, sum + nums[ptr], size);
      combination(sumList, nums, ptr + 1, sum, size);
    }
  }
}