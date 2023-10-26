import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());

    int[] card = new int[N + 1];
    int[] checkNum = new int[1000001];

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      int num = Integer.parseInt(st.nextToken());

      checkNum[num] = i;
      card[i] = num;
    }

    int[] result = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      for (int j = card[i] * 2; j <= 1000000; j += card[i]) {

        if (checkNum[j] != 0) {
          result[i]++;
          result[checkNum[j]]--;
        }
      }
    }

    for (int i = 1; i <= N; i++) {
      System.out.print(result[i] + " ");
    }
  }
}
