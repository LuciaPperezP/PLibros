package libros.BD.dao;

import libros.BD.dao.usuarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsuariosDAO {


    Connection conn;

    public UsuariosDAO(Connection conn) {
        this.conn = conn;
    }


    public char validarUsuario(String pusuario, String ppassword) {
        try {
            String query = "SELECT * FROM usuarios WHERE nombre_usuario='" + pusuario + "' AND contraseña=('" + ppassword + "')";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                char tipo = rs.getString("tipo_user").charAt(0);
                return tipo;
            }
            else
                return 'I';
        } catch (Exception e)
        {
            e.printStackTrace();
            return 'I';
        }
    }

    public Boolean insertarUsuario(usuarios insertUsuario) {
        try {
            String query = "insert into usuarios "
                    + " (nombre, email, nombre_usuario, contraseña, tipo_user)"
                    + " values (?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, insertUsuario.getNombre());
            st.setString(2, insertUsuario.getEmail());
            st.setString(  3, insertUsuario.getNombre_usuario());
            st.setString(4,insertUsuario.getContraseña());
            st.setString(5, String.valueOf(insertUsuario.getTipo_user()));
            st.execute();
            //data.add(transaction);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }


}
