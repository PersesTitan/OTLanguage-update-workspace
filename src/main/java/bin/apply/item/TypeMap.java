package bin.apply.item;

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
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public HpMap get(Object key) {
        return null;
    }

    @Override
    public HpMap put(String key, HpMap value) {
        return null;
    }

    /**
     * @param type VariableType
     * @param name VariableName
     * @param value VariableType
     */
    public void put(String type, String name, Object value) {
        if (super.containsKey(type)) {
            HpMap map = super.get(type);
            if (super.get(type).containsKey(name)) {
                super.get(type).put()
            }
        }
    }

    @Override
    public HpMap remove(Object key) {
        return super.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends HpMap> m) {

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
