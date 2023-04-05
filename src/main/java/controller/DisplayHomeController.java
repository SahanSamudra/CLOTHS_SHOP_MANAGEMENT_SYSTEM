package controller;

import javafx.collections.ObservableList;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

import static javafx.collections.FXCollections.observableArrayList;

public class DisplayHomeController {
    public AnchorPane DisplayHomeContext;


    public PieChart clothesPieChart;
    public javafx.scene.chart.PieChart PieChart;
    public AreaChart areaChart;


    public void loadClothesPieChart() throws SQLException, ClassNotFoundException {
        ObservableList<PieChart.Data> pieChartData = observableArrayList(
                new PieChart.Data("Pants", 25),
                new PieChart.Data("Shirt", 20),
                new PieChart.Data("T-Shirt", 30),
                new PieChart.Data("Gowns", 10),
                new PieChart.Data("Other", 15)
        );
        clothesPieChart.setData(pieChartData);

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

        XYChart.Series series =new XYChart.Series();
        series.getData().add(new XYChart.Data("0",2));
        series.getData().add(new XYChart.Data("1",23));
        series.getData().add(new XYChart.Data("2",65));
        series.getData().add(new XYChart.Data("3",68));
        series.getData().add(new XYChart.Data("4",32));
        series.getData().add(new XYChart.Data("5",56));
        series.getData().add(new XYChart.Data("6",76));
        series.getData().add(new XYChart.Data("7",44));
        series.getData().add(new XYChart.Data("8",50));
        areaChart.getData().add(series);

    }


    public void initialize() {


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
}










