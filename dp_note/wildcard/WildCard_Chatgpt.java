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

        // 매칭하면서 한칸씩 이동하는 것이라고 보면 된다.
        // i가 아래쪽으로 한칸 이동하는 것은 text를 한문자 스캔 완료 한 것이고,
        // j가 오른족으로 한칸 이동하는 것은 pattern을 한문자 스캔 한 것이라고 보면 된다.
        for (int i = 1; i <= tLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (pattern.charAt(j - 1) == '?' || text.charAt(i - 1) == pattern.charAt(j - 1)) {
                    cache[i][j] = cache[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '*') {
                    // '*'는 0칸이 될수도 있고, 모든 칸이 될 수 있다. 따라서, 아래와 같이 처리.
                    // 참고로, j - 1칸을 가져 오는 것이 0칸인 경우 
                    // 이유는 pattern에서 비교할 칸이 한칸 줄어든 것이다. 그림(표)으로 확인해보면 확실하다.
                    cache[i][j] = cache[i - 1][j] || cache[i][j - 1];
                }
            }
        }

        return cache[tLen][pLen];
    }
}