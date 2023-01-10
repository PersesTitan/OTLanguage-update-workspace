package bin.calculator;

import bin.apply.run.StartRun;
import bin.exception.MatchException;
import bin.exception.VariableException;

import java.util.*;
import java.util.function.BiFunction;

import static bin.exception.MatchException.*;
import static bin.exception.MatchException.GrammarTypeClass.*;
import static bin.token.Token.*;
import static bin.token.Token.AND;

public interface CalculatorTool {
    Map<String, BiFunction<Double, Double, String>> compare = new HashMap<>() {{
        put(">", (a, b) -> a > b ? TRUE : FALSE);
        put("<", (a, b) -> a < b ? TRUE : FALSE);
        put("<=", (a, b) -> a <= b ? TRUE : FALSE);
        put(">=", (a, b) -> a >= b ? TRUE : FALSE);
        put("=", (a, b) -> Objects.equals(a, b) ? TRUE : FALSE);
    }};

    Map<String, BiFunction<Object, Object, Object>> number = new HashMap<>() {{
        put("+", CalculatorTool::sum);
        put("-", CalculatorTool::minus);
        put("*", CalculatorTool::multiply);
        put("/", CalculatorTool::divide);
        put("%", CalculatorTool::remainder);
    }};

    default boolean isNumber(Object value) {
        return value instanceof Integer
                || value instanceof Long
                || value instanceof Float
                || value instanceof Double;
    }

    default Stack<String> getStack(String line) {
        Stack<String> stack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(line, "ㅇㄴ".concat(OR).concat(AND), true);
        while (tokenizer.hasMoreTokens()) {
            String group = tokenizer.nextToken();
            switch (group) {
                case "ㅇ" -> {
                    String a = tokenizer.nextToken();
                    if (a.equals("ㅇ")) stack.add(TRUE);
                    else if (a.equals("ㄴ")) stack.add(NOT);
                    else if (compare.containsKey(a)) {      // >, <, <=, >=, =
                        String b = tokenizer.nextToken();
                        if (b.equals("ㅇ")) stack.add(a);
                        else {
                            if (stack.isEmpty()) stack.add(group.concat(a).concat(b));
                            else stack.add(stack.pop().concat(group).concat(a).concat(b));
                        }
                    } else if (number.containsKey(a)) {     // +, -, *, /, %
                        String b = tokenizer.nextToken();
                        if (b.equals("ㅇ")) stack.add(a);
                        else {
                            if (stack.isEmpty()) stack.add(group.concat(a).concat(b));
                            else stack.add(stack.pop().concat(group).concat(a).concat(b));
                        }
                    } else stack.add(stack.pop().concat(group).concat(a));
                }
                case "ㄴ" -> {
                    String a = tokenizer.nextToken();
                    if (a.equals("ㄴ")) stack.add(FALSE);
                    else stack.add(group.concat(a));
                }
                case OR -> stack.add(OR);
                case AND -> stack.add(AND);
                default -> stack.add(group);
            }
        }
        return stack;
    }

    private static Object sum(Object a, Object b) {
        if (a instanceof Integer a1) {
            if (b instanceof Integer b1) return a1 + b1;
            else if (b instanceof Float b1) return a1 + b1;
            else if (b instanceof Long b1) return a1 + b1;
            else if (b instanceof Double b1) return a1 + b1;
            else throw new VariableException().typeMatch();
        } else if (a instanceof Float a1) {
            if (b instanceof Integer b1) return a1 + b1;
            else if (b instanceof Float b1) return a1 + b1;
            else if (b instanceof Long b1) return a1 + b1;
            else if (b instanceof Double b1) return a1 + b1;
            else throw new VariableException().typeMatch();
        } else if (a instanceof Long a1) {
            if (b instanceof Integer b1) return a1 + b1;
            else if (b instanceof Float b1) return a1 + b1;
            else if (b instanceof Long b1) return a1 + b1;
            else if (b instanceof Double b1) return a1 + b1;
            else throw new VariableException().typeMatch();
        } else if (a instanceof Double a1) {
            if (b instanceof Integer b1) return a1 + b1;
            else if (b instanceof Float b1) return a1 + b1;
            else if (b instanceof Long b1) return a1 + b1;
            else if (b instanceof Double b1) return a1 + b1;
            else throw new VariableException().typeMatch();
        } else throw new VariableException().typeMatch();
    }

    private static Object minus(Object a, Object b) {
        if (a instanceof Integer a1) {
            if (b instanceof Integer b1) return a1 - b1;
            else if (b instanceof Float b1) return a1 - b1;
            else if (b instanceof Long b1) return a1 - b1;
            else if (b instanceof Double b1) return a1 - b1;
            else throw new VariableException().typeMatch();
        } else if (a instanceof Float a1) {
            if (b instanceof Integer b1) return a1 - b1;
            else if (b instanceof Float b1) return a1 - b1;
            else if (b instanceof Long b1) return a1 - b1;
            else if (b instanceof Double b1) return a1 - b1;
            else throw new VariableException().typeMatch();
        } else if (a instanceof Long a1) {
            if (b instanceof Integer b1) return a1 - b1;
            else if (b instanceof Float b1) return a1 - b1;
            else if (b instanceof Long b1) return a1 - b1;
            else if (b instanceof Double b1) return a1 - b1;
            else throw new VariableException().typeMatch();
        } else if (a instanceof Double a1) {
            if (b instanceof Integer b1) return a1 - b1;
            else if (b instanceof Float b1) return a1 - b1;
            else if (b instanceof Long b1) return a1 - b1;
            else if (b instanceof Double b1) return a1 - b1;
            else throw new VariableException().typeMatch();
        } else throw new VariableException().typeMatch();
    }

    private static Object divide(Object a, Object b) {
        if (a instanceof Integer a1) {
            if (b instanceof Integer b1) return a1 / b1;
            else if (b instanceof Float b1) return a1 / b1;
            else if (b instanceof Long b1) return a1 / b1;
            else if (b instanceof Double b1) return a1 / b1;
            else throw new VariableException().typeMatch();
        } else if (a instanceof Float a1) {
            if (b instanceof Integer b1) return a1 / b1;
            else if (b instanceof Float b1) return a1 / b1;
            else if (b instanceof Long b1) return a1 / b1;
            else if (b instanceof Double b1) return a1 / b1;
            else throw new VariableException().typeMatch();
        } else if (a instanceof Long a1) {
            if (b instanceof Integer b1) return a1 / b1;
            else if (b instanceof Float b1) return a1 / b1;
            else if (b instanceof Long b1) return a1 / b1;
            else if (b instanceof Double b1) return a1 / b1;
            else throw new VariableException().typeMatch();
        } else if (a instanceof Double a1) {
            if (b instanceof Integer b1) return a1 / b1;
            else if (b instanceof Float b1) return a1 / b1;
            else if (b instanceof Long b1) return a1 / b1;
            else if (b instanceof Double b1) return a1 / b1;
            else throw new VariableException().typeMatch();
        } else throw new VariableException().typeMatch();
    }

    private static Object multiply(Object a, Object b) {
        if (a instanceof Integer a1) {
            if (b instanceof Integer b1) return a1 * b1;
            else if (b instanceof Float b1) return a1 * b1;
            else if (b instanceof Long b1) return a1 * b1;
            else if (b instanceof Double b1) return a1 * b1;
            else throw new VariableException().typeMatch();
        } else if (a instanceof Float a1) {
            if (b instanceof Integer b1) return a1 * b1;
            else if (b instanceof Float b1) return a1 * b1;
            else if (b instanceof Long b1) return a1 * b1;
            else if (b instanceof Double b1) return a1 * b1;
            else throw new VariableException().typeMatch();
        } else if (a instanceof Long a1) {
            if (b instanceof Integer b1) return a1 * b1;
            else if (b instanceof Float b1) return a1 * b1;
            else if (b instanceof Long b1) return a1 * b1;
            else if (b instanceof Double b1) return a1 * b1;
            else throw new VariableException().typeMatch();
        } else if (a instanceof Double a1) {
            if (b instanceof Integer b1) return a1 * b1;
            else if (b instanceof Float b1) return a1 * b1;
            else if (b instanceof Long b1) return a1 * b1;
            else if (b instanceof Double b1) return a1 * b1;
            else throw new VariableException().typeMatch();
        } else throw new VariableException().typeMatch();
    }

    private static Object remainder(Object a, Object b) {
        if (a instanceof Integer a1) {
            if (b instanceof Integer b1) return a1 % b1;
            else if (b instanceof Float b1) return a1 % b1;
            else if (b instanceof Long b1) return a1 % b1;
            else if (b instanceof Double b1) return a1 % b1;
            else throw new VariableException().typeMatch();
        } else if (a instanceof Float a1) {
            if (b instanceof Integer b1) return a1 % b1;
            else if (b instanceof Float b1) return a1 % b1;
            else if (b instanceof Long b1) return a1 % b1;
            else if (b instanceof Double b1) return a1 % b1;
            else throw new VariableException().typeMatch();
        } else if (a instanceof Long a1) {
            if (b instanceof Integer b1) return a1 % b1;
            else if (b instanceof Float b1) return a1 % b1;
            else if (b instanceof Long b1) return a1 % b1;
            else if (b instanceof Double b1) return a1 % b1;
            else throw new VariableException().typeMatch();
        } else if (a instanceof Double a1) {
            if (b instanceof Integer b1) return a1 % b1;
            else if (b instanceof Float b1) return a1 % b1;
            else if (b instanceof Long b1) return a1 % b1;
            else if (b instanceof Double b1) return a1 % b1;
            else throw new VariableException().typeMatch();
        } else throw new VariableException().typeMatch();
    }
}
