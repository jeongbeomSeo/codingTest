import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[] ans;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    ans = new int[1001];

    ans[1] = 3;
    for(int pointN = 2; pointN <= 1000; pointN++) {
      int canSeePoints = 0;

      // (0,0) -> (N,N) 이은 직선 기준 한 쪽만 Processing
      for(int j = 1; j < pointN; j++) {
        // 좌표상 x가 1이라면 모든 점들은 (0,0)과 이어진다.
        if(j == 1) canSeePoints++;
        else if(Euclidean(pointN, j)) canSeePoints++;
      }

      canSeePoints *= 2;
      ans[pointN] = canSeePoints + ans[pointN - 1];
    }

    // Test Case
    int tc = Integer.parseInt(br.readLine());
    for(int counter = 0; counter < tc; counter++) {
      int N = Integer.parseInt(br.readLine());

      System.out.println(ans[N]);
    }

  }
  // 서로수 확인 (서로수인 경우 true)
  static boolean Euclidean(int x, int y) {
    if(y == 0){
      if(x <= 1)
        return true;
      else
        return false;
    }
    else
      return Euclidean(y, x % y);
  }
}
