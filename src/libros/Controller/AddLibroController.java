package libros.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import libros.BD.dao.*;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddLibroController implements Initializable

    {

        @FXML
        Button btnSeleccionar, BtnSave, BtnCancel;
        @FXML
        TextField txtURLCompar, txtURLDescarga, txtEtiq, txtTitulo, txtAutor, txtAño, txtEditor, txtPag, txtTam;
        @FXML
        ComboBox<Idioma> cbIdioma;
        @FXML
        ComboBox<String> cbLicencia;
        @FXML
        ComboBox<Categoria> cbCate;
        @FXML
        ComboBox<Subcategoria> cbSubcate;
        @FXML
        DatePicker dpDate;
        @FXML
        TextArea txaContenido;
        @FXML
        Label lblruta;
        @FXML
        ImageView Imgn;
        String categ_name;
        String licence;
        char id_idiom;
        int id_cate, id_sub;
        LibrosDAO transactionDAO = new LibrosDAO(MySQL.getConnection());//Consulta para libros
        AutorDAO autorDAO=new AutorDAO(MySQL.getConnection());//Consulta para autores

        public void initialize(URL location, ResourceBundle resources) {
        llenaCBidioma();
        llenaLicencia();
        llenaCBCategoria();
        cbSubcate.setDisable(true);
        btnSeleccionar.setOnAction(handler);
        BtnSave.setOnAction(handler);
        BtnCancel.setOnAction(handler);
        cbCate.setOnAction(handler);
        cbLicencia.setOnAction(handler);
        cbIdioma.setOnAction(handler);

    }


        EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert;

                if (event.getSource() == btnSeleccionar) {
                    seleccionar_pdf();
                } else if (BtnSave == event.getSource()) {
                    Libros libros = new Libros();
                    libros.setTitulo(txtTitulo.getText());
                    libros.setAño(txtAño.getText());
                    libros.setEditor(txtEditor.getText());
                    libros.setPaginas(Integer.valueOf(txtPag.getText()));
                    LocalDate localDate = dpDate.getValue();
                    libros.setFecha_public(Date.valueOf(localDate));
                    libros.setTamaño(Float.valueOf(txtTam.getText()));
                    libros.setLicencia(licence);
                    libros.setContenido(txaContenido.getText());

                    if (cbSubcate.getSelectionModel().getSelectedIndex() > -1)
                    {
                        id_sub = cbSubcate.getValue().getId_sub();
                        id_cate=cbSubcate.getValue().getId_cat();
                    }
                    libros.setId_cate(id_cate);
                    libros.setId_sub(id_sub);
                    System.out.println(id_sub);
                    //Inserta libro
                    transactionDAO.Insert(lblruta.getText(), libros);

                    System.out.println("Titulo: " + txtTitulo.getText());
                    //botiene el id del libro
                    int id_libro=transactionDAO.obtenidLibros(txtTitulo.getText()).getIdbook();
                    System.out.println("Libro agregado: "+ id_libro);
                    //El id del libro se utiliza para relacionar el autor en y ell libro en la tabla escribe
                    AddAutor(id_libro);
                    //El id del libro se usa para udentificar en que idiomas está disponible
                    //idiomaDisponible(id_libro);


                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Información");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Libro registrado");

                    alert1.showAndWait();
                    closeStage();


                } else if (BtnCancel == event.getSource()) {
                    closeStage();
                } else if (cbCate.getSelectionModel().getSelectedIndex() > -1) {
                    //Obtiene el id de la categoría seleccionada
                    categ_name = cbCate.getValue().getNombre();
                    //El Combobox de subcategoría se habilita
                    cbSubcate.setDisable(false);
                    //Llena con los nombres de las subcategoría según la categoría seleccionada
                    llenaCBSubcategoria(categ_name);
                }
                else if (cbIdioma.getSelectionModel().getSelectedIndex() > -1) {
                    //Obtiene el id del idioma seleccionado
                    id_idiom = cbIdioma.getValue().getId_idioma();
                } else if (cbLicencia.getSelectionModel().getSelectedIndex() > -1) {
                    //obtiene la licencia seleccionada
                    licence = cbLicencia.getValue();
                }

            }
        };

        public void idiomaDisponible(int idlibro){

            idiomDisponible disponible=new idiomDisponible();

            //Obtiene el id del libro
            disponible.setId_book(idlibro);
            System.out.println("id_libro: "+idlibro);

            //Obtiene el id del idioma
            disponible.setId_idioma(id_idiom);
            System.out.println("id_idioma: "+id_idiom);

            //Con los id´s
            transactionDAO.DisponibleIdioma(disponible);
        }

        public void AddAutor(int idlibro){
            Autor autor=new Autor();
            Escribe escribe=new Escribe();

            if(autorDAO.buscaAutor(txtAutor.getText())!=null)
            {
                escribe.setId_libro(idlibro);
                int autor_id = autorDAO.buscaAutor(txtAutor.getText()).getId_autor();
                System.out.println("Autor existente: " + autor_id);
                escribe.setId_autor(autor_id);

                autorDAO.EscribeLibro(escribe);
            }
            else
            {
                autor.setNombre(txtAutor.getText());
                System.out.println("Nombre autor"+autor.getNombre());


                if (autorDAO.insertAutor(autor)) {
                    escribe.setId_libro(idlibro);
                    System.out.println("idlibro"+escribe.getId_libro());
                    int autor_id = autorDAO.buscaAutor(txtAutor.getText()).getId_autor();
                    System.out.println("Autor: " + txtAutor.getText());
                    escribe.setId_autor(autor_id);
                    System.out.println("id_autor nuevo: "+autor_id);
                    autorDAO.EscribeLibro(escribe);
                } else {
                    System.out.println("Error al guardar autor...");
                }
            }
        }

        public void llenaCBidioma() {
        cbIdioma.setItems(transactionDAO.Idiomas());
        //Convierte ComboBox toString a StringConverter
        cbIdioma.setConverter(new StringConverter<Idioma>() {

            @Override
            public String toString(Idioma p) {
                return (p.getNom_idioma());
            }

            @Override
            public Idioma fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

        public void llenaLicencia() {

        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Libre", "Prueba", "Compra");
        cbLicencia.setItems(items);
    }

        public void llenaCBCategoria() {
        cbCate.setItems(transactionDAO.Categorias());
        //Convierte ComboBox toString a StringConverter
        cbCate.setConverter(new StringConverter<Categoria>() {

            @Override
            public String toString(Categoria p) {
                return (p.getNombre());
            }

            @Override
            public Categoria fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }


        public void llenaCBSubcategoria(String subcatName) {
        cbSubcate.setItems(transactionDAO.Subcategorias(subcatName));
        //Convierte ComboBox toString a StringConverter
        cbSubcate.setConverter(new StringConverter<Subcategoria>() {

            @Override
            public String toString(Subcategoria p) {
                return (p.getNombre_sub());
            }

            @Override
            public Subcategoria fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }


        private void closeStage() {
        Stage stage = (Stage) BtnCancel.getScene().getWindow();
        stage.close();
    }

        public void seleccionar_pdf() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(null);

        // Mostar la imagen
        if (imgFile != null) {
            System.out.println("file:" + imgFile.getAbsolutePath());
            lblruta.setText(imgFile.getAbsolutePath());
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            Imgn.setImage(image);
            Imgn.setFitHeight(19);
            Imgn.setFitWidth(25);

        }

    }
}
