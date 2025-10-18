package calculator;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {
        // TODO: 프로그램 구현

        try {
            String inputText = "";
            String separator = null;
            BigDecimal result = BigDecimal.ZERO;
            String regex = ",|:";

            System.out.println("덧셈할 문자열을 입력해주세요");
            inputText = Console.readLine();

            if (inputText.equals("")) {
                System.out.println("결과 : 0");
                return;
            }

            if (inputText.contains("//") && inputText.contains("\\n")) {
                separator = takeSeparator(inputText);
                textConfirm(inputText, separator);
                inputText = inputText.split("\\\\n")[1];
                regex += "|" + Pattern.quote(separator);
            }

            for (String i : inputText.split(regex)) {
                if (i.contains("-")) {
                    stopProgram("음수 사용불가!");
                }
                result = result.add(new BigDecimal(i));
            }

            System.out.println("결과 : " + result);
        } catch (Exception e) {
            stopProgram(e.getMessage());
        }
    }

    private static String takeSeparator(String text) {
        int tempFront = text.indexOf("//") + 2;
        int tempBack = text.indexOf("\\n");
        String temp = text.substring(tempFront, tempBack);

        if (temp.matches("[+-]?\\d*(\\.\\d+)?")) {
            stopProgram("구분자 는 숫자사용이 불가합니다.");
        }

        return text.substring(tempFront, tempBack);
    }

    private static void textConfirm(String text, String separator) {
        String pattern1 = String.format("^//.+(?:\\\\n)\\d+(?:[,:%s]\\d+)*$", separator);
        String pattern2 = "^\\d+[,:]\\d+(?:[,:]\\d+)*$";

        if (separator == null) {
            stopProgram("커스텀 구분자 입력시 필수 입력해야합니다!");
        } else if (!text.matches(pattern1) && !text.matches(pattern2)) {
            stopProgram("올바르지 않은 구분자입니다.");
        }
    }

    private static void stopProgram(String errMessage) {
        throw new IllegalArgumentException(errMessage);
    }
}
