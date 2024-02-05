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

    Item[] initItemArray = new Item[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      int weight = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      int count = Integer.parseInt(st.nextToken());

      initItemArray[i] = new Item(weight, cost, count);
    }

    List<SeparatedItem> itemList = dividedItemList(initItemArray, N);

    int rowLen = itemList.size();

    int[][] dpTable = new int[rowLen + 1][M + 1];

    for (int i = 1; i <= rowLen; i++) {
      for (int j = 0; j <= M; j++) {
        if (j >= itemList.get(i - 1).weight) {
          dpTable[i][j] = Math.max(dpTable[i - 1][j], dpTable[i - 1][j - itemList.get(i - 1).weight] + itemList.get(i - 1).cost);
        } else {
          dpTable[i][j] = dpTable[i - 1][j];
        }
      }
    }

    System.out.println(dpTable[rowLen][M]);
  }
  private static List<SeparatedItem> dividedItemList(Item[] initItemArray, int N) {

    List<SeparatedItem> itemList = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      int weight = initItemArray[i].weight;
      int cost = initItemArray[i].cost;
      int count = initItemArray[i].count;
      while (count != 1) {
        int curCount = count - count / 2;
        itemList.add(new SeparatedItem(weight * curCount, cost * curCount));

        count -= curCount;
      }
      itemList.add(new SeparatedItem(weight, cost));
    }

    return itemList;
  }
}
class SeparatedItem {
  int weight;
  int cost;

  SeparatedItem(int weight, int cost) {
    this.weight = weight;
    this.cost = cost;
  }
}
class Item {
  int weight;
  int cost;
  int count;

  Item (int weight, int cost, int count) {
    this.weight = weight;
    this.cost = cost;
    this.count = count;
  }
}