package libros.BD.dao;

import javafx.scene.image.Image;

import java.util.Date;


public class Libros {
    int idbook,id_cate,id_sub,paginas,id_autor;
    String titulo,editor,año,contenido,etiquetas,url,licencia,descarga;
    Date fecha_public;
    float tamaño;
    Image imagen;

    public Libros() {
    }

    public int getIdbook() {
        return idbook;
    }

    public void setIdbook(int idbook) {
        this.idbook = idbook;
    }

    public int getId_cate() {
        return id_cate;
    }

    public void setId_cate(int id_cate) {
        this.id_cate = id_cate;
    }

    public int getId_sub() {
        return id_sub;
    }

    public void setId_sub(int id_sub) {
        this.id_sub = id_sub;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getDescarga() {
        return descarga;
    }

    public void setDescarga(String descarga) {
        this.descarga = descarga;
    }

    public Date getFecha_public() {
        return fecha_public;
    }

    public void setFecha_public(Date fecha_public) {
        this.fecha_public = fecha_public;
    }

    public float getTamaño() {
        return tamaño;
    }

    public void setTamaño(float tamaño) {
        this.tamaño = tamaño;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public Libros(int idbook, int id_cate, int id_sub, int paginas, String titulo, String editor, String año, String contenido,
                  String etiquetas, String url, String licencia, String descarga, Date fecha_public, float tamaño, Image imagen) {
        this.idbook = idbook;
        this.id_cate = id_cate;
        this.id_sub = id_sub;
        this.paginas = paginas;
        this.titulo = titulo;
        this.editor = editor;
        this.año = año;
        this.contenido = contenido;
        this.etiquetas = etiquetas;
        this.url = url;
        this.licencia = licencia;
        this.descarga = descarga;
        this.fecha_public = fecha_public;
        this.tamaño = tamaño;
        this.imagen = imagen;

    }

}
