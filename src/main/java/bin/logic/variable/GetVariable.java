package bin.logic.variable;

import bin.apply.item.map.AccessMap;
import bin.apply.item.TypeValue;
import bin.apply.type.VariableType;
import bin.logic.variable.tool.CustomCollection;
import bin.logic.variable.tool.CustomList;
import bin.logic.variable.tool.CustomMap;
import bin.logic.variable.tool.CustomSet;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static bin.apply.type_tool.VariableValueGet.getInteger;
import static bin.token.Token.*;
import static bin.token.VariableToken.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetVariable {
    private static GetVariable getVariable;
    public static GetVariable getInstance() {
        if (getVariable == null) {
            synchronized (GetVariable.class) {
                getVariable = new GetVariable();
            }
        }
        return getVariable;
    }

    public Object get(String line, AccessMap repositoryArray) {
        String[] tokens = line.split(VARIABLE_CUT, 2);

        String variableName = tokens[0].strip();
        String option = tokens[1].stripLeading();

        TypeValue typeValue = repositoryArray.getTypeValueOrNull(variableName);
        if (typeValue == null) return null;
        VariableType variableType = VariableType.getVariable(typeValue.type());

        return switch (variableType.getBigType()) {
            case ORIGIN -> {
                if (option.equals("")) {
                    Object value = typeValue.value();
                    if (value instanceof Boolean v) yield v ? TRUE : FALSE;
                    else yield value;
                } else yield null;
            }
            case SET -> getValue((CustomSet<?>) typeValue.value(), option);
            case LIST -> getValue((CustomList<?>) typeValue.value(), option);
            case MAP -> {
                CustomMap<?, ?> map = (CustomMap<?, ?>) typeValue.value();
                if (option.equals("")) yield map;
                else if (option.equals(SET_SIZE)) yield map.size();
                else if (option.equals(SET_ISEMPTY)) yield map.isEmpty();
                else yield null;
            }
            case CLASS -> {
                if (option.equals("")) yield typeValue.value();
                else yield null;
            }
        };
    }

    private Object getValue(String value, String option) {
        return option.substring(value.length()).strip();
    }

    private Object getValue(CustomCollection list, String option) {
        if (option.equals("")) return list;
        else if (option.equals(SET_SIZE)) return list.size();
        else if (option.equals(SET_ISEMPTY)) return list.isEmpty();
        else if (option.startsWith(SET_CONTAINS)) return list.contains(getValue(SET_CONTAINS, option));
        else if (option.startsWith(SET_GET)) return list.get(getInteger(getValue(SET_GET, option)));
        else return null;
    }

}
