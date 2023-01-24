package bin.apply.repository;

import bin.apply.Repository;
import bin.exception.VariableException;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@RequiredArgsConstructor
public class VariableInformationMap extends HashMap<String, HpMap> {
    /**
     * 변수 생성하는 로직
     * @param type  ex) ㅇㅁㅇ
     * @param key   ex) 변수명
     * @param value ex) Hello World
     */
    public void create(String type, String key, Object value) {
        if (Repository.variableTypes.containsKey(type)) {
            // 이미 정의된 변수명일때
            if (haveVariableName(key)) throw new VariableException().defineType();
            // 해당 타입이 존재할때
            if (super.containsKey(type)) {
                super.get(type).put(key, getValue(type, value));
            } else {
                HpMap map = new HpMap(Repository.variableTypes.get(type));
                map.put(key, getValue(type, value));
                super.put(type, map);
            }
        // 존재하지않는 변수 타입일때
        } else throw new VariableException().noDefineType();
    }

    public void update(String key, Object value) {
        // 정의되지 않은 변수명일때
        for (Entry<String, HpMap> entry: super.entrySet()) {
            // 변수가 존재할때
            if (entry.getValue().containsKey(key)) {
                entry.getValue().put(key, getValue(entry.getKey(), value));
                return;
            }
        }
        throw new VariableException().noDefineVariable();
    }

    public Object get(String variableName) {
        // 정의되지 않은 변수명일때
        for (HpMap map : super.values()) {
            if (map.containsKey(variableName)) return map.get(variableName);
        }
        throw new VariableException().noDefineVariable();
    }

    private Object getValue(String type, Object value) {
        if (Repository.variableTypes.get(type).equals(value.getClass())) return value;
        else return Repository.createWorks.get(type).run(new Object[] {value}, null);
    }

    // 변수명이 존재하는지 확인
    private boolean haveVariableName(String variableName) {
        return super.values()
                .stream()
                .anyMatch(v -> v.containsKey(variableName));
    }
}
