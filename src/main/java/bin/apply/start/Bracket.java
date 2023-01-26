package bin.apply.start;

import java.util.Map;

public class Bracket {
    public long getPair(Map<Long, String> file, long lineNumber) {
        int stack = 0;
        for (long i = lineNumber;; i++) {
            String line = file.get(i);
            if (line.isEmpty()) continue;
            if (line.endsWith("{")) stack++;
            else if (line.startsWith("}")) {
                if (stack <= 0) return i;
                else stack--;
            }
        }
    }
}
