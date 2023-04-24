package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Customer;
import model.Item;
import model.ItemReports;
/*import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;*/
import model.Order;
import tm.PlaceOrderTm;
import util.ValidationUtil;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class PaymentFormController {


    public Label lblDate;
    public Label lblTime;
    public AnchorPane loadCustomerContext;
    public JFXTextField txtBrandName;
    public JFXTextField txtPrice;
    public JFXTextField txtWarrantyDue;
    public JFXTextField txtItemType;
    public JFXTextField txtModel;
    public JFXTextField txtDesceiption;
    public JFXTextField txtQty;
    public JFXTextField txtDiscount;
    public Label lblItemCount;
    public Label lblDiscount;
    public Label lblTotal;
    public Label viewCustomerInOrderContext;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerContact;
    public JFXTextField txtCustomerID;
    public TableView tblBilling;
    public TableColumn colItemID;

    public TableColumn colBrandName;
    public TableColumn colModel;
    public TableColumn colWarramtyDue;
    public TableColumn colDescription;
    public TableColumn colPrice;
    public TableColumn colQty;
    public JFXTextField txtIMEI;
    public TableColumn colItemType;
    public TableColumn colImei;
    public ComboBox cmbCustomer;
    public JFXTextField txtQtyOnHand;
    public ComboBox cmbItemCode;
    public TableColumn colItemTotal;
    public Label lblFinalTotal;
    public Label lblOrderId;
    public Button btnAddToCart;
    public JFXButton btnClear;
    public JFXButton btnPlaceOrder;
    public JFXTextField txtSize;
    public TableColumn colSize;
    public TableColumn colDate;

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern qtyPattern = Pattern.compile("^[0-9]{1,2}$");

    private void storeValidations(){
        map.put(txtQty, qtyPattern);

    }

    int cartSelectedRawRemove = -1;


    public void initialize(){
        lblItemCount.setText("0");
        btnAddToCart.setDisable(true);
        storeValidations();

        colItemID.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colItemTotal.setCellValueFactory(new PropertyValueFactory<>("cost"));



        cmbCustomer.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) ->{

            try {
                setData(new  CustomerSaveController().getCustomer((String)newValue));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }));/////////





        cmbItemCode.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) ->{

            try {
                setData(new  ItemSaveController().getItems((String)newValue));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }));/////////

        try {
            loadItemIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            loadCusIDs();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        initClock();
        setOrderId();


        tblBilling.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {

            cartSelectedRawRemove= (int) newValue;
        });

    }

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


    public void loadCusIDs() throws SQLException, ClassNotFoundException {
        List<String> cusids=new CustomerSaveController().getCusIDs();
        cmbCustomer.getItems().addAll(cusids);

    }


    private void setData(Customer cc1) {
        txtCustomerID.setText(cc1.getCustomerId());



    }


    public void loadItemIds() throws SQLException, ClassNotFoundException {
        List<String> itemIds=new ItemSaveController().getItemIds();
        cmbItemCode.getItems().addAll(itemIds);

    }



    private void setData(Item mm1) {

        txtItemType.setText(mm1.getType());
        txtSize.setText(mm1.getSize());
        txtPrice.setText(String.valueOf(mm1.getPrice()));
        txtQtyOnHand.setText(String.valueOf(mm1.getQty()));



    }
    private void setOrderId(){

        try {
            lblOrderId.setText(new  OrderController().getOrderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    ObservableList<PlaceOrderTm> oblist =FXCollections.observableArrayList();
    public void btnAddToCart(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String ItemType = txtItemType.getText();
        String ItemSize = txtSize.getText();
        int QtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        int QtyForCustomer = Integer.parseInt(txtQty.getText());
        lblItemCount.setText(String.valueOf(Integer.parseInt(lblItemCount.getText())+QtyForCustomer));
        double UnitPrice =Double.parseDouble(txtPrice.getText());
        double Total = QtyForCustomer * UnitPrice;

        if (QtyOnHand<QtyForCustomer){
            new Alert(Alert.AlertType.WARNING,"Invalid Qty").show();
            return;
        }



        PlaceOrderTm tm= new PlaceOrderTm((String) cmbItemCode.getValue(),
                ItemType,
                ItemSize,
                QtyForCustomer,
                UnitPrice,
                Total

        );

        int rowNumber=isExists(tm);

        if (rowNumber==-1){
            oblist.add(tm);

        }else {
            PlaceOrderTm temp = oblist.get(rowNumber);
            PlaceOrderTm newTm = new PlaceOrderTm(
                    temp.getCode(),temp.getType(),
                    temp.getSize(),temp.getQty()+QtyForCustomer,
                    UnitPrice,Total+temp.getCost()
            );

            if (QtyOnHand<temp.getQty()){
                new Alert(Alert.AlertType.WARNING,"Invalid Qty").show();
                return;
            }

            oblist.remove(rowNumber);
            oblist.add(newTm);
        }
        tblBilling.setItems(oblist);

        calculateCost();
    }


    private int isExists(PlaceOrderTm tm){

        for (int i = 0; i < oblist.size() ; i++) {
            if (tm.getCode().equals(oblist.get(i).getCode())){
                return i;
            }
        }

        return -1;
    }

    void calculateCost(){

        double ttl=0;
        for (PlaceOrderTm tm:oblist
        ) {
            ttl+=  tm.getCost();
        }
        lblFinalTotal.setText(ttl+"  /=");
    }


    public void btnClear(ActionEvent actionEvent) {

        if (cartSelectedRawRemove==-1){
            new Alert(Alert.AlertType.WARNING,"Please Select Row").show();
        }else {
            oblist.remove(cartSelectedRawRemove);
            calculateCost();
            tblBilling.refresh();
        }
    }

    public void btnPlaceOrder(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ArrayList<ItemReports> items= new ArrayList<>();
        double total=0;
        for (PlaceOrderTm tempTm:oblist
        ) {

            total+=tempTm.getCost();
            items.add(new ItemReports(tempTm.getCode(),tempTm.getPrice(),
                    tempTm.getQty(),tempTm.getCost(),lblDate.getText())



            );

        }
        Order order =new Order(
                lblOrderId.getText(),
                txtCustomerID.getText(),
                lblDate.getText(),
                lblTime.getText(),
                total,
                items
        );
        if (new OrderController().placeOrder(order)){

            /*try {
                JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("../invoice/Dream Bill.jrxml"));
                JasperReport compileReport = JasperCompileManager.compileReport(design);
                Get all values from table
                ObservableList<PlaceOrderTm> items1 = tblBilling.getItems();
                Create a Bean Array Data Source and pass the table values to it

                setting values for parameters
                String OrderId = lblOrderId.getText();
                String Cost =lblFinalTotal.getText();


                Setting parameter values
                HashMap map = new HashMap();
                map.put("OrderId", OrderId);// id= param name of report // customerID= input value of text field
                map.put("Cost", Cost);

                JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, new JRBeanArrayDataSource(items1.toArray()));
                JasperViewer.viewReport(jasperPrint, false);

                //if you wanna print the report directly you can use this instead of JasperViewer
                JasperPrintManager.printReport(jasperPrint,false);

            } catch (JRException e) {
                e.printStackTrace();
            }*/

        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }
    }

    public void KeyPress(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnAddToCart);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
            }
        }
    }

    public void itemSelectOnAction(ActionEvent actionEvent) {
    }

    public void txtQtyKeyPressOnAction(KeyEvent keyEvent) {
        KeyPress(keyEvent);
    }
}
