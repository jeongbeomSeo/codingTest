import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int MAX = 1000001;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    N = Integer.parseInt(br.readLine());

    int[] array = new int[N + 1];
    int[] isUsingNumberIdx = new int[MAX];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int num = Integer.parseInt(st.nextToken());

      array[i + 1] = num;
      isUsingNumberIdx[num] = i + 1;
    }

    int[] result = queryResult(array, isUsingNumberIdx);

    for (int i = 1; i < N + 1; i++) {
      bw.write(result[i] + " ");
    }
    bw.flush();
    bw.close();
  }
  private static int[] queryResult(int[] array, int[] isUsingNumberIdx) {

    int[] result = new int[N + 1];

    for (int i = 1; i < N + 1; i++) {
      int num = array[i];

      for (int j = num * 2; j < MAX; j += num) {
        if (isUsingNumberIdx[j] != 0) {
          result[i]++;
          result[isUsingNumberIdx[j]]--;
        }
      }
    }
    return result;
  }
}