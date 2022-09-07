class Solution {
  public int[] solution(int N, int[] stages) {
    int[] answer = new int[N];
    double[][] rate = new double[N][2];
    int user = stages.length;


    for(int i = 0; i < N; i++) {
      int count = 0;
      int stage = i + 1;
      for(int j = 0; j < stages.length; j++) {
        if(stages[j] == stage) {
          count++;
        }
      }
      rate[i][0] = stage;
      rate[i][1] = (double)count/(double)user;
      user -= count;
    }

    for(int i = 0; i < rate.length; i++) {
      for(int j = i + 1; j < rate.length; j++) {
        if(rate[i][1] < rate[j][1]) {
          double[] temp;
          temp = rate[j];
          rate[j] = rate[i];
          rate[i] = temp;
        }
        if(rate[i][1] == rate[j][1] && rate[i][0] > rate[j][0]) {
          double[] temp;
          temp = rate[j];
          rate[j] = rate[i];
          rate[i] = temp;
        }
      }
      answer[i] = (int)rate[i][0];
    }

    return answer;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int N = 4;
    int[] stages = {4, 4, 4, 4, 4};

    int[] array = solution.solution(N, stages);
    for(int i = 0; i < array.length; i++) {
      if(i < array.length -1 ) System.out.print(array[i] +", ");
      else System.out.println(array[i]);
    }
  }
}

