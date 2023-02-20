package bin.apply;

import bin.exception.MatchException;
import bin.token.Token;

import java.util.Map;
import java.util.Stack;

public class Loop implements Token {
    public int next(Map<Integer, String> code, int position) {
        Stack<Integer> stack = new Stack<>();
        int size = code.size();
        for (int i = position + 1; i<size; i++) {
            String line = code.get(i);
            if (line.isEmpty()) continue;
            if (line.startsWith(LOOP_S)) {
                if (stack.isEmpty()) return i;
                else stack.pop();
            } else if (line.endsWith(LOOP_E)) stack.add(i);
        }
        throw MatchException.ZONE_MATCH_ERROR.getThrow(code.get(position));
    }

    // ex) value : } => 변수명
    public String getReturn(String value) {
        int i = value.indexOf(RETURN_TOKEN) + RETURN_TOKEN.length();
        return value.substring(i).strip();
    }

    // ex) value : } <= ㅇㅁㅇ 변수명
    public String getPut(String value) {
        int i = value.indexOf(PUT_TOKEN) + PUT_TOKEN.length();
        return value.substring(i).strip();
    }

    public boolean isReturn(String value) {
        int i = value.indexOf(RETURN_TOKEN);
        return value.startsWith(LOOP_E)
                && i != -1
                && value.substring(1, i).isBlank()
                && !value.substring(i + RETURN_TOKEN.length()).isBlank();
    }

    public boolean isPut(String value) {
        int i = value.indexOf(PUT_TOKEN);
        return value.startsWith(LOOP_E)
                && i != -1
                && value.substring(1, i).isBlank()
                && !value.substring(i + PUT_TOKEN.length()).isBlank();
    }
}
