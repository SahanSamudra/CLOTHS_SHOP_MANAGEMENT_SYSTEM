package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
/*import model.Accessories;*/
import javafx.scene.layout.AnchorPane;
import model.Supplier;
import tm.SupplierTm;
import util.ValidationUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class SupplierController {
    public JFXTextField txtSId;
    public JFXTextField txtSAddress;
    public JFXTextField txtSName;
    public JFXTextField txtSNic;
    public JFXTextField txtSContact;
    public JFXTextField txtSCompany;
    public TableColumn colSiD;
    public TableColumn colSNic;
    public TableColumn colSName;
    public TableColumn colSAddress;
    public TableColumn colSContact;
    public TableColumn colSCompany;
    public TableView tblSupplier;
    public Button btnSave;
    public TableColumn colSid;
    public TableColumn ColSContact;
    public AnchorPane SupplierFormContext;

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(S)[0-9]{1,3}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,40}$");
    Pattern contactPattern = Pattern.compile("^(071|072|073|074|070|075|076|077|078|079)[-]?[0-9]{6}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{3,30}$");
    Pattern nicPattern = Pattern.compile("^[A-z0-9/ ]{8,11}$");
    Pattern companyPattern = Pattern.compile("^[A-z0-9/ ]{3,30}$");

    private void storeValidations() {
        map.put(txtSId, idPattern);
        map.put(txtSName, namePattern);
        map.put(txtSNic, nicPattern);
        map.put(txtSAddress,addressPattern);
        map.put(txtSContact, contactPattern);
        map.put(txtSCompany, companyPattern);

    }


    public void  initialize(){
        btnSave.setDisable(true);
        storeValidations();



        colSiD.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colSNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSCompany.setCellValueFactory(new PropertyValueFactory<>("company"));

        try {
            loadAllSuppliers(new SupplierSaveController().getAllSupplier());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    public void btnSupplierSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Supplier s1=new Supplier(txtSId.getText(),
                txtSNic.getText(),
                txtSName.getText(),
                txtSAddress.getText(),
                txtSContact.getText(),
                txtSCompany.getText()


        );
        if(new SupplierSaveController().saveSupplier(s1)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Save", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadAllSuppliers(new SupplierSaveController().getAllSupplier());

            }

           /* loadAllSuppliers(new SupplierSaveController().getAllSupplier());

            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added").show();*/
        } else{
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }
    }

    //------------------------------------------------------------------------------------

    public void loadAllSuppliers(ArrayList<Supplier>suppliers){
        ObservableList<SupplierTm> observableList=FXCollections.observableArrayList();
        suppliers.forEach(s->{
            observableList.add(new SupplierTm(s.getSid(),s.getSnic(),s.getName(),s.getAddress(),s.getContact(),s.getCompany()));
            tblSupplier.setItems(observableList);
        });
    }

    public void btnUpdateSupplier(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Supplier s=new Supplier(txtSId.getText(), txtSNic.getText(), txtSName.getText(), txtSContact.getText(),
                txtSAddress.getText(),txtSCompany.getText());



        if (new SupplierSaveController().updateSupplier(s)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Update", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadAllSuppliers(new SupplierSaveController().getAllSupplier());

            }


        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }



    public void btnDeleteSupplier(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (new SupplierSaveController().deleteSupplier(txtSId.getText())) {

            loadAllSuppliers(new SupplierSaveController().getAllSupplier());
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted..").show();
            txtSId.clear();


        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }


    private void setData(Supplier s1) {
        txtSId.setText(s1.getSid());
        txtSNic.setText(s1.getSnic());
        txtSName.setText(s1.getName());
        txtSContact.setText(s1.getContact());
        txtSAddress.setText(s1.getAddress());
        txtSCompany.setText(s1.getCompany());

    }


    public void btnSearchCus(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Supplier S1 = new SupplierSaveController().getSupplier(txtSId.getText());
        if (S1 == null) {

            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(S1);
        }
    }

    public void SupKeyPress(KeyEvent keyEvent) {


        Object response = ValidationUtil.validate(map,btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {


            }
        }
    }



}
