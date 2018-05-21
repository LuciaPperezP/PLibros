package libros.BD.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibrosDAO {
        Connection conn;
        public LibrosDAO(Connection conn)
        {
            this.conn = conn;
        }

        public boolean Insert(String ruta,Libros lib){
            String query = "INSERT INTO libros(titulo,editor,año,paginas,contenido,fecha_public,tamaño,\n" +
                    "imagen,etiquetas,url,licencia,descarga,id_cate,id_sub) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            FileInputStream fis = null;
            PreparedStatement ps = null;
            try {
                conn.setAutoCommit(false);
                File file = new File(ruta);
                fis = new FileInputStream(file);
                ps = conn.prepareStatement(query);
                ps.setString(1,lib.getTitulo());
                ps.setString(2,lib.getEditor());
                ps.setString(3,lib.getAño());
                ps.setInt(4,lib.getPaginas());
                ps.setString(5,lib.getContenido());
                ps.setDate(6,(Date)lib.getFecha_public());
                ps.setFloat(7,lib.getTamaño());
                ps.setBinaryStream(8,fis,(int)file.length());
                ps.setString(9,lib.getEtiquetas());
                ps.setString(10,lib.getUrl());
                ps.setString(11,lib.getLicencia());
                ps.setString(12,lib.getDescarga());
                ps.setInt(13,lib.getId_cate());
                ps.setInt(14,lib.getId_sub());
                ps.execute();
                conn.commit();
                return true;

            } catch (Exception ex) {
               // Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    ps.close();
                    fis.close();
                } catch (Exception ex) {
                   // Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return false;
        }

    public boolean DisponibleIdioma(idiomDisponible disponible){
       try {
                String query = "insert into idiomDisponible(id_book,id_idiom)"
                        + " values (?,?)";
                PreparedStatement st =  conn.prepareStatement(query);
                st.setInt(1, disponible.getId_book());
                st.setString(2, String.valueOf(disponible.getId_idioma()));
                st.execute();

           return true;

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

            return false;
        }

        //************+Seleccionar los libros**********************+
        public Libros obtenidLibros(String tittle) {
            // ImageView imageView;
            List<Libros> transactions = new ArrayList<Libros>();
            Libros libros = null;
            try {
                int idbook,id_cate,id_sub,paginas;
                String titulo,editor,año,contenido,etiquetas,url,licencia,descarga;
                java.util.Date fecha_public;
                float tamaño;
                Blob blop;
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM libros where titulo='"+tittle+"';");
                while (rs.next())
                {
                    libros=new Libros();
                    idbook=rs.getInt("idbook");
                    titulo=rs.getString("titulo");
                    editor=rs.getString("editor");
                    año=rs.getString("año");
                    paginas=rs.getInt("paginas");
                    contenido=rs.getString("contenido");
                    fecha_public=rs.getDate("fecha_public");
                    tamaño=rs.getFloat("tamaño");
                    Blob blob = rs.getBlob("imagen");
                    etiquetas=rs.getString("etiquetas");
                    url=rs.getString("url");
                    licencia=rs.getString("licencia");
                    descarga=rs.getString("descarga");
                    id_cate=rs.getInt("id_cate");
                    id_sub=rs.getInt("id_sub");


                    byte byteImage[] ;
                    byteImage = blob.getBytes(1, (int) blob.length());
                    Image img = new Image(new ByteArrayInputStream(byteImage));
                    libros.setIdbook(idbook);
                    libros.setTitulo(titulo);
                    libros.setEditor(editor);
                    libros.setAño(año);
                    libros.setPaginas(paginas);
                    libros.setContenido(contenido);
                    libros.setFecha_public(fecha_public);
                    libros.setTamaño(tamaño);
                    libros.setImagen(img);
                    libros.setEtiquetas(etiquetas);
                    libros.setUrl(url);
                    libros.setLicencia(licencia);
                    libros.setDescarga(descarga);
                    libros.setId_cate(id_cate);
                    libros.setId_sub(id_sub);
                    transactions.add(libros);
                }
                rs.close();
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(LibrosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return libros;
        }



        public ObservableList<Idioma> Idiomas (){
            String idioma = "";
            ObservableList<Idioma> transactions = FXCollections.observableArrayList();
            Idioma p=null;
            try {
                String query = "select * from idioma";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    //Mientras haya resultados obtengo el valor.
                    p=new Idioma(rs.getString("id").charAt(0),
                            rs.getString("nombre"));
                    transactions.add(p);
                    System.out.println(p.Nom_idioma);
                }
                rs.close();
                st.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Error al recuperar información...");
            }
            return transactions;
        }


        public  ObservableList<Categoria> Categorias (){
            String categoria = "";
            ObservableList<Categoria> consultaCate = FXCollections.observableArrayList();
            Categoria p=null;
            try {
                String query = "select * from categoria";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    //Mientras haya resultados obteng1o el valor.
                    p=new Categoria(rs.getInt("id_cate"),
                            rs.getString("Nombre"));
                    consultaCate.add(p);
                    System.out.println(p.Nombre);
                }
                rs.close();
                st.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Error al recuperar información...");
            }
            return consultaCate;
        }

        public  ObservableList<Subcategoria> Subcategorias (String subcatName){
            String categoria = "";
            ObservableList<Subcategoria> consultaCate = FXCollections.observableArrayList();
            Subcategoria p=null;
            try {
                String query = "select * from subcategoria where id_cat in (select id_cate from categoria where Nombre='"+subcatName+"');";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    //Mientras haya resultados obteng1o el valor.
                    p=new Subcategoria(rs.getInt("id_sub"),rs.getInt("id_cat"),
                            rs.getString("Nombre"));
                    consultaCate.add(p);
                    System.out.println(p.Nombre_sub);
                }
                rs.close();
                st.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Error al recuperar información...");
            }
            return consultaCate;
        }
}
