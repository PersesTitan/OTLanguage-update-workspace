package bin.apply.item.map;

import bin.apply.item.TypeValue;
import bin.exception.VariableException;

import java.util.LinkedList;

import static bin.token.Token.ACCESS;

public class AccessMap extends LinkedList<TypeMap> {
    public AccessMap() {
        super();
        super.add(new TypeMap());
    }

    public Object get(String variable) {
        int count = accessCount(variable);
        if (count == -1) throw new VariableException().localNoVariable();
        variable = variable.substring(count);
        return this.get(count).getValue(variable);
    }

    public TypeValue getTypeValue(String key) {
        int count = accessCount(key);
        if (count == -1) throw new VariableException().localNoVariable();
        key = key.substring(count);
        return super.get(count).getTypeValue(key);
    }

    public TypeValue getTypeValueOrNull(String key) {
        int count = accessCount(key);
        if (count == -1) throw new VariableException().localNoVariable();
        key = key.substring(count);
        return super.get(count).getTypeValueOrNull(key);
    }

    public Object getOrNull(String variable) {
        int count = accessCount(variable);
        if (count == -1) throw new VariableException().localNoVariable();
        variable = variable.substring(count);
        return this.getOrNull(variable);
    }

    // value = ~~변수명
    private int accessCount(String value) {
        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == ACCESS.charAt(0)) count++;
            else break;
        }
        return count >= this.size() ? -1 : count;
    }
}