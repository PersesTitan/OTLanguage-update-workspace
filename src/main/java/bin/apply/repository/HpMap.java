package bin.apply.repository;

import bin.apply.casting.OriginCasting;
import bin.exception.VariableException;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static bin.apply.Repository.noUse;
import static bin.token.Token.*;

public class HpMap extends HashMap<String, Object> {
    private final Map<String, Integer> hp = new HashMap<>();
    @Getter
    private final Class<?> klass;

    public HpMap(Class<?> klass) {
        this.klass = klass;
    }

    @Override
    public boolean containsKey(Object key) {
        // [10]변수명 일때 쪼개기
        String k = key.toString();
        if (k.startsWith("[")) k = k.substring(k.indexOf("]") + 1);
        return super.containsKey(k);
    }

    @Override
    public Object get(Object key) {
        Object object = super.get(key);
        int hpCount = hp.getOrDefault(key.toString(), -1);
        if (hpCount != -1) {
            if (--hpCount == 0) remove(key);
            else hp.put(key.toString(), hpCount);
        }
        return object;
    }

    @Override
    public Object put(String key, Object value) {
        // 타입 확인
        if (!klass.equals(value.getClass())) throw new VariableException().typeMatch();
        if (!key.matches(VARIABLE_NAME)) throw new VariableException().variableNameMatch();
        // [1]변수명
        if (key.startsWith("[")) {
            int right = key.indexOf("]");
            int hpValue = OriginCasting.getInteger(key.substring(1, right));
            // 값이 0이하이라면 값을 넣지 않음
            if (hpValue <= 0) return null;
            hp.put(key, hpValue);
            key = key.substring(right + 1);
        }

        // 예약어 확인
        if (noUse.contains(key)) throw new VariableException().reservedWorks();
        // 사용 불가 단어
        if (key.contains(OR) || key.contains(AND) || key.contains(NOT) || key.contains(TRUE) || key.contains(FALSE))
            throw new VariableException().cannotInclude();
        else return super.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        hp.remove(key);
        return super.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ?> m) {
        for (var ms : m.entrySet()) put(ms.getKey(), ms.getValue());
    }

    @Override
    public void clear() {
        hp.clear();
        super.clear();
    }

    @Override
    public String toString() {
        if (klass.equals(Boolean.class)) {
            Iterator<Entry<String, Object>> i = entrySet().iterator();
            if (! i.hasNext()) return "{}";

            StringBuilder sb = new StringBuilder();
            sb.append('{');
            for (;;) {
                Entry<String, Object> e = i.next();
                Object value = e.getValue();
                sb.append(e.getKey()).append('=');
                sb.append((boolean) value ? TRUE : FALSE);
                if (! i.hasNext()) return sb.append('}').toString();
                sb.append(',').append(' ');
            }
        } else return super.toString();
    }
}
