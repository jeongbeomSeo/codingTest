import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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
public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Number[] nums = new Number[N];
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++) {
      nums[i] = new Number(i, Integer.parseInt(st.nextToken()));
    }

    Arrays.sort(nums);

    int max = Integer.MIN_VALUE;
    for(int i = 0; i < N; i++) {
      if(nums[i].idx - i > max) max = nums[i].idx - i;
    }

    System.out.println(max);

  }
}