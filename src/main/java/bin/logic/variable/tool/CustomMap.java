package bin.logic.variable.tool;

import bin.apply.type.OriginType;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static bin.token.Token.FALSE;
import static bin.token.Token.TRUE;

public class CustomMap<K, V> extends LinkedHashMap<K, V> {
    private final OriginType types;
    public CustomMap(OriginType types) {
        super();
        this.types = types;
    }

    @Override
    public V put(K key, V value) {
        return super.put(key, cast(value));
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    private V cast(V v) {
        return (V) types.getWork().apply(v);
    }

    @Override
    public String toString() {
        if (types.equals(OriginType.BOOL)) {
            Iterator<Map.Entry<K,V>> i = entrySet().iterator();
            if (! i.hasNext()) return "{}";

            StringBuilder sb = new StringBuilder('{');
            for (;;) {
                Map.Entry<K,V> e = i.next();
                Object key = e.getKey();
                Object value = e.getValue();
                sb.append(key == this ? "(this Map)" : (key instanceof Boolean v ? boolToString(v) : key));
                sb.append('=');
                sb.append(value == this ? "(this Map)" : (value instanceof Boolean v ? boolToString(v) : value));
                if (!i.hasNext()) return sb.append('}').toString();
                sb.append(',').append(' ');
            }
        }
        return super.toString();
    }

    private String boolToString(boolean bool) {
        return bool ? TRUE : FALSE;
    }
}