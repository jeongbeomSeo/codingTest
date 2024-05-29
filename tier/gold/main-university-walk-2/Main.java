import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final long[][] defaultGraph = {
            {0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1},
            {0, 1, 0, 1, 0, 0, 1, 1},
            {0, 0, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 1, 0, 1, 0, 1},
            {1, 1, 1, 0, 0, 0, 1, 0}
    };
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        System.out.println(query(N));
    }
    private static int query(int N) {

        long[][] result = new long[8][8];
        for (int i = 0; i < 8; i++) result[i][i] = 1;

        while (N != 0) {
            int t = 0;
            while (Math.pow(2, t + 1) <= N) t++;

            result = matrixMultiplication(result, getGraph((int)Math.pow(2, t)));
            N -= (int)Math.pow(2, t);
        }

        return (int)(result[0][0] % MOD);
    }
    private static long[][] getGraph(int n) {

        if (n == 1) {
            return defaultGraph;
        }

        long[][] resultGraph = getGraph(n / 2);
        return matrixMultiplication(resultGraph, resultGraph);
    }
    private static long[][] matrixMultiplication(long[][] graph1, long[][] graph2) {

        long[][] result = new long[graph1.length][graph1[0].length];

        for (int i = 0; i < graph1.length; i++) {
            for (int j = 0; j < graph1[i].length; j++) {
                long sum = 0L;
                for (int k = 0; k < graph1[i].length; k++) {
                    sum += graph1[i][k] * graph2[k][j];
                    sum %= MOD;
                }

                result[i][j] = sum;
            }
        }

        return result;
    }
}
