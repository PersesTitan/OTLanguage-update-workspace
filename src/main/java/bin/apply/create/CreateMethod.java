package bin.apply.create;

import bin.apply.item.ParamItem;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class CreateMethod {
    private final String name;
    private final ParamItem[] params;
    private final Map<Long, String> repository;

    private final long start;
    private final long end;
    private final boolean isLoop;
}
