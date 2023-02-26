package bin.work.loop;

import bin.Repository;
import bin.apply.Loop;
import bin.apply.Read;
import bin.apply.Start;
import bin.apply.variable.Parser;
import bin.exception.MatchException;
import bin.exception.VariableException;
import bin.token.EditToken;
import bin.token.KlassToken;
import bin.token.Token;
import work.CreateWork;
import work.StartWork;

import java.util.Map;

public class For {
    public static boolean isLoop(String line) {
        int start, end;
        return line.endsWith(Token.LOOP_S)
                && (start = line.indexOf(Token.FOR)) >= 0
                && (end = line.lastIndexOf(Token.FOR)) >= 0
                && start != end;
    }

    public static int start(String line, String path, int start) {
        Map<Integer, String> code = Repository.codes.get(path);
        int end = Loop.next(code, start);
        String endLoop = code.get(end);

        int f = line.indexOf(Token.FOR);
        int s = line.lastIndexOf(Token.FOR);
        String a = line.substring(0, f).strip();
        String b = line.substring(f + 1, s).strip();
        String c = line.substring(s + 1, line.length() - 1).strip();

        switch (Loop.getLoopType(endLoop)) {
            case PUT -> {
                String[] tokens = Loop.getPut(endLoop).split("\\s+", 2);
                if (tokens.length != 2) throw MatchException.GRAMMAR_ERROR.getThrow(endLoop);
                String type = tokens[0];
                String name = tokens[1];
                if (!Repository.isKlass(type)) throw VariableException.NO_DEFINE_TYPE.getThrow(type);
                try {
                    switch (type) {
                        case KlassToken.INT_VARIABLE -> {
                            int ai = (int) Parser.parser(type, a);
                            int bi = (int) Parser.parser(type, b);
                            int ci = (int) Parser.parser(type, c);
                            Repository.repositoryArrays.create(type, name, ai);
                            for (int i = ai; i < bi; i += ci) {
                                Repository.repositoryArrays.update(type, name, i);
                                Read.read(path, start + 1, end);
                            }
                        }
                        case KlassToken.FLOAT_VARIABLE -> {
                            float ai = (float) Parser.parser(type, a);
                            float bi = (float) Parser.parser(type, b);
                            float ci = (float) Parser.parser(type, c);
                            Repository.repositoryArrays.create(type, name, ai);
                            for (float i = ai; i < bi; i += ci) {
                                Repository.repositoryArrays.update(type, name, i);
                                Read.read(path, start + 1, end);
                            }
                        }
                        case KlassToken.LONG_VARIABLE -> {
                            long ai = (long) Parser.parser(type, a);
                            long bi = (long) Parser.parser(type, b);
                            long ci = (long) Parser.parser(type, c);
                            Repository.repositoryArrays.create(type, name, ai);
                            for (long i = ai; i < bi; i += ci) {
                                Repository.repositoryArrays.update(type, name, i);
                                Read.read(path, start + 1, end);
                            }
                        }
                        case KlassToken.DOUBLE_VARIABLE -> {
                            double ai = (double) Parser.parser(type, a);
                            double bi = (double) Parser.parser(type, b);
                            double ci = (double) Parser.parser(type, c);
                            Repository.repositoryArrays.create(type, name, ai);
                            for (double i = ai; i < bi; i += ci) {
                                Repository.repositoryArrays.update(type, name, i);
                                Read.read(path, start + 1, end);
                            }
                        }
                        default -> throw VariableException.DEFINE_TYPE.getThrow(type);
                    }
                } finally {
                    Repository.repositoryArrays.remove(type, name);
                }
            }
            case NONE -> {
                double ai = Parser.getNumber(a);
                double bi = Parser.getNumber(b);
                double ci = Parser.getNumber(c);
                for (double i = ai; i < bi; i += ci) {
                    Read.read(path, start + 1, end);
                }
            }
            default -> throw MatchException.ZONE_MATCH_ERROR.getThrow(endLoop);
        }
        return end;
    }
}
