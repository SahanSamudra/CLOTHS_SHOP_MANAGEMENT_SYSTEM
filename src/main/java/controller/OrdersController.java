package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.ItemReports;
import model.Order;
import tm.CustomerTm;
import tm.OrderDetailsTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersController {

    public TableView tblOrder;
    public TableColumn colOrderId;
    public TableColumn colCID;
    public TableColumn colOrderDate;
    public TableColumn colOTime;
    public TableColumn colOCost;
    public TableView tblOrderDetails;
    public TableColumn colItemCodeR;
    public TableColumn colOidR;
    public TableColumn colUnitPriceR;
    public TableColumn colItemCountR;
    public TableColumn colTotalR;
    public TableColumn colDateR;

    public void initialize(){

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("oid"));
        colCID.setCellValueFactory(new PropertyValueFactory<>("ocid"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("odate"));
        colOTime.setCellValueFactory(new PropertyValueFactory<>("otime"));
        colOCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        colItemCodeR.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colOidR.setCellValueFactory(new PropertyValueFactory<>("oid"));
        colUnitPriceR.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        colItemCountR.setCellValueFactory(new PropertyValueFactory<>("itemcount"));
        colTotalR.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDateR.setCellValueFactory(new PropertyValueFactory<>("date"));


        try {
            loadOrderDetails(new OrderController().getAllOderDetails());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadOrders(new OrderController().getAllOders());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void loadOrders(ArrayList<Order> orders) {
        ObservableList<Order> observableList = FXCollections.observableArrayList();
        orders.forEach(c -> {
            observableList.add(new Order(c.getOid(), c.getOcid(), c.getOdate(), c.getOtime(),c.getCost()));
            tblOrder.setItems(observableList);
        });

    }

    public void loadOrderDetails(ArrayList<OrderDetailsTm> orderDetailsTms) {
        ObservableList<OrderDetailsTm> observableList = FXCollections.observableArrayList();
        orderDetailsTms.forEach(c -> {
            observableList.add(new OrderDetailsTm(c.getItemCode(), c.getOid(), c.getUnitprice(), c.getItemcount(),c.getTotal(),c.getDate()));
            tblOrderDetails.setItems(observableList);
        });

    }
}
