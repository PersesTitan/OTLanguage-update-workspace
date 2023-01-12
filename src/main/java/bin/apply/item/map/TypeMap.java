package bin.apply.item.map;

import bin.apply.item.TypeValue;
import bin.apply.type.VariableType;
import bin.exception.VariableException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TypeMap extends HashMap<String, HpMap> implements Map<String, HpMap> {
    @Override
    public int size() {
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return super.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return super.containsValue(value);
    }

    @Override
    public HpMap get(Object key) {
        return super.get(key);
    }

    public TypeValue getTypeValue(String key) {
        for (Entry<String, HpMap> entry : super.entrySet()) {
            if (entry.getValue().containsKey(key))
                return new TypeValue(entry.getKey(), key, entry.getValue().get(key));
        }
        throw new VariableException().noDefineVariable();
    }

    public TypeValue getTypeValueOrNull(String key) {
        for (Entry<String, HpMap> entry : super.entrySet()) {
            if (entry.getValue().containsKey(key))
                return new TypeValue(entry.getKey(), key, entry.getValue().get(key));
        }
        return null;
    }

    public Object getValue(String key) {
        for (HpMap hpMap : super.values()) {
            if (hpMap.containsKey(key)) return hpMap.get(key);
        }
        throw new VariableException().noDefineVariable();
    }

    public Object getValueOrNull(String key) {
        for (HpMap hpMap : super.values()) {
            if (hpMap.containsKey(key)) return hpMap.get(key);
        }
        return null;
    }

    @Override
    public HpMap put(String key, HpMap value) {
        return super.put(key, value);
    }

    /**
     * @param type VariableType
     * @param name VariableName
     * @param value VariableType
     */
    public void put(String type, String name, Object value) {
        // 값이 존재할때 값을 넣기
        if (super.containsKey(type)) {
            if (super.containsKey(super.get(type).containsKey(name)))
                super.get(type).put(name, value);
            else throw new VariableException().noDefineVariable();
        } else throw new VariableException().noDefineType();
    }

    /**
     * <p>정의된 변수 타입인지 확인하는 로직</p>
     * <p>해당 변수명이 존재하는지 확인하는 로직</p>
     * @param type variable type
     * @param name variable name
     * @param value variable value
     */
    // 변수 생성
    public void create(String type, String name, Object value) {
        if (!VariableType.isValidType(type)) throw new VariableException().noDefineType();
        else if (haveVariableName(name)) throw new VariableException().sameVariable();
        else if (super.containsKey(type)) super.get(type).put(name, value);
        else {
            HpMap map = new HpMap(type);
            map.put(name, value);
            super.put(type, map);
        }
    }

    // 업데이트
    public void update(String name, Object value) {
        for (HpMap hpMap : super.values()) {
            if (hpMap.containsKey(name)) {
                hpMap.replace(name, value);
                return;
            }
        }
        throw new VariableException().noDefineVariable();
    }

    // 존재한는 변수명인지 확인하는 로직
    public boolean haveVariableName(String variableName) {
        for (HpMap hp : super.values()) {
            if (hp.containsKey(variableName)) return true;
        }
        return false;
    }

    @Override
    public HpMap remove(Object key) {
        return super.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends HpMap> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public Set<String> keySet() {
        return super.keySet();
    }

    @Override
    public Collection<HpMap> values() {
        return super.values();
    }

    @Override
    public Set<Entry<String, HpMap>> entrySet() {
        return super.entrySet();
    }
}
