public class WildCard_JongManBook {
  static int[][] dp = new int[101][101];
  static String W;
  static String S;

  static void init_dp(int N) {
    for (int i = 0; i <= N; i++) {
      for (int j = 0; j <= N; j++) {
        dp[i][j] = -1;
      }
    }
  }

  // 확인 안했을 경우에 -1, 확인 했지만 아닌 경우 0, 맞는 경우 1
  static int matched_dp(int w, int s) {

    if (dp[w][s] != -1) return dp[w][s];

    // W[w]와 S[s]를 맞춰나간다
    if (w < W.length() && s < S.length() && (W.charAt(w) == S.charAt(s) || W.charAt(w) == '?'))
      return dp[w][s] = matched_dp(w + 1, s + 1);

    // 위의 조건에서 아닌 경우는 3가지 경우가 존재한다.
    // 1. 모두 검사한 경우, 2. '*'가 나온 경우, 3. 글자가 다른 경우

    // CASE 1
    if (w == W.length()) {
      return (s == S.length()) ? 1 : 0;
    }

    // CASE 2
    if (W.charAt(w) == '*') {
      if (matched_dp(w + 1, s) == 1 || s < S.length() && matched_dp(w, s + 1) == 1) {
        return dp[w][s] = 1;
      }
    }

    // CASE 3
    return -1;

  }
}

// 완전 탐색

/*
 public class Main {
  static boolean perfect_match(String pat, String txt) {
    int pos = 0;
    int patLen = pat.length();
    int txtLen = txt.length();

    while (pos < patLen && pos < txtLen && (pat.charAt(pos) == txt.charAt(pos) || pat.charAt(pos) == '?'))
      pos++;

    if (pos == patLen)
      return pos == txtLen;

    if (pat.charAt(pos) == '*')
      for (int skip = 0; pos + skip <= txtLen; skip++)
        if (perfect_match(pat.substring(pos + 1), txt.substring(pos + skip)))
          return true;
    
    return false;
  }
}
 */