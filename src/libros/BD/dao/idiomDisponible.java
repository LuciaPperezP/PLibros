package libros.BD.dao;

public class idiomDisponible {
    int id_book;
    char id_idioma;

    public idiomDisponible() {
    }

    public idiomDisponible(int id_book, char id_idioma) {
        this.id_book = id_book;
        this.id_idioma = id_idioma;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public char getId_idioma() {
        return id_idioma;
    }

    public void setId_idioma(char id_idioma) {
        this.id_idioma = id_idioma;
    }
}
