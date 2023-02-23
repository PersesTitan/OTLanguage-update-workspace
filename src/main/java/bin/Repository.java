package bin;

import bin.apply.variable.create.CreateTool;
import bin.apply.variable.create.Types;
import bin.apply.variable.create.origin.*;
import bin.repository.AccessList;
import bin.repository.WorkMap;
import bin.token.KlassToken;
import bin.work.sys.ForceQuit;
import bin.work.sys.Print;
import bin.work.sys.Println;
import work.CreateWork;
import work.ReplaceWork;
import work.StartWork;

import java.util.HashMap;
import java.util.Map;

public interface Repository {
    AccessList repositoryArrays = new AccessList();

    // 코드를 저장해두는 공간
    Map<String, Map<Integer, String>> codes = new HashMap<>();

    Map<String, CreateWork<?>> createWorks = new HashMap<>() {{
        put(KlassToken.INT_VARIABLE, new CreateInteger());
        put(KlassToken.BOOL_VARIABLE, new CreateBoolean());
        put(KlassToken.CHARACTER_VARIABLE, new CreateCharacter());
        put(KlassToken.DOUBLE_VARIABLE, new CreateDouble());
        put(KlassToken.FLOAT_VARIABLE, new CreateFloat());
        put(KlassToken.LONG_VARIABLE, new CreateLong());
        put(KlassToken.STRING_VARIABLE, new CreateString());
        for (Types types : Types.values()) {
            String set = types.getSet(), list = types.getList(), map = types.getMap();
            put(set, CreateTool.createSet(types, set));
            put(list, CreateTool.createList(types, list));
            put(map, CreateTool.createMap(Types.STRING, types, map));
        }
    }};

    WorkMap<ReplaceWork> replaceWorks = new WorkMap<>();
    WorkMap<StartWork> startWorks = new WorkMap<>() {{
        put(KlassToken.SYSTEM, KlassToken.PRINT, new Print());
        put(KlassToken.SYSTEM, KlassToken.PRINTLN, new Println());
        put(KlassToken.SYSTEM, KlassToken.QUIT, new ForceQuit());
    }};

    static boolean isKlass(String klassType) {
        return Repository.createWorks.containsKey(klassType);
    }
}
