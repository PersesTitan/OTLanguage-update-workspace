package bin.apply;

import bin.apply.item.map.AccessMap;
import bin.apply.item.Color;
import bin.apply.run.ReplaceRun;
import bin.apply.run.StartRun;
import bin.apply.type.DebugMode;
import bin.apply.type.RunType;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static bin.token.Token.GET_E;
import static bin.token.Token.GET_S;

public class Setting {
    public static final AtomicBoolean developmentMode;
    public static final AtomicReference<DebugMode> debugMode;
    public static final AtomicReference<RunType> runType;
    static {
        developmentMode = new AtomicBoolean(false);
        debugMode = new AtomicReference<>(DebugMode.INFO);
        runType = new AtomicReference<>(RunType.Normal);
    }

    public static void start(String line, String errorLine, AccessMap repositoryArray) {
        if (line.isEmpty()) return;

        if (StartRun.getInstance().start(line, repositoryArray, true)) return;
        if (line.contains(GET_S) && line.contains(GET_E))
            line = ReplaceRun.getInstance().start(line, repositoryArray);
        StartRun.getInstance().start(line, repositoryArray, false);
    }

    public static String replace(String line) {
        return line;
    }

    // 메세지
    public static void runMessage(String errorLine) {
        warringMessage(String.format("경고! %s는 실행되지 않은 라인 입니다.", errorLine));
    }

    public static void warringMessage(String message) {
        if (debugMode.get().check(DebugMode.WARRING))
            System.out.printf("%s%s%s\n", Color.YELLOW, message, Color.RESET);
    }

    public static void errorMessage(String message) {
        if (debugMode.get().check(DebugMode.ERROR))
            System.out.printf("%s%s%s\n", Color.RED, message, Color.RESET);
    }
}
