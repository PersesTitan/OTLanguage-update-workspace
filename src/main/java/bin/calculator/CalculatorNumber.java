package bin.calculator;

import java.util.Stack;
import java.util.StringTokenizer;

import static bin.token.Token.AND;
import static bin.token.Token.OR;

public class CalculatorNumber implements CalculatorTool {
    public void start(String line) {

    }

    private void split(String line) {

    }

    private void returnValue(Stack<String> stack) {
        int first = getFirst(stack);

    }

    private int getFirst(Stack<String> stack) {
        int value = stack.indexOf("%");
        int a = stack.indexOf("*");
        if (a != -1) value = value == -1 ? a : Math.min(value, a);
        int b = stack.indexOf("/");
        if (b != -1) value = value == -1 ? b : Math.min(value, b);
        return value;
    }

    private int getSecond(Stack<String> stack) {
        int value = stack.indexOf("-");
        int a = stack.indexOf("+");
        if (a != -1) value = value == -1 ? a : Math.min(value, a);
        return value;
    }
}
