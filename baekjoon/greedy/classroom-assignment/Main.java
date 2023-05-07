import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Class[] classes = new Class[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      classes[i] = new Class(start, end);
    }

    Arrays.sort(classes);

    System.out.println(greedy(classes, N));

  }
  static int greedy(Class[] classes, int N) {
    Queue<Class> q = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);
    q.add(classes[0]);
    int ptr = 1;
    while (ptr < N) {
      Class curClass = q.peek();

      if (curClass.end <= classes[ptr].start) {
        q.poll();
      }
      q.add(new Class(classes[ptr].start, classes[ptr].end));
      ptr++;
    }
    return q.size();
  }
}

class Class implements Comparable<Class>{
  int start;
  int end;

  Class(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public int compareTo(Class o) {
    if (this.start > o.start) return 1;
    else if (this.start < o.start) return -1;
    else {
      return this.end - o.end;
    }
  }
}
