package bin.apply.make;

import bin.apply.Repository;
import bin.apply.repository.AccessMap;
import bin.exception.VariableException;
import lombok.Getter;

public record MakeKlass(String klassName, String[] paramsType) {
    public MakeKlass(String klassName, String[] paramsType) {
        this.paramsType = paramsType;
        this.klassName = klassName;
        // 유효한 클래스명인지 확인하는 로직
        for (String paramType : paramsType) {
            if (!Repository.variableTypes.containsKey(paramType))
                throw new VariableException().noDefineType();
        }
        // 클래스 타입 추가
        Repository.add(klassName, Klass.class);
    }

    private void create(String name, Object[] params, AccessMap accessMap) {
        // 파라미터 길이가 일치하는지 확인
        if (params.length != paramsType.length)
            throw new VariableException().klassParamsError();
        // 파라미터 타입이 일치하는지 확인
        for (int i = 0; i<paramsType.length; i++) {
            Class<?> klass = Repository.variableTypes.get(paramsType[i]);
            if (klass.equals(params[i].getClass())) {
                // 만약 커스텀 클래스 일때
                if (params[i] instanceof Klass k && !k.klassName().equals(paramsType[i])) {
                    throw new VariableException().typeMatch(params[i].toString());
                }
            } else throw new VariableException().typeMatch(params[i].toString());
        }
    }
}
