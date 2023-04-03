import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    long N = Long.parseLong(br.readLine());

    Map<Long, Long> map = new HashMap<>();
    Queue<Long> q = new ArrayDeque<>();

    q.offer(N);
    map.put(N, 0L);

    while (!q.isEmpty()) {
      long n = q.poll();
      long curCnt = map.get(n);

      if (n == 1) {
        System.out.println(curCnt);
        break;
      }

      if (n % 3 == 0 && n / 3 >= 1) {
        long nextNum = n / 3;
        if (!map.containsKey(nextNum)) {
          map.put(nextNum, curCnt + 1);
          q.offer(nextNum);
        }
      }
      if (n % 2 == 0 && n / 2 >= 1) {
        long nextNum = n / 2;
        if (!map.containsKey(nextNum)) {
          map.put(nextNum, curCnt + 1);
          q.offer(nextNum);
        }
      }
      if (n - 1 >= 1) {
        long nextNum = n - 1;
        if (!map.containsKey(n - 1)) {
          map.put(nextNum, curCnt + 1);
          q.offer(nextNum);
        }
      }
    }
  }
}