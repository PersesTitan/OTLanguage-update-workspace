package bin.calculator;

import java.util.StringTokenizer;

import static bin.token.Token.AND;
import static bin.token.Token.OR;

public class CalculatorNumber implements CalculatorTool {
    public void start(String line) {

    }

    private void split(String line) {
        StringTokenizer token = new StringTokenizer(line, "ㅇㄴ" + OR + AND, true);
        while (token.hasMoreTokens()) {
            String group = token.nextToken();
        }
    }

    private void returnValue(String line) {

    }
}
