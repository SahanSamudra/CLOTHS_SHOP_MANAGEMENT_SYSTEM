package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.ItemModel;
import model.Supplier;
import tm.Item;
import tm.ItemTm;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemFormController {
    public AnchorPane ItemContext;

    @FXML
    private TableView<ItemTm> tblItems;


    @FXML
    void cmbSupplier0(ActionEvent event) {

    }

    @FXML
    void cmbTypeO(ActionEvent event) {

    }

    public TableColumn colItemId;
    public TableColumn colItemType;
    public TableColumn colSize;
    public TableColumn colPrice;
    public TableColumn colQty;
    public TableColumn colDate;
    public TableColumn colSupplier;
    public JFXTextField txtSize;
    public JFXTextField txtPrice;
    public JFXTextField txtQty;
    public JFXTextField txtItemId;
    public JFXComboBox<String> cmbType;
    public JFXComboBox<String> cmbSupplier;
    public JFXDatePicker DatePicker;
    public Button btnSave;





    public void txtKeyPress(KeyEvent keyEvent) {
    }

    public void btnSearchItem(ActionEvent actionEvent) {
        String id = txtItemId.getText();
        try {
            Item item = ItemModel.search(id);
            if (item != null) {
                txtItemId.setText(item.getId());
                cmbType.getSelectionModel().getSelectedItem();
                txtPrice.setText(String.valueOf(item.getPrice()));
                txtQty.setText(String.valueOf(item.getQty()));
                txtSize.getText();
                cmbSupplier.getSelectionModel().getSelectedItem();
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer Not Found").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnItemSave(ActionEvent actionEvent) {
        String id = txtItemId.getText();
        String type = String.valueOf(cmbType.getSelectionModel().getSelectedItem());
        Double price = Double.valueOf(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        String size = txtSize.getText();
        String supliyer = String.valueOf(cmbSupplier.getSelectionModel().getSelectedItem());

        Item item = new Item(id, type, price, qty, size, supliyer);

        try {
            boolean isSave = ItemModel.save(item);
            if (isSave) {
                loadTable();
                new Alert(Alert.AlertType.CONFIRMATION, "OK").show();

            }
        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnItemUpdate(ActionEvent actionEvent) throws ClassNotFoundException {
        String id = txtItemId.getText();
        String type = String.valueOf(cmbType.getSelectionModel().getSelectedItem());
        Double price = Double.valueOf(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        String size = txtSize.getText();
        String supliyer = String.valueOf(cmbSupplier.getSelectionModel().getSelectedItem());

        Item item = new Item(id, type, price, qty, size, supliyer);
        try {
            boolean isUpdate = ItemModel.update(item);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "OK").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void btnItemDelete(ActionEvent actionEvent) {
        String id = txtItemId.getText();
        try {
            boolean isDelete = ItemModel.delete(id);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "OK").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    void loadTable() {
        try {
            ObservableList<ItemTm> obList = FXCollections.observableArrayList();
            ResultSet all = ItemModel.getAll();
            while (all.next()){
                String stringPrice = all.getString(3);
                String qty = all.getString(4);
                obList.add(new ItemTm(all.getString(1),all.getString(2),Double.parseDouble(stringPrice),Integer.parseInt(qty)
                        ,all.getString(5),all.getString(6)));
            }
            tblItems.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void initialize() {
        setCellValueFactory();
        loadSupplierId();
        setCmbTypeComboxData();
        loadTable();
    }

    private void setCmbTypeComboxData() {
        ObservableList<String> type=FXCollections.observableArrayList();
        type.add("Shirt");
        type.add("Tshirt");
        cmbType.setItems(type);
    }

    private void loadSupplierId() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            List<String> supplierId = Supplier.searchSupplierId();
            for (String code : supplierId) {
                observableList.add(code);
            }
            cmbSupplier.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItemType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supID"));
    }
}
