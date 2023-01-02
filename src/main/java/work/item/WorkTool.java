package work.item;

import bin.apply.type_tool.Casting;
import bin.exception.VariableException;

public interface WorkTool extends Casting {
    default Object[] getParams(TypeListDTO[] types, String[] params) {
        for (TypeListDTO typeList : types) {
            if (typeList.size() == params.length) {
                Object[] value = new Object[params.length];
                TypeDTO[] ts = typeList.types();
                for (int i = 0; i < params.length; i++)
                    value[i] = cast(ts[i], params[i]);
                return value;
            }
        }
        throw new VariableException().methodParamsCount();
    }

    default TypeDTO[] getTypes(TypeListDTO[] types, String[] params) {
        for (TypeListDTO typeList : types) {
            if (typeList.size() == params.length)
                return typeList.types();
        }
        throw new VariableException().methodParamsCount();
    }
}
