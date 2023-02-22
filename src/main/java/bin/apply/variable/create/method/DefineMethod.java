package bin.apply.variable.create.method;

import bin.Repository;
import bin.apply.Read;
import bin.apply.variable.Parser;
import bin.exception.MatchException;
import bin.exception.VariableException;
import bin.repository.TypeMap;
import lombok.Getter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DefineMethod {
    @Getter
    private final String methodName;

    private final TypeMap repository = new TypeMap();
    private final String filePath;
    private final String[][] paramTypes;
    private final int start;
    private final int end;

    public DefineMethod(String filePath, int start, int end,
                        String methodName, String[][] paramTypes) {
        // 변수명 중복 확인용
        Set<String> variableNames = new HashSet<>();
        // 파라미터 타입 체크
        for (String[] paramType : paramTypes) {
            if (!Repository.isKlass(paramType[0])) throw VariableException.NO_DEFINE_TYPE.getThrow(paramType[0]);
            // 변수명 중복 확인
            String variableName = paramType[1];
            if (variableNames.contains(variableName))
                throw VariableException.DEFINE_NAME.getThrow(variableName);
            else variableNames.add(variableName);
        }
        this.filePath = filePath;
        this.start = start;
        this.end = end;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
    }

    public void setVariable(String[] paramValues) {
        int len = this.paramTypes.length;
        if (paramValues.length != len)
            throw MatchException.PARAM_COUNT_ERROR.getThrow(len + "!=" + paramValues.length);
        for (int i = 0; i < len; i++) {
            String[] param = this.paramTypes[i];
            String type = param[0];
            this.repository.create(type, param[1], Parser.parser(type, paramValues[i]));
        }
    }

    public void start(String... paramValues) {
        Repository.repositoryArrays.addFirst(this.repository);
        this.setVariable(paramValues);
        try {
            Read.read(this.filePath, this.start, this.end);
        } finally {
            // 추가했던 저장소 제거
            Repository.repositoryArrays.removeFirst();
        }
    }
}
