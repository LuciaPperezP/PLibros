package libros.BD.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AutorDAO {

    Connection conn;
    public AutorDAO(Connection conn)
    {
        this.conn = conn;
    }


    public Boolean insertAutor(Autor autor) {
        try {
            String query = "insert into autores (Nombre) values (?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, autor.getNombre());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }
//Inserta en la tabla escribe relación entre autor y libro
    public boolean EscribeLibro(Escribe escribe){
        try {
            String query = "insert into  escribe(id_autor,id_book)"
                    + " values (?,?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(1, escribe.getId_autor());
            st.setString(2, String.valueOf(escribe.getId_libro()));
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Autor buscaAutor(String nombre) {
        List<Autor> transactions = new ArrayList<Autor>();
        Autor p = null;
        try {
            String query = "SELECT * FROM autores where Nombre='"+nombre+"';";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                p = new Autor(
                        rs.getInt("id_autor"),
                        rs.getString("Nombre"));
                transactions.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return p;
    }
}
