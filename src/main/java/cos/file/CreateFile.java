package cos.file;

import bin.token.KlassToken;
import work.CreateWork;

import java.io.File;

public class CreateFile extends CreateWork<File> {
    public CreateFile() {
        super(File.class, FileToken.FILE, KlassToken.STRING_VARIABLE);
    }

    @Override
    protected Object createItem(Object... params) {
        return new File(params[0].toString());
    }

    @Override
    public void reset() {}
}
