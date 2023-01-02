package work.item;

import bin.apply.type.VariableType;

public record TypeDTO(VariableType varType, String varName) {
    public String getKlassName() {
        return varType.getKlassName(varName);
    }

    public <V> V cast(Class<V> klass, Object value) {
        return klass.cast(value);
    }
}