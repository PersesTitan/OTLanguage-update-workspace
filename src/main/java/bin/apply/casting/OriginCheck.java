package bin.apply.casting;

import static bin.token.Token.FALSE;
import static bin.token.Token.TRUE;

public interface OriginCheck {
    // 기본 변수
    static boolean isBoolean(Object value) {
        if (value instanceof Boolean) return true;
        else {
            final String v = value.toString().strip();
            if (v.isEmpty()) return false;
            else return v.equals(TRUE) || v.equals(FALSE);
        }
    }

    static boolean isCharacter(Object value) {
        if (value instanceof Character) return true;
        else {
            try {return value.toString().length() == 1 || isInteger(value);}
            catch (Exception e) {return false;}
        }
    }

    static boolean isInteger(Object value) {
        if (value instanceof Integer) return true;
        else {
            try {Integer.parseInt(value.toString());return true;}
            catch (Exception e) {return false;}
        }
    }

    static boolean isDouble(Object value) {
        if (value instanceof Double) return true;
        else {
            try {Double.parseDouble(value.toString());return true;}
            catch (Exception e) {return false;}
        }
    }

    static boolean isFloat(Object value) {
        if (value instanceof Float) return true;
        else {
            try {Float.parseFloat(value.toString());return true;}
            catch (Exception e) {return false;}
        }
    }

    static boolean isLong(Object value) {
        if (value instanceof Long) return true;
        else {
            try {Long.parseLong(value.toString());return true;}
            catch (Exception e) {return false;}
        }
    }

    static boolean isString(Object value) {
        return value instanceof String;
    }
}
