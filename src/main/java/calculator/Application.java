package calculator;

import java.util.*;
import java.util.regex.Pattern;

import camp.nextstep.edu.missionutils.*;


public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Boolean condition = true;
        while (condition) {
            try {
                String inputText = "";
                String separator = null;
                int result = 0;
                String regex = ",|:";
                System.out.println("덧셈할 문자열을 입력해주세요");

                inputText = Console.readLine();
                if(inputText.contains("//") && inputText.contains("\\n")){
                    separator = takeSeparator(inputText);
                    textConfirm(inputText,separator);
                    inputText = inputText.split("\\\\n")[1];
                    regex += "|" + Pattern.quote(separator);
                }
                
                int[] intArray = Arrays.stream(inputText.split(regex)).mapToInt(Integer::parseInt).toArray();
                for (int i : intArray) {
                    result += i;
                }
                System.out.println("결과 : " + result);
            } catch (Exception e) {
                // TODO: handle exception
                stopProgram(e.getMessage());
            }
            
        }
       
    }

    // private static Boolean customDetect(String test){
    //     return test.contains("//") && test.contains("\\n");
    // }
    private static String takeSeparator(String text){
        String temp;
        int tempFront = text.indexOf("//") + 2;
        int tempBack = text.indexOf("\\n");
        temp = text.substring(tempFront, tempBack);
        if(temp.matches("[+-]?\\d*(\\.\\d+)?"));{
            stopProgram("custom separator can not use number!");
        }
        return text.substring(tempFront, tempBack);
    }

    private static void textConfirm(String text,String separator){
        String pattern1 = String.format("^//.+(?:\\\\n)\\d+(?:[,:%s]\\d+)*$",separator);
        String pattern2 = "^\\d+[,:]\\d+(?:[,:]\\d+)*$";
        if(separator == null){
            stopProgram("separator cannot be null!");
        }
        if(!text.matches(pattern1) && !text.matches(pattern2)){
            stopProgram("separator is invalid!");
        }
    }

    private static void stopProgram(String errMessage){
        throw new IllegalArgumentException(errMessage);
    }
    
}