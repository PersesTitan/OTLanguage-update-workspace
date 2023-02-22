package bin.apply;

import bin.Repository;
import bin.exception.MatchException;
import bin.exception.VariableException;
import bin.repository.AccessList;
import bin.repository.HpMap;
import bin.token.EditToken;
import bin.token.KlassToken;
import bin.token.Token;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class Start {
    public static int start(int position, String line) {
        String[] tokens = line.split("(?=\\s|\\" + Token.PARAM_S + ")", 2);
        String params = tokens.length == 1
                ? null
                : tokens[1];
        if (tokens[0].contains(Token.ACCESS)) {                                     // ex) ㅇㅁㅇ~ㅅㅁㅅ
            StringTokenizer tokenizer = new StringTokenizer(tokens[0], Token.ACCESS);
            if (tokenizer.hasMoreTokens()) {
                String klassName = tokenizer.nextToken();
                String methodName = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : "";
                HpMap map = Repository.repositoryArrays.getMap(klassName);
                return Repository.startWorks
                        .get(map.getKlassType(), methodName)
                        .start(position, map.get(klassName), params);
            } else throw MatchException.GRAMMAR_ERROR.getThrow(tokens[0]);
        } else {
            String klassType = tokens[0];
            // 변수 생성하기
            if (Repository.createWorks.containsKey(klassType)) {                    // ex) ㅇㅁㅇ
                if (params == null) throw MatchException.GRAMMAR_ERROR.getThrow(line);
                else if (params.charAt(0) != Token.PARAM_S) {                         // <공백>값
                    String value = params.strip();
                    if (value.contains(Token.PUT)) {                                // ㅇㅁㅇ <변수값>:<초기값>
                        int i = value.indexOf(Token.PUT);
                        Repository.repositoryArrays.create(klassType,
                                value.substring(0, i),                      // 변수값
                                value.substring(i + 1));          // 초기값
                        return position + 1;
                    } else throw MatchException.GRAMMAR_ERROR.getThrow(line);
                } else throw MatchException.GRAMMAR_ERROR.getThrow(line);
            } else {
                // 정의되지 않은 클래스일때
                if (params == null || params.charAt(0) == Token.PARAM_S) {
                    // ex) [값1][값2]
                    return Repository.startWorks.get(KlassToken.SYSTEM)
                            .get(klassType)
                            .start(position, null, params);
                } else {
                    // ex) ㅅㅁㅅ 값
                    return Repository.startWorks.get(KlassToken.SYSTEM)
                            .get(klassType)
                            .start(position, null, params.strip());
                }
            }
        }
    }
}
