package bin.work.system;

import bin.apply.Repository;
import bin.apply.repository.AccessList;
import bin.apply.token.KlassToken;
import work.StartWork;

import java.util.Map;

import static bin.apply.token.KlassToken.QUIT;
import static bin.apply.token.KlassToken.SYSTEM;

public class ForceQuit extends StartWork {
    public ForceQuit() {
        super(false);
    }

    @Override
    public void start(String line, Object[] params, long loopLine, Map<Long, String> repository, AccessList accessList) {
        System.exit(0);
    }

    @Override
    protected void reset() {
        Repository.startWorks.put(SYSTEM, QUIT, this);
    }
}
