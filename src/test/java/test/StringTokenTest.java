package test;

import java.util.*;

public class StringTokenTest {
    private final List<String> list;
    private final ListIterator<String> iterator;
    public StringTokenTest(String line, String...str) {
        // 정렬
        str = Arrays.stream(str)
                .filter(line::contains)
                .toList()
                .toArray(new String[0]);

        this.list = new ArrayList<>(Arrays.asList(cut(line, str[0])));
        for (String token : str) {
            int size = list.size() - 1;
            if (list.get(size).endsWith(token)) {
                cutEnd(list, token);
            }

            size = list.size();
            for (int i = 0; i < size; i++) {
                if (i % 2 != 0) continue;
                else if (isPass(list, i, token)) continue;
                cut(list, i, token);
                if (size != list.size()) {
                    i = 0;
                    size = list.size();
                }
            }
        }
        list.removeIf(String::isEmpty);
        this.iterator = list.listIterator();
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public String next() {
        return this.iterator.next();
    }

    public List<String> getList() {
        return this.list;
    }

    // 패스 해야하는 로직
    private boolean isPass(List<String> list, int i, String word) {
        return Objects.equals(list.get(i), word);
    }

    private void cutAndAdd(List<String> list, int i, String a, String b, String c) {
        list.remove(i);
        list.add(i, a);
        list.add(i, b);
        list.add(i, c);
    }

    private void cutEnd(List<String> list, String word) {
        final int pos = list.size() - 1;
        String token = list.get(pos);
        while (token.endsWith(word)) {
            token = token.substring(0, token.length() - word.length());
            list.add(word);
        }
        list.set(pos, token);
    }

    private void cut(List<String> list, int pos, String word) {
        String line = list.get(pos);
        int i = line.indexOf(word);
        if (i == -1) return;
        String a = line.substring(0, i);
        String b = line.substring(i, i + word.length());
        String c = line.substring(i + word.length());
        cutAndAdd(list, pos, a, b, c);
    }

    private String[] cut(String line, String word) {
        int i = line.indexOf(word);
        return new String[] {
            line.substring(0, i),
            line.substring(i, i + word.length()),
            line.substring(i + word.length())
        };
    }
}
