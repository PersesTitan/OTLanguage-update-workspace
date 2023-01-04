package work;

import bin.apply.item.map.AccessMap;
import lombok.Getter;
import work.item.TypeDTO;
import work.item.TypeListDTO;
import work.item.WorkTool;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;

@Getter
public abstract class StartWork implements Serializable, WorkTool {
    private final boolean isLoop;
    private final String type;
    private final TypeListDTO[] types;

    public StartWork(boolean isLoop, String type, TypeListDTO...types) {
        this.isLoop = isLoop;
        this.types = types;
        this.type = type;
    }

    public abstract void start(String line, String loop, Object[] params, TypeDTO[] types, AccessMap repositoryArray);
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
