import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
  static int N;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());

    int[] plus = new int[N];
    int[] minus = new int[N];

    int plusSize = 0;
    int minusSize = 0;
    for (int i = 0; i < N; i++) {
      int num = Integer.parseInt(br.readLine());

      if (num > 0) {
        plus[plusSize++] = num;
      } else {
        minus[minusSize++] = num;
      }
    }

    plus = Arrays.stream(plus)
                    .boxed()
                            .sorted(Collections.reverseOrder())
                                    .mapToInt(Integer::intValue).toArray();
    Arrays.sort(minus);

    System.out.println(getSum(plus, plusSize) + getSum(minus, minusSize));
  }
  private static long getSum(int[] array, int size) {

    int i;
    long sum = 0;
    for (i = 0; i < size; i += 2) {
      if (i + 1 < size) {
        if (array[i + 1] == 1) {
          sum += (long)array[i] + (long)array[i + 1];
        } else {
          sum += ((long)array[i] * (long)array[i + 1]);
        }
      } else {
        sum += array[i];
      }
    }
    return sum;
  }
}