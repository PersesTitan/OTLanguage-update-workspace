package work;

import bin.apply.repository.AccessList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

public abstract class StartWork implements Serializable {
    public abstract Object start(String line, Object[] params,
                                 Map<Long, String> repository, AccessList accessList);
    protected abstract void reset(ObjectInputStream ois);

    @Serial
    private void readObject(ObjectInputStream ois) {
        try {
            ois.defaultReadObject();
            reset(ois);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
