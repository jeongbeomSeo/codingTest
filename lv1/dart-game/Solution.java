import java.util.Stack;

class Solution {
  public int solution(String dartResult) {
    int answer = 0;

    Stack<Integer> stack = new Stack<>();

    for(int i = 0; i < dartResult.length(); i++) {
      if(dartResult.charAt(i) >= '0' && dartResult.charAt(i) <= '9') {
        int num = dartResult.charAt(i) - '0';
        if(dartResult.charAt(i+1) == '0') {
          num = 10;
          i++;
        }
        stack.push(num);
        continue;
      }
      else if(dartResult.charAt(i) > 'A') {
        if(dartResult.charAt(i) == 'D') {
          stack.push((int)Math.pow(stack.pop(),2));
        }
        else if(dartResult.charAt(i) == 'T') stack.push((int)Math.pow(stack.pop(),3));
        continue;
      }
      else {
        if (dartResult.charAt(i) == '#') {
          stack.push(stack.pop() * (-1));
        }
        else {
          int pop1 = stack.pop() * 2;
          if(!stack.empty()) stack.push(stack.pop() * 2);
          stack.push(pop1);
        }
        continue;
      }
    }

    answer = stack.pop() + stack.pop() + stack.pop();

    return answer;
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    String dartResult = "1S*2T*3S";
    System.out.println(solution.solution(dartResult));
  }
}