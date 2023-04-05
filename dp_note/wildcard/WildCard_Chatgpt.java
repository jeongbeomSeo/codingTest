import java.util.*;

public class WildCard_Chatgpt {
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