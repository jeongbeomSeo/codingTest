import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static long[] b;
  static int[] f;

  static long cardSort(int[] nums) {
    // Counting Sort
    int n = nums.length;
    long[] sum = new long[n - 1];
    long min = 0;

    if(n == 1) return 0;

    f = new int[1001];
    b = new long[n];


    for(int i = 0; i < n; i++) f[nums[i]]++;
    for(int i = 1; i < f.length; i++) f[i] += f[i - 1];
    for(int i = n - 1; i >= 0; i--) b[--f[nums[i]]] = nums[i];
    for(int i = 0; i < n - 1; i++) {
      sum[i] = b[i] + b[i + 1];
      int j = i + 1;

      // 삽입 정렬
      for(; j < n - 1 && b[j + 1] < sum[i]; j++) {
        b[j] = b[j + 1];
      }
      b[j] = sum[i];
    }

    // 비교 횟수 전부 더하기
    for(int i = 0; i < sum.length; i++)
      min += sum[i];

    return min;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[] cards = new int[N];

    for(int i = 0; i < cards.length; i++) {
      cards[i] = Integer.parseInt(br.readLine());
    }

    System.out.println(cardSort(cards));

  }
}
