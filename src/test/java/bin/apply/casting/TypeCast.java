package bin.apply.casting;

import bin.exception.VariableException;
import bin.orign.CreateOrigin;
import bin.token.Token;
import bin.apply.Repository;
import lombok.Getter;

import java.util.Objects;
import java.util.function.Function;

@Getter
public enum TypeCast {
    INTEGER(INT_VARIABLE, Integer.class, OriginCasting::getInteger, OriginCheck::isInteger),
    LONG(LONG_VARIABLE, Long.class, OriginCasting::getLong, OriginCheck::isLong),
    BOOLEAN(BOOL_VARIABLE, Boolean.class, OriginCasting::getBoolean, OriginCheck::isBoolean),
    CHARACTER(CHARACTER_VARIABLE, Character.class, OriginCasting::getChar, OriginCheck::isCharacter),
    FLOAT(FLOAT_VARIABLE, Float.class, OriginCasting::getFloat, OriginCheck::isFloat),
    DOUBLE(DOUBLE_VARIABLE, Double.class, OriginCasting::getDouble, OriginCheck::isDouble),
    STRING(STRING_VARIABLE, String.class, Object::toString, OriginCheck::isString);

    private final String type;
    private final Class<?> klass;
    private final Function<Object, Object> casting;
    private final Function<Object, Boolean> isCheck;

    TypeCast(String type, Class<?> klass, Function<Object, Object> casting, Function<Object, Boolean> isCheck) {
        Repository.variableTypes.put(type, klass);
        this.type = type;
        this.klass = klass;
        this.casting = casting;
        this.isCheck = isCheck;

        Repository.createWorks.put(type, new CreateOrigin<>(this, klass));
    }

    public static boolean isInType(Object o) {
        for (TypeCast typeCast : TypeCast.values()) {
            if (typeCast.getKlass().equals(o.getClass())) return true;
        }
        return false;
    }

    public static TypeCast getType(String type) {
        for (TypeCast typeValue : TypeCast.values()) {
            if (Objects.equals(type, typeValue.type)) return typeValue;
        }
        throw new VariableException().noDefineOrigin();
    }

    public static <V> TypeCast getType(Class<V> klass) {
        for (TypeCast typeValue : TypeCast.values()) {
            if (typeValue.getKlass().equals(klass)) return typeValue;
        }
        throw new VariableException().noDefineOrigin();
    }

    public static String toString(Object o) {
        if (o instanceof Boolean b) return b ? Token.TRUE : Token.FALSE;
        else return o.toString();
    }

    public boolean isBoolean() {
        return this.equals(TypeCast.BOOLEAN);
    }

    public <V> V casting(Class<V> klass, Object value) {
        return klass.cast(casting.apply(value));
    }

    public Object casting(Object value) {
        return casting.apply(value);
    }

    public boolean isCheck(Object o) {
        return isCheck.apply(o);
    }
}
