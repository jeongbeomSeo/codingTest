import java.util.Scanner;

public class Main {
  static int N;
  static int M;
  static int[] sequence;
  static boolean[] flag;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();

    sequence = new int[M];
    flag = new boolean[N + 1];

    nandm(0);
  }
  static void nandm(int size) {
    for(int i = 1; i <= N; i++) {
      if(M == 1) {
        System.out.println(i);
      }
      else {
        if(!flag[i]) {
          if(size >= 1) {
            if(sequence[size -1] > i) continue;
          }
          sequence[size] = i;
          if(size == M -1) print();
          else {
            flag[i] = true;
            nandm(size + 1);
            flag[i] = false;
          }
        }
      }
    }
  }
  static void print() {
    for(int i = 0; i < M; i++) {
      System.out.print(sequence[i] + " ");
    }
    System.out.println();
  }
}