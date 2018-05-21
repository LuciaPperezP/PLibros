package libros.BD.dao;

public class Idioma {
    char id_idioma;
    String Nom_idioma;

    public Idioma(char id_idioma, String nom_idioma) {
        this.id_idioma = id_idioma;
        Nom_idioma = nom_idioma;
    }

    public Idioma() {
    }

    public char getId_idioma() {
        return id_idioma;
    }

    public void setId_idioma(char id_idioma) {
        this.id_idioma = id_idioma;
    }

    public String getNom_idioma() {
        return Nom_idioma;
    }

    public void setNom_idioma(String nom_idioma) {
        Nom_idioma = nom_idioma;
    }
}
