package calculator;

import java.util.*;
import java.util.regex.Pattern;

import camp.nextstep.edu.missionutils.*;


public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        String inputText = "";
        String separator = null;
        int result = 0;
        String regex = ",|:";
        System.out.println("덧셈할 문자열을 입력해주세요");
        inputText = Console.readLine();
        // String temp = customDetect(inputText);
        if(customDetect(inputText)){
            separator = takeSeparator(inputText);
            inputText = inputText.split("\\\\n")[1];
            regex += "|" + Pattern.quote(separator);
        }
        
        int[] intArray = Arrays.stream(inputText.split(regex)).mapToInt(Integer::parseInt).toArray();
        for (int i : intArray) {
            result += i;
        }
        System.out.println("결과 : " + result);
    }

    private static Boolean customDetect(String test){
        try {
            if (test.indexOf("//") != -1 && test.indexOf("\\n") != -1) {
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    private static String takeSeparator(String test){
        int tempFront = test.indexOf("//") + 2;
        int tempBack = test.indexOf("\\n");
        return test.substring(tempFront, tempBack);
    }
}