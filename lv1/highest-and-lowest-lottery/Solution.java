
class Solution {
  public int[] solution(int[] lottos, int[] win_nums) {
    int[] answer = {};
    int count = 0;
    int zeroCount = 0;

    for(int i = 0; i < lottos.length; i++) {
      if(lottos[i] == 0) {
        zeroCount++;
      }
      else {
        // 중복인 경우가 제외되어 있기 때문에 고려 X
        for(int j = 0; j < win_nums.length; j++) {
          if(lottos[i] == win_nums[j]) {
            count++;
          }
        }
      }
    }

    int highestScore = count + zeroCount;

    answer = ranking(highestScore, count);

    return answer;
  }

  String ranking(int highestScore, int count){
    int highestRank;
    int lowestRank;
    switch (highestScore) {
      case 6: highestRank = 1;
        break;
      case 5: highestRank = 2;
        break;
      case 4: highestRank = 3;
        break;
      case 3: highestRank = 4;
        break;
      case 2: highestRank = 5;
        break;
      default: highestRank = 6;
        break;
    }
    switch (count) {
      case 6: lowestRank = 1;
        break;
      case 5: lowestRank = 2;
        break;
      case 4: lowestRank = 3;
        break;
      case 3: lowestRank = 4;
        break;
      case 2: lowestRank = 5;
        break;
      default: lowestRank = 6;
        break;
    }

    return new int[]{highestRank, lowestRank};
  }
}