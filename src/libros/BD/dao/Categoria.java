package libros.BD.dao;

public class Categoria {
    int id_cate;
    String Nombre;

    public Categoria(int id_cate, String nombre) {
        this.id_cate = id_cate;
        Nombre = nombre;
    }

    public Categoria() {
    }

    public int getId_cate() {
        return id_cate;
    }

    public void setId_cate(int id_cate) {
        this.id_cate = id_cate;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
