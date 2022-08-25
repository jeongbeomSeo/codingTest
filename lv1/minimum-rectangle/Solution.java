class Solution {
  public int solution(int[][] sizes) {
    int answer = 0;
    for(int i = 0; i < sizes.length; i++) {
      if(sizes[i][1] > sizes[i][0]) {
        int temp = sizes[i][1];
        sizes[i][1] = sizes[i][0];
        sizes[i][0] = temp;
      }
    }
    int maxIndex = 0;
    int diffColMaxIndex = 0;
    for(int i = 1; i < sizes.length; i++) {
      if(sizes[maxIndex][0] < sizes[i][0]) {
        maxIndex = i;
      }
      if(sizes[diffColMaxIndex][1] < sizes[i][1]) {
        diffColMaxIndex = i;
      }
    }

    answer = sizes[maxIndex][0] * sizes[diffColMaxIndex][1];


    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};

    System.out.println(solution.solution(sizes));
  }
}