package bin.exception;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
public enum MatchException implements ExceptionTool {
    GRAMMAR_ERROR("문법이 일치하지 않습니다."),
    PARAM_COUNT_ERROR("메소드의 파리미터 길이가 유효하지 않습니다."),
    ZONE_ERROR("존을 사용할 수 없거나 사용해야합니다."),
    DEFINE_CLASS_NAME("이미 정의된 클래스 이름입니다."),
    DEFINE_METHOD_NAME("이미 정의된 메소드 이름입니다."),
    CALCULATOR_ERROR("계산식 중 유효하지 않는 토큰이 존재합니다."),
    ;

    private AtomicReference<String> errorCode;
    private final String message;

    @Override
    public String getSubMessage() {
        return switch (this) {
            case GRAMMAR_ERROR ->
                    """
                    The grammar does not match. %s
                    Please check the grammar.
                    """;
            case PARAM_COUNT_ERROR ->
                    """
                    The parameter length of the method is not valid. %s
                    Please check the method parameters.
                    """;
            case ZONE_ERROR ->
                    """
                    Zone is not available or must be used. %s
                    Please check the grammar.
                    """;
            case DEFINE_CLASS_NAME ->
                    """
                    The class name is already defined. %s
                    Please check the name.
                    """;
            case DEFINE_METHOD_NAME ->
                    """
                    The method name is already defined. %s
                    Please check the name.
                    """;
            case CALCULATOR_ERROR ->
                    """
                    An invalid token exists in the calculation expression. %s
                    Please check the calculation formula.
                    """;
        };
    }

    @Override
    public String getErrorCode() {
        return "(" + this.errorCode.getAndSet(null) + ")";
    }

    public MatchError getThrow(String errorCode) {
        if (this.errorCode == null) this.errorCode = new AtomicReference<>(errorCode);
        return new MatchError(this.message);
    }

    public static class MatchError extends RuntimeException {
        public MatchError(String message) {
            super(message);
        }
    }
}
