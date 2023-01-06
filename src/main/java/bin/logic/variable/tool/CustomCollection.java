package bin.logic.variable.tool;

import bin.apply.type.OriginType;

import java.util.AbstractCollection;
import java.util.AbstractSequentialList;
import java.util.Iterator;

import static bin.token.Token.FALSE;
import static bin.token.Token.TRUE;

public interface CustomCollection {
    int size();
    boolean isEmpty();
    boolean contains(Object o);
    Object get(int index);
    default String toString(AbstractCollection<?> list, OriginType types) {
        if (types.equals(OriginType.BOOL)) {
            Iterator<?> it = list.iterator();
            if (!it.hasNext()) return "[]";
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (; ; ) {
                Object e = it.next();
                sb.append(e == this
                        ? "(this Collection)"
                        : (e instanceof Boolean v ? boolToString(v) : e));
                if (!it.hasNext()) return sb.append(']').toString();
                sb.append(',').append(' ');
            }
        } else return list.toString();
    }

    private String boolToString(boolean v) {
        return v ? TRUE : FALSE;
    }
}
