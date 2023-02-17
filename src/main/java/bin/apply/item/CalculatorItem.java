package bin.apply.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CalculatorItem {
    SUM("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVISION("/"),
    REMAINDER("%");

    private final String sing;
}
