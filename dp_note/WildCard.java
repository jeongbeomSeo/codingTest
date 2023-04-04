import java.util.*;

public class WildCard {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String pattern = "";
    int i, j, length;
    boolean first = true, edit, in = false;
    boolean print = false;
    boolean[][] d = new boolean[2][22];

    while (true) {
      String text = scanner.nextLine();
      if (text.equals("")) {
        in = false;
        continue;
      }

      if (!in) {
        pattern = text;
        in = true;
        print = false;
      } else {
        length = text.length();

        // d 배열의 첫번째 요소가 빈 문자라는 것을 인지.
        // i=0부터 시작하므로 그것의 대각선인 d[1][0]을 true로 채워줘야 한다.
        for (i = 0; i < 2; i++) Arrays.fill(d[i], false);
        d[1][0] = true;
        edit = true;

        for (i = 0; i < pattern.length() && edit; i++) {
          edit = false;
          Arrays.fill(d[i % 2], false);

          // 와일드 카드 '*'가 나오면 이전 문자에서 true 나온 위치 이후로 전부 true로 채워주기
          if (pattern.charAt(i) == '*') {
            j = 0;

            while (!d[1 - i % 2][j] && j <= length) j++;
            if (j > length) break;

            edit = true;
            Arrays.fill(d[i % 2], j, 22, true);
          }

          // 와일드 카드가 아닌 경우
          else {
            for (j = 0; j < length; j++) {
              // 패턴에서의 원하는 특정 문자 나오면서 이전 문자(대각선)
              if (pattern.charAt(i) == text.charAt(j) && d[1 - i % 2][j]) {
                d[i % 2][j + 1] = true;
                edit = true;
              }
            }
          }
        }

        // i는 패턴 문자열 까지 도달했으면서 text 문자열을 전부 확인했다면 끝 요소가 true로 되어있어야 한다.
        if (i == pattern.length() && d[1 - i % 2][length]) {
          if (!print) {
            if (!first) System.out.println();
            System.out.println("MATCHEDS FOR THE PATTERN: " + pattern);
            first = false;
            print = true;
          }
          System.out.println(text);
        }
      }
    }
  }
}
/*
import java.util.*;

public class WildcardMatching {
    static boolean[][] cache;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        while (T-- > 0) {
            String pattern = sc.nextLine();
            int n = Integer.parseInt(sc.nextLine());

            while (n-- > 0) {
                String text = sc.nextLine();
                boolean isMatched = isWildcardMatch(text, pattern);
                if (isMatched) {
                    System.out.println(text);
                }
            }
        }

        sc.close();
    }

    static boolean isWildcardMatch(String text, String pattern) {
        int tLen = text.length();
        int pLen = pattern.length();

        cache = new boolean[tLen + 1][pLen + 1];
        cache[0][0] = true;

        for (int j = 1; j <= pLen; j++) {
            if (pattern.charAt(j - 1) == '*') {
                cache[0][j] = cache[0][j - 1];
            }
        }

        for (int i = 1; i <= tLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (pattern.charAt(j - 1) == '?' || text.charAt(i - 1) == pattern.charAt(j - 1)) {
                    cache[i][j] = cache[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '*') {
                    cache[i][j] = cache[i - 1][j] || cache[i][j - 1];
                }
            }
        }

        return cache[tLen][pLen];
    }
}
*/
