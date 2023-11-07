import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, K;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    Item[] items = new Item[N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      int weight = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      items[i] = new Item(weight, cost);
    }

    int[] bags = new int[K];
    for (int i = 0; i < K; i++) {
      bags[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(items);
    Arrays.sort(bags);

    System.out.println(queryResult(items, bags));
  }
  private static long queryResult(Item[] items, int[] bags) {
    Queue<Item> pq = new PriorityQueue<>((o1, o2) -> o2.cost - o1.cost);

    int idx = 0;
    long result = 0;
    for (int i = 0; i < K; i++) {

      while (idx < N && bags[i] >= items[idx].weight) {
        pq.add(items[idx++]);
      }

      if (!pq.isEmpty()) {
        result += pq.poll().cost;
      }
    }

    return result;
  }
}
class Item implements Comparable<Item>{
  int weight;
  int cost;

  Item(int weight, int cost) {
    this.weight = weight;
    this.cost = cost;
  }

  @Override
  public int compareTo(Item o) {
    return this.weight - o.weight;
  }
}