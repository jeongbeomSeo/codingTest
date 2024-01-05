import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String str = br.readLine();

    System.out.println(getPostPrefix(str));
  }
  private static String getPostPrefix(String str) {

    StringBuilder sb = new StringBuilder();
    Deque<Character> stack = new ArrayDeque<>();
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);

      if (isOpr(c)) {
        if (c == '*' || c == '/') {
          while (!stack.isEmpty() && (stack.peekFirst() == '*' || stack.peekFirst() == '/')) {
            sb.append(stack.pollFirst());
          }
          stack.addFirst(c);
        } else {
          while (!stack.isEmpty() && stack.peekFirst() != '(') {
            sb.append(stack.pollFirst());
          }
          stack.addFirst(c);
        }
      } else if (c == '(') {
        stack.addFirst(c);
      } else if (c == ')') {
        while (!stack.isEmpty() && stack.peekFirst() != '(') {
          sb.append(stack.pollFirst());
        }
        stack.pollFirst();
      } else {
        sb.append(c);
      }
    }
    while (!stack.isEmpty()) sb.append(stack.pollFirst());

    return sb.toString();
  }
  private static boolean isOpr(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/';
  }
}