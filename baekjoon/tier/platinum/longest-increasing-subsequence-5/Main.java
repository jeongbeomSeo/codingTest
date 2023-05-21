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

    int[] buffer = new int[N];
    int size = 0;

    buffer[size++] = nums[0];
    int[] idxList = new int[N];

    for (int i = 1; i < N; i++) {
      if (buffer[size - 1] < nums[i]) {
        idxList[i] = size;
        buffer[size++] = nums[i];
      }
      // else if (buffer[size - 1] > nums[i]) 이 방식으로도 하면 틀린다.
      else {
        int idx = lower_bound(buffer, 0, size, nums[i]);
        buffer[idx] = nums[i];
        idxList[i] = idx;
      }
    }

    System.out.println(size);
    int[] result = new int[size];
    int last = N - 1;

    for (int i = size - 1; i >= 0; i--) {
      for (int j = last; j >= 0; j--) {
        if (idxList[j] == i) {
          result[i] = nums[j];
          last = j - 1;
          break;
        }
      }
    }
    /* 이 방식 쓰면 틀림
    int last = 0;
    for (int i = 0; i < size; i++) {
      for (; last < N; last++) {
        if (idxList[last] == i) {
          result[i] = nums[last++];
          break;
        }
      }
    }
    */

   for (int i = 0; i < size; i++) System.out.print(result[i] + " ");

  }
  static int lower_bound(int[] buffer, int left, int right, int target) {
    while (left < right) {
      int mid = (left + right) / 2;

      if (buffer[mid] < target) left = mid + 1;
      else right = mid;
    }
    return left;
  }
}
