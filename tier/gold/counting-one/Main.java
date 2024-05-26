import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long[] counts = getCounts();

//        System.out.println(query(counts, B));
        System.out.println(query(counts, B) - query(counts, A - 1));
    }
    private static long query(long[] counts, long num) {

        long result = 0L;
        for (int i = counts.length - 1; i >= 0; i--) {
            if ((num & (1L << i)) != 0) {
                result += counts[i] + (num - (1L << i) + 1);
                num -= (1L << i);
            }
        }
        return result;
    }
    private static long[] getCounts() {

        long[] counts = new long[63];
        counts[1] = 1;

        for (int i = 2; i < counts.length; i++) {
            counts[i] = 2 * counts[i - 1] + (long)Math.pow(2, i - 1);
        }

 /*       for (long count : counts) {
            System.out.println(count);
        }*/

        return counts;
    }
}