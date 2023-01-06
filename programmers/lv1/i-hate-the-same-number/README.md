# 같은 숫자는 싫어

## 문제 설명

배열 arr가 주어집니다. 배열 arr의 각 원소는 숫자 0부터 9까지로 이루어져 있습니다. 이때, 배열 arr에서 연속적으로 나타나는 숫자는 하나만 남기고 전부 제거하려고 합니다. 단, 제거된 후 남은 수들을 반환할 때는 배열 arr의 원소들의 순서를 유지해야 합니다. 예를 들면,

- arr = [1, 1, 3, 3, 0, 1, 1] 이면 [1, 3, 0, 1] 을 return 합니다.
- arr = [4, 4, 4, 3, 3] 이면 [4, 3] 을 return 합니다.
배열 arr에서 연속적으로 나타나는 숫자는 제거하고 남은 수들을 return 하는 solution 함수를 완성해 주세요.

## 제한사항

- 배열 arr의 크기 : 1,000,000 이하의 자연수
- 배열 arr의 원소의 크기 : 0보다 크거나 같고 9보다 작거나 같은 정수

## 입출력 예

- 입력값: [1, 1, 3, 3, 0, 1, 1]
  - 기댓값: [1, 3, 0, 1]
- 입력값: 	[4, 4, 4, 3, 3]
  - 기댓값: [4, 3]

|arr| answer    |
|------|-----------|
|[1,1,3,3,0,1,1]	| [1,3,0,1] |
|[4,4,4,3,3]| 	[4,3]    |

## 필요 개념

- Array와 List의 차이
  - Array의 경우 선언 해두고 나면 크기를 바꿀수 없다. 즉, 요소 추가 불가능
  - List의 경우 선언해두고 크기를 변경 가능
- Array에서 List로 변경하는 방법
  - int의 경우 primitive type이란 것에 주의
- List에서 Array로 변경하는 방법 
  - String의 경우 toArray로 간단히 변경할 수 있지만, int의 경우 다르다.
  - int의 경우 Integer에서 int로 바꿔주는 것이기 때문에 반복문을 활용하든가 아니면, **intStream**을 활용
- intStream 
## 참고한 사이트

- ArrayList<Integer> to int[] 
  - https://velog.io/@deannn/Java-int%ED%98%95-ArrayList-%EB%B0%B0%EC%97%B4-%EB%B3%80%ED%99%98
- IntStream
  - https://m.blog.naver.com/myca11/221386518751
- Java의 데이터 타입
  - https://gbsb.tistory.com/6
- Method map, mapToInt
  - https://dev-kani.tistory.com/32
- Java Stream 총정리 
  - https://futurecreator.github.io/2018/08/26/java-8-streams/
- 제너릭(Generic)
  - https://st-lab.tistory.com/153
- Wrapper Class and boxing, un-boxing
  - https://blog.naver.com/PostView.nhn?isHttpsRedirect=true&blogId=heartflow89&logNo=220975218499&redirect=Dlog&widgetTypeCall=true
- Array and Collections Difference
  - https://www.techiedelight.com/ko/difference-between-arrays-collections-java/
