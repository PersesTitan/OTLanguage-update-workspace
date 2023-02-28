import bin.Repository;
import bin.Setting;
import bin.apply.Read;
import bin.apply.Start;
import bin.apply.variable.create.CreateTool;
import bin.apply.variable.create.Types;
import bin.exception.Error;
import bin.token.KlassToken;
import bin.token.Token;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main implements Repository {
    public static void main(String[] args) {
        try {
            String test = """
                    1^10^1 {
                        0^:ㅇ_^1 {
                            ㅅㅁㅅ *                        
                        }         
                        ㅆㅁㅆ[]
                    }<= ㅇㅈㅇ ㅇ
                    """;
            Setting.readFiles("test", test);
            Read.read("test");
        } catch (Error e) {
            e.printStackTrace();
            String path = Read.errorPath.getAndSet(null);
            if (path == null) e.print();
            else {
                String line = Read.errorLine.getAndSet(null);
                int pos = Read.errorPosition.getAndSet(0);
                if (pos != 0 && line != null) e.print(path, line, pos);
                else e.print(path);
            }
        }
    }

    private Main(String[] args) {
        normal(args);
    }

    private void normal(String[] args) {
        Setting.readFiles(args[1]);
    }
}
