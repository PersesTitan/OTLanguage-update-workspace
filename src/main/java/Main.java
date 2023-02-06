import bin.apply.Repository;
import bin.apply.token.Token;
import bin.apply.variable.VariableTool;
import bin.apply.variable.type.ListType;
import bin.apply.variable.type.MapType;
import bin.apply.variable.type.OriginType;
import bin.apply.variable.type.SetType;

import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static bin.apply.Repository.klassItems;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    private Main() {
        ListType.load();

//        System.out.println(klassItems.size());
//        System.out.println(klassItems.keySet());
//
//        Repository.repositoryArray.create(OriginType.BOOLEAN.getName(), "변수1", "ㅇㅇ");
//        Repository.repositoryArray.create(ListType.INTEGER.getName(), "변수2", "[1,22,2,3]");
//        Repository.repositoryArray.create(SetType.INTEGER.getName(), "변수3", "(1,22,2,3.0)");
//
//        System.out.println(Repository.repositoryArray.get("변수1"));
//        System.out.println(Repository.repositoryArray.get("변수2"));
//        System.out.println(Repository.repositoryArray.get("변수3"));

    }

    private void readFile() {
    }
}
