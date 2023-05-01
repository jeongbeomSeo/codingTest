import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    Jewel[] jewels = new Jewel[N];
    int[] bags = new int[K];

    for (int i = 0 ; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int weight = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      jewels[i] = new Jewel(weight, cost);
    }

    for (int i = 0; i < K; i++) {
      bags[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(jewels, new Comparator<Jewel>() {
      @Override
      public int compare(Jewel o1, Jewel o2) {
        if (o1.weight == o2.weight)
          return o2.cost - o1.cost;
        return o1.weight - o2.weight;
      }
    });
    Arrays.sort(bags);

    System.out.println(greedy(jewels, bags, N, K));
  }
  static long greedy(Jewel[] jewels, int[] bags, int N, int K) {
    Queue<Jewel> pq = new PriorityQueue<>((o1, o2) -> o2.cost - o1.cost);
    long totalMoney = 0;

    int idx = 0;
    for (int i = 0 ; i < K; i++) {

      while (idx < N && jewels[idx].weight <= bags[i]) {
        pq.offer(jewels[idx++]);
      }

      if (!pq.isEmpty()) {
        totalMoney += pq.poll().cost;
      }
    }
    return totalMoney;
  }
}

class Jewel {
  int weight;
  int cost;

  Jewel(int weight, int cost) {
    this.weight = weight;
    this.cost = cost;
  }
}