import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int MIN = 0;
  static int MAX = 1000000000;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());

     int[] house = new int[N];

     for (int i = 0; i < N; i++) {
       house[i] = Integer.parseInt(br.readLine());
     }

    Arrays.sort(house);
    int min = 1;
    int max = house[N - 1];

    System.out.println(binarySearch(house, min, max + 1, N, C));

  }
  static int binarySearch(int[] house, int min, int max, int N, int C) {

    while (min < max) {
      int mid = (min + max) / 2;

      int beforeIdx = 0;
      int count = 1;
      for(int i = 1; i < N; i++) {
        if (house[i] - house[beforeIdx] >= mid) {
          count++;
          beforeIdx = i;
        }
      }

      if (count >= C) min = mid + 1;
      else max = mid;
    }

    return min - 1;
  }
}
