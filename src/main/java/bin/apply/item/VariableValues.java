package bin.apply.item;

import java.util.function.Function;

public record VariableValues(Class<?> klass, Function<Object, Object> casting) {
}
