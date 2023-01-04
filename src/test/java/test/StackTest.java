package test;

import bin.calculator.CalculatorTool;

import java.util.*;

import static bin.token.Token.*;

public class StackTest extends LinkedList<String> implements List<String> {
    private static final Set<String> set = new HashSet<>() {{
        add(TRUE);
        add(FALSE);
        add(NOT);
        add(OR);
        add(AND);
        CalculatorTool.compare
                .keySet()
                .forEach(v -> add("ㅇ".concat(v).concat("ㅇ")));
        CalculatorTool.number
                .keySet()
                .forEach(v -> add("ㅇ".concat(v).concat("ㅇ")));
    }};

    @Override
    public boolean add(String s) {
        // 토큰에 포함되는 단어일때
        if (set.contains(s)) {
            return super.add(s);
        } else {
            // 마지막 토큰이 포함되는 단어일때
            if (super.isEmpty() || set.contains(super.getLast())) {
                return super.add(s);
            } else {
                int pos = super.size() - 1;
                super.set(pos, super.getLast().concat(s));
                return true;
            }
        }
    }

    @Override
    public void add(int index, String element) {
        if (set.contains(element)) {
            super.add(index, element);
        } else {
            if (super.isEmpty() || set.contains(super.get(index))) {
                super.add(index, element);
            } else {
                super.set(index, super.get(index).concat(element));
            }
        }
    }
}
