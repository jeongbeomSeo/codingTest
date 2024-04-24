import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Queue<Integer[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (!o1[0].equals(o2[0])) return o1[0] - o2[0];
            return o2[1] - o1[1];
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int deadLine = Integer.parseInt(st.nextToken());
            int reward = Integer.parseInt(st.nextToken());
            pq.add(new Integer[] {deadLine, reward});
        }

        int result = 0;
        for (int i = 0; i <= N; i++) {
            while (!pq.isEmpty() && pq.peek()[0] <= i) {
                pq.poll();
            }

            if (pq.isEmpty()) break;

            result += pq.poll()[1];
        }

        System.out.println(result);
    }
}