class Solution {
  public String solution(String new_id) {
    String answer = "";

    new_id = firstStep(new_id);
    new_id = secondStep(new_id);
    new_id = thirdStep(new_id);
    new_id = fourthStep(new_id);
    new_id = fifthStep(new_id);
    new_id = sixthStep(new_id);
    answer = seventhStep(new_id);

    return answer;
  }

  String firstStep(String new_id) {
    // 소문자로 전부 변환
    String new_id_lowerCase = new_id.toLowerCase();
    return new_id_lowerCase;
  }

  String secondStep(String new_id) {
    // 주어진 문자 외에 문자 제거
    String convert_new_id = new_id.replaceAll("([^a-z0-9-_.])", "");
    return convert_new_id;
  }

  String thirdStep(String new_id) {
    // 최소 N번 반복 하는 문자 제거
    String convert_new_id = new_id.replaceAll("(\\.{2,})",".");
    return convert_new_id;
  }

  String fourthStep(String new_id) {
    //.(dot)으로 시작하거나 끝나는 문자열 처리
    String convert_new_id = new_id;
    while(convert_new_id.startsWith(".")) {
      convert_new_id = convert_new_id.substring(1);
    }
    while(convert_new_id.endsWith(".")){
      convert_new_id = convert_new_id.substring(0, convert_new_id.length() - 1);
    }
    return convert_new_id;
  }

  String fifthStep(String new_id) {
    //빈 문자열 "a"넣어주기
    String convert_new_id = new_id.isEmpty() ? "a" : new_id;
    return convert_new_id;
  }

  String sixthStep(String new_id) {
    //최고 문자열 길이에 맞게 문자열 조정 및 끝 문자 .(dot)일 경우 처리
    String convert_new_id = new_id.length() > 15 ? (new_id.substring(0 , 15).endsWith(".") ? new_id.substring(0,14) : new_id.substring(0 , 15)) : new_id;
    return convert_new_id;
  }

  String seventhStep(String new_id) {
    // 최소 문자열 길이 에 맞게 문자열 조정
    String convert_new_id = new_id;
    while(convert_new_id.length() < 3) {
      convert_new_id = convert_new_id.concat(convert_new_id.substring(convert_new_id.length() - 1));
    }
    return convert_new_id;
  }


  public static void main(String[] args) {
    String input = "-+";
    Solution solution = new Solution();
    String output = solution.solution(input);

    System.out.println("output = " + output);
  }
}