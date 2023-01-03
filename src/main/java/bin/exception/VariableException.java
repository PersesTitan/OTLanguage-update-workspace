package bin.exception;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import static bin.token.Token.*;

public class VariableException extends RuntimeException implements ExceptionMessage {
    private final String typeMatch = "변수 값이 유효하지 않습니다.";
    private final String variableNameMatch = "변수명이 유효하지 않습니다.";
    private final String sameVariable = "이미 존재하는 변수명입니다.";
    private final String noDefineVariable = "정의되지 않은 변수명입니다.";
    private final String noDefineType = "정의되지 않은 변수 타입 입니다.";
    private final String defineType = "이미 정의된 변수 타입 입니다.";
    private final String localNoVariable = "해당 위치의 변수에 접근할 수 없습니다.";
    private final String reservedWorks = "예약된 단어를 사용할 수 없습니다.";
    private final String noGrammar = "문법이 올바르지 않습니다.";
    private final String forTypeMatchError = "^^문의 타입으로 유효하지 않습니다.";
    private final String methodParamsCount = "매개변수 갯수가 일치하지 않습니다.";
    private final String cannotInclude = "사용할 수 없는 문자가 포함되어 있습니다.";
    private final String methodParamsError = "메소드의 파리미터 길이가 유효하지 않습니다.";
    private final String noDefineMethod = "정의되지 않은 메소드명 입니다.";
    private final String methodTypeMatch = "메소드 타입이 일치하지 않습니다.";
    private final String definedMethodName = "이미 정의된 메소드 이름입니다.";

    public VariableException() {}

    public VariableException(String message) {
        super(message);
    }

    @Override
    public void errorMessage(RuntimeException e, String path, String line, long position) {
        String subMessage = switch (e.getMessage()) {
            case typeMatch -> "The variable value is invalid.\nPlease check the variable type.";
            case sameVariable -> "Variable name that already exists.\nReserved words or already used variable names cannot be reused.";
            case defineType -> defineTypeWord.get() == null
                    ? "Variable type that already exists.\nReserved words or already used variable types cannot be reused."
                    : String.format("Variable type that already exists. (%s)\nReserved words or already used variable types cannot be reused.", defineTypeWord.get());
            case localNoVariable -> "You cannot access the variable at that location.\nPlease check your current location.";
            case reservedWorks -> "Reserved words cannot be used.";
            case noDefineType -> "Undefined variable type.\nPlease check the variable type.";
            case noGrammar -> "The grammar does not match.\nPlease check the grammar.";
            case forTypeMatchError -> "^^ It is not valid as a return value of the query.\nPlease check the variable type";
            case definedMethodName -> "The method name already defined.\nPlease check the defined name.";
            case methodParamsCount -> "The number of parameters does not match.\nPlease check the number of parameters.";
            case methodTypeMatch -> "Method types do not match.\nPlease check the corresponding method type.";
            case variableNameMatch -> "Variable name is not valid.\nPlease check the variable name.";
            case cannotInclude -> "Didn't use : " + String.join(", ", Arrays.asList(OR, AND, NOT, TRUE, FALSE)) + "\nContains characters that cannot be included\nPlease change the variable name.";
            case methodParamsError -> "The parameter length of the method is not valid.\nPlease check the method parameters.";
            case noDefineMethod -> "Undefined method name.\nPlease check the method name.";
            case noDefineVariable -> "Undefined variable name.\nPlease check the variable name.";
            default -> "";
        };
        ErrorMessage.printErrorMessage(e, subMessage, path, line, position);
    }

    public VariableException defineType() {
        return new VariableException(defineType);
    }

    private final AtomicReference<String> defineTypeWord = new AtomicReference<>();
    public VariableException defineType(String word) {
        defineTypeWord.set(word);
        return defineType();
    }

    public VariableException noDefineVariable() {
        return new VariableException(noDefineVariable);
    }

    public VariableException noDefineMethod() {
        return new VariableException(noDefineMethod);
    }

    public VariableException methodParamsError() {
        return new VariableException(methodParamsError);
    }

    public VariableException cannotInclude() {
        return new VariableException(cannotInclude);
    }

    public VariableException variableNameMatch() {
        return new VariableException(variableNameMatch);
    }

    public VariableException methodTypeMatch() {
        return new VariableException(methodTypeMatch);
    }

    public VariableException methodParamsCount() {
        return new VariableException(methodParamsCount);
    }

    public VariableException definedMethodName() {
        return new VariableException(definedMethodName);
    }

    public VariableException forTypeMatchError() {
        return new VariableException(forTypeMatchError);
    }

    public VariableException noGrammar() {
        return new VariableException(noGrammar);
    }

    public VariableException typeMatch() {
        return new VariableException(typeMatch);
    }

    public VariableException sameVariable() {
        return new VariableException(sameVariable);
    }

    public VariableException localNoVariable() {
        return new VariableException(localNoVariable);
    }

    public VariableException reservedWorks() {
        return new VariableException(reservedWorks);
    }

    public VariableException noDefineType() {
        return new VariableException(noDefineType);
    }

}
