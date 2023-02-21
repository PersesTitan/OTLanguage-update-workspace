package bin.apply.variable.create.klass;

import bin.Repository;
import bin.exception.VariableException;
import bin.repository.AccessList;
import bin.repository.TypeMap;
import work.CreateWork;

import java.util.Map;

public class DefineKlass {
    private final TypeMap repository = new TypeMap();
    private final Map<Integer, String> code;
    private final String[] paramTypes;
    private final String klassName;
    private final int start;
    private final int end;

    public DefineKlass(Map<Integer, String> code, int start, int end, String klassName, String... paramTypes) {
        // 파라미터 타입 체크
        for (String paramType : paramTypes) {
            if (!Repository.isKlass(paramType)) throw VariableException.NO_DEFINE_TYPE.getThrow(paramType);
        }
        this.code = code;
        this.start = start;
        this.end = end;
        this.klassName = klassName;
        this.paramTypes = paramTypes;
    }
}
