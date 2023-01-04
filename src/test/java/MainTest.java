import bin.apply.Setting;
import bin.apply.item.map.AccessMap;
import bin.calculator.CalculatorTool;
import test.StackTest;
import test.StringTokenTest;

import java.util.Stack;
import java.util.StringTokenizer;

import static bin.token.Token.*;

public class MainTest implements CalculatorTool {
    public MainTest() {
        Stack<String> stack = getStack("133ㅇ +ㅇ 변수명 ㅇㅇㄸㄴㄴ");
        System.out.println(stack);
    }

    public static void main(String[] args) {
        new MainTest();
        String line = "133 ㅇ+ㅇ 변수명 ㅇㅇㄸㄴㄴ";
        StringTokenizer tokenizer = new StringTokenizer(line, NOT + OR + AND, true);

        StringTokenTest tokenTest = new StringTokenTest(line, NOT, TRUE, FALSE, OR, AND);
        System.out.println(tokenTest.getList());

        new CreateModule().create();
        AccessMap map = new AccessMap();
        String total = """
                ㅆㅁㅆ 앙
                ㅇㅅㅇ 변수:1.2
                ㅆㅁㅆ :변수_
                
                ㅇㅂㅇ 변수1:ㅇㅇ
                ㅇㅂㅇ 변수2:ㄴㄴ
                ㄹㅂㄹ 변수3:[ㄴㄴ, ㅇㅇ]
                ㅆㅁㅆ :변수1_ :변수2_ :변수3_
                """;
        total.lines().forEach(v -> Setting.start(v, v, map));
    }
}