package work;

import bin.Repository;
import bin.exception.MatchException;
import bin.exception.VariableException;
import bin.token.EditToken;
import bin.token.KlassToken;
import lombok.Getter;

@Getter
public abstract class CreateWork<T> implements WorkTool {
    private final Class<T> klass;
    private final String klassName;
    private final String[] params;

    public CreateWork(Class<T> klass, String klassName, String...params) {
        this.klass = klass;
        this.klassName = klassName;
        this.params = params;
        if (KlassToken.BASIC_KLASS.contains(klassName) && params.length == 1 && params[0].equals(klassName)) return;
        // 이미 정의된 타입일때
        if (Repository.isKlass(klassName))
            throw VariableException.DEFINE_TYPE.getThrow(klassName);
        // 존재하지 않는 타입일때 (자기 자신 클래스 허용)
        for (String param : params) {
            // 정의된 클래스이거나 기본 클래스일때 자신의 클래스는 허용
            if (!Repository.isKlass(param)) throw VariableException.NO_DEFINE_TYPE.getThrow(param);
        }
        reset();
    }

    protected abstract Object createItem(Object... params);

    public boolean check(Object value) {
        return this.klass.equals(value.getClass());
    }

    public Object parser(String params) {
        // 기본 클래스일때
        if (KlassToken.BASIC_KLASS.contains(this.klassName)) return createItem(params);
        else {
            int len = this.params.length;
            String[] param = EditToken.cutParams(len, params);
            Object[] values = new Object[len];
            for (int i = 0; i<len; i++) values[i] = Repository.createWorks.get(this.params[i]).parser(param[i]);
            return createItem(values);
        }
    }

    public Object create(Object... params) {
        int len = params.length;
        // 파라미터 갯수 확인
        if (len != this.params.length)
            throw MatchException.PARAM_COUNT_ERROR.getThrow(len + "!=" + this.params.length);
        // 타입 체크
        if (len == 1 && check(params[0])) return params[0];
        if (KlassToken.BASIC_KLASS.contains(klassName)) return createItem(params);
        for (int i = 0; i<len; i++) {
            Object value = params[i];
            if (!Repository.createWorks.get(this.params[i]).check(value))
                throw VariableException.DEFINE_TYPE.getThrow(value.toString());
        }
        return createItem(params);
    }

    public int getParamsLen() {
        return this.params.length;
    }
}
