import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] tables = new int[N][2];

        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            tables[i][0] = Integer.parseInt(st.nextToken());
            tables[i][1] = Integer.parseInt(st.nextToken());

            maxHeight = Math.max(maxHeight, tables[i][1]);
        }

        Arrays.sort(tables, (o1, o2) -> o1[0] - o2[0]);


        int height = 0;

        int i;
        for (i = 0; tables[i][1] != maxHeight; i++) {
            if (height < tables[i][1]) {
                height = tables[i][1];
            } else {
                tables[i][1] = height;
            }
        }

        height = 0;
        for (int j = tables.length - 1; j > i; j--) {
            if (height < tables[j][1]) {
                height = tables[j][1];
            } else {
                tables[j][1] = height;
            }
        }

        int sum = maxHeight;
        for (i = 0; i < N - 1; i++) {
            sum += (tables[i + 1][0] - tables[i][0]) * Math.min(tables[i][1], tables[i + 1][1]);
        }

        System.out.println(sum);
    }
}