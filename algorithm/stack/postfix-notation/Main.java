import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String str = br.readLine();

    StringBuffer sb = new StringBuffer();
    Deque<Character> stackOper = new ArrayDeque<>();
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);

      if (isOper(c)) {
        if (stackOper.isEmpty()) {
          stackOper.addFirst(c);
        } else {
          if (lowPriorityOper(c)) {
            // 현재 연산자: + || -
            while (!stackOper.isEmpty() && !isOpenBracket(stackOper.peekFirst())) {
              sb.append(stackOper.pollFirst());
            }
            stackOper.addFirst(c);
          } else {
            // 현재 연산자: * 또는 /
            if (lowPriorityOper(stackOper.peekFirst()) || isOpenBracket(stackOper.peekFirst())) {
              stackOper.addFirst(c);
            } else {
              sb.append(stackOper.pollFirst());
              stackOper.addFirst(c);
            }
          }
        }
      } else if (isBracket(c)) {
        if (isOpenBracket(c)) {
          stackOper.addFirst(c);
        } else {
          while (!isOpenBracket(stackOper.peekFirst())) {
            sb.append(stackOper.pollFirst());
          }
          stackOper.pollFirst();
        }
      } else {
        sb.append(c);
      }
    }

    while (!stackOper.isEmpty()) sb.append(stackOper.pollFirst());
    System.out.println(sb);
  }
  private static boolean lowPriorityOper(char c) {
    return c == '+' || c == '-';
  }
  private static boolean isOper(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/';
  }
  private static boolean isBracket(char c) {
    return c == '(' || c == ')';
  }
  private static boolean isOpenBracket(char c) {
    return c == '(';
  }
}