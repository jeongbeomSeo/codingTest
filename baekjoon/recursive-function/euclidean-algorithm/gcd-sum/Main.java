import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int tc = Integer.parseInt(br.readLine());

    for(int test = 0; test < tc; test++) {

      st = new StringTokenizer(br.readLine());
      int count = Integer.parseInt(st.nextToken());
      int[] nums = new int[count];

      if (count == 1) continue;

      for(int i = 0; i < count; i++) {
        nums[i] = Integer.parseInt(st.nextToken());
      }

      ArrayList<Integer> sumList = new ArrayList<>();
      for(int i = 0; i < nums.length - 1; i++) {
        for(int j = i + 1; j < nums.length; j++) {
          if (nums[i] > nums[j])
            sumList.add(euclidean(nums[i], nums[j]));
          else
            sumList.add(euclidean(nums[j], nums[i]));
        }
      }

      long ans = 0;
      for(int i = 0; i < sumList.size(); i++) {
        ans += sumList.get(i);
      }
      System.out.println(ans);
    }
  }
  static int euclidean(int x, int y) {
    if(y == 0)
      return x;
    else
      return euclidean(y, x % y);
  }
}
