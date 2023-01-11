package bin.apply.run;

import bin.apply.item.map.AccessMap;
import bin.exception.VariableException;
import bin.logic.variable.GetVariable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import work.ReplaceWork;
import work.item.TypeDTO;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static bin.apply.Repository.replaceWorks;
import static bin.token.Token.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReplaceRun implements RunTool {
    private static ReplaceRun replaceRun;
    public static ReplaceRun getInstance() {
        if (replaceRun == null) {
            synchronized (ReplaceRun.class) {
                replaceRun = new ReplaceRun();
            }
        }
        return replaceRun;
    }

    private final String ALL = "[\\s\\S]";
    private final Matcher matcher1 = Pattern.compile(String.format(
            "%s[^%s]".concat(ALL).concat("*%s(").concat(ALL).concat("]*%s)?"),
            GET_S, GET_S, GET_E, DEFAULT
    )).matcher("");
    private final Matcher matcher2 = Pattern.compile(String.format(
            "%s[^%s%s]+%s([^%s%s]*%s)?",
            GET_S, GET_S, GET_E, GET_E,
            GET_E, DEFAULT, DEFAULT
    )).matcher("");

    public String start(String line, AccessMap repositoryArray) {
        line = getReplaceToken(matcher1.reset(line), line, repositoryArray);
        if (line.contains(GET_S) && line.contains(GET_E))
            line = getReplaceToken(matcher2.reset(line), line, repositoryArray);
        return line;
    }

    private String getReplaceToken(Matcher matcher, String line, AccessMap repositoryArray) {
        while (matcher.find()) {
            String group = matcher.group();
            String token = getToken(group, line, repositoryArray);
            if (token != null) matcher.reset(line = line.replaceFirst(Pattern.quote(group), token));
        }
        return line;
    }

    private String getToken(String group, String line, AccessMap repositoryArray) {
        if (group.endsWith(DEFAULT)) {
            int pos = group.lastIndexOf(GET_E);
            String value = group.substring(1, pos);             // 값
            String def = group.substring(pos + 1, group.length() - 1); // 기본값
            return getValue(line, value, def, repositoryArray);
        } else if (group.endsWith(GET_E)) {
            String value = bothCut(group);
            return getValue(line, value, null, repositoryArray);
        } else return group;
    }

    // line 계산할 값, def 기본값
    public String getValue(String line, String token, String def, AccessMap repositoryArray) {
        final String[] tokens = token.split("(?=\\s|\\[)", 2);
        final String variableName = tokens[0];      // ㅇㅁㅇ~ㅇㅅㅇ, ㅇㅁㅇ
        if (tokens.length == 1) {
            // 파라미터가 존재하지 않을때
            Object value = GetVariable.getInstance().get(token, repositoryArray);
            return value == null ? def : value.toString();
        } else {
            String variableValue = tokens[1];     // 변수명:값
            StringTokenizer accessToken = new StringTokenizer(variableName, ACCESS);
            String klassName = accessToken.hasMoreTokens()
                    ? accessToken.nextToken() : "";
            String methodName = accessToken.hasMoreTokens()
                    ? accessToken.nextToken("").substring(1) : "";

            String[] params = switch (variableValue.charAt(0)) {
                case '[' -> {
                    if (variableValue.endsWith("]")) yield getParams(variableValue);
                    else throw new VariableException().typeMatch();
                }
                case ' ' -> new String[] {variableValue.strip()};
                default -> new String[0];
            };

            Object value;
            if (replaceWorks.containsKey(klassName) && replaceWorks.get(klassName).containsKey(methodName)) {
                ReplaceWork work = replaceWorks.get(klassName).get(methodName);
                TypeDTO[] typeDTOS = work.getTypes(params);
                Object[] objects = work.getParams(params);
                // 순수 변수 가져오기
                value = work.start(line, objects, typeDTOS, repositoryArray);
            } else value = GetVariable.getInstance().get(token, repositoryArray);
            return value == null ? def : value.toString();
        }
    }
}