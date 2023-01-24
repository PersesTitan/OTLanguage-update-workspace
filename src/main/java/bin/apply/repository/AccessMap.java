package bin.apply.repository;

import bin.exception.VariableException;
import bin.token.Token;

import java.util.LinkedList;

public class AccessMap extends LinkedList<VariableInformationMap> {
    public void create(String type, String variableName, Object value) {
        super.get(0).create(type, variableName, value);
    }

    public void update(String variableName, Object value) {
        int access = accessCount(variableName);
        variableName = variableName.substring(access);
        if (super.size() > access) {
            super.get(access).update(variableName, value);
        } else throw new VariableException().localNoVariable();
    }

    public Object get(String variableName) {
        int access = accessCount(variableName);
        variableName = variableName.substring(access);
        if (super.size() > access) {
            return super.get(access).get(variableName);
        } else throw new VariableException().localNoVariable();
    }

    private int accessCount(String line) {
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == Token.ACCESS.charAt(0)) count++;
            else break;
        }
        return count;
    }
}
