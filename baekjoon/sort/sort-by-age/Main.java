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
