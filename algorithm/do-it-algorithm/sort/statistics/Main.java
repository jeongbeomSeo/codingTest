import java.io.*;

public class Main {

  static void shellSort(int[] nums) {
    int n = nums.length;
    int h;
    for(h = 1; h < n / 9; h = h * 3 + 1)
      ;

    for( ; h >= 1; h /= 3) {
      for(int i = h; i < n; i++) {
        int temp = nums[i];
        int j;
        for(j = i - h; j >= 0 && nums[j] > temp; j -= h)
          nums[j + h] = nums[j];
        nums[j + h] = temp;
      }
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[] nums = new int[N];

    double sum = 0;
    for(int i = 0 ; i < N; i++) {
      nums[i] = Integer.parseInt(br.readLine());
      sum += nums[i];
    }

    // 정렬 하기 (오름 차순)
    shellSort(nums);


    // 도수분포표
    // -4000 ~ 4000 까지라서 4000더해서 index 전부 0이상 만들기
    int[] f = new int[8001];
    for(int i = 0; i < nums.length; i++) f[nums[i] + 4000]++;

    int mode = 4000;
    int second = 4000;
    int count = 0;
    for(int i = 0; i < f.length; i++) {
      if(f[i] > count)  {
        mode = i - 4000;
        count = f[i];
        second = 4000;
      }
      else if(f[i] == count) {
        if(mode > i - 4000) {
          second = mode;
          mode = i - 4000;
        }
        else if(i - 4000 < second) second = i - 4000;
      }
    }


    int avg = (int)Math.round((sum / N));           // 산술 평균
    mode = (second != 4000) ? second : mode;        // 최빈 값
    int median = nums[nums.length / 2];             // 중앙 값
    int range = nums[nums.length -1] - nums[0];     // 범위

    System.out.println(avg);
    System.out.println(median);
    System.out.println(mode);
    System.out.println(range);
  }
}
