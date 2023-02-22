package bin.apply;

import bin.Repository;
import bin.Setting;
import bin.exception.Error;
import bin.work.loop.For;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Read {
    public final static AtomicReference<String> errorPath = new AtomicReference<>();
    public final static AtomicReference<String> errorLine = new AtomicReference<>();
    public final static AtomicInteger errorPosition = new AtomicInteger();

    public static void read(String path) {
        Setting.readFiles(path);
        read(path, 1, Repository.codes.get(path).size() + 1);
    }

    public static void read(String path, int start, int end) {
        Map<Integer, String> code = Repository.codes.get(path);
        for (int i = start; i < end;) {
            i = start(path, code.get(i), i);
        }
    }

    private static int start(String path, String line, int position) {
        if (line.isEmpty()) return position + 1;
        try {
            if (Replace.isHave(line)) line = Replace.startLine(line);
            if (For.isLoop(line)) return For.start(line, path, position) + 1;
            else return Start.start(position, line);
        } catch (Error e) {
            errorPath.set(path);
            errorLine.set(line);
            errorPosition.set(position);
            throw e;
        } catch (NullPointerException e) {
            Setting.runMessage(line);
            return position + 1;
        }
    }
}
