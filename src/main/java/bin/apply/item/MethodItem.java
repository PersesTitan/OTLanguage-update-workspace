package bin.apply.item;

import java.util.Map;

public record MethodItem(String name, Map<Long, String> repository,
                         long start, long end, boolean isLoop,
                         ParamItem...params) {
}
