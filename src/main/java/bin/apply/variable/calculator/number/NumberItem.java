package bin.apply.variable.calculator.number;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public enum NumberItem {
    INTEGER(new Number<>(Integer::sum, (a,b) -> a-b, (a,b) -> a*b, (a,b) -> a/b, (a,b) -> a%b)),
    LONG(new Number<>(Long::sum, (a,b) -> a-b, (a,b) -> a*b, (a,b) -> a/b, (a,b) -> a%b)),
    FLOAT(new Number<>(Float::sum, (a,b) -> a-b, (a,b) -> a*b, (a,b) -> a/b, (a,b) -> a%b)),
    DOUBLE(new Number<>(Double::sum, (a,b) -> a-b, (a,b) -> a*b, (a,b) -> a/b, (a,b) -> a%b));

    public final static List<String> sings = Arrays.asList("+", "-", "*", "/", "%");
    private final Number<?> number;

    public static boolean contains(String sing) {
        return NumberItem.sings.contains(sing);
    }

    public <T> T get(String sing, T a, T b) {
        return (T) number.getNumber(sing, a, b);
    }
}
