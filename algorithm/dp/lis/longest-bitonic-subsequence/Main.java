import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] dp_front = new int[N];
    int[] dp_back = new int[N];
    int[] arr = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(lbs_dp(dp_front, dp_back, arr, N));
  }
  static int lbs_dp(int[] dp_front, int[] dp_back, int[] arr, int N) {
    for (int i = 0; i < N; i++) {
      int back_i = N - 1 - i;
      dp_front[i] = 1;
      dp_back[back_i] = 0;
      for (int j = 0; j < i; j++) {
        int back_j = N - 1 - j;
        if (arr[j] < arr[i])
          dp_front[i] = Math.max(dp_front[i], dp_front[j] + 1);
        if (arr[back_i] > arr[back_j])
          dp_back[back_i] = Math.max(dp_back[back_i], dp_back[back_j] + 1);
      }
    }
    /*
    for (int i = N - 1; i >= 0; i--) {
      dp_back[i] = 0;
      for (int j = N - 1; j > i; j--) {
        if (arr[i] > arr[j])
          dp_back[i] = Math.max(dp_back[i], dp_back[j] + 1);
      }
    }
    */
    int max = 0;
    for (int i = 0; i < N; i++) {
      max = Math.max(max, dp_front[i] + dp_back[i]);
    }
    return max;
  }
}
