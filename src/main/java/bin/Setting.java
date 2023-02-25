package bin;

import bin.apply.mode.DebugMode;
import bin.exception.FileException;
import bin.exception.VariableException;
import bin.token.ColorToken;
import bin.token.SeparatorToken;
import bin.token.Token;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

public class Setting {
    public static void readFiles(String path, String lines) {
        ListIterator<String> iterator = lines.lines().toList().listIterator();
        Map<Integer, String> code = new HashMap<>();
        while (iterator.hasNext()) {
            String line = iterator.next();
            code.put(iterator.nextIndex(), (line = line.strip()).startsWith(Token.REMARK) ? "" : line);
        }
        Repository.codes.put(path, code);
    }

    public static void readFiles(String path) {
        final File file = new File(path);    //파일 생성
        String filePath = file.getPath();
        String fileName = file.getName();

        if (Repository.codes.containsKey(filePath)) return; // 이미 저장된 파일일때
        if (!file.exists()) throw FileException.DO_NOT_PATH.getThrow(filePath);
        if (!file.isFile()) throw FileException.FILE_TYPE_ERROR.getThrow(filePath);
        if (!file.canRead()) throw FileException.DO_NOT_READ.getThrow(filePath);
        if (SeparatorToken.isExtension(fileName)) throw FileException.EXTENSION_MATCH_ERROR.getThrow(fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            Map<Integer, String> code = new HashMap<>();
            for (int i = 1; ; i++) {
                String line = reader.readLine();
                if (line == null) break;
                code.put(i, (line = line.strip()).startsWith(Token.REMARK) ? "" : line);
            }
            Repository.codes.put(filePath, code);
        } catch (IOException e) {
            if (DebugMode.isDevelopment()) e.printStackTrace();
            throw FileException.DO_NOT_READ.getThrow(path);
        }
    }

    public static String toString(Object value) {
        if (value instanceof Boolean b) return b ? Token.TRUE : Token.FALSE;
        else return value.toString();
    }

    public static void runMessage(String errorLine) {
        warringMessage(String.format("경고! %s는 실행되지 않은 라인 입니다.", errorLine));
    }

    public static void warringMessage(String message) {
        if (DebugMode.WARRING.check())
            System.out.printf("%s%s%s\n", ColorToken.YELLOW, message, ColorToken.RESET);
    }

    public static void errorMessage(String message) {
        if (DebugMode.ERROR.check())
            System.out.printf("%s%s%s\n", ColorToken.RED, message, ColorToken.RESET);
    }
}
