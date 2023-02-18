package bin.apply;

import bin.Repository;
import bin.repository.AccessList;
import bin.repository.HpMap;
import bin.token.EditToken;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static bin.token.Token.*;

public class Replace {
    private static Replace replace;
    public static Replace getInstance() {
        if (replace == null) {
            synchronized (Replace.class) {
                replace = new Replace();
            }
        }
        return replace;
    }

    private final Matcher matcher1 = Pattern.compile(String.format(
                    "%s[^%s][\\s\\S]*%s([\\s\\S]*%s)?",
                    REPLACE_S, REPLACE_S, REPLACE_E, REPLACE_D))
            .matcher("");
    private final Matcher matcher2 = Pattern.compile(String.format(
            "%s[^%s%s]+%s([^%s%s]*%s)?",
            REPLACE_S, REPLACE_S, REPLACE_E, REPLACE_E,
            REPLACE_E, REPLACE_D, REPLACE_D
    )).matcher("");

    public Object start(AccessList repositoryArray, String line) {
        return null;
    }

    private String replace(AccessList repositoryArray, String line, String def) {
        int findStart = EditToken.findStart(line);
        String[] klassMethod = EditToken.getKlassName(line, findStart);
        String klassName = klassMethod[0];
        String methodName = klassMethod[1];

        String[] paramsValues;
        if (EditToken.isKlassName(klassName)) {

        } else {
            HpMap map = repositoryArray.getMap(klassName);
            String klassType = map.getKlassType();
        }

        return null;
    }
}
