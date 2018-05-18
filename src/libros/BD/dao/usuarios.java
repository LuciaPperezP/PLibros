package libros.BD.dao;

import java.sql.Date;

public class usuarios {
    private  String nombre_usuario;
    private String nombre;
    private String email;
    private String contraseña;
    private char tipo_user;

    public usuarios(String nombre_usuario, String email, String contraseña, String tipo_user) {
    }

    public usuarios(String nombre_usuario,String nombre, String email, String contraseña, char tipo_user) {
        this.nombre_usuario=nombre_usuario;
        this.nombre=nombre;
        this.email=email;
        this.contraseña=contraseña;
        this.tipo_user=tipo_user;

    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public char getTipo_user() {
        return tipo_user;
    }

    public void setTipo_user(char tipo_user) {
        this.tipo_user = tipo_user;
    }
}