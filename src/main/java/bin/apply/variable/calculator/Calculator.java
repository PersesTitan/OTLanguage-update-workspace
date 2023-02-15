package bin.apply.variable.calculator;

import bin.apply.variable.calculator.number.NumberItem;
import bin.exception.MatchException;

import java.util.Stack;
import java.util.StringTokenizer;

public interface Calculator {
    default Stack<String> parser(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line, "ㅇ", true);
        Stack<String> stack = new Stack<>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.equals("ㅇ") && tokenizer.hasMoreTokens()) {
                String sing = tokenizer.nextToken();
                if (NumberItem.contains(sing)) {
                    if (tokenizer.hasMoreTokens()) {
                        if (tokenizer.nextToken().equals("ㅇ")) stack.add(sing);
                        else throw MatchException.CALCULATOR_ERROR.getThrow(line);
                    } else throw MatchException.CALCULATOR_ERROR.getThrow(line);
                } else add(stack, token.concat(sing));
            } else add(stack, token);
        }

        return stack;
    }

    private void add(Stack<String> stack, String token) {
        if (stack.isEmpty() || NumberItem.contains(stack.lastElement())) stack.add(token);
        else stack.add(stack.pop().concat(token));
    }
}
