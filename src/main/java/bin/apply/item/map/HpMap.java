package bin.apply.item.map;

import bin.apply.type_tool.Casting;
import bin.exception.VariableException;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static bin.apply.type_tool.VariableValueGet.getInteger;
import static bin.token.Token.VARIABLE;

@RequiredArgsConstructor
public class HpMap extends HashMap<String, Object> implements Map<String, Object>, Casting {
    private final Map<String, Integer> hp = new HashMap<>();
    private final String type;

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
        String k = key.toString();
        if (k.startsWith("[")) k = k.substring(k.indexOf(']') + 1);
        return super.containsKey(k);
    }

    @Override
    public boolean containsValue(Object value) {
        return super.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        int count = hp.getOrDefault(key.toString(), -1);
        if (count == -1) return super.get(key);
        else {
            if (--count <= 0) return this.remove(key);
            else {
                hp.replace(key.toString(), count);
                return super.get(key);
            }
        }
    }

    private String getKey(String key) {
        if (key.startsWith("[")) {
            int s = key.indexOf(']');
            int hp = getInteger(key.substring(1, s));
            this.hp.put(key, hp);
            return key.substring(s + 1);
        } else return key;
    }

    private Object getValue(String key, Object value) {
        if (key.matches(VARIABLE)) {
            return cast(type, value);
        } else {
            this.hp.remove(key);
            throw new VariableException().cannotInclude();
        }
    }

    @Override
    public Object put(String key, Object value) {
        key = getKey(key);
        return super.put(key, getValue(key, value));
    }

    @Override
    public boolean replace(String key, Object oldValue, Object newValue) {
        key = getKey(key);
        return super.replace(key, oldValue, getValue(key, newValue));
    }

    @Override
    public Object replace(String key, Object value) {
        key = getKey(key);
        return super.replace(key, getValue(key, value));
    }

    @Override
    public Object remove(Object key) {
        hp.remove(key);
        return super.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        hp.clear();
        super.clear();
    }

    @Override
    public Set<String> keySet() {
        return super.keySet();
    }

    @Override
    public Collection<Object> values() {
        return super.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return super.entrySet();
    }
}
