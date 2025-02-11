import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] chain = new int[N + 1];

        Queue<Integer> q = new ArrayDeque<>();
        q.add(N);

        List<Integer> res = new ArrayList<>();

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == 1) {
                while (cur != N) {
                    res.add(cur);
                    cur = chain[cur];
                }
                res.add(cur);
                break;
            }

            if (cur % 3 == 0 && chain[cur / 3] == 0) {
                chain[cur / 3] = cur;
                q.add(cur / 3);
            }
            if (cur % 2 == 0 && chain[cur / 2] == 0) {
                chain[cur / 2] = cur;
                q.add(cur / 2);
            }
            if (chain[cur - 1] == 0) {
                chain[cur - 1] = cur;
                q.add(cur - 1);
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(res.size() - 1 + "\n");
        for (int i = res.size() - 1; i >= 0; i--) {
            bw.write(res.get(i) + " ");
        }

        bw.flush();
        bw.close();
    }
}
