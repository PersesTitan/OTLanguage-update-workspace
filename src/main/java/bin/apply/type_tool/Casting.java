package bin.apply.type_tool;

import work.item.TypeDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public interface Casting {
    Map<String, Function<Object, Object>> castItems = new HashMap<>();

    default Object cast(TypeDTO dto, Object value) {
        return cast(dto.getKlassName(), value);
    }

    default Object cast(String klassName, Object value) {
        Function<Object, Object> work = castItems.get(klassName);
        assert work != null : "정의되지 않은 클래스명입니다.";
        return work.apply(value);
    }
}
