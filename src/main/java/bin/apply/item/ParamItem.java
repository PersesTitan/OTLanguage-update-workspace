package bin.apply.item;

import bin.apply.Repository;

public record ParamItem(String type, String name) {
    public Object casting(String value) {
        return Repository.klassItems.get(type).casting().apply(value);
    }
}
