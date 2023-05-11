import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int[][] points = new int[3][2];
    for (int i = 0; i < 3; i++) {
      st = new StringTokenizer(br.readLine());
      points[i][0] = Integer.parseInt(st.nextToken());
      points[i][1] = Integer.parseInt(st.nextToken());
    }

    System.out.println(CCW(points));

  }
  static int CCW(int[][] points) {
    double gradient = (double)(points[1][1] - points[0][1]) / (points[1][0] - points[0][0]);

    double result = gradient * (points[2][0] - points[0][0]) + points[0][1];
    if (points[2][1] > result) return 1;
    else if (points[2][1] < result) return -1;
    else return 0;
  }
}
