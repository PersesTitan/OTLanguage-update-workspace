package bin.calculator.tool;

import bin.exception.VariableException;

public interface CalculatorBool {
    // <
    static boolean small(Object a, Object b) {
        if (a instanceof Integer a1) {
            if (b instanceof Integer b1) return a1 < b1;
            else if (b instanceof Long b1) return a1 < b1;
            else if (b instanceof Float b1) return a1 < b1;
            else if (b instanceof Double b1) return a1 < b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Long a1) {
            if (b instanceof Integer b1) return a1 < b1;
            else if (b instanceof Long b1) return a1 < b1;
            else if (b instanceof Float b1) return a1 < b1;
            else if (b instanceof Double b1) return a1 < b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Float a1) {
            if (b instanceof Integer b1) return a1 < b1;
            else if (b instanceof Long b1) return a1 < b1;
            else if (b instanceof Float b1) return a1 < b1;
            else if (b instanceof Double b1) return a1 < b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Double a1) {
            if (b instanceof Integer b1) return a1 < b1;
            else if (b instanceof Long b1) return a1 < b1;
            else if (b instanceof Float b1) return a1 < b1;
            else if (b instanceof Double b1) return a1 < b1;
            else throw new VariableException().noCalculator(b.toString());
        } else throw new VariableException().noCalculator(a.toString());
    }

    // <=
    static boolean smallOrSame(Object a, Object b) {
        if (a instanceof Integer a1) {
            if (b instanceof Integer b1) return a1 <= b1;
            else if (b instanceof Long b1) return a1 <= b1;
            else if (b instanceof Float b1) return a1 <= b1;
            else if (b instanceof Double b1) return a1 <= b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Long a1) {
            if (b instanceof Integer b1) return a1 <= b1;
            else if (b instanceof Long b1) return a1 <= b1;
            else if (b instanceof Float b1) return a1 <= b1;
            else if (b instanceof Double b1) return a1 <= b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Float a1) {
            if (b instanceof Integer b1) return a1 <= b1;
            else if (b instanceof Long b1) return a1 <= b1;
            else if (b instanceof Float b1) return a1 <= b1;
            else if (b instanceof Double b1) return a1 <= b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Double a1) {
            if (b instanceof Integer b1) return a1 <= b1;
            else if (b instanceof Long b1) return a1 <= b1;
            else if (b instanceof Float b1) return a1 <= b1;
            else if (b instanceof Double b1) return a1 <= b1;
            else throw new VariableException().noCalculator(b.toString());
        } else throw new VariableException().noCalculator(a.toString());
    }

    // >
    static boolean big(Object a, Object b) {
        if (a instanceof Integer a1) {
            if (b instanceof Integer b1) return a1 > b1;
            else if (b instanceof Long b1) return a1 > b1;
            else if (b instanceof Float b1) return a1 > b1;
            else if (b instanceof Double b1) return a1 > b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Long a1) {
            if (b instanceof Integer b1) return a1 > b1;
            else if (b instanceof Long b1) return a1 > b1;
            else if (b instanceof Float b1) return a1 > b1;
            else if (b instanceof Double b1) return a1 > b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Float a1) {
            if (b instanceof Integer b1) return a1 > b1;
            else if (b instanceof Long b1) return a1 > b1;
            else if (b instanceof Float b1) return a1 > b1;
            else if (b instanceof Double b1) return a1 > b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Double a1) {
            if (b instanceof Integer b1) return a1 > b1;
            else if (b instanceof Long b1) return a1 > b1;
            else if (b instanceof Float b1) return a1 > b1;
            else if (b instanceof Double b1) return a1 > b1;
            else throw new VariableException().noCalculator(b.toString());
        } else throw new VariableException().noCalculator(a.toString());
    }

    // >=
    static boolean bigOrSame(Object a, Object b) {
        if (a instanceof Integer a1) {
            if (b instanceof Integer b1) return a1 >= b1;
            else if (b instanceof Long b1) return a1 >= b1;
            else if (b instanceof Float b1) return a1 >= b1;
            else if (b instanceof Double b1) return a1 >= b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Long a1) {
            if (b instanceof Integer b1) return a1 >= b1;
            else if (b instanceof Long b1) return a1 >= b1;
            else if (b instanceof Float b1) return a1 >= b1;
            else if (b instanceof Double b1) return a1 >= b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Float a1) {
            if (b instanceof Integer b1) return a1 >= b1;
            else if (b instanceof Long b1) return a1 >= b1;
            else if (b instanceof Float b1) return a1 >= b1;
            else if (b instanceof Double b1) return a1 >= b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Double a1) {
            if (b instanceof Integer b1) return a1 >= b1;
            else if (b instanceof Long b1) return a1 >= b1;
            else if (b instanceof Float b1) return a1 >= b1;
            else if (b instanceof Double b1) return a1 >= b1;
            else throw new VariableException().noCalculator(b.toString());
        } else throw new VariableException().noCalculator(a.toString());
    }

    // =
    static boolean same(Object a, Object b) {
        if (a instanceof Integer a1) {
            if (b instanceof Integer b1) return a1.equals(b1);
            else if (b instanceof Long b1) return (long) a1 == b1;
            else if (b instanceof Float b1) return (float) a1 == b1;
            else if (b instanceof Double b1) return (double) a1 == b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Long a1) {
            if (b instanceof Integer b1) return a1 == (long) b1;
            else if (b instanceof Long b1) return a1.equals(b1);
            else if (b instanceof Float b1) return (float) a1 == b1;
            else if (b instanceof Double b1) return (double) a1 == b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Float a1) {
            if (b instanceof Integer b1) return a1 == (float) b1;
            else if (b instanceof Long b1) return a1 == (float) b1;
            else if (b instanceof Float b1) return a1.equals(b1);
            else if (b instanceof Double b1) return (double) a1 == b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Double a1) {
            if (b instanceof Integer b1) return a1 == (double) b1;
            else if (b instanceof Long b1) return a1 == (double) b1;
            else if (b instanceof Float b1) return a1 == (double) b1;
            else if (b instanceof Double b1) return a1.equals(b1);
            else throw new VariableException().noCalculator(b.toString());
        } else throw new VariableException().noCalculator(a.toString());
    }
}
