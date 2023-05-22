import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int X = Integer.parseInt(br.readLine());

    int count = 0;
    for (int i = 0; i <= 6; i++) {
      if ((X & (1 << i)) == (1 << i)) count++;
    }

    System.out.println(count);
  }
}
