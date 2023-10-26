import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, S;
  static int[] arr;

  static int ans = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());

    arr = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    query(0, 0, 0);

    if (S == 0) ans--;

    System.out.println(ans);


  }
  static void query(int ptr, int sum, int size) {

    if (ptr == N) {
      if (sum == S) {
        ans++;
        return;
      }
    }
    else  {
      query(ptr + 1, sum + arr[ptr], size + 1);
      query(ptr + 1, sum, size);
    }
  }
}