import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String[] matchMin = new String[9];
    static String[] dp = new String[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // matchMin 배열 초기화
        matchMin[2] = "1";
        matchMin[3] = "7";
        matchMin[4] = "4";
        matchMin[5] = "2";
        matchMin[6] = "0"; // '0'은 첫 자리에 올 수 없으므로 주의
        matchMin[7] = "8";
        matchMin[8] = "10";

        // dp 배열 초기화
        dp[2] = "1";
        dp[3] = "7";
        dp[4] = "4";
        dp[5] = "2";
        dp[6] = "6"; // dp[6]을 '6'으로 설정하여 첫 자리에 '0'이 오지 않도록 함
        dp[7] = "8";
        dp[8] = "10";

        // dp 배열 계산
        for (int i = 9; i <= 100; i++) {
            dp[i] = null;
            for (int j = 2; j <= 7; j++) {
                if (dp[i - j] == null) continue;
                String num = dp[i - j] + matchMin[j];
                // 숫자가 '0'으로 시작하지 않도록 처리
                if (num.charAt(0) == '0') continue;
                if (dp[i] == null || compareNumbers(num, dp[i]) < 0) {
                    dp[i] = num;
                }
            }
        }

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int matchesHave = Integer.parseInt(br.readLine());

            // 가장 작은 수 출력
            sb.append(dp[matchesHave]).append(" ");

            // 가장 큰 수 출력
            if (matchesHave % 2 == 0) {
                // 짝수인 경우 '1'을 최대한 많이 사용
                for (int i = 0; i < matchesHave / 2; i++) {
                    sb.append('1');
                }
            } else {
                // 홀수인 경우 '7'을 사용하고 나머지에 '1'을 최대한 많이 사용
                sb.append('7');
                for (int i = 0; i < (matchesHave - 3) / 2; i++) {
                    sb.append('1');
                }
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    // 숫자 문자열 비교 함수
    private static int compareNumbers(String a, String b) {
        if (a.length() != b.length()) {
            return a.length() - b.length();
        }
        return a.compareTo(b);
    }
}
