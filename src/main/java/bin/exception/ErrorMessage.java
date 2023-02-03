package bin.exception;

import bin.apply.Repository;
import bin.apply.Setting;

import java.util.Iterator;

public interface ErrorMessage {
    // errorType: VariableError
    // message: 변수타입이 유효하지 않습니다.
    // subMessage: No vail ~ fix
    // path: start.otl
    static void printErrorMessage(String errorType, String message, String subMessage) {
        final String nextLine = System.lineSeparator().concat("\t");

        StringBuilder messageTotal = new StringBuilder("OTLanguage.");
        messageTotal.append(errorType).append(": ").append(message);

        final Iterator<String> pathIte = Repository.paths.iterator();
        final Iterator<Long> posIte = Repository.positions.iterator();

        messageTotal.append(nextLine).append("otl file location where it occurred(")
                .append(pathIte.next()).append(':').append(posIte.next()).append(')');
        while (pathIte.hasNext() && posIte.hasNext()) {
            String path = pathIte.next();
            long position = posIte.next();
            messageTotal.append(nextLine).append("- ").append(path).append(':').append(position);
        }
        messageTotal.append(nextLine).append("otl ");
        subMessage.strip().lines().forEach(line -> messageTotal.append(nextLine).append("otl ").append(line));

        if (Repository.runType.get().isShell()) Setting.errorMessage(messageTotal.toString());
        else System.err.println(messageTotal);
    }
}
