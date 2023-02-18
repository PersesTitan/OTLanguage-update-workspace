package bin.apply;

import bin.Repository;
import bin.Setting;
import bin.exception.Error;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Read {
    public final static AtomicReference<String> errorPath = new AtomicReference<>();
    public final static AtomicReference<String> errorLine = new AtomicReference<>();
    public final static AtomicInteger errorPosition = new AtomicInteger();

    public static void read(String path) {
        Setting.readFiles(path);
        for (Map.Entry<Integer, String> entry : Repository.codes.get(path).entrySet()) {
            start(path, entry.getValue(), entry.getKey());
        }
    }

    public static void read(String path, int start, int end) {
        Map<Integer, String> code = Repository.codes.get(path);
        for (int i = start; i<end; i++) start(path, code.get(i), i);
    }

    private static void start(String path, String line, int position) {
        try {
            if (line.isEmpty()) return;

        } catch (Error e) {
            errorPath.set(path);
            errorLine.set(line);
            errorPosition.set(position);
            throw e;
        }
    }
}
