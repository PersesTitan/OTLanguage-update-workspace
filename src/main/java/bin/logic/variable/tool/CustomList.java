package bin.logic.variable.tool;

import bin.apply.type.OriginType;

import java.util.Collection;
import java.util.LinkedList;

public class CustomList<V> extends LinkedList<V> implements CustomCollection {
    private final OriginType types;
    public CustomList(OriginType types) {
        super();
        this.types = types;
    }

    @Override
    public boolean add(V v) {
        return super.add(cast(v));
    }

    @Override
    public boolean addAll(Collection<? extends V> c) {
        return super.addAll(c.stream().map(this::cast).toList());
    }

    private V cast(V v) {
        return (V) types.getWork().apply(v);
    }

    @Override
    public String toString() {
        return toString(this, types);
    }
}