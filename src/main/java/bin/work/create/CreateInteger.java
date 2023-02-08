package bin.work.create;

import bin.apply.token.KlassToken;
import bin.apply.variable.casting.Casting;
import bin.apply.variable.casting.TypeCheck;
import work.CreateWork;

public final class CreateInteger extends CreateWork {
    public CreateInteger() {
        super(Integer.class, KlassToken.INT_VARIABLE, KlassToken.INT_VARIABLE);
    }

    @Override
    protected Object casting(Object... params) {
        if (params[0] instanceof Integer i) return i;
        else {
            String value = params[0].toString();
            if (TypeCheck.isInteger(value) || value.length() == 1) {
                return Casting.getInteger(value);
            } else {

            }
        }

        return null;
    }

    @Override
    protected void reset() {}
}
