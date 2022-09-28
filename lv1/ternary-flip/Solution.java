import java.util.ArrayList;

class Solution {
  public int solution(int n) {
    int answer = 0;

    ArrayList<Integer> ternary = new ArrayList<>();

    makeFlipTernary(ternary,n);
    String s = "";
    for(int i = 0; i < ternary.size(); i++) {
      s += ternary.get(i);
    }

    answer = Integer.parseInt(s, 3);

    return answer;
  }
  void makeFlipTernary(ArrayList ternary, int n) {
    while (n > 0) {
      ternary.add(n % 3);
      n /= 3;
    }
  }
}