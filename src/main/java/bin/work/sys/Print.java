package bin.work.sys;

import bin.token.KlassToken;
import work.StartWork;

public class Print extends StartWork {
    public Print() {
        super(KlassToken.SYSTEM, false, KlassToken.STRING_VARIABLE);
    }

    @Override
    protected void startItem(Object value, Object... params) {
        System.out.print(params[0]);
    }

    @Override
    public void reset() {}
}
