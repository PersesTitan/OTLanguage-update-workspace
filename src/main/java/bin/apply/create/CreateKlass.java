package bin.apply.create;

import bin.apply.Repository;
import bin.apply.item.ParamItem;
import bin.apply.repository.HpMap;
import bin.apply.repository.TypeMap;
import lombok.RequiredArgsConstructor;

public class CreateKlass implements Repository {
    private final TypeMap repository = new TypeMap();
    private final String name;
    private final ParamItem[] params;
    private final long start;
    private final long end;

    public CreateKlass(String name, ParamItem[] paramItems, long start, long end) {
        this.name = name;
        this.params = paramItems;
        this.start = start;
        this.end = end;
        Repository.repository.put(name, repository);
    }

    // 클래스의 저장공간에서 값을 가져옴
    public Object get(String name) {
        return repository.find(name);
    }

    public void start() {
        repositoryArray.add(repository);
        try {

        } finally {
            repositoryArray.pop();
        }
    }
}
