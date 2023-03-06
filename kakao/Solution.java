class Solution {
  public int[] solution(int[][] users, int[] emoticons) {
    int[] answer = new int[2];
    // 가입자면 true 미가입자면 false
    boolean[] subscriber = new boolean[users.length];

    int upperLimit = 0;
    int maxIndex = 0;
    int subscriberCount = 0;

    for(int i = 0; i < users.length; i++) {
       int maxMoney = 0;
       for(int j = 0; j < emoticons.length; j++) {
         maxMoney += emoticons[j] * users[i][0];
       }
       if(users[i][1] < maxMoney) {
         subscriberCount++;
         subscriber[i] = true;
         if(upperLimit < users[i][1]) {
           upperLimit = users[i][1];
           maxIndex = i;
         }
       }
    }

    int[] ratio = new int[emoticons.length];
    for(int i = 0; i < emoticons.length; i ++) {
      ratio[i] = 40;
    }

    int salesMoney= 0;
    int root = 0;
    int rotate = 0;

    while (true) {
      int checkSubscriber = 0;
      int sum = 0;
      int noSubuSum = 0;
      for(int i = 0; i < users.length; i++) {
        for(int j = 0; j < emoticons.length; j++) {
          if(ratio[i] - users[i][0] >= 0 ) {
            sum += emoticons[j] * (double)( 1.0 - (double) ratio[j]/ (double) 100);
          }
        }
        if(sum > users[i][1]) checkSubscriber++;
        else noSubuSum += sum;
      }
      if(checkSubscriber < subscriberCount) break;
      if(salesMoney < noSubuSum) salesMoney = noSubuSum;
      if(root > 0) ratio[root - 1] += 10 * rotate;
      if(root !=  emoticons.length) ratio[root++] -= 10 * rotate;
      else {
        rotate += 1;
        root = 0;
      }
    }



    return answer;
  }
}