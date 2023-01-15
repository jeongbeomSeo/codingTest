import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int max = Integer.MIN_VALUE;
  static int min = Integer.MAX_VALUE;

  static int N;
  // 수열
  static int[] seq;
  // 연산자 수 ( [ + , - , x , ÷ ] )
  static int[] opr;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // 수의 개수
    N = Integer.parseInt(br.readLine());


    seq = new int[N];
    opr = new int[4];

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++) {
      seq[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < 4; i++) {
      opr[i] = Integer.parseInt(st.nextToken());
    }

    findMaxMinNums(1, seq[0]);

    System.out.println(max);
    System.out.println(min);
  }

  static void findMaxMinNums(int ptr, int sum) {
    if(ptr == N) {
      max = Math.max(max, sum);
      min = Math.min(min, sum);
      return;
    }
    for(int j = 0; j < 4; j++) {
      if (opr[j] != 0) {
        --opr[j];
        switch (j) {
          case 0:
            findMaxMinNums(ptr + 1, sum + seq[ptr]);
            break;
          case 1:
            findMaxMinNums(ptr + 1, sum - seq[ptr]);
            break;
          case 2:
            findMaxMinNums(ptr + 1, sum * seq[ptr]);
            break;
          case 3:
            findMaxMinNums(ptr + 1, sum / seq[ptr]);
            break;
        }
        ++opr[j];
      }
    }
  }
}
