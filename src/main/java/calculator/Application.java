package calculator;

import java.util.*;

import camp.nextstep.edu.missionutils.*;


public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        String a = "";
        int result = 0;
        System.out.println("덧셈할 문자열을 입력해주세요");
        a = Console.readLine();
        int[] intArray = Arrays.stream(a.split(",|:")).mapToInt(Integer::parseInt).toArray();
        for (int i : intArray) {
            result += i;
        }
        System.out.println("결과 : " + result);
    }
}