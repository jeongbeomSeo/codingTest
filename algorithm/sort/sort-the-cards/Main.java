import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[] nums = new int[N];
    Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1 - o2);
    for (int i = 0; i < N; i++) {
      int num = Integer.parseInt(br.readLine());
      pq.add(num);
      nums[i] = num;
    }

    long result = 0L;

    while (pq.size() >= 2) {
      int card1 = pq.poll();
      int card2 = pq.poll();

      int nxtCard = card1 + card2;
      result += nxtCard;

      pq.add(nxtCard);
    }

    System.out.println(result);
  }
}