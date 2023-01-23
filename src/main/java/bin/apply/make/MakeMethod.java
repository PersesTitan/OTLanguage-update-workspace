package bin.apply.make;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MakeMethod {
    private final Object[] params;
    private final MakeKlass klass;
}
