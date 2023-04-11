import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] usingChar = new int['Z' - 'A' + 1];
    int[] usingNumber = new int[10];

    String[] str = new String[N];
    int maxLength = 0;
    for (int i = 0; i < N; i++) {
      str[i] = br.readLine();
      maxLength = Math.max(maxLength, str[i].length());
    }

  }
  static void calcGreedy(String[] str, int[] usingChar, )
}