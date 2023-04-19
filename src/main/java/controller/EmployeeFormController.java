package controller;

import DB.DbConnection;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.Customer;
import model.Employee;
import tm.EmployeeTm;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {
    public Button btnSave;
    public JFXTextField txtENIC;
    public JFXTextField txtEAddress;
    public JFXTextField txtEContact;
    public JFXTextField txtEId;
    public JFXTextField txtSallary;
    public JFXTextField txtEName;
    public TableView <EmployeeTm>tblEmployee;
    public TableColumn colEId;
    public TableColumn colEName;
    public TableColumn colENic;
    public TableColumn colEAddress;
    public TableColumn colESallary;
    public TableColumn colEContact;
    public Button btnSaveEmployee;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblEmployee.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("empId"));
        tblEmployee.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblEmployee.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("NIC"));
        tblEmployee.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblEmployee.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("salary"));
        tblEmployee.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("contact"));

        getAllCustomer();

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtEId.setText(newValue.getEmpId());
            txtEName.setText(newValue.getName());
            txtENIC.setText(newValue.getNIC());
            txtEAddress.setText(newValue.getAddress());
            txtSallary.setText(String.valueOf(newValue.getSalary()));
            txtEContact.setText(newValue.getContact());
        });
    }

    private void getAllCustomer() {
        ArrayList<Employee> employees = new ArrayList<>();
        ObservableList<EmployeeTm>  employeeTms = FXCollections.observableArrayList();
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Employee");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6)));

            }
            for (Employee employee : employees) {
                employeeTms.add(new EmployeeTm(employee.getEmpId(), employee.getName(),employee.getNIC(),employee.getAddress(),employee.getSalary(),employee.getContact()));
            }
            tblEmployee.setItems(employeeTms);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnSaveEmployee(ActionEvent actionEvent) {
        Employee employee = new Employee(txtEId.getText(),txtEName.getText(),txtENIC.getText(),txtEAddress.getText(),Double.parseDouble(txtSallary.getText()),txtEContact.getText());
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Employee " + " VALUES (?,?,?,?,?,?)");
            preparedStatement.setObject(1,employee.getEmpId());
            preparedStatement.setObject(2,employee.getName());
            preparedStatement.setObject(3,employee.getNIC());
            preparedStatement.setObject(4,employee.getAddress());
            preparedStatement.setObject(5,employee.getSalary());
            preparedStatement.setObject(6,employee.getContact());
            int add = preparedStatement.executeUpdate();
            if (add > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again", ButtonType.OK).show();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clearText();
    }

    public void SupKeyPress(KeyEvent keyEvent) {
    }

    public void btnUpdateEmployee(ActionEvent actionEvent) {
        Employee employee = new Employee(txtEId.getText(),txtEName.getText(),txtENIC.getText(),txtEAddress.getText(),Double.parseDouble(txtSallary.getText()),txtEContact.getText());
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Employee SET " + "eName=?,NIC=?,eAddress=?,salary=?,eContact=? WHERE eId = ?");
            preparedStatement.setObject(1,employee.getName());
            preparedStatement.setObject(2,employee.getNIC());
            preparedStatement.setObject(3,employee.getAddress());
            preparedStatement.setObject(4,employee.getSalary());
            preparedStatement.setObject(5,employee.getContact());
            preparedStatement.setObject(6,employee.getEmpId());
            int update = preparedStatement.executeUpdate();
            if (update > 0) {

                new Alert(Alert.AlertType.CONFIRMATION, "Updated", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again", ButtonType.OK).show();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clearText();
    }

    public void btnDeleteEmployee(ActionEvent actionEvent) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Employee WHERE eId=? ");
            preparedStatement.setObject(1, txtEId.getText());
            int delete = preparedStatement.executeUpdate();
            if (delete > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again", ButtonType.OK).show();
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clearText();
    }

    public void btnSearch(ActionEvent actionEvent) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Employee WHERE eId = ?");
            preparedStatement.setObject(1, txtEId.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                txtEName.setText(resultSet.getString(2));
                txtENIC.setText(resultSet.getString(3));
                txtEAddress.setText(resultSet.getString(4));
                txtSallary.setText(resultSet.getString(5));
                txtEContact.setText(resultSet.getString(6));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearText() {
        txtEId.clear();
        txtEName.clear();
        txtENIC.clear();
        txtEAddress.clear();
        txtSallary.clear();
        txtEContact.clear();


    }





}