package bin.exception;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
public enum FileException implements ExceptionTool {
    DO_NOT_READ("파일을 읽을 수 없습니다.");
    ;

    private AtomicReference<String> errorCode;
    private final String message;

    @Override
    public String getSubMessage() {
        return switch (this) {
            case DO_NOT_READ ->
                    """
                    File not found. %s
                    Please check the file location and path.
                    """;
        };
    }

    @Override
    public String getErrorCode() {
        return "(" + this.errorCode.getAndSet(null) + ")";
    }

    public FileError getThrow(String errorCode) {
        if (this.errorCode == null) this.errorCode = new AtomicReference<>(errorCode);
        return new FileError(this.message);
    }

    public static class FileError extends RuntimeException {
        public FileError(String message) {
            super(message);
        }
    }
}
