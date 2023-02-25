package bin.token;

import bin.Repository;
import bin.exception.MatchException;
import bin.repository.AccessList;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public interface EditToken {
    static String bothCut(String line) {
        return line.substring(1, line.length() - 1);
    }

    static int findStart(String line) {
        int len = line.length();
        for (int i = 0; i<len; i++) {
            switch (line.charAt(i)) {
                case ' ', '\t', '\n', '\r', '\f', Token.PARAM_S -> {
                    return i;
                }
            }
        }
        return len;
    }

    static String[] getKlassName(String line, int i) {
        line = line.substring(0, i);
        if (!line.contains(Token.ACCESS)) return new String[] {line};
        StringTokenizer tokenizer = new StringTokenizer(line, Token.ACCESS);
        if (tokenizer.hasMoreTokens()) {
            String klassName = tokenizer.nextToken();
            String methodName = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : "";
            return new String[] {klassName, methodName};
        } else throw MatchException.PARAM_MATCH_ERROR.getThrow(line);
    }

    static String[] getParams(String line, int i, int paramCount) {
        return cutParams(paramCount, line.substring(i));
    }

    // [값1][값2][값3] => 값1, 값2, 값3
    static String[] cutParams(int paramCount, String line) {
        if (line == null) return new String[0];
        if (paramCount == 1) {
            if (line.charAt(0) == Token.PARAM_S) {
                if (line.charAt(line.length() - 1) == Token.PARAM_E)
                    return new String[] {EditToken.bothCut(line)};
                else throw MatchException.PARAM_MATCH_ERROR.getThrow(line);
            } else return new String[] {line.strip()};
        }
        List<String> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        char[] chars = line.toCharArray();
        int i = 0;
        for (char c : chars) {
            switch (c) {
                case Token.PARAM_S -> stack.add(i);
                case Token.PARAM_E -> {
                    if (stack.isEmpty()) throw MatchException.PARAM_MATCH_ERROR.getThrow(line);
                    else if (stack.size() == 1) list.add(line.substring(stack.pop() + 1, i));
                    else stack.pop();
                }
                default -> {
                    if (stack.isEmpty()) throw MatchException.GRAMMAR_ERROR.getThrow(line);
                }
            }
            i++;
        }
        if (!stack.isEmpty()) throw MatchException.PARAM_MATCH_ERROR.getThrow(line);
        if (paramCount != list.size()) throw MatchException.PARAM_COUNT_ERROR.getThrow(list.toString());
        return list.toArray(new String[0]);
    }
}
