package bin.logic.console;

import bin.apply.Repository;
import bin.apply.item.map.AccessMap;
import bin.apply.type.VariableType;
import work.StartWork;
import work.item.TypeDTO;
import work.item.TypeListDTO;

import java.io.ObjectInputStream;

public class Println extends StartWork {
    // [String]
    public Println(String type, TypeListDTO... types) {
        super(false, type, types);
        VariableType.add(getType());
        Repository.addDefineWord(getType());
    }

    @Override
    public void start(String line, String loop,
                      Object[] params, TypeDTO[] types, AccessMap repositoryArray) {
        System.out.println(params[0]);
    }

    @Override
    protected void serial(ObjectInputStream oi) {
        VariableType.add(getType());
        Repository.addDefineWord(getType());
    }
}