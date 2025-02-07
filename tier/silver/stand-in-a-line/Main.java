import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Deque<Node> stack = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int count = Integer.parseInt(st.nextToken());
            stack.addLast(new Node(i, count));
        }

        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();

            list.add(node.count, node.idx);
        }

        StringBuilder sb = new StringBuilder();
        for (Integer e : list) {
            sb.append(e).append(" ");
        }

        System.out.println(sb.toString());

    }
}
class Node {
    int idx;
    int count;

    public Node(int idx, int count) {
        this.idx = idx;
        this.count = count;
    }
}