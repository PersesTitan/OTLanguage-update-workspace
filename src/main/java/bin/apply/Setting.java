package bin.apply;

import bin.apply.casting.TypeCast;
import bin.apply.item.Color;
import bin.apply.item.DebugMode;
import bin.apply.item.RunType;
import bin.orign.CreateList;
import bin.orign.CreateMap;
import bin.orign.CreateSet;

public class Setting {
    public static DebugMode debugMode = DebugMode.INFO;
    public static RunType runType = RunType.Normal;

    public static void runMessage(String errorLine) {
        warringMessage(String.format("경고! %s는 실행되지 않은 라인 입니다.", errorLine));
    }

    public static void warringMessage(String message) {
        if (debugMode.check(DebugMode.WARRING))
            System.out.printf("%s%s%s\n", Color.YELLOW, message, Color.RESET);
    }

    public static void errorMessage(String message) {
        if (debugMode.check(DebugMode.ERROR))
            System.out.printf("%s%s%s\n", Color.RED, message, Color.RESET);
    }

    static {
        for (TypeCast typeCast : TypeCast.values()) {
            CreateSet<?> set = new CreateSet<>(typeCast, typeCast.getKlass());
            Repository.createWorks.put(set.getType(), set);

            CreateList<?> list = new CreateList<>(typeCast, typeCast.getKlass());
            Repository.createWorks.put(list.getType(), list);

            CreateMap<?, ?> map = new CreateMap<>(String.class, typeCast, typeCast.getKlass());
            Repository.createWorks.put(map.getType(), map);
        }
    }
}
