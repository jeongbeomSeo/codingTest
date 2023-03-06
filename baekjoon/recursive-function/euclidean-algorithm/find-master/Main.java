import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // M , N
    int M = Math.abs(Integer.parseInt(st.nextToken()));
    int N = Math.abs(Integer.parseInt(st.nextToken()));

    // Case 1
    if(M == N) {
      if(M == 0) System.out.println(0);
      else if(M == 1 || M == -1) System.out.println(1);
      else System.out.println(2);
    }
    else {
      if(euclidean(M, N)) System.out.println(1);
      else System.out.println(2);
    }


  }
  static boolean euclidean(int x, int y) {
    if(y == 0) {
      if(x <= 1)
        return true;
      else
        return false;
    }
    return euclidean(y, x % y);
  }
}
