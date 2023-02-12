package bin.apply.repository;

import bin.apply.Repository;
import bin.apply.token.Token;
import bin.apply.variable.casting.Casting;
import bin.exception.VariableException;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class HpMap extends HashMap<String, Object> {
    private final Map<String, Integer> hp = new HashMap<>();
    private final String klassType;

    public HpMap(String klassType) {
        this.klassType = klassType;
    }

    @Override
    public boolean containsKey(Object key) {
        String k = key.toString();
        if (k.charAt(0) == '[') k = k.substring(k.indexOf(']') + 1);
        return super.containsKey(k);
    }

    @Override
    public Object get(Object key) {
        String k = key.toString();
        if (this.hp.containsKey(k)) {
            int i = this.hp.get(k);
            if (--i == 0) this.remove(key);
            else hp.put(key.toString(), i);
        }
        return super.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        if (key.charAt(0) == '[') {
            int right = key.indexOf(']');
            if (right == -1) throw VariableException.VARIABLE_NAME_ERROR.getThrow(key);
            int hpValue = Casting.getInteger(key.substring(1, right));
            // 값이 0이하이라면 값을 넣지 않음
            if (hpValue <= 0) return null;
            hp.put(key, hpValue);
            key = key.substring(right + 1);
        }
        if (key.matches(Token.VARIABLE))
            throw VariableException.VARIABLE_NAME_ERROR.getThrow(key);
        if (Repository.createWork.containsKey(key))
            throw VariableException.VARIABLE_NAME_CLASS.getThrow(key);
        if (noUse(key)) throw VariableException.VARIABLE_NAME_NOT_USE.getThrow(key);

        if (Repository.createWork.get(klassType).check(value)) return super.put(key, value);
        else throw VariableException.TYPE_ERROR.getThrow(value.toString());
    }

    private boolean noUse(String key) {
        for (String token : Token.NO_USE) {
            if (key.contains(token)) return true;
        }
        return false;
    }



    @Override
    public Object remove(Object key) {
        this.hp.remove(key);
        return super.remove(key);
    }
}