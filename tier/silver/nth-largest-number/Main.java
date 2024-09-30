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

        Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                pq.add(Integer.parseInt(st.nextToken()));
            }
        }

        while (--N != 0) {
            pq.poll();
        }

        System.out.println(pq.poll());
    }
}
