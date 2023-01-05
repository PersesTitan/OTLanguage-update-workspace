package bin.token;

public interface EditToken {
    static String bothCut(String line) {
        return line.substring(1, line.length() - 1);
    }
}
