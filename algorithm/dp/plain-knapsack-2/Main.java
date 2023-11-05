import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    List<Item> itemList = new ArrayList<>();
    for (int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      int weight = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      int quantity = Integer.parseInt(st.nextToken());

      addItems(itemList, weight, cost, quantity);
    }

    System.out.println(getMaxCost(itemList, M));
  }
  private static int getMaxCost(List<Item> itemList, int M) {

    int listSize = itemList.size();
    int[][] table = new int[listSize + 1][M + 1];

    for (int i = 1; i < listSize + 1; i++) {
      Item item = itemList.get(i - 1);

      for (int j = 1; j < M + 1; j++) {
        if (j < item.weight) {
          table[i][j] = table[i - 1][j];
        } else {
          table[i][j] = Math.max(table[i - 1][j], table[i - 1][j - item.weight] + item.cost);
        }
      }
    }

    return table[listSize][M];
  }
  private static void addItems(List<Item> itemList, int weight, int cost, int quantity) {
    for (int i = quantity; i > 0; i = (i >> 1)) {
      int remain = i - (i >> 1);

      itemList.add(new Item(weight * remain, cost * remain, remain));
    }
  }
}
class Item {
  int weight;
  int cost;
  int quota;

  Item(int weight, int cost, int quota) {
    this.weight = weight;
    this.cost = cost;
    this.quota = quota;
  }
}