/*
 * 개미 전사가 정찰병에게 들키지 않고 식량 창고를 약탈하기 위해서는 최소한 한 칸 이상 떨어진 식량 창고를 약탈해야 한다.
 *
 * 입력:
 * 첫째 줄에 식량 창고가의 개수 N이 주어집니다. (3 <= N <= 100)
 * 둘째 줄에 공백을 기준을 각 식량창고에 저장된 식량의 개수 K가 주어집니다. (0 <= K <= 1,000)
 *
 * 출력:
 * 첫쨰 줄에 개미 전사가 얻을 수 있는 식량의 최댓값을 출력하세요.
 *
 * 입력 예시
 * 4
 * 1 3 1 4
 *
 * 출력 예시
 * 8
 * */

import java.util.Scanner;

public class AntWorrier {
  static int[] d = new int[100];
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 정수 N을 입력받기
    int n = sc.nextInt();

    // 모든 식량 정보 입력 받기
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
    // 다이나믹 프로그래밍 (Dynamic Programming) 진행 (바텀업)
    d[0] = arr[0];
    d[1] = Math.max(arr[0], arr[1]);
    for (int i = 2; i < n; i++) {
      d[i] = Math.max(d[i - 1], d[i - 2] + arr[i]);
    }

    // 계산된 결과 출력
    System.out.println(d[n - 1]);
  }
}
