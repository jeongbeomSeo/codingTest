import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[] alphabet = new int['Z' - 'A' + 1];
    String[] str = new String[N];

    for (int i = 0; i < N; i++) {
      str[i] = br.readLine();
    }

    transform_string(str, alphabet, N);

    System.out.println(calculateWordNumber(alphabet));
  }
  static private int charToInt(char c) {
    return c - 'A';
  }

  static void transform_string(String[] str, int[] alphabet, int N) {

    for (int i = 0; i < N; i++) {
      int strLength = str[i].length();

      for (int j = 0; j < strLength; j++) {
        char curChar = str[i].charAt(j);
        int curIdx = charToInt(curChar);

        alphabet[curIdx] += (int)Math.pow(10, (strLength - 1) - j);
      }
    }
  }

  static int calculateWordNumber(int[] alphabet) {

    int length = 'Z' - 'A' + 1;
    int sum = 0;

    int num = 9;
    while (num >= 0) {
      boolean change = false;

      int maxIdx = -1;
      int maxValue = 0;
      for (int i = 0; i < length; i++) {
        if (maxValue < alphabet[i]) {
          maxIdx = i;
          maxValue = alphabet[i];
          change = true;
        }
      }

      if (!change) break;

      sum += (num * alphabet[maxIdx]);

      alphabet[maxIdx] = 0;
      num--;
    }
    return sum;

  }
}