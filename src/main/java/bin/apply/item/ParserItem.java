package bin.apply.item;

import bin.Repository;
import bin.exception.MatchException;
import bin.exception.VariableException;
import bin.token.EditToken;
import bin.token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public final class ParserItem {
//    private final String klassName;
//    private final String methodName;
//    private final String[] params;


    public ParserItem(String line) {
        int findStart = EditToken.findStart(line);
        // 클래스명 또는 메소드명 (ㅇㅁㅇ~ㅁㅅㅁ 값)
        StringTokenizer tokenizer = new StringTokenizer(line.substring(0, findStart), Token.ACCESS);
        String params = line.substring(findStart);
        if (tokenizer.hasMoreTokens()) {
            String klassName = tokenizer.nextToken();
            String methodName = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : "";
            // 순수 클래스만 존재할때 (ㅇㅁㅇ 값)
            if (Repository.createWorks.containsKey(klassName)) {
                if (methodName.equals("")) {

                } else throw VariableException.VARIABLE_NAME_ERROR.getThrow(klassName);
            }
        }
    }
}
