package bin.apply.variable.calculator.number;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Number<T> {
    private final Map<String, BiFunction<T, T, T>> number = new HashMap<>();

    @SafeVarargs
    public Number(BiFunction<T, T, T>... functions) {
        int len = NumberItem.sings.size();
        for (int i = 0; i<len; i++) number.put(NumberItem.sings.get(i), functions[i]);
    }

    public T getNumber(String sing, Object a, Object b) {
        return this.number.get(sing).apply((T) a, (T) b);
    }
}
