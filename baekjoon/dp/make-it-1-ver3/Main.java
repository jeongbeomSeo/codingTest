import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Main {
  static final long INF = Long.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    long N = Long.parseLong(br.readLine());

    Map<Long, Long> map = new HashMap<>();
    Queue<Long> queue = new ArrayDeque<>();

    map.put(N, 0L);
    queue.offer(N);

    while (!queue.isEmpty()) {
      long curNum = queue.poll();
      long curCnt = map.get(curNum);

      if (curNum == 1) {
        System.out.println(curCnt);
        return;
      }

      if (curNum % 3 == 0 && curNum / 3 >= 1) {
        long nextNum = curNum / 3;
        if (!map.containsKey(nextNum)) {
          map.put(nextNum, curCnt + 1);
          queue.offer(nextNum);
        }
      }

      if (curNum % 2 == 0 && curNum / 2 >= 1) {
        long nextNum = curNum / 2;
        if (!map.containsKey(nextNum)) {
          map.put(nextNum, curCnt + 1);
          queue.offer(nextNum);
        }
      }

      if (curNum - 1 >= 1) {
        long nextNum = curNum - 1;
        if (!map.containsKey(nextNum)) {
          map.put(nextNum, curCnt + 1);
          queue.offer(nextNum);
        }
      }
    }
  }
}