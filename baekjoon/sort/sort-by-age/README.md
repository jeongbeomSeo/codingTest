# 나이순 정렬

**실버 5**

|시간 제한	|메모리 제한|	제출|	정답|	맞힌 사람	|정답 비율|
|---|---|---|---|---|---|
|3 초|	256 MB|	98840|	43748|	33382|	43.058%|

## 문제

온라인 저지에 가입한 사람들의 나이와 이름이 가입한 순서대로 주어진다. 이때, 회원들을 나이가 증가하는 순으로, 나이가 같으면 먼저 가입한 사람이 앞에 오는 순서로 정렬하는 프로그램을 작성하시오.

## 입력

첫째 줄에 온라인 저지 회원의 수 N이 주어진다. (1 ≤ N ≤ 100,000)

둘째 줄부터 N개의 줄에는 각 회원의 나이와 이름이 공백으로 구분되어 주어진다. 나이는 1보다 크거나 같으며, 200보다 작거나 같은 정수이고, 이름은 알파벳 대소문자로 이루어져 있고, 길이가 100보다 작거나 같은 문자열이다. 입력은 가입한 순서로 주어진다.

## 출력

첫째 줄부터 총 N개의 줄에 걸쳐 온라인 저지 회원을 나이 순, 나이가 같으면 가입한 순으로 한 줄에 한 명씩 나이와 이름을 공백으로 구분해 출력한다.

## 예제 입력 1

```
3
21 Junkyu
21 Dohyun
20 Sunyoung
```

## 예제 출력 1

```
20 Sunyoung
21 Junkyu
21 Dohyun
```

## 문제 의도

1. 안정적인 정렬
   1. 버블 정렬
   2. 삽입 정렬
   3. 병합 정렬
   4. 도수 정렬(계수 정렬, 카운팅 정렬)
2. 불안정적인 정렬
   1. 선택 정렬
   2. 셸 정렬
   3. 퀵 정렬
   4. 힙 정렬

## 나의 코드 

```java
import java.io.*;
import java.util.StringTokenizer;

class Person{
  int age;
  String name;

  Person(int age, String name) {
    this.age = age;
    this.name = name;
  }
}

public class Main {
  static void countingSort(Person[] people, int max) {
    int n = people.length;
    int[] f = new int[max + 1];
    Person[] b = new Person[n];

    for(int i = 0; i < n; i++) f[people[i].age]++;
    for(int i = 1; i < f.length; i++) f[i] += f[i - 1];
    for(int i = n - 1; i >= 0; i--) b[--f[people[i].age]] = people[i];
    for(int i = 0; i < n; i++) people[i] = b[i];
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    Person[] people = new Person[N];
    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int age = Integer.parseInt(st.nextToken());
      String name = st.nextToken();
      people[i] = new Person(age, name);
    }

    countingSort(people, 200);

    for(int i = 0 ; i < people.length; i++)
      bw.write(people[i].age + " " + people[i].name + "\n");
    bw.flush();
    bw.close();


  }
}
```