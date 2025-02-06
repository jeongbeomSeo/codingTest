import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] counts = new int[100001];

        int res = 0;
        Deque<Integer> dq = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            dq.addLast(num);
            if(++counts[num] > K) {
                while (!dq.isEmpty() && counts[num] > K) {
                    counts[dq.pollFirst()]--;
                }
            }

            res = Math.max(res, dq.size());
        }

        System.out.println(res);
    }
}
