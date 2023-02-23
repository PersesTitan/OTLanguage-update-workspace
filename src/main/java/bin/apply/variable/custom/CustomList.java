package bin.apply.variable.custom;

import bin.apply.variable.create.Types;
import bin.token.Token;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.LinkedList;

import static bin.token.Token.FALSE;
import static bin.token.Token.TRUE;

@Getter
@RequiredArgsConstructor
public class CustomList<V> extends LinkedList<V> {
    private final Types types;
//    private final Class<V> klass;

    @Override
    public String toString() {
        Iterator<V> it = iterator();
        if (! it.hasNext()) return Token.LIST_S.concat(Token.LIST_E);

        StringBuilder sb = new StringBuilder(Token.LIST_S);
        for (;;) {
            V e = it.next();
            if (e instanceof Boolean bool) sb.append(bool ? TRUE : FALSE);
            else sb.append(e);
            if (! it.hasNext()) return sb.append(Token.LIST_E).toString();
            sb.append(',').append(' ');
        }
    }
}
