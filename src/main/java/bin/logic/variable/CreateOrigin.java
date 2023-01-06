package bin.logic.variable;

import bin.apply.item.map.AccessMap;
import work.StartWork;
import work.item.TypeDTO;
import work.item.TypeListDTO;

import java.io.ObjectInputStream;

public class CreateOrigin extends StartWork {
    public CreateOrigin(String type, TypeListDTO... types) {
        super(false, type, types);
    }

    @Override
    public void start(String line, String loop,
                      Object[] params, TypeDTO[] types, AccessMap repositoryArray) {
        repositoryArray.getFirst().create(getType(), params[0].toString(), params[1]);
    }

    @Override
    protected void serial(ObjectInputStream oi) {

    }
}
