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
