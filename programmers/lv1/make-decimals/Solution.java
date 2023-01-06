class Solution {
  public int solution(int[] nums) {
    int answer = 0;

    for (int i = 0; i < nums.length - 2; i++) {
      for (int j = i+1; j < nums.length - 1; j++) {
        for(int k = j+1; k < nums.length; k++) {
          int result = nums[i] + nums[j] + nums[k];
          if(isPrime(result)) {
            answer++;
          }
        }
      }
    }
    // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    System.out.println("Hello Java");

    return answer;
  }
  public boolean isPrime(int n) {
    for(int i = 2; i <=Math.sqrt(n); i++) {
      if(n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int[] input = {1, 2, 3, 4};
    Solution solution = new Solution();
    System.out.println(solution.solution(input));
  }
}