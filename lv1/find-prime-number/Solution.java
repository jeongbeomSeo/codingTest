class Solution {
  public int solution(int n) {
    int answer = 0;

    for(int i = 2; i <= n ; i++ ) {
      if(findDecimals(i)) answer++;
    }
    return answer;
  }
  boolean findDecimals(int n) {
    for (int i = 2; i <= (int)Math.sqrt(n); i++) {
      if( n % i == 0) {
        return false;
      }
    }
    return true;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.solution(10));
  }
}