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

      int mess = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      jewels[i][0] = mess;
      jewels[i][1] = cost;
    }

    int[] bags = new int[K];
    for (int i = 0; i < K; i++) {
      bags[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(bags);
    Arrays.sort(jewels, (o1, o2) -> o1[0] - o2[0]);

    System.out.println(query(jewels, bags, N, K));
  }
  private static long query(int[][] jewels, int[] bags, int N, int K) {
    Queue<Integer> costPQ = new PriorityQueue<>((o1, o2) -> o2 - o1);

    long result = 0;

    int idx = 0;
    for (int i = 0; i < K; i++) {
      int bagWeight = bags[i];

      while (idx < N) {
        if (jewels[idx][0] <= bagWeight) {
          costPQ.add(jewels[idx][1]);
          idx++;
        } else {
          break;
        }
      }

      if (!costPQ.isEmpty()) result += costPQ.poll();
    }

    return result;
  }
}