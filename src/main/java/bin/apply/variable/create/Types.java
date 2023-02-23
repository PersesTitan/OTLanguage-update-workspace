package bin.apply.variable.create;

import bin.Repository;
import bin.apply.variable.create.origin.*;
import bin.token.KlassToken;
import bin.token.Token;
import lombok.Getter;
import work.CreateWork;

@Getter
public enum Types {
    INTEGER (KlassToken.INT_VARIABLE, KlassToken.SET_INTEGER,
            KlassToken.LIST_INTEGER, KlassToken.MAP_INTEGER),
    LONG    (KlassToken.LONG_VARIABLE, KlassToken.SET_LONG,
            KlassToken.LIST_LONG, KlassToken.MAP_LONG),
    BOOLEAN (KlassToken.BOOL_VARIABLE, KlassToken.SET_BOOLEAN,
            KlassToken.LIST_BOOLEAN, KlassToken.MAP_BOOLEAN),
    STRING  (KlassToken.STRING_VARIABLE, KlassToken.SET_STRING,
            KlassToken.LIST_STRING, KlassToken.MAP_STRING),
    FLOAT   (KlassToken.FLOAT_VARIABLE, KlassToken.SET_FLOAT,
            KlassToken.LIST_FLOAT, KlassToken.MAP_FLOAT),
    DOUBLE  (KlassToken.DOUBLE_VARIABLE, KlassToken.SET_DOUBLE,
            KlassToken.LIST_DOUBLE, KlassToken.MAP_DOUBLE);

    private final String type;
    private final String set;
    private final String list;
    private final String map;

    Types(String type, String set, String list, String map) {
        this.type = type;
        this.set = set;
        this.list = list;
        this.map = map;
    }

    public CreateWork<?> getCreateWork() {
        return Repository.createWorks.get(type);
    }

    public static String toString(Object value) {
        return (value instanceof Boolean b)
                ? (b ? Token.TRUE : Token.FALSE)
                : value.toString();
    }
}
