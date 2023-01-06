class Solution {
  static boolean check[] = new boolean[5];

  public int solution(int[] numbers, int target) {
    int answer = 0;
    return answer;
  }

  int findTarget(int[] numbers, int minusSum, int plusSum, int target, int nums) {
    if(!check[nums]) {
      plusSum +=  findTarget(numbers, minusSum, plusSum, target, nums);
      minusSum -= findTarget(numbers, minusSum, plusSum, target, nums);
    }
    nums++;

    return
  }

}