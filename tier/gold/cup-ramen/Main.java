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

        Queue<Work> pq = new PriorityQueue<>((o1, o2) -> o1.deadLine - o2.deadLine);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int deadLine = Integer.parseInt(st.nextToken());
            int reward = Integer.parseInt(st.nextToken());
            pq.add(new Work(deadLine, reward));
        }

        Queue<Integer> result = new PriorityQueue<>((o1, o2) -> o1 - o2);

        while (!pq.isEmpty()) {
            Work curWork = pq.poll();

            if (result.size() < curWork.deadLine) {
                result.add(curWork.reward);
            } else {
                if (result.peek() < curWork.reward) {
                    result.poll();
                    result.add(curWork.reward);
                }
            }
        }

        Long sum = 0L;
        while (!result.isEmpty()) sum += result.poll();

        System.out.println(sum);
    }
}
class Work {
    int deadLine;
    int reward;

    Work(int deadLine, int reward) {
        this.deadLine = deadLine;
        this.reward = reward;
    }
}