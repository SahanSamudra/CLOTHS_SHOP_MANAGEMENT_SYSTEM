package controller;

import DB.DbConnection;
import com.beust.ah.A;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import service.QrPerformance;
import tm.AttendenceTM;
import tm.CustomerTm;
import tm.EmployeeTm;
import to.AttendanceTo;
import to.EmployeeTo;
import to.WorkingDayTo;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable, QrPerformance {
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
    public AnchorPane EmployeeAttendanceContext;
    public TableView tblAttendance;
    public TableColumn colEmployeeName;
    public TableColumn colStatus;
    public Label lblEmployee;
    public TableView<AttendenceTM> tblAttendence;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getAllCustomer();
        setTable();addWorkingDay();
        tblEmployee.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("empId"));
        tblEmployee.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblEmployee.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("NIC"));
        tblEmployee.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblEmployee.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("salary"));
        tblEmployee.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("contact"));



        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtEId.setText(newValue.getEmpId());
            txtEName.setText(newValue.getName());
            txtENIC.setText(newValue.getNIC());
            txtEAddress.setText(newValue.getAddress());
            txtSallary.setText(String.valueOf(newValue.getSalary()));
            txtEContact.setText(newValue.getContact());
        });
    }



    public void addWorkingDay(){
        try {
            boolean b = false;
            WorkingDayTo workingDay = WorkingDayModel.getWorkingDay(Date.valueOf(LocalDate.now()));
            if(workingDay==null){
                String id = WorkingDayModel.getNewId();
                ArrayList<Employee> allEmployee = getAllCustomer();
                ArrayList<AttendanceTo> attendance = new ArrayList<>();
                for (Employee employee : allEmployee){
                    attendance.add(new AttendanceTo(employee.getEmpId(),id, Time.valueOf(LocalTime.now()),"N"));
                }
                b= WorkingDayModel.addWorkingDay(attendance);
            }
            setTable();
            System.out.println("Already Added "+!b);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void setTable(){
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<AttendenceTM,String >("employeeName"));
        colStatus.setCellValueFactory(new PropertyValueFactory<AttendenceTM,String>("status"));
        try {
            //ArrayList<AttendanceTo> at = AttendanceModel.getAttendanceByDay(WorkingDayModel.getWorkingDay(Date.valueOf(LocalDate.now())).getId());
            WorkingDayTo workingDay = WorkingDayModel.getWorkingDay(Date.valueOf(LocalDate.now()));
            if(workingDay==null)return;
            ArrayList<AttendenceTM> at = AttendanceModel.getAllAttendance(workingDay.getId());

            tblAttendence.setItems(FXCollections.observableArrayList(at));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnEmployeeAttendance(ActionEvent actionEvent) {

        //PigCageContext.setTranslateX(1700);

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.2));
        slide.setNode(EmployeeAttendanceContext);

        slide.setToX(0);
        slide.play();

        EmployeeAttendanceContext.setTranslateX(-176);
        slide.setOnFinished((ActionEvent e)-> {

        });
    }



    public void loadCustomers(ArrayList<Employee> customers) {
        ObservableList<EmployeeTm> observableList = FXCollections.observableArrayList();
        customers.forEach(c -> {
            observableList.add(new EmployeeTm(c.getEmpId(), c.getName(), c.getNIC(), c.getAddress(),c.getSalary(),c.getContact()));
            tblEmployee.setItems(observableList);
        });

    }
    private ArrayList<Employee> getAllCustomer() {
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
            return employees;
    }

    public void btnSaveEmployee(ActionEvent actionEvent) {
        Employee employee = new Employee(txtEId.getText(), txtEName.getText(), txtENIC.getText(), txtEAddress.getText(), Double.parseDouble(txtSallary.getText()), txtEContact.getText());
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Employee " + " VALUES (?,?,?,?,?,?)");
            preparedStatement.setObject(1, employee.getEmpId());
            preparedStatement.setObject(2, employee.getName());
            preparedStatement.setObject(3, employee.getNIC());
            preparedStatement.setObject(4, employee.getAddress());
            preparedStatement.setObject(5, employee.getSalary());
            preparedStatement.setObject(6, employee.getContact());
            int add = preparedStatement.executeUpdate();
            if (add > 0) {
               new Alert(Alert.AlertType.CONFIRMATION, "Saved", ButtonType.OK).show();
                getAllCustomer();

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
                getAllCustomer();
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
                getAllCustomer();
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


    public void btnEAttendance(ActionEvent actionEvent) {

    }

    public void btnQrOnACtion(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("/view/QrScannerForm.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        Parent load = loader.load();
        QrScannerFormController controller = loader.getController();
        controller.setController(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }

    @Override
    public void qrIdRequestAction(String id) {
        System.out.println(id+"Send");
        ObservableList<AttendenceTM> items = tblAttendence.getItems();
        for (AttendenceTM ob : items){
            if(ob.getEmployeeId().equals(id)){
                ob.setStatus("Y");
            }
        }
        tblAttendence.refresh();
    }

    @Override
    public String getDetail(String id) {
        System.out.println(id+" requested");
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Employee WHERE eId = ?");
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Searching");
            while (resultSet.next()) {
                System.out.println("Found");
                return resultSet.getString(1);
            }
            System.out.println("Not Found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ObservableList<AttendenceTM> items = tblAttendence.getItems();
        ArrayList<AttendanceTo> list = new ArrayList<>();
        WorkingDayTo workingDay = WorkingDayModel.getWorkingDay(Date.valueOf(LocalDate.now()));

        for (AttendenceTM ob : items){
            list.add(new AttendanceTo(ob.getEmployeeId(),workingDay.getId(),Time.valueOf(LocalTime.now()),ob.getStatus()));
        }
        boolean b = AttendanceModel.updateAttendance(list);
        if(b){
            new Alert(Alert.AlertType.INFORMATION,"Success :)").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Failed").show();
        }
    }
}