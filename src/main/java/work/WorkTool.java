package work;

import bin.exception.FileException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;

public interface WorkTool extends Serializable {
    void reset();

    @Serial
    private void readObject(ObjectInputStream ois) {
        try {
            ois.defaultReadObject();
            reset();
        } catch (IOException | ClassNotFoundException e) {
            throw FileException.DO_NOT_INCLUDE.getThrow(e.getMessage());
        }
    }
}
