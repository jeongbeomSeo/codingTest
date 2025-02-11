import java.io.*;
import java.util.Arrays;

public class Main {
    private static final int SIZE = 1000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        long[] fx = new long[SIZE];
        Arrays.fill(fx, 1);

        for (int i = 2; i < SIZE; i++) {
            for (int j = i; j < SIZE; j += i) {
                fx[j] += i;
            }
        }

        long[] gx = new long[SIZE];
        for (int i = 1; i < SIZE; i++) {
            gx[i] += fx[i] + gx[i - 1];
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (T-- != 0) {
            int n = Integer.parseInt(br.readLine());
            bw.write(gx[n] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
