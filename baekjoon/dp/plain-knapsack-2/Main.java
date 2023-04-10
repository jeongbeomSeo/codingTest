import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    // row: 물건, col: 최대 허용치 무게, value: {만족도, 가방에 남은 무게}
    int[][][] dp = new int[N + 1][M + 1][2];

    // row: 물건, col(value): {물건의 무게, 민호의 만족도, 물건의 개수}
    int[][] knapsack = new int[N + 1][3];

    for (int i = 1 ; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      knapsack[i][0] = Integer.parseInt(st.nextToken());
      knapsack[i][1] = Integer.parseInt(st.nextToken());
      knapsack[i][2] = Integer.parseInt(st.nextToken());
    }

    for (int i = 0; i < M + 1; i++) {
      dp[0][i][1] = M;
    }

    knapsack_dp(dp, knapsack, N, M);

    System.out.println(dp[N][M][0]);
  }

  static void knapsack_dp(int[][][] dp, int[][] knapsack, int N, int M) {
    for (int i = 1; i < N + 1; i++) {
      for (int w = 1 ; w < M + 1; w++) {
        int curThingWeight = knapsack[i][0];
        int happyPoint = knapsack[i][1];
        int remainNum = knapsack[i][2];

        if (curThingWeight > w) {
          dp[i][w][0] = dp[i - 1][w][0];
          dp[i][w][1] = dp[i - 1][w][1];
        }
        else {
          int remainWeight = dp[i - 1][w - curThingWeight][1];
          // 남은 무게 / 물건 무게 => 무게 기준 가방에 넣을 수 있는 물건 갯수
          int calcNum = remainWeight / curThingWeight;
          // 최종적으로 해당 물건 사용 가능한 갯수: 현재 가지고 있는 물건의 갯수와 비교 => 가지고 있는 갯수가 더 크면 가방에 넣을 수 있을 만큼 물건 전부 넣고, 아니면 가지고 있는거 전부 넣기
          int canUseNum = calcNum >= remainNum ? remainNum : calcNum;
          if (dp[i - 1][w][0] >= dp[i - 1][w - curThingWeight][0] + happyPoint * canUseNum) {
            dp[i][w][0] = dp[i - 1][w][0];
            dp[i][w][1] = dp[i - 1][w][1];
          }
          else {
            dp[i][w][0] = dp[i - 1][w - curThingWeight][0] + happyPoint * canUseNum;
            dp[i][w][1] = dp[i - 1][w - curThingWeight][1] - curThingWeight * canUseNum;
          }
        }
      }
    }
  }
}
