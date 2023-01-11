package bin.apply.run;

import bin.apply.type_tool.Casting;
import work.item.TypeDTO;

public interface RunTool extends Casting {
    // ㅇㅁㅇ[안녕] => 안녕
    default String bothCut(String line) {
        return line.substring(1, line.length()-1);
    }

    default String bothCut(String line, int start, int end) {
        return line.substring(start, line.length() - end);
    }

    default String[] getParams(String line) {
        line = bothCut(line);
        return line.split("]\\[", count(line));
    }

    default int count(String value) {
        int count = 1, i = -1;
        while ((i = value.indexOf("][", i+1)) != -1) count++;
        return count;
    }
}