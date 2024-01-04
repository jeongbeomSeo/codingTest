import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(br.readLine());
    }

    List<Integer> buffer = new ArrayList<>();
    for (int i = 0; i < K; i++) {
      int idx = upperBound(buffer, 0, i, array[i]);
      buffer.add(idx, array[i]);
    }

    long result = 0L;
    int getResultIdx = (K + 1) / 2 - 1;
    result += buffer.get(getResultIdx);

    for (int i = K; i < N; i++) {
      int removeIdx = lowerBound(buffer, 0, K, array[i - K]);
      buffer.remove(removeIdx);

      int addIdx = upperBound(buffer, 0, K - 1, array[i]);
      buffer.add(addIdx, array[i]);
      result += buffer.get(getResultIdx);
    }

    System.out.println(result);
  }
  private static int lowerBound(List<Integer> buffer, int left, int right, int value) {
    while (left < right) {
      int mid = (left + right) / 2;

      if (buffer.get(mid) < value) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left;
  }
  private static int upperBound(List<Integer> buffer, int left, int right, int value) {
    while (left < right) {
      int mid = (left + right) / 2;

      if (buffer.get(mid) <= value) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left;
  }
}