import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int num1 = Integer.parseInt(st.nextToken());
    int num2 = Integer.parseInt(st.nextToken());

    // Greates Common Divosor
    int gcd;
    if(num1 > num2)
      gcd = euclidean(num1, num2);
    else
      gcd = euclidean(num2, num1);

    int num1Div = num1 / gcd;
    int num2DIv = num2 / gcd;

    // Least Common Multiple
    int lcm = num1Div * num2DIv * gcd;
    System.out.println(gcd);
    System.out.println(lcm);
  }
  static int euclidean(int x, int y) {
    if(y == 0)
      return x;
    else
      return euclidean(y, x % y);
  }
}
