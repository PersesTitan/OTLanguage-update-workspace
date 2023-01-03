package bin.apply.type_tool;

import static bin.token.Token.FALSE;
import static bin.token.Token.TRUE;

public interface VariableTypeCheck {
    static boolean isBoolean(String value) {
        return !value.isBlank() && (value.equals(TRUE) || value.equals(FALSE));
    }

    static boolean isInteger(String value) {
        try {Integer.parseInt(value);return true;
        } catch (Exception e) {return value.length() == 1;}
    }

    static boolean isCharacter(String value) {
        try {return value.length() == 1 || isInteger(value);}
        catch (Exception e) {return false;}
    }

    static boolean isDouble(String value) {
        try {Double.parseDouble(value);return true;
        } catch (Exception e) {return false;}
    }

    static boolean isFloat(String value) {
        try {Float.parseFloat(value);return true;}
        catch (Exception e) {return false;}
    }

    static boolean isLong(String value) {
        try {Long.parseLong(value);return true;}
        catch (Exception e) {return false;}
    }

    // Other Type
    static boolean isList(Object value) {
        String v = value.toString();
        return v.startsWith("[") && v.endsWith("]");
    }

    static boolean isMap(Object value) {
        String v = value.toString();
        return v.startsWith("{") && v.endsWith("}");
    }
}
