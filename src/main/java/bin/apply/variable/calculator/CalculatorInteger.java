package bin.apply.variable.calculator;

import bin.apply.item.CalculatorItem;
import bin.apply.variable.Parser;
import bin.apply.variable.calculator.number.NumberItem;
import bin.exception.MatchException;
import bin.repository.AccessList;
import bin.token.KlassToken;

import java.lang.constant.Constable;
import java.lang.constant.ConstantDesc;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public final class CalculatorInteger implements Calculator {
    public int calculator(String line) {
        Stack<Object> stack = this.parser(line)
                .stream()
                .map(v -> {
                    if (NumberItem.contains(v)) return v;
                    else return Parser.parser(KlassToken.INT_VARIABLE, v.strip());
                }).collect(Collectors.toCollection(Stack::new));
        return -1;
    }
}
