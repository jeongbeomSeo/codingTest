import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dp = new int[10001];
        Arrays.fill(dp, Integer.MAX_VALUE);

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        List<Node> shortcuts = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            shortcuts.add(new Node(src, dst, cost));
        }

        Queue<Pos> q = new ArrayDeque<>();
        q.add(new Pos( 0, 0));
        while (!q.isEmpty()) {
            Pos curPos = q.poll();

            if (dp[curPos.pos] < curPos.cost) continue;

            if (curPos.pos >= D) {
                continue;
            }

            for (Node shrotcut : shortcuts) {
                if (curPos.pos == shrotcut.src && curPos.cost + shrotcut.cost < dp[shrotcut.dst]) {
                    q.add(new Pos(shrotcut.dst, curPos.cost + shrotcut.cost));
                    dp[shrotcut.dst] = curPos.cost + shrotcut.cost;
                }
            }

            if (dp[curPos.pos + 1] > curPos.cost + 1) {
                q.add(new Pos(curPos.pos + 1, curPos.cost + 1));
                dp[curPos.pos + 1] = curPos.cost + 1;
            }
        }

        System.out.println(dp[D]);
    }
}
class Pos {
    int pos;
    int cost;

    Pos (int pos, int cost) {
        this.pos = pos;
        this.cost = cost;
    }
}
class Node {
    int src;
    int dst;
    int cost;

    Node(int src, int dst, int cost) {
        this.src = src;
        this.dst = dst;
        this.cost = cost;
    }
}
