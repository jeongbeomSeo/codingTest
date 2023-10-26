import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int maxSize = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Scheduler[] schedulers = new Scheduler[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      schedulers[i] = new Scheduler(start, end);
    }
    Arrays.sort(schedulers);

    Scheduler[] timeTable = new Scheduler[N];

    System.out.println(greedy(schedulers, timeTable, N));
  }
  static int greedy(Scheduler[] schedulers, Scheduler[] timeTable, int N) {

    int size = 0;
    timeTable[size++] = schedulers[0];
    for (int i = 1; i < N; i++) {
      if (timeTable[size - 1].end <= schedulers[i].start) {
        timeTable[size++] = schedulers[i];
      }
      else {
        if (timeTable[size - 1].end > schedulers[i].end) {
          timeTable[size - 1] = schedulers[i];
        }
      }
    }
    return size;
  }
}
class Scheduler implements Comparable<Scheduler>{
  int start;
  int end;

  Scheduler(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public int compareTo(Scheduler o) {
    return this.start - o.start;
  }
}