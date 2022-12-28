package bin.apply.item;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HpMap extends HashMap<String, Object> implements Map<String, Object> {
    private final Map<String, Integer> hp = new HashMap<>();

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
        return false;
    }

    @Override
    public Object get(Object key) {
        return super.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {

    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Object> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return null;
    }
}
