public class Solution {
  public int solution(int n) {
    int ans = 0;

    while (n > 0) {
      if (n % 2 != 0) {
        n = (n - 1) / 2;
        ans++;
      } else {
        n = n / 2;
      }
    }
    // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    System.out.println("Hello Java");

    return ans;
  }
}