import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static void backSort(int temp, int[] sortTemp, int idx, int K) {
    int j;
    for(j = idx; j < K - 1 && sortTemp[j + 1] < temp; j++)
      sortTemp[j] = sortTemp[j + 1];
    sortTemp[j] = temp;
  }

  static void frontSort(int temp, int[] sortTemp, int idx, int K) {
    int j;
    for(j = idx; j > 0 && sortTemp[j - 1] > temp; j--)
      sortTemp[j] = sortTemp[j - 1];
    sortTemp[j] = temp;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    // 배열 두 개 초기화(입력 받은 대로의 배열, 길이가 K인 수열)
    int[] timeTemp = new int[N];
    int[] sortTemp = new int[K];

    long sum = 0;

    for(int i = 0; i < N; i++) {
      int temp = Integer.parseInt(br.readLine());
      timeTemp[i] = temp;

      // time < K || time == K
      if(i <= K - 1) {
        sortTemp[i] = temp;
        // 중앙 값 표시 시작
        if(i == K - 1) {
          Arrays.sort(sortTemp);
          sum += sortTemp[(K - 1)/ 2];
        }
      }

      // time > K
      else if(i > K - 1) {
        // timeTemp에서 (i - K)위치에 있는 요소의 값과 같은 요소를 sortTemp에서 추출하고 temp 넣어주기
        int idx = 0;

        for(int j = 0; j < sortTemp.length; j++)
          if(sortTemp[j] == timeTemp[i - K]) {
            idx = j;
            sortTemp[j] = temp;
            break;
          }

        // 정렬 해주기 (양쪽 끝 index 처리 후 나머지 처리)
        if(idx == 0 || idx < K - 1 && sortTemp[idx] > sortTemp[idx + 1]) backSort(temp, sortTemp, idx, K);

        else frontSort(temp, sortTemp, idx, K);
        // 새로운 중앙 값 합 처리
        sum += sortTemp[K / 2];
      }

    }

    System.out.println(sum);
  }
}
