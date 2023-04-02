import java.util.*;

public class Main {
  static int INF = Integer.MAX_VALUE;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    long N = sc.nextLong();

    Map<Long, Integer> map = new HashMap<>();

    bfs(map, N);

    System.out.println(map.get((long)1));
  }

  static void bfs(Map<Long, Integer> map, long N) {
    Queue<Long> q = new LinkedList<>();

    q.add(N);

    map.put(N, 0);

    while (!q.isEmpty()) {
      long curNum = q.poll();

      if (curNum % 3 == 0) {
        long nextNum = curNum / 3;
        if (!map.containsKey(nextNum)) {
          map.put(nextNum, map.get(curNum) + 1);
          q.add(nextNum);
        }
      }

      if (curNum % 2 == 0) {
        long nextNum = curNum / 2;
        if (!map.containsKey(nextNum)) {
          map.put(nextNum, map.get(curNum) + 1);
          q.add(nextNum);
        }
      }

      long nextNum = curNum - 1;
      if (!map.containsKey(nextNum)) {
        map.put(nextNum, map.get(curNum) + 1);
        q.add(nextNum);
      }
    }
  }
}