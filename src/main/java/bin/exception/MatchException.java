package bin.exception;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
public enum MatchException implements ExceptionTool {
    GRAMMAR_ERROR("문법이 일치하지 않습니다.")
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
