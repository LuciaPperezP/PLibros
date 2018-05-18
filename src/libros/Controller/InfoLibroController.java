package libros.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoLibroController implements Initializable {


    @FXML
    Button btnOk, btnVer, btnDescargar;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnOk.setOnAction(handler);
        btnDescargar.setOnAction(handler);
        btnVer.setOnAction(handler);
    }


    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>()
    {

        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource()==btnOk)
            {
                System.out.println("cerrar ventana");
            }
            else if(event.getSource()==btnVer)
            {
                System.out.println("visualizar libro");
            }
            else if(event.getSource()==btnDescargar)
            {
                System.out.println("abrir link descarga");
            }

        }
    };

}