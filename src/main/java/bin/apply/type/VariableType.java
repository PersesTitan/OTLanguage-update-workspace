package bin.apply.type;

import bin.apply.Repository;
import bin.apply.type_tool.Casting;
import bin.apply.type_tool.VariableValueGet;
import bin.exception.VariableException;
import bin.token.VariableToken;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Function;

@Getter
public enum VariableType implements VariableToken {
    Integer(BigType.ORIGIN, OriginType.INT, INT_VARIABLE),
    Long(BigType.ORIGIN, OriginType.LONG, LONG_VARIABLE),
    Boolean(BigType.ORIGIN, OriginType.BOOL, BOOL_VARIABLE),
    String(BigType.ORIGIN, OriginType.STRING, STRING_VARIABLE),
    Character(BigType.ORIGIN, OriginType.CHARACTER, CHARACTER_VARIABLE),
    Float(BigType.ORIGIN, OriginType.FLOAT, FLOAT_VARIABLE),
    Double(BigType.ORIGIN, OriginType.DOUBLE, DOUBLE_VARIABLE),

    SetInteger(BigType.SET, OriginType.INT, SET_INTEGER),
    SetLong(BigType.SET, OriginType.LONG, SET_LONG),
    SetBool(BigType.SET, OriginType.BOOL, SET_BOOLEAN),
    SetString(BigType.SET, OriginType.STRING, SET_STRING),
    SetCharacter(BigType.SET, OriginType.CHARACTER, SET_CHARACTER),
    SetFloat(BigType.SET, OriginType.FLOAT, SET_FLOAT),
    SetDouble(BigType.SET, OriginType.DOUBLE, SET_DOUBLE),

    ListInteger(BigType.LIST, OriginType.INT, LIST_INTEGER),
    ListLong(BigType.LIST, OriginType.LONG, LIST_LONG),
    ListBool(BigType.LIST, OriginType.BOOL, LIST_BOOLEAN),
    ListString(BigType.LIST, OriginType.STRING, LIST_STRING),
    ListCharacter(BigType.LIST, OriginType.CHARACTER, LIST_CHARACTER),
    ListFloat(BigType.LIST, OriginType.FLOAT, LIST_FLOAT),
    ListDouble(BigType.LIST, OriginType.DOUBLE, LIST_DOUBLE),

    MapInteger(BigType.MAP, OriginType.INT, MAP_INTEGER),
    MapLong(BigType.MAP, OriginType.LONG, MAP_LONG),
    MapBool(BigType.MAP, OriginType.BOOL, MAP_BOOLEAN),
    MapString(BigType.MAP, OriginType.STRING, MAP_STRING),
    MapCharacter(BigType.MAP, OriginType.CHARACTER, MAP_CHARACTER),
    MapFloat(BigType.MAP, OriginType.FLOAT, MAP_FLOAT),
    MapDouble(BigType.MAP, OriginType.DOUBLE, MAP_DOUBLE),

    Class(BigType.CLASS, null, null);

    private final OriginType originType;
    private final BigType bigType;

    @Getter(value = AccessLevel.PRIVATE)
    private final String type;

    VariableType(BigType bigType, OriginType originType, String type) {
        this.originType = originType;
        this.bigType = bigType;
        this.type = type;
        if (this.isClass() || type == null) return;
        VariableType.add(type);
        // 동작 세팅
        Function<Object, Object> work = originType.getWork();
        if (isOrigin()) Casting.castItems.put(type, work);
        else if (isSet()) Casting.castItems.put(type, v -> VariableValueGet.getSet(work, v, originType));
        else if (isList()) Casting.castItems.put(type, v -> VariableValueGet.getList(work, v, originType));
        else if (isMap()) Casting.castItems.put(type, v -> VariableValueGet.getMap(work, v, originType));
    }

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

    public boolean isClass() {
        return getBigType().equals(BigType.CLASS);
    }

    // 클래스 값 가져오기
    public String getKlassName(String klass) {
        return this.equals(Class) ? klass : this.getType();
    }

    public String getKlassName() {
        return this.getType();
    }

    // 타입 추가 하는 로직
    public static void add(String type) {
        if (Repository.types.contains(type))
            throw new VariableException().defineType(type);
        else Repository.types.add(type);
    }

    // 혀용하는 변수 타입인지 확인하는 로직
    public static boolean isValidType(String type) {
        return Repository.types.contains(type);
    }

    public static void isValidTypeError(String type) {
        if (!isValidType(type)) throw new VariableException().noDefineType();
    }

    public static VariableType getVariable(String type) {
        for (VariableType variableType : values()) {
            if (Objects.equals(variableType.getType(), type)) return variableType;
        }
        return VariableType.Class;
    }

    public static BigType getBigType(Object value) {
        if (value instanceof LinkedHashSet<?>) return BigType.SET;
        else if (value instanceof LinkedList<?>) return BigType.LIST;
        else if (value instanceof LinkedHashMap<?, ?>) return BigType.MAP;
        else if (value instanceof Integer || value instanceof Long
                || value instanceof String || value instanceof Character
                || value instanceof Float || value instanceof Double) return BigType.ORIGIN;
        else return BigType.CLASS;
    }
}
