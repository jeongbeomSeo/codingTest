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

    int[][] knapsack = new int[N + 1][3];
    int[][] dp = new int[N + 1][M + 1];
    int[][] remainder = new int[N + 1][M + 1];

    for (int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());

      knapsack[i][0] = Integer.parseInt(st.nextToken());
      knapsack[i][1] = Integer.parseInt(st.nextToken());
      knapsack[i][2] = Integer.parseInt(st.nextToken());
    }

    knapsack_dp(knapsack, dp, remainder, N, M);

    System.out.println(dp[N][M]);
  }
  static void knapsack_dp(int[][] knapsack, int[][] dp, int[][] remainder, int N, int M) {
    for (int i = 1; i < N + 1; i++) remainder[0][i] = i;

    for (int i = 1; i < N + 1; i++) {
      int itemWeight = knapsack[i][0];
      int itemSatisfy = knapsack[i][1];
      int itemAmount = knapsack[i][2];
      for (int w = 1; w < M + 1; w++) {
        if (itemWeight > w) {
          dp[i][w] = dp[i - 1][w];
        }
        else {
          int canUseItemAmount = (w / itemWeight) > itemAmount ? itemAmount : w / itemWeight;
          int itemTotalWeight = canUseItemAmount * itemWeight;
          int diagonalRemainder = w - itemTotalWeight;

          int diagonalTotalSatisfy = dp[i - 1][diagonalRemainder] + itemSatisfy * canUseItemAmount;
          int upTotalSatisfy = dp[i - 1][w] + dp[i][remainder[i - 1][w]];

          if (diagonalTotalSatisfy > upTotalSatisfy) {
            dp[i][w] = diagonalTotalSatisfy;
            remainder[i][w] = remainder[i - 1][diagonalRemainder];
          }
          else if (diagonalTotalSatisfy == upTotalSatisfy) {
            if (remainder[i - 1][diagonalRemainder] >= remainder[i - 1][w]) {
              dp[i][w] = diagonalTotalSatisfy;
              remainder[i][w] = remainder[i - 1][diagonalRemainder];
            }
            else {
              dp[i][w] = upTotalSatisfy;
              remainder[i][w] = remainder[i - 1][w] % itemWeight;
            }
          }
          else {
            dp[i][w] = upTotalSatisfy;
            remainder[i][w] = remainder[i - 1][w] % itemWeight;
          }
        }
      }
    }
  }
}