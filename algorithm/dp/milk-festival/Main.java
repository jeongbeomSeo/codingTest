import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] milkArray = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            milkArray[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dpTable = new int[N][3];

        query(milkArray, dpTable, N);

        System.out.println(Math.max(Math.max(dpTable[N - 1][0], dpTable[N - 1][1]), dpTable[N - 1][2]));
    }
    private static void query(int[] milkArray, int[][] dp, int N) {
        // 첫 번째 우유 가게 처리
        if (milkArray[0] == 0) {
            dp[0][0] = 1; // 첫 번째 가게가 딸기우유인 경우
        }

        for (int i = 1; i < N; i++) {
            int milk = milkArray[i];

            // 이전 가게까지의 최대 우유 수를 그대로 유지
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = dp[i - 1][1];
            dp[i][2] = dp[i - 1][2];

            // 현재 우유에 따라 업데이트
            if (milk == 0) {
                dp[i][0] = Math.max(dp[i][0], dp[i - 1][2] + 1);
            } else if (milk == 1 && dp[i-1][0] > 0) { // 초코우유를 마시려면 이전에 딸기우유를 마셔야 함
                dp[i][1] = Math.max(dp[i][1], dp[i - 1][0] + 1);
            } else if (milk == 2 && dp[i-1][1] > 0) { // 바나나우유를 마시려면 이전에 초코우유를 마셔야 함
                dp[i][2] = Math.max(dp[i][2], dp[i - 1][1] + 1);
            }
        }
    }
}