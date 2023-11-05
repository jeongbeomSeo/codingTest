import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Item[] items = new Item[N + 1];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            items[i] = new Item(weight, cost);
        }

        int[][] table = new int[N + 1][K + 1];

        activeDp(table, items, N, K);

        System.out.println(table[N][K]);
    }
    private static void activeDp(int[][] table, Item[] items, int N, int K) {

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < K + 1; j++) {
                if (items[i].weight <= j) {
                    table[i][j] = Math.max(table[i - 1][j], table[i - 1][j - items[i].weight] + items[i].cost);
                } else {
                    table[i][j] = table[i - 1][j];
                }
            }
        }
    }
}
class Item {
    int weight;
    int cost;

    Item(int weight, int cost) {
        this.weight = weight;
        this.cost = cost;
    }
}