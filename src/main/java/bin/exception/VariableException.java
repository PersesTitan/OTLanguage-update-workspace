package bin.exception;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
public enum VariableException implements ExceptionTool {
    TYPE_ERROR("변수 값이 유효하지 않습니다."),
    NO_DEFINE_NAME("정의되지 않은 변수명입니다."),
    NO_DEFINE_TYPE("정의되지 않은 변수타입입니다."),
    DEFINE_NAME("이미 존재하는 변수명입니다."),
    DEFINE_TYPE("이미 정의된 타입입니다."),
    ACCESS_ERROR("해당 위치에 접근할 수 없습니다.")
    ;

    private AtomicReference<String> errorCode;
    private final String message;

    @Override
    public String getSubMessage() {
        return switch (this) {
            case TYPE_ERROR ->
                    """
                    The variable value is invalid. %s
                    Please check the variable type.
                    """;
            case NO_DEFINE_NAME ->
                    """
                    Undefined variable name. %s
                    Please check the variable name.
                    """;
            case NO_DEFINE_TYPE ->
                    """
                    Undefined variable type. %s
                    Please check the variable type.
                    """;
            case ACCESS_ERROR ->
                    """
                    The location is inaccessible. %s
                    Please check your current location.
                    """;
            case DEFINE_NAME ->
                    """
                    Variable name that already exists. %s
                    Reserved words or already used variable names cannot be reused.
                    """;
            case DEFINE_TYPE ->
                    """
                    Variable type that already exists. %s
                    Reserved words or already used variable types cannot be reused.
                    """;
        };
    }

    @Override
    public String getErrorCode() {
        return "(" + this.errorCode.getAndSet(null) + ")";
    }

    public VariableError getThrow(String errorCode) {
        if (this.errorCode == null) this.errorCode = new AtomicReference<>(errorCode);
        return new VariableError(this.message);
    }

    // 에러 클래스
    public static class VariableError extends RuntimeException {
        public VariableError(String message) {
            super(message);
        }
    }
}
