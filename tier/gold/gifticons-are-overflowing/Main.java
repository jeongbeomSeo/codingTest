import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Node> pq = new PriorityQueue<>(getNodeComparator());

        /**
         * 1. 사용할 계획을 우선 정렬한 뒤
         * 2. 기프티콘 정보(Node)를 전부 pq에 넣어주면서
         * 3. 현재 사용할 계획(idx)와 일치하면서 해당 계획의 time이 넘어갈 때 까지 계획 전부 넘기기 (해당 계획 time만 넘어갈 정도로)
         * 4. idx 일치시 처리한 후 다음 계획 처리
         * 5. 계속해서 반복
         */

        Node[] gifts = new Node[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int remainTime = Integer.parseInt(st.nextToken());
            gifts[i] = new Node(i, remainTime);

            pq.add(gifts[i]);
        }

        Node[] schedules = new Node[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int scheduleTime = Integer.parseInt(st.nextToken());
            schedules[i] = new Node(i, scheduleTime);
        }

        Arrays.sort(schedules, getNodeComparator());

        int cur = 0;
        long count = 0L;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            // 현재 처리 예정 계획이 아닌 경우
            if (schedules[cur].idx != node.idx) {
                // 1. 현재 처리 예정인 기프티콘보다 시간이 높아야 하며,
                if (gifts[schedules[cur].idx].time > node.time) {
                    long diff = gifts[schedules[cur].idx].time - node.time;
                    if (diff % 30 != 0) diff += 30;
                    count += diff / 30;
                    node.time += 30 * (diff / 30);
                }
                // 2. 처리 예정되어 있는 시간보다도 높아야 한다.
                if (schedules[cur].time > node.time) {
                    long diff = schedules[cur].time - node.time;
                    if (diff % 30 != 0) diff += 30;
                    count += diff / 30;
                    node.time += 30 * (diff / 30);
                }

                // 3. 그대로 다시 pq로 넣어준다.
                pq.add(node);
            } else {
                // 1. 기한 연장을 하지 않아도 되는 경우
                if (schedules[cur].time <= node.time) {
                    cur++;
                } else {
                    // 2. 기간 연장을 해야되는 경우
                    long diff = schedules[cur].time - node.time;
                    if (diff % 30 != 0) diff += 30;
                    count += diff / 30;
                    node.time += 30 * (diff / 30);
                    pq.add(node);
                }
            }
        }

        System.out.println(count);
    }

    private static Comparator<Node> getNodeComparator() {
        return (o1, o2) -> {
            if (o1.time != o2.time) return Long.compare(o1.time, o2.time);
            return o1.idx - o2.idx;
        };
    }
}
class Node {
    int idx;
    long time;

    public Node(int idx, long time) {
        this.idx = idx;
        this.time = time;
    }
}
