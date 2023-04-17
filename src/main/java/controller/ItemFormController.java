package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class ItemFormController {
    public AnchorPane ItemContext;
    public TableView tblItems;
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
    public JFXComboBox cmbType;
    public JFXComboBox cmbSupplier;
    public JFXDatePicker DatePicker;
    public Button btnSave;

    public void txtKeyPress(KeyEvent keyEvent) {
    }

    public void btnSearchItem(ActionEvent actionEvent) {
    }

    public void btnItemSave(ActionEvent actionEvent) {
    }

    public void btnItemUpdate(ActionEvent actionEvent) {
    }

    public void btnItemDelete(ActionEvent actionEvent) {
    }
}
