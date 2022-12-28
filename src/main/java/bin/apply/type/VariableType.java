package bin.apply.type;

import bin.token.VariableToken;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VariableType implements VariableToken {
    Boolean(BigType.ORIGIN, BOOL_VARIABLE),
    Character(BigType.ORIGIN, CHARACTER_VARIABLE),
    Double(BigType.ORIGIN, DOUBLE_VARIABLE),
    Float(BigType.ORIGIN, FLOAT_VARIABLE),
    Integer(BigType.ORIGIN, INT_VARIABLE),
    Long(BigType.ORIGIN, LONG_VARIABLE),
    String(BigType.ORIGIN, STRING_VARIABLE),

    SetInteger(BigType.SET, SET_INTEGER),
    SetLong(BigType.SET, SET_LONG),
    SetBool(BigType.SET, SET_BOOLEAN),
    SetString(BigType.SET, SET_STRING),
    SetCharacter(BigType.SET, SET_CHARACTER),
    SetFloat(BigType.SET, SET_FLOAT),
    SetDouble(BigType.SET, SET_DOUBLE),

    ListInteger(BigType.LIST, LIST_INTEGER),
    ListLong(BigType.LIST, LIST_LONG),
    ListBool(BigType.LIST, LIST_BOOLEAN),
    ListString(BigType.LIST, LIST_STRING),
    ListCharacter(BigType.LIST, LIST_CHARACTER),
    ListFloat(BigType.LIST, LIST_FLOAT),
    ListDouble(BigType.LIST, LIST_DOUBLE),

    MapInteger(BigType.MAP, MAP_INTEGER),
    MapLong(BigType.MAP, MAP_LONG),
    MapBool(BigType.MAP, MAP_BOOLEAN),
    MapString(BigType.MAP, MAP_STRING),
    MapCharacter(BigType.MAP, MAP_CHARACTER),
    MapFloat(BigType.MAP, MAP_FLOAT),
    MapDouble(BigType.MAP, MAP_DOUBLE);

    private final BigType bigType;
    private final String type;

    public boolean isOrigin() {
        return getBigType().equals(BigType.ORIGIN);
    }

    public boolean isSet() {
        return getBigType().equals(BigType.SET);
    }

    public boolean isList() {
        return getBigType().equals(BigType.LIST);
    }

    public boolean isMap() {
        return getBigType().equals(BigType.MAP);
    }
}
