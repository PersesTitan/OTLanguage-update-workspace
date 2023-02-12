package bin.apply.repository;

import bin.apply.token.Token;
import bin.exception.MatchException;

import java.util.HashMap;
import java.util.Map;

public class WorkMap<V> extends HashMap<String, Map<String, V>> {
    public void put(String klassName, String methodName, V work) {
        if (super.containsKey(klassName)) {
            Map<String, V> methodMap = super.get(klassName);
            if (methodMap.containsKey(methodName)) {
                String errorCode = klassName + Token.ACCESS + methodName;
                throw MatchException.DEFINE_METHOD_NAME.getThrow(errorCode);
            } else methodMap.put(methodName, work);
        } else super.put(klassName, new HashMap<>() {{put(methodName, work);}});
    }
}
