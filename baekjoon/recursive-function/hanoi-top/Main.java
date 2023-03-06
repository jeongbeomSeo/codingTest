import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  static long count = 0;
  static BigInteger bigCount;
  static boolean print;
  static ArrayList<String> printList;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // 원판의 개수
    int N = Integer.parseInt(br.readLine());

    printList = new ArrayList<>();
    print = false;
    if(N <= 20) print = true;
    if(print) {
      hanoi(N, 1, 3);
    }
    else {
      bigCount = BigInteger.valueOf(2);
      bigCount = bigCount.pow(N).subtract(new BigInteger("1"));
    }
    if(print){
      bw.write(count+"\n");
      bw.flush();
      for(String str : printList) bw.write(str+"\n");
      bw.flush();
      bw.close();
    }
    else {
      bw.write(bigCount+"\n");
      bw.flush();
      bw.close();
    }
  }
  static void hanoi(int N, int src, int dest) {
    count++;
    if(N > 1){
      hanoi(N-1, src, 6- dest - src);
    }
    printList.add(src + " " + dest);
    if(N > 1) {
      hanoi(N -1, 6 - dest - src, dest);
    }
  }
}
