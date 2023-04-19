package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public Label lblError;
    public JFXPasswordField Password;

    public AnchorPane LoginFormContext;

    public JFXTextField UserName;

    public void btnLogin(ActionEvent actionEvent) throws IOException {


        LoginFormManager();

    }
    public void LoginFormManager() throws IOException {
        String user = "U";
        String password = "1";
        if (UserName.getText().equals(user) && Password.getText().equals(password)) {
            Stage window = (Stage) LoginFormContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
            window.centerOnScreen();
        } else if (UserName.getText().isEmpty() && Password.getText().isEmpty()) {
            lblError.setText("Your User Name Or Password IS Empty...!");
            UserName.clear();
            Password.clear();
        }
        else if (!UserName.getText().equals(user)) {
            lblError.setText("Your User Name is incorrect..!");
            UserName.clear();
            Password.clear();
        } else if (!Password.getText().equals(password)) {
            lblError.setText("Your Password is incorrect..!");
            UserName.clear();
            Password.clear();
        }
    }
}
