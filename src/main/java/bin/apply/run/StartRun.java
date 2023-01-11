package bin.apply.run;

import bin.apply.Repository;
import bin.apply.item.map.AccessMap;
import bin.apply.type.VariableType;
import bin.exception.MatchException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import work.StartWork;
import work.item.TypeDTO;

import java.util.StringTokenizer;

import static bin.apply.Repository.startWorks;
import static bin.token.Token.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StartRun implements RunTool {
    private static StartRun startRun;
    public static StartRun getInstance() {
        if (startRun == null) {
            synchronized (StartRun.class) {
                startRun = new StartRun();
            }
        }
        return startRun;
    }

    public boolean start(String line, AccessMap repositoryArray, boolean isPriority) {
        final String[] tokens = line.split("(?=\\s|\\[)", 2);
        String variableName = tokens[0];      // ㅇㅁㅇ~ㅇㅅㅇ, ㅇㅁㅇ
        String variableValue = tokens[1];     // 변수명:값

        if (isPriority) {
            if (variableName.charAt(0) == '!' && variableName.endsWith("!")) {
                variableName = bothCut(variableName);
                return start(variableName, variableValue, line, repositoryArray);
            } else return false;
        } else return start(variableName, variableValue, line, repositoryArray);
    }

    private boolean start(String variableName, String variableValue, String line, AccessMap repositoryArray) {
        StringTokenizer accessToken = new StringTokenizer(variableName, ACCESS);
        if (!accessToken.hasMoreTokens()) return false;
        String klassName = accessToken.nextToken();
        String methodName = accessToken.hasMoreTokens()
                ? accessToken.nextToken("").substring(1) : "";

        String loop = null;
        String[] params = switch (variableValue.charAt(0)) {
            case '[' -> {
                // [값1][값2][값3] => 값1][값2][값3 => 값1, 값2, 값3
                if (isLoop(variableValue)) {
                    int position = variableValue.lastIndexOf('(');
                    loop = variableValue.substring(position).strip();
                    // [값1][값2][값3] => 값1][값2][값3
                    yield getParams(variableValue.substring(0, position).strip());
                } else yield getParams(variableValue);
            }
            case ' ' -> {
                if (isLoop(variableValue)) {
                    int position = variableValue.lastIndexOf('(');
                    loop = variableValue.substring(position).strip();
                    // 파라미터
                    yield new String[] {variableValue.substring(0, position).strip()};
                } else {
                    // 클래스 생성
                    if (methodName.equals("")
                            && VariableType.isValidType(klassName)
                            && !Repository.defineWordList.contains(klassName)) {
                        // 변수명:값 => 변수명, 값
                        String[] variableTokens = variableValue.split(GET_S, 2);
                        variableTokens[0] = variableTokens[0].strip();
                        variableTokens[1] = variableTokens[1].strip();
                        yield variableTokens;
                    } else yield new String[] {variableValue.stripLeading()};
                }
            }
            default -> new String[0];
        };

        StartWork work = startWorks.get(klassName).get(methodName);
        if (work.isLoop()) {
            // TODO: 루프 문법일때 루프가 존재하지 않을때 에러 발생
            if (loop == null) throw new MatchException().grammarError();
        } else {
            // TODO: 루프 문법이 아닐때 루프가 존재할때 에러 발생
            if (loop != null) throw new MatchException().grammarError();
        }

        TypeDTO[] typeDTOS = work.getTypes(params);
        Object[] objects = work.getParams(params);
        work.start(line, loop, objects, typeDTOS, repositoryArray);
        return true;
    }

    private boolean isLoop(String line) {
        return line.contains("(") && (line.endsWith(")") || line.contains(RETURN_TOKEN) || line.contains(PUTIN_TOKEN));
    }
}