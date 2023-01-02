package work.item;

public record TypeListDTO(TypeDTO...types) {
    public int size() {
        return types.length;
    }
}
