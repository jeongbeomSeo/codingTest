## [1차] 비밀지도

2018 KAKAO BLIND RECRUITMENT

## 문제설명 

네오는 평소 프로도가 비상금을 숨겨놓는 장소를 알려줄 비밀지도를 손에 넣었다. 그런데 이 비밀지도는 숫자로 암호화되어 있어 위치를 확인하기 위해서는 암호를 해독해야 한다. 다행히 지도 암호를 해독할 방법을 적어놓은 메모도 함께 발견했다.

1. 지도는 한 변의 길이가 ```n```인 정사각형 배열 형태로, 각 칸은 "공백"(" ") 또는 "벽"("#") 두 종류로 이루어져 있다.
2. 전체 지도는 두 장의 지도를 겹쳐서 얻을 수 있다. 각각 "지도 1"과 "지도 2"라고 하자. 지도 1 또는 지도 2 중 어느 하나라도 벽인 부분은 전체 지도에서도 벽이다. 지도 1과 지도 2에서 모두 공백인 부분은 전체 지도에서도 공백이다.
3. "지도 1"과 "지도 2"는 각각 정수 배열로 암호화되어 있다.
4. 암호화된 배열은 지도의 각 가로줄에서 벽 부분을 ```1```, 공백 부분을 ```0```으로 부호화했을 때 얻어지는 이진수에 해당하는 값의 배열이다.

![secret map](./secret.png)

네오가 프로도의 비상금을 손에 넣을 수 있도록, 비밀지도의 암호를 해독하는 작업을 도와줄 프로그램을 작성하라.

## 입력 형식

입력으로 지도의 한 변 크기 n 과 2개의 정수 배열 arr1, arr2가 들어온다.

- 1 ≦ ```n``` ≦ 16
- ```arr1```, ```arr2```는 길이 ```n```인 정수 배열로 주어진다.
- 정수 배열의 각 원소 ```x```를 이진수로 변환했을 때의 길이는 ```n``` 이하이다. 즉, 0 ≦ ```x``` ≦ 2n - 1을 만족한다.

## 출력 형식

원래의 비밀지도를 해독하여 ```#```, ```공백```으로 구성된 문자열 배열로 출력하라.

## 입출력 예제

- 입력값: 5, {9, 20, 28, 18, 11}, {30, 1, 21, 17, 28}
  - 기댓값: {"#####", "# # #", "### #", "#  ##", "#####"}
- 입력값: 6, {46, 33, 33, 22, 31, 50}, {27, 56, 19, 14, 14, 10}
- 기댓값: {"######", "###  #", "##  ##", " #### ", " #####", "### # "}

| 매개변수 | 	값                                           |
|------|----------------------------------------------|
 n    | 	5                                           |
 | arr1 | 	[9, 20, 28, 18, 11]                         |
 | arr2 | 	[30, 1, 21, 17, 28]                         |
 | 출력   | 	["#####","# # #", "### #", "# ##", "#####"] |

|매개변수|	값|
|---|---|
|n|	6|
|arr1|	[46, 33, 33 ,22, 31, 50]|
|arr2|	[27 ,56, 19, 14, 14, 10]|
|출력|	["######", "### #", "## ##", " #### ", " #####", "### # "]|


## 필요 개념

- 10진수 -> 2진수 
- 2비트 코드 사용법 

## 참고한 사이트

- 10진수 <--> 2진수
  - https://onepinetwopine.tistory.com/768
- [Java] 10진수 <-> 2진수, 8진수, 16진수로 변환하기
  - https://hianna.tistory.com/527 
- 비트 연산
  - https://vmpo.tistory.com/106
- 비트 이동 연산자
  - https://coding-factory.tistory.com/521

## 나의 코드

```java
import java.util.ArrayList;

class Solution {
  public String[] solution(int n, int[] arr1, int[] arr2) {
    String[] answer = new String[arr1.length];


    for(int i = 0; i < n; i++) {
      int[] binary = new int[n];
      int map1Line = arr1[i];
      int map2Line = arr2[i];

      ArrayList<Integer> map1List = new ArrayList<>();
      ArrayList<Integer> map2List = new ArrayList<>();

      makeBinaryNumber(map1List, map2List, map1Line, map2Line);

      int z = 0;
      for(int j = binary.length - 1;  j >= binary.length - map1List.size() ; j--) {
        binary[j] = map1List.get(z++);
      }
      z = 0;
      for(int j = binary.length - 1; j >= binary.length - map2List.size(); j--) {
        binary[j] = binary[j] == 1 ? 1 : (map2List.get(z) == 1 ? 1 : 0);
        z++;
      }

      String line = "";
      for(int j = 0; j < n; j++) {
      line += binary[j] == 1 ? "#" : " ";
      }

      answer[i] = line;

    }

    return answer;
  }

  void makeBinaryNumber(ArrayList map1List, ArrayList map2List,int num1, int num2) {
    while (num1 != 0) {
      int remain = num1 % 2 == 1 ? 1 : 0;
      num1 /= 2;
      map1List.add(remain);
    }
    while (num2 != 0) {
      int remain = num2 % 2 == 1 ? 1 : 0;
      num2 /= 2;
      map2List.add(remain);
    }
  }
  public static void main(String[] args) {
    Solution solution = new Solution();
    int n = 5;
    int[] arr1 = {9, 20, 28, 18, 11};
    int[] arr2 = {30, 1, 21, 17, 28};
    System.out.println(solution.solution(n , arr1, arr2));
  }
}
```

## 다른 사람 풀이

```java
class Solution {
  public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            result[i] = Integer.toBinaryString(arr1[i] | arr2[i]);
        }

        for (int i = 0; i < n; i++) {
            result[i] = String.format("%" + n + "s", result[i]);
            result[i] = result[i].replaceAll("1", "#");
            result[i] = result[i].replaceAll("0", " ");
        }

        return result;
    }
}
```

```java
class Solution {
    public String makeSharp(int n, int m) {
        if(n == 0) {
            if( m > 0) {
                String str = "";
                for(int i = 0; i < m; i++) {
                    str += " ";
                }
                return str;
            }
            else return "";
        }
        else {
            return n % 2 == 0 ? makeSharp(n/2, m-1) + " " : makeSharp(n/2, m-1) + "#"; 
        }
    }
    public String[] solution(int n, int [] arr1, int [] arr2) {
        String [] answer = new String[n];
        int [] secretMap = new int[n];
        for(int i = 0; i < n; i++) {
            secretMap[i] = arr1[i] | arr2[i];
            answer[i] = makeSharp(secretMap[i], n);
        }
        return answer;
    }
}
```