import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int max = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());
    int[][] allTimeTable = new int[N][2];
    int[][] timeTable = new int[N][2];

    for (int i = 0 ; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      allTimeTable[i][0] = Integer.parseInt(st.nextToken());
      allTimeTable[i][1] = Integer.parseInt(st.nextToken());
    }

    greedy(timeTable, allTimeTable, 0, 0);

    System.out.println(max);

  }

  static void greedy(int[][] timeTable, int[][] allTimeTable, int size,  int ptr) {

    if (ptr == N) {
      max = Math.max(max, size);
    }

    else {
      int idx = binarySearch(timeTable, 0, size - 1, allTimeTable[ptr]);

      if (idx != -1) {
        timeTable[idx] = allTimeTable[ptr];
        greedy(timeTable, allTimeTable, size + 1, ptr + 1);
        timeTable[idx] = null;
      }
      greedy(timeTable, allTimeTable, size, ptr + 1);
    }

  }

  static int binarySearch(int[][] time, int start, int end, int[] target) {

    if (end < 0) return 0;

    if (start < end) {
      int mid = (start + end) / 2;
      if (time[mid][0] < target[0]) return binarySearch(time, mid + 1, end, target);
      else return binarySearch(time, start, mid, target);
    }
    else {
      if (start == 0 && target[1] <= time[start][1]) return start;
      if (start != 0 && target[1] <= time[start][1] && time[start - 1][1] < target[1] ) return start;

      return -1;
    }
  }
}