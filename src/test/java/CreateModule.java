import bin.apply.type.VariableType;
import bin.logic.console.Print;
import bin.logic.console.Println;
import bin.logic.variable.CreateOrigin;
import bin.logic.variable.GetVariable;
import work.StartWork;
import work.item.TypeDTO;
import work.item.TypeListDTO;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Predicate;

import static bin.apply.Repository.startWorks;
import static bin.token.Token.PRINT;
import static bin.token.Token.PRINTLN;

public class CreateModule {
    public void create() {
        TypeDTO string = new TypeDTO(VariableType.String, VariableType.String.getKlassName());
        TypeListDTO stringList = new TypeListDTO(string);

        createNew(PRINT, "", new Print(PRINT, stringList));
        createNew(PRINTLN, "", new Println(PRINTLN, stringList));
        Arrays.stream(VariableType.values())
                .filter(Predicate.not(VariableType::isClass))
                .forEach(v -> {
                    String klassName = v.getKlassName();
                    TypeListDTO dtoList = new TypeListDTO(string, new TypeDTO(v, v.getKlassName()));
                    createNew(klassName, "", new CreateOrigin(klassName, dtoList));
                });
    }

    private void createNew(String klassName, String methodName, StartWork work) {
        VariableType.isValidTypeError(klassName);
        if (!startWorks.containsKey(klassName)) startWorks.put(klassName, new HashMap<>());
        if (!startWorks.get(klassName).containsKey(methodName)) startWorks.get(klassName).put(methodName, work);
        else throw new RuntimeException();
    }
}
