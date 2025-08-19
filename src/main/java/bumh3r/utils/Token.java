package bumh3r.utils;

public record Token(
        int num,
        String value,
        String categoria,
        int linea
) {
    @Override
    public String toString() {
        return String.format("Token #%d Texto: \'%s\' Categoría: \"%s\" Línea: %d\n",num,value,categoria,linea);
    }
}
