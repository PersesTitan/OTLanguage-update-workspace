package bin.logic.console;

import bin.apply.item.map.AccessMap;
import work.ReplaceWork;
import work.item.TypeDTO;
import work.item.TypeListDTO;

import java.io.ObjectInputStream;

public class Scanner extends ReplaceWork {
    public Scanner(String returnType, String type, TypeListDTO... types) {
        super(returnType, type, types);
    }

    @Override
    public Object start(String line, Object[] params, TypeDTO[] types, AccessMap repositoryArray) {
        return null;
    }

    @Override
    protected void serial(ObjectInputStream oi) {

    }
}