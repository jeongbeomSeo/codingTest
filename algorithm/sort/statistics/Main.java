import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(array);

    int[] result = query(array, N);

    for (int i = 0; i < 4; i++) {
      System.out.println(result[i]);
    }
  }
  private static int[] query(int[] array, int N) {

    int sum = array[0];
    int min = array[0];
    int max = array[N - 1];
    int rangeMinMax = max - min;
    int centerValue = array[N / 2];

    List<Number> numList = new ArrayList<>();
    int value = array[0];
    int count = 1;
    for (int i = 1; i < N; i++) {
      sum += array[i];

      if (value == array[i]) {
        count++;
      } else {
        numList.add(new Number(value, count));
        value = array[i];
        count = 1;
      }
    }
    numList.add(new Number(value, count));

    Collections.sort(numList);

    int valueWhenMaxCount = 0;
    if (numList.isEmpty()) valueWhenMaxCount = array[0];
    else valueWhenMaxCount = numList.get(0).value;
    if (numList.size() > 1) {
      if (numList.get(0).count == numList.get(1).count) {
        valueWhenMaxCount = numList.get(1).value;
      }
    }

    return new int[]{getMean(sum, N), centerValue, valueWhenMaxCount, rangeMinMax};
  }
  private static int getMean(int sum, int N) {
    if (sum == 0) return 0;
    else {
      return (int)Math.round((double)sum / N);
    }
  }
}
class Number implements Comparable<Number>{
  int value;
  int count;

  Number (int value, int count) {
    this.value = value;
    this.count = count;
  }

  @Override
  public int compareTo(Number o) {
    if (this.count != o.count) return o.count - this.count;
    else {
      return this.value - o.value;
    }
  }
}