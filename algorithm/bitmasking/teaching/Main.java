import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    String[] list = new String[N];
    for (int i = 0; i < N; i++) {
      list[i] = br.readLine();
    }
    if (K < 5) {
      System.out.println(0);
    } else {
      System.out.println(queryResult(list, N, K));
    }
  }
  private static int queryResult(String[] list, int N, int K) {

    int max = Integer.MIN_VALUE;
    for (int i = (1 << K) - 1; i < (1 << 26); i++) {
      if (Integer.bitCount(i) == K && checkValidation(i)) {
        int count = 0;
        for (int j = 0; j < N; j++) {
          if (checkCanReading(list[j], i)) {
            count++;
          }
        }
        max = Math.max(max, count);
      }
    }

    return max;
  }
  private static boolean checkCanReading(String word, int num) {
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);

      if ((num & (1 << convertCharToInt(c))) == 0) return false;
    }
    return true;
  }
  private static boolean checkValidation(int num) {

    if ((num & (1 << convertCharToInt('a'))) == 0) return false;
    if ((num & (1 << convertCharToInt('n'))) == 0) return false;
    if ((num & (1 << convertCharToInt('t'))) == 0) return false;
    if ((num & (1 << convertCharToInt('i'))) == 0) return false;
    if ((num & (1 << convertCharToInt('c'))) == 0) return false;

    return true;
  }
  private static int convertCharToInt(char c) {
    return c - 'a';
  }
}