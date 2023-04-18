import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int MAX_COM = 100001;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Switch[] switches = new Switch[N + 1];
    for (int i = 0; i < N + 1; i++) {
      switches[i] = new Switch();
    }

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      switches[i].port = Integer.parseInt(st.nextToken());
      switches[i].cost = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(switches);

    for (int i = 1; i < N + 1; i++) {
      System.out.println(switches[i].port + " " + switches[i].cost);
    }

    int M = Integer.parseInt(br.readLine());

    long[] dp = new long[MAX_COM];

  }
  static void dp(Switch[] switches, long[] dp, int N, int M) {

    dp[1] = switches[1].cost;
    dp[2] = switches[1].cost;

    for (int i = 1; i <= switches[1].port; i++) {
      dp[i] = switches[1].cost;
      if (i == M) return;
    }

    int curPtr = switches[1].port + 1;
    int curSwitch = 2;
    while (curPtr < M) {
      dp[curPtr] = Math.min(dp[curPtr - switches[curSwitch - 1].port] + switches[curSwitch].cost,
              )
    }
  }

}

class Switch implements Comparable<Switch>{
  int port;
  int cost;


  Switch() {}
  Switch(int port, int cost) {
    this.port = port;
    this.cost = cost;
  }

  @Override
  public int compareTo(Switch o) {
    return this.cost - o.cost;
  }
}