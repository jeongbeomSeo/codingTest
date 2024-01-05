import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1 - o2);

    for (int i = 0; i < N; i++) {
      pq.add(Integer.parseInt(br.readLine()));
    }

    int result = 0;
    while (pq.size() != 1) {
      int num1 = pq.poll();
      int num2 = pq.poll();

      int sum = num1 + num2;
      result += sum;
      pq.add(sum);
    }

    System.out.println(result);
  }
}