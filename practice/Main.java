import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    private static String minStr;
    private static String maxStr;
    private static long minResult = Long.MAX_VALUE;
    private static long maxResult = Long.MIN_VALUE;
    private static int size;
    private static char[] arrows;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        size = Integer.parseInt(br.readLine());

        arrows = new char[size];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            arrows[i] = st.nextToken().charAt(0);
        }

        recursive(new boolean[10], new int[size + 1], 0);

        System.out.println(maxStr);
        System.out.println(minStr);
    }
    private static void recursive(boolean[] isUsed, int[] buffer, int ptr) {

        if (ptr == size + 1) {
            String str = Arrays.stream(buffer). mapToObj(String::valueOf).collect(Collectors.joining());
            long num = Long.parseLong(str);
            if (maxResult < num) {
                maxResult = num;
                maxStr = str;
            }
            if (minResult > num) {
                minResult = num;
                minStr = str;
            }
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (isUsed[i]) continue;

            if (ptr > 0) {
                if (arrows[ptr - 1] == '<') {
                    if (buffer[ptr - 1] > i) continue;
                } else {
                    if (buffer[ptr - 1] < i) continue;
                }
            }

            buffer[ptr] = i;
            isUsed[i] = true;
            recursive(isUsed, buffer, ptr + 1);
            isUsed[i] = false;
        }
    }
}