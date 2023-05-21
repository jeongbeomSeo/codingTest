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

    int[] weights = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      weights[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(weights);

    if (weights[0] != 1) {
      System.out.println(1);
    }
    else {
      int sum = weights[0];
      for (int i = 1; i < N; i++) {
        if (sum + 1 >= weights[i]) sum += weights[i];
        else break;
      }

      System.out.println(sum + 1);
    }
  }
}