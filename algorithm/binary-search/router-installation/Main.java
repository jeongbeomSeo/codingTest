import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
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
    int min = house[0];
    int max = house[N - 1];

    System.out.println(upper_bound(house, 0, max - min + 1, C, N));
  }
  private static int upper_bound(int[] house, long left, long right, int target, int N) {
    while (left < right) {
      long mid = (left + right) / 2;

      int count = queryCanInstallationCount(house, N, mid);

      if (count >= target) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return (int)(left - 1);
  }
  private static int queryCanInstallationCount(int[] house, int size, long distance) {

    int count = 1;
    int lastIdx = 0;
    for (int i = 1; i < size; i++) {
      if (house[i] - house[lastIdx] >= distance) {
        count++;
        lastIdx = i;
      }
    }
    return count;
  }
}