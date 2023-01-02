package work;

import bin.apply.item.AccessMap;
import bin.apply.type_tool.Casting;
import bin.exception.VariableException;
import lombok.Getter;
import work.item.TypeDTO;
import work.item.TypeListDTO;
import work.item.WorkTool;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;

@Getter
public abstract class ReplaceWork implements Serializable, WorkTool {
    private final String returnType;
    private final String type;
    private final TypeListDTO[] types;

    public ReplaceWork(String returnType, String type, TypeListDTO...types) {
        this.returnType = returnType;
        this.type = type;
        this.types = types;
    }

    public abstract Object start(String line, Object[] params, TypeDTO[] types, AccessMap repositoryArray);
    protected abstract void serial(ObjectInputStream oi);

    @Serial
    private void readObject(ObjectInputStream oi) {
        try {
            oi.defaultReadObject();
            serial(oi);
        } catch (IOException | ClassNotFoundException e) {
            // TODO : add read error
        }
    }

    public Object[] getParams(String[] params) {
        return getParams(types, params);
    }

    public TypeDTO[] getTypes(String[] params) {
        return getTypes(types, params);
    }
}
