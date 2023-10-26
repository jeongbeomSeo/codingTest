import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    String[] txts = new String[N];
    // int[] txtsLen = new int[N];
    for(int i = 0; i < N; i++) {
      txts[i] = br.readLine();
      // txtsLen[i] = txts[i].length();
    }

    String[] pats = new String[M];
    // int[] patsLen = new int[M];
    for(int i = 0; i < M; i++) {
      pats[i] = br.readLine();
      // patsLen[i] = pats[i].length();
    }

    int findedNum = 0;
    for(int i = 0; i < N; i++) {
      int txtLen = txts[i].length();
      for(int j = 0; j < M; j++) {
        if(txtLen == pats[j].length() && txts[i].equals(pats[j]))
          findedNum++;
      }
    }
    System.out.println(findedNum);
  }
}