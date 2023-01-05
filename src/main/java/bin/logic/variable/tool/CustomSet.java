package bin.logic.variable.tool;

import bin.apply.type.OriginType;

import java.util.Collection;
import java.util.LinkedHashSet;

public class CustomSet<V> extends LinkedHashSet<V> implements CustomCollection {
    private final OriginType types;
    public CustomSet(OriginType types) {
        super();
        this.types = types;
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(cast(o));
    }

    @Override
    public boolean add(V v) {
        return super.add(cast(v));
    }

    @Override
    public V get(int i) {
        return (V) super.toArray()[i];
    }

    @Override
    public boolean addAll(Collection<? extends V> c) {
        return super.addAll(c.stream().map(this::cast).toList());
    }

    private V cast(Object v) {
        return (V) types.getWork().apply(v);
    }

    @Override
    public String toString() {
        return toString(this, types);
    }
}
