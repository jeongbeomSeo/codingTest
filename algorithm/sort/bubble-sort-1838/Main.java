import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    Number[] numberArray = new Number[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int value = Integer.parseInt(st.nextToken());

      numberArray[i] = new Number(i, value);
    }

    Arrays.sort(numberArray);

    System.out.println(getQueryResult(numberArray, N));
  }
  private static int getQueryResult(Number[] numberArray, int N) {

    int max = Integer.MIN_VALUE;

    for (int i = 0; i < N; i++) {
      max = Math.max(max, numberArray[i].originIdx - i);
    }

    if (max == Integer.MIN_VALUE) return 0;
    return max;
  }
}
class Number implements Comparable<Number>{
  int originIdx;
  int value;

  Number (int originIdx, int value) {
    this.originIdx = originIdx;
    this.value = value;
  }

  @Override
  public int compareTo(Number o) {
    return this.value - o.value;
  }
}