package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashBoardFormController {


    public AnchorPane DashboardFormContext;
    public AnchorPane DisplayDashboard;
    public AnchorPane AllContext;

    public void btnLogout(ActionEvent actionEvent) throws IOException {

        Stage window = (Stage) DashboardFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        window.centerOnScreen();
    }

    public void btnCustomer(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CustomerForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);
    }

    public void btnHome(ActionEvent actionEvent) throws IOException {
        home();
    }

    public void initialize() throws IOException {
        home();
    }


    public void home() throws IOException {
        URL resource = getClass().getResource("/view/DisplayHome.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);

    }

    public void btnPayment(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/PaymentForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);
    }

    public void btnSupplier(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/SupplierForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);
   }

    public void btnEmployee(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/EmployeeForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);
    }

    public void btnItem(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("/view/ItemForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);
    }

    public void btnOrder(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/OrderForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);
    }

    public void btnReturn(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/ReturnForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);
    }

    public void btnReports(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/ReportForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        AllContext.getChildren().clear();
        AllContext.getChildren().add(load);
    }
}
