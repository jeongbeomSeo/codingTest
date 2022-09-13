# [1차]캐시

## 문제 설명

지도개발팀에서 근무하는 제이지는 지도에서 도시 이름을 검색하면 해당 도시와 관련된 맛집 게시물들을 데이터베이스에서 읽어 보여주는 서비스를 개발하고 있다.
이 프로그램의 테스팅 업무를 담당하고 있는 어피치는 서비스를 오픈하기 전 각 로직에 대한 성능 측정을 수행하였는데, 제이지가 작성한 부분 중 데이터베이스에서 게시물을 가져오는 부분의 실행시간이 너무 오래 걸린다는 것을 알게 되었다.
어피치는 제이지에게 해당 로직을 개선하라고 닦달하기 시작하였고, 제이지는 DB 캐시를 적용하여 성능 개선을 시도하고 있지만 캐시 크기를 얼마로 해야 효율적인지 몰라 난감한 상황이다.

어피치에게 시달리는 제이지를 도와, DB 캐시를 적용할 때 캐시 크기에 따른 실행시간 측정 프로그램을 작성하시오.

## 입력 형식

- 캐시 크기(```cacheSize```)와 도시이름 배열(```cities```)을 입력받는다.
- ```cacheSize```는 정수이며, 범위는 0 ≦ cacheSize ≦ 30 이다.
- ```cities```는 도시 이름으로 이뤄진 문자열 배열로, 최대 도시 수는 100,000개이다.
- 각 도시 이름은 공백, 숫자, 특수문자 등이 없는 영문자로 구성되며, 대소문자 구분을 하지 않는다. 도시 이름은 최대 20자로 이루어져 있다.

## 출력 형식

- 입력된 도시이름 배열을 순서대로 처리할 때, "총 실행시간"을 출력한다.

## 조건

- 캐시 교체 알고리즘은 ```LRU```(Least Recently Used)를 사용한다.
- ```cache hit```일 경우 실행시간은 ```1```이다.
- ```cache miss```일 경우 실행시간은 ```5```이다.

## 입출력 예제

|캐시크기(cacheSize)| 	도시이름(cities)                                                                                                        |	실행시간|
|---|----------------------------------------------------------------------------------------------------------------------|---|
|3| 	["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"]                            |	50|
|3| 	["Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"]                                   |	21|
|2| 	["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"]   |	60|
|5| 	["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"]   |	52|
|2| 	["Jeju", "Pangyo", "NewYork", "newyork"]                                                                            |	16|
|0| 	["Jeju", "Pangyo", "Seoul", "NewYork", "LA"]                                                                        |	25|

## 참고한 사이트

- LRU(Least Recently Used)
  - https://j2wooooo.tistory.com/121
- FIFO, LFU, LRU
  - https://m.blog.naver.com/PostView.nhn?blogId=kyung778&logNo=60164009610&proxyReferer=https:%2F%2Fwww.google.com%2F
- Cash, Cache hit, Cache miss
  - https://m.blog.naver.com/tlstjd436/221824813403

## 나의 코드

```java
import java.util.ArrayList;

class Solution {
  public int solution(int cacheSize, String[] cities) {
    int cash = 0;
    int count = 0;
    ArrayList<String> pages = new ArrayList<>();
    for(int i = 0; i< cities.length; i++) {
      if(cacheSize == 0) {
        cash += 5;
        continue;
      }
      boolean hit= false;
      String city = cities[i].toLowerCase();
      for(int j = 0; j < pages.size(); j++) {
        // cache hit인 경우
        if(pages.get(j).equals(city)) {
          pages.remove(j);
          pages.add(city);
          cash++;
          hit = true;
          break;
        }
      }
      //hit인 경우 다음 city로 넘기기.
      if(hit) continue;
      //cache miss인 경우

      //Full Size
      if(count == cacheSize)  pages.remove(0);
      else  count++;
      
      pages.add(pages.size(), city);
      cash += 5;
    }

    return cash;
  }
}

```
