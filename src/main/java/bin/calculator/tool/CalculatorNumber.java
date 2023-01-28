package bin.calculator.tool;

import bin.exception.VariableException;

public interface CalculatorNumber {
    // 더하기
    static Object sum(Object a, Object b) {
        if (a instanceof Integer a1) {
            if (b instanceof Integer b1) return a1 + b1;
            else if (b instanceof Long b1) return a1 + b1;
            else if (b instanceof Float b1) return a1 + b1;
            else if (b instanceof Double b1) return a1 + b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Long a1) {
            if (b instanceof Integer b1) return a1 + b1;
            else if (b instanceof Long b1) return a1 + b1;
            else if (b instanceof Float b1) return a1 + b1;
            else if (b instanceof Double b1) return a1 + b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Float a1) {
            if (b instanceof Integer b1) return a1 + b1;
            else if (b instanceof Long b1) return a1 + b1;
            else if (b instanceof Float b1) return a1 + b1;
            else if (b instanceof Double b1) return a1 + b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Double a1) {
            if (b instanceof Integer b1) return a1 + b1;
            else if (b instanceof Long b1) return a1 + b1;
            else if (b instanceof Float b1) return a1 + b1;
            else if (b instanceof Double b1) return a1 + b1;
            else throw new VariableException().noCalculator(b.toString());
        } else throw new VariableException().noCalculator(a.toString());
    }

    // 빼기
    static Object subtract(Object a, Object b) {
        if (a instanceof Integer a1) {
            if (b instanceof Integer b1) return a1 - b1;
            else if (b instanceof Long b1) return a1 - b1;
            else if (b instanceof Float b1) return a1 - b1;
            else if (b instanceof Double b1) return a1 - b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Long a1) {
            if (b instanceof Integer b1) return a1 - b1;
            else if (b instanceof Long b1) return a1 - b1;
            else if (b instanceof Float b1) return a1 - b1;
            else if (b instanceof Double b1) return a1 - b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Float a1) {
            if (b instanceof Integer b1) return a1 - b1;
            else if (b instanceof Long b1) return a1 - b1;
            else if (b instanceof Float b1) return a1 - b1;
            else if (b instanceof Double b1) return a1 - b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Double a1) {
            if (b instanceof Integer b1) return a1 - b1;
            else if (b instanceof Long b1) return a1 - b1;
            else if (b instanceof Float b1) return a1 - b1;
            else if (b instanceof Double b1) return a1 - b1;
            else throw new VariableException().noCalculator(b.toString());
        } else throw new VariableException().noCalculator(a.toString());
    }

    // 곱하기
    static Object multiply(Object a, Object b) {
        if (a instanceof Integer a1) {
            if (b instanceof Integer b1) return a1 * b1;
            else if (b instanceof Long b1) return a1 * b1;
            else if (b instanceof Float b1) return a1 * b1;
            else if (b instanceof Double b1) return a1 * b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Long a1) {
            if (b instanceof Integer b1) return a1 * b1;
            else if (b instanceof Long b1) return a1 * b1;
            else if (b instanceof Float b1) return a1 * b1;
            else if (b instanceof Double b1) return a1 * b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Float a1) {
            if (b instanceof Integer b1) return a1 * b1;
            else if (b instanceof Long b1) return a1 * b1;
            else if (b instanceof Float b1) return a1 * b1;
            else if (b instanceof Double b1) return a1 * b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Double a1) {
            if (b instanceof Integer b1) return a1 * b1;
            else if (b instanceof Long b1) return a1 * b1;
            else if (b instanceof Float b1) return a1 * b1;
            else if (b instanceof Double b1) return a1 * b1;
            else throw new VariableException().noCalculator(b.toString());
        } else throw new VariableException().noCalculator(a.toString());
    }

    // 나누기
    static Object division(Object a, Object b) {
        if (a instanceof Integer a1) {
            if (b instanceof Integer b1) return a1 / b1;
            else if (b instanceof Long b1) return a1 / b1;
            else if (b instanceof Float b1) return a1 / b1;
            else if (b instanceof Double b1) return a1 / b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Long a1) {
            if (b instanceof Integer b1) return a1 / b1;
            else if (b instanceof Long b1) return a1 / b1;
            else if (b instanceof Float b1) return a1 / b1;
            else if (b instanceof Double b1) return a1 / b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Float a1) {
            if (b instanceof Integer b1) return a1 / b1;
            else if (b instanceof Long b1) return a1 / b1;
            else if (b instanceof Float b1) return a1 / b1;
            else if (b instanceof Double b1) return a1 / b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Double a1) {
            if (b instanceof Integer b1) return a1 / b1;
            else if (b instanceof Long b1) return a1 / b1;
            else if (b instanceof Float b1) return a1 / b1;
            else if (b instanceof Double b1) return a1 / b1;
            else throw new VariableException().noCalculator(b.toString());
        } else throw new VariableException().noCalculator(a.toString());
    }

    // 나머지
    static Object remainder(Object a, Object b) {
        if (a instanceof Integer a1) {
            if (b instanceof Integer b1) return a1 % b1;
            else if (b instanceof Long b1) return a1 % b1;
            else if (b instanceof Float b1) return a1 % b1;
            else if (b instanceof Double b1) return a1 % b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Long a1) {
            if (b instanceof Integer b1) return a1 % b1;
            else if (b instanceof Long b1) return a1 % b1;
            else if (b instanceof Float b1) return a1 % b1;
            else if (b instanceof Double b1) return a1 % b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Float a1) {
            if (b instanceof Integer b1) return a1 % b1;
            else if (b instanceof Long b1) return a1 % b1;
            else if (b instanceof Float b1) return a1 % b1;
            else if (b instanceof Double b1) return a1 % b1;
            else throw new VariableException().noCalculator(b.toString());
        } else if (a instanceof Double a1) {
            if (b instanceof Integer b1) return a1 % b1;
            else if (b instanceof Long b1) return a1 % b1;
            else if (b instanceof Float b1) return a1 % b1;
            else if (b instanceof Double b1) return a1 % b1;
            else throw new VariableException().noCalculator(b.toString());
        } else throw new VariableException().noCalculator(a.toString());
    }
}
