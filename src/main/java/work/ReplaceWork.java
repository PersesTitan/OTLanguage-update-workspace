package work;

import bin.Repository;
import bin.apply.variable.Parser;
import bin.exception.VariableException;
import bin.token.EditToken;
import bin.token.KlassToken;

public abstract class ReplaceWork implements WorkTool {
    private final CreateWork<?> createWork;
    private final String klassType;
    private final String[] params;

    public ReplaceWork(String klassType, String... params) {
        this.params = params;
        this.klassType = klassType;
        if (!this.klassType.equals(KlassToken.SYSTEM)) {
            if (!Repository.isKlass(klassType)) throw VariableException.NO_DEFINE_TYPE.getThrow(klassType);
        }
        // 존재하지 않는 타입일때 (자기 자신 클래스 허용)
        for (String param : params) {
            // 정의된 클래스이거나 기본 클래스일때 자신의 클래스는 허용
            if (!Repository.isKlass(param)) throw VariableException.NO_DEFINE_TYPE.getThrow(param);
        }
        this.createWork = Repository.createWorks.get(klassType);
        reset();
    }

    protected abstract Object replaceItem(Object value, Object... params);

    public Object replace(Object value, String param) {
        if (!this.klassType.equals(KlassToken.SYSTEM)) {
            // 타입이 일치하는지 확인
            if (!createWork.check(value)) throw VariableException.VALUE_ERROR.getThrow(value.toString());
        }
        int len = this.params.length;
        // 파라미터 생성하는 작업
        String[] params = EditToken.cutParams(len, param);
        Object[] values = new Object[len];
        for (int i = 0; i<len; i++) values[i] = Parser.parser(this.params[i], params[i]);
        return replaceItem(value, values);
    }
}
