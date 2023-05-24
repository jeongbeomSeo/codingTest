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

    String[] words = new String[N];
    for (int i = 0; i < N; i++) {
      words[i] = br.readLine();
    }

    int max = 0;
    if (K >= 5) {
      for (int i = (1 << K) - 1; i < (1 << 26); i++) {
        if (Integer.bitCount(i) == K) {
          if (!basic_condition(i)) continue;
          int count = 0;
          for (String word : words) {
            int j;
            for (j = 0; j < word.length(); j++) {
              int idx = convertToInt(word.charAt(j));
              if (((1 << idx) & i) != (1 << idx)) break;
            }
            if (j == word.length()) count++;
          }
          max = Math.max(max, count);
        }
      }
    }
    System.out.println(max);
  }
  static boolean basic_condition(int num) {
    int a = convertToInt('a');
    if (((1 << a) & num) != (1 << a)) return false;
    int n = convertToInt('n');
    if (((1 << n) & num) != (1 << n)) return false;
    int t = convertToInt('t');
    if (((1 << t) & num) != (1 << t)) return false;
    int i = convertToInt('i');
    if (((1 << i) & num) != (1 << i)) return false;
    int c = convertToInt('c');
    if (((1 << c) & num) != (1 << c)) return false;

    return true;
  }
  static int convertToInt(char c) {
    return c - 'a';
  }
}
