import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- != 0) {
            int N = Integer.parseInt(br.readLine());

            if (N == 1) {
                System.out.println(1);
                continue;
            }
            Node[] tree = new Node[N];

            BFS(tree, N);
            if (tree[0] == null) {
                System.out.println("BRAK");
                continue;
            }
            StringBuilder sb = new StringBuilder();
            print(sb, tree, 0);
            System.out.println(sb.toString());
        }
    }
    private static void print(StringBuilder sb, Node[] tree, int cur) {

        if (cur == -1) {
            return;
        }

        print(sb, tree, tree[cur].prevValue);
        sb.append(tree[cur].num);
    }

    private static void BFS(Node[] tree, int N) {

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] isVisited = new boolean[N];

        q.add(1);
        isVisited[1] = true;
        tree[1] = new Node(-1, '1', 1);

        while (!q.isEmpty()) {
            Integer cur = q.poll();

            if (cur == 0) break;

            if (tree[cur].count == 100) continue;

            for (int i = 0; i <= 1; i++) {
                Integer nxt = (cur * 10 + i) % N;
                char num = (char)(i + '0');

                if (!isVisited[nxt]) {
                    tree[nxt] = new Node(cur, num, tree[cur].count + 1);
                    isVisited[nxt] = true;
                    q.add(nxt);
                }
            }
        }
    }
}
class Node {
    int prevValue;
    char num;
    int count;

    Node(int prevValue, char num, int count) {
        this.prevValue = prevValue;
        this.num = num;
        this.count = count;
    }
}