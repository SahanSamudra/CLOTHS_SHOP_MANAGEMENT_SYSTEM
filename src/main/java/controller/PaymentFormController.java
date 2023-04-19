package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.ItemModel;
import tm.Item;
import tm.ItemTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class PaymentFormController {

    public TableView tblItems;
    public TableColumn colItemId;
    public TableColumn colItemType;
    public TableColumn colSize;
    public TableColumn colPrice;
    public TableColumn colQty;
    public TableColumn colDate;
    public Label lblOrderId;
    public JFXTextField txtPrice;
    public JFXTextField txtSize;
    public JFXTextField txtItemType;
    public JFXTextField txtQty;
    public JFXTextField txtQtyOnHand;
    public Label lblDate1;
    public Label lblItemCount;
    public Label lblDiscount;
    public Label lblFinalTotal;
    public JFXButton btnPlaceOrder;
    public JFXComboBox cmbItemID;
    public JFXButton btnClear;
    public JFXButton btnAddToCart;
    ObservableList<String> itemCode= FXCollections.observableArrayList();
    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern qtyPattern = Pattern.compile("^[0-9]{1,6}$");

    private void storeValidations(){
        map.put(txtQty, qtyPattern);

    }

    int cartSelectedRawRemove = -1;


    public void initialize(){
        btnAddToCart.setDisable(true);
        storeValidations();
        initClock();
        setOrderId();
        itemCodeSetComboBoxData();


        tblItems.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {

            cartSelectedRawRemove= (int) newValue;
        });

    }

    private void itemCodeSetComboBoxData() {
        try {
            ResultSet allItemsCode = ItemModel.getAllItemsCode();

            while (allItemsCode.next()){

                itemCode.add(allItemsCode.getString(1));

            }
            cmbItemID.setItems(itemCode);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public Label lblDate;
    public Label lblTime;

    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));


            SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            lblDate.setText(formatter2.format(date));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void setOrderId(){

        try {
            lblOrderId.setText(new  OrderFormController().getOrderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnPlaceOrder(ActionEvent actionEvent) {
    }

    public void btnClear(ActionEvent actionEvent) {
    }

    public void btnAddToCart(ActionEvent actionEvent) {
    }


    public void itemSelectOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int selectedIndex = cmbItemID.getSelectionModel().getSelectedIndex();
        String s = itemCode.get(selectedIndex);
        Item item = ItemModel.search(s);
        int qty = item.getQty();
        String size = item.getSize();
        String supplier = item.getSupplier();
        String type = item.getType();
        double price = item.getPrice();
        txtQtyOnHand.setText(qty+"");
        txtSize.setText(size);
        txtPrice.setText(price+"");
    }
}
