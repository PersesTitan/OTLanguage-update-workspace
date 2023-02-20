package bin.apply.variable.create;

import bin.apply.variable.custom.CustomList;
import bin.apply.variable.custom.CustomMap;
import bin.apply.variable.custom.CustomSet;
import bin.exception.MatchException;
import bin.exception.VariableException;
import bin.token.EditToken;
import bin.token.Token;
import work.CreateWork;

import java.util.StringTokenizer;

public interface CreateTool {
    static <T> CreateWork<CustomSet<T>> createSet(Types types, String type) {
        return new CreateWork<>((Class<CustomSet<T>>) new CustomSet<>(types).getClass(), type, type) {
            @Override
            protected Object createItem(Object... params) {
                if (params[0] instanceof CustomSet<?> set) {
                    if (set.getTypes().equals(types)) return set;
                    else throw VariableException.TYPE_ERROR.getThrow(params[0].toString());
                } else {
                    String value = params[0].toString();
                    if (value.startsWith(Token.SET_S) && value.endsWith(Token.SET_E)) {
                        StringTokenizer tokenizer = new StringTokenizer(EditToken.bothCut(value), ",");
                        CreateWork<?> createWork = types.getCreateWork();
                        CustomSet<T> set = new CustomSet<>(types);
                        while (tokenizer.hasMoreTokens()) set.add((T) createWork.parser(tokenizer.nextToken().strip()));
                        return set;
                    }
                    throw MatchException.GRAMMAR_ERROR.getThrow(value);
                }
            }

            @Override
            public boolean check(Object value) {
                return value instanceof CustomSet<?> set && set.getTypes().equals(types);
            }

            @Override
            protected void reset() {}
        };
    }

    static <T> CreateWork<CustomList<T>> createList(Types types, String type) {
        return new CreateWork<>((Class<CustomList<T>>) new CustomList<>(types).getClass(), type, type) {
            @Override
            protected Object createItem(Object... params) {
                if (params[0] instanceof CustomList<?> list) {
                    if (list.getTypes().equals(types)) return list;
                    else throw VariableException.TYPE_ERROR.getThrow(params[0].toString());
                } else {
                    String value = params[0].toString();
                    if (value.startsWith(Token.LIST_S) && value.endsWith(Token.LIST_E)) {
                        StringTokenizer tokenizer = new StringTokenizer(EditToken.bothCut(value), ",");
                        CreateWork<?> createWork = types.getCreateWork();
                        CustomList<T> list = new CustomList<>(types);
                        while (tokenizer.hasMoreTokens()) list.add((T) createWork.parser(tokenizer.nextToken().strip()));
                        return list;
                    }
                    throw MatchException.GRAMMAR_ERROR.getThrow(value);
                }
            }

            @Override
            public boolean check(Object value) {
                return value instanceof CustomList<?> list && list.getTypes().equals(types);
            }

            @Override
            protected void reset() {}
        };
    }

    static <K, V> CreateWork<CustomMap<K, V>> createMap(Types keyType, Types valueType, String type) {
        return new CreateWork<>((Class<CustomMap<K, V>>) new CustomMap<>(keyType, valueType).getClass(), type, type) {
            @Override
            protected Object createItem(Object... params) {
                if (params[0] instanceof CustomMap<?,?> map) {
                    if (map.getKeyKlass().equals(keyType) && map.getValueKlass().equals(valueType)) return map;
                    else throw VariableException.TYPE_ERROR.getThrow(params[0].toString());
                } else {
                    String value = params[0].toString();
                    if (value.startsWith(Token.MAP_S) && value.endsWith(Token.MAP_E)) {
                        StringTokenizer tokenizer = new StringTokenizer(EditToken.bothCut(value), ",");
                        CreateWork<?> createKey = keyType.getCreateWork();
                        CreateWork<?> createValue = valueType.getCreateWork();
                        CustomMap<K, V> map = new CustomMap<>(keyType, valueType);
                        while (tokenizer.hasMoreTokens()) {
                            String token = tokenizer.nextToken();
                            if (!token.contains("=")) throw MatchException.MAP_MATCH_ERROR.getThrow(token);
                            String[] kv = token.split("=", 2);
                            map.put((K) createKey.parser(kv[0].strip()), (V) createValue.parser(kv[1].strip()));
                        }
                        return map;
                    }
                    throw MatchException.GRAMMAR_ERROR.getThrow(value);
                }
            }

            @Override
            public boolean check(Object value) {
                return value instanceof CustomMap<?, ?> map
                        && map.getKeyKlass().equals(keyType)
                        && map.getValueKlass().equals(valueType);
            }

            @Override
            protected void reset() {}
        };
    }
}
