package bin.apply.casting;

import bin.apply.custom.CustomList;
import bin.apply.custom.CustomMap;
import bin.apply.custom.CustomSet;
import bin.exception.MatchException;
import bin.exception.VariableException;
import bin.token.EditToken;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

import static bin.token.Token.COMMA;

public interface OtherCasting {
    static boolean listCheck(String line) {
        return !line.startsWith("[") || !line.endsWith("]");
    }

    static boolean mapCheck(String line) {
        return line.startsWith("{") && line.endsWith("}");
    }

    // SET
    static <V> CustomSet<V> getCasting(CustomSet<V> set, String value) {
        final TypeCast typeCasting = set.getTypeCasting();
        final Class<V> klass = set.getKlass();
        if (listCheck(value)) {
            // 값이 1개일때 추가하기
            if (typeCasting.isCheck(value)) set.add(typeCasting.casting(klass, value));
            else throw new VariableException().typeMatch();
        } else set.addAll(castingSet(klass, typeCasting, value));
        return set;
    }

    private static <V> LinkedHashSet<V> castingSet(Class<V> klass, TypeCast typeCasting, String value) {
        LinkedHashSet<V> set = new LinkedHashSet<>();
        // [...] => ...
        StringTokenizer tokenizer = new StringTokenizer(EditToken.bothCut(value), COMMA);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().strip();
            set.add(typeCasting.casting(klass, token));
        }
        return set;
    }

    // LIST
    static <V> CustomList<V> getCasting(CustomList<V> list, String value) {
        final TypeCast typeCasting = list.getTypeCasting();
        final Class<V> klass = list.getKlass();
        if (listCheck(value)) {
            // 값이 1개일때 추가하기
            if (typeCasting.isCheck(value)) list.add(typeCasting.casting(klass, value));
            else throw new VariableException().typeMatch();
        } else list.addAll(castingList(klass, typeCasting, value));
        return list;
    }

    private static <V> LinkedList<V> castingList(Class<V> klass, TypeCast typeCasting, String value) {
        LinkedList<V> list = new LinkedList<>();
        // [...] => ...
        StringTokenizer tokenizer = new StringTokenizer(EditToken.bothCut(value), COMMA);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().strip();
            list.add(typeCasting.casting(klass, token));
        }
        return list;
    }

    // MAP
    static <K, V> CustomMap<K, V> getCasting(CustomMap<K, V> map, String value) {
        final TypeCast keyType = map.getKeyType();
        final TypeCast valueType = map.getValueType();
        final Class<K> keyClass = map.getKeyClass();
        final Class<V> valueClass = map.getValueClass();
        if (mapCheck(value)) {
            map.putAll(castingMap(keyClass, valueClass, keyType, valueType, value));
        } else throw new MatchException().grammarError();
        return map;
    }

    private static <K, V> LinkedHashMap<K, V> castingMap(Class<K> keyClass, Class<V> valueClass,
                                                         TypeCast keyType, TypeCast valueType, String line) {
        LinkedHashMap<K, V> map = new LinkedHashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(EditToken.bothCut(line), COMMA);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().strip();
            if (token.contains("=")) {
                String[] ts = token.split("=", 2);
                String key = ts[0].strip(), value = ts[1].strip();
                K k = keyType.casting(keyClass, key);
                V v = valueType.casting(valueClass, value);
                map.put(k, v);
            } else throw new MatchException().grammarError();
        }
        return map;
    }
}
