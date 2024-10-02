import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        long[] lengths = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            lengths[i] = Long.parseLong(st.nextToken());
        }

        long[] costs = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            costs[i] = Long.parseLong(st.nextToken());
        }

        long curCost = 0L;
        long curGas = 0L;
        long minCost = Long.MAX_VALUE;
        for (int i = 0; i < N - 1; i++) {
            minCost = Math.min(minCost, costs[i]);

            if (curGas < lengths[i]) {
                long diff = lengths[i] - curGas;
                curGas += diff - lengths[i];   // 0
                curCost += diff * minCost;
            } else {
                curGas -= lengths[i];
            }
        }

        System.out.println(curCost);
    }
}
