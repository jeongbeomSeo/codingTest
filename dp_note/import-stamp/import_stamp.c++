#include <iostream>
#include <iomanip>
#include <algorithm>

#define MAXMONEY 32
#define MAX 80

using namespace std;

int table[9][9][8];
int mx[8][8];

// 수입 증지의 개수 n, 종류의 개수 m
// now: 현재 넣어야 할 순서의 증지 위치
// num 배열: 증지의 가격이 순서대로 들어있는 배열
void dfs(int now, int num[8], int n, int m)
{
  int i, j;
  // 들어 있는 증지 체크 배열
  int d[MAX];

  // 동적 배열 초기화
  for (i = 0; i < MAX; i++) d[i] = -1;
  // 0 원을 구성할 수 있으므로 초기 값 0 으로 세팅
  d[0] = 0;

  // CC
  for (i = 0; i < now; i++) {
    // 100 까지 들어 있는 값 비교
    for (j = 0; j < MAX; j++) {
      // 현재 사용된 개수가 이전에 저장한 개수보다 작거나
      // 이전에 안 채워진 경우
      if (d[j] != -1 && j + num[j] < MAX 
      && (d[j + num[j]] > d[j] + 1 || d[j + num[j]] == -1)
      && d[j] + 1 <= m)
      d[j + num[i]] = d[j] + 1; // 개수 갱신
    }
  }

  // 연속된 개수 찾기
  for (i = 0; i < MAX; i++) {
    // 중간에 빈 것이 있는 경우
    if(d[i] == -1) {
      // 연속된 결과가 이전에 결과보다 더 적은 경우 리턴한다.
      if (i - 1 < num[now - 1]) return;

      // 멈추고 빠져 나간다.
      break;
    }
  }
  // i - 1: 연속된 개수

  // 마지막 수입 증지 개수와 같은 경우, 즉, 증지 n 개를 채운 경우
  if (now == n) {
    // 마지막에 빈 곳의 결과가 이전 것보다 좋으면 저장하고 갱신한다.
    if (i - 1 > mx[m][n]) {
      mx[m][now] = i - 1;
      // 연속된 최대 개수가 되는 증지의 구성을 출력한다.
      copy(num, num + 8, table[m][now]);
    }

    return;
  }

  // 이전 까지의 금액 + 1 원부터 최대 금액까지 넣으면서
  // 최대 가지수 구하기
  for (i = num[now - 1] + 1; i <= MAXMONEY; i++) {
    // now 번째 금액을 i 원으로 저장하고 dfs로 방문
    num[now] = i;
    dfs(now + 1, num, n, m);
    num[now] = 0;
  }
}

int main()
{
  int tmp[8];
  int i, j;
  int h, k;

  fill(tmp, tmp + 8, 0);
  tmp[0] = 1;

  // 미리 모든 경우의 결과를 구해둔다.
  // 수입 증지의 개수 h
  for (i = 1 ; i <= 8; i++) {
    // 증지 종류의 개수 k
    for (j = 1; j <= 9 - i; j++) dfs(1, tmp, i, j);
  }

  while(cin >> h >> k) {
    if (h == 0 && k == 0) break;

    // n(h, k) 를 구성하는 증지의 가격 구성
    for (i = 0; i < k; i++) cout << setw(3) << table[h][k][i];

    // 연속된 최대 개수 출력
    cout << "->" << setw(3) << mx[h][k] << endl;
  }

  return 0;
}
