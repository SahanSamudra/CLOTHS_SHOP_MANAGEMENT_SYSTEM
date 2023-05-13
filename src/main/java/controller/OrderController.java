package controller;

import DB.DbConnection;
import model.Item;
import model.Customer;
import model.ItemReports;
import model.Order;
import tm.OrderDetailsTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {

    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst =
                DbConnection.getInstance().getConnection().
                        prepareStatement("SELECT oid FROM Orders ORDER BY oid DESC LIMIT 1"
                        ).executeQuery();
        if (rst.next()) {
            int tempId = Integer.
                    parseInt(rst.getString("oid").split("-")[1]);
            tempId = (tempId + 1);

                System.out.println(tempId);
                return String.format("P-%03d",tempId);

        } else {
            return "P-001";
        }

    }


    public boolean placeOrder(Order order) {

        Connection con = null;
        try {
            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm = con.
                    prepareStatement("INSERT into Orders VALUES (?,?,?,?,?)");
            stm.setObject(1, order.getOid());
            stm.setObject(2, order.getOcid());
            stm.setObject(3, order.getOdate());
            stm.setObject(4, order.getOtime());
            stm.setObject(5, order.getCost());


            if (stm.executeUpdate() > 0) {


                if (SaveItemReports(order.getOid(), order.getItems())) {
                    con.commit();
                    return true;
                } else {
                    con.rollback();
                    return false;
                }

            } else {
                con.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    private boolean SaveItemReports(String orderId, ArrayList<ItemReports> items) throws SQLException, ClassNotFoundException {
        for (ItemReports temp : items


        ) {
            PreparedStatement stm =
                    DbConnection.getInstance().getConnection().
                            prepareStatement("INSERT INTO `Item_Reports` VALUES (?,?,?,?,?,?)");
            stm.setObject(2, orderId);
            stm.setObject(1, temp.getItemCode());
            stm.setObject(3, temp.getUnitprice());
            stm.setObject(4, temp.getItemcount());
            stm.setObject(5, temp.getTotal());
            stm.setObject(6, temp.getDate());
            if (stm.executeUpdate() > 0) {


                if (updateQty(temp.getItemCode(), temp.getItemcount())) {

                } else {
                    return false;
                }

            } else {
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String itemCode, int Qty) throws SQLException, ClassNotFoundException {


        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement
                        ("UPDATE Item SET qty=(qty-" + Qty
                                + ") WHERE Iid=?");
        stm.setObject(1, itemCode);
        return stm.executeUpdate() > 0;
    }

    public ArrayList<Order> getAllOders() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Orders");
        ResultSet rst = stm.executeQuery();
        ArrayList<Order> orders = new ArrayList<>();
        while (rst.next()) {
            orders.add(new Order(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getDouble(5)));

        }
        return orders;
    }

    public ArrayList<OrderDetailsTm> getAllOderDetails() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item_Reports");
        ResultSet rst = stm.executeQuery();
        ArrayList<OrderDetailsTm> orderDetailsTms = new ArrayList<>();
        while (rst.next()) {
            orderDetailsTms.add(new OrderDetailsTm(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4), rst.getDouble(5), rst.getString(6)));

        }
        return orderDetailsTms;
    }

    public Order getOrder(String oid) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Orders WHERE oid=?");
        preparedStatement.setObject(1, oid);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return new Order(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5)

            );

        } else {
            return null;
        }
    }

}
