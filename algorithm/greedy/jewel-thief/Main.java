import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[][] jewels = new int[N][2];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      jewels[i][0] = Integer.parseInt(st.nextToken());  // 보석의 무게
      jewels[i][1] = Integer.parseInt(st.nextToken());  // 보석의 가격
    }

    int[] bags = new int[K];
    for (int i = 0; i < K; i++) {
      bags[i] = Integer.parseInt(br.readLine());  // 가방의 가용치 무게
    }

    Arrays.sort(jewels, (o1, o2) -> o1[0] - o2[0]);
    Arrays.sort(bags);

    System.out.println(query(jewels, bags, N, K));
  }
  private static long query(int[][] jewels, int[] bags, int N, int K) {

    long result = 0;
    Queue<Integer> costPQ = new PriorityQueue<>((o1, o2) -> o2 - o1);

    int jewelIdx = 0;
    for (int i = 0; i < K; i++) {
      int bagAvailableWeight = bags[i];

      while (jewelIdx < N) {
        if (jewels[jewelIdx][0] <= bagAvailableWeight) {
          costPQ.add(jewels[jewelIdx][1]);
          jewelIdx++;
        }
        else break;
      }

      if (!costPQ.isEmpty()) {
        result += costPQ.poll();
      }
    }

    return result;
  }
}