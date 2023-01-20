package bin.apply.custom;

import bin.apply.casting.TypeCast;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.LinkedList;

import static bin.token.Token.FALSE;
import static bin.token.Token.TRUE;

@Getter
@RequiredArgsConstructor
public class CustomList<V> extends LinkedList<V> {
    private final TypeCast typeCasting;
    private final Class<V> klass;

    @Override
    public String toString() {
        if (typeCasting.isBoolean()) {
            Iterator<V> it = iterator();
            if (! it.hasNext()) return "[]";
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (;;) {
                sb.append((boolean) it.next() ? TRUE : FALSE);
                if (! it.hasNext()) return sb.append(']').toString();
                sb.append(',').append(' ');
            }
        } else return super.toString();
    }
}
