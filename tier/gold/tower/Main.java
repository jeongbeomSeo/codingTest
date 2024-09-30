import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Deque<Node> stack = new ArrayDeque<>();

        int N = Integer.parseInt(br.readLine());
        int[] history = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && stack.peekFirst().height < height) stack.pop();

            if (!stack.isEmpty()) history[i] = stack.peekFirst().idx + 1;

            stack.push(new Node(i, height));
        }

        for (int ans : history) System.out.print(ans + " ");
    }
}
class Node {
    int idx;
    int height;

    Node(int idx, int height) {
        this.idx = idx;
        this.height = height;
    }
}