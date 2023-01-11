package bin.apply;

import bin.apply.item.map.TypeMap;
import bin.exception.VariableException;
import work.ReplaceWork;
import work.StartWork;

import java.util.*;

public interface Repository {
    LinkedList<TypeMap> repositoryArray = new LinkedList<>();
    Set<String> types = new HashSet<>();

    Map<String, Map<String, StartWork>> startWorks = new HashMap<>();
    Map<String, Map<String, ReplaceWork>> replaceWorks = new HashMap<>();
    Set<String> defineWordList = new HashSet<>();

    static void addDefineWord(String word) {
        if (defineWordList.contains(word)) throw new VariableException().defineType(word);
        else defineWordList.add(word);
    }
}
