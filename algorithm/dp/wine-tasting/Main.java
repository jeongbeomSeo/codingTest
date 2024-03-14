import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        int[] dpTable = new int[n];
        if (n >= 1) dpTable[0] = array[0];
        if (n >= 2) dpTable[1] = array[0] + array[1];
        if (n >= 3) {
            dpTable[2] = Math.max(Math.max(array[0] + array[2], array[1] + array[2]), dpTable[1]);
        }
        if (n >= 4) {
            for (int i = 3; i < n; i++) {
                dpTable[i] = dpTable[i - 1];

                dpTable[i] = Math.max(dpTable[i], array[i] + Math.max(dpTable[i - 3] + array[i - 1], dpTable[i - 2]));
            }
        }

        System.out.println(dpTable[n - 1]);
    }
}