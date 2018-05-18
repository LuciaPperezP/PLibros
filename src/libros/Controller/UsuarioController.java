package libros.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UsuarioController implements Initializable
{
    @FXML
    Button btnejemplo;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        btnejemplo.setOnAction(handler);

    }

    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>()
    {

        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource()==btnejemplo)
            {
                try
                {
                    mostrarInfoLibro();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
    };



    public void mostrarInfoLibro() throws IOException
    {
        Stage stage =new Stage();
        FXMLLoader loader;
        Parent parent;


            InfoLibroController infoLibrofx = new InfoLibroController();
            stage.setTitle("Info libro");
            stage.setResizable(false);

        loader = new FXMLLoader(getClass().getResource("../fxml/infoLibro.fxml"));
            loader.setController(infoLibrofx);

        try
        {
            parent= loader.load();
            parent.getStylesheets().add("/libros/css/estiloA.css");
            Scene scene=new Scene(parent,450,600);
            stage.setScene(scene);
            stage.show();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }



    }
}
