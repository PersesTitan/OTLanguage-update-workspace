package bin.apply.item;

import java.util.function.Function;

public record KlassItem(Class<?> klass, Function<Object, Object> casting) {
}
