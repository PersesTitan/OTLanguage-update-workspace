package bin.token;

public interface Token {
    String ACCESS = "~";
    String VARIABLE = "[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z]+[0-9ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z-]*";
    String VARIABLE_NAME = "(\\[\\d+\\])?".concat(VARIABLE);                // ex) [10]변수명
    String VARIABLE_ACCESS = ACCESS.concat("*").concat(VARIABLE);       // ex) ~~변수명
    String VARIABLE_CUT = "(?![0-9ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z-]+)";
    String GET_S = ":";
    String GET_E = "_";
    String DEFAULT = ";";

    String TRUE  = "ㅇㅇ";
    String FALSE = "ㄴㄴ";
    String OR = "ㄸ";
    String AND = "ㄲ";
    String NOT = "ㅇㄴ";

    String IF = "?ㅅ?";
    String ELSE_IF = "?ㅈ?";
    String ELSE = "?ㅉ?";

    String RETURN_TOKEN = "=>";
    String PUTIN_TOKEN  = "<=";

    String FILE = "ㅍㅅㅍ";

    // CONSOLE
    String PRINT = "ㅅㅁㅅ";
    String PRINTLN = "ㅆㅁㅆ";
}