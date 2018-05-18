package libros;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/sample.fxml"));
        primaryStage.setTitle("Libros");
        root.getStylesheets().add("/libros/estilo1.css");
        primaryStage.setScene(new Scene(root, 390, 590));
        primaryStage.setResizable(false);
        primaryStage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }
}
