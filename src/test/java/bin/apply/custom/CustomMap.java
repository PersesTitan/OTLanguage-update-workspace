package bin.apply.custom;

import bin.apply.casting.TypeCast;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static bin.token.Token.FALSE;
import static bin.token.Token.TRUE;

@Getter
@RequiredArgsConstructor
public class CustomMap<K, V> extends LinkedHashMap<K, V> {
    private final TypeCast keyType;
    private final Class<K> keyClass;
    private final TypeCast valueType;
    private final Class<V> valueClass;

    @Override
    public String toString() {
        if (keyType.isBoolean() || valueType.isBoolean()) {
            Iterator<Map.Entry<K,V>> i = entrySet().iterator();
            if (! i.hasNext()) return "{}";

            StringBuilder sb = new StringBuilder();
            sb.append('{');
            for (;;) {
                Map.Entry<K,V> e = i.next();
                K key = e.getKey();
                V value = e.getValue();
                sb.append(key instanceof Boolean b ? (b ? TRUE : FALSE) : key);
                sb.append('=');
                sb.append(value instanceof Boolean b ? (b ? TRUE : FALSE) : value);
                if (! i.hasNext()) return sb.append('}').toString();
                sb.append(',').append(' ');
            }
        } else return super.toString();
    }
}
