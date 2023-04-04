/*
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
 * 예를 들어, 수열 A = {10, 20, 10, 30, 20, 50}인 경우에는 가장 긴 증가하는 부분 수열은
 * {10, 20, 30, 50}이고, 길이는 4입니다.
 *
 * 수열 A의 크기 N (1 <= N <= 1,000)이 주어진다.
 * 수열 A를 이루고 있는 요소 a의 크기는 (1 <= a <= 1,000) 입니다.
 *
 * 예제 입력 1
 * 6
 * 10 20 10 30 20 50
 *
 * 예제 출력 1
 * 4
 */
public class Lis {
  static void lis(int[] arr, int[] dp, int N) {
    for (int k = 0; k < N; k++) {
      dp[k] = 1;
      for (int i = 0; i < k; i++) {
        if (arr[i] < arr[k]) {
          if (arr[i] < arr[k]) {
            dp[k] = Math.max(dp[k], dp[i] + 1);
          }
        }
      }
    }
  }

  static int binary_search(int[] lis, int left, int right, int target) {
    int mid;

    while (left < right) {
      mid = (left + right) / 2;

      if (lis[mid] < target) {
        left = mid  + 1;
      }
      else {
        right = mid;
      }
    }
    return right;
  }

  static int lis_dp(int[] lis, int[] arr, int N) {
    lis[0] = arr[0];

    int len = 1;
    for (int i = 1; i < N; i++) {
      if (arr[i] > lis[len - 1])
        lis[len++] = arr[i];
      else if (arr[i] < lis[len - 1]) {
        int idx = binary_search(lis, 0, len - 1, arr[i]);
        lis[idx] = arr[i];
      }
    }
    return len;
  }
}
