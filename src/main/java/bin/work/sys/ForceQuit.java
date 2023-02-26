package bin.work.sys;

import bin.token.KlassToken;
import work.StartWork;

public class ForceQuit extends StartWork {
    public ForceQuit() {
        super(KlassToken.SYSTEM, false);
    }

    @Override
    protected void startItem(Object value, Object... params) {
        System.exit(0);
    }

    @Override
    public void reset() {

    }
}
