import bin.apply.type.VariableType;
import bin.logic.console.Print;
import bin.logic.console.Println;
import work.StartWork;
import work.item.TypeDTO;
import work.item.TypeListDTO;

import java.util.LinkedList;

import static bin.token.Token.PRINT;

public class MakeModule {
    public static void main(String[] args) {
        new MakeModule();
    }

    public MakeModule() {
        LinkedList<StartWork> list = new LinkedList<>();
    }

    public void start() {
        TypeDTO string = new TypeDTO(VariableType.Class, VariableType.STRING_VARIABLE);
        TypeListDTO stringList = new TypeListDTO(string);
        new Print(PRINT, stringList);
    }
}
