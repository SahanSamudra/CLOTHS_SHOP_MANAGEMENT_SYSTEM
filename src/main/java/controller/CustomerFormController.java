package controller;

import DB.DbConnection;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Customer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import tm.CustomerTm;
import util.ValidationUtil;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class CustomerFormController {
    public TableView tblCustomers;
    public TableColumn colCustomerId;
    public TableColumn colCusName;
    public TableColumn colCusContact;
    public TableColumn colCusAddress;
    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;

    public JFXTextField txtCustomerId;
    public Button btnSave;
    public JFXTextField txtCusContact;


    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(C)[0-9]{1,3}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,40}$");
    Pattern contactPattern = Pattern.compile("^(071|072|073|074|070|075|076|077|078|079)[-]?[0-9]{7}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{3,50}$");

    private void storeValidations() {
        map.put(txtCustomerId, idPattern);
        map.put(txtCusName, namePattern);
        map.put(txtCusContact, contactPattern);
        map.put(txtCusAddress,addressPattern);
    }

    public void initialize() {
        btnSave.setDisable(true);
        storeValidations();

        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colCusContact.setCellValueFactory(new PropertyValueFactory<>("noOfContact"));
        colCusAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));


        try {
            loadCustomers(new CustomerSaveController().getAllCustomer());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnCustomerSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Customer c1 = new Customer(



                txtCustomerId.getText(),
                txtCusName.getText(),
                txtCusContact.getText(),
                txtCusAddress.getText()


        );
        if (new CustomerSaveController().saveCustomer(c1)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Save", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadCustomers(new CustomerSaveController().getAllCustomer());

            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void loadCustomers(ArrayList<Customer> customers) {

        ObservableList<CustomerTm> observableList = FXCollections.observableArrayList();
        customers.forEach(c -> {

            observableList.add(new CustomerTm(c.getCustomerId(), c.getCustomerName(), c.getCustomerAddress(), c.getNoOfContact()));
            tblCustomers.setItems(observableList);

        });

    }

    public void btnCustomerUpdate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Customer c = new Customer(txtCustomerId.getText(), txtCusName.getText(), txtCusContact.getText(), txtCusAddress.getText());


        if (new CustomerSaveController().updateCustomer(c)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Update", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadCustomers(new CustomerSaveController().getAllCustomer());

            }


        } else {
            loadCustomers(new CustomerSaveController().getAllCustomer());
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void btnCustomerDelete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        if (new CustomerSaveController().deleteCustomer(txtCustomerId.getText())) {

            loadCustomers(new CustomerSaveController().getAllCustomer());
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted..").show();
            txtCustomerId.clear();


        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void CustomerKeyPress(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {

        if (keyEvent.getCode() == KeyCode.ENTER) {
            String cid = txtCustomerId.getText();

            Customer C1 = new CustomerSaveController().getCustomer(txtCustomerId.getText());
            if (C1 == null) {

                new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
            } else {
                setData(C1);
            }
        }
    }

    private void setData(Customer c1) {
        txtCustomerId.setText(c1.getCustomerId());
        txtCusName.setText(c1.getCustomerName());
        txtCusContact.setText(c1.getNoOfContact());
        txtCusAddress.setText(c1.getCustomerAddress());


    }

    public void txtKeyPress(KeyEvent keyEvent) {

        Object response = ValidationUtil.validate(map,btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }

    }

    public void btnSearch(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer C1 = new CustomerSaveController().getCustomer(txtCustomerId.getText());
        if (C1 == null) {

            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(C1);
        }


    }

    public void btnPrint(ActionEvent actionEvent) {
        new Thread() {
            @Override
            public void run() {
                try {
                    JasperDesign load = JRXmlLoader.load(new File("D:\\IJSE\\CLOTHS_SHOP_MANAGEMENT_SYSTEM\\src\\main\\java\\invoice\\customer_all_form.jrxml"));

                    JasperReport js = JasperCompileManager.compileReport(load);

                    JasperPrint jp = JasperFillManager.fillReport(js, null, DbConnection.getInstance().getConnection());

                    JasperViewer viewer = new JasperViewer(jp , false);
                    viewer.show();

                } catch (JRException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                this.stop();
            }
  }.start();
    }
}
