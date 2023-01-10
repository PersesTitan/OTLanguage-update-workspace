package bin.apply.type;

import bin.apply.type_tool.VariableValueGet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum OriginType {
    INT(VariableValueGet::getInteger),
    LONG(VariableValueGet::getLong),
    BOOL(VariableValueGet::getBoolean),
    STRING(VariableValueGet::getString),
    CHARACTER(VariableValueGet::getCharacter),
    FLOAT(VariableValueGet::getFloat),
    DOUBLE(VariableValueGet::getDouble);

    private final Function<Object, Object> work;
}
