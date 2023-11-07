import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  static int N;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());

    Time[] times = new Time[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());

      times[i] = new Time(start, end);
    }

    Arrays.sort(times);

    System.out.println(queryResult(times));
  }
  private static int queryResult(Time[] times) {

    Queue<Time> q = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);

    q.add(times[0]);
    int maxSize = 1;
    for (int i = 1; i < N; i++) {
      if (!q.isEmpty() && q.peek().end <= times[i].start) {
        while (!q.isEmpty() && q.peek().end <= times[i].start) q.poll();
        q.add(times[i]);
      } else {
        q.add(times[i]);
        maxSize = Math.max(maxSize, q.size());
      }
    }

    return maxSize;
  }
}
class Time implements Comparable<Time> {
  int start;
  int end;

  Time(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public int compareTo(Time o) {
    if (this.start - o.start != 0) return this.start - o.start;

    return this.end - o.end;
  }
}