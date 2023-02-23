package bin.apply.variable.custom;

import bin.apply.variable.create.Types;
import bin.token.Token;
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
    private final Types keyKlass;
    private final Types valueKlass;
//    private final Class<K> keyKlass;
//    private final Class<V> valueKlass;

    @Override
    public String toString() {
        Iterator<Map.Entry<K,V>> i = entrySet().iterator();
        if (! i.hasNext()) return Token.MAP_S.concat(Token.MAP_E);

        StringBuilder sb = new StringBuilder(Token.MAP_S);
        for (;;) {
            Map.Entry<K,V> e = i.next();
            K key = e.getKey();
            V value = e.getValue();
            if (key instanceof Boolean bool) sb.append(bool ? TRUE : FALSE);
            else sb.append(key);
            sb.append('=');
            if (value instanceof Boolean bool) sb.append(bool ? TRUE : FALSE);
            else sb.append(value);
            if (! i.hasNext()) return sb.append(Token.MAP_E).toString();
            sb.append(',').append(' ');
        }
    }
}
