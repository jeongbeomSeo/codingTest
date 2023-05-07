import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    Integer[] nums = new Integer[N];
    for (int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(nums, Collections.reverseOrder());

    int ptr = 0;
    long sum = 0;
    while (ptr < N) {
      if (nums[ptr] > 0) {
        if (ptr + 1 < N &&
                nums[ptr] * nums[ptr + 1] > nums[ptr] + nums[ptr + 1]) {
          sum += (nums[ptr] * nums[ptr + 1]);
          ptr += 2;
        }
        else {
          sum += nums[ptr];
          ptr += 1;
        }
      }
      else break;
    }

    int minusPtr = N - 1;
    while (minusPtr >= ptr) {
      if (nums[minusPtr] <= 0) {
        if (minusPtr - 1 >= ptr &&
                nums[minusPtr] * nums[minusPtr - 1] > nums[minusPtr] + nums[minusPtr - 1]) {
          sum += nums[minusPtr] * nums[minusPtr - 1];
          minusPtr -= 2;
        }
        else {
          sum += nums[minusPtr];
          minusPtr -= 1;
        }
      }
      else break;
    }
    System.out.println(sum);
   }
}