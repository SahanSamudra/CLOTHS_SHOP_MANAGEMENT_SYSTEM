package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Item;
import model.ItemModel;
import model.model2.ItemModel2;
import tm.ItemTm;
import util.ValidationUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class ItemFormController {
    public AnchorPane ItemContext;
    public TableColumn colMTotalCost;
    public JFXTextField txtId;

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

    public JFXComboBox<String> cmbType;
    public JFXComboBox<String> cmbSupplier;
    public JFXDatePicker DatePicker;
    public Button btnSave;





    private void setCmbTypeComboxData() {
        ObservableList<String> type=FXCollections.observableArrayList();
        type.add("Shirt");
        type.add("T-shirt");
        type.add("Pants");
        type.add("Gowns");
        type.add("Frock");
        type.add("Other");
        cmbType.setItems(type);
    }



    public void  initialize() throws SQLException, ClassNotFoundException {

        setCmbTypeComboxData();
        loadSupIDs();
            colItemId.setCellValueFactory(new PropertyValueFactory<>("Iid"));
            colItemType.setCellValueFactory(new PropertyValueFactory<>("type"));
            colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
            colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
           colMTotalCost.setCellValueFactory(new PropertyValueFactory<>("tcost"));

        try {
            loadAllItems(new ItemSaveController().getAllItem());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void loadAllItems(ArrayList<Item> items){
        /*ObservableList<ItemTm> observableList= FXCollections.observableArrayList();
        items.forEach(m->{
            observableList.add(new ItemTm(m.getIid(),m.getType(),m.getSize(),m.getPrice(),m.getQty(),m.getSupplier(),
                    m.getTcost()));
            tblItems.setItems(observableList);
        });*/
        ObservableList<ItemTm> observableList= FXCollections.observableArrayList();
        try {
            ArrayList<Item> allItem = ItemModel2.getAllItem();
            for (Item tem:allItem){
                observableList.add(new ItemTm(tem.getIid(),tem.getType(),tem.getPrice(),tem.getQty(),tem.getSize(),tem.getSupplier(),tem.getTcost()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        tblItems.setItems(observableList);


    }
    private void setData(Item m1) {
        txtId.setText(m1.getIid());
        cmbSupplier.setValue(m1.getSupplier());
        txtSize.setText(m1.getSize());
        cmbType.setValue(m1.getType());
        txtQty.setText(String.valueOf(m1.getQty()));
        txtPrice.setText(String.valueOf(m1.getPrice()));


    }

    public void loadSupIDs() throws SQLException, ClassNotFoundException {
        List<String> supids=new SupplierSaveController().getSupplierIDs();
        cmbSupplier.getItems().addAll(supids);

    }


    public void btnSearchItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item M1 = new ItemSaveController().getItems(txtId.getText());
        if (M1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(M1);
        }
    }

    public void btnItemSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        System.out.println(txtId.getText()+"==============");

        double Tcost=Double.parseDouble(txtQty.getText())*Double.parseDouble(txtPrice.getText());

        Item m= new Item(txtId.getText(),cmbType.getValue(),Double.parseDouble(txtPrice.getText()),Integer.parseInt(txtQty.getText()),String.valueOf(txtSize.getText()),
                cmbSupplier.getValue(), Tcost);
 





        if (new ItemSaveController().saveItem(m)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Save", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadAllItems(new ItemSaveController().getAllItem());

            }
         /*   loadAllMobiles(new MobileController().getAllMobile());
            new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added").show();*/
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void btnItemUpdate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        double Tcost=Double.parseDouble(txtQty.getText())*Double.parseDouble(txtPrice.getText());
        Item m= new Item(txtId.getText(),cmbType.getValue(),Double.parseDouble(txtPrice.getText()),Integer.parseInt(txtQty.getText()),String.valueOf(txtSize.getText()),
                cmbSupplier.getValue(), Tcost);


        if (new ItemSaveController().updateItems(m)) {  Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Update", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadAllItems(new ItemSaveController().getAllItem());

            }

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void btnItemDelete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (new ItemSaveController().deleteItems(txtId.getText())) {
            loadAllItems(new ItemSaveController().getAllItem());
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted..").show();
            txtId.clear();

        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void txtKeyPress(KeyEvent keyEvent) {
    }

    public void KeyPress(KeyEvent keyEvent) {

    }





 /*   public void txtKeyPress(KeyEvent keyEvent) {
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
    }*/
}
