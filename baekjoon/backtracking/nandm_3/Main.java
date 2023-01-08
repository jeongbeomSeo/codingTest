import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
  static int N;
  static int M;
  static int[] seq;
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException{
    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();

    seq = new int[M];

    nandm(0);

    bw.flush();
    bw.close();
  }
  static void nandm(int size) throws IOException{
    for(int i = 1; i <= N; i++) {
      seq[size] = i;
      if(size == M - 1)
        print();
      else
        nandm(size + 1);
    }
  }
  static void print() throws IOException {
    for(int i = 0; i < seq.length; i++) {
      bw.write(seq[i] + " ");
    }
    bw.newLine();
  }
}
