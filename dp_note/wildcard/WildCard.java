/*
 * 해당 문제는 알고스팟의 와일드 카드 문제를 기반으로 책에 나와있는 로직대로 풀어본 예시코드입니다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class WildCard {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int C = Integer.parseInt(br.readLine());

    // 테스트 카운트만큼 실행
    while (C-- > 0) {
      // 와일드 카드 문자열 입력 받기 
      String W = br.readLine();

      // 주어지는 문자열의 갯수 입력 받기
      int N = Integer.parseInt(br.readLine());

      String[] S = new String[N];

      // 문자열 입력 받기
      for (int i = 0; i < N; i++) {
        S[i] = br.readLine();
      }

      ArrayList<String> str = new ArrayList<>();
      dp_match(W, S, str);

      // 아스키 코드의 순번에 맞게 출력 (오름차순 정렬)
      Collections.sort(str);
      for (String s : str) System.out.println(s);
      str = null;
    }
  }

  static void dp_match(String W, String[] S, ArrayList<String> str) {


    for (int idx = 0; idx < S.length; idx++) {
      // 와일드 카드를 행으로, 비교 문자열을 열로 생각하고 첫뻔째 열은 빈 문자열 그 이후부터 문자당 한칸씩 boolean값으로 차지.
      boolean[][] dp = new boolean[2][101];

      int w = W.length();
      String curStr = S[idx];
      int s = curStr.length();

      // 빈 문자열은 true로 설정 
      dp[1][0] = true;

      int i, j;
      for (i = 0; i < w; i++) {
        // 매번 해당 dp 테이블은 초기화 
        Arrays.fill(dp[i % 2], false);

        boolean edit = false;
        if (W.charAt(i) == '*') {
          for (j = 0; j <= s && !dp[1 - i % 2][j]; j++);
          // '*'의 경우 0개의 문자가 와도 되기 때문에 j + 1부터 채워주는 것이 아니라 j부터 채워주는 것이다.
          Arrays.fill(dp[i % 2], j, s + 1, true);
          edit = true;
        }
        else {
          for (j = 0; j < s; j++) {
            // 대각선이 true이면서 문자가 같은 경우, 혹은 대각선이 true이면서 와일드 카드 문자가 ?가 나온 경우 
            if (dp[1 - i % 2][j] && (W.charAt(i) == curStr.charAt(j) || W.charAt(i) == '?')) {
              dp[i % 2][j + 1] = true;
              edit = true;
            }
          }
        }
        if (!edit) break;
      }
      if (i == w && dp[1 - i % 2][s]) str.add(curStr);
    }

  }
}

