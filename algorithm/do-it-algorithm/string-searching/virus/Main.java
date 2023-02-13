import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  static long[] calcHash(ArrayList<Integer> pat, int K) {
    long fowardHash = 0;
    long reverseHash = 0;
    long power = 1;

    for(int i = 0; i < K; i++) {
      fowardHash += pat.get(i) * power;
      reverseHash += pat.get(K - 1 - i) * power;
      if(i != K -1) {
        power *= 2;
      }
    }
    return new long[]{fowardHash, reverseHash, power};
  }

  static long[] nextHash(ArrayList<Integer> pat, int pt, long[] txtHash, int K) {
    long power = txtHash[2];
    txtHash[0] = (txtHash[0] - pat.get(pt - 1)) / 2 + pat.get(K - 1 + pt) * power;
    txtHash[1] = 2 * (txtHash[1] - pat.get(pt - 1) * power) + pat.get(K - 1 + pt);

    return txtHash;
  }

  static boolean rkMatch(ArrayList txt, ArrayList pat, int pt, long[] mainHash, int K) {
    long[] txtHash = new long[3];
    int txtLen = txt.size();

    txtHash = calcHash(txt, K);
    for(int i = 0; i <= txtLen - K; i++) {
      if(txtHash[0] == mainHash[0] || txtHash[0] == mainHash[1]) {
        boolean check = true;
        for(int j = 0; j < K; j++) {
          if(pat.get(pt + j) != txt.get(i + j)) {
            check = false;
            break;
          }
        }
        if(check) return true;
      }
      if(i != txtLen - K) {
        txtHash = nextHash(txt, i + 1, txtHash, K);
      }
    }


    return false;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    ArrayList<Integer>[] programs = new ArrayList[N];
    for(int p = 0; p < N; p++) {
      int M = Integer.parseInt(br.readLine());

      programs[p] = new ArrayList<>();
      st = new StringTokenizer(br.readLine());
      for(int i = 0 ; i < M; i++) {
        int num = Integer.parseInt(st.nextToken());
        programs[p].add(num);
      }
    }

    boolean success = false;

    int program1Length = programs[0].size();
    long[] mainHash = calcHash(programs[0], K);
    for(int pt = 0; pt <= program1Length - K; pt++) {
      if(pt != 0) mainHash = nextHash(programs[0], pt, mainHash, K);
      int i;
      for(i = 1; i < N; i++) {
        if(!rkMatch(programs[i], programs[0], pt, mainHash, K)) break;
      }
      if(i == N) {
        success = true;
        break;
      }
    }
    if(success) System.out.println("YES");
    else System.out.println("NO");
  }
}
