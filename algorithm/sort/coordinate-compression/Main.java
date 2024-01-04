import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    Number[] array = new Number[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int value = Integer.parseInt(st.nextToken());
      array[i] = new Number(i, value);
    }

    Arrays.sort(array);

    int idx = 0;
    int[] result = new int[N];
    result[array[0].idx] = idx;
    for (int i = 1; i < N; i++) {
      if (array[i - 1].value != array[i].value) idx++;
      result[array[i].idx] = idx;
    }

    for (int i : result) {
      bw.write(i + " ");
    }
    bw.flush();
    bw.close();
  }
}
class Number implements Comparable<Number>{
  int idx;
  int value;

  Number(int idx, int value) {
    this.idx = idx;
    this.value = value;
  }

  @Override
  public int compareTo(Number o) {
    return this.value - o.value;
  }
}