import java.io.*;
import java.util.StringTokenizer;

public class Main {
  private static final int MAX_SIZE = 1000001;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[] nums = new int[N];
    boolean[] numBoolArray = new boolean[MAX_SIZE];
    int[] resultArray = new int[MAX_SIZE];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int num = Integer.parseInt(st.nextToken());

      nums[i] = num;
      numBoolArray[num] = true;
    }

    for (int i = 0; i < N; i++) {
      int num = nums[i];
      for (int j = num + num; j < MAX_SIZE; j += num) {
        if (numBoolArray[j]) {
          resultArray[j]--;
          resultArray[num]++;
        }
      }
    }

    for (int i = 0; i < N - 1; i++) {
      bw.write(resultArray[nums[i]] + " ");
    }
    bw.write(resultArray[nums[N - 1]] + "");

    bw.flush();
    bw.close();
  }
}