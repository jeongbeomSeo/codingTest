import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
  int i;
  int j;

  int yeonCount;
  int allCount;

}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] str = new String[5];

    for (int i = 0; i < str.length; i++) {
      str[i] = br.readLine();
    }


  }
}