package libros.BD.dao;

public class Subcategoria {
  int  id_sub,id_cat;
    String Nombre_sub;

    public Subcategoria(int id_sub, int id_cat, String nombre_sub) {
        this.id_sub = id_sub;
        this.id_cat = id_cat;
        Nombre_sub = nombre_sub;
    }

    public Subcategoria() {
    }

    public int getId_sub() {
        return id_sub;
    }

    public void setId_sub(int id_sub) {
        this.id_sub = id_sub;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getNombre_sub() {
        return Nombre_sub;
    }

    public void setNombre_sub(String nombre_sub) {
        Nombre_sub = nombre_sub;
    }
}
