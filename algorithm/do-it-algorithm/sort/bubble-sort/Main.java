import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[] buff;
  static long swap;
  static void mergeSort(int[] nums) {
    int n = nums.length;

    buff = new int[n];
    swap = 0;

    __mergeSort(nums, 0, n - 1);

    buff = null;
  }

  static void __mergeSort(int[] nums, int left, int right) {
    if(left < right) {
      int i;
      int j = 0; int p = 0;
      int center = (left + right) / 2;
      int k = left;

      __mergeSort(nums, left, center);
      __mergeSort(nums, center + 1, right);

      for(i = left; i <= center; i++)
        buff[p++] = nums[i];

      while (i <= right && j < p) {
        // swap 횟수 계산 후 처리
        // 오른쪽 기준
        if(buff[j] > nums[i]) {
          swap += (i - k);
          nums[k++] = nums[i++];
        }
        else nums[k++] = buff[j++];
      }

      while (j < p) {
        nums[k++] = buff[j++];
      }

    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    st = new StringTokenizer(br.readLine());
    // N = 0 ~ 500,000
    int[] nums = new int[N];
    for(int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    mergeSort(nums);

    System.out.println(swap);
  }
}
