package bin.apply.casting;

import bin.exception.VariableException;

import static bin.token.Token.FALSE;
import static bin.token.Token.TRUE;

// 기본 변수 String => Object
public interface OriginCasting {
    static int getInteger(String value) {
        try {return Integer.parseInt(value);
        } catch (Exception e) {
            if (value.length() == 1) return value.charAt(0);
            else throw new VariableException().typeMatch();
        }
    }

    static long getLong(String value) {
        try {return Long.parseLong(value);}
        catch (Exception e) {throw new VariableException().typeMatch();}
    }

    static boolean getBoolean(String value) {
        if (value.equals(FALSE) || value.equals(TRUE)) return value.equals(TRUE);
        else throw new VariableException().typeMatch();
    }

    static char getChar(String value) {
        if (value.length() == 1) return value.charAt(0);
        else {
            try {return (char) Integer.parseInt(value);
            } catch (Exception e) {throw new VariableException().typeMatch();}
        }
    }

    static float getFloat(String value) {
        try {return Float.parseFloat(value);}
        catch (Exception e) {throw new VariableException().typeMatch();}
    }

    static double getDouble(String value) {
        try {return Double.parseDouble(value);
        } catch (Exception e) {throw new VariableException().typeMatch();}
    }

    // get Object
    static int getInteger(Object value) {
        return value instanceof Integer i ? i : getInteger(value.toString());
    }

    static long getLong(Object value) {
        return value instanceof Long l ? l : getLong(value.toString());
    }

    static boolean getBoolean(Object value) {
        return value instanceof Boolean b ? b : getBoolean(value.toString());
    }

    static char getChar(Object value) {
        return value instanceof Character c ? c : getChar(value.toString());
    }

    static float getFloat(Object value) {
        return value instanceof Float f ? f : getFloat(value.toString());
    }

    static double getDouble(Object value) {
        return value instanceof Double d ? d : getDouble(value.toString());
    }
}
