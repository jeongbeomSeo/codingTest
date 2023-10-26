import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String str = br.readLine();
    Stack<Character> stack = new Stack<>();
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < str.length(); i++) {
      char curChar = str.charAt(i);

      if (curChar >= 'A' && curChar <= 'Z') sb.append(curChar);
      else if (stack.isEmpty()) stack.add(curChar);
      else {
        if (curChar == '+' || curChar == '-') {
          while (!stack.isEmpty() && stack.peek() != '(') sb.append(stack.pop());
          stack.add(curChar);
        }
        else if (curChar == '*' || curChar == '/') {
          if (stack.peek() == '*' || stack.peek() == '/') sb.append(stack.pop());
          stack.add(curChar);
        }
        else if (curChar == '(') stack.add(curChar);
        else {
          while (stack.peek() != '(') sb.append(stack.pop());
          stack.pop();
        }
      }
    }
    while (!stack.isEmpty()) sb.append(stack.pop());

    System.out.println(sb);
  }
}