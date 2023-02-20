package bin.apply;

import bin.apply.item.LoopItem;
import bin.exception.MatchException;
import bin.token.Token;

import java.util.Map;
import java.util.Stack;

public class Loop {
    public static int next(Map<Integer, String> code, int position) {
        Stack<Integer> stack = new Stack<>();
        int size = code.size();
        for (int i = position + 1; i<size; i++) {
            String line = code.get(i);
            if (line.isEmpty()) continue;
            if (line.startsWith(Token.LOOP_S)) {
                if (stack.isEmpty()) return i;
                else stack.pop();
            } else if (line.endsWith(Token.LOOP_E)) stack.add(i);
        }
        throw MatchException.ZONE_MATCH_ERROR.getThrow(code.get(position));
    }

    // ex) value : } => 변수명
    public static String getReturn(String value) {
        int i = value.indexOf(Token.RETURN_TOKEN) + Token.RETURN_TOKEN.length();
        return value.substring(i).strip();
    }

    // ex) value : } <= ㅇㅁㅇ 변수명
    public static String getPut(String value) {
        int i = value.indexOf(Token.PUT_TOKEN) + Token.PUT_TOKEN.length();
        return value.substring(i).strip();
    }

    public static boolean isReturn(String value) {
        int i = value.indexOf(Token.RETURN_TOKEN);
        return value.startsWith(Token.LOOP_E)
                && i != -1
                && value.substring(1, i).isBlank()
                && !value.substring(i + Token.RETURN_TOKEN.length()).isBlank();
    }

    public static boolean isPut(String value) {
        int i = value.indexOf(Token.PUT_TOKEN);
        return value.startsWith(Token.LOOP_E)
                && i != -1
                && value.substring(1, i).isBlank()
                && !value.substring(i + Token.PUT_TOKEN.length()).isBlank();
    }

    public static LoopItem getLoopType(String value) {
        if (value.equals(Token.LOOP_S)) return LoopItem.NONE;   // }
        else if (isPut(value)) return LoopItem.PUT;                               // } <= ㅇㅁㅇ 변수명
        else if (isReturn(value)) return LoopItem.RETURN;                         // } => 변수명
        else return LoopItem.NOT;
    }
}
