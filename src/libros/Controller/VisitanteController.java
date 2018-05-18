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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VisitanteController implements Initializable
{
    @FXML
    Button btnRegistrarse;
    int tipoVentana=0;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnRegistrarse.setOnAction(handler);

    }

    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() //progamar evento de los botones
    {
        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource()==btnRegistrarse)
            {
                tipoVentana=1;
                mostrarVentanaRegistro(tipoVentana);
            }

        }
    };

    public void mostrarVentanaRegistro(int tipoVentana)
    {
        Stage stage =new Stage();
        FXMLLoader loader;
        Parent parent;

        RegistrarUsuarioController registrarUsuariofx = new RegistrarUsuarioController(tipoVentana);
        stage.setTitle("Registro");
        stage.setResizable(false);

        loader = new FXMLLoader(getClass().getResource("../fxml/registrarUsuario.fxml"));
        loader.setController(registrarUsuariofx);

        try
        {
            parent= loader.load();
            parent.getStylesheets().add("/libros/css/estiloA.css");
            Scene scene=new Scene(parent,400,450);
            stage.setScene(scene);
            stage.show();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
