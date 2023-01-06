package bin.logic.file;

import bin.apply.item.map.AccessMap;
import bin.apply.type.VariableType;
import work.StartWork;
import work.item.TypeDTO;
import work.item.TypeListDTO;

import java.io.File;
import java.io.ObjectInputStream;

import static bin.apply.file.Information.SEPARATOR_FILE;
import static bin.token.Token.ACCESS;

public class CreateFile extends StartWork {
    // [String]
    public CreateFile(boolean isLoop, String type, TypeListDTO... types) {
        super(isLoop, type, types);
    }

    @Override
    public void start(String line, String loop,
                      Object[] params, TypeDTO[] types, AccessMap repositoryArray) {
        File value = new File(params[0].toString().replace(ACCESS, SEPARATOR_FILE));
    }

    @Override
    protected void serial(ObjectInputStream oi) {
        VariableType.add(getType());
    }
}