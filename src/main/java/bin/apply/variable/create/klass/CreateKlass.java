package bin.apply.variable.create.klass;

import bin.token.KlassToken;
import work.CreateWork;

public class CreateKlass extends CreateWork<DefineKlass> {
    public CreateKlass() {
        super(DefineKlass.class, KlassToken.KLASS, KlassToken.KLASS);
    }

    @Override
    protected Object createItem(Object... params) {
        return null;
    }

    @Override
    public void reset() {}
}
