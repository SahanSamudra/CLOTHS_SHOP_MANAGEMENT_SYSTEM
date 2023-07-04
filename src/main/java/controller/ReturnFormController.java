package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import model.Item;
import model.ReturnItem;
import model.model2.ItemModel2;
import model.model2.ReturnModel2;
import tm.ReturnItemTm;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ReturnFormController {
    public TableView<ReturnItemTm> tblReturn;
    public TableColumn<ReturnItemTm,String> colReturnID;
    public TableColumn<ReturnItemTm,String> colItemCodeR;
    public TableColumn<ReturnItemTm,Date> colBilingDateR;
    public TableColumn<ReturnItemTm,Date> colReturnDate;
    public TableColumn<ReturnItemTm,Double> colPriceReturn;
    public JFXTextField txtSize;
    public JFXTextField txtQty;
    public JFXTextField txtItemId;
    public Button btnSave;
    public JFXDatePicker BillingD;
    public DatePicker dpReturnDate;
    public JFXComboBox<Item> cmbItemId;
    public JFXTextField txtOrderId;

    public void initialize() {
        //setComboBoxData();
        setConverter();
        setCellValueFactory();
        setTable();
    }

    public void setCellValueFactory(){
        colReturnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItemCodeR.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPriceReturn.setCellValueFactory(new PropertyValueFactory<>("price"));
        colBilingDateR.setCellValueFactory(new PropertyValueFactory<>("orderId"));
    }

    public void setTable(){
        try {
            tblReturn.setItems(FXCollections.observableArrayList(ReturnModel2.getAllForTable()));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setConverter() {
        cmbItemId.setConverter(new StringConverter<Item>() {
            @Override
            public String toString(Item item) {
                if (item == null) return null;
                return item.getIid();
            }

            @Override
            public Item fromString(String s) {
                return null;
            }
        });
    }

    public void setComboBoxData() {
        try {
            ArrayList<Item> allItem = ItemModel2.getAllItem();
            cmbItemId.setItems(FXCollections.observableArrayList(allItem));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtKeyPress(KeyEvent keyEvent) {
    }

    public void btnSearchItem(ActionEvent actionEvent) {
    }

    public void btnSearchReturn(ActionEvent actionEvent) {



    }

    public void btnReturnSave(ActionEvent actionEvent) {
        ReturnItem returnItem = collectData();
        Item selectedItem = cmbItemId.getSelectionModel().getSelectedItem();
        if(selectedItem==null)return;
        selectedItem.setQty(selectedItem.getQty()-Integer.parseInt(txtQty.getText()));
        if (returnItem != null) {
            try {
                boolean b = ReturnModel2.addData(returnItem,selectedItem);
                if(b){
                    new Alert(Alert.AlertType.INFORMATION,"Operation Success").show();
                    setTable();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Operation Failed").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void btnReturnUpdate(ActionEvent actionEvent) {
        ReturnItem returnItem = collectData();
        try {
            boolean b = ReturnModel2.updateData(returnItem);
            if(b){
                new Alert(Alert.AlertType.INFORMATION,"Operation Success").show();
                setTable();
            }else {
                new Alert(Alert.AlertType.ERROR,"Operation Failed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnReturnDelete(ActionEvent actionEvent) {
        ReturnItem returnItem = collectData();
        Optional<ButtonType> alert = new Alert(Alert.AlertType.CONFIRMATION, "Do Ypu Want To Delete",
                ButtonType.YES, ButtonType.NO).showAndWait();
        if(alert.isPresent()){
            if(alert.get().equals(ButtonType.YES)){
                try {
                    boolean b = ReturnModel2.deleteRecord(returnItem);
                    if(b){
                        new Alert(Alert.AlertType.INFORMATION,"Operation Success").show();
                        setTable();
                    }else {
                        new Alert(Alert.AlertType.ERROR,"Operation Failed").show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void cmbItemOnAction(ActionEvent actionEvent) {
        Item selectedItem = cmbItemId.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        txtSize.setText(selectedItem.getSize());
    }


    public ReturnItem collectData() {
        Item selectedItem = cmbItemId.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return new ReturnItem(txtItemId.getText(), 0 ,null , null,null ,null);
        return new ReturnItem(txtItemId.getText(), Integer.parseInt(txtQty.getText()), txtSize.getText(), selectedItem.getIid(),Date.valueOf(dpReturnDate.getValue()),txtOrderId.getText());
    }

    public void txtOrderIdOnAction(ActionEvent actionEvent) {
        System.out.println("called");
        try {
            ArrayList<Item> allItem = ReturnModel2.getAllByOrderId(txtOrderId.getText());
            for (Item ob:allItem
                 ) {
                System.out.println(ob.getIid());
            }
            cmbItemId.setItems(FXCollections.observableArrayList(allItem));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
