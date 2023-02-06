package bin.work.console;

import bin.apply.Repository;
import bin.apply.item.ParamItem;
import bin.apply.repository.AccessList;
import bin.apply.variable.type.OriginType;
import work.StartWork;

import java.util.Map;

import static bin.apply.token.KlassToken.PRINT;

public class Print extends StartWork {
    public Print() {
        super(new ParamItem(OriginType.STRING.getName(), ""));
    }

    @Override
    public void start(String line, Object[] params, long loopLine,
                      Map<Long, String> repository, AccessList accessList) {
        System.out.print(params[0]);
    }

    @Override
    protected void reset() {
        Repository.startWorks.put(PRINT, "", this);
    }
}
