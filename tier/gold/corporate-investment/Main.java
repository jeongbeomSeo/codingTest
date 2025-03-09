import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // idx1: 투자 액수(원)
        // idx2: 기업
        // val: 이익
        int[][] matrix = new int[N + 1][M + 1];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int money = Integer.parseInt(st.nextToken());
            for (int j = 1; j < M + 1; j++) {
                matrix[money][j] = Integer.parseInt(st.nextToken());
            }
        }

        // idx1: 투자 액수
        // idx2: 순차 탐색한 기업의 수
        // val: 최대 이익
        int[][] dp = new int[N + 1][M + 1];

        // dp와 idx 동일
        // val: 이전 투자 액수
        int[][] trace = new int[N + 1][M + 1];

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                for (int k = 0; k <= i; k++) {
                    if (dp[i][j] < dp[i - k][j - 1] + matrix[k][j]) {
                        dp[i][j] = dp[i - k][j - 1] + matrix[k][j];
                        trace[i][j] = i - k;
                    }
                }
            }
        }

        System.out.println(dp[N][M]);

        Deque<Integer> stack = new ArrayDeque<>();
        int money = N;
        for (int i = M; i >= 1; i--) {
            stack.push(money - trace[money][i]);
            money = trace[money][i];
        }

        for (Integer value : stack) {
            System.out.print(value + " ");
        }
    }
}