import java.io.*;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> top = new PriorityQueue<>((o1, o2) -> o1 - o2);
        PriorityQueue<Integer> bottom = new PriorityQueue<>((o1, o2) -> o2 - o1);

        top.add(10001);
        bottom.add(-10001);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            bottom.add(num);

            if (bottom.peek() > top.peek()) {
                int bottomNum = bottom.poll();
                int topNum = top.poll();

                top.add(bottomNum);
                bottom.add(topNum);
            }

            if (bottom.size() == top.size() + 2) {
                top.add(bottom.poll());
            }

            bw.write(bottom.peek() + "\n");
        }

        bw.flush();
        bw.close();
    }
}
