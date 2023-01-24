package bin.apply;

import bin.exception.VariableException;
import work.CreateWork;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface Repository {
    // 파일 값
    Map<String, Map<Long, String>> filesValue = new HashMap<>();

    // 변수 타입들이 정의된 리스트
    Map<String, Class<?>> variableTypes = new HashMap<>();

    static void add(String type, Class<?> klass) {
        if (variableTypes.containsKey(type)) throw new VariableException().defineType();
        else variableTypes.put(type, klass);
    }

    Set<String> noUse = new HashSet<>();
    Map<String, CreateWork> createWorks = new HashMap<>();
}
