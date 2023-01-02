import bin.apply.Setting;
import bin.apply.item.AccessMap;

public class MainTest {
    public static void main(String[] args) {
        new CreateModule().create();
        AccessMap map = new AccessMap();
        String total = """
                ㅆㅁㅆ 앙
                ㅇㅅㅇ 변수:1.2
                ㅆㅁㅆ :변수_
                
                ㅇㅂㅇ 변수1:ㅇㅇ
                ㅇㅂㅇ 변수2:ㄴㄴ
                ㄹㅂㄹ 변수3:[ㄴㄴ, ㅇㅇ]
                
                ㅆㅁㅆ :변수1_ :변수2_ :변수3_
                """;
        total.lines().forEach(v -> Setting.start(v, v, map));
    }
}
