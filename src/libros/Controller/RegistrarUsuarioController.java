package libros.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import libros.BD.dao.MySQL;
import libros.BD.dao.UsuariosDAO;
import libros.BD.dao.usuarios;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class RegistrarUsuarioController implements Initializable {
    @FXML
    RadioButton rbUsuario, rbAdmin;
    @FXML
    Label lbTipo, lbEstadoContraseña;
    @FXML
    Button btnRegistrar, btnCancelar;
    @FXML
    TextField txtNombre,txtEmail,txtUsuario;
   @FXML
   PasswordField txtContraseña, txtConfirmarContraseña;

    int tipo = 0;

    private UsuariosDAO usuariosDAO = new UsuariosDAO(MySQL.getConnection());
    usuarios objUsuarios;


    public RegistrarUsuarioController(int tipoVentana)//constructor
    {
        this.tipo = tipoVentana;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbEstadoContraseña.setVisible(false);

        if (tipo == 1) {
            rbUsuario.setVisible(false);
            rbAdmin.setVisible(false);
            lbTipo.setVisible(false);
        }
        btnRegistrar.setOnAction(handler);
        btnCancelar.setOnAction(handler);


    }

    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            if(event.getSource()==btnRegistrar)
            {
                String cont1=txtContraseña.getText();
                String cont2=txtConfirmarContraseña.getText();

                if(cont1.equals(cont2))//valida que las contraseñas coincidan
                {
                    registrarUsuario();
                }
                else
                    {
                        lbEstadoContraseña.setVisible(true);
                    }
            }

            else if(event.getSource()==btnCancelar)
            {
                cerrarVentana();

            }

        }
    };

    private void registrarUsuario()
    {
        try
        {
            usuarios objUsuarios = new usuarios("","","","",'D');
            objUsuarios.setNombre(txtNombre.getText());
            objUsuarios.setEmail(txtEmail.getText());
            objUsuarios.setNombre_usuario(txtUsuario.getText());
            objUsuarios.setContraseña(txtContraseña.getText());
            if(rbAdmin.isSelected())
            {
                objUsuarios.setTipo_user('A');
            }
            else if(rbUsuario.isSelected()||tipo==1)
            {
                objUsuarios.setTipo_user('U');
            }

            usuariosDAO.insertarUsuario(objUsuarios);//llamar consulta para insertar nuevo registro
            showMessage("Registro insertado");
            cerrarVentana();
        }
        catch (Exception e)
        {
            showMessage("Error en el registro");
        }
    }


    public void showMessage(String mensaje)
    {
        Alert alert1=new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Mensaje de notificación");
        alert1.setContentText(mensaje);
        alert1.show();
    }

    private void cerrarVentana()
    {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

}

