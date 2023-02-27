package bin.work.sys;

import bin.Setting;
import bin.token.KlassToken;
import work.StartWork;

public class Println extends StartWork {
    public Println() {
        super(KlassToken.SYSTEM, false, KlassToken.STRING_VARIABLE);
    }

    @Override
    protected void startItem(Object value, Object... params) {
        System.out.println(params[0]);
    }

    @Override
    public void reset() {}
}
