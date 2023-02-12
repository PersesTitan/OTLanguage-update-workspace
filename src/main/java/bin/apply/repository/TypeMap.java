package bin.apply.repository;

import bin.apply.variable.VariableTool;
import bin.exception.VariableException;

import java.util.Arrays;
import java.util.HashMap;

public class TypeMap extends HashMap<String, HpMap> {
    public void create(String type, String name, Object value) {
        if (defineVariableName(name)) throw VariableException.DEFINE_NAME.getThrow(name);
        if (super.containsKey(type)) super.get(type).put(name, value);
        else {
            VariableTool.getInstance().checkNoDefine(type);
            super.put(type, new HpMap(type) {{put(name, value);}});
        }
    }

    // 변수명이 존재하는지 확인
    private boolean defineVariableName(String name) {
        return super.values()
                .stream()
                .anyMatch(v -> v.containsKey(name));
    }

    public void update(String name, Object value) {
        for (HpMap map : super.values()) {
            if (map.containsKey(name)) {
                map.replace(name, value);
                return;
            }
        }
        throw VariableException.NO_DEFINE_NAME.getThrow(name);
    }

    public Object find(String name) {
        for (HpMap map : super.values()) {
            if (map.containsKey(name)) return map.get(name);
        }
        throw VariableException.NO_DEFINE_NAME.getThrow(name);
    }

    public HpMap findMap(String name) {
        return super.values().stream()
                .filter(v -> v.containsKey(name))
                .findFirst()
                .orElseThrow(() -> VariableException.NO_DEFINE_NAME.getThrow(name));
    }

    public boolean findVar(String name) {
        return super.values()
                .stream()
                .anyMatch(v -> v.containsKey(name));
    }

    public String getKlass(String name) {
        for (HpMap map : super.values()) {
            if (map.containsKey(name)) return map.getKlassType();
        }
        throw VariableException.NO_DEFINE_NAME.getThrow(name);
    }
}
