import java.util.Stack;

class Solution {
  public int solution(String s) {
    int answer = 0;
    for(int i = 0; i < s.length() - 1; i++) {
      Stack<Character> brackets = new Stack<>();
      boolean check = true;
      for(int j = i; j < s.length() + i; j++) {
        int index = j;
        if(index >= s.length()) index %= s.length();
        // 처음에 나오는 것 cut
        if(brackets.empty() && (s.charAt(index) == '}' || s.charAt(index) == ']' || s.charAt(index) == ')') ) {
          check = false;
          break;
        }
        else if(s.charAt(index) == '{' || s.charAt(index) == '[' || s.charAt(index) == '(') brackets.add(s.charAt(index));
        else if(s.charAt(index) == '}') {
          if(brackets.pop() == '{') continue;
          else {
            check = false;
            break;
          }
        }
        else if(s.charAt(index) == ']') {
          if (brackets.pop() =='[') continue;
          else {
            check = false;
            break;
          }
        }
        else if(s.charAt(index) == ')') {
          if (brackets.pop() == '(') continue;
          else {
            check = false;
            break;
          }
        }
      }
      if(brackets.empty() && check) answer++;
    }
    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String input = 	"[](){}";
    System.out.println(solution.solution(input));
  }
}