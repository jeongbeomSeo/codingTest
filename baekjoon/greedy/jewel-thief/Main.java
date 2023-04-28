import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
  static int sum = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    ArrayList<Jewel> jewels = new ArrayList<>();
    ArrayList<Bag> bags = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int weight = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      jewels.add(new Jewel(weight, cost));
    }

    for (int i = 0; i < K; i++) {
      int size = Integer.parseInt(br.readLine());
      bags.add(new Bag(size));
    }

    Collections.sort(jewels);
    Collections.sort(bags);

    query(jewels, bags, N, K);

    System.out.println(sum);
  }
  static void query(ArrayList<Jewel> jewels, ArrayList<Bag> bags, int N, int K) {

    int bagSize = K;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < bagSize; j++) {
        if (bags.get(j).size >= jewels.get(i).weight) {
          sum += jewels.get(i).cost;
          bags.remove(j);
          bagSize--;
          break;
        }
      }
    }
  }
}

class Bag implements Comparable<Bag> {
  int size;
  int cost;

  Bag(int size) {
    this.size = size;
  }

  @Override
  public int compareTo(Bag o) {
    return this.size - o.size;
  }
}

class Jewel implements Comparable<Jewel>{
  int weight;
  int cost;

  Jewel(int weight, int cost) {
    this.weight = weight;
    this.cost = cost;
  }

  @Override
  public int compareTo(Jewel o) {
    return o.cost - this.cost;
  }
}