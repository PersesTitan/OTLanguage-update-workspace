package bin.apply;

import bin.Repository;
import bin.apply.mode.DebugMode;
import bin.apply.variable.Parser;
import bin.exception.MatchException;
import bin.repository.AccessList;
import bin.repository.HpMap;
import bin.token.EditToken;
import bin.token.KlassToken;
import bin.token.Token;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static bin.token.Token.*;

public class Replace {
    private static final Matcher matcher1 = Pattern.compile(String.format(
                    "%s[^%s][\\s\\S]*%s([\\s\\S]*%s)?",
                    REPLACE_S, REPLACE_S, REPLACE_E, REPLACE_D))
            .matcher("");
    private static final Matcher matcher2 = Pattern.compile(String.format(
            "%s[^%s%s]+%s([^%s%s]*%s)?",
            REPLACE_S, REPLACE_S, REPLACE_E, REPLACE_E,
            REPLACE_E, REPLACE_D, REPLACE_D
    )).matcher("");

    public static boolean isHave(String line) {
        return line.contains(REPLACE_S) && line.contains(REPLACE_E);
    }

    public static String startLine(String line) {
        line = startMatch(matcher1.reset(line), line);
        return isHave(line)
                ? startMatch(matcher2.reset(line), line)
                : line;
    }

    private static String startMatch(Matcher matcher, String line) {
        while (matcher.find()) {
            String group = matcher.group();
            String value = EditToken.bothCut(group);
            String def = null;
            if (group.endsWith(REPLACE_D)) {
                int i = value.lastIndexOf(REPLACE_E);
                def = value.substring(i + 1);
                value = value.substring(0, i);
            }

            String sub;
            if ((sub = sub(value, def)) != null) {
                matcher.reset(line = line.replaceFirst(Pattern.quote(group), sub));
            }
        }
        return line;
    }

    private static String sub(String value, String def) {
        try {
            return Parser.parser(value);
        } catch (Exception e) {
            if (DebugMode.isDevelopment()) e.printStackTrace();
            return def;
        }
    }

    // ex) line : ㅇㅁㅇ~ㅁㅅㅁ[1][값]
    public static Object replace(String line) {
        String[] tokens = line.split("(?=\\s|\\" + Token.PARAM_S + ")", 2);
        if (tokens[0].contains(Token.ACCESS)) {                                     // ex) ㅇㅁㅇ~ㅅㅁㅅ
            StringTokenizer tokenizer = new StringTokenizer(tokens[0], Token.ACCESS);
            if (tokenizer.hasMoreTokens()) {
                String klassName = tokenizer.nextToken();
                String methodName = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : "";
                HpMap map = Repository.repositoryArrays.getMap(klassName);
                return Repository.replaceWorks
                        .get(map.getKlassType(), methodName)
                        .replace(map.get(klassName), tokens[1]);
            } else throw MatchException.GRAMMAR_ERROR.getThrow(tokens[0]);
        } else {
            String klassType = tokens[0];
            // 변수 생성하기
            if (Repository.createWorks.containsKey(klassType))
                throw MatchException.GRAMMAR_ERROR.getThrow(line);
            else {
                // 정의되지 않은 클래스일때
                if (tokens[1].charAt(0) == Token.PARAM_S) {
                    // ex) [값1][값2]
                    return Repository.replaceWorks.get(KlassToken.SYSTEM)
                            .get(klassType)
                            .replace(null, tokens[1]);
                } else {
                    // ex) ㅅㅁㅅ 값
                    return Repository.replaceWorks.get(KlassToken.SYSTEM)
                            .get(klassType)
                            .replace(null, tokens[1].strip());
                }
            }
        }
    }
}
