package bin.apply;

import bin.Repository;
import bin.exception.VariableException;
import bin.repository.AccessList;
import bin.token.EditToken;
import bin.token.KlassToken;
import bin.token.Token;
import work.CreateWork;

import java.util.regex.Pattern;

public class Start {
    public void start(AccessList repositoryArray, String line) {
        String[] tokens = line.split("(?=\\s|\\" + Token.PARAM_S + ")", 2);
        if (tokens[0].contains(Token.ACCESS)) {                                     // ex) ㅇㅁㅇ~ㅅㅁㅅ

        } else {
            if (Repository.createWorks.containsKey(tokens[0])) {                    // ex) ㅇㅁㅇ
                if (tokens[1].charAt(0) != Token.PARAM_S) {                         // <공백>값
                    String value = tokens[1].strip();
                    if (value.contains(Token.PUT)) {                                // ㅇㅁㅇ <변수값>:<초기값>
                        int i = value.indexOf(Token.PUT);
                        String variableName = value.substring(0, i);                // 번수값
                        String variableValue = value.substring(i + 1);    // 초기값
                        repositoryArray.create(tokens[0], variableName, variableValue);
                    }
                } else {

                }
            } else {
                // 정의되지 않은 클래스일때
                String klassType = KlassToken.DEFAULT_KLASS.get();


            }
        }
    }
}
