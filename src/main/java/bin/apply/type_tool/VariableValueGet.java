package bin.apply.type_tool;

import bin.apply.type.OriginType;
import bin.exception.MatchException;
import bin.exception.VariableException;
import bin.logic.variable.tool.CustomList;
import bin.logic.variable.tool.CustomMap;
import bin.logic.variable.tool.CustomSet;
import bin.token.EditToken;

import java.util.StringTokenizer;
import java.util.function.Function;

import static bin.token.Token.FALSE;
import static bin.token.Token.TRUE;

public interface VariableValueGet {
    static boolean getBoolean(Object value) {
        if (value instanceof Boolean v) return v;
        if (value.equals(FALSE) || value.equals(TRUE)) return value.equals(TRUE);
        else throw new VariableException().typeMatch();
    }

    static double getDouble(Object value) {
        if (value instanceof Double v) return v;
        try {
            return Double.parseDouble(value.toString());
        } catch (Exception e) {
            throw new VariableException().typeMatch();
        }
    }

    static float getFloat(Object value) {
        if (value instanceof Float v) return v;
        try {
            return Float.parseFloat(value.toString());
        } catch (Exception e) {
            throw new VariableException().typeMatch();
        }
    }

    static long getLong(Object value) {
        if (value instanceof Long v) return v;
        try {
            return Long.parseLong(value.toString());
        } catch (Exception e) {
            throw new VariableException().typeMatch();
        }
    }

    static char getCharacter(Object value) {
        if (value instanceof Character v) return v;
        String v = value.toString();
        if (v.length() == 1) return v.charAt(0);
        else {
            try {return (char) Integer.parseInt(v);
            } catch (Exception e) {throw new VariableException().typeMatch();}
        }
    }

    static int getInteger(Object value) {
        if (value instanceof Integer v) return v;
        String v = value.toString();
        try {
            return Integer.parseInt(v);
        } catch (Exception e) {
            if (v.length() == 1) return v.charAt(0);
            else throw new VariableException().typeMatch();
        }
    }

    static String getString(Object value) {
        return value.toString();
    }

    /**
     * Get SET, LIST, MAP
     */
    static CustomSet<?> getSet(Function<Object, Object> work, Object value, OriginType originType) {
        if (value instanceof CustomSet<?> set) return set;
        else {
            if (VariableTypeCheck.isList(value)) {
                CustomSet<Object> set = new CustomSet<>(originType);
                String v = EditToken.bothCut(value.toString());
                StringTokenizer token = new StringTokenizer(v, ",");
                while (token.hasMoreTokens()) set.add(work.apply(token.nextToken().strip()));
                return set;
            } else throw new VariableException().typeMatch();
        }
    }

    static CustomList<?> getList(Function<Object, Object> work, Object value, OriginType originType) {
        if (value instanceof CustomList<?> list) return list;
        else {
            if (VariableTypeCheck.isList(value)) {
                CustomList<Object> list = new CustomList<>(originType);
                String v = EditToken.bothCut(value.toString());
                StringTokenizer token = new StringTokenizer(v, ",");
                while (token.hasMoreTokens()) list.add(work.apply(token.nextToken().strip()));
                return list;
            } else throw new VariableException().typeMatch();
        }
    }

    static CustomMap<?, ?> getMap(Function<Object, Object> work, Object value, OriginType originType) {
        if (value instanceof CustomMap<?, ?> list) return list;
        else {
            if (VariableTypeCheck.isMap(value)) {
                CustomMap<String, Object> map = new CustomMap<>(originType);
                String v = EditToken.bothCut(value.toString());
                StringTokenizer token = new StringTokenizer(v, ",");
                while (token.hasMoreTokens()) {
                    String val = token.nextToken(); // 키값 = 값
                    if (val.contains("=")) {
                        String[] kv = val.strip().split("=", 2);
                        map.put(kv[0], work.apply(kv[1]));
                    } else throw new MatchException().grammarError();
                }
                return map;
            } else throw new VariableException().typeMatch();
        }
    }
}
