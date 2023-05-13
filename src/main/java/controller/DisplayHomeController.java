package controller;

import DB.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import util.CrudUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static javafx.collections.FXCollections.observableArrayList;

public class DisplayHomeController {
    public AnchorPane DisplayHomeContext;


    public PieChart clothesPieChart;
    public javafx.scene.chart.PieChart PieChart;
    public AreaChart areaChart;
    public Label lblSupplierCount;
    public Label lblEmployeeCount;
    public Label lblCustomerCustomer;
    public Label lblOrderCount;
    public Label lblReturnCount;
    public Label lblItemCount;

  /*  type.add("Shirt");
        type.add("T-shirt");
        type.add("Pants");
        type.add("Gowns");
        type.add("Frock");
        type.add("Other");*/
    private void loadClothesPieChart() throws SQLException, ClassNotFoundException {


        ObservableList<PieChart.Data> pieChartData = observableArrayList(
                new PieChart.Data("Pants", 25),
                new PieChart.Data("Shirt", 20),
                new PieChart.Data("T-Shirt", 30),
                new PieChart.Data("Gowns", 10),
                new PieChart.Data("Other", 15)
        );
        clothesPieChart.setData(pieChartData);

   /*     Shirt
        T-shirt
        Pants
        Gowns
        Frock
        Other*/


    }

    public void loadPieChart() throws SQLException, ClassNotFoundException {
        ObservableList<javafx.scene.chart.PieChart.Data> pieChartData = observableArrayList(
                new PieChart.Data("January", 13),
                new PieChart.Data("February", 25),
                new PieChart.Data("March", 10),
                new PieChart.Data("April", 43),
                new PieChart.Data("April", 25),
                new PieChart.Data("May", 22),
                new PieChart.Data("June", 12),
                new PieChart.Data("July", 82),
                new PieChart.Data("August", 22),
                new PieChart.Data("September", 32),
                new PieChart.Data("October", 24),
                new PieChart.Data("November", 22),
                new PieChart.Data("December", 22));
        PieChart.setData(pieChartData);


        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("0", 2));
        series.getData().add(new XYChart.Data("1", 23));
        series.getData().add(new XYChart.Data("2", 65));
        series.getData().add(new XYChart.Data("3", 68));
        series.getData().add(new XYChart.Data("4", 32));
        series.getData().add(new XYChart.Data("5", 56));
        series.getData().add(new XYChart.Data("6", 76));
        series.getData().add(new XYChart.Data("7", 44));
        series.getData().add(new XYChart.Data("8", 50));
        areaChart.getData().add(series);

        for (int i = 6; i>0; i--) {

        }

    }
    public void getLineChartData(String date){
        String now= String.valueOf(LocalDate.now());
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT total FROM item_reports WHERE rdate=?", now);
            double tot = 0;
            if (resultSet.next()){
                String string = resultSet.getString(1);
                double v = Double.parseDouble(string);
                tot+=v;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    public void initialize() {


        try {
            ItemCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            ReturnCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            OrderCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            CustomerCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            EmployeeCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            SupplierCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            loadPieChart();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        clothesPieChart.setTitle("Types of Clothes");

        try {
            loadClothesPieChart();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void SupplierCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("Select * From Supplier");
        ResultSet rst = stm.executeQuery();
        int a = 0;
        while (rst.next()) {
            a++;
            lblSupplierCount.setText(String.valueOf(a));
        }
    }

    public void EmployeeCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("Select * From Employee");
        ResultSet rst = stm.executeQuery();
        int a = 0;
        while (rst.next()) {
            a++;
            lblEmployeeCount.setText(String.valueOf(a));
        }
    }

    public void CustomerCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm= DbConnection.getInstance().getConnection().prepareStatement("Select * From Customer");
        ResultSet rst=stm.executeQuery();
        int a=0;
        while (rst.next()){
            a++;
            lblCustomerCustomer.setText(String.valueOf(a));
        }

    }

    public void OrderCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("Select * From Orders");
        ResultSet rst = stm.executeQuery();
        int a = 0;
        while (rst.next()) {
            a++;
            lblOrderCount.setText(String.valueOf(a));
        }
    }


    public void ReturnCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("Select * From returnItem");
        ResultSet rst = stm.executeQuery();
        int a = 0;
        while (rst.next()) {
            a++;
            lblReturnCount.setText(String.valueOf(a));
        }
    }

    public void ItemCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("Select * From Item");
        ResultSet rst = stm.executeQuery();
        int a = 0;
        while (rst.next()) {
            a++;
            lblItemCount.setText(String.valueOf(a));
        }
    }
}










