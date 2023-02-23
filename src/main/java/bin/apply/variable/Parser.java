package bin.apply.variable;

import bin.Repository;
import bin.apply.Replace;
import bin.apply.variable.create.Types;
import bin.exception.Error;
import bin.exception.MatchException;
import bin.exception.VariableException;
import bin.token.KlassToken;
import bin.token.Token;

public class Parser {
    public static String parser(String value) {
        if (Repository.repositoryArrays.find(value)) return Types.toString(Repository.repositoryArrays.get(value));
        else if (isMethod(value)) return Types.toString(Replace.replace(value));
        else throw MatchException.GRAMMAR_ERROR.getThrow(value);
    }

    public static Object parser(String type, String value) {
        // 문자열일때 그냥 값 넣기
        if (type.equals(KlassToken.STRING_VARIABLE)) return value;
        // 변수명인지 확인하는 로직
        else if (Repository.repositoryArrays.find(type, value)) return Repository.repositoryArrays.get(type, value);
        // 값이 메소드 형태일때
        else if (isMethod(value)) return Replace.replace(value);
        else return Repository.createWorks.get(type).parser(value);
    }

    public static double getNumber(String value) {
        try {
            if (Repository.repositoryArrays.find(value)) return (double) Repository.repositoryArrays.get(value);
            else if (isMethod(value)) return (double) Replace.replace(value);
            else {
                double d;
                try {d = (double) Parser.parser(KlassToken.INT_VARIABLE, value);}
                catch (Exception e) {
                    try {d = (double) Parser.parser(KlassToken.LONG_VARIABLE, value);}
                    catch (Exception e1) {
                        try {d = (double) Parser.parser(KlassToken.FLOAT_VARIABLE, value);}
                        catch (Exception e2) {
                            try {d = (double) Parser.parser(KlassToken.DOUBLE_VARIABLE, value);
                            } catch (Exception e3) {
                                throw VariableException.VALUE_ERROR.getThrow(value);
                            }
                        }
                    }
                }
                return d;
            }
        } catch (Error e) {
            throw e;
        } catch (Exception e) {
            throw VariableException.VALUE_ERROR.getThrow(value);
        }
    }

    public static int getInt(String value) {
        try {
            return (int) parser(KlassToken.INT_VARIABLE, value);
        } catch (Error e) {
            throw e;
        } catch (Exception e) {
            throw VariableException.VALUE_ERROR.getThrow(value);
        }
    }

    public static long getLong(String value) {
        try {
            return (long) parser(KlassToken.LONG_VARIABLE, value);
        } catch (Error e) {
            throw e;
        } catch (Exception e) {
            throw VariableException.VALUE_ERROR.getThrow(value);
        }
    }

    // ex) token : ㅇㅁㅇ~ㅁㅅㅁ[1][2]
    private static boolean isMethod(String token) {
        if (token.charAt(0) == Token.PARAM_S) return false;
        return token.contains(Token.ACCESS);
    }
}
