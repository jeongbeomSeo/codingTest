import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());

    int[] arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    ArrayList<Integer> aSum = new ArrayList<>();
    ArrayList<Integer> bSum = new ArrayList<>();

    combination(arr, aSum, 0, new int[N / 2 + 1], 0, N / 2);
    combination(arr, bSum, N / 2, new int[N / 2 + 1], 0, N);

    Collections.sort(bSum);
    int size = bSum.size();

    long count = 0;
    for (int i = 0; i < aSum.size(); i++) {
      int target = S - aSum.get(i);

      count += upper_bound(bSum, 0, size, target) - lower_bound(bSum, 0, size, target);
    }

    if (S == 0) System.out.println(count - 1);
    else System.out.println(count);
  }
  static void combination(int[] arr, ArrayList<Integer> sum, int ptr, int[] buffer, int size, int N) {
    if (ptr == N) {
      int result = 0;
      for (int i = 0; i < size; i++) {
        result += buffer[i];
      }
      sum.add(result);
    }
    else {
      buffer[size] = arr[ptr];
      combination(arr, sum, ptr + 1, buffer, size + 1, N);
      buffer[size] = 0;

      combination(arr, sum, ptr + 1 , buffer, size, N);
    }
  }
  static int upper_bound(ArrayList<Integer> bSum, int left, int right, int target) {
    while (left < right) {
      int mid = (left + right) / 2;
      int value = bSum.get(mid);

      if (value <= target) left = mid + 1;
      else right = mid;
    }
    return left;
  }
  static int lower_bound(ArrayList<Integer> bSum, int left, int right, int target) {
    while (left < right) {
      int mid = (left + right) / 2;
      int value = bSum.get(mid);

      if (value < target) left = mid + 1;
      else right = mid;
    }
    return left;
  }
}
