class Solution {
  public int solution(int n, int k) {
    int answer = -1;
    String number = changeNumber(n , k);
    String isPrimeNumber = "";
    for(int i = 0; i < number.length(); i++) {
      String is = "";

    }

    return answer;
  }

  String changeNumber(int n, int k) {
    String reverseAns = "";
    String ans = "";
    while(true) {
      if (n / k != 0) {
      int s = n % k;
      reverseAns += s;
      n /= k;
      }
      else break;
    }
    for(int i = 0; i < reverseAns.length(); i++) {
      ans += reverseAns.charAt(reverseAns.length() - (i+1));
    }
    return ans;
  }
  boolean checkPrimeNumber(int n) {
    for(int i = 2; i < (int)Math.sqrt(n); i++) {
      if( n % i == 0) return false;
    }
    return  true;
  }
}