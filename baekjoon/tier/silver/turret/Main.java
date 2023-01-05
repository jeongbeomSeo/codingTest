import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    // Test Case
    int tc = Integer.parseInt(br.readLine());

    for (int count = 0; count < tc; count++) {
      st = new StringTokenizer(br.readLine());

      // x1, y1, x2, y2, r1, r2
      int x1 = Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      int r1 = Integer.parseInt(st.nextToken());
      int x2 = Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());
      int r2 = Integer.parseInt(st.nextToken());

      double distance = calcDistance(x1, y1, x2, y2);
      if(distance == 0) {
        if (r1 == r2) System.out.println(-1);
        else System.out.println(0);
        continue;
      }

      if(Math.abs(r1 - r2) > distance) System.out.println(0);
      else if (Math.abs(r1 - r2) == distance) System.out.println(1);
      else if (distance > Math.abs(r1 - r2) && distance < r1 + r2) System.out.println(2);
      else if (distance == r1 + r2) System.out.println(1);
      else System.out.println(0);
    }
  }
  static double calcDistance(int x1, int y1, int x2, int y2) {
    return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
  }
}