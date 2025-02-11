import java.io.*;
import java.util.Arrays;

public class Main {
    private static final String WRONG_ANS = "Goldbach's conjecture is wrong.";
    private static final int MAX_SIZE = 1000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[] primeTable = generatePrimeTable(MAX_SIZE);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) break;

            boolean isSuccess = false;

            for (int i = 2; i <= n / 2; i++) {
                if (primeTable[i] && primeTable[n - i]) {
                    bw.write(n + " = " + i + " + " + (n - i));
                    isSuccess = true;
                    break;
                }
            }

            if (!isSuccess) bw.write(WRONG_ANS);

            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }
    private static boolean[] generatePrimeTable(int size) {

        boolean[] table = new boolean[size];
        Arrays.fill(table, true);
        table[1] = false;

        for (int i = 2; i < Math.sqrt(size); i++) {
            for (int j = i * i; j < size; j += i) {
                table[j] = false;
            }
        }

        return table;
    }
}
